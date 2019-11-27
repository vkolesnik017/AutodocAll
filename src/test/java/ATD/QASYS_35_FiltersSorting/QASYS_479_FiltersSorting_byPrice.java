package ATD.QASYS_35_FiltersSorting;


import ATD.DataBase;
import ATD.ListingTecdoc_page;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;

public class QASYS_479_FiltersSorting_byPrice {
    private ListingTecdoc_page listingTecdocPage = new ListingTecdoc_page();
    private DataBase dataBase = new DataBase();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @Test
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "Test checks price filter on listing (Tecdoc, ACC) in list and tile mode")
    public void checkPriceFilterTecdocAndAcc() throws SQLException{
        //Tecdoc listing check
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_car_list"));
        executeJavaScript("arguments[0].value='20';", listingTecdocPage.minPriceValue());
        executeJavaScript("arguments[0].value='30';", listingTecdocPage.maxPriceValue());
        listingTecdocPage.priceFilterSubmitButton().click();
        listingTecdocPage.minPriceMapping().shouldHave(text("20"));
        listingTecdocPage.maxPriceMapping().shouldHave(text("30"));
        listingTecdocPage.checkPricesRange(17.00f, 33.00f, listingTecdocPage.priceOfAllProductsOnPageInList());
        listingTecdocPage.showListingInTileModeButton().click();
        listingTecdocPage.minPriceMapping().shouldHave(text("20"));
        listingTecdocPage.maxPriceMapping().shouldHave(text("30"));
        listingTecdocPage.checkPricesRange(17.00f, 33.00f, listingTecdocPage.priceOfAllProductsOnPageInTile());

        //ACC listing check
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "listing_accessories2"));
        executeJavaScript("arguments[0].value='5';", listingTecdocPage.minPriceValue());
        executeJavaScript("arguments[0].value='14';", listingTecdocPage.maxPriceValue());
        listingTecdocPage.priceFilterSubmitButton().click();
        listingTecdocPage.minPriceMapping().shouldHave(text("5"));
        listingTecdocPage.maxPriceMapping().shouldHave(text("14"));
        listingTecdocPage.checkPricesRange(2.00f, 17.00f, listingTecdocPage.priceOfAllProductsOnPageInList());
        listingTecdocPage.showListingInTileModeButton().click();
        listingTecdocPage.minPriceMapping().shouldHave(text("5"));
        listingTecdocPage.maxPriceMapping().shouldHave(text("14"));
        listingTecdocPage.checkPricesRange(2.00f, 17.00f, listingTecdocPage.priceOfAllProductsOnPageInTile());
    }

    @Test
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "Test checks price filter on listing (LKW) in list and tile mode")
    public void checkPriceFilterLkw() throws SQLException {
        open("https://lkwteile.autodoc.de/" + dataBase.getRouteByRouteName("DE", "lkw_category_car_list"));
        executeJavaScript("arguments[0].value='20';", listingTecdocPage.minPriceValue());
        executeJavaScript("arguments[0].value='30';", listingTecdocPage.maxPriceValue());
        listingTecdocPage.priceFilterSubmitButton().click();
        listingTecdocPage.minPriceMapping().shouldHave(text("20"));
        listingTecdocPage.maxPriceMapping().shouldHave(text("30"));
        listingTecdocPage.checkPricesRange(17.00f, 33.00f, listingTecdocPage.priceOfAllProductsOnPageInList());
        listingTecdocPage.showListingInTileModeButton().click();
        listingTecdocPage.minPriceMapping().shouldHave(text("20"));
        listingTecdocPage.maxPriceMapping().shouldHave(text("30"));
        listingTecdocPage.checkPricesRange(17.00f, 33.00f, listingTecdocPage.priceOfAllProductsOnPageInTile());
    }

    @Test
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "Test checks price filter on listing (Tecdoc, ACC) with pagination")
    public void checkPriceFilterTecdocAndAccPagination() throws SQLException {
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_car_list"));
        executeJavaScript("arguments[0].value='20';", listingTecdocPage.minPriceValue());
        executeJavaScript("arguments[0].value='30';", listingTecdocPage.maxPriceValue());
        listingTecdocPage.priceFilterSubmitButton().click();
        listingTecdocPage.minPriceMapping().shouldHave(text("20"));
        listingTecdocPage.maxPriceMapping().shouldHave(text("30"));
        listingTecdocPage.secondListingPage().click();
        listingTecdocPage.minPriceMapping().shouldHave(text("20"));
        listingTecdocPage.maxPriceMapping().shouldHave(text("30"));
        listingTecdocPage.checkPricesRange(17.00f, 33.00f, listingTecdocPage.priceOfAllProductsOnPageInList());

        //ACC listing
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "listing_accessories2"));
        executeJavaScript("arguments[0].value='5';", listingTecdocPage.minPriceValue());
        executeJavaScript("arguments[0].value='14';", listingTecdocPage.maxPriceValue());
        listingTecdocPage.priceFilterSubmitButton().click();
        listingTecdocPage.minPriceMapping().shouldHave(text("5"));
        listingTecdocPage.maxPriceMapping().shouldHave(text("14"));
        listingTecdocPage.secondListingPage().click();
        listingTecdocPage.minPriceMapping().shouldHave(text("5"));
        listingTecdocPage.maxPriceMapping().shouldHave(text("14"));
        listingTecdocPage.checkPricesRange(2.00f, 17.00f, listingTecdocPage.priceOfAllProductsOnPageInList());
    }

    @Test
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "Test checks price filter on listing (LKW) with pagination")
    public void checkPriceFilterLkwPagination() throws SQLException {
        open("https://lkwteile.autodoc.de/" + dataBase.getRouteByRouteName("DE", "lkw_category_car_list"));
        executeJavaScript("arguments[0].value='10';", listingTecdocPage.minPriceValue());
        executeJavaScript("arguments[0].value='30';", listingTecdocPage.maxPriceValue());
        listingTecdocPage.priceFilterSubmitButton().click();
        listingTecdocPage.minPriceMapping().shouldHave(text("10"));
        listingTecdocPage.maxPriceMapping().shouldHave(text("30"));
        listingTecdocPage.secondListingPage().click();
        listingTecdocPage.minPriceMapping().shouldHave(text("10"));
        listingTecdocPage.maxPriceMapping().shouldHave(text("30"));
        listingTecdocPage.checkPricesRange(7.00f, 33.00f, listingTecdocPage.priceOfAllProductsOnPageInTile());
    }

    @Test
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "Test checks price filter reset on listing (Tecdoc, ACC)")
    public void checkPriceFilterResetTecdocAndAcc() throws SQLException {
        //Tecdoc listing check
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_car_list"));
        String defaultMinPrice = listingTecdocPage.minPriceMapping().getText();
        String defaultMaxPrice = listingTecdocPage.maxPriceMapping().getText();
        executeJavaScript("arguments[0].value='20';", listingTecdocPage.minPriceValue());
        executeJavaScript("arguments[0].value='30';", listingTecdocPage.maxPriceValue());
        listingTecdocPage.priceFilterSubmitButton().click();
        listingTecdocPage.minPriceMapping().shouldHave(text("20"));
        listingTecdocPage.maxPriceMapping().shouldHave(text("30"));
        listingTecdocPage.checkPricesRange(17.00f, 33.00f, listingTecdocPage.priceOfAllProductsOnPageInList());
        listingTecdocPage.resetPriceFilerButton().click();
        listingTecdocPage.minPriceMapping().shouldHave(text(defaultMinPrice));
        listingTecdocPage.maxPriceMapping().shouldHave(text(defaultMaxPrice));
        float minFloat = Float.parseFloat(defaultMinPrice);
        float maxFloat = Float.parseFloat(defaultMaxPrice);
        listingTecdocPage.checkPricesRange(minFloat, maxFloat, listingTecdocPage.priceOfAllProductsOnPageInTile());

        //ACC listing check
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "listing_accessories2"));
        String defaultMinPriceAcc = listingTecdocPage.minPriceMapping().getText();
        String defaultMaxPriceAcc = listingTecdocPage.maxPriceMapping().getText();
        executeJavaScript("arguments[0].value='20';", listingTecdocPage.minPriceValue());
        executeJavaScript("arguments[0].value='30';", listingTecdocPage.maxPriceValue());
        listingTecdocPage.priceFilterSubmitButton().click();
        listingTecdocPage.minPriceMapping().shouldHave(text("20"));
        listingTecdocPage.maxPriceMapping().shouldHave(text("30"));
        listingTecdocPage.checkPricesRange(16.00f, 33.00f, listingTecdocPage.priceOfAllProductsOnPageInList());
        listingTecdocPage.resetPriceFilerButton().click();
        listingTecdocPage.minPriceMapping().shouldHave(text(defaultMinPriceAcc));
        listingTecdocPage.maxPriceMapping().shouldHave(text(defaultMaxPriceAcc));
        float minFloatAcc = Float.parseFloat(defaultMinPriceAcc);
        float maxFloatAcc = Float.parseFloat(defaultMaxPriceAcc);
        listingTecdocPage.checkPricesRange(minFloatAcc, maxFloatAcc, listingTecdocPage.priceOfAllProductsOnPageInTile());
    }

    @Test
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "Test checks price filter reset on listing (LKW)")
    public void checkPriceFilterResetLKW() throws SQLException {
        open("https://lkwteile.autodoc.de/" + dataBase.getRouteByRouteName("DE", "lkw_category_car_list"));
        String defaultMinPrice = listingTecdocPage.minPriceMapping().getText();
        String defaultMaxPrice = listingTecdocPage.maxPriceMapping().getText();
        executeJavaScript("arguments[0].value='20';", listingTecdocPage.minPriceValue());
        executeJavaScript("arguments[0].value='30';", listingTecdocPage.maxPriceValue());
        listingTecdocPage.priceFilterSubmitButton().click();
        listingTecdocPage.minPriceMapping().shouldHave(text("20"));
        listingTecdocPage.maxPriceMapping().shouldHave(text("30"));
        listingTecdocPage.checkPricesRange(17.00f, 33.00f, listingTecdocPage.priceOfAllProductsOnPageInList());
        listingTecdocPage.resetPriceFilerButton().click();
        listingTecdocPage.minPriceMapping().shouldHave(text(defaultMinPrice));
        listingTecdocPage.maxPriceMapping().shouldHave(text(defaultMaxPrice));
        float minFloat = Float.parseFloat(defaultMinPrice);
        float maxFloat = Float.parseFloat(defaultMaxPrice);
        listingTecdocPage.checkPricesRange(minFloat, maxFloat, listingTecdocPage.priceOfAllProductsOnPageInTile());
    }

    @Test
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "Test checks price filter on listing (Tecdoc) with few products")
    public void checkPriceFilterTecdocWithFewProducts() throws SQLException {
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_car_list3"));
        executeJavaScript("arguments[0].value='13';", listingTecdocPage.minPriceValue());
        executeJavaScript("arguments[0].value='14';", listingTecdocPage.maxPriceValue());
        listingTecdocPage.priceFilterSubmitButton().click();
        listingTecdocPage.minPriceMapping().shouldHave(text("13"));
        listingTecdocPage.maxPriceMapping().shouldHave(text("14"));
        listingTecdocPage.priceFilterBlock().shouldBe(visible);
    }

    @Test
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "Test checks price filter on listing (Tecdoc) with filter by side")
    public void checkPriceFilterTecdocWithFilerBySide() throws SQLException {
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_car_list"));
        executeJavaScript("arguments[0].value='69';", listingTecdocPage.minPriceValue());
        executeJavaScript("arguments[0].value='243';", listingTecdocPage.maxPriceValue());
        listingTecdocPage.priceFilterSubmitButton().click();
        listingTecdocPage.minPriceMapping().shouldHave(text("69"));
        listingTecdocPage.maxPriceMapping().shouldHave(text("243"));
        listingTecdocPage.filterBySide().click();
        listingTecdocPage.checkFilterBySide("Hinterachse");
        listingTecdocPage.checkPricesRange(66f, 246f, listingTecdocPage.priceOfAllProductsOnPageInList());
    }

    @Test
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "Test checks price filter on listing (LKW) with filter by side")
    public void checkPriceFilterTecdocWithFilerBySideLKW() throws SQLException {
        open("https://lkwteile.autodoc.de/" + dataBase.getRouteByRouteName("DE", "lkw_category_car_list"));
        executeJavaScript("arguments[0].value='15';", listingTecdocPage.minPriceValue());
        executeJavaScript("arguments[0].value='113';", listingTecdocPage.maxPriceValue());
        listingTecdocPage.priceFilterSubmitButton().click();
        listingTecdocPage.minPriceMapping().shouldHave(text("15"));
        listingTecdocPage.maxPriceMapping().shouldHave(text("113"));
        listingTecdocPage.filterBySideLKW().click();
        listingTecdocPage.checkFilterBySide("vorne");
        listingTecdocPage.checkPricesRange(12f, 116f, listingTecdocPage.priceOfAllProductsOnPageInList());
    }

    @Test
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "Test checks price filter on listing (Tecdoc, ACC) with filter by characteristic/brand")
    public void checkPriceFilterTecdocWithFilerByBrand() throws SQLException {
        //Tecdoc listing price + characteristic
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_car_list"));
        executeJavaScript("arguments[0].value='18';", listingTecdocPage.minPriceValue());
        executeJavaScript("arguments[0].value='283';", listingTecdocPage.maxPriceValue());
        listingTecdocPage.priceFilterSubmitButton().click();
        listingTecdocPage.minPriceMapping().shouldHave(text("18"));
        listingTecdocPage.maxPriceMapping().shouldHave(text("283"));
        listingTecdocPage.gelochtAttribute().click();
        listingTecdocPage.checkFilterBySide("Gelocht");
        listingTecdocPage.checkPricesRange(15f, 286f, listingTecdocPage.priceOfAllProductsOnPageInList());

        //ACC listing price + brand filter
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "listing_accessories2"));
        String brandName = listingTecdocPage.filterByBrand().attr("alt");
        listingTecdocPage.firstBrandInFilterButton().click();
        listingTecdocPage.preloader().shouldBe(attribute("style", "display: none;"));
        executeJavaScript("arguments[0].value='15';", listingTecdocPage.minPriceValue());
        executeJavaScript("arguments[0].value='25';", listingTecdocPage.maxPriceValue());
        listingTecdocPage.priceFilterSubmitButton().click();
        listingTecdocPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingTecdocPage.checkPricesRange(12f, 28f, listingTecdocPage.priceOfAllProductsOnPageInList());
        listingTecdocPage.checkProductTitleOnListing(brandName, true, listingTecdocPage.productTitleInListMode());
    }
}
