package ATD.QASYS_35_FiltersSorting;


import ATD.DataBase;
import ATD.ListingTecdoc_page;
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
    private ListingTecdoc_page listingTecdocPage = new ListingTecdoc_page();
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
        listingTecdocPage.firstBrandInFilterButton().click();
        listingTecdocPage.preloader().shouldBe(attribute("style", "display: none;"));
        String brand1 = listingTecdocPage.firstBrandNameInFiler().attr("alt");
        listingTecdocPage.secondBrandInFilterButton().click();
        listingTecdocPage.preloader().shouldBe(attribute("style", "display: none;"));
        String brand2 = listingTecdocPage.firstBrandNameInFiler().attr("alt");
        listingTecdocPage.checkProductTitleOnListingWithTwoExpectedTexts(brand1, brand2, true, listingTecdocPage.productTitleInListMode());
        close();
    }

    @Test(dataProvider = "routeOem")
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC01 Test checks brand filter with two brands selected (Oem listing)")
    public void checkBrandFilterWithTwoBrandsSelectedOem(String route) {
        open(route);
        listingTecdocPage.firstBrandButtonOemListing().click();
        listingTecdocPage.preloader().shouldBe(attribute("style", "display: none;"));
        String brand1 = listingTecdocPage.firstBrandNameOemListing().attr("alt");
        listingTecdocPage.secondBrandButtonOemListing().click();
        listingTecdocPage.preloader().shouldBe(attribute("style", "display: none;"));
        String brand2 = listingTecdocPage.firstBrandNameOemListing().attr("alt");
        listingTecdocPage.checkProductTitleOnListingWithTwoExpectedTexts(brand1, brand2, true, listingTecdocPage.productTitleInListMode());
        close();
    }

    @Test(dataProvider = "routeAcc")
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC01 Test checks brand filter with two brands selected (Acc listing)")
    public void checkBrandFilterWithTwoBrandsSelectedAcc(String route) {
        open(route);
        String brand1 = listingTecdocPage.firstBrandNameInFiler().attr("alt");
        String brand2 = listingTecdocPage.secondBrandNameInFilter().attr("alt");
        listingTecdocPage.firstBrandInFilterButton().click();
        listingTecdocPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingTecdocPage.secondBrandInFilterButton().click();
        listingTecdocPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingTecdocPage.checkProductTitleOnListingWithTwoExpectedTexts(brand1, brand2, true, listingTecdocPage.productTitleInListMode());
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC01 Test checks brand filter with two brands selected (LKW listing)")
    public void checkBrandFilterWithTwoBrandsSelectedLKW(String route) {
        open(route);
        listingTecdocPage.firstBrandInFilterButton().click();
        listingTecdocPage.preloader().shouldBe(attribute("style", "display: none;"));
        String firstBrand = listingTecdocPage.firstBrandNameInFiler().attr("alt");
        String secondBrand = listingTecdocPage.secondBrandNameInFilter().attr("alt");
        listingTecdocPage.secondBrandInFilterButton().click();
        listingTecdocPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingTecdocPage.checkProductTitleOnListingWithTwoExpectedTexts(firstBrand, secondBrand, true, listingTecdocPage.productTitleInListMode());
        close();
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC02 Test checks brand filter in tile mode (Tecdoc listing)")
    public void checkBrandFilterInTileMode(String route) {
        open(route);
        closeCookiesFooterMessage();
        listingTecdocPage.firstBrandInFilterButton().click();
        listingTecdocPage.preloader().shouldBe(attribute("style", "display: none;"));
        String brand1 = listingTecdocPage.firstBrandNameInFiler().attr("alt");
        listingTecdocPage.showListingInTileModeButton().click();
        listingTecdocPage.checkProductTitleOnListing(brand1, true, listingTecdocPage.productTitleInTileMode());
        close();
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC02 Test checks brand filter in tile mode (LKW listing)")
    public void checkBrandFilterInTileModeLKW(String route) {
        open(route);
        listingTecdocPage.firstBrandInFilterButton().click();
        listingTecdocPage.preloader().shouldBe(attribute("style", "display: none;"));
        String brand1 = listingTecdocPage.firstBrandNameInFiler().attr("alt");
        listingTecdocPage.showListingInTileModeButton().click();
        listingTecdocPage.checkProductTitleOnListing(brand1, true, listingTecdocPage.productTitleInTileMode());
        close();
    }

    @Test(dataProvider = "routesTecdocBySide")
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC03 Test checks brand filter with filter by side (Tecdoc listing)")
    public void checkBrandFilterWithFilterBySide(String route) {
        open(route);
        closeCookiesFooterMessage();
        listingTecdocPage.firstBrandInFilterButton().click();
        listingTecdocPage.preloader().shouldBe(attribute("style", "display: none;"));
        String brand1 = listingTecdocPage.firstBrandNameInFiler().attr("alt");
        listingTecdocPage.filterBySideBack().click();
        listingTecdocPage.checkProductTitleOnListing(brand1, true, listingTecdocPage.productTitleInListMode());
        close();
    }

    @Test
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC03 Test checks brand filter with filter by side (LKW listing)")
    public void checkBrandFilterWithFilterBySideLKWsearch() throws SQLException {
        open("https://lkwteile.autodoc.de/" +  dataBase.getRouteByRouteName("DE", "lkw_search"));
        String brand1 = listingTecdocPage.firstBrandNameInFiler().attr("alt");
        listingTecdocPage.filterBySideLKW().click();
        listingTecdocPage.firstBrandInFilterButton().click();
        listingTecdocPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingTecdocPage.getBrandFromTitle(brand1, 2, true, listingTecdocPage.productTitleInListMode());
    }

    @Test
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC03 Test checks brand filter with filter by side (LKW listing)")
    public void checkBrandFilterWithFilterBySideLKWcategory() throws SQLException {
        open("https://lkwteile.autodoc.de/" +  dataBase.getRouteByRouteName("DE", "lkw_category_car_list"));
        String brand1 = listingTecdocPage.firstBrandNameInFiler().attr("alt");
        listingTecdocPage.filterBySideLKW().click();
        listingTecdocPage.firstBrandInFilterButton().click();
        listingTecdocPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingTecdocPage.getBrandFromTitle(brand1, 0, true, listingTecdocPage.productTitleInListMode());
    }

    @Test
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC03 Test checks brand filter with filter by side (LKW listing)")
    public void checkBrandFilterWithFilterBySideLKWcategory2() throws SQLException {
        open("https://lkwteile.autodoc.de/" +  dataBase.getRouteByRouteName("DE", "lkw_category_car_list2"));
        listingTecdocPage.filterBySideLKW().click();
        listingTecdocPage.preloader().shouldBe(attribute("style", "display: none;"));
        String brand1 = listingTecdocPage.firstBrandNameInFiler().attr("alt");
        listingTecdocPage.firstBrandInFilterButton().click();
        listingTecdocPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingTecdocPage.getBrandFromTitle(brand1, 0, true, listingTecdocPage.productTitleInListMode());
    }


    @Test
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC04 Test checks brand filter with pagination (Tecdoc listing)")
    public void checkBrandFilterPagination() throws SQLException {
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "search2"));
        closeCookiesFooterMessage();
        listingTecdocPage.firstBrandInFilterButton().click();
        listingTecdocPage.preloader().shouldBe(attribute("style", "display: none;"));
        String brand1 = listingTecdocPage.firstBrandNameInFiler().attr("alt");
        listingTecdocPage.secondListingPage().click();
        listingTecdocPage.checkProductTitleOnListing(brand1, true, listingTecdocPage.productTitleInListMode());
        close();
    }

    @Test
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC05 Test checks brand filter with pagination (LKW listing)")
    public void checkBrandFilterPaginationLKW() throws SQLException {
        open("https://lkwteile.autodoc.de/" +  dataBase.getRouteByRouteName("DE", "lkw_category_car_list5"));
        String brandName1 = listingTecdocPage.firstBrandNameInFiler().attr("alt").split(" ")[0];
        String brandName2 = listingTecdocPage.secondBrandNameInFilter().attr("alt").split(" ")[0];
        String brandName3 = listingTecdocPage.thirdBrandNameInFilter().attr("alt").split(" ")[0];
        String brandName4 = listingTecdocPage.fourthBrandNameInFilter().attr("alt").split(" ")[0];
        String brandName5 = listingTecdocPage.fifthBrandNameInFilter().attr("alt").split(" ")[0];
        String brandName6 = listingTecdocPage.sixthBrandNameInFilter().attr("alt").split(" ")[0];
        listingTecdocPage.secondListingPage().click();
        listingTecdocPage.checkProductTitleOnListingWithSixExpectedTexts(brandName1, brandName2, brandName3, brandName4, brandName5, brandName6,true, listingTecdocPage.productTitleInListMode());
        close();
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC06 Test checks brand filter reset (Tecdoc listing)")
    public void checkBrandFilterReset(String route) {
        open(route);
        closeCookiesFooterMessage();
        listingTecdocPage.firstBrandInFilterButton().click();
        listingTecdocPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingTecdocPage.firstBrandInFilterButton().click();
        listingTecdocPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingTecdocPage.checkUniqueBrandsOnListing(3, listingTecdocPage.productTitleInListMode());
        close();
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC06 Test checks brand filter reset (LKW listing)")
    public void checkBrandFilterResetLKW(String route) {
        open(route);
        listingTecdocPage.firstBrandInFilterButton().click();
        listingTecdocPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingTecdocPage.firstBrandInFilterButton().click();
        listingTecdocPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingTecdocPage.checkUniqueBrandsOnListing(3, listingTecdocPage.productTitleInListMode());
        close();
    }

    @Test(dataProvider = "routeAcc")
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC06 Test checks brand filter reset (Acc listing)")
    public void checkBrandFilterResetAcc(String route) {
        open(route);
        listingTecdocPage.firstBrandInFilterButton().click();
        listingTecdocPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingTecdocPage.firstBrandInFilterButton().click();
        listingTecdocPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingTecdocPage.checkUniqueBrandsOnListing(3, listingTecdocPage.productTitleInListMode());
    }

    @Test(dataProvider = "routeOem")
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC06 Test checks brand filter reset (Oem listing)")
    public void checkBrandFilterResetOem(String route) {
        open(route);
        listingTecdocPage.firstBrandButtonOemListing().click();
        listingTecdocPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingTecdocPage.firstBrandButtonOemListing().click();
        listingTecdocPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingTecdocPage.checkUniqueBrandsOnListing(3, listingTecdocPage.productTitleInListMode());
    }

    @Test
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC07 TC08 Test checks brand filter reset (LKW listing)")
    public void checkBrandFilterBySideLKW() throws SQLException {
        open("https://lkwteile.autodoc.de/" +  dataBase.getRouteByRouteName("DE", "lkw_category_car_list"));
        listingTecdocPage.filterBySideLKW().click();
        listingTecdocPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingTecdocPage.brandFilterBlock().shouldBe(visible);
    }

    @Test
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC09 Test checks brand filter with characteristic filter (LKW listing)")
    public void checkBrandFilterCharacteristic() throws SQLException{
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_car_list"));
        String brandName = listingTecdocPage.secondBrandNameInFilter().text();
        listingTecdocPage.firstBrandInFilterButton().click();
        listingTecdocPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingTecdocPage.gelochtAttribute().click();
        listingTecdocPage.checkFilterBySide("Gelocht");
        listingTecdocPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingTecdocPage.checkProductTitleOnListing(brandName, true, listingTecdocPage.productTitleInListMode());
    }
}
