package ATD;


import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;

public class Profile_setting_page_Logic extends Profile_setting_page {

    @Step("Checks presence change pass block. Profile_setting_page_Logic")
    public Profile_setting_page_Logic checkPresenceChangePassBlock() {
        changePassBlock().shouldBe(visible);
        return this;
    }

    @Step("Checks presence change email block. Profile_setting_page_Logic")
    public Profile_setting_page_Logic checkPresenceChangeEmailBlock() {
        changeEmailBlock().shouldBe(visible);
        return this;
    }

    @Step("Fills field old pass {old pass}. Profile_setting_page_Logic")
    public Profile_setting_page_Logic fillFieldOldPass(String oldPass) {
        oldPassField().setValue(oldPass);
        return this;
    }

    @Step("Fills field new pass {new pass}. Profile_setting_page_Logic")
    public Profile_setting_page_Logic fillFieldNewPass(String newPass) {
        newPassField().setValue(newPass);
        return this;
    }

    @Step("Fills field confirm pass {confirm pass}. Profile_setting_page_Logic")
    public Profile_setting_page_Logic fillFieldConfirmPass(String confirmPass) {
        confirmPassField().setValue(confirmPass);
        return this;
    }

    @Step("Click save password button. Profile_setting_page_Logic")
    public Profile_setting_page_Logic clickSvePassBtn() {
        savePassBtn().click();
        return this;
    }

    @Step("Checks presence and close pop up. Profile_setting_page_Logic")
    public Profile_setting_page_Logic closePopUp() {
        popUpUpdate().shouldBe(visible);
        closePopUP().click();
        return this;
    }

    @Step("Checks title inside popup {expectedText}. Profile_setting_page_Logic")
    public Profile_setting_page_Logic checkTitleInsidePopUp(String expectedText) {
        titleInsidePopUp().shouldHave(text(expectedText));
        return this;
    }

    @Step("Checks for error text {expectedText} inside popup. Profile_setting_page_Logic")
    public Profile_setting_page_Logic checkErrorTextInsidePopUp(String expectedText) {
        errorTextInsidePopUp().shouldHave(matchesText(expectedText));
        return this;
    }


    @Step(":from Profile_setting_page_Logic")
    public Main_page_Logic logOutClick() {
        new Main_page_Logic().logOutClick();
        return page(Main_page_Logic.class);
    }
}
