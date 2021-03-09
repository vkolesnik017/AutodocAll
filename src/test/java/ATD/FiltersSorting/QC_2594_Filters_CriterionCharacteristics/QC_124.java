package ATD.FiltersSorting.QC_2594_Filters_CriterionCharacteristics;


import ATD.Accessories_listing_criteria_page_Logic;
import ATD.Listing_accessories_page_Logic;
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

public class QC_124 {
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
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_search5");
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

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks side filter cancelling on LKW routes")
    public void testSideFilterCancellingLKWcar() throws SQLException {
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("subprod", "DE", "lkw_main", "lkw_category_car_list6"));
        String characteristic = listingPage.getTextFromElement(listingPage.verschleisswarnkontaktFirstButtonInSidebar());
        listingPage.clickFilterButton(listingPage.verschleisswarnkontaktFirstButtonInSidebar())
                .waitUntilPreloaderDisappear()
                .checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.verschleiswarnkontaktProductAttributeGenericRouteLKW(), listingPage.verschleiswarnkontaktProductAttributeTecdocRouteLKW());
        int numberOfAttributesFilter = listingPage.getSizeOfCollection(listingPage.verschleiswarnkontaktProductAttributeTecdocRouteLKW());
        listingPage.clickFilterButton(listingPage.verschleisswarnkontaktFirstButtonInSidebar())
                .waitUntilPreloaderDisappear();
        int numberOfAttributesNoFilter = listingPage.getSizeOfCollection(listingPage.verschleiswarnkontaktProductAttributeTecdocRouteLKW());
        listingPage.checkFilterIsCanceled(numberOfAttributesFilter, numberOfAttributesNoFilter);
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks side filter cancelling on LKW routes")
    public void testSideFilterCancellingLKWmodel() throws SQLException {
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("subprod", "DE", "lkw_main", "lkw_category_car_list7"));
        String characteristic = listingPage.getTextFromElement(listingPage.verschleisswarnkontaktFirstButtonInSidebar());
        listingPage.clickFilterButton(listingPage.verschleisswarnkontaktFirstButtonInSidebar())
                .waitUntilPreloaderDisappear()
                .checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.verschleiswarnkontaktProductAttributeGenericRouteLKW(), listingPage.verschleiswarnkontaktProductAttributeTecdocRouteLKW());
        int numberOfAttributesFilter = listingPage.getSizeOfCollection(listingPage.verschleiswarnkontaktProductAttributeTecdocRouteLKW());
        listingPage.clickFilterButton(listingPage.activeSideFilterLkwCheckbox())
                .waitUntilPreloaderDisappear();
        int numberOfAttributesNoFilter = listingPage.getSizeOfCollection(listingPage.verschleiswarnkontaktProductAttributeTecdocRouteLKW());
        listingPage.checkFilterIsCanceled(numberOfAttributesFilter, numberOfAttributesNoFilter);
    }

    @Test(enabled = false)
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks side filter cancelling on LKW routes")
    public void testSideFilterCancellingLKWsearch() throws SQLException {
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("subprod", "DE", "lkw_main", "lkw_search"));
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

    @Test(dataProvider = "routesLKW", enabled = false)
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

    @DataProvider(name = "routesAccessories", parallel = true)
    Object[] dataProviderAccessories() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "accessories_listing_criteria");
    }

    @Test(dataProvider = "routesAccessories")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks side filter cancelling")
    public void testSideFilterCancellingAccessories(String route) {
        openPage(route);

        accessoriesPage.setMaterialFilterByName("Eco-Leder")
                .checkListingWithSelectedMaterialFilter("Eco-Leder");
        listingPage.waitUntilPreloaderDisappear();
        accessoriesPage.clickOnSelectedMaterialFilterByName("Eco-Leder");
        listingPage.waitUntilPreloaderDisappear();
        new Listing_accessories_page_Logic().checkListingWithUniqueMaterialFilter();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
