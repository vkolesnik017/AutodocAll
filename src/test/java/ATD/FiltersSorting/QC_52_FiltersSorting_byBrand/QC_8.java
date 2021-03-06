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

public class QC_8 {

    private Listing_page_Logic listingPageLogic = new Listing_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list,search2,category_car_list6,search19,listing_chemicals");
    }

    @DataProvider(name = "routeOem", parallel = true)
    Object[] dataProviderOem() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_oen3");
    }

    @DataProvider(name = "routeAcc", parallel = true)
    Object[] dataProviderAcc() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "listing_instruments,listing_instruments7,accessories_listing_criteria");
    }

    @DataProvider(name = "routesLKW", parallel = true)
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_search6,lkw_search,lkw_category_car_list,lkw_category_car_list2");
    }

    @DataProvider(name = "routesMoto", parallel = true)
    Object[] dataProviderMoto() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_search,moto_category_car_list,moto_category_car_list_model5");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks brand filter with two brands selected (Tecdoc listing)")
    public void checkBrandFilterWithTwoBrandsSelected(String route) {
        openPage(route);
        listingPageLogic.clickFirstBrandNameInFilter()
                .waitUntilPreloaderDisappear();
        listingPageLogic.clickSecondBrandNameInFilter()
                .waitUntilPreloaderDisappear();
        String brand1 = listingPageLogic.getTextOrAttributeFromFilter(listingPageLogic.firstResetBrandBtn(), listingPageLogic.firstBrandNameInFiler(), "alt");
        String brand2 = listingPageLogic.getTextOrAttributeFromFilter(listingPageLogic.secondResetBtn(), listingPageLogic.secondBrandNameInFilter(), "alt");
        listingPageLogic.checkThatListingDisplaysProductOfTwoSelectedBrand(brand1, brand2, true, listingPageLogic.productTitleInListMode());
    }

    @Test(dataProvider = "routeOem")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks brand filter with two brands selected (Oem listing)")
    public void checkBrandFilterWithTwoBrandsSelectedOem(String route) {
        openPage(route);
        listingPageLogic.clickFirstBrandNameOemListing()
                .waitUntilPreloaderDisappear();
        String brand1 = listingPageLogic.getAtributeFromElement(listingPageLogic.firstBrandNameOemListing(), "alt");
        listingPageLogic.clickSecondBrandNameOemListing()
                .waitUntilPreloaderDisappear();
        String brand2 = listingPageLogic.getAtributeFromElement(listingPageLogic.firstBrandNameOemListing(), "alt");
        listingPageLogic.checkProductTitleOnListingWithTwoExpectedTexts(brand1, brand2, true, listingPageLogic.productTitleInListMode());
    }

    @Test(dataProvider = "routeAcc")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks brand filter with two brands selected (Acc listing)")
    public void checkBrandFilterWithTwoBrandsSelectedAcc(String route) {
        openPage(route);
        String brand1 = listingPageLogic.getAtributeFromElementAndReturnsFirstWord(listingPageLogic.firstBrandNameInFiler(), "alt");
        String brand2 = listingPageLogic.getAtributeFromElementAndReturnsFirstWord(listingPageLogic.secondBrandNameInFilter(), "alt");
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
        listingPageLogic.clickSecondBrandNameInFilter()
                .waitUntilPreloaderDisappear();
        String brand1 = listingPageLogic.getTextOrAttributeFromFilter(listingPageLogic.firstResetBrandBtn(), listingPageLogic.firstBrandNameInFiler(), "alt");
        String brand2 = listingPageLogic.getTextOrAttributeFromFilter(listingPageLogic.secondResetBtn(), listingPageLogic.secondBrandNameInFilter(), "alt");
        listingPageLogic.checkThatListingDisplaysProductOfTwoSelectedBrand(brand1, brand2, true, listingPageLogic.productTitleInListMode());
    }


    @Test(dataProvider = "routesMoto")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks brand filter with two brands selected (Moto listing)")
    public void checkBrandFilterWithTwoBrandsSelectedMoto(String route) {
        openPage(route);
        new Main_page_Logic().closeCarSelectorTooltipIfVisible();
        listingPageLogic.clickFirstBrandNameInFilter()
                .waitWhileFirstBrandBecomeActive();
        listingPageLogic.clickSecondBrandNameInFilter()
                .waitWhileSecondBrandBecomeActive();
        String brand1 = listingPageLogic.getTextOrAttributeFromFilter(listingPageLogic.firstResetBrandBtn(), listingPageLogic.firstBrandNameInFiler(), "alt");
        String brand2 = listingPageLogic.getTextOrAttributeFromFilter(listingPageLogic.secondResetBtn(), listingPageLogic.secondBrandNameInFilter(), "alt");
        listingPageLogic.checkProductTitleOnListingWithTwoExpectedTexts(brand1, brand2, true, listingPageLogic.productTitleInListMode());
    }


    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
