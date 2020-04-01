package BVS;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Main_page {

    SelenideElement cartIcon() {
        return $x("//div[@id='cart_block']");
    }
}
