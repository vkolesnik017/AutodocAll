package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class Profile_page {

    public SelenideElement nameOfClient(){
        return $(byXpath("//div[@class='name_cash']//span[@class='name']"));
    }

    // settings tab
    public SelenideElement settingsTabBtn() {
        return $(byCssSelector(".settings_link"));
    }

    public SelenideElement oldPasswordFiled() {
        return $(byName("old_pass"));
    }

    public SelenideElement newPasswordField() {
        return $(byName("new_pass"));
    }

    public SelenideElement newPasswordConfirmField() {
        return $(byName("new_pass_confirm"));
    }

    public SelenideElement changePasswordBtn() {
        return $(byCssSelector(".password_submit"));
    }

    public SelenideElement closeSuccessfulPasswordChangePopup() {
        return $(byXpath("//*[@class='popup ']//*[contains(text(),'wurden aktualisiert')]/..//a"));
    }


}
