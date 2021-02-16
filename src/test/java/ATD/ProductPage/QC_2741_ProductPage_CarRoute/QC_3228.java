package ATD.ProductPage.QC_2741_ProductPage_CarRoute;

import ATD.Main_page_Logic;
import AWS.ProductsPdf_aws;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.awt.*;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3228 {
    ProductsPdf_aws pdfAwsPage = new ProductsPdf_aws();
    Main_page_Logic mainPage = new Main_page_Logic();
    private String pathFile = "C:" + "\\" + "Autotests" + "\\" + "files" + "\\" + "data" + "\\" + "DE.pdf";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShopsWithMainRoute("prod", "DE", "main");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Check adding pdf file for generic and brand via AWS")
    public void testCheckAddingPdfFileForGenericAndBrandViaAws(String route) throws AWTException {

        String uploadFile = pdfAwsPage.openProductsPdf()
                .setSkinInFirstLine("2")
                .setGenericFirstLine("1427")
                .setBrandFirstLine("RIDEX")
                .clickOnSearch()
                .absenceResultSearchTable()
                .setGenericSecondLine("1427")
                .setBrandSecondLine("RIDEX")
                .setTypeOfFileSecondLine("1")
                .uploadFile(pathFile)
                .presencePreloader()
                .presenceAlertMessage("Success upload")
                .clickOnSearch()
                .clickOnDoNotDisplayCheckBox("0")
                .getTitleOfUploadFile("0");
        openPage(route);
        mainPage.useSearch("Gelenk, Längswelle")
                .presenceOfTecDocListing()
                .selectGeneric("_1427")
                .selectBrandInBlock("100015")
                .clickOnProductInTecDocListing(0)
                .displayRidexInfoBlock()
                .presenceOfUploadFile(uploadFile);
        pdfAwsPage.openProductsPdf()
                .setSkinInFirstLine("2")
                .setGenericFirstLine("1427")
                .setBrandFirstLine("RIDEX")
                .clickOnSearch()
                .removeFile("0")
                .displayOfRemoveMessage("Удалено");
        openPage(route);
        mainPage.useSearch("Gelenk, Längswelle")
                .presenceOfTecDocListing()
                .selectGeneric("_1427")
                .selectBrandInBlock("100015")
                .clickOnProductInTecDocListing(0)
                .absenceOfRidexInfoBlock();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
