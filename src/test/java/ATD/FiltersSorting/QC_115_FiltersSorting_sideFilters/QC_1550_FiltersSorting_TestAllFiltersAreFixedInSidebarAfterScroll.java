package ATD.FiltersSorting.QC_115_FiltersSorting_sideFilters;


import ATD.DataBase;
import ATD.Listing_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1550_FiltersSorting_TestAllFiltersAreFixedInSidebarAfterScroll {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_car_list21");
    }

    @DataProvider(name = "routesLKW", parallel = true)
    Object[] dataProviderLKWsearch() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list15");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks All Filters Are Fixed In Sidebar After Scroll")
    public void testAllFiltersAreFixedInSidebarAfterScroll(String route) {
        openPage(route);
        new Listing_page_Logic().checkFiltersFixInSidebar();
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks All Filters Are Fixed In Sidebar After Scroll Search Route")
    public void testAllFiltersAreFixedInSidebarAfterScrollSeachRoute() throws SQLException {
        openPage(new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "search21"));
        new Listing_page_Logic().checkFiltersFixInSidebarSearchRoute();
    }

    @Test(dataProvider = "routesLKW", enabled = false)
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks All Filters Are Fixed In Sidebar After Scroll LKW")
    public void testAllFiltersAreFixedInSidebarAfterScrollLKW(String route) {
        openPage(route);
        new Listing_page_Logic().checkFiltersFixInSidebarLKW();
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks All Filters Are Fixed In Sidebar After Scroll LKW Search Route")
    public void testAllFiltersAreFixedInSidebarAfterScrollLKWsearchRoute() throws SQLException {
        openPage(new DataBase().getFullRouteByRouteAndSubroute("subprod", "DE", "lkw_main", "lkw_search"));
        new Listing_page_Logic().checkFiltersFixInSidebarLKWsearchRoute();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
