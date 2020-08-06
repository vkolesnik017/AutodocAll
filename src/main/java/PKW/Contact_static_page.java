package PKW;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Contact_static_page {

    SelenideElement orderFormIdOrderField() {
        return $x("//input[@id='form_Order[orderId]']");
    }

    SelenideElement orderFormTelField() {
        return $x("//input[@id='form_Order[phone]']");
    }

    SelenideElement orderFormEmailField() {
        return $x("//input[@id='form_Order[email]']");
    }

}
