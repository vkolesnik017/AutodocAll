package ATD;

import Common.DataBase;
import io.qameta.allure.Step;

import java.sql.SQLException;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;

public class CartPayments_page_Logic extends CartPayments_page{

    @Step("Click Vorkasse. CartPayments_page")
    public CartPayments_page_Logic chooseVorkasse() {
        vorkasseBtn().click();
        return this;
    }

    // For PL shop
    @Step("Click przelew bankowy. CartPayments_page")
    public CartPayments_page_Logic choosePrzelewBankowy() {
        przelewBankowyBtn().click();
        return this;
    }

    // For EN shop
    @Step("Click Unicredit Bank. CartPayments_page")
    public CartPayments_page_Logic chooseUnicreditBank() {
        unicreditBankBtn().click();
        return this;
    }

    //For AT shop
    @Step("Click Bank Austria. CartPayments_page")
    public CartPayments_page_Logic chooseBankAustria() {
        bankAustria().click();
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

    @Step("Method clicks on the desired payment method {paymentsName}, for each shop {shop}. CartPayments_page")
    public CartPayments_page_Logic clickOnTheDesiredPaymentMethod(String shop, String paymentsName) throws SQLException {
        String paymentsId = new DataBase().getPaymentsLocator("payments_atd", shop, paymentsName);
        paymentsLocator(paymentsId).click();
        return this;
    }

    @Step("Checks default active payments method{paymentMethod}. CartPayments_page")
    public CartPayments_page_Logic checkActivePaymentMethod(String paymentMethod) {
        sleep(3000);
        activePayment(paymentMethod).shouldBe(visible);
        return this;
    }
}
