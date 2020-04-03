package TKF;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CartAddress_page {

    SelenideElement countryInSelector(String country) {
        return $(byXpath("//select[contains(@class,'country input')]//*[@data-code='" + country + "']"));
    }

    SelenideElement postalCodeField() {
        return $(By.id("form_lPlz"));
    }

    SelenideElement nextButton() {
        return $x("//a[@class='crumbs-button address-form-confirm']");
    }

    SelenideElement textFromPopUpCOVID19() {
        return $x("//div[@class='popup ']//ul//li");
    }

    SelenideElement closeBtnPopupCOVID19() {
        return $x("//a[@class='close']");
    }
}
