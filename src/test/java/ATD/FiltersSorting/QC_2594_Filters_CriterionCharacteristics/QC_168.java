package ATD.FiltersSorting.QC_2594_Filters_CriterionCharacteristics;


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

public class QC_168 {
    private Listing_page_Logic listingPageLogic = new Listing_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks filter by side in tile mode")
    public void testProductOutputWithFilterBySide(String route) {
        openPage(route);
        listingPageLogic.clickFilterBySideBack()
                .waitUntilPreloaderDisappear()
                .checkSideInTileMode("hinten")
                .clickShowListingInListModeButton()
                .waitUntilPreloaderDisappear()
                .clickSecondListingPageButton()
                .waitUntilPreloaderDisappear()
                .checkProductAttributeOnListingWithCarAndFilter("hinten", listingPageLogic.einbauseiteProductAttributeGenericRoute(), listingPageLogic.einbauseiteProductAttributeTecdocRoute())
                .clickFilterBySideBack()
                .waitUntilPreloaderDisappear()
                .checkUniqueBrandsOnListing(2, listingPageLogic.einbauseiteProductAttributeTecdocRoute());
    }

    @DataProvider(name = "routesLKW", parallel = true)
    Object[] dataProviderLKW2routes() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list6,lkw_category_car_list7");
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks filter by side in tile mode LKW")
    public void testProductOutputWithFilterBySideLKW(String route) {
        openPage(route);
        listingPageLogic.clickFilterBySideFront()
                .waitUntilPreloaderDisappear()
                .clickShowListingInTileModeButton()
                .waitUntilPreloaderDisappear()
                .checkInstallationSide("Vorderachse")
                .clickFilterBySideFront()
                .waitUntilPreloaderDisappear()
                .checkPresenceOfUniqueProductsBySideFilter();
    }


    @DataProvider(name = "routesAccessories", parallel = true)
    Object[] dataProviderAccessories() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "accessories_listing_criteria");
    }

    @Test(dataProvider = "routesAccessories")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks filter by side in tile mode LKW")
    public void testProductOutputWithFilterBySideAccessories(String route) {
        openPage(route);
        listingPageLogic.clickFilterBySideFront()
                .waitUntilPreloaderDisappear()
                .clickShowListingInTileModeButton()
                .waitUntilPreloaderDisappear()
                .checkInstallationSide("Vorderachse")
                .clickFilterBySideFront()
                .waitUntilPreloaderDisappear()
                .checkPresenceOfUniqueProductsBySideFilter();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
