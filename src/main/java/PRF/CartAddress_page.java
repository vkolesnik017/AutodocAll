package PRF;

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
        return $(By.id("form_lPlz"));
    }

    SelenideElement nextButton() {
        return $x("//a[@class='btn next_button']");
    }

    SelenideElement errorMessageCOVID19() {
        return $x("//div[@class='error-message']");
    }
}
