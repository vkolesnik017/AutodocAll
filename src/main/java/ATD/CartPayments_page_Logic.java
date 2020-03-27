package ATD;

import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;

public class CartPayments_page_Logic extends CartPayments_page{

    @Step("Click Vorkasse. CartPayments_page")
    public CartPayments_page_Logic chooseVorkasse() {
        vorkasseBtn().click();
        return this;
    }

    @Step("Click PayPal. CartPayments_page")
    public CartPayments_page_Logic choosePayPal() {
        payPalBtn().click();
        return this;
    }

    @Step("Click next button. CartPayments_page")
    public CartAllData_page_Logic nextBtnClick() {
        nextBtn().click();
        return page(CartAllData_page_Logic.class);
    }

    @Step("Checks for the absence of PayPal method")
    public CartPayments_page_Logic checkAbsenceOfPayPalMethod(){
        payPalBtn().shouldNotBe(visible);
        return this;
    }

    @Step("Checks for the presence of PayPal method")
    public CartPayments_page_Logic checkPresenceOfPayPalMethod(){
        payPalBtn().shouldBe(visible);
        return this;
    }
}
