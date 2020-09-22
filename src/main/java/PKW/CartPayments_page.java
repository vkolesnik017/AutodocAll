package PKW;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CartPayments_page {

    SelenideElement btnReturnTheAddressPage() {
        return $x("//li[@class='complete second_step link ']//a");
    }

    SelenideElement activePayment(String paymentMethod) {
        return $x("//input[contains(@id,'"+ paymentMethod +"')]/../../..//div[contains(@class,'row--active')]");
    }

    SelenideElement paymentsLocator(String locator) {
        return $(locator);
    }

    SelenideElement nextBtn() {
        return $(byCssSelector("[id='apply_payment']>a"));
    }
}
