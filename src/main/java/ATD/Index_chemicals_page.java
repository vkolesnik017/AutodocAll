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

    SelenideElement detailsInPopupProductInBlockTopProducts() {
        return $x("//div[@class='top-product-block']//div[@class='item-table-box__details']");
    }


}
