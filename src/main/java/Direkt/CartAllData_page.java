package Direkt;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class CartAllData_page {

    SelenideElement nextBtn() {
        return $x("//div[@class='cart-summary']//a[@class='cart-button']");
    }
}
