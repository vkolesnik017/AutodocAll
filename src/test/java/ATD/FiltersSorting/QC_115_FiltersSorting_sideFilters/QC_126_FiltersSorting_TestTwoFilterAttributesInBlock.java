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

public class QC_126_FiltersSorting_TestTwoFilterAttributesInBlock {
    private Listing_page_Logic listingPage = new Listing_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_car_list2,search4,search17,search18");
    }

    @DataProvider(name = "routesLKW", parallel = true)
    Object[] dataProviderLKWsearch() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_search5");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks two filter attributes in block")
    public void testTwoFilterAttributesInBlock(String route) {
        openPage(route);
        listingPage.hoverOnSideFilterAndClick(listingPage.langeFilterCheckbox3())
                    .waitUntilPreloaderDisappear();
        String characteristic = listingPage.getTextFromElement(listingPage.activeSideFilter2());
        listingPage.hoverOnSideFilterAndClick(listingPage.activeSideFilter2())
                    .waitUntilPreloaderDisappear()
                    .checkTextInElement(listingPage.activeSideFilter(), characteristic)
                    .checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.langeProductAttributeGenericRoute(), listingPage.langeProductAttributeTecdocRoute());
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks two filter attributes in block LKW")
    public void testTwoFilterAttributesInBlockLKW(String route) {
        openPage(route);
        listingPage.clickFilterButton(listingPage.langeFilterCheckbox3())
                    .waitUntilPreloaderDisappear();
        String characteristic = listingPage.getTextFromElement(listingPage.activeSideFilter2());
        listingPage.clickFilterButton(listingPage.activeSideFilter2())
                    .waitUntilPreloaderDisappear()
                    .checkTextInElement(listingPage.activeSideFilter(), characteristic)
                    .checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.langeProductAttributeGenericRoute(), listingPage.langeProductAttributeTecdocRoute());
    }

    @Test(enabled = false)
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks two filter attributes in block on route search with generic")
    public void testTwoFilterAttributesInBlockRouteWithGeneric() throws Exception {
        openPage(new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "search5"));
        listingPage.clickFilterButton(listingPage.langeFilterCheckbox3())
                    .waitUntilPreloaderDisappearAndSleep(5000);
        String characteristic = listingPage.getTextFromElement(listingPage.activeSideFilter2());
        listingPage.clickFilterButton(listingPage.activeSideFilter2())
                    .waitUntilPreloaderDisappear()
                    .checkTextInElement(listingPage.activeSideFilter4FirstPosition(), characteristic)
                    .checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.langeProductAttributeGenericRoute(), listingPage.langeProductAttributeTecdocRoute());
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks two filter attributes in block on LKW model")
    public void testTwoFilterAttributesLKWmodel() throws SQLException {
        openPage(new DataBase().getFullRouteByRouteAndSubroute("subprod", "DE", "lkw_main", "lkw_category_car_list7"));
        listingPage.clickFilterButton(listingPage.langeFilterCheckbox3())
                .waitUntilPreloaderDisappear();
        String characteristic = listingPage.getTextFromElement(listingPage.activeSideFilter2());
        listingPage.clickFilterButton(listingPage.activeSideFilter2())
                .waitUntilPreloaderDisappear()
                .checkTextInElement(listingPage.activeSideFilter(), characteristic)
                .checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.langeProductAttributeGenericRoute(), listingPage.langeProductAttributeTecdocRoute());
    }

    @Test(enabled = false)
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks two filter attributes in block on LKW car")
    public void testTwoFilterAttributesLKWcar() throws Exception {
        openPage(new DataBase().getFullRouteByRouteAndSubroute("subprod", "DE", "lkw_main", "lkw_category_car_list6"));
        listingPage.clickFilterButton(listingPage.langeFilterCheckbox3())
                .waitUntilPreloaderDisappearAndSleep(2000);
        String characteristic = listingPage.getTextFromElement(listingPage.activeSideFilterAttributeLkw2());
        listingPage.clickFilterButton(listingPage.activeSideFilterAttributeLkw2())
                .waitUntilPreloaderDisappear()
                .checkTextInElement(listingPage.activeSideFilterLkw(), characteristic)
                .checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.hoheProductAttributeGenericRouteLKW(), listingPage.hoheProductAttributeTecdocRouteLKW());
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks two filter attributes in block on LKW search")
    public void testTwoFilterAttributesLKWsearch() throws SQLException {
        openPage(new DataBase().getFullRouteByRouteAndSubroute("subprod", "DE", "lkw_main", "lkw_search"));
        listingPage.clickFilterButton(listingPage.langeFilterCheckbox3())
                .waitUntilPreloaderDisappear();
        String characteristic = listingPage.getTextFromElement(listingPage.durchmesserSideFilterButtonSecondValue());
        listingPage.clickFilterButton(listingPage.durchmesserSideFilterButtonSecondValue())
                .waitUntilPreloaderDisappear()
                .checkTextInElement(listingPage.durchmesserSideFilterButtonFirstValue(), characteristic)
                .checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.durchmesserProductAttributeGenericRoute(), listingPage.durchmesserProductAttributeTecdocRoute());
    }

    @AfterMethod
    public void tearDown() {
        close();
    }
}
