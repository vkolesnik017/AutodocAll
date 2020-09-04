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

    SelenideElement btnPreviousInPagination() {
        return $x("//div[@class='pagination']/span[@class='previous']");
    }

    SelenideElement btnNextInPagination() {
        return $x("//div[@class='pagination']/span[@class='next'][1]");
    }

    SelenideElement btnLastInPagination() {
        return $x("//div[@class='pagination']/span[@class='next'][2]");
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





}
