package ATD.ProductPage.QC_2741_ProductPage_CarRoute;

import ATD.Product_page_Logic;
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

public class QC_1969_CheckPresenceTheBlockLawECEOnLightBulbPage {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "LightBulbsProduct");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks presence block 'Law ECE' on light bulb page")
    public void testCheckPresenceTheBlockLawECEOnLightBulbPage(String route) {
        openPage(route);
        new Product_page_Logic().checkPresenceBlockApprovalECE()
                .checkTextInsideBlockApprovalECE("Nur für den Gebrauch im Gelände. Diese Lampen haben keine ECE-Zulassung für die Verwendung auf öffentlichen Straßen");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
