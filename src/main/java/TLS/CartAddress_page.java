package TLS;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CartAddress_page {

    SelenideElement countryInSelector(String country) {
        return $(byXpath("//*[@class='country inp_text']//*[@data-code='" + country + "']"));
    }

    SelenideElement postalCodeField() {
        return $(By.id("form_rPlz"));
    }

    SelenideElement nextButton() {
        return $x("//a[@class='green']");
    }

    SelenideElement billingCheckBox() {
        return $x("//input[@name='delivery_mode']");
    }

    SelenideElement textFromPopUpCOVID19() {
        return $x("//div[@class='popup pop_soft404 ']//ul//li");
    }

    SelenideElement closeBtnPopupCOVID19() {
        return $x("//div[@class='popup_top']//a[@class='close']");
    }
}