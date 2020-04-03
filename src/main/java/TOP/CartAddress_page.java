package TOP;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CartAddress_page {

    SelenideElement countryInSelector(String country) {
        return $(byXpath("//select[@name='lLand']/option[@data-code='" + country + "']"));
    }

    SelenideElement countryField() {
        return $x("//div[@id='lieferung']//div[@class='ik_select_link']");
    }

    SelenideElement postalCodeField() {
        return $(By.id("form_lPlz"));
    }

    SelenideElement nextButton() {
        return $(byXpath("//a[@class='btn']//span"));
    }

    SelenideElement textFromPopUpCOVID19() {
        return $x("//div[@class='popup_inner']//ul//li");
    }

    SelenideElement closeBtnPopupCOVID19() {
        return $x("//a[@class='close']//span[2]");
    }
}