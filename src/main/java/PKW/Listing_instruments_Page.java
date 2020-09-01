package PKW;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;


public class Listing_instruments_Page {

    SelenideElement titleCategory() {
        return $x("//h1/span");
    }

    SelenideElement firstBrandInBrandsBlock() {
        return $x("//div[contains(@class,'listing-brand-filters__brand')][1]");
    }

    SelenideElement thirdBrandInBrandsBlock() {
        return $x("//div[contains(@class,'listing-brand-filters__brand')][3]");
    }

    SelenideElement firstBrandInBrandsBlockImg() {
        return $x("//div[@class='listing-brand-filters__list-brands delay-overflow']//div[1]//img");
    }

    SelenideElement thirdBrandInBrandsBlockImg() {
        return $x("//div[@class='listing-brand-filters__list-brands delay-overflow']//div[3]//img");
    }

    SelenideElement brandsBlock() {
        return $x("//div[@class='listing-brand-filters__list-brands delay-overflow']");
    }

    ElementsCollection titleNameProductsFromListing() {
        return $$x("//div[@class='brand-products']//*[contains(@class,'ga-click prod_link')]");
    }

    SelenideElement btnMoreInBrandsBlock() {
        return $x("//div[contains(@class,'listing-brand-filters_more-link')]/a[@class='more_link js-listing-brand-filters']");
    }

    SelenideElement btnLessInBrandsBlock() {
        return $x("//div[contains(@class,'listing-brand-filters_more-link')]/a[@class='more_link js-listing-brand-filters less_link']");
    }

    SelenideElement titleNameProductInListing() {
        return $x("//div[@class='brand-products'][1]//a[@class='ga-click prod_link']");
    }

    SelenideElement idProductInBtnAddBasket() {
        return $x("//div[@class='basket_btn button active_red_button ']");
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

    SelenideElement redBtnAddToBasket() {
        return $x("//div[@class='brand-products']//a[@class='indenwarenkorb']");
    }

    SelenideElement popupBasketAddedProducts() {
        return $x("//div[@class='cart-items-block ']");
    }




}
