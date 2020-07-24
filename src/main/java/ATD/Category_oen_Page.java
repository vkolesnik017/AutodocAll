package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Category_oen_Page {
    SelenideElement listProductBlock() {return $x("//ul[@class='list_products ']");}

    ElementsCollection titleOfProductInList() {return $$x("//div[@class='name']/*[self::a or self::span][1]");}

    ElementsCollection activeBtnAddToBasket() {return $$x("//div[@class='button ']");}

    ElementsCollection btnAddToBasketWithBrand(String brand) {return $$x("//div[@class='button '][@data-brand-name='"+brand+"']");}

    ElementsCollection  priceOfProduct() {return $$x("//p[@class='actual_price']");}

}


