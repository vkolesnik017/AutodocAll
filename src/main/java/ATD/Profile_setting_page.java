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

    SelenideElement popUpUpdate() {
        return $x("//div[@id='popup_update']");
    }

    SelenideElement closePopUP() {
        return $x("//div[@class='buttons-inner']");
    }
}
