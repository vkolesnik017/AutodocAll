package ATD.Search.QC_536_SearchLogic;

import ATD.Listing_page_Logic;
import ATD.Main_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.CommonMethods.waitWhileRouteContainsExpected;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class QC_538 {

    private String genericName = "Fahrwerksfeder";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "maker_car_list5");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Evlentiev")
    @Description(value = "The test verifies that at the listing have only products of generic Fahrwerksfeder after search by text Fahrwerksfeder")
    public void testSearchByGeneric(String route) {
        open(route);
        new Main_page_Logic().useSearch(genericName);
        waitWhileRouteContainsExpected("search");
        new Listing_page_Logic().checksProductTitlesContainExpectedTextGoingAllPagination(genericName);
    }

    @DataProvider(name = "routeLKW")
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_maker_car_list16");
    }

    @Test(dataProvider = "routeLKW")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "The test verifies that at the listing have only products of generic Fahrwerksfeder after search by text Fahrwerksfeder")
    public void testSearchByGenericLKW(String route) {
        open(route);
        new Main_page_Logic().useSearch(genericName);
        waitWhileRouteContainsExpected("search");
        new Listing_page_Logic().checksProductTitlesContainExpectedTextGoingAllPagination(genericName);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
