package EU;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class Product_page {

    SelenideElement numberBasket() {
        return $x("//div[@class='head_center']//figure[@class='code']");
    }

    SelenideElement buyButton() {
        return $x("//a[@class='cart_bt ']");
    }
}
