package PKW;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Product_page {

    SelenideElement titleProduct() {
        return $x("//div[@class='pkw-product__row ']//h2");
    }

    public SelenideElement cartIcon() {
        return $(byCssSelector(".header__cart-count"));
    }

    SelenideElement numberBasket() {
        return $(byCssSelector(".code"));
    }

    SelenideElement buyButton() {
        return $(byCssSelector(".pkw-product__buy-btn"));
    }

    // locators in popup of cart
    public SelenideElement firstProductPriceInPopupOfCart() {
        return $(byCssSelector(".row-price"));
    }

    SelenideElement closeBtnOfPopupOtherCategory() {
        return $x("//span[@class='popup-related__close']");
    }

    SelenideElement closeBtnOFPopupReview() {
        return $x("//span[@class='popup-related__close']");
    }
}
