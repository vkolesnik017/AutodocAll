package ATD;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;


public class CartPayments_page {

    // Locators for cart-page-steps block
    SelenideElement btnReturnTheAddressPage() {
        return $x("//li[@class='complete second_step link ']//a");
    }

    SelenideElement btnReturnTheCartPage() {
        return $x("//li[@class='complete link first_step']/a");
    }

    // locators for payment types
    SelenideElement payPalBtn() {
        return $(byId("paypal"));
    }

    SelenideElement vorkasseBtn() {
        return $(byId("hypovereinsbank"));
    }

    //for PL shop
    SelenideElement przelewBankowyBtn() {
        return $(byId("przelew_bankowy"));
    }

    //for EN shop
    SelenideElement unicreditBankBtn() {
        return $(byId("unicredit_bank_en"));
    }

    //for AT shop
    SelenideElement bankAustria() {
        return $(byId("bankAustria"));
    }

    SelenideElement paymentsLocator(String locator) {
        return $(locator);
    }

    SelenideElement activePayment(String paymentMethod) {
        return $x("//input[contains(@id,'"+ paymentMethod +"')]/../../..//div[contains(@class,'row--active')]");
    }

    // Next step button
    SelenideElement nextBtn() {
        return $(byCssSelector("[id='apply_payment']>a"));
    }
}

