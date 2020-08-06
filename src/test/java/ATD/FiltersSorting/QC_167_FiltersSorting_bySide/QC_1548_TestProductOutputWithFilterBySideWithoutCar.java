package ATD.FiltersSorting.QC_167_FiltersSorting_bySide;


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
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1548_TestProductOutputWithFilterBySideWithoutCar {
    private Listing_page_Logic listingPageLogic = new Listing_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "search21,search22");
    }

    @DataProvider(name = "routesLKW", parallel = true)
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_search7");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks filter by side without car")
    public void testFilterBySideWithoutCar(String route) {
        openPage(route);
        listingPageLogic.clickFilterBySideBack()
                    .waitUntilPreloaderDisappear()
                    .checkSideInTileMode("Hinterachse")
                    .clickShowListingInListModeButton()
                    .waitUntilPreloaderDisappear()
                    .clickSecondListingPageButton()
                    .waitUntilPreloaderDisappear()
                    .checkProductAttributeOnListingWithCarAndFilter("Hinterachse", listingPageLogic.einbauseiteProductAttributeGenericRoute(), listingPageLogic.einbauseiteProductAttributeTecdocRoute())
                    .clickFilterBySideBack()
                    .waitUntilPreloaderDisappear()
                    .checkUniqueBrandsOnListing(2, listingPageLogic.einbauseiteProductAttributeTecdocRoute());
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks filter by side without car LKW")
    public void testFilterBySideWithoutCarLKW(String route) {
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
    public void close() {
        closeWebDriver();
    }
}
