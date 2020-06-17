package Direkt;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CartAddress_page {

    SelenideElement shippingTelInput() {
        return $(byXpath("//input[@name='address[shipping][phone]']"));
    }

    SelenideElement shippingSurnameInput() {
        return $(byXpath("//input[@name='address[shipping][surname]']"));
    }

    SelenideElement shippingNameInput() {
        return $(byXpath("//input[@name='address[shipping][name]']"));
    }

    SelenideElement shippingFirmCheckbox() {
        return $(byXpath("//label[@for='honorific_company_shipping']"));
    }

    SelenideElement shippingFirmInput() {
        return $(byXpath("//input[@name='address[shipping][company]']"));
    }

    SelenideElement shippingPLZInput() {
        return $(byXpath("//input[@name='address[shipping][postcode]']"));
    }

    SelenideElement shippingOrtInput() {
        return $(byXpath("//input[@name='address[shipping][city]']"));
    }

    SelenideElement shippingStreetInput() {
        return $(byXpath("//input[@name='address[shipping][street]']"));
    }

    SelenideElement shippingHouseInput() {
        return $(byXpath("//input[@name='address[shipping][house]']"));
    }

    SelenideElement chekboxSameAddress() {
        return $(byXpath("//label[@for='showBilling']"));
    }



    SelenideElement shippingCountryInSelector(String country) {
        return $(byXpath("//select[@name='address[shipping][countryId]']/option[@data-code='" + country + "']"));
    }

    SelenideElement countryInSelector(String country) {
        return $(byXpath("//*[@name='rLand']//*[@data-code='" + country + "']"));
    }

    SelenideElement billingTelInput() {
        return $(byXpath("//input[@name='address[billing][phone]']"));
    }

    SelenideElement postalCodeField() {
        return $(By.id("form_rPlz"));
    }

    SelenideElement billingCheckBox() {
        return $x("//input[@id='isBilling']");
    }

    SelenideElement nextButton() {
        return $(byXpath("//button[@class='cart-address__address-button cart-button']"));
    }

    SelenideElement popupCOVID19() {
        return $x("//div[@class='popup pop_soft404 ']//ul//li");
    }

    SelenideElement closeBtnPopupCOVID19() {
        return $x("//div[@class='popup_top']//a[@class='close']");
    }
}
