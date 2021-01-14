package ATD;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CartAddress_page {

    public SelenideElement shippingForm() {
        return $x("//div[@class='shipping-form input-form']");
    }

    public SelenideElement vorname() {
        return $(byId("form_lVorname"));
    }

    public SelenideElement nameIn() {
        return $(byId("form_lName"));
    }

    public SelenideElement strasse() {
        return $(byId("form_lStrasse"));
    }

    SelenideElement deliveryHouse() {
        return $(byId("form_delivery_house"));
    }

    public SelenideElement ort() {
        return $(byId("form_lOrt"));
    }

    SelenideElement telephon() {
        return $(byId("form_lTelefon"));
    }

    public SelenideElement checkboxFirmShipping() {
        return $x("//input[@id='is_company_shipping']");
    }

    public SelenideElement fieldFirm() {
        return $(byId("form_lFirma"));
    }

    SelenideElement idCompanyShipping() {
        return $(byId("form_lUmsatzId"));
    }

    public SelenideElement postalCodeFieldForShipping() {
        return $(By.id("form_lPlz"));
    }

    SelenideElement countryInSelectorForShipping(String country) {
        return $(byXpath("//*[@name='lLand']//*[@data-code='" + country + "']"));
    }

    SelenideElement vornameBilling() {
        return $(byId("form_rVorname"));
    }

    SelenideElement nameInBilling() {
        return $(byId("form_rName"));
    }

    SelenideElement strasseBilling() {
        return $(byId("form_rStrasse"));
    }

    SelenideElement paymentHouseBilling() {
        return $(byId("form_payment_house"));
    }

    SelenideElement ortBilling() {
        return $(byId("form_rOrt"));
    }

    SelenideElement telephonBilling() {
        return $(byId("form_rTelefon"));
    }

    public SelenideElement fieldFirmBilling() {
        return $(byId("form_Firma"));
    }

    SelenideElement idCompanyBilling() {
        return $(byId("form_rUmsatzId"));
    }

    public SelenideElement checkboxFirmBilling() {
        return $x("//input[@id='is_company_billing']");
    }

    SelenideElement countryInSelectorForBilling(String country) {
        return $(byXpath("//*[@name='rLand']//*[@data-code='" + country + "']"));
    }

    public SelenideElement postalCodeFieldForBilling() {
        return $(By.id("form_rPlz"));
    }

    SelenideElement billingCheckBox() {
        return $x("//input[@id='showBilling']");
    }

    SelenideElement billingForm() {
        return $(By.id("billing_form"));
    }

    public SelenideElement currentCountryInSelector() {
        return $(byXpath("//*[@id='form_lLand']/option[@selected]"));
    }

    SelenideElement fiscalCodeField() {
        return $(byName("lFiscalCode"));
    }

    public SelenideElement nextButton() {
        return $(byCssSelector(".address-continue>a"));
    }

    SelenideElement popupErrorAboutWrongCompany() {
        return $x("//div[@class='cart-popup ']");
    }

    SelenideElement fixBtnInPopupAboutWrongCompany() {
        return $x("//a[@class='color close_popup stay']");
    }

    SelenideElement continueBtnInPopupAboutWrongCompany() {
        return $x("//a[@class='color close_popup continue']");
    }

    SelenideElement btnEinkaufFortsetzenFromPopupErrorAboutWrongCompany() {
        return $x("//div[@class='popup-content__buttons']//a[2]");
    }

    SelenideElement tooltipCOVID19() {
        return $x("//div[@class='error-message']");
    }

    public SelenideElement personalNumberBlock() {
        return $x("//div[@class='row block-personal-number']");
    }

    SelenideElement infoLabelForPersonalNumber() {
        return $x("//div[@class='row block-personal-number']//div[@class='info']");
    }

    SelenideElement textFromPersonalNumberTooltip() {
        return $x("//div[@class='personal_number_tooltip-text']");
    }

    SelenideElement inputPersonalNumber() {
        return $x("//input[@data-gac='Personal_number']");
    }

    SelenideElement getMyAddressBtn() {
        return $x("//div[@class='block-personal-number__button']");
    }

    SelenideElement errorMessage() {
        return $x("//div[@class='error-message']");
    }

    public SelenideElement errorTooltipForBilling() {
        return $x("//form[@id='billing_form']//div[@class='error-message']");
    }

    public SelenideElement errorTooltipForShipping() {
        return $x("//form[@id='shipping_form']//div[@class='error-message']");
    }

    public SelenideElement fiscalCodeBlockInSippingForm() {
        return $x("//div[@class='fiscal-code-block']//div[@id='l-fiscal-code-optional']");
    }

    public SelenideElement fiscalCodeBlockInBillingForm() {
        return $x("//div[@class='fiscal-code-block']//div[@id='r-fiscal-code-optional']");
    }

    public SelenideElement textFiscalCodeInShippingForm() {
        return $x("//input[@id='is-lFiscal-code']/../label[contains(@for,'lFiscalCode') and @style='display: block;']");
    }

    public SelenideElement textFiscalCodeInBillingForm() {
        return $x("//input[@id='is-rFiscal-code']/../label[@for='rFiscalCode']");
    }

    public SelenideElement textFiscalCodeInBillingForm2() {
        return $x("//input[@id='is-rFiscal-code']/../label[@for='rFiscalCode_190' ]");
    }

    SelenideElement checkboxFiscalCode() {
        return $x("//input[@id='is-lFiscal-code']");
    }


    SelenideElement checkboxFiscalCodeBilling() {
        return $x("//input[@id='is-rFiscal-code']");
    }

    public SelenideElement fieldFiscalCode() {
        return $x("//input[@name='lFiscalCode']");
    }

    public SelenideElement fieldFiscalCodeBilling() {
        return $x("//input[@name='rFiscalCode']");
    }

    SelenideElement notesWithAddressRestrictions() {
        return $x("//span[@style='color: inherit;']");
    }

    // block with cart steps
    SelenideElement secondStep() {
       return  $x("//li[@class='complete second_step no-active-link active']");
    }

    SelenideElement thirdStep() {
        return $x("//li[@class='third_step no-active-link ']");
    }

    SelenideElement fourthStep() {
        return $x("//li[@class='fourth_step no-active-link']");
    }

}
