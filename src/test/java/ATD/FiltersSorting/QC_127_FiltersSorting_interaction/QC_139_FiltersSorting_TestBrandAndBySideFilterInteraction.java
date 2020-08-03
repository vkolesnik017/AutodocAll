package ATD.FiltersSorting.QC_127_FiltersSorting_interaction;


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

public class QC_139_FiltersSorting_TestBrandAndBySideFilterInteraction {
    private Listing_page_Logic listingPage = new Listing_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_car_list");
    }

    @DataProvider(name = "routesLKW", parallel = true)
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list9");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks side and brand filters interaction")
    public void testSideAndBrandFilterInteraction(String route) {
        openPage(route);
        listingPage.clickFilterBySideBack()
                .waitUntilPreloaderDisappear();
        String brandName = listingPage.getAtributeFromElement(listingPage.firstBrandNameInFiler(), "alt");
        listingPage.clickFilterButton(listingPage.firstBrandInFilterButton())
                .waitUntilPreloaderDisappear()
                .checkProductAttributeOnListingWithCarAndFilter("Hinterachse", listingPage.einbauseiteProductAttributeGenericRoute(), listingPage.einbauseiteProductAttributeTecdocRoute())
                .checkProductTitleOnListing(brandName, true, listingPage.productTitleInListMode());
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks side and brand filters interaction")
    public void testSideAndBrandFilterInteractionLKW(String route) {
        openPage(route);
        listingPage.clickFilterBySideBack()
                .waitUntilPreloaderDisappear();
        String brandName = listingPage.getAtributeFromElement(listingPage.firstBrandNameInFiler(), "alt");
        listingPage.clickFilterButton(listingPage.firstBrandInFilterButton())
                .waitUntilPreloaderDisappear()
                .checkProductAttributeOnListingWithCarAndFilter("Hinterachse", listingPage.einbauseiteProductAttributeGenericRoute(), listingPage.einbauseiteProductAttributeTecdocRoute())
                .checkProductTitleOnListing(brandName, true, listingPage.productTitleInListMode());
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks side and brand filters interaction")
    public void testSideAndBrandFilterInteractionLKWmodelRoute() throws SQLException {
        openPage(new DataBase().getFullRouteByRouteAndSubroute("subprod", "DE", "lkw_main", "lkw_category_car_list2"));
        listingPage.clickFilterBySideBack()
                .waitUntilPreloaderDisappear();
        String brandName = listingPage.getAtributeFromElement(listingPage.firstBrandNameInFilterLKWmodelRoute(), "alt");
        listingPage.clickFilterButton(listingPage.firstBrandInFilterButtonLKWmodelRoute())
                .waitUntilPreloaderDisappear()
                .checkProductAttributeOnListingWithCarAndFilter("Hinterachse", listingPage.einbauseiteProductAttributeGenericRoute(), listingPage.einbauseiteProductAttributeTecdocRoute())
                .checkProductTitleOnListing(brandName, true, listingPage.productTitleInListMode());
    }

    @AfterMethod
    public void tearDown() {
        close();
    }
}
