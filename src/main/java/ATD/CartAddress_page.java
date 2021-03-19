package ATD;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CartAddress_page {

    // Locator for cart-page-steps block
    SelenideElement secondStep() {
        return $x("//li[@class='complete second_step no-active-link active']");
    }

    SelenideElement thirdStep() {
        return $x("//li[@class='third_step no-active-link ']");
    }

    SelenideElement fourthStep() {
        return $x("//li[@class='fourth_step no-active-link']");
    }


    // Locators for shipping_form block are present on all shops
    public SelenideElement shippingForm() {
        return $x("//div[@class='shipping-form input-form']");
    }

    public SelenideElement fieldName() {
        return $(byId("form_lVorname"));
    }

    public SelenideElement fieldLastName() {
        return $(byId("form_lName"));
    }

    public SelenideElement checkboxFirmShipping() {
        return $x("//input[@id='is_company_shipping']");
    }

    public SelenideElement fieldFirm() {
        return $(byId("form_lFirma"));
    }

    SelenideElement fieldIdCompanyShipping() {
        return $(byId("form_lUmsatzId"));
    }

    public SelenideElement fieldStreet() {
        return $(byId("form_lStrasse"));
    }

    SelenideElement fieldHouse() {
        return $(byId("form_delivery_house"));
    }

    SelenideElement notesWithAddressRestrictions() {
        return $x("//span[@style='color: inherit;']");
    }

    public SelenideElement fieldPostalCodeForShipping() {
        return $(By.id("form_lPlz"));
    }

    public SelenideElement fieldCity() {
        return $(byId("form_lOrt"));
    }

    SelenideElement countryInSelectorForShipping(String country) {
        return $(byXpath("//*[@name='lLand']//*[@data-code='" + country + "']"));
    }

    public SelenideElement currentCountryInSelector() {
        return $(byXpath("//*[@id='form_lLand']/option[@selected]"));
    }

    SelenideElement fieldTelephoneShipping() {
        return $(byId("form_lTelefon"));
    }

    public SelenideElement errorTooltipForShipping() {
        return $x("//form[@id='shipping_form']//div[@class='error-message']");
    }

    public SelenideElement errorTooltipFiscalCodeFieldForShipping() {
        return $x("//input[@id='form_lFiscalCode']/..//div[@class='error-message']");
    }

    SelenideElement billingCheckBox() {
        return $x("//input[@id='showBilling']");
    }

    SelenideElement checkBoxConsentToCall() {
        return $x("//input[@id='form_surveySubscribe']");
    }

    SelenideElement textConsentToCall() {
        return $x("//label[@for='form_surveySubscribe']");
    }


    // Locators for shipping_form block are not present on all shops
    public SelenideElement fiscalCodeBlockInSippingForm() {
        return $x("//div[@class='fiscal-code-block']//div[@id='l-fiscal-code-optional']");
    }

    SelenideElement checkboxFiscalCode() {
        return $x("//input[@id='is-lFiscal-code']");
    }

    public SelenideElement textFiscalCodeInShippingForm() {
        return $x("//input[@id='is-lFiscal-code']/../label[contains(@for,'lFiscalCode') and @style='display: block;']");
    }

    public SelenideElement fieldFiscalCode() {
        return $x("//input[@name='lFiscalCode']");
    }

    public SelenideElement personalNumberBlock() {
        return $x("//div[@class='row block-personal-number']");
    }

    SelenideElement inputPersonalNumber() {
        return $x("//input[@data-gac='Personal_number']");
    }

    SelenideElement getMyAddressBtn() {
        return $x("//div[@class='block-personal-number__button']");
    }

    SelenideElement infoLabelForPersonalNumber() {
        return $x("//div[@class='row block-personal-number']//div[@class='info']");
    }

    SelenideElement textFromPersonalNumberTooltip() {
        return $x("//div[@class='personal_number_tooltip-text']");
    }


    // Locators for billing_form block
    SelenideElement billingForm() {
        return $(By.id("billing_form"));
    }

    SelenideElement fieldNameBilling() {
        return $(byId("form_rVorname"));
    }

    SelenideElement fieldLastNameBilling() {
        return $(byId("form_rName"));
    }

    public SelenideElement checkboxFirmBilling() {
        return $x("//input[@id='is_company_billing']");
    }

    public SelenideElement fieldFirmBilling() {
        return $(byId("form_Firma"));
    }

    SelenideElement fieldIdCompanyBilling() {
        return $(byId("form_rUmsatzId"));
    }

    SelenideElement fieldStreetBilling() {
        return $(byId("form_rStrasse"));
    }

    SelenideElement fieldHouseBilling() {
        return $(byId("form_payment_house"));
    }

    public SelenideElement fieldPostalCodeForBilling() {
        return $(By.id("form_rPlz"));
    }

    SelenideElement fieldCityBilling() {
        return $(byId("form_rOrt"));
    }

    SelenideElement countryInSelectorForBilling(String country) {
        return $(byXpath("//*[@name='rLand']//*[@data-code='" + country + "']"));
    }

    SelenideElement fieldTelephoneBilling() {
        return $(byId("form_rTelefon"));
    }

    public SelenideElement fiscalCodeBlockInBillingForm() {
        return $x("//div[@class='fiscal-code-block']//div[@id='r-fiscal-code-optional']");
    }

    public SelenideElement textFiscalCodeInBillingForm() {
        return $x("//input[@id='is-rFiscal-code']/../label[@for='rFiscalCode']");
    }

    public SelenideElement textFiscalCodeInBillingForm2() {
        return $x("//input[@id='is-rFiscal-code']/../label[@for='rFiscalCode_190' ]");
    }

    SelenideElement checkboxFiscalCodeBilling() {
        return $x("//input[@id='is-rFiscal-code']");
    }

    public SelenideElement fieldFiscalCodeBilling() {
        return $x("//input[@name='rFiscalCode']");
    }

    public SelenideElement errorTooltipFiscalCodeFieldForBilling() {
        return $x("//input[@id='form_rFiscalCode']/..//div[@class='error-message']");
    }

    public SelenideElement errorTooltipForBilling() {
        return $x("//form[@id='billing_form']//div[@class='error-message']");
    }


    // Locator for next page button
    public SelenideElement nextButton() {
        return $(byCssSelector(".address-continue>a"));
    }


    // Locators for all popups, tooltips and error messages on the page
    SelenideElement popupErrorAboutWrongCompany() {
        return $x("//div[@class='cart-popup ']");
    }

    SelenideElement BtnFixInPopupAboutWrongCompany() {
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

    SelenideElement errorMessage() {
        return $x("//div[@class='error-message']");
    }

}
