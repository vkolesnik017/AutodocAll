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

    // For PL shop
    @Step("Click przelew bankowy. CartPayments_page")
    public CartPayments_page_Logic chossePrzelewBankowy() {
        przelewBankowyBtn().click();
        return this;
    }

    // For EN shop
    @Step("Click Unicredit Bank. CartPayments_page")
    public CartPayments_page_Logic chosseUnicreditBank() {
        unicreditBankBtn().click();
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

    @Step("Checks for the absence of PayPal method.CartPayments_page")
    public CartPayments_page_Logic checkAbsenceOfPayPalMethod(){
        payPalBtn().shouldNotBe(visible);
        return this;
    }

    @Step("Checks for the presence of PayPal method. CartPayments_page")
    public CartPayments_page_Logic checkPresenceOfPayPalMethod(){
        payPalBtn().shouldBe(visible);
        return this;
    }

    @Step("Return to the address page. CartPayments_page")
    public CartAddress_page_Logic clickBtnReturnTheAddressPage() {
        btnReturnTheAddressPage().click();
        return page(CartAddress_page_Logic.class);
    }
}
