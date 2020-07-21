package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Category_name_brand_page {

    SelenideElement topProductsBlock() {return $x("//div[@class='product-list product-list--4items js-product-list-slider--4 js-product-list-animation js-car-popup slick-initialized slick-slider']");}

    ElementsCollection visibleTitleOfTopProducts() {return $$x("//div[@class='product-list__item__title']");}

    ElementsCollection popUpOfTopProducts() {return $$x("//div[@class='product-list__item__popup']");}

    SelenideElement activeBtnForwardOfTopProductsBlock() {return $x("//button[@class='slick-next slick-arrow']");}

    SelenideElement titleOfTopProductsBlock() {return $x("//div[@class='title_list']/b");}

    ElementsCollection visibleCharacteristicInPopUpOfTopProducts(int position) {return $$x("(//div[@class='product-list__item__popup'])["+position+"]//ul/li/span[1]");}
}
