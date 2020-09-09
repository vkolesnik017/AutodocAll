package LKW_trucks.QC_144_Header_trucks_routes;

import Common.DataBase;
import ATD.LKW_main_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

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
        new LKW_main_page_Logic().checkNavigationCategoriesInHeader();
    }
    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
