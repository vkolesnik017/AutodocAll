package ATD;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CartAddress_page {

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

    SelenideElement firm() {
        return $(byId("form_lFirma"));
    }


    public SelenideElement currentCountryInSelector() {
        return $(byXpath("//*[@id='form_lLand']/option[@selected]"));
    }

    SelenideElement postalCodeFieldForShipping() {
        return $(By.id("form_lPlz"));
    }

    SelenideElement postalCodeFieldForBilling() {
        return $(By.id("form_rPlz"));
    }

    SelenideElement fiscalCodeField() {
        return $(byName("lFiscalCode"));
    }

    public SelenideElement nextButton() {
        return $(byCssSelector(".address-continue>a"));
    }

    SelenideElement countryInSelectorForShipping(String country) {
        return $(byXpath("//*[@name='lLand']//*[@data-code='" + country + "']"));
    }

    SelenideElement countryInSelectorForBilling(String country) {
        return $(byXpath("//*[@name='rLand']//*[@data-code='" + country + "']"));
    }

    SelenideElement billingCheckBox() {
        return $x("//input[@id='showBilling']");
    }

    SelenideElement billingForm() {
        return $(By.id("billing_form"));
    }

    SelenideElement popupErrorAboutWrongCompany() {
        return $x("//div[@class='cart-popup ']");
    }

    SelenideElement btnEinkaufFortsetzenFromPopupErrorAboutWrongCompany() {
        return $x("//div[@class='popup-content__buttons']//a[2]");
    }

    SelenideElement tooltipCOVID19() {
        return $x("//div[@class='error-message']");
    }
}
