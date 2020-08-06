package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class Tyres_dimension_page {

    SelenideElement productsList() {
        return $x("//ul[@class='list_products']");
    }

    ElementsCollection btnOutOfStock() {
        return $$x("//div[@class='button not_active']/a");
    }

    SelenideElement feedBackPopUp() {
        return $x("//div[@class='popup-available']");
    }

    SelenideElement popUPAboutInvalidEmail() {
        return $x("//div[@id='popup_update']//div[@class='txt ']");
    }

    SelenideElement btnSendOfFeedBackPopUp() {
        return $x("//input[@class='popup-available__button']");
    }

    SelenideElement emailFieldOfFeedBackPopUp() {
        return $(byId("form_AvailabilityReminder[email]"));
    }
}
