package ATD;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class CartPayments_page {

    private SelenideElement payPalBtn() {
        return $(byId("paypal"));
    }
    private SelenideElement vorkasseBtn() { return $(byId("hypovereinsbank"));
    }

    @Step
    public CartPayments_page chooseVorkasse() {
        vorkasseBtn().click();
        return this;
    }


    @Step
    public CartPayments_page choosePayPal() {
        payPalBtn().click();
        return this;
    }

    private SelenideElement nextBtn() {
        return $(byCssSelector("[id='apply_payment']>a"));
    }

    @Step
    public CartAllData_page_Logic nextBtnClick() {
        nextBtn().click();
        return page(CartAllData_page_Logic.class);
    }
}
