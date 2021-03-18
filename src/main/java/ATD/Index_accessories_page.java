package ATD;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;


class Index_accessories_page {


// Locators for Title block

    SelenideElement mainTitlePage() {
        return $x("//h1[@class='page-title'][text()]");
    }

    SelenideElement textSubtitle() {
        return $x("//div[@class='page-subtitle']");
    }


// Locators for accessories-top block

    SelenideElement nameFirstCategoryBlockTopAccessories() {
        return $x("//div[@class='accessories-tabs__item active']//div[@class='accessories-tabs__slide-title']");
    }

    SelenideElement blockTopCategories() {
        return $x("//div[@class='accessories-top']");
    }

    SelenideElement firstTabInBlockTopAccessories() {
        return $x("//div[@class='accessories-top']//li[1]");
    }

    SelenideElement secondTabInBlockTopAccessories() {
        return $x("//div[@class='accessories-top']//li[2]");
    }

    SelenideElement thirdTabInBlockTopAccessories() {
        return $x("//div[@class='accessories-top']//li[3]");
    }

    ElementsCollection categoriesInTabBlockTopAccessories() {
        return $$x("//div[@class='accessories-tabs__item active']//span[@data-gac='TOP_catalog_accessories']");
    }


// Locators for accessories-catalog block

    SelenideElement blockMainCatalogCategories() {
        return $x("//div[@class='accessories-catalog']");
    }


    SelenideElement catalogFirstGroup() {
        return $x("//div[@class='accessories-catalog__row'][1]//div[@ class='accessories-catalog__group-img'][1]");
    }

    ElementsCollection logicalUnionsMainBlock() {
        return $$x("//div[@class='accessories-catalog__row']//a[@data-cat-id and @class='accessories-catalog__group js-accessories-catalog-group']");
    }

    ElementsCollection mainCategories() {
        return $$x("//div[@class='accessories-catalog__row'][1]//div[@class='accessories-catalog__categories']//li/a");
    }

    SelenideElement secondCategoryInLogicalUnion() {
        return $x("//div[@class='accessories-catalog__categories' and @style='display: block;']//li[2]");
    }

    ElementsCollection categoriesInLogicalUnion() {
        return $$x("//div[@class='accessories-catalog__categories' and @style='display: block;']//li");
    }


// Locators for features block

    SelenideElement blockFeatures() {
        return $x("//div[@class='features__wrapp']");
    }


// Locators for delivery block

    SelenideElement blockDelivery() {
        return $x("//div[@class='delivery']");
    }


// Locators for top-brands block

    SelenideElement blockTopBrands() {
        return $x("//div[@class='top-brands']");
    }


// Locators for top-product block

    SelenideElement blockTopProducts() {
        return $x("//div[@class='top-product-block']");
    }

    ElementsCollection miniCardsInTop6ProductsBlock() {
        return $$x("//div[@class='top-product']//div[@class='product-list__item active']");
    }

    SelenideElement titleTop6ProductsBlock() {
        return $x("//div[@class='top-product-block']//h3[text()]");
    }

    SelenideElement firstBtnAddToBasketInTop6Block() {
        return $x("//div[@class='price_box product-list__item__button']");
    }

    SelenideElement nameFirstProductInTop6Block() {
        return $x("//div[@class='product-list__item__title']/span[text()]");
    }

    SelenideElement btnDetailsInPopupTop6Block() {
        return $x("//div[@class='product-list__item__popup']//span[@class='link pointer ga-click']");
    }


// Locators for advantages block

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


// Locators for


}
