package ATD.QASYS_35_FiltersSorting;


import ATD.DataBase;
import ATD.Listing_page;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.SetUp.setUpBrowser;
import static ATD.CommonMethods.closeCookiesFooterMessage;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class QASYS_36_FilterSorting_byBrand {
    private Listing_page listingPage = new Listing_page();
    private DataBase dataBase = new DataBase();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_car_list,search2,category_car_list6");
    }

    @DataProvider(name = "routesTecdocBySide", parallel = true)
    Object[] dataProvidertecdocBySide() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_car_list,search2");
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
    @Owner(value = "oleg")
    @Description(value = "TC01 Test checks brand filter with two brands selected (Tecdoc listing)")
    public void checkBrandFilterWithTwoBrandsSelected(String route) {
        open(route);
        closeCookiesFooterMessage();
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
    @Owner(value = "oleg")
    @Description(value = "TC01 Test checks brand filter with two brands selected (Oem listing)")
    public void checkBrandFilterWithTwoBrandsSelectedOem(String route) {
        open(route);
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
    @Owner(value = "oleg")
    @Description(value = "TC01 Test checks brand filter with two brands selected (Acc listing)")
    public void checkBrandFilterWithTwoBrandsSelectedAcc(String route) {
        open(route);
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
    @Owner(value = "oleg")
    @Description(value = "TC01 Test checks brand filter with two brands selected (LKW listing)")
    public void checkBrandFilterWithTwoBrandsSelectedLKW(String route) {
        open(route);
        listingPage.firstBrandInFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        String firstBrand = listingPage.firstBrandNameInFiler().attr("alt");
        String secondBrand = listingPage.secondBrandNameInFilter().attr("alt");
        listingPage.secondBrandInFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductTitleOnListingWithTwoExpectedTexts(firstBrand, secondBrand, true, listingPage.productTitleInListMode());
        close();
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC02 Test checks brand filter in tile mode (Tecdoc listing)")
    public void checkBrandFilterInTileMode(String route) {
        open(route);
        closeCookiesFooterMessage();
        listingPage.firstBrandInFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        String brand1 = listingPage.firstBrandNameInFiler().attr("alt");
        listingPage.showListingInTileModeButton().click();
        listingPage.checkProductTitleOnListing(brand1, true, listingPage.productTitleInTileMode());
        close();
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC02 Test checks brand filter in tile mode (LKW listing)")
    public void checkBrandFilterInTileModeLKW(String route) {
        open(route);
        listingPage.firstBrandInFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        String brand1 = listingPage.firstBrandNameInFiler().attr("alt");
        listingPage.showListingInTileModeButton().click();
        listingPage.checkProductTitleOnListing(brand1, true, listingPage.productTitleInTileMode());
        close();
    }

    @Test(dataProvider = "routesTecdocBySide")
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC03 Test checks brand filter with filter by side (Tecdoc listing)")
    public void checkBrandFilterWithFilterBySide(String route) {
        open(route);
        closeCookiesFooterMessage();
        listingPage.firstBrandInFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        String brand1 = listingPage.firstBrandNameInFiler().attr("alt");
        listingPage.filterBySideBack().click();
        listingPage.checkProductTitleOnListing(brand1, true, listingPage.productTitleInListMode());
        close();
    }

    @Test
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC03 Test checks brand filter with filter by side (LKW listing)")
    public void checkBrandFilterWithFilterBySideLKWsearch() throws SQLException {
        open("https://lkwteile.autodoc.de/" +  dataBase.getRouteByRouteName("DE", "lkw_search"));
        String brand1 = listingPage.firstBrandNameInFiler().attr("alt");
        listingPage.filterBySideLKW().click();
        listingPage.firstBrandInFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.getBrandFromTitle(brand1, 2, true, listingPage.productTitleInListMode());
    }

    @Test
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC03 Test checks brand filter with filter by side (LKW listing)")
    public void checkBrandFilterWithFilterBySideLKWcategory() throws SQLException {
        open("https://lkwteile.autodoc.de/" +  dataBase.getRouteByRouteName("DE", "lkw_category_car_list"));
        String brand1 = listingPage.firstBrandNameInFiler().attr("alt");
        listingPage.filterBySideLKW().click();
        listingPage.firstBrandInFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.getBrandFromTitle(brand1, 0, true, listingPage.productTitleInListMode());
    }

    @Test
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC03 Test checks brand filter with filter by side (LKW listing)")
    public void checkBrandFilterWithFilterBySideLKWcategory2() throws SQLException {
        open("https://lkwteile.autodoc.de/" +  dataBase.getRouteByRouteName("DE", "lkw_category_car_list2"));
        listingPage.filterBySideLKW().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        String brand1 = listingPage.firstBrandNameInFiler().attr("alt");
        listingPage.firstBrandInFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.getBrandFromTitle(brand1, 0, true, listingPage.productTitleInListMode());
    }


    @Test
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC04 Test checks brand filter with pagination (Tecdoc listing)")
    public void checkBrandFilterPagination() throws SQLException {
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "search2"));
        closeCookiesFooterMessage();
        listingPage.firstBrandInFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        String brand1 = listingPage.firstBrandNameInFiler().attr("alt");
        listingPage.secondListingPage().click();
        listingPage.checkProductTitleOnListing(brand1, true, listingPage.productTitleInListMode());
        close();
    }

    @Test
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC05 Test checks brand filter with pagination (LKW listing)")
    public void checkBrandFilterPaginationLKW() throws SQLException {
        open("https://lkwteile.autodoc.de/" +  dataBase.getRouteByRouteName("DE", "lkw_category_car_list5"));
        String brandName1 = listingPage.firstBrandNameInFiler().attr("alt").split(" ")[0];
        String brandName2 = listingPage.secondBrandNameInFilter().attr("alt").split(" ")[0];
        String brandName3 = listingPage.thirdBrandNameInFilter().attr("alt").split(" ")[0];
        String brandName4 = listingPage.fourthBrandNameInFilter().attr("alt").split(" ")[0];
        String brandName5 = listingPage.fifthBrandNameInFilter().attr("alt").split(" ")[0];
        String brandName6 = listingPage.sixthBrandNameInFilter().attr("alt").split(" ")[0];
        listingPage.secondListingPage().click();
        listingPage.checkProductTitleOnListingWithSixExpectedTexts(brandName1, brandName2, brandName3, brandName4, brandName5, brandName6,true, listingPage.productTitleInListMode());
        close();
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC06 Test checks brand filter reset (Tecdoc listing)")
    public void checkBrandFilterReset(String route) {
        open(route);
        closeCookiesFooterMessage();
        listingPage.firstBrandInFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.firstBrandInFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkUniqueBrandsOnListing(3, listingPage.productTitleInListMode());
        close();
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC06 Test checks brand filter reset (LKW listing)")
    public void checkBrandFilterResetLKW(String route) {
        open(route);
        listingPage.firstBrandInFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        if(listingPage.tooltipCarSelectorClose().is(visible)) {
            listingPage.tooltipCarSelectorClose().click();
        }
        listingPage.firstBrandInFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkUniqueBrandsOnListing(3, listingPage.productTitleInListMode());
        close();
    }

    @Test(dataProvider = "routeAcc")
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC06 Test checks brand filter reset (Acc listing)")
    public void checkBrandFilterResetAcc(String route) {
        open(route);
        listingPage.firstBrandInFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.firstBrandInFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkUniqueBrandsOnListing(3, listingPage.productTitleInListMode());
    }

    @Test(dataProvider = "routeOem")
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC06 Test checks brand filter reset (Oem listing)")
    public void checkBrandFilterResetOem(String route) {
        open(route);
        listingPage.firstBrandButtonOemListing().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.firstBrandButtonOemListing().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkUniqueBrandsOnListing(3, listingPage.productTitleInListMode());
    }

    @Test
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC07 TC08 Test checks brand filter reset (LKW listing)")
    public void checkBrandFilterBySideLKW() throws SQLException {
        open("https://lkwteile.autodoc.de/" +  dataBase.getRouteByRouteName("DE", "lkw_category_car_list"));
        listingPage.filterBySideLKW().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.brandFilterBlock().shouldBe(visible);
    }

    @Test
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC09 Test checks brand filter with characteristic filter (LKW listing)")
    public void checkBrandFilterCharacteristic() throws SQLException{
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_car_list"));
        String brandName = listingPage.secondBrandNameInFilter().text();
        listingPage.firstBrandInFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.gelochtAttribute().click();
        listingPage.checkFilterBySide("Gelocht");
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductTitleOnListing(brandName, true, listingPage.productTitleInListMode());
    }
}
