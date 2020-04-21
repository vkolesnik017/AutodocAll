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
import static com.codeborne.selenide.Selenide.close;

public class QC_1573_FiltersSorting_TestProductOutputWithFixedFiltersInSidebar {
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
    public void testProductOutputWithFixedFiltersInSidebar(String route) {
        openPage(route);
        new Listing_page_Logic().checkOutputWithFiltersByBrandFixInSidebar(2)
                                .checkOutputWithFiltersBySideFixInSidebarBackSide("hinten")
                                .checkOutputWithFiltersByRatingFixInSidebar()
                                .checkLangeFilterFixInSidebar();
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks All Filters Are Fixed In Sidebar After Scroll Route Search")
    public void testProductOutputWithFixedFiltersInSidebarRouteSearch() throws SQLException {
        openPage(new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "search21"));
        new Listing_page_Logic().checkOutputWithFiltersByBrandFixInSidebarRouteSearch(0)
                                .checkOutputWithFiltersBySideFixInSidebarSearchRoute("Vorderachse")
                                .checkfurprNummerFilterFixInSidebar();
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks All Filters Are Fixed In Sidebar After Scroll Route LKW")
    public void testProductOutputWithFixedFiltersInSidebarRouteLKW(String route) {
        openPage(route);
        new Listing_page_Logic().checkOutputWithFiltersByBrandFixInSidebar(2)
                                .checkOutputWithFiltersBySideFixInSidebar("Vorderachse")
                                .checkOutputWithFiltersByRatingFixInSidebar();
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks All Filters Are Fixed In Sidebar After Scroll Route LKW Search")
    public void testProductOutputWithFixedFiltersInSidebarRouteLKWsearch() throws SQLException {
        openPage(new DataBase().getFullRouteByRouteAndSubroute("subprod", "DE", "lkw_main", "lkw_search"));
        new Listing_page_Logic().checkOutputWithFiltersByBrandFixInSidebar(2)
                                .checkOutputWithFiltersBySideFixInSidebar("Vorderachse")
                                .checkOutputWithFiltersByRatingFixInSidebar();
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
