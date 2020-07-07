package ATD;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CartAddress_page {

    SelenideElement shippingForm() {
        return $x("//div[@class='shipping-form input-form']");
    }

    SelenideElement vorname() {
        return $(byId("form_lVorname"));
    }

    SelenideElement nameIn() {
        return $(byId("form_lName"));
    }

    SelenideElement strasse() {
        return $(byId("form_lStrasse"));
    }

    SelenideElement deliveryHouse() {
        return $(byId("form_delivery_house"));
    }

    SelenideElement ort() {
        return $(byId("form_lOrt"));
    }

    SelenideElement telephon() {
        return $(byId("form_lTelefon"));
    }

    SelenideElement checkboxFirmShipping() {
        return $x("//input[@id='is_company_shipping']");
    }

    SelenideElement fieldFirm() {
        return $(byId("form_lFirma"));
    }

    SelenideElement idCompanyShipping() {
        return $(byId("form_lUmsatzId"));
    }

    SelenideElement postalCodeFieldForShipping() {
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

    SelenideElement fieldFirmBilling() {
        return $(byId("form_Firma"));
    }

    SelenideElement idCompanyBilling() {
        return $(byId("form_rUmsatzId"));
    }

    SelenideElement checkboxFirmBilling() {
        return $x("//input[@id='is_company_billing']");
    }

    SelenideElement countryInSelectorForBilling(String country) {
        return $(byXpath("//*[@name='rLand']//*[@data-code='" + country + "']"));
    }

    SelenideElement postalCodeFieldForBilling() {
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
}
