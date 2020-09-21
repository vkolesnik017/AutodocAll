package PKW;

import Common.DataBase;
import io.qameta.allure.Step;

import java.sql.SQLException;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;

public class CartPayments_page_Logic extends CartPayments_page {

    @Step("Return to the address page. CartPayments_page")
    public CartAddress_page_Logic clickBtnReturnTheAddressPage() {
        btnReturnTheAddressPage().click();
        return page(CartAddress_page_Logic.class);
    }

    @Step("Checks default active payments method{paymentMethod}. CartPayments_page")
    public CartPayments_page_Logic checkActivePaymentMethod(String paymentMethod) {
        sleep(3000);
        activePayment(paymentMethod).shouldBe(visible);
        return this;
    }

    @Step("Method clicks on the desired payment method {paymentsName}, for each shop {shop}. CartPayments_page")
    public CartPayments_page_Logic clickOnTheDesiredPaymentMethod(String shop, String paymentsName) throws SQLException {
        String paymentsId = new DataBase("ATD").getPaymentsLocator("payments_atd", shop, paymentsName);
        paymentsLocator(paymentsId).click();
        return this;
    }

    @Step("Click next button. CartPayments_page")
    public CartAllData_page_Logic nextBtnClick() {
        nextBtn().click();
        return page(PKW.CartAllData_page_Logic.class);
    }
}
