package PKW;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CartAddress_page {

    SelenideElement countryInSelectorForShipping(String country) {
        return $(byXpath("//*[@name='lLand']//*[@data-code='" + country + "']"));
    }

    SelenideElement countryInSelectorForBilling(String country) {
        return $(byXpath("//*[@name='rLand']//*[@data-code='" + country + "']"));
    }

    SelenideElement postalCodeFieldForShipping() {
        return $(By.id("form_lPlz"));
    }

    SelenideElement postalCodeFieldForBilling() {
        return $(By.id("form_rPlz"));
    }

    SelenideElement nextButton() {
        return $(byCssSelector(".address-continue>a"));
    }

    SelenideElement billingCheckBox() {
        return $x("//input[@id='showBilling']");
    }

    SelenideElement billingForm() {
        return $(By.id("billing_form"));
    }

    SelenideElement tooltipCOVID19() {
        return $x("//div[@class='error-message']");
    }

    SelenideElement telephon() {
        return $(byId("form_lTelefon"));
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

    SelenideElement checkboxFirmShipping() {
        return $x("//input[@id='is_company_shipping']");
    }

    SelenideElement fieldFirm() {
        return $(byId("form_lFirma"));
    }

    SelenideElement idCompanyShipping() {
        return $(byId("form_lUmsatzId"));
    }

    SelenideElement continueBtnInPopupAboutWrongCompany() {
        return $x("//a[@class='color close_popup continue']");
    }
}
