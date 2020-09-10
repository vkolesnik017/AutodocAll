package ATD.FiltersSorting.QC_52_FiltersSorting_byBrand;


import ATD.Listing_page_Logic;
import ATD.Main_page_Logic;
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

public class QC_53_FiltersSorting_TestBrandFilterInTileMode {
    private Listing_page_Logic listingPageLogic = new Listing_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list,search2,search19");
    }

    @DataProvider(name = "routesLKW", parallel = true)
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_search,lkw_category_car_list,lkw_category_car_list2,lkw_search6");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks brand filter in tile mode (Tecdoc listing)")
    public void checkBrandFilterInTileMode(String route) {
        openPage(route);
        listingPageLogic.clickFirstBrandNameInFilter()
                    .waitUntilPreloaderDisappear();
        String brand1 = listingPageLogic.getTextOrAttributeFromFilter(listingPageLogic.firstResetBrandBtn(), listingPageLogic.firstBrandNameInFiler(), "alt");
        listingPageLogic.clickShowListingInTileModeButton()
                .checkProductTitleOnListingAfterChangingView(brand1, listingPageLogic.productTitleInTileMode());
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks brand filter in tile mode (LKW listing)")
    public void checkBrandFilterInTileModeLKW(String route) {
        openPage(route);
        new Main_page_Logic().closeCarSelectorTooltipIfVisible();
        listingPageLogic.clickFirstBrandNameInFilter()
                    .waitUntilPreloaderDisappear();
        String brand1 = listingPageLogic.getTextOrAttributeFromFilter(listingPageLogic.firstResetBrandBtn(), listingPageLogic.firstBrandNameInFiler(), "alt");
        listingPageLogic.clickShowListingInTileModeButton()
                .checkProductTitleOnListingAfterChangingView(brand1, listingPageLogic.productTitleInTileMode());
    }
    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
