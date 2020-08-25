package ATD;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Merchant_page {

    //Button from the EPS merchant
    public SelenideElement abbrechenSubmit() {
        return $x("//button[@id='abbrechenSubmit']");
    }

    //Elements from the Bancontact/Mister Cash merchant
    SelenideElement fieldForEnteringCardNumber() {
        return $x("//body[@id='custom-body']//input[@name='CARDCODE']");
    }
    SelenideElement fieldForEnteringMonthDate() {
        return $x("//input[@name='MONTHDATE']");
    }
    SelenideElement fieldForEnteringYearDate() {
        return $x("//input[@name='YEARDATE']");
    }
    SelenideElement annulerenSubmit() {
        return $x("//p[@id='placeholder-cancel']");
    }

    //KlarnaCheckout form with data
    SelenideElement klarnaCheckoutFormWithDataBlock() {
        return $x("//div[@data-cid='addmo']");
    }


    //This method is used on the merchant page for payment using the Bancontact/Mister Cash
    @Step("Fills in the fields for entering card data and cancels the payment. Merchant_page")
    public CartPayments_page fillsInFieldsForEnteringDataAndCancelsPayment(String cardNum, String monthDate, String yearDate) {
        switchTo().frame("be2bill-frame");
        fieldForEnteringCardNumber().setValue(cardNum);
        fieldForEnteringMonthDate().setValue(monthDate);
        fieldForEnteringYearDate().setValue(yearDate);
        annulerenSubmit().click();
        return page(CartPayments_page.class);
    }

    //This method is used on the merchant page for payment using the KlarnaCheckout
    @Step("Checks presence form in merchant page from KlarnaCheckout method. Merchant_page")
    public Merchant_page checkPresenceFormInMerchantPageFromKlarnaCheckoutMethod() {
        switchTo().frame("klarna-checkout-iframe");
        klarnaCheckoutFormWithDataBlock().shouldBe(visible);
        return this;
    }
}
