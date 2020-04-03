package TKF;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Product_page {

    SelenideElement buyButton() {
        return $x("//a[@class='but_cart_2 order_btn basket_btn ']");
    }
}
