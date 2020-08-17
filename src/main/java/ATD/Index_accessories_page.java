package ATD;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;



class Index_accessories_page {

    SelenideElement blockFeatures() {
        return $x("//div[@class='features__wrapp']");
    }

    SelenideElement blockDelivery() {
        return $x("//div[@class='delivery']");
    }

    SelenideElement blockTopBrands() {
        return $x("//div[@class='top-brands']");
    }

    SelenideElement blockTopProducts() {
        return  $x("//div[@class='top-product-block']");
    }

    SelenideElement catalogFirstGroup() {
        return $x("//div[@class='accessories-catalog__row'][1]/div[@class='accessories-catalog__group'][1]");
    }

    ElementsCollection logicalUnionsMainBlock() {
        return $$x("//div[@class='accessories-catalog__row']//div[@class='accessories-catalog__group-name']");
    }

    ElementsCollection mainCategories() {
        return $$x("//div[@class='accessories-catalog__row'][1]//div[@class='accessories-catalog__categories']//li//img");
    }

    SelenideElement secondCategoryInLogicalUnion(){
        return $x("//div[@class='accessories-catalog__categories' and @style='display: block;']//li[2]");
    }

    ElementsCollection categoriesInLogicalUnion(){
        return $$x("//div[@class='accessories-catalog__categories' and @style='display: block;']//li");
    }

    SelenideElement mainTitlePage() {
        return $x("//h1[@class='page-title'][text()]");
    }

    SelenideElement nameFirstCategoryBlockTopAccessories() {
        return $x("//div[@class='accessories-tabs__item active']//div[@class='accessories-tabs__slide-title']");
    }

    SelenideElement textSubtitle() {
        return $x("//div[@class='page-subtitle']");
    }

    SelenideElement blockTopCategories() {
        return $x("//div[@class='accessories-top']");
    }

    ElementsCollection miniCardsInTop6ProductsBlock () {
        return $$x("//div[@class='top-product']//div[@class='product-list__item active']");
    }

    SelenideElement firstBtnAddToBasketInTop6Block() {
        return $x("//div[@class='product-list__item active']//a[@onclick]");
    }

    SelenideElement nameFirstProductInTop6Block() {
        return $x("//div[@class='product-list__item__title']/span[text()]");
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

    SelenideElement btnDetailsInPopupTop6Block() {
        return $x("//div[@class='product-list__item__popup']//span[@class='link ga-click']");
    }

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

    SelenideElement blockMainCatalogCategories() {
        return $x("//div[@class='accessories-catalog']");
    }


}
