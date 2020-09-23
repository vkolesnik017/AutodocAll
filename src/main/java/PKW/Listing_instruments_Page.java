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

    SelenideElement blockProductsListing() {
        return $x("//div[@class='listing_items']");
    }

    ElementsCollection productsListing() {
        return $$x("//div[@class='listing_items']//div[@class='brand-products']");
    }

    SelenideElement blockBreadcrumbs() {
        return $x("//div[@class='crabs']");
    }

    SelenideElement firstBreadcrumb() {
        return $x("//div[@class='crabs']/div[1]//a");
    }

    SelenideElement secondBreadcrumb() {
        return $x("//div[@class='crabs']/div[2]//a");
    }

    SelenideElement thirdBreadcrumb() {
        return $x("//*[@class='defcur']");
    }

    SelenideElement btnSecondPageInPagination() {
        return $x("//div[@class='pagination']/span[3]");
    }

    SelenideElement btnForReturnOnFirstPageInPagination() {
        return $x("//div[@class='pagination']/span[@class='first']");
    }

    SelenideElement blockPagination() {
        return $x("//div[@class='pagination_wrap']");
    }


    SelenideElement btnPreviousInPagination() {
        return $x("//div[@class='pagination']/span[@class='previous']");
    }

    SelenideElement btnNextInPagination() {
        return $x("//div[@class='pagination']/span[@class='next'][1]");
    }

    SelenideElement btnLastInPagination() {
        return $x("//div[@class='pagination']/span[@class='next'][2]");
    }


}
