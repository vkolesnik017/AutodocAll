package ATD.FiltersSorting.QC_115_FiltersSorting_sideFilters;


import Common.DataBase;
import ATD.Listing_page_Logic;
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

public class QC_120 {
    private Listing_page_Logic listingPage = new Listing_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list2");
    }

    @DataProvider(name = "routesLKWsearch", parallel = true)
    Object[] dataProviderLKWsearch() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_search5");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks Wischblattausfuhrung side filter")
    public void testWischblattausfuhrungFilter(String route) {
        openPage(route);
        String characteristic = listingPage.getTextFromElement(listingPage.wischblattausfuhrungFilterAttribute());
        listingPage.hoverOnSideFilterAndClick(listingPage.wischblattausfuhrungFilterCheckbox())
                    .waitUntilPreloaderDisappear()
                    .checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.wischblattausfuhrungProductAttributeGenericRoute(), listingPage.wischblattausfuhrungProductAttributeTecdocRoute());
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks Wischblattausfuhrung side filter LKW")
    public void testWischblattausfuhrungFilterLKw() throws SQLException {
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("subprod", "DE", "lkw_main", "lkw_category_car_list"));
        String characteristic = listingPage.getTextFromElement(listingPage.wischblattausfuhrungFilterAttribute());
        listingPage.clickFilterButton(listingPage.wischblattausfuhrungFilterCheckbox())
                    .waitUntilPreloaderDisappear()
                    .checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.wischblattausfuhrungProductAttributeGenericRoute(), listingPage.wischblattausfuhrungProductAttributeTecdocRoute());
    }

    @Test(dataProvider = "routesLKWsearch", enabled = false)
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks Wischblattausfuhrung side filter LKW search")
    public void testWischblattausfuhrungFilterLKWsearch(String route) {
        openPage(route);
        String characteristic = listingPage.getTextFromElement(listingPage.wischblattausfuhrungFilterAttribute());
        listingPage.hoverOnSideFilterAndClick(listingPage.wischblattausfuhrungFilterCheckbox())
                    .waitUntilPreloaderDisappear()
                    .checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.wischblattausfuhrungProductAttributeGenericRoute(), listingPage.wischblattausfuhrungProductAttributeTecdocRoute());
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
