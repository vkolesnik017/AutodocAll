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

public class QC_56_FiltersSorting_TestBrandFilterReset {
    private Listing_page_Logic listingPageLogic = new Listing_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list,search4,category_car_list6");
    }

    @DataProvider(name = "routeOem", parallel = true)
    Object[] dataProviderOem() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_oen3");
    }

    @DataProvider(name = "routeAcc", parallel = true)
    Object[] dataProviderAcc() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "listing_instruments");
    }

    @DataProvider(name = "routesLKW", parallel = true)
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_search,lkw_category_car_list,lkw_category_car_list2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks brand filter reset (Tecdoc listing)")
    public void checkBrandFilterReset(String route) {
        openPage(route);
        listingPageLogic.clickFirstBrandNameInFilter()
                    .waitUntilPreloaderDisappear()
                    .clickFirstBrandNameInFilter()
                    .waitUntilPreloaderDisappear()
                    .checkUniqueBrandsOnListing(2, listingPageLogic.productTitleInListMode());
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks brand filter reset (LKW listing)")
    public void checkBrandFilterResetLKW(String route) {
        openPage(route);
        new Main_page_Logic().closeCarSelectorTooltipIfVisible();
        listingPageLogic.clickFirstBrandNameInFilter()
                .waitUntilPreloaderDisappear()
                .clickFirstBrandNameInFilter()
                .waitUntilPreloaderDisappear()
                .checkUniqueBrandsOnListing(2, listingPageLogic.productTitleInListMode());
    }

    @Test(dataProvider = "routeAcc")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks brand filter reset (Acc listing)")
    public void checkBrandFilterResetAcc(String route) {
        openPage(route);
        listingPageLogic.clickFirstBrandNameInFilter()
                .waitUntilPreloaderDisappear()
                .clickFirstBrandNameInFilter()
                .waitUntilPreloaderDisappear()
                .checkUniqueBrandsOnListing(2, listingPageLogic.productTitleInListMode());
    }

    @Test(dataProvider = "routeOem")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks brand filter reset (Oem listing)")
    public void checkBrandFilterResetOem(String route) {
        openPage(route);
        listingPageLogic.clickFirstBrandNameOemListing()
                    .waitUntilPreloaderDisappear()
                    .clickFirstBrandNameOemListing()
                    .waitUntilPreloaderDisappear()
                    .checkUniqueBrandsOnListing(2, listingPageLogic.productTitleInListMode());
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
