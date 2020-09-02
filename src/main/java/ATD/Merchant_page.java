package ATD;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
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
        return $x("//div[@class='order_holder core_loader_holder']");
    }
    SelenideElement headerBackBtn() {
        return $x("//a[@id='header_back_button']");
    }
    SelenideElement cancelTransactionBtnTrustly() {
        return $x("//div[@id='core_order_cancel']/img");
    }
    SelenideElement btnExit() {
        return $x("//a[@class='prompt-yes']");
    }
    SelenideElement frame() {
        return $x(" //div[@class='cont_concardis']//iframe");
    }

    //Elements from the BraintreeCreditCard merchant
    SelenideElement braintreeCardSubmitBtn() {
        return $x("//div[@class='creditcard-form__button']//input");
    }
    SelenideElement infoPopUp() {
        return $x("//div[@class='info-popup']");
    }
    SelenideElement infoBtn() {
        return $x("//a[@class='creditcard-form__info']");
    }
    SelenideElement fieldCreditCardNum() {
        return $x("//input[@id='credit-card-number']");
    }
    SelenideElement fieldExpiration() {
        return $x("//input[@id='expiration']");
    }
    SelenideElement fieldCVV() {
        return $x("//input[@id='cvv']");
    }
    SelenideElement resSetBtn() {
        return $x("//div[@class='creditcard-form__resset']/a");
    }
    SelenideElement iFrameFieldCreditCardNum() {
        return $x("//div[@id='card-number']//iframe");
    }
    SelenideElement iFrameFieldExpiration() {
        return $x("//div[@id='expiration-date']//iframe");
    }
    SelenideElement iFrameFieldCVV() {
        return $x("//div[@id='cvv']//iframe");
    }

    //Elements from the B2billCreditCard merchant
    SelenideElement b2billSubmitCreditCard() {
        return $x("//input[@type='submit']");
    }
    SelenideElement creditCardFormErrors() {
        return $x("//ul[@class='creditcard-form__errors']");
    }
    SelenideElement fieldCardNum() {
        return $x("//input[@name='hosted-fields-card']");
    }
    SelenideElement fieldExpiry() {
        return $x("//input[@name='hosted-fields-expiry']");
    }
    SelenideElement fieldCryptogram() {
        return $x("//input[@name='hosted-fields-cryptogram']");
    }
    SelenideElement creditCardFormInfoBtn() {
        return $x("//a[@class='creditcard-form__info']");
    }
    SelenideElement creditCardFormInfo() {
        return $x("//div[@class='info-popup']");
    }
    SelenideElement creditCardFormReset() {
        return $x("//div[@class='creditcard-form__resset']/a");
    }
    SelenideElement iFrameFieldCardNum() {
        return $x("//span[@id='card-number']//iframe");
    }
    SelenideElement iFrameFieldExpiry() {
        return $x("//span[@id='expiration-date']//iframe");
    }
    SelenideElement iFrameFieldCryptogram() {
        return $x("//div[@id='cvv']//iframe");
    }



    //This method is used on the merchant page for payment using the B2billCreditCard
    @Step("Checks presence element in merchant page for payment B2billCreditCard and cancels order. Merchant_page")
    public CartPayments_page_Logic checkPresenceElementFromMerchantPageB2billCreditCardAndCancelOrder(String cardNum, String expiration, String cvv) {
        checkingContainsUrl("/be2bill");
        b2billSubmitCreditCard().click();
        creditCardFormErrors().shouldBe(visible);
        switchTo().frame(iFrameFieldCardNum());
        fieldCardNum().setValue(cardNum);
        switchTo().window(0);
        switchTo().frame(iFrameFieldExpiry());
        fieldExpiry().setValue(expiration);
        switchTo().window(0);
        switchTo().frame(iFrameFieldCryptogram());
        fieldCryptogram().setValue(cvv);
        switchTo().window(0);
        sleep(5000);
        creditCardFormInfoBtn().shouldBe(visible).click();
        creditCardFormInfo().waitUntil(visible, 5000).shouldHave(attribute("style","display: block;"));
        creditCardFormInfoBtn().click();
        creditCardFormInfo().shouldNotBe(visible).shouldHave(attribute("style", "display: none;"));
        creditCardFormReset().click();
        checkingContainsUrl("/basket/payments");
        return page(CartPayments_page_Logic.class);
    }

    //This method is used on the merchant page for payment using the BraintreeCreditCard
    @Step("Checks presence element in merchant page for payment BraintreeCreditCard and cancels order. Merchant_page")
    public CartPayments_page_Logic checkPresenceElementFromMerchantPageBraintreeCreditCardAndCancelOrder(String cardNum, String expiration, String cvv) {
        checkingContainsUrl("/bcreditcards");
        braintreeCardSubmitBtn().shouldHave(attribute("disabled"));
        switchTo().frame(iFrameFieldCreditCardNum());
        fieldCreditCardNum().setValue(cardNum);
        switchTo().window(0);
        switchTo().frame(iFrameFieldExpiration());
        fieldExpiration().setValue(expiration);
        switchTo().window(0);
        switchTo().frame(iFrameFieldCVV());
        fieldCVV().setValue(cvv);
        switchTo().window(0);
        braintreeCardSubmitBtn().shouldNotHave(attribute("disabled"));
        infoBtn().click();
        infoPopUp().shouldBe(visible).shouldHave(attribute("style","display: block;"));
        infoBtn().click();
        infoPopUp().shouldNotBe(visible).shouldHave(attribute("style", "display: none;"));
        resSetBtn().click();
        checkingContainsUrl("/basket/payments");
        return page(CartPayments_page_Logic.class);
    }

    //This method is used on the merchant page for payment using the Trustly
    @Step("Checks presence element in merchant page for payment Trustly and cancels order. Merchant_page")
    public CartPayments_page_Logic checkPresenceElementFromMerchantPageTrustlyAndCancelOrder() {
        checkingContainsUrl("/trustly");
        switchTo().frame(frame());
        allBank().click();
        formForDataInMerchant().shouldBe(visible);
        headerBackBtn().click();
        allBank().shouldBe(visible);
        cancelTransactionBtnTrustly().click();
        btnExit().waitUntil(visible, 5000).click();
        checkingContainsUrl("/basket/payments");
        return page(CartPayments_page_Logic.class);
    }


    //This method is used on the merchant page for payment using the Ideal
    @Step("Checks presence element in merchant page for payment Ideal and cancels order. Merchant_page")
    public CartPayments_page_Logic checkPresenceElementFromMerchantPageIdealAndCancelOrder() {
        checkingContainsUrl("secure-magenta1.be2bill.com");
        validateSubmit().click();
        validateError().shouldBe(visible);
        cancelTransactionBtn().click();
        checkingContainsUrl("/basket/payments");
        return page(CartPayments_page_Logic.class);
    }

    //This method is used on the merchant page for payment using the Sofort
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

    //This method is used on the merchant page for payment using the klarna
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
