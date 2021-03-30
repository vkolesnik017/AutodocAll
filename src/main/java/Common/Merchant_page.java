package Common;

import ATD.CartPayments_page;
import ATD.CartPayments_page_Logic;
import ATD.Main_page_Logic;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;

import static Common.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Merchant_page {

    SelenideElement logoAutodoc() {
        return $x("//div[@class='cart-page-head__logo']/a");
    }

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
    SelenideElement klarnaSubmitBtn() {
        return $x("//div[@class='klarna-authorize']/a");
    }
    SelenideElement klarnaCancelBtn() {
        return $x("//div[@class='klarna-cancelation']/a");
    }

    //Elements from the Sofort merchant
    SelenideElement cancelTransaction() {
        return $x("//div[@class='left hidden-small']//a[@id='AbortLink']");
    }
    SelenideElement submitCancelTransactionBtn() {
        return $x("//button[@id='CancelTransaction']");
    }
    SelenideElement cookieModal() {
        return $x("//div[@class='modal-container compact']");
    }
    SelenideElement cookieAcceptAllBtn() {
        return $x("(//div[@id='cookie-modal-basic']//button[contains(@class,'modal-accept-all')])[2]");
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
        return $x("//div[contains(@data-testid,'list-item')]/div");
    }
    SelenideElement formForDataInMerchant() {
        return $x("//div[@class='sc-oUAoT kVASKi']");
    }
    SelenideElement headerBackBtn() {
        return $x("(//span[@name='close'])[1]");
    }
    SelenideElement cancelTransactionBtnTrustly() {
        return $x("//button[@data-testid='abort-order-button']/span");
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
    public SelenideElement postPayLabel() {
        return $x("//img[contains(@src,'postpay')]");
    }
    public SelenideElement cartaSiLabel() {
        return $x("//img[contains(@src,'cs')]");
    }
    public SelenideElement discoverLabel() {
        return $x("//img[contains(@src,'discover')]");
    }
    public SelenideElement americanExpressLabel() {
        return $x("//img[contains(@src,'cards/ae')]");
    }
    public SelenideElement visaLabel() {
        return $x("//img[contains(@src,'visa.png')]");
    }
    public SelenideElement masterCardLabel() {
        return $x("//img[contains(@src,'mc.png')]");
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
        creditCardFormInfoBtn().waitUntil(appear, 10000);
        JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
        js.executeScript("arguments[0].click();", creditCardFormInfoBtn());
        creditCardFormInfo().waitUntil(visible, 5000).shouldHave(attribute("style","display: block;"));
        js.executeScript("arguments[0].click();", creditCardFormInfoBtn());
        creditCardFormInfo().shouldNotBe(visible).shouldHave(attribute("style", "display: none;"));
        js.executeScript("arguments[0].click();",creditCardFormReset());
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

    @Step("Checks presence of payment methods label for required country. Merchant_page")
    public Merchant_page checksPresencePaymentsMethodLabelForRequiredCountry(String shop) {
        sleep(5000);
        if (shop.equals("IT")) {
            visaLabel().shouldBe(visible);
            masterCardLabel().shouldBe(visible);
            postPayLabel().shouldBe(visible);
            cartaSiLabel().shouldBe(visible);
        } else {
            visaLabel().shouldBe(visible);
            masterCardLabel().shouldBe(visible);
            discoverLabel().shouldBe(visible);
            americanExpressLabel().shouldBe(visible);
        }
        resSetBtn().click();
        return this;
    }

    //This method is used on the merchant page for payment using the Trustly
    @Step("Checks presence element in merchant page for payment Trustly and cancels order. Merchant_page")
    public CartPayments_page_Logic checkPresenceElementFromMerchantPageTrustlyAndCancelOrder() {
        checkingContainsUrl("/trustly");
        switchTo().frame(frame());
        allBank().waitUntil(visible, 10000).click();
        headerBackBtn().waitUntil(visible, 10000).click();
        cancelTransactionBtnTrustly().waitUntil(visible, 10000).click();
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
        if (cookieModal().isDisplayed()) {
            cookieAcceptAllBtn().shouldBe(visible).click();
        }
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
        klarnaSubmitBtn().shouldBe(visible);
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

    @Step("Click by logo Autodoc. Merchant_page")
    public Main_page_Logic clickByLogoAutodoc() {
        logoAutodoc().shouldBe(visible).click();
        return page(Main_page_Logic.class);
    }



}
