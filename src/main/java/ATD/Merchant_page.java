package ATD;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.NoAlertPresentException;

import static ATD.CommonMethods.checkingContainsUrl;
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

    //Elements from the Klarna merchant
    SelenideElement closeErrorPopupBtn() {
        return $x("//div[@class='popup-content__buttons']/a");
    }
    SelenideElement klarnaSubmitBtn() {
        return $x("//a[@class='yellow klarna-submit']");
    }
    SelenideElement klarnaCancelBtn() {
        return $x("//a[@class='gray']");
    }

    //Elements from the Sofort merchant
    SelenideElement cancelTransaction() {
        return $x("//div[@class='left hidden-small']//a[@id='AbortLink']");
    }
    SelenideElement submitCancelTransactionBtn() {
        return $x("//button[@id='CancelTransaction']");
    }

    //Elements from the Ideal merchant
    SelenideElement validateSubmit() {
        return $x("//input[@id='b2b-submit']");
    }
    SelenideElement validateError() {
        return $x("//div[@id='b2b-errors']");
    }
    SelenideElement cancelTransactionBtn() {
        return $x("//input[@id='b2b-cancel']");
    }

    //Elements from the Trustly merchant
    SelenideElement allBank() {
        return $x("//div[@class='bank_img_container']");
    }
    SelenideElement formForDataInMerchant() {
        return $x("//form[@id='core_order_holder']");
    }
    SelenideElement headerBackBtn() {
        return $x("//a[@id='header_back_button']");
    }
    SelenideElement cancelTransactionBtnTrustly() {
        return $x("//div[@id='core_order_cancel']/img");
    }


    @Step("Checks presence element in merchant page for payment Ideal and cancels order. Merchant_page")
    public CartPayments_page_Logic checkPresenceElementFromMerchantPageIdealAndCancelOrder() {
        checkingContainsUrl("secure-magenta1.be2bill.com");
        validateSubmit().click();
        validateError().shouldBe(visible);
        cancelTransactionBtn().click();
        checkingContainsUrl("/basket/payments");
        return page(CartPayments_page_Logic.class);
    }

    @Step("Cancels order for Sofort method. Merchant_page")
    public CartPayments_page_Logic cancelOrderForSofortMethod() {
        checkingContainsUrl("sofort.com");
        cancelTransaction().click();
        try {
            switchTo().alert().accept();
        }catch (NoAlertPresentException e) {
            System.out.println("No dialog found");
        }
        submitCancelTransactionBtn().click();
        checkingContainsUrl("/basket/payments");
        return page(CartPayments_page_Logic.class);
    }


    @Step("Checks presence element in merchant page for payment klarna. Merchant_page")
    public CartPayments_page_Logic checkPresenceElementFromMerchantPageForPaymentKlarna() {
        checkingContainsUrl("/klarna");
        klarnaSubmitBtn().click();
        closeErrorPopupBtn().shouldBe(visible).click();
        checkingContainsUrl("/klarna");
        klarnaCancelBtn().click();
        checkingContainsUrl("/basket/payments");
        return page(CartPayments_page_Logic.class);
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
