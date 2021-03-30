package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;


class Index_instruments_page {


//Locators for Title block

    SelenideElement titleMainPage() {
        return $x("//h1[@class='special-catalog__title']");
    }

    SelenideElement subTitleMainPage() {
        return $x("//div[@class='special-catalog__subtitle']");
    }


//Locators for top-product-block best-product__income block

    SelenideElement productInBlockTop10() {
        return $x("//div[@class='rec_products_wrap'][1]//div[@class='rec_products_block js-products-item '][1]//span[@class='link ga-click']");
    }

    SelenideElement titleTop10ProductsBlock() {
        return $x("//div[@class='top-product-block best-product__income']/h2[text()]");
    }

    SelenideElement subtitleBlockTop10() {
        return $x("//div[@class='top-product__text']");
    }

    ElementsCollection miniCardsInTop10ProductsBlock() {
        return $$x("//div[@class='top-product-block best-product__income']//div[@class='rec_products_block js-products-item ']");
    }

    SelenideElement firstBtnAddToBasketInTop10ProductsBlock() {
        return $x("//div[@class='rec_products_wrap'][1]//div[@class='rec_products_block js-products-item '][1]//div[@class='rec_prod_btn button ']");
    }

    SelenideElement btnDetailsInPopupGoodsTop10ProductsBlock() {
        return $x("//div[@class='rec_prod_info_popup']//span[@class='details link']");
    }


//Locators for top-product block

    SelenideElement titleTop6ProductsBlock() {
        return $x("//div[@class='top-product-block']//h2[text()]");
    }

    ElementsCollection miniCardsInTopProductsBlock() {
        return $$x("//div[@class='top-product']//div[@class='product-list__item active']");
    }

    SelenideElement nameFirstProductInTop6ProductsBlock() {
        return $x("//div[@class='product-list__item active']//div[@class='product-list__item__title']/span[text()]");
    }

    SelenideElement firstBtnAddToBasketInTop6ProductsBlock() {
        return $x("//div[@class='product-list__item active']//div[@class='price_box product-list__item__button']");
    }

    SelenideElement btnDetailsInPopupGoodsTop6Block() {
        return $x("//div[@class='product-list__item active']//div[@class='item-table-box__details']");
    }


//Locators for top-brands block

    SelenideElement blockTopBrands() {
        return $x("//div[@class='top-brands']");
    }


// Locators for features block

    SelenideElement blockFeatures() {
        return $x("//div[@class='features__wrapp']");
    }


//Locators for delivery block

    SelenideElement blockDelivery() {
        return $x("//div[@class='delivery']//ul[@class='delivery__wrapp']");
    }


//Locators for special-catalog block

    SelenideElement firstGroupLogicalUnion() {
        return $x("//div[@class='special-catalog__row'][1]//div[@class='special-catalog__group'][1]");
    }

    SelenideElement firstCategoryInLogicalUnion() {
        return $x("//div[@class='special-catalog__categories' and @style='display: block;']//li[1]//span");
    }

    SelenideElement firstSeparateCategoryInMainCatalog() {
        return $x("//div[@class='special-catalog']//div[@class='special-catalog__row'][2]/a[1]");
    }

    public ElementsCollection categoriesFromLogicalUnion() {
        return $$x("//div[@id='content']//div[@class='special-catalog__wrapp']//ul/li/a");
    }

    ElementsCollection logicalUnions() {
        return $$x("//div[@id='content']//div[@class='special-catalog__group']");
    }

    public  ElementsCollection nameSeparateCategories() {
        return $$x("//div[@id='content']//a[@class='special-catalog__group ga-click']");
    }


//Locators for advantages block

    SelenideElement blockAdvantages() {
        return $x("//div[@class='advantages']");
    }

    SelenideElement firstTabAdvantages() {
        return $x("//div[@class='advantages']//i[@class='advantages-warranty']");
    }

    SelenideElement secondTabAdvantage() {
        return $x("//div[@class='advantages']//i[@class='advantages-back']");
    }

    SelenideElement thirdTabAdvantage() {
        return $x("//div[@class='advantages']//i[@class='advantages-circle']");
    }

    SelenideElement popupSecondTabAdvantage() {
        return $x("//div[@class='advantages__item'][2]//div[@class='advantages__item-popup']");
    }

    SelenideElement popupThirdTabAdvantage() {
        return $x("//div[@class='advantages__item'][3]//div[@class='advantages__item-popup']");
    }

    SelenideElement seoText() {return $(".features");}

}
