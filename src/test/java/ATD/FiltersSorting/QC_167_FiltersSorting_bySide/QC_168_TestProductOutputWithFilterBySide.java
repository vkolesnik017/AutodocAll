package ATD.FiltersSorting.QC_167_FiltersSorting_bySide;


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

public class QC_168_TestProductOutputWithFilterBySide {
    private Listing_page_Logic listingPageLogic = new Listing_page_Logic();
    private DataBase dataBase = new DataBase();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_car_list2,search4");
    }

    @DataProvider(name = "routesLKW", parallel = true)
    Object[] dataProviderLKW2routes() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list7,lkw_search,lkw_category_car_list6");
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

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks filter by side in tile mode")
    public void testProductOutputWithFilterBySideSearchRoute() throws SQLException {
        openPage("https://autodoc.de/" + dataBase.getRouteByRouteName("DE", "search5"));
        listingPageLogic.clickFilterBySideFront()
                .waitUntilPreloaderDisappear()
                .checkSideInTileMode("vorne")
                .clickShowListingInListModeButton()
                .waitUntilPreloaderDisappear()
                .clickSecondListingPageButton()
                .waitUntilPreloaderDisappear()
                .checkProductAttributeOnListingWithCarAndFilter("vorne", listingPageLogic.einbauseiteProductAttributeGenericRoute(), listingPageLogic.einbauseiteProductAttributeTecdocRoute())
                .clickFilterBySideBack()
                .waitUntilPreloaderDisappear()
                .checkUniqueBrandsOnListing(2, listingPageLogic.einbauseiteProductAttributeTecdocRoute());
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks filter by side in tile mode LKW")
    public void testProductOutputWithFilterBySideLKW(String route) {
        openPage(route);
        listingPageLogic.clickFilterBySideFront()
                .waitUntilPreloaderDisappear()
                .clickShowListingInTileModeButton()
                .waitUntilPreloaderDisappear()
                .checkSideInTileModeLKW()
                .clickShowListingInListModeButton()
                .waitUntilPreloaderDisappear()
                .clickSecondListingPageButton()
                .waitUntilPreloaderDisappear()
                .checkProductAttributeOnListingWithCarAndFilter("Vorderachse", listingPageLogic.einbauseiteProductAttributeGenericRoute(), listingPageLogic.einbauseiteProductAttributeTecdocRoute())
                .clickFilterBySideFront()
                .waitUntilPreloaderDisappear()
                .checkUniqueBrandsOnListing(2, listingPageLogic.einbauseiteProductAttributeTecdocRoute());
    }

    @AfterMethod
    public void tearDown() {
        close();
    }
}
