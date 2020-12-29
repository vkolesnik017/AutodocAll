package ATD.Tyres.QC_1272_TyresMainPage;


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

public class QC_1344_TestPopularDimensionsRelinkBlockPresence {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routesTyresPage", parallel = true)
    Object[] dataProviderTyresPage() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres,tyres2,tyres3,tyres4");
    }

    @DataProvider(name = "routesTyresFeature", parallel = true)
    Object[] dataProviderTyresFeature() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_maker,tyres_feature");
    }

    @Test(dataProvider = "routesTyresPage")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Popular Dimensions Relink Block Presence from Tyres page")
    public void testPopularDimensionsRelinkBlockPresenceFromTyresPage(String route) {
        openPage(route);
        new Tyres_page_Logic().checkTyresDimensionRelinkBlockPresence();
    }

    @Test(dataProvider = "routesTyresFeature")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test Checks Popular Dimensions Relink Block Presence from Tyres feature and maker page")
    public void testPopularDimensionsRelinkBlockPresenceFromTyresFeatureAndMakerPage(String route) {
        openPage(route);
        new Tyres_feature_page_Logic().checkTyresDimensionRelinkBlockPresence();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
