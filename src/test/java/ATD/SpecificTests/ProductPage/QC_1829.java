package ATD.SpecificTests.ProductPage;

import ATD.Product_page_Logic;
import Common.Excel;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static ATD.CommonMethods.openPage;
import static Common.Excel.parseExcel;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class QC_1829 {
    private final String dataFile = "C://Autotests/files/data/qc_1829.xls";
    private final String result = "C://Autotests/files/data/QC_1829_result.txt";
    Excel excelFIle = new Excel();
    private final String url = "https://test.autodoc.de/brand/";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "data")
   /* Object[] dataProvider() {
        return new SetUp("ATD").setUpShopsWithMainRoute("prod", "DE", "main");
    }*/

    Object[] dataProvider() {
        return new Excel().setUpAllCellFromExcel(dataFile);
    }


    @Test(dataProvider = "data")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Checking the default quantity in the product counter")
    public void testCheckingDefaultQuantityInProductCounter(String data) throws IOException {
        String urlId = parseExcel(data)[0].trim();
        String expectedQuantity = parseExcel(data)[1].trim();

        open(url + urlId);

        new Product_page_Logic().compareQuantityOfProductAndWriteToFile(expectedQuantity, urlId, result);



   /*     openPage(route);
        List<String> idOfProduct = excelFIle.getValuesFromFile(dataFile, 6, 0, "Sheet_");
        List<String> quantityOfProduct = excelFIle.getValuesFromFile(dataFile, 6, 1, "Sheet_");
        new Product_page_Logic().compareQuantityOfProductWithFile(idOfProduct, quantityOfProduct, result);*/
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
