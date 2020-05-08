package LKW_trucks.QC_144_Header_trucks_routes;

import ATD.DataBase;
import ATD.LKW_main_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_153_NavigationBlockInHeader_lkw {
    private LKW_main_page_Logic mainPage = new LKW_main_page_Logic();
    private DataBase db = new DataBase();
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_main");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Checks navigate categories in header LKW")
    public void testNavigateCategoriesInHeader(String route) throws SQLException {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        mainPage.clickPkwCategory();
        checkingContainsUrl(db.getRouteByRouteName(shop, "main"));
        mainPage.clickMotoCategory();
        checkingContainsUrl(db.getRouteByRouteName(shop, "moto_main"));
        mainPage.clickTiresCategory();
        checkingContainsUrl(db.getRouteByRouteName(shop, "tyres"));
        mainPage.clickInstrumentsCategory();
        checkingContainsUrl(db.getRouteByRouteName(shop, "index_instruments"));
        mainPage.clickAccessoriesCategory();
        checkingContainsUrl(db.getRouteByRouteName(shop, "index_accessories"));
        mainPage.clickEngineOilCategory();
        checkingContainsUrl(db.getRouteByRouteName(shop, "engine_oil"));
        mainPage.clickFiltersCategory();
        checkingContainsUrl(db.getRouteByRouteName(shop, "filters"));
        mainPage.clickBrakeSystemCategory();
        checkingContainsUrl(db.getRouteByRouteName(shop, "brake_system"));
        mainPage.clickEngineCategory();
        checkingContainsUrl(db.getRouteByRouteName(shop, "engine"));
    }
    @AfterMethod
    private void tearDown() {
        close();
    }
}
