package ATD.QASYS_35_FiltersSorting;


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

import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;

public class QASYS_38_FiltersSorting_byGeneric {
    private Listing_page listingPage = new Listing_page();
    private Main_page mainPage = new Main_page();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_car_list4,search,listing_accessories");
    }

    @DataProvider(name = "routesLKW", parallel = true)
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_search2,lkw_category_car_list4,lkw_search2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC01 Test checks generic filter on listing (Tecdoc, ACC)")
    public void checkFilterByGeneric(String route) {
        open(route);
        String genericName = listingPage.firstGeneric().text();
        listingPage.firstGeneric().click();
        listingPage.checkProductTitleOnListing(genericName, true, listingPage.productTitleInListMode());
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC01 Test checks generic filter on listing LKW")
    public void checkFilterByGenericLKW(String route) {
        open(route);
        mainPage.closeCarSelectorTooltipIfVisible();
        String genericName = listingPage.secondGeneric().text();
        listingPage.secondGeneric().click();
        listingPage.checkProductTitleOnListing(genericName, true, listingPage.productTitleInListMode());
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC02 Test checks generic filter on listing (Tecdoc, ACC) with two generics")
    public void checkFilterWithTwoGenerics(String route) {
        open(route);
        String genericName = listingPage.firstGeneric().getText();
        listingPage.firstGeneric().click();
        String genericName2 = listingPage.fourthGeneric().getText();
        listingPage.fourthGeneric().click();
        listingPage.checkProductTitleOnListing(genericName2, true, listingPage.productTitleInListMode());
        listingPage.checkProductTitleOnListing(genericName, false, listingPage.productTitleInListMode());
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC02 Test checks generic filter on listing LKW with two generics")
    public void checkFilterWithTwoGenericsLKW(String route) {
        open(route);
        mainPage.closeCarSelectorTooltipIfVisible();
        String genericName = listingPage.firstGeneric().getText();
        listingPage.firstGeneric().click();
        String genericName2 = listingPage.secondGeneric().getText();
        listingPage.secondGeneric().click();
        listingPage.checkProductTitleOnListing(genericName2, true, listingPage.productTitleInListMode());
        listingPage.checkProductTitleOnListing(genericName, false, listingPage.productTitleInListMode());
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC03 Test checks generic position in generic block on listing (Tecdoc, ACC)")
    public void checkGenericPosition(String route) {
        open(route);
        String genericName = listingPage.secondGeneric().text();
        listingPage.secondGeneric().click();
        listingPage.firstGeneric().shouldHave(text(genericName));
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC03 Test checks generic position in generic block on listing LKW")
    public void checkGenericPositionLKW(String route) {
        open(route);
        mainPage.closeCarSelectorTooltipIfVisible();
        String genericName = listingPage.secondGeneric().text();
        listingPage.secondGeneric().click();
        listingPage.firstGeneric().shouldHave(text(genericName));
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC04 Test checks generic filter on listing (Tecdoc, ACC) in tile mode")
    public void checkFilterByGenericInTileMode(String route) {
        open(route);
        String genericName = listingPage.fourthGeneric().text();
        listingPage.fourthGeneric().click();
        listingPage.checkProductTitleOnListing(genericName, true, listingPage.productTitleInListMode());
        listingPage.showListingInTileModeButton().click();
        listingPage.checkProductTitleOnListing(genericName, true, listingPage.productTitleInTileMode());
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC04 Test checks generic filter on listing LKW in tile mode")
    public void checkFilterByGenericInTileModeLKW(String route) {
        open(route);
        mainPage.closeCarSelectorTooltipIfVisible();
        String genericName = listingPage.secondGeneric().text();
        listingPage.secondGeneric().click();
        listingPage.checkProductTitleOnListing(genericName, true, listingPage.productTitleInListMode());
        listingPage.showListingInTileModeButton().click();
        listingPage.checkProductTitleOnListing(genericName, true, listingPage.productTitleInTileMode());
    }
}
