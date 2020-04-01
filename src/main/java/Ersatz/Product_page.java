package Ersatz;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Product_page {

    SelenideElement cartDropDown() {
        return $x("//div[@class='cart_drop_down']");
    }

    SelenideElement numberBasket() {
        return $(byCssSelector(".code"));
    }

    SelenideElement buyButton() {
        return $(byCssSelector(".product-card__info-buy-button"));
    }

    // locators in popup of cart
    public SelenideElement firstProductPriceInPopupOfCart() {
        return $(byCssSelector(".row-price"));
    }
}
