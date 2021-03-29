package ATD.Selector_Tires.QC_1104_TyresSelector;

import ATD.Tyres_feature_page_Logic;
import ATD.Tyres_page_Logic;
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

public class QC_2712 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres,tyres_maker,tyres_model");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks displaying of default parameters in selector at main page")
    public void testCheckDisplayOfDefaultParametersInSelector(String route) {
        openPage(route);
        new Tyres_page_Logic().defaultValuesOfSelector("205", "55", "16").checkOfSeasonSelector();
    }

    @DataProvider(name = "routesOffRoad", parallel = true)
    Object[] dataProviderOffRoad() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres2");
    }

    @Test(dataProvider = "routesOffRoad")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks displaying of default parameters in selector at main page")
    public void testCheckDisplayOfDefaultParametersInSelectorOffRoad(String route) {
        openPage(route);
        new Tyres_page_Logic().defaultValuesOfSelector("215", "65", "16").checkOfSeasonSelector();
    }

    @DataProvider(name = "routesLKW", parallel = true)
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres3");
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks displaying of default parameters in selector at main page")
    public void testCheckDisplayOfDefaultParametersInSelectorLKW(String route) {
        openPage(route);
        new Tyres_page_Logic().defaultValuesOfSelector("235", "65", "16").checkOfSeasonSelector();
    }

    @DataProvider(name = "routesMoto", parallel = true)
    Object[] dataProviderMoto() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres4");
    }

    @Test(dataProvider = "routesMoto")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks displaying of default parameters in selector at main page")
    public void testCheckDisplayOfDefaultParametersInSelectorMoto(String route) {
        openPage(route);
        new Tyres_page_Logic().defaultValuesOfSelector("120", "70", "17").checkOfSeasonMotoSelector("0");
    }

    @DataProvider(name = "routesFeature", parallel = true)
    Object[] dataProviderFeature() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_feature");
    }

    @Test(dataProvider = "routesFeature")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks displaying of default parameters in selector at main page")
    public void testCheckDisplayOfDefaultParametersInSelectorFeature(String route) {
        openPage(route);
        new Tyres_feature_page_Logic().defaultValuesOfSelector("205", "55", "16").checkOfSeasonSelector();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
