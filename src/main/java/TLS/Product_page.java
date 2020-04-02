package TLS;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Product_page {

    SelenideElement numberBasket() {
        return $(byCssSelector(".code"));
    }

    SelenideElement buyButton() {
        return $x("//a[@class='product-card__but ']");
    }
}
