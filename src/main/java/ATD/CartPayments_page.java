package ATD;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;


public class CartPayments_page {

    SelenideElement payPalBtn() {
        return $(byId("paypal"));
    }
    SelenideElement vorkasseBtn() {
        return $(byId("hypovereinsbank"));
    }
    SelenideElement nextBtn() {
        return $(byCssSelector("[id='apply_payment']>a"));
    }
}

