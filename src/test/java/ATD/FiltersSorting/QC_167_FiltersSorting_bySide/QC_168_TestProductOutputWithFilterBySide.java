package ATD.FiltersSorting.QC_167_FiltersSorting_bySide;


import ATD.DataBase;
import ATD.Listing_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.close;

public class QC_168_TestProductOutputWithFilterBySide {
    private Listing_page_Logic listingPage = new Listing_page_Logic();
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
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list6,lkw_category_car_list7,lkw_search");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks filter by side in tile mode")
    public void testFilterBySideInTileMode(String route) {
        openPage(route);
        listingPage.filterBySideBack().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        int productWithFiler = listingPage.einbauseiteProductAttributeGenericRoute().size();
        listingPage.showListingInTileModeButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingInTileMode2("hinten", productWithFiler);
        listingPage.showListingInListModeButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.secondListingPage().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingWithCarAndFilter("hinten", listingPage.einbauseiteProductAttributeGenericRoute(), listingPage.einbauseiteProductAttributeTecdocRoute());
        listingPage.filterBySideBack().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkUniqueBrandsOnListing(2, listingPage.einbauseiteProductAttributeTecdocRoute());
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks filter by side in tile mode")
    public void testFilterBySideInTileModeSearchRoute() throws SQLException {
        openPage("https://autodoc.de/" + dataBase.getRouteByRouteName("DE", "search5"));
        listingPage.filterBySideLKW().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        int productWithFiler = listingPage.einbauseiteProductAttributeGenericRoute().size();
        listingPage.showListingInTileModeButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingInTileMode2("vorne", productWithFiler);
        listingPage.showListingInListModeButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.secondListingPage().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingWithCarAndFilter("vorne", listingPage.einbauseiteProductAttributeGenericRoute(), listingPage.einbauseiteProductAttributeTecdocRoute());
        listingPage.filterBySideLKW().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkUniqueBrandsOnListing(2, listingPage.einbauseiteProductAttributeTecdocRoute());
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks filter by side in tile mode LKW")
    public void testFilterBySideInTileModeLKW(String route) {
        openPage(route);
        listingPage.filterBySideLKW().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.showListingInTileModeButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        int vorderachseAttributes = listingPage.vorderachseAttributeInTileMode().size();
        int producntsOnListing = listingPage.productsOnListingInTileMode().size();
        Assert.assertEquals(vorderachseAttributes, producntsOnListing);
        listingPage.showListingInListModeButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.secondListingPage().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingWithCarAndFilter("Vorderachse", listingPage.einbauseiteProductAttributeGenericRoute(), listingPage.einbauseiteProductAttributeTecdocRoute());
        listingPage.filterBySideLKW().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkUniqueBrandsOnListing(2, listingPage.einbauseiteProductAttributeTecdocRoute());
    }

    @AfterMethod
    public void tearDown() {
        close();
    }
}
