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

public class QC_124_FiltersSorting_TestSideFilterCancelling {
    private Listing_page_Logic listingPage = new Listing_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_car_list2,search4,search5,search17,search18");
    }

    @DataProvider(name = "routesLKW", parallel = true)
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_search5");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks side filter cancelling")
    public void testSideFilterCancelling(String route) {
        openPage(route);
        String characteristic = listingPage.getTextFromElement(listingPage.activeSideFilter2());
        listingPage.hoverOnSideFilterAndClick(listingPage.activeSideFilter2())
                    .waitUntilPreloaderDisappear()
                    .checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.langeProductAttributeGenericRoute(), listingPage.langeProductAttributeTecdocRoute());
        int numberOfAttributesFilter = listingPage.getSizeOfCollection(listingPage.langeProductAttributeGenericRoute());
        listingPage.hoverOnSideFilterAndClick(listingPage.activeSideFilter())
                    .waitUntilPreloaderDisappear();
        int numberOfAttributesNoFilter = listingPage.getSizeOfCollection(listingPage.langeProductAttributeTecdocRoute());
        listingPage.checkFilterIsCanceled(numberOfAttributesFilter, numberOfAttributesNoFilter);
    }

    @Test(enabled = false)
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks side filter cancelling on LKW routes")
    public void testSideFilterCancellingLKWcar() throws SQLException {
        openPage(new DataBase().getFullRouteByRouteAndSubroute("subprod", "DE", "lkw_main", "lkw_category_car_list6"));
        String characteristic = listingPage.getTextFromElement(listingPage.langeFilterAttribute2());
        listingPage.clickFilterButton(listingPage.langeFilterCheckbox2())
                    .waitUntilPreloaderDisappear()
                    .checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.hoheProductAttributeGenericRouteLKW(), listingPage.hoheProductAttributeTecdocRouteLKW());
        int numberOfAttributesFilter = listingPage.getSizeOfCollection(listingPage.hoheProductAttributeTecdocRoute());
        listingPage.clickFilterButton(listingPage.activeSideFilterLkw())
                    .waitUntilPreloaderDisappear();
        int numberOfAttributesNoFilter = listingPage.getSizeOfCollection(listingPage.hoheProductAttributeTecdocRoute());
        listingPage.checkFilterIsCanceled(numberOfAttributesFilter, numberOfAttributesNoFilter);
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks side filter cancelling on LKW routes")
    public void testSideFilterCancellingLKWmodel() throws SQLException {
        openPage(new DataBase().getFullRouteByRouteAndSubroute("subprod", "DE", "lkw_main", "lkw_category_car_list7"));
        String characteristic = listingPage.getTextFromElement(listingPage.langeFilterAttribute3());
        listingPage.clickFilterButton(listingPage.langeFilterCheckbox3())
                    .waitUntilPreloaderDisappear()
                    .checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.langeProductAttributeGenericRoute(), listingPage.langeProductAttributeTecdocRoute());
        int numberOfAttributesFilter = listingPage.getSizeOfCollection(listingPage.langeProductAttributeTecdocRoute());
        listingPage.clickFilterButton(listingPage.activeSideFilterLkwCheckbox())
                    .waitUntilPreloaderDisappear();
        int numberOfAttributesNoFilter = listingPage.getSizeOfCollection(listingPage.langeProductAttributeTecdocRoute());
        listingPage.checkFilterIsCanceled(numberOfAttributesFilter, numberOfAttributesNoFilter);
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks side filter cancelling on LKW routes")
    public void testSideFilterCancellingLKWsearch() throws SQLException {
        openPage(new DataBase().getFullRouteByRouteAndSubroute("subprod", "DE", "lkw_main", "lkw_search"));
        String characteristic = listingPage.getTextFromElement(listingPage.durchmesserSideFilterButtonFirstValue());
        listingPage.clickFilterButton(listingPage.durchmesserSideFilterButtonFirstValue())
                    .waitUntilPreloaderDisappear()
                    .checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.durchmesserProductAttributeGenericRoute(), listingPage.durchmesserProductAttributeTecdocRoute());
        int numberOfAttributesFilter = listingPage.getSizeOfCollection(listingPage.durchmesserProductAttributeGenericRoute());
        listingPage.clickFilterButton(listingPage.durchmesserSideFilterButtonFirstValue())
                    .waitUntilPreloaderDisappear();
        int numberOfAttributesNoFilter = listingPage.getSizeOfCollection(listingPage.durchmesserProductAttributeTecdocRoute());
        listingPage.checkFilterIsCanceled(numberOfAttributesFilter, numberOfAttributesNoFilter);
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks side filter cancelling")
    public void testSideFilterCancellingLKW(String route) {
        openPage(route);
        String characteristic = listingPage.getTextFromElement(listingPage.activeSideFilter2());
        listingPage.clickFilterButton(listingPage.activeSideFilter2())
                    .waitUntilPreloaderDisappear()
                    .checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.langeProductAttributeGenericRoute(), listingPage.langeProductAttributeTecdocRoute());
        int numberOfAttributesFilter = listingPage.getSizeOfCollection(listingPage.langeProductAttributeTecdocRoute());
        listingPage.clickFilterButton(listingPage.activeSideFilter())
                    .waitUntilPreloaderDisappear();
        int numberOfAttributesNoFilter = listingPage.getSizeOfCollection(listingPage.langeProductAttributeTecdocRoute());
        listingPage.checkFilterIsCanceled(numberOfAttributesFilter, numberOfAttributesNoFilter);
    }

    @AfterMethod
    private void teatDown() {
        close();
    }
}
