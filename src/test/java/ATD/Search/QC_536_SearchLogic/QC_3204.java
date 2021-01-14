package ATD.Search.QC_536_SearchLogic;

import ATD.Main_page_Logic;
import Common.DataBase;
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

public class QC_3204 {

    DataBase db = new DataBase("ATD");
    Main_page_Logic mainPage = new Main_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "maker_car_list7");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Checking the transition to search listing when searching for a keyword that matches the name of the TecDoc category in the session when there are no products for the TecDoc listing")
    public void testCheckRedirectToSearchListing(String route) throws SQLException {
        openPage(route);
        mainPage.useSearch("Bremsbeläge");
        waitWhileRouteBecomeExpected("search");
        checkingContainsUrl(db.getRouteByRouteName("DE", "search21"));
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
