package ATD.SpecificTests;

import ATD.CommonMethods;
import Common.DataBase;
import Common.Excel;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static ATD.CommonMethods.openPage;
import static Common.Excel.parseExcel;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2112 {
    private CommonMethods commonMethods = new CommonMethods();

    private final String dataFile = "C://Autotests/files/data/QC_2112_data.xls";
    private final String result = "C://Autotests/files/res/QC_2112_data.txt";

        private String shop = System.getenv("ShopFromJenkins").toLowerCase();
//    private String shop = "de";

    @BeforeClass
    void setUp() throws IOException {
        setUpBrowser(false, "chrome", "77.0", false);
        Configuration.pageLoadStrategy="normal";
        commonMethods.writerInFile(result, true, shop);
    }

    @DataProvider(name = "data", parallel = true)
    Object[] dataProvider() {
        return new Excel().setUpAllCellFromExcel(dataFile, shop);
    }


    @Test(dataProvider = "data")
    public void listing(String data) throws Exception {
        System.out.println(data);
        String makerId = parseExcel(data)[0].trim();
        String modelId = parseExcel(data)[2].trim();
        String modelFromData = parseExcel(data)[3].trim();

        String shop = new DataBase("ATD").getRouteByRouteName(this.shop, "main");
        System.out.println(shop + " SHOP FROM DB");
        String startUrl = "https://www." + shop + "/search?maker_id=" + makerId + "&model_id=" + modelId;
        openPage(startUrl);

        try {
            //locators for selector
            SelenideElement markFromSelector = $(By.xpath("//select[@id='form_maker_id']//option[@selected]"));
            SelenideElement modelFromSelector = $(By.xpath("//select[@id='form_model_id']//option[@selected]"));

            // markFromSelector
            markFromSelector.shouldBe(Condition.visible);

            //modelFromSelector
            modelFromSelector.shouldBe(Condition.visible);
            String modelFromSelectorText = modelFromSelector.getAttribute("innerText");
            modelFromSelectorText = substring_2(modelFromSelectorText, startUrl);

            if (!modelFromData.equals(modelFromSelectorText))
                commonMethods.writerInFile(result, true, "Model from data doesn't equals model from selector:#" + modelFromData + "#" + modelFromSelectorText + "#" + startUrl);

        } catch (ElementNotFound element) {
            commonMethods.writerInFile(result, true, "Trouble with element in selector" + "#" + startUrl);
        }
        closeWebDriver();

    }

    private String substring_2(String textFromSelector, String startUrl) throws IOException {
        try {
            textFromSelector = textFromSelector.substring(0, textFromSelector.lastIndexOf("(")).trim();
//            System.out.println(textFromSelector);
        } catch (Exception e) {
            commonMethods.writerInFile(result, true, "Fail" + "#" + startUrl);
        }
        return textFromSelector;
    }

}

