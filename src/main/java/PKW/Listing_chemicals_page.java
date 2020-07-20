package PKW;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;


public class Listing_chemicals_page {


    SelenideElement titleNameListingPage() {
        return $x("//h1/span");
    }

    SelenideElement btnPlusInQuantityCounter() {
        return $x("//div[@class='brand-products'][1]//a[@class='ga-click plus']");
    }

    SelenideElement btnMinusInQuantityCounter() {
        return $x("//div[@class='brand-products'][1]//a[@class='ga-click minus']");
    }

    SelenideElement counterValueInQuantityCounter() {
        return $x("//div[@class='brand-products'][1]//input[@class='ammount']");
    }

    SelenideElement blockBreadCrumbs() {return $x("//div[@class='crabs']"); }

    SelenideElement firstBreadCrumb() {
        return $x("//div[@class='crabs']//div[@style='float:left;'][1]");
    }

    SelenideElement secondBreadCrumb() {
        return $x("//div[@class='crabs']//div[@style='float:left;'][2]");
    }

    SelenideElement thirdBreadCrumb() {
        return $x("//div[@class='crabs']//a[@class='defcur']");
    }

    SelenideElement blockPagination() {
        return $x("//div[@class='pagination_wrap']");
    }

    SelenideElement btnForReturnOnFirstPageInPagination() {
        return $x("//div[@class='pagination']/span[@class='first']");
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

    SelenideElement btnFourPageInPagination() {
    return $x("//div[@class='pagination']/span[6]");
    }

    SelenideElement brandsBlock() {
        return $x("//div[@class='listing-brand-filters__list-brands']");
    }

    SelenideElement firstBrandInBrandsBlock() {
        return $x("//div[contains(@class,'listing-brand-filters__brand')][1]");
    }

    SelenideElement thirdBrandInBrandsBlock() {
        return $x("//div[contains(@class,'listing-brand-filters__brand')][3]");
    }

    SelenideElement firstBrandInBrandsBlockImg() {
        return $x("//div[@class='listing-brand-filters__list-brands']//div[1]//img");
    }


    SelenideElement thirdBrandInBrandsBlockImg() {
        return $x("//div[@class='listing-brand-filters__list-brands']//div[3]//img");
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

    SelenideElement blockProductsListing() {
        return $x("//div[@class='listing_items']");
    }

    ElementsCollection productsListing() {
        return $$x("//div[@class='listing_items']//div[@class='brand-products']");
    }

    SelenideElement titleNameFirstProductInListing() {
        return $x("//div[@class='brand-products'][1]//a[@class='ga-click prod_link']");
    }

    SelenideElement redBtnAddToBasket() {
        return $x("//div[@class='brand-products'][1]//a[@class='indenwarenkorb']");
    }

    SelenideElement idProductInBtnAddBasket() {
        return $x("//div[@class='basket_btn button active_red_button ']");
    }

    SelenideElement blockFeatures() {
        return $x("//div[@class='sb_content features_wrap']");
    }

    SelenideElement firstFeatures() {
        return $x("//div[@class='sb_content features_wrap']//li[@class='preise']");
    }

    SelenideElement popupFirstFeatures() {
        return $x("//li[@class='preise']//span[@class='pophover-text']");
    }

    SelenideElement genericsBlock() {
        return $x("//div[@class='filter-accessories js-filter-generic js-filter-wrapper hidden']");
    }

    SelenideElement firstGenericInGenericsBlock() {
        return $x("//label[contains(@class,'filter-accessories__items-item ')][2]");
    }

    SelenideElement secondGenericInGenericsBlock() {
        return $x("//label[contains(@class,'filter-accessories__items-item ')][3]");
    }

    SelenideElement blockPaymentMethod() {
        return $x("//div[@class='sb_content payment_method']");
    }


}
