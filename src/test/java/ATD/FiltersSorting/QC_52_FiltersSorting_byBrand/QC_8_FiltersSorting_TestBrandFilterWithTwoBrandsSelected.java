package ATD.FiltersSorting.QC_52_FiltersSorting_byBrand;


import ATD.Listing_page_Logic;
import ATD.Main_page_Logic;
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

public class QC_8_FiltersSorting_TestBrandFilterWithTwoBrandsSelected {
    private Listing_page_Logic listingPageLogic = new Listing_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_car_list,search2,category_car_list6,search19");
    }

    @DataProvider(name = "routeOem", parallel = true)
    Object[] dataProviderOem() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_oen3");
    }

    @DataProvider(name = "routeAcc", parallel = true)
    Object[] dataProviderAcc() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "listing_instruments");
    }

    @DataProvider(name = "routesLKW", parallel = true)
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_search,lkw_category_car_list,lkw_category_car_list2,lkw_search6");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks brand filter with two brands selected (Tecdoc listing)")
    public void checkBrandFilterWithTwoBrandsSelected(String route) {
        openPage(route);
        listingPageLogic.clickFirstBrandNameInFilter()
                    .waitUntilPreloaderDisappear();
        String brand1 = listingPageLogic.firstBrandNameInFiler().attr("alt");
        listingPageLogic.clickSecondBrandNameInFilter()
                    .waitUntilPreloaderDisappear();
        String brand2 = listingPageLogic.firstBrandNameInFiler().attr("alt");
        listingPageLogic.checkProductTitleOnListingWithTwoExpectedTexts(brand1, brand2, true, listingPageLogic.productTitleInListMode());
    }

    @Test(dataProvider = "routeOem")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks brand filter with two brands selected (Oem listing)")
    public void checkBrandFilterWithTwoBrandsSelectedOem(String route) {
        openPage(route);
        listingPageLogic.clickFirstBrandNameOemListing()
                    .waitUntilPreloaderDisappear();
        String brand1 = listingPageLogic.firstBrandNameOemListing().attr("alt");
        listingPageLogic.clickSecondBrandNameOemListing()
                    .waitUntilPreloaderDisappear();
        String brand2 = listingPageLogic.firstBrandNameOemListing().attr("alt");
        listingPageLogic.checkProductTitleOnListingWithTwoExpectedTexts(brand1, brand2, true, listingPageLogic.productTitleInListMode());
    }

    @Test(dataProvider = "routeAcc")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks brand filter with two brands selected (Acc listing)")
    public void checkBrandFilterWithTwoBrandsSelectedAcc(String route) {
        openPage(route);
        String brand1 = listingPageLogic.firstBrandNameInFiler().attr("alt");
        String brand2 = listingPageLogic.secondBrandNameInFilter().attr("alt");
        listingPageLogic.clickFirstBrandNameInFilter()
                .waitUntilPreloaderDisappear()
                .clickSecondBrandNameInFilter()
                .waitUntilPreloaderDisappear()
                .checkProductTitleOnListingWithTwoExpectedTexts(brand1, brand2, true, listingPageLogic.productTitleInListMode());
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks brand filter with two brands selected (LKW listing)")
    public void checkBrandFilterWithTwoBrandsSelectedLKW(String route) {
        openPage(route);
        new Main_page_Logic().closeCarSelectorTooltipIfVisible();
        listingPageLogic.clickFirstBrandNameInFilter()
                    .waitUntilPreloaderDisappear();
        String firstBrand = listingPageLogic.firstBrandNameInFiler().attr("alt");
        String secondBrand = listingPageLogic.secondBrandNameInFilter().attr("alt");
        listingPageLogic.clickSecondBrandNameInFilter()
                    .waitUntilPreloaderDisappear()
                    .checkProductTitleOnListingWithTwoExpectedTexts(firstBrand, secondBrand, true, listingPageLogic.productTitleInListMode());
    }
    @AfterMethod
    private void teatDown() {
        close();
    }
}
