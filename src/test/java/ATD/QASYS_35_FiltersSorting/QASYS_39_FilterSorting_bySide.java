package ATD.QASYS_35_FiltersSorting;


import ATD.DataBase;
import ATD.Listing_page;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.closeCookiesFooterMessage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.open;

public class QASYS_39_FilterSorting_bySide {
    private Listing_page listingPage = new Listing_page();
    private DataBase dataBase = new DataBase();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_car_list2,search4,search5");
    }

    @DataProvider(name = "routesLKW", parallel = true)
    Object[] dataProviderLKW2routes() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list6,lkw_category_car_list7,lkw_search");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC01 Test checks filter by side")
    public void testFilterBySide(String route) {
        open(route);
        listingPage.filterBySideBack().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingWithCarAndFilter("hinten", listingPage.einbauseiteProductAttributeGenericRoute(), listingPage.einbauseiteProductAttributeTecdocRoute());
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC01 Test checks filter by side ACC")
    public void testFilterBySideAcc() throws SQLException {
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_car_list2"));
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "listing_accessories"));
        listingPage.filterBySideBack().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingWithCarAndFilter("hinten", listingPage.einbauseiteProductAttributeGenericRoute(), listingPage.einbauseiteProductAttributeTecdocRoute());
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC01 Test checks filter by side LKW")
    public void testFilterBySideLKW(String route) {
        open(route);
        listingPage.filterBySideLKW().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        int vorderachseAttributes = listingPage.vorderachseAttributeInListMode().size();
        int producntsOnListing = listingPage.productsOnListingInListMode().size();
        Assert.assertEquals(vorderachseAttributes, producntsOnListing);
    }


    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC02 Test checks filter by side in tile mode")
    public void testFilterBySideInTileMode(String route) {
        open(route);
        closeCookiesFooterMessage();
        listingPage.filterBySideBack().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        int productWithFiler = listingPage.einbauseiteProductAttributeGenericRoute().size();
        listingPage.showListingInTileModeButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingInTileMode2("hinten", productWithFiler);
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC02 Test checks filter by side in tile mode ACC")
    public void testFilterBySideInTileModeAcc() throws SQLException {
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_car_list2"));
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "listing_accessories"));
        closeCookiesFooterMessage();
        listingPage.filterBySideBack().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        int productWithFiler = listingPage.einbauseiteProductAttributeGenericRoute().size();
        listingPage.showListingInTileModeButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingInTileMode2("hinten", productWithFiler);
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC02 Test checks filter by side in tile mode LKW")
    public void testFilterBySideInTileModeLKW(String route) {
        open(route);
        closeCookiesFooterMessage();
        listingPage.filterBySideLKW().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.showListingInTileModeButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        int vorderachseAttributes = listingPage.vorderachseAttributeInTileMode().size();
        int producntsOnListing = listingPage.productsOnListingInTileMode().size();
        Assert.assertEquals(vorderachseAttributes, producntsOnListing);
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC03 Test checks filter by side pagination")
    public void testFilterBySidePagination(String route) {
        open(route);
        closeCookiesFooterMessage();
        listingPage.filterBySideLKW().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.secondListingPage().click();
        listingPage.checkProductAttributeOnListingWithCarAndFilter("vorne", listingPage.einbauseiteProductAttributeGenericRoute(), listingPage.einbauseiteProductAttributeTecdocRoute());
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC03 Test checks filter by side pagination ACC")
    public void testFilterBySidePaginationAcc() throws SQLException {
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_car_list2"));
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "listing_accessories3"));
        closeCookiesFooterMessage();
        listingPage.filterBySideLKW().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.secondListingPage().click();
        listingPage.checkProductAttributeOnListingWithCarAndFilter("vorne", listingPage.einbauseiteProductAttributeGenericRoute(), listingPage.einbauseiteProductAttributeTecdocRoute());
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC03 Test checks filter by side pagination LKW")
    public void testFilterBySidePaginationLKW(String route) {
        open(route);
        closeCookiesFooterMessage();
        listingPage.filterBySideLKW().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.secondListingPage().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        int vorderachseAttributes = listingPage.vorderachseAttributeInListMode().size();
        int producntsOnListing = listingPage.productsOnListingInListMode().size();
        Assert.assertEquals(vorderachseAttributes, producntsOnListing);
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC04 Test checks filter by side cancelling")
    public void testFilterBySideCancelling(String route) {
        open(route);
        listingPage.filterBySideBack().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingWithCarAndFilter("hinten", listingPage.einbauseiteProductAttributeGenericRoute(), listingPage.einbauseiteProductAttributeTecdocRoute());
        listingPage.filterBySideBack().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkUniqueBrandsOnListing(2, listingPage.einbauseiteProductAttributeTecdocRoute());
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC04 Test checks filter by side cancelling ACC")
    public void testFilterBySideCancellingAcc() throws SQLException {
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_car_list2"));
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "listing_accessories"));
        listingPage.filterBySideBack().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingWithCarAndFilter("hinten", listingPage.einbauseiteProductAttributeGenericRoute(), listingPage.einbauseiteProductAttributeTecdocRoute());
        listingPage.filterBySideBack().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkUniqueBrandsOnListing(2, listingPage.einbauseiteProductAttributeTecdocRoute());
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC04 Test checks filter by side cancelling LKW")
    public void testFilterBySideCancellingLKW(String route) {
        open(route);
        listingPage.filterBySideBack().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.filterBySideBack().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkUniqueBrandsOnListing(2, listingPage.einbauseiteProductAttributeTecdocRoute());
    }
}
