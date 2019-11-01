package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

class Profile_page {

    SelenideElement nameOfClient(){
        return $(byXpath("//div[@data-tab-id='vehicle']//span[@class='name']"));
    }

    // settings tab
    SelenideElement settingsTabBtn() {
        return $(byCssSelector(".settings_link"));
    }

    SelenideElement oldPasswordFiled() {
        return $(byName("old_pass"));
    }

    SelenideElement newPasswordField() {
        return $(byName("new_pass"));
    }

    SelenideElement newPasswordConfirmField() {
        return $(byName("new_pass_confirm"));
    }

    SelenideElement changePasswordBtn() {
        return $(byCssSelector(".password_submit"));
    }

    SelenideElement closeSuccessfulPasswordChangePopup() {
        return $(byXpath("//*[@class='popup ']//*[contains(text(),'wurden aktualisiert')]/..//a"));
    }


}
