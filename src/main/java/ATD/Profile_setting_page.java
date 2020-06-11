package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Profile_setting_page {

    SelenideElement changePassBlock() {
        return $x("//div[@class='change_pass']");
    }

    SelenideElement changeEmailBlock() {
        return $x("//div[@class='change_pass another_email']");
    }

    SelenideElement oldPassField() {
        return $x("//input[@name='old_pass']");
    }

    SelenideElement newPassField() {
        return $x("//input[@name='new_pass']");
    }

    SelenideElement confirmPassField() {
        return $x("//input[@name='new_pass_confirm']");
    }

    SelenideElement savePassBtn() {
        return $(".password_submit");
    }

    SelenideElement newEmailField() {
        return $x("//form[@id='email_info']//input[@name='email']");
    }

    SelenideElement confirmEmailField() {
        return $x("//form[@id='email_info']//input[@name='email_confirm']");
    }

    SelenideElement saveEmailBtn() {
        return $(".email_submit");
    }

    SelenideElement popUpUpdate() {
        return $x("//div[@id='popup_update']");
    }

    SelenideElement titleInsidePopUp() {
        return $x("//div[@id='popup_update']//h3");
    }

    SelenideElement closePopUP() {
        return $x("//div[@class='buttons-inner']");
    }

    SelenideElement errorTextInsidePopUp() {
        return $x("//div[@class='txt ']//ul");
    }

    SelenideElement textInsidePopUpSubscribe() {
        return $x("//div[@class='txt ']");
    }

    SelenideElement subscribeCheckbox() {
        return $x("//input[@id='subscribeCheckbox']");
    }
}