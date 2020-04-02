package EU;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CartAddress_page {

    SelenideElement countryInSelector(String country) {
        return $(byXpath("//div[@class='or_sel']//*[@data-code='" + country + "']"));
    }

    SelenideElement postalCodeField() {
        return $(By.id("form_rPlz"));
    }

    SelenideElement nextButton() {
        return $x("//a[@class='orange_bt']");
    }

    SelenideElement textFromPopUpCOVID19() {
        return $x("//div[@class='popup reduced current_popup']//ul//li");
    }

    SelenideElement closeBtnPopUpCOVID19() {
        return $x("//a[@class='close close_i']");
    }
}
