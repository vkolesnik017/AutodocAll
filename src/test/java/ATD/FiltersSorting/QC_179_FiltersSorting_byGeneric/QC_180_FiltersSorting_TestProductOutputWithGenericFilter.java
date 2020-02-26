package ATD.FiltersSorting.QC_179_FiltersSorting_byGeneric;


import ATD.DataBase;
import ATD.Listing_page;
import ATD.Main_page;
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
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.refresh;

public class QC_180_FiltersSorting_TestProductOutputWithGenericFilter {
    private Listing_page listingPage = new Listing_page();
    private Main_page mainPage = new Main_page();
    private DataBase dataBase = new DataBase();

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
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list3,lkw_category_car_list4,lkw_search2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks generic position in generic block on listing (Tecdoc, ACC)")
    public void checkGenericPosition(String route) {
        openPage(route);
        String genericName = listingPage.secondGeneric().text();
        listingPage.secondGeneric().click();
        listingPage.firstGeneric().shouldHave(text(genericName));
        listingPage.checkProductTitleOnListing(genericName, true, listingPage.productTitleInListMode());
        refresh();
        listingPage.firstGeneric().shouldHave(text(genericName));
        listingPage.checkProductTitleOnListing(genericName, true, listingPage.productTitleInListMode());
        listingPage.secondGeneric().click();
        listingPage.secondGeneric().click();
        listingPage.checkProductTitleOnListing(genericName, true, listingPage.productTitleInListMode());
        listingPage.showListingInTileModeButton().click();
        listingPage.checkProductTitleOnListing(genericName, true, listingPage.productTitleInTileMode());
        listingPage.firstGeneric().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkUniqueGenericsOnListing(2, listingPage.productTitleInTileMode());
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks generic position in generic block on listing LKW")
    public void checkGenericPositionLKW(String route) {
        openPage(route);
        mainPage.closeCarSelectorTooltipIfVisible();
        String genericName = listingPage.fourthGeneric().text();
        listingPage.fourthGeneric().click();
        listingPage.firstGeneric().shouldHave(text(genericName));
        listingPage.checkProductTitleOnListing(genericName, true, listingPage.productTitleInListMode());
        refresh();
        listingPage.firstGeneric().shouldHave(text(genericName));
        listingPage.checkProductTitleOnListing(genericName, true, listingPage.productTitleInListMode());
        listingPage.secondGeneric().click();
        listingPage.fourthGeneric().click();
        listingPage.checkProductTitleOnListing(genericName, true, listingPage.productTitleInListMode());
        listingPage.showListingInTileModeButton().click();
        listingPage.checkProductTitleOnListing(genericName, true, listingPage.productTitleInTileMode());
        listingPage.firstGeneric().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkUniqueGenericsOnListing(2, listingPage.productTitleInTileMode());
    }

    @AfterMethod
    public void tearDown() {
        close();
    }
}
