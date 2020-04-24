package ATD;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;


public class CartPayments_page {

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

    SelenideElement nextBtn() {
        return $(byCssSelector("[id='apply_payment']>a"));
    }

    SelenideElement btnReturnTheAddressPage() {
        return $x("//li[@class='complete second_step link ']//a");
    }
}

