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

public class QC_122_FiltersSorting_TestSideFilterWithPagination {
    private Listing_page_Logic listingPage = new Listing_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks side filter with pagination")
    public void testSideFilterWithPagination(String route) {
        openPage(route);
        String characteristic = listingPage.getTextFromElement(listingPage.langeFilterAttribute450());
        listingPage.hoverOnSideFilterAndClick(listingPage.langeFilterCheckbox450())
                    .waitUntilPreloaderDisappear()
                    .clickSecondListingPageButton()
                    .waitUntilPreloaderDisappear()
                    .checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.langeProductAttributeGenericRoute(), listingPage.langeProductAttributeTecdocRoute());
    }

    @Test(enabled = false)
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks side filter with pagination search")
    public void testSideFilterWithPaginationSearch() throws SQLException {
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "search17"));
        listingPage.clickMoreCharacteristicInFilter();
        String characteristic = listingPage.getTextFromElement(listingPage.langeFilterAttribute280());
        listingPage.hoverOnSideFilterAndClick(listingPage.langeFilterCheckbox280())
                .waitUntilPreloaderDisappear()
                .clickSecondListingPageButton()
                .waitUntilPreloaderDisappear()
                .checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.langeProductAttributeGenericRoute(), listingPage.langeProductAttributeTecdocRoute());
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks side filter with pagination")
    public void testSideFilterWithPaginationLKW() throws SQLException {
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("subprod", "DE", "lkw_main", "lkw_category_car_list28"));
        String characteristic = listingPage.getTextFromElement(listingPage.langeFilterCheckboxLKW700());
        listingPage.clickFilterButton(listingPage.langeFilterCheckboxLKW700())
                    .waitUntilPreloaderDisappear()
                    .clickSecondListingPageButton()
                    .waitUntilPreloaderDisappear()
                    .checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.langeProductAttributeGenericRouteLKW(), listingPage.langeProductAttributeTecdocRouteLKW());
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
