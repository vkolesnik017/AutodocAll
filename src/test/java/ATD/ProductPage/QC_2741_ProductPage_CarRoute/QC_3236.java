package ATD.ProductPage.QC_2741_ProductPage_CarRoute;

import ATD.Product_page_Logic;
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
import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3236 {

    ProductsPdf_aws pdfAwsPage = new ProductsPdf_aws();
    Product_page_Logic productPage = new Product_page_Logic();
    private String pathFile = "C:" + "\\" + "Autotests" + "\\" + "files" + "\\" + "data" + "\\" + "DE.pdf";
    private String artId = "8094462";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product91");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Check adding pdf file via AWS")
    public void testCheckAddingPdfFileViaAws(String route) throws AWTException {

        String uploadFile = pdfAwsPage.openProductsPdf()
                .setSkinInSecondLine("2")
                .setArtIdProduct(artId)
                .setTypeOfFileSecondLine("1")
                .uploadFile(pathFile)
                .presencePreloader()
                .presenceAlertMessage("Success upload")
                .setSkinInFirstLine("2")
                .clickOnSearch()
                .clickOnDoNotDisplayCheckBox(artId)
                .getTitleOfUploadFile(artId);
        openPage(route);
        productPage.displayRidexInfoBlock()
                .presenceOfUploadFile(uploadFile);
        pdfAwsPage.openProductsPdf()
                .setSkinInFirstLine("2")
                .clickOnSearch()
                .removeFile(artId)
                .displayOfRemoveMessage("Удалено");
        openPage(route);
        productPage.absenceOfRidexInfoBlock();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
