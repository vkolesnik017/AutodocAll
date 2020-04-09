package KAUF;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CartAddress_page {

    SelenideElement countryInSelector(String country) {
        return $(byXpath("//*[@name='rLand']//*[@data-code='" + country + "']"));
    }

    SelenideElement postalCodeField() {
        return $(By.id("form_rPlz"));
    }

    SelenideElement nextButton() {
        return $x("//a[@class='green_bt next_button']");
    }

    SelenideElement textFromPopUpCOVID19() {
        return $x("//div[@class='popup_sie popup ']//ul//li");
    }

    SelenideElement closeBtnPopupCOVID19() {
        return $x("//a[@class='close middle']");
    }

    SelenideElement billingCheckBox() {
        return $x("//div[@class='jq-checkbox__div']");
    }
}
