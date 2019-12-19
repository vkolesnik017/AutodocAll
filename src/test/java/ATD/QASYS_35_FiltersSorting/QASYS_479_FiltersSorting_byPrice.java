package ATD.QASYS_35_FiltersSorting;


import ATD.DataBase;
import ATD.Listing_page;
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
    private Listing_page listingPage = new Listing_page();
    private DataBase dataBase = new DataBase();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @Test
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC01 TC02 Test checks price filter on listing (Tecdoc, ACC) in list and tile mode")
    public void checkPriceFilterTecdocAndAcc() throws SQLException{
        //Tecdoc listing check
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_car_list"));
        executeJavaScript("arguments[0].value='20';", listingPage.minPriceValue());
        executeJavaScript("arguments[0].value='30';", listingPage.maxPriceValue());
        listingPage.priceFilterSubmitButton().click();
        listingPage.minPriceMapping().shouldHave(text("20"));
        listingPage.maxPriceMapping().shouldHave(text("30"));
        listingPage.checkPricesRange(17.00f, 33.00f, listingPage.priceOfAllProductsOnPageInList());
        listingPage.showListingInTileModeButton().click();
        listingPage.minPriceMapping().shouldHave(text("20"));
        listingPage.maxPriceMapping().shouldHave(text("30"));
        listingPage.checkPricesRange(17.00f, 33.00f, listingPage.priceOfAllProductsOnPageInTile());

        //ACC listing check
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "listing_accessories2"));
        executeJavaScript("arguments[0].value='5';", listingPage.minPriceValue());
        executeJavaScript("arguments[0].value='14';", listingPage.maxPriceValue());
        listingPage.priceFilterSubmitButton().click();
        listingPage.minPriceMapping().shouldHave(text("5"));
        listingPage.maxPriceMapping().shouldHave(text("14"));
        listingPage.checkPricesRange(2.00f, 17.00f, listingPage.priceOfAllProductsOnPageInList());
        listingPage.showListingInTileModeButton().click();
        listingPage.minPriceMapping().shouldHave(text("5"));
        listingPage.maxPriceMapping().shouldHave(text("14"));
        listingPage.checkPricesRange(2.00f, 17.00f, listingPage.priceOfAllProductsOnPageInTile());
    }

    @Test
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC01 TC02 Test checks price filter on listing (LKW) in list and tile mode")
    public void checkPriceFilterLkw() throws SQLException {
        open("https://lkwteile.autodoc.de/" + dataBase.getRouteByRouteName("DE", "lkw_category_car_list"));
        executeJavaScript("arguments[0].value='20';", listingPage.minPriceValue());
        executeJavaScript("arguments[0].value='30';", listingPage.maxPriceValue());
        listingPage.priceFilterSubmitButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.minPriceMapping().shouldHave(text("20"));
        listingPage.maxPriceMapping().shouldHave(text("30"));
        listingPage.checkPricesRange(17.00f, 33.00f, listingPage.priceOfAllProductsOnPageInList());
        listingPage.showListingInTileModeButton().click();
        listingPage.minPriceMapping().shouldHave(text("20"));
        listingPage.maxPriceMapping().shouldHave(text("30"));
        listingPage.checkPricesRange(17.00f, 33.00f, listingPage.priceOfAllProductsOnPageInTile());
    }

    @Test
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC03 TC05 Test checks price filter on listing (Tecdoc, ACC) with pagination")
    public void checkPriceFilterTecdocAndAccPagination() throws SQLException {
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_car_list"));
        executeJavaScript("arguments[0].value='20';", listingPage.minPriceValue());
        executeJavaScript("arguments[0].value='30';", listingPage.maxPriceValue());
        listingPage.priceFilterSubmitButton().click();
        listingPage.minPriceMapping().shouldHave(text("20"));
        listingPage.maxPriceMapping().shouldHave(text("30"));
        listingPage.secondListingPage().click();
        listingPage.minPriceMapping().shouldHave(text("20"));
        listingPage.maxPriceMapping().shouldHave(text("30"));
        listingPage.checkPricesRange(17.00f, 33.00f, listingPage.priceOfAllProductsOnPageInList());

        //ACC listing
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "listing_accessories2"));
        executeJavaScript("arguments[0].value='5';", listingPage.minPriceValue());
        executeJavaScript("arguments[0].value='14';", listingPage.maxPriceValue());
        listingPage.priceFilterSubmitButton().click();
        listingPage.minPriceMapping().shouldHave(text("5"));
        listingPage.maxPriceMapping().shouldHave(text("14"));
        listingPage.secondListingPage().click();
        listingPage.minPriceMapping().shouldHave(text("5"));
        listingPage.maxPriceMapping().shouldHave(text("14"));
        listingPage.checkPricesRange(2.00f, 17.00f, listingPage.priceOfAllProductsOnPageInList());
    }

    @Test
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC04 Test checks price filter on listing (LKW) with pagination")
    public void checkPriceFilterLkwPagination() throws SQLException {
        open("https://lkwteile.autodoc.de/" + dataBase.getRouteByRouteName("DE", "lkw_category_car_list"));
        executeJavaScript("arguments[0].value='10';", listingPage.minPriceValue());
        executeJavaScript("arguments[0].value='30';", listingPage.maxPriceValue());
        listingPage.priceFilterSubmitButton().click();
        listingPage.minPriceMapping().shouldHave(text("10"));
        listingPage.maxPriceMapping().shouldHave(text("30"));
        listingPage.secondListingPage().click();
        listingPage.minPriceMapping().shouldHave(text("10"));
        listingPage.maxPriceMapping().shouldHave(text("30"));
        listingPage.checkPricesRange(7.00f, 33.00f, listingPage.priceOfAllProductsOnPageInTile());
    }

    @Test
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC06 Test checks price filter reset on listing (Tecdoc, ACC)")
    public void checkPriceFilterResetTecdocAndAcc() throws SQLException {
        //Tecdoc listing check
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_car_list"));
        String defaultMinPrice = listingPage.minPriceMapping().getText();
        String defaultMaxPrice = listingPage.maxPriceMapping().getText();
        executeJavaScript("arguments[0].value='20';", listingPage.minPriceValue());
        executeJavaScript("arguments[0].value='30';", listingPage.maxPriceValue());
        listingPage.priceFilterSubmitButton().click();
        listingPage.minPriceMapping().shouldHave(text("20"));
        listingPage.maxPriceMapping().shouldHave(text("30"));
        listingPage.checkPricesRange(17.00f, 33.00f, listingPage.priceOfAllProductsOnPageInList());
        listingPage.resetPriceFilerButton().click();
        listingPage.minPriceMapping().shouldHave(text(defaultMinPrice));
        listingPage.maxPriceMapping().shouldHave(text(defaultMaxPrice));
        float minFloat = Float.parseFloat(defaultMinPrice);
        float maxFloat = Float.parseFloat(defaultMaxPrice);
        listingPage.checkPricesRange(minFloat, maxFloat, listingPage.priceOfAllProductsOnPageInTile());

        //ACC listing check
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "listing_accessories2"));
        String defaultMinPriceAcc = listingPage.minPriceMapping().getText();
        String defaultMaxPriceAcc = listingPage.maxPriceMapping().getText();
        executeJavaScript("arguments[0].value='20';", listingPage.minPriceValue());
        executeJavaScript("arguments[0].value='30';", listingPage.maxPriceValue());
        listingPage.priceFilterSubmitButton().click();
        listingPage.minPriceMapping().shouldHave(text("20"));
        listingPage.maxPriceMapping().shouldHave(text("30"));
        listingPage.checkPricesRange(16.00f, 33.00f, listingPage.priceOfAllProductsOnPageInList());
        listingPage.resetPriceFilerButton().click();
        listingPage.minPriceMapping().shouldHave(text(defaultMinPriceAcc));
        listingPage.maxPriceMapping().shouldHave(text(defaultMaxPriceAcc));
        float minFloatAcc = Float.parseFloat(defaultMinPriceAcc);
        float maxFloatAcc = Float.parseFloat(defaultMaxPriceAcc);
        listingPage.checkPricesRange(minFloatAcc, maxFloatAcc, listingPage.priceOfAllProductsOnPageInTile());
    }

    @Test
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC06 Test checks price filter reset on listing (LKW)")
    public void checkPriceFilterResetLKW() throws SQLException {
        open("https://lkwteile.autodoc.de/" + dataBase.getRouteByRouteName("DE", "lkw_category_car_list"));
        String defaultMinPrice = listingPage.minPriceMapping().getText();
        String defaultMaxPrice = listingPage.maxPriceMapping().getText();
        executeJavaScript("arguments[0].value='20';", listingPage.minPriceValue());
        executeJavaScript("arguments[0].value='30';", listingPage.maxPriceValue());
        listingPage.priceFilterSubmitButton().click();
        listingPage.minPriceMapping().shouldHave(text("20"));
        listingPage.maxPriceMapping().shouldHave(text("30"));
        listingPage.checkPricesRange(17.00f, 33.00f, listingPage.priceOfAllProductsOnPageInList());
        listingPage.resetPriceFilerButton().click();
        listingPage.minPriceMapping().shouldHave(text(defaultMinPrice));
        listingPage.maxPriceMapping().shouldHave(text(defaultMaxPrice));
        float minFloat = Float.parseFloat(defaultMinPrice);
        float maxFloat = Float.parseFloat(defaultMaxPrice);
        listingPage.checkPricesRange(minFloat, maxFloat, listingPage.priceOfAllProductsOnPageInTile());
    }

    @Test
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC07 Test checks price filter on listing (Tecdoc) with few products")
    public void checkPriceFilterTecdocWithFewProducts() throws SQLException {
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_car_list3"));
        executeJavaScript("arguments[0].value='13';", listingPage.minPriceValue());
        executeJavaScript("arguments[0].value='14';", listingPage.maxPriceValue());
        listingPage.priceFilterSubmitButton().click();
        listingPage.minPriceMapping().shouldHave(text("13"));
        listingPage.maxPriceMapping().shouldHave(text("14"));
        listingPage.priceFilterBlock().shouldBe(visible);
    }

    @Test
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC08 Test checks price filter on listing (Tecdoc) with filter by side")
    public void checkPriceFilterTecdocWithFilerBySide() throws SQLException {
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_car_list"));
        executeJavaScript("arguments[0].value='69';", listingPage.minPriceValue());
        executeJavaScript("arguments[0].value='243';", listingPage.maxPriceValue());
        listingPage.priceFilterSubmitButton().click();
        listingPage.minPriceMapping().shouldHave(text("69"));
        listingPage.maxPriceMapping().shouldHave(text("243"));
        listingPage.filterBySideBack().click();
        listingPage.checkFilterBySide("Hinterachse");
        listingPage.checkPricesRange(66f, 246f, listingPage.priceOfAllProductsOnPageInList());
    }

    @Test
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC09 Test checks price filter on listing (LKW) with filter by side")
    public void checkPriceFilterTecdocWithFilerBySideLKW() throws SQLException {
        open("https://lkwteile.autodoc.de/" + dataBase.getRouteByRouteName("DE", "lkw_category_car_list"));
        executeJavaScript("arguments[0].value='15';", listingPage.minPriceValue());
        executeJavaScript("arguments[0].value='113';", listingPage.maxPriceValue());
        listingPage.priceFilterSubmitButton().click();
        listingPage.minPriceMapping().shouldHave(text("15"));
        listingPage.maxPriceMapping().shouldHave(text("113"));
        listingPage.filterBySideLKW().click();
        listingPage.checkFilterBySide("vorne");
        listingPage.checkPricesRange(12f, 116f, listingPage.priceOfAllProductsOnPageInList());
    }

    @Test
    @Flaky
    @Owner(value = "oleg")
    @Description(value = "TC10 TC11 Test checks price filter on listing (Tecdoc, ACC) with filter by characteristic/brand")
    public void checkPriceFilterTecdocWithFilerByBrand() throws SQLException {
        //Tecdoc listing price + characteristic
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_car_list"));
        executeJavaScript("arguments[0].value='18';", listingPage.minPriceValue());
        executeJavaScript("arguments[0].value='283';", listingPage.maxPriceValue());
        listingPage.priceFilterSubmitButton().click();
        listingPage.minPriceMapping().shouldHave(text("18"));
        listingPage.maxPriceMapping().shouldHave(text("283"));
        listingPage.gelochtAttribute().click();
        listingPage.checkFilterBySide("Gelocht");
        listingPage.checkPricesRange(15f, 286f, listingPage.priceOfAllProductsOnPageInList());

        //ACC listing price + brand filter
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "listing_accessories2"));
        String brandName = listingPage.firstBrandNameInFiler().attr("alt");
        listingPage.firstBrandInFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        executeJavaScript("arguments[0].value='15';", listingPage.minPriceValue());
        executeJavaScript("arguments[0].value='25';", listingPage.maxPriceValue());
        listingPage.priceFilterSubmitButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkPricesRange(12f, 28f, listingPage.priceOfAllProductsOnPageInList());
        listingPage.checkProductTitleOnListing(brandName, true, listingPage.productTitleInListMode());
    }
}
