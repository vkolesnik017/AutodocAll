package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Index_chemicals_page {


    SelenideElement catalogFirstGroup() {
        return $x("//div[@class='special-catalog__row'][1]//div[@class='special-catalog__group'][1]");
    }

    SelenideElement catalogCategories() {
        return $x("//div[@class='special-catalog__categories' and @style='display: block;']");
    }

    SelenideElement nameFirstCategoryInLogicalUnion() {
        return $x("//div[@class='special-catalog__categories' and @style='display: block;']//span[text()]");
    }

    SelenideElement secondCategoryInLogicalUnion() {
        return $x("//div[@class='special-catalog__categories' and @style='display: block;']//li[2]/a");
    }

    ElementsCollection categoriesInLogicalUnion() {
        return $$x("//div[@class='special-catalog__categories' and @style='display: block;']//li/a");
    }

    SelenideElement blockTopBrands() {
        return $x("//div[@class='top-brands']/ul/img");
    }

    SelenideElement blockFeatures() {
        return $x("//div[@class='features__wrapp']");
    }


    SelenideElement blockTopProducts() {
        return $x("//div[@class='top-product-block']");
    }

    ElementsCollection productsInTopBlock() {
        return $$x("//div[@class='product-list__item active']");
    }

    SelenideElement blockAdvantages() {
        return $x("//div[@class='advantages']");
    }

    SelenideElement secondAdvantageInBlockAdvantages() {
        return $x("//div[@class='advantages']//div[@class='advantages__item'][2]");
    }

    SelenideElement blockAdvantagesPopup(){
        return $x("//div[@class='advantages']//div[@class='advantages__item-popup']");
    }

    SelenideElement nameFirstProductInBlockTopProducts() {
        return $x("//div[@class='top-product-block']//div[@class='product-list__item__title']");
    }

    SelenideElement detailsBtnInPopupInTopProductBlock() {
        return $x("//div[@class='top-product-block']//div[@class='item-table-box__details']");
    }

    SelenideElement subTitleNameMainPage() {
        return $x("//div[@class='special-catalog__subtitle']");
    }

    SelenideElement titleNameMainPage() {
        return $x("//h1[@class='special-catalog__title']");
    }

    SelenideElement blockDelivery() {
        return $x("//div[@class='delivery']//ul[@class='delivery__wrapp']");
    }

    SelenideElement firstBtnAddToBasketInTopProductsBlock() {
        return $x("//div[@class='product-list__item active']//div[@class='price_box product-list__item__button']");
    }

    SelenideElement firstSeparateCategoryInMainCatalog() {
        return $x("//div[@class='special-catalog']//div[@class='special-catalog__row'][2]/a[1]");
    }

    ElementsCollection separateCategoriesInMainCatalog() {
        return $$x("//div[@class='special-catalog__wrapp']/div/a[@class='ga-click special-catalog__group']");
    }

    ElementsCollection categoriesFromLogicalUnion() {
        return $$x("//main[@id='content']//div[@class='special-catalog__wrapp']//ul/li/a");
    }

    ElementsCollection logicalUnions() {
        return $$x("//main[@id='content']//div[@class='special-catalog__group']");
    }

    ElementsCollection separateCategories() {
        return $$x("//div[@class='special-catalog__wrapp']/div/a[@class='ga-click special-catalog__group']");
    }

    SelenideElement blockMainProducts() {
        return $x("//div[@class='special-catalog']");
    }

}
