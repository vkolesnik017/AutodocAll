package XXL;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$;

public class Product_page {

    SelenideElement buyButton() {
        return $(byCssSelector(".product-info__buy-btn>a"));
    }
}
