package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class PasswordRecovery_page {

    SelenideElement passwordField() {
        return $(byId("new_pass"));
    }

    SelenideElement passwordConfirmField() {
        return $(byId("new_pass_confirm"));
    }

    SelenideElement sendButton() {
        return $(byId("recovery_btn_submit"));
    }

    SelenideElement closePopupOfSuccessPasswordRecovery() {
        return $(byXpath("//*[@id='popup_update']//a"));
    }

}
