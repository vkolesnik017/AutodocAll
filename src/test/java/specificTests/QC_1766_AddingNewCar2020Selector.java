package specificTests;

import ATD.Excel;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
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

public class QC_1766_AddingNewCar2020Selector {
    private final String dataFile = "C://Autotests/files/data/QC_1766_data.xls";
    private final String result = "C://Autotests/files/res/QC_1766_result.txt";


    @BeforeClass
    void setUp() throws IOException {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "data", parallel = false)
    Object[] dataProvider() {
        return new Excel().setUpAllCellFromExcel(dataFile);
    }


    @Test(dataProvider = "data")
    public void listing(String data) throws Exception {
        System.out.println(data);
        String makerId = parseExcel(data)[0].trim();
        String groupId = parseExcel(data)[2].trim();
        String modelId = parseExcel(data)[4].trim();
        String carId = parseExcel(data)[6].trim();
        String makerTitle = parseExcel(data)[1].trim().replaceAll("Ë", "E");
        String modelTitle = parseExcel(data)[5].trim();
        String groupTitle = parseExcel(data)[3].trim();
        String carTitle = parseExcel(data)[7].trim();


        String startUrl = "https://www.buycarparts.co.uk/search?&maker_id=" + makerId + "&model_id=" + modelId + "&group_id=" + groupId + "&car_id=" + carId;
        System.out.println(startUrl);
        openPage(startUrl);

        try {
            //locators for selector
            SelenideElement markFromSelector = $(By.xpath("//select[@id='form_maker_id']//option[@selected]"));
            SelenideElement modelFromSelector = $(By.xpath("//select[@id='form_model_id']//option[@selected]"));
            SelenideElement groupModelTitleFromSelector = $(By.xpath("//select[@id='form_model_id']//option[@selected]/.."));
            SelenideElement engineFromSelector = $(By.xpath("//select[@id='form_car_id']//option[@selected]"));

            // markFromSelector
            markFromSelector.shouldBe(Condition.visible);
            String markFromSelectorText = markFromSelector.getAttribute("innerText").replaceAll("Ë", "E");

            //modelFromSelector
            modelFromSelector.shouldBe(Condition.visible);
            String modelFromSelectorText = modelFromSelector.getAttribute("innerText");
            modelFromSelectorText = substring(modelFromSelectorText, startUrl);

            //modelGroupTitleFromSelector
            groupModelTitleFromSelector.shouldBe(Condition.visible);
            String modelGroupTitleFromSelectorText = groupModelTitleFromSelector.getAttribute("label");

            //engineFromSelector
            groupModelTitleFromSelector.shouldBe(Condition.visible);
            String engineFromSelectorText = engineFromSelector.getAttribute("innerText");
            engineFromSelectorText = substringForEngine(engineFromSelectorText, carTitle, startUrl);


            if (!makerTitle.equals(markFromSelectorText))
                writer(result, true, "MakerTitle" + "#" + makerTitle + "#" + "MakerTitle from page" + "#" + markFromSelectorText + "#" + startUrl);

            if (!modelTitle.equals(modelFromSelectorText))
                writer(result, true, "ModelTitle" + "#" + modelTitle + "#" + "ModelTitle from page" + "#" + modelFromSelectorText + "#" + startUrl);

            if (!groupTitle.equals(modelGroupTitleFromSelectorText))
                writer(result, true, "GroupTitle" + "#" + groupTitle + "#" + "GroupTitle from page" + "#" + modelGroupTitleFromSelectorText + "#" + startUrl);

            if (!carTitle.equals(engineFromSelectorText))
                writer(result, true, "CarTitle" + "#" + carTitle + "#" + "CarTitle from page" + "#" + engineFromSelectorText + "#" + startUrl);

        } catch (ElementNotFound element) {
            writer(result, true, "Trouble with element in selector" + "#" + startUrl);
        }
        close();

    }

    private void writer(String fileName, boolean append, String write) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, append), StandardCharsets.UTF_8));
        System.out.println("Write in file" + " " + fileName);
        bufferedWriter.newLine();
        bufferedWriter.write(write);
        bufferedWriter.close();
    }

    private String substring(String textFromSelector, String startUrl) throws IOException {
        try {
            textFromSelector = textFromSelector.substring(0, textFromSelector.lastIndexOf("(")).trim();
//            System.out.println(textFromSelector);
        } catch (Exception e) {
            writer(result, true, "Fail with substring" + "#" + startUrl);
        }
        return textFromSelector;
    }

    private String substringForEngine(String textFromSelector, String carTitleText, String startUrl) throws IOException {
        try {
            if (carTitleText.contains("(")) {
                textFromSelector = textFromSelector.substring(0, textFromSelector.indexOf(")") + 1).trim();
            } else {
                textFromSelector = textFromSelector.substring(0, textFromSelector.indexOf("(")).trim();
            }
            System.out.println(textFromSelector);
        } catch (Exception e) {
            writer(result, true, "Fail with substring" + "#" + startUrl);
        }
        return textFromSelector;
    }

}

