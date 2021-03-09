package ATD.FiltersSorting.QC_2594_Filters_CriterionCharacteristics;


import ATD.Accessories_listing_criteria_page_Logic;
import ATD.Listing_page_Logic;
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

public class QC_126 {
    private Listing_page_Logic listingPage = new Listing_page_Logic();
    private Accessories_listing_criteria_page_Logic accessoriesPage = new Accessories_listing_criteria_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list2");
    }

    @DataProvider(name = "routesLKW", parallel = true)
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list6,lkw_category_car_list7");
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

    @Test(dataProvider = "routesLKW", enabled = false)
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
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "search5"));
        listingPage.clickFilterButton(listingPage.langeFilterCheckbox3())
                .waitUntilPreloaderDisappearAndSleep(5000);
        String characteristic = listingPage.getTextFromElement(listingPage.activeSideFilter2());
        listingPage.clickFilterButton(listingPage.activeSideFilter2())
                .waitUntilPreloaderDisappear()
                .checkTextInElement(listingPage.activeSideFilter4FirstPosition(), characteristic)
                .checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.langeProductAttributeGenericRoute(), listingPage.langeProductAttributeTecdocRoute());
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks two filter attributes in block on LKW model")
    public void testTwoFilterAttributesLKWmodel(String route) {
        openPage(route);
        listingPage.clickFilterButton(listingPage.verschleisswarnkontaktFirstButtonInSidebar())
                .waitUntilPreloaderDisappear();
        String characteristic = listingPage.getTextFromElement(listingPage.secondValueInActiveSideFilter());
        listingPage.clickFilterButton(listingPage.verschleisswarnkontaktSecondButtonInSidebar())
                .waitUntilPreloaderDisappear()
                .checkTextInElement(listingPage.activeSideFilterLkwCheckbox(), characteristic)
                .checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.verschleiswarnkontaktProductAttributeGenericRouteLKW(), listingPage.verschleiswarnkontaktProductAttributeTecdocRouteLKW());
    }

    @Test(enabled = false)
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks two filter attributes in block on LKW search")
    public void testTwoFilterAttributesLKWsearch() throws SQLException {
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("subprod", "DE", "lkw_main", "lkw_search"));
        listingPage.clickFilterButton(listingPage.langeFilterCheckbox3())
                .waitUntilPreloaderDisappear();
        String characteristic = listingPage.getTextFromElement(listingPage.durchmesserSideFilterButtonSecondValue());
        listingPage.clickFilterButton(listingPage.durchmesserSideFilterButtonSecondValue())
                .waitUntilPreloaderDisappear()
                .checkTextInElement(listingPage.durchmesserSideFilterButtonFirstValue(), characteristic)
                .checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.durchmesserProductAttributeGenericRoute(), listingPage.durchmesserProductAttributeTecdocRoute());
    }

    @DataProvider(name = "routesAccessories", parallel = true)
    Object[] dataProviderAccessories() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "accessories_listing_criteria");
    }

    @Test(dataProvider = "routesAccessories")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks two filter attributes in block")
    public void testTwoFilterAttributesInBlockAccessories(String route) {
        openPage(route);
        accessoriesPage.setCategoryInSideBarByPosition(0);
        listingPage.waitUntilPreloaderDisappear();
        String selectedCategory = accessoriesPage.getCategory(1);
        accessoriesPage.setCategoryInSideBarByPosition(1);
        listingPage.waitUntilPreloaderDisappear();
        accessoriesPage.checkProductsWithSelectedCategoryFilter(selectedCategory);
    }


    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
