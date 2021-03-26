package PKW;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Oe_number_page {

    SelenideElement titlePage() {
        return $x("//h2/span");
    }

    SelenideElement listProductBlock() {return $x("//div[@class='listing_items']");}

    ElementsCollection titleOfProductInOenListing() {
        return $$x("//div[@class='prod_link']/*[self::a or self::span]");
    }

    ElementsCollection  priceOfProduct() {return $$x("//div[@class='price']");}

    ElementsCollection activeBtnAddToBasket() {return $$x("//div[@class='basket_btn button active_red_button ']");}

    ElementsCollection btnAddToBasketWithBrand(String brand) {
        return $$x("//div[@class='basket_btn button active_red_button '][@data-brand-name='" + brand + "']");
    }
}
