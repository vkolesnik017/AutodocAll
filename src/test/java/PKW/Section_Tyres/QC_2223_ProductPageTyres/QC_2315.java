package PKW.Section_Tyres.QC_2223_ProductPageTyres;

import Common.SetUp;
import PKW.Tyres_item_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2315 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("subprod", "DE", "main_tyres", "tyre_item2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "OlhaLavrynenko")
    @Description(value = "Test Checks Presence of Analogs Block On Tyres Product Page")
    public void testAnalogsBlockPresence(String route) {
        openPage(route);
        new Tyres_item_page_Logic().presenceOfAnalogsBlock();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
