package ATD.Tyres.QC_1272_TyresMainPage;


import ATD.Tyres_maker_page_Logic;
import Common.SetUp;
import ATD.Tyres_page_Logic;
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

public class QC_1339_TestSeasonBlockPresence {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres,tyres2,tyres3,tyres4");
    }

    @DataProvider(name = "routesTyresMakerPage", parallel = true)
    Object[] dataProviderTyresMakerPage() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_maker");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Season Block Presence")
    public void testSeasonBlockPresence(String route) {
        openPage(route);
        new Tyres_page_Logic().checkTyresSeasonBlockPresence();
    }

    @Test(dataProvider = "routesTyresMakerPage")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test Checks Season Block Presence from Tyres maker page")
    public void testSeasonBlockPresenceFromTyresMakerPage(String route) {
        openPage(route);
        new Tyres_maker_page_Logic().checkTyresSeasonBlockPresence();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
