package ATD.FiltersSorting.QC_2594_Filters_CriterionCharacteristics;


import ATD.Listing_page_Logic;
import ATD.Moto_Category_car_list_page_Logic;
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

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1550 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list21");
    }

    @DataProvider(name = "routesLKW", parallel = true)
    Object[] dataProviderLKWsearch() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list15");
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
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "search21"));
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
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("subprod", "DE", "lkw_main", "lkw_search"));
        new Listing_page_Logic().checkFiltersFixInSidebarLKWsearchRoute();
    }

    @DataProvider(name = "routesMoto", parallel = true)
    Object[] dataProviderMoto() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_car_list19,moto_category_car_list_model11");
    }

    @Test(dataProvider = "routesMoto")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks All Filters Are Fixed In Sidebar After Scroll")
    public void testAllFiltersAreFixedInSidebarAfterScrollMoto(String route) {
        openPage(route);

        new Moto_Category_car_list_page_Logic().checkFiltersFixInSidebar();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
