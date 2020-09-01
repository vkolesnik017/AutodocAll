package PKW;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;


public class Listing_accessories_page {

    SelenideElement mainTitleListingPage() {
        return $x("//h1/span");
    }

    SelenideElement counterValueInQuantityCounter() {
        return $x("//div[@class='brand-products'][1]//input[@class='ammount']");
    }

    SelenideElement btnPlusInQuantityCounter() {
        return $x("//div[@class='brand-products'][1]//a[@class='ga-click plus']");
    }

    SelenideElement btnMinusInQuantityCounter() {
        return $x("//div[@class='brand-products'][1]//a[@class='ga-click minus']");
    }

    SelenideElement titleNameProductInListing() {
        return $x("//div[@class='brand-products'][1]//a[@class='ga-click prod_link']");
    }

    SelenideElement blockProductsListing() {
        return $x("//div[@class='listing_items']");
    }

    ElementsCollection productsListing() {
        return $$x("//div[@class='listing_items']//div[@class='brand-products']");
    }

    SelenideElement idProductInBtnAddBasket() {
        return $x("//div[@class='basket_btn button active_red_button ']");
    }

    SelenideElement redBtnAddToBasket() {
        return $x("//div[@class='brand-products']//a[@class='indenwarenkorb']");
    }

    SelenideElement popupBasketAddedProducts() {
        return $x("//div[@class='cart-items-block ']");
    }

    SelenideElement blockBreadCrumbs() {
        return $x("//div[@class='crabs']");
    }

    SelenideElement firstBreadCrumb() {
        return $x("//div[@class='crabs']//div[1]/a");
    }

    SelenideElement secondBreadCrumb() {
        return $x("//div[@class='crabs']//div[2]/a");
    }

    SelenideElement thirdBreadCrumb() {
        return $x("//div[@class='crabs']//div[3]/a");
    }





}
