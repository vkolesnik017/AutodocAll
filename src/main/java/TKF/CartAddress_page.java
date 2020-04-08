package TKF;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

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

    SelenideElement billingCheckBox() {
        return $x("//input[@id='isBilling']/..//span[@class='radio-text']");
    }

    SelenideElement billingForm() {
        return $x("//form[@id='address-form']");
    }


    SelenideElement nextButton() {
        return $x("//div[@class='delivery']//a[contains(@class,'crumbs-button')]");
    }

    SelenideElement nextButtonShipping() {
        return $x("//a[@class='crumbs-button shipping-form-confirm']");
    }

    SelenideElement textFromPopUpCOVID19() {
        return $x("//div[@class='popup ']//ul//li");
    }

    SelenideElement closeBtnPopupCOVID19() {
        return $x("//a[@class='close']");
    }
}
