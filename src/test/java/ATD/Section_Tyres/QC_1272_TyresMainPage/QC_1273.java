package ATD.Section_Tyres.QC_1272_TyresMainPage;


import ATD.Tyres_feature_page_Logic;
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

public class QC_1273 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres,tyres2,tyres3,tyres4,tyres_maker,tyres_model");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Tyres Size Selector Presence")
    public void testTyresSizeSelectorPresence(String route) {
        openPage(route);
        new Tyres_page_Logic().checkTyresSizeSelectorPresence();
    }

    @DataProvider(name = "routesFeature", parallel = true)
    Object[] dataProviderFeature() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_feature");
    }

    @Test(dataProvider = "routesFeature")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Checks Tyres Size Selector Presence")
    public void testTyresSizeSelectorPresenceFeature(String route) {
        openPage(route);
        new Tyres_feature_page_Logic().presenceOfTyresSizeSelector();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
