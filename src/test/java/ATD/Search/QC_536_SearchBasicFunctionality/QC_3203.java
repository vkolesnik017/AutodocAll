package ATD.Search.QC_536_SearchBasicFunctionality;

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

public class QC_3203 {

    DataBase db = new DataBase("ATD");
    Main_page_Logic mainPage = new Main_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "maker_car_list21");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Checking redirect to TecDoc listing when searching for a keyword that matches the name of the TecDoc category with car in the session")
    public void testCheckRedirectToTecDocListing(String route) throws SQLException {
        openPage(route);
        mainPage.useSearch("Bremsbeläge");
        waitWhileRouteBecomeExpected("category_car_list");
        checkingContainsUrl(db.getRouteByRouteName("DE", "category_car_list63"));
        mainPage.useSearch("Luftfilter");
        checkingContainsUrl(db.getRouteByRouteName("DE", "category_car_list64"));
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
