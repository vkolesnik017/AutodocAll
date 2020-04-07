package BVS;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byXpath;
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

    SelenideElement tooltipCOVID19() {
        return $x("//div[@class='error-message']");
    }

    SelenideElement billingCheckBox() {
        return $x("//input[@id='showBilling']");
    }

    SelenideElement billingForm() {
        return $(By.id("billing_form"));
    }
}
