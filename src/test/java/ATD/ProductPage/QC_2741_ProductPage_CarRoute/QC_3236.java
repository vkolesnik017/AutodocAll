package ATD.ProductPage.QC_2741_ProductPage_CarRoute;

import AWS.ProductsPdf_aws;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3236 {

    private String pathFile = "C:/Autotests/files/data/DE.pdf";
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
    public void testCheckAddingPdfFileViaAws(String route) {


        new ProductsPdf_aws().openProductsPdf()
                .setSkinInSecondLine("2")
                .setArtIdProduct("8094462")
                .setTypeOfFileSecondLine("1")
                .uploadFile(pathFile);

        openPage(route);

    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
