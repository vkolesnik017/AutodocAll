package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class PasswordRecovery_page {

    SelenideElement passwordField() {
        return $(byId("new_pass"));
    }

    SelenideElement passwordConfirmField() {
        return $(byId("new_pass_confirm"));
    }

    public SelenideElement sendButton() {
        return $(byId("recovery_btn_submit"));
    }

    SelenideElement closePopupOfSuccessPasswordRecovery() {
        return $(byXpath("//*[@id='popup_update']//a"));
    }

    SelenideElement contactForm() {
        return $x("//div[@class='contact_form']");
    }

    SelenideElement popupError() {
        return $x("//div[@id='popup_update']");
    }

    SelenideElement closePopupErrorBtn() {
        return $x("//div[@class='buttons-inner']//a[@class='close']/span[2]");
    }

}
