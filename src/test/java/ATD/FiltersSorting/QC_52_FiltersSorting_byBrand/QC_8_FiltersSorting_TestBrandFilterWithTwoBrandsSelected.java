package ATD.FiltersSorting.QC_52_FiltersSorting_byBrand;


import ATD.Listing_page;
import ATD.Main_page;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.close;

public class QC_8_FiltersSorting_TestBrandFilterWithTwoBrandsSelected {
    private Listing_page listingPage = new Listing_page();
    private Main_page mainPage = new Main_page();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_car_list,search2,category_car_list6");
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
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_search,lkw_category_car_list,lkw_category_car_list2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks brand filter with two brands selected (Tecdoc listing)")
    public void checkBrandFilterWithTwoBrandsSelected(String route) {
        openPage(route);
        listingPage.firstBrandInFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        String brand1 = listingPage.firstBrandNameInFiler().attr("alt");
        listingPage.secondBrandInFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        String brand2 = listingPage.firstBrandNameInFiler().attr("alt");
        listingPage.checkProductTitleOnListingWithTwoExpectedTexts(brand1, brand2, true, listingPage.productTitleInListMode());
        close();
    }

    @Test(dataProvider = "routeOem")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks brand filter with two brands selected (Oem listing)")
    public void checkBrandFilterWithTwoBrandsSelectedOem(String route) {
        openPage(route);
        listingPage.firstBrandButtonOemListing().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        String brand1 = listingPage.firstBrandNameOemListing().attr("alt");
        listingPage.secondBrandButtonOemListing().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        String brand2 = listingPage.firstBrandNameOemListing().attr("alt");
        listingPage.checkProductTitleOnListingWithTwoExpectedTexts(brand1, brand2, true, listingPage.productTitleInListMode());
        close();
    }

    @Test(dataProvider = "routeAcc")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks brand filter with two brands selected (Acc listing)")
    public void checkBrandFilterWithTwoBrandsSelectedAcc(String route) {
        openPage(route);
        String brand1 = listingPage.firstBrandNameInFiler().attr("alt");
        String brand2 = listingPage.secondBrandNameInFilter().attr("alt");
        listingPage.firstBrandInFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.secondBrandInFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductTitleOnListingWithTwoExpectedTexts(brand1, brand2, true, listingPage.productTitleInListMode());
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks brand filter with two brands selected (LKW listing)")
    public void checkBrandFilterWithTwoBrandsSelectedLKW(String route) {
        openPage(route);
        mainPage.closeCarSelectorTooltipIfVisible();
        listingPage.firstBrandInFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        String firstBrand = listingPage.firstBrandNameInFiler().attr("alt");
        String secondBrand = listingPage.secondBrandNameInFilter().attr("alt");
        listingPage.secondBrandInFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductTitleOnListingWithTwoExpectedTexts(firstBrand, secondBrand, true, listingPage.productTitleInListMode());
        close();
    }
}
