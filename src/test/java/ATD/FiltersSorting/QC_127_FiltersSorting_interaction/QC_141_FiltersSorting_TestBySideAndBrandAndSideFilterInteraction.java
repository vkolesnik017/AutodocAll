package ATD.FiltersSorting.QC_127_FiltersSorting_interaction;


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

public class QC_141_FiltersSorting_TestBySideAndBrandAndSideFilterInteraction {
    private Listing_page_Logic listingPage = new Listing_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routesLKW", parallel = true)
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list2,lkw_category_car_list9");
    }

    @Test(enabled = false)
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks by side, brand and side filters interaction LKW route search")
    public void testBySideAndBrandAndSideFilterInteractionLKWsearch() throws Exception {
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("subprod", "DE", "lkw_main", "lkw_search6"));
        String brandName = listingPage.getAtributeFromElementLKWsearch(listingPage.secondBrandNameInFilter(), "alt");
        listingPage.clickFilterButton(listingPage.secondBrandInFilterButton())
                .waitUntilPreloaderDisappear()
                .clickFilterBySideFront()
                .waitUntilPreloaderDisappearAndSleep(3000);
        String durchmesserValue = listingPage.getTextFromElement(listingPage.durchmesserSideFilterButtonSecondValue());
        listingPage.clickFilterButton(listingPage.durchmesserSideFilterButtonSecondValue())
                .waitUntilPreloaderDisappear()
                .checkProductAttributeOnListingWithCarAndFilter("Vorderachse", listingPage.einbauseiteProductAttributeGenericRoute(), listingPage.einbauseiteProductAttributeTecdocRoute())
                .checkProductAttributeOnListingWithCarAndFilter(durchmesserValue, listingPage.durchmesserProductAttributeGenericRoute(), listingPage.durchmesserProductAttributeTecdocRoute())
                .checkProductTitleOnListing(brandName, true, listingPage.productTitleInListMode());
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks by side, brand and side filters interaction LKW route model")
    public void testBySideAndBrandAndSideFilterInteractionLKWmodel(String route) throws Exception {
        openPage(route);
        String bremsscheibenartValue = listingPage.getTextFromElement(listingPage.bremsscheibenartSideFilterButton());
        listingPage.clickFilterButton(listingPage.bremsscheibenartSideFilterButton())
                .waitUntilPreloaderDisappear()
                .clickFilterBySideBack()
                .waitUntilPreloaderDisappear();
        String brandName = listingPage.getAtributeFromElement(listingPage.firstBrandNameOemListing(), "alt");
        listingPage.clickFilterButton(listingPage.firstBrandButtonOemListing())
                .waitUntilPreloaderDisappear()
                .checkProductAttributeOnListingWithCarAndFilter("Hinterachse", listingPage.einbauseiteProductAttributeGenericRoute(), listingPage.einbauseiteProductAttributeTecdocRoute())
                .checkProductAttributeOnListingWithCarAndFilter(bremsscheibenartValue, listingPage.bremsscheibenartProductAttributeGenericRoute(), listingPage.bremsscheibenartProductAttributeTecdocRoute())
                .checkProductTitleOnListing(brandName, true, listingPage.productTitleInListMode());
    }

    @Test(enabled = false)
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks by side, brand and side filters interaction route without car")
    public void testBySideAndBrandAndSideFilterInteractionSearchRouteWithoutCar() throws Exception {
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "search19"));
        listingPage.clickFilterBySideBack()
                .waitUntilPreloaderDisappearAndSleep(3000);
        String brandName = listingPage.getAtributeFromElement(listingPage.firstBrandNameInFiler(), "alt");
        listingPage.clickFilterButton(listingPage.firstBrandInFilterButton())
                .waitUntilPreloaderDisappear();
        String durchmesserValue = listingPage.getTextFromElement(listingPage.durchmesserSideFilterButtonFirstValue());
        listingPage.clickFilterButton(listingPage.durchmesserSideFilterButtonFirstValue())
                .waitUntilPreloaderDisappear()
                .checkProductAttributeOnListingWithCarAndFilter("Hinterachse", listingPage.einbauseiteProductAttributeGenericRoute(), listingPage.einbauseiteProductAttributeTecdocRoute())
                .checkProductAttributeOnListingWithCarAndFilter(durchmesserValue, listingPage.durchmesserProductAttributeGenericRoute(), listingPage.durchmesserProductAttributeTecdocRoute())
                .checkProductTitleOnListing(brandName, true, listingPage.productTitleInListMode());
    }

    @Test(enabled = false)
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks by side, brand and side filters interaction route brand without car")
    public void testBySideAndBrandAndSideFilterInteractionSearchRouteBrandWithoutCar() throws Exception {
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "search20"));
        listingPage.clickFilterBySideBack()
                .waitUntilPreloaderDisappear();
        String durchmesserValue = listingPage.getTextFromElement(listingPage.durchmesserSideFilterButtonFirstValue());
        listingPage.clickFilterButton(listingPage.durchmesserSideFilterButtonFirstValue())
                .waitUntilPreloaderDisappear()
                .checkProductAttributeOnListingWithCarAndFilter("Hinterachse", listingPage.einbauseiteProductAttributeGenericRoute(), listingPage.einbauseiteProductAttributeTecdocRoute())
                .checkProductAttributeOnListingWithCarAndFilter(durchmesserValue, listingPage.durchmesserProductAttributeGenericRoute(), listingPage.durchmesserProductAttributeTecdocRoute())
                .checkProductTitleOnListing("BOSCH", true, listingPage.productTitleInListMode());
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
