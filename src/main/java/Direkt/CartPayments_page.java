package Direkt;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class CartPayments_page {

    SelenideElement btnReturnTheAddressPage() {
        return $x("//li[@class='second_step link ']");
    }

    SelenideElement nextBtn() {
        return $x("//button[@class='cart-payment__button cart-button']");
    }

    SelenideElement vorkasse() {
        return $x("//label[@for='check43']");
    }
}
