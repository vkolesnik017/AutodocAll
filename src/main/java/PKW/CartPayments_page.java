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

    public SelenideElement visaLabel() {
        return $x("//img[contains(@src,'cards/visa')]");
    }

    public SelenideElement masterCardLabel() {
        return $x("//img[contains(@src,'cards/mc')]");
    }

    public SelenideElement discoverLabel() {
        return $x("//img[contains(@src,'cards/discover')]");
    }

    public SelenideElement americanExpressLabel() {
        return $x("//img[contains(@src,'cards/ae')]");
    }

    public SelenideElement postePayLabel() {
        return $x("//img[contains(@src,'cards/postpay')]");
    }

    public SelenideElement cartSiLabel() {
        return $x("//img[contains(@src,'cards/cs')]");
    }
}
