package ATD.FiltersSorting.QC_115_FiltersSorting_sideFilters;


import ATD.Listing_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;

public class QC_1550_FiltersSorting_TestAllFiltersAreFixedInSidebarAfterScroll {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "");
    }

    @DataProvider(name = "routesLKW", parallel = true)
    Object[] dataProviderLKWsearch() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "");
    }

    @Test//(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks All Filters Are Fixed In Sidebar After Scroll")
    public void testAllFiltersAreFixedInSidebarAfterScroll() {
        openPage("https://www.autodoc.de/autoteile/scheibenwischer-10233/vw/passat/passat-variant-3b6/16347-2-0");
        new Listing_page_Logic().checkFiltersFixInSidebar();
    }
}
