package Direkt;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Payment_handler_page {

    SelenideElement confirmationLabel() {
        return $x("//div[@class='payment-confirmation__order-label']");
    }

}
