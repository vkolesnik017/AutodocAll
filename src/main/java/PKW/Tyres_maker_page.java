package PKW;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Tyres_maker_page {

    SelenideElement linkingBlockByBrands() {
        return $x("//div[@class='top-tyre-brands']");
    }

    SelenideElement brandByName(String titleOfBrand) {
        return $x("//div[@class='top-tyre-brands']//a/img[contains(@alt,'" + titleOfBrand + "')]");
    }

    SelenideElement btnAllBrands() {return $x("//div[@class='type_list_all_brands']/a");}

    SelenideElement relinkByCarBlock() {return $x("//div[@class='top-tyre-makers tyre-page__slider']");}

    SelenideElement modelFromRelinkByCarBlock() {return $x("//div[@class='top-tyre-makers tyre-page__slider']//ul//li//span");}

    SelenideElement btnMoreFromRelinkByCarBlock() {return $x("//div[@class='top-tyre-makers tyre-page__slider']//a[@class='tyre-page__btn__link ga-click']");}

    SelenideElement relinkBlockBySize() {
        return $x("//div[@class='tyre-popular tyre-page__slider']");
    }

    SelenideElement sizeDiameterFromRelinkBlock() {
        return $x("//div[@class='tyre-popular tyre-page__slider']//div[@class='block-2-columns']//li/a");
    }

    SelenideElement wheelDimensionsFromRelinkBlock() {
        return $x("//div[@class='tyre-popular tyre-page__slider']//div[@class='block-3-columns']//li/a");
    }

    SelenideElement btnAllSizeFromRelinkBySizeBlock() {return $x("//div[@class='tyre-popular tyre-page__slider']//div[@class='tyre-page__btn']/a");}

    SelenideElement premiumCarBlock()  {
        return $x("//div[@class='tyre-for-brand tyre-page__slider']");
    }

    SelenideElement carFromPremiumBlock() {
        return $x("//div[@class='tyre-for-brand tyre-page__slider']//div[@class='slick-list draggable']//li/span");
    }

    SelenideElement activeCarFromPremiumBlock() {
        return $x("//div[@class='tyre-for-brand tyre-page__slider']//li[@aria-hidden='false']//span");
    }

    SelenideElement firstDotPaginationInPremiumBlock() {
        return $x("//div[@class='tyre-page__btn-wrap']//ul[@class='slick-dots']/li[1]");
    }

    SelenideElement secondDotPaginationInPremiumBlock() {
        return $x("//div[@class='tyre-page__btn-wrap']//ul[@class='slick-dots']/li[2]");
    }

    SelenideElement btnPrevInPremiumBlock() {
        return $x("//div[contains(@class,'prev slick-arrow')]");
    }

    SelenideElement topTyresBlock() {
        return $x("//div[@class='pkw-more__span_100']");
    }

    SelenideElement productFromTopBlock() {
        return $x("//div[@class='pkw-more__span_100']//li[@class='pkw-related__product']");
    }

    ElementsCollection productsFromTopBlock() {
        return $$x("//div[@class='pkw-more__span_100']//li[@class='pkw-related__product']");
    }

    SelenideElement btnAddToBasketFromTopBlock() {
        return $x("//li[@class='pkw-related__product']//span[@data-ga-action='Add_to_basket']");
    }

    SelenideElement btnDetailsInPopupFromTopBlock() {
        return $x("//div[@class='pkw-related__item-header']//span[@class='ga-click']/span");
    }

    SelenideElement popupBasketAddedProducts() {
        return $x("//div[@class='cart-items-block ']");
    }

    SelenideElement productFromPopupCart() {
        return $x("//div[@class='cart-items-block ']//div[@class='row-text']");
    }

    SelenideElement iconCart() {
        return $x("//div[@id='cart-container']");
    }

    SelenideElement modelProductFromTopBlock() {
        return $x("//li[@class='pkw-related__product']//span[@class='pkw-related__title']");
    }



}