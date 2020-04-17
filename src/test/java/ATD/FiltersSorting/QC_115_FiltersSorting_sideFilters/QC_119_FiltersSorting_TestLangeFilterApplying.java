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

public class QC_119_FiltersSorting_TestLangeFilterApplying {
    private Listing_page_Logic listingPage = new Listing_page_Logic();
    private DataBase dataBase = new DataBase();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_car_list2,search4,search17");
    }

    @DataProvider(name = "routesLKWsearch", parallel = true)
    Object[] dataProviderLKWsearch() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_search5");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks Lange side filter")
    public void testLangeFilter(String route) {
        openPage(route);
        String characteristic = listingPage.getTextFromElement(listingPage.langeFilterAttribute());
        listingPage.hoverOnSideFilterAndClick(listingPage.langeFilterCheckbox())
                    .waitUntilPreloaderDisappear()
                    .checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.langeProductAttributeGenericRoute(), listingPage.langeProductAttributeTecdocRoute());
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks Lange side filter LKW")
    public void testLangeFilterLKW() throws SQLException {
        openPage(new DataBase().getFullRouteByRouteAndSubroute("subprod", "DE", "lkw_main", "lkw_category_car_list"));
        String characteristic = listingPage.getTextFromElement(listingPage.langeFilterCheckboxLKW());
        listingPage.clickFilterButton(listingPage.langeFilterCheckboxLKW())
                    .waitUntilPreloaderDisappear()
                    .checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.langeProductAttributeGenericRouteLKW(), listingPage.langeProductAttributeTecdocRouteLKW());
    }

    @Test(dataProvider = "routesLKWsearch")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks Lange side filter route LKW search")
    public void testLangeFilterLKWsearch(String route) {
        openPage(route);
        String characteristic = listingPage.getTextFromElement(listingPage.langeFilterAttribute());
        listingPage.clickFilterButton(listingPage.langeFilterCheckbox())
                    .waitUntilPreloaderDisappear()
                    .checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.langeProductAttributeGenericRoute(), listingPage.langeProductAttributeTecdocRoute());
    }

    @AfterMethod
    public void tearDown() {
        close();
    }
}
