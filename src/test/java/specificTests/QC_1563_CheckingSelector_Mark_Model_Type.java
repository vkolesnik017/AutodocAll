package specificTests;

import ATD.Excel;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import static ATD.CommonMethods.openPage;
import static ATD.Excel.parseExcel;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.close;

public class QC_1563_CheckingSelector_Mark_Model_Type {
    private final String dataFile = "C://Autotests/files/data/QC_1563_data.xls";
    private final String result = "C://Autotests/files/res/QC_1563_result.txt";

    private String shop = System.getenv("ShopFromJenkins").toLowerCase();
//    private String shop = "DE";

    @BeforeClass
    void setUp() throws IOException {
        setUpBrowser(false, "chrome", "77.0");
        writer(result, true, shop);
    }

    @DataProvider(name = "data", parallel = true)
    Object[] dataProvider() {
        return new Excel().setUpAllCellFromExcel(dataFile, shop);
    }


    @Test(dataProvider = "data")
    public void listing(String data) throws Exception {
        System.out.println(data);
        String makerId = parseExcel(data)[0];
        String modelId = parseExcel(data)[4];
        String groupId = parseExcel(data)[2];
        String groupTitle = parseExcel(data)[3];
        String brand = parseExcel(data)[1];
        String model = parseExcel(data)[7];


        String shop = calculateUrl(this.shop.trim());
        String startUrl = "https://test." + shop + "maker_id=" + makerId + "&model_id=" + modelId + "&group_id=" + groupId;
        openPage(startUrl);

        //locators for selector
        SelenideElement markFromSelector = $(By.xpath("//select[@id='form_maker_id']//option[@selected]"));
        SelenideElement modelFromSelector = $(By.xpath("//select[@id='form_model_id']//option[@selected]"));
        SelenideElement groupModelTitleFromSelector = $(By.xpath("//select[@id='form_model_id']//option[@selected]/.."));

        // markFromSelector
        markFromSelector.shouldBe(Condition.visible);
        String markFromSelectorText = markFromSelector.getAttribute("innerText");

        //modelFromSelector
        modelFromSelector.shouldBe(Condition.visible);
        String modelFromSelectorText = modelFromSelector.getAttribute("innerText");
        modelFromSelectorText = substring_2(model, modelFromSelectorText, startUrl);

        //modelGroupTitleFromSelector
        groupModelTitleFromSelector.shouldBe(Condition.visible);
        String modelGroupTitleFromSelectorText = groupModelTitleFromSelector.getAttribute("label");

        if (!brand.equals(markFromSelectorText))
            writer(result, true, "MakerTitle" + "#" + brand + "#" + "MakerTitle from page" + "#" + markFromSelectorText + "#" + startUrl);

        if (!model.equals(modelFromSelectorText))
            writer(result, true, "ModelTitle" + "#" + model + "#" + "ModelTitle from page" + "#" + modelFromSelectorText + "#" + startUrl);

        if (!groupTitle.equals(modelGroupTitleFromSelectorText))
            writer(result, true, "GroupTitle" + "#" + groupTitle + "#" + "GroupTitle from page" + "#" + modelGroupTitleFromSelectorText + "#" + startUrl);

        close();

    }

    private void writer(String fileName, boolean append, String write) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, append), StandardCharsets.UTF_8));
        System.out.println("Write in file" + " " + fileName);
        bufferedWriter.newLine();
        bufferedWriter.write(write);
        bufferedWriter.close();
    }


    private String calculateUrl(String shopFromTest) {
        String shop = null;
        switch (shopFromTest) {
            case "de":
                shop = "pkwteile.de/ersatzteile-suche?";
                break;
            case "fr":
                shop = "piecesauto24.com/rechercher?";
                break;
            case "it":
                shop = "autoparti.it/ricerca?";
                break;
            case "gb":
                shop = "buycarparts.co.uk/search?&";
                break;
            case "gr":
                shop = "antallaktikaonline.gr/search?";
                break;
            case "bg":
                shop = "avtochastionline24.bg/search?";
                break;
            case "hu":
                shop = "autoalkatreszonline24.hu/search?";
                break;
            case "cz":
                shop = "automobilovedily24.cz/search?";
                break;
            case "ro":
                shop = "autopieseonline24.ro/search?";
                break;
            case "pl":
                shop = "czesciauto24.pl/search?";
                break;
            case "nl":
                shop = "auto-onderdelen24.nl/search?";
                break;
            case "pt":
                shop = "autopecasonline24.pt/search?";
                break;
            case "fi":
                shop = "autonvaraosat24.fi/search?";
                break;
            case "dk":
                shop = "bildeleshop.dk/search?";
                break;
            case "se":
                shop = "bildelaronline24.se/search?";
                break;
            case "no":
                shop = "autodeler.co.no/search?";
                break;
            case "es":
                shop = "recambioscoches.es/search?";
                break;
            case "sk":
                shop = "autodoc.sk/search?";
                break;
            case "si":
                shop = "autodoc.si/search?";
                break;
            case "lt":
                shop = "autodoc.lt/search?";
                break;
            case "lv":
                shop = "autodoc.lv/search?";
                break;
            case "ee":
                shop = "autodoc.ee/search?";
                break;
        }
        return shop;
    }

    public String substring(String textFromData, String textFromSelector, String startUrl) throws IOException {
        try {

            int countDataType = StringUtils.countMatches(textFromData, "(");
            if (countDataType > 0) {
                textFromSelector = textFromSelector.substring(0, textFromSelector.indexOf(")") + 1).trim();
                System.out.println(textFromSelector);
            } else {
                textFromSelector = textFromSelector.substring(0, textFromSelector.indexOf("(")).trim();
                System.out.println(textFromSelector);
            }
        } catch (Exception e) {
            writer(result, true, "Fail" + "#" + startUrl);
        }
        return textFromSelector;
    }

    private String substring_2(String textFromData, String textFromSelector, String startUrl) throws IOException {
        try {
            textFromSelector = textFromSelector.substring(0, textFromSelector.lastIndexOf("(")).trim();
//            System.out.println(textFromSelector);
        } catch (Exception e) {
            writer(result, true, "Fail" + "#" + startUrl);
        }
        return textFromSelector;
    }

}

