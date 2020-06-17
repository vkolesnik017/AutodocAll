package Direkt;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$;

public class Product_page {

    public SelenideElement cartDropDown() {
        return $(byCssSelector("//div[@class='cart_drop_down']"));
    }

    SelenideElement numberBasket() {
        return $(By.xpath("//div[@class='header-middle__cart']//span[@class='count']"));
    }

    SelenideElement buyButton() {
        return $(byCssSelector(".buy-button"));
    }

    // locators in popup of cart
    public SelenideElement firstProductPriceInPopupOfCart() {
        return $(By.xpath("//div[@class='cart-tooltip']//div[@class='price']"));
    }
}
