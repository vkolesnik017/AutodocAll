package ATD;


import io.qameta.allure.Step;
import org.testng.Assert;

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

    @Step("Fills field new Email {newEmail}. Profile_setting_page_Logic")
    public Profile_setting_page_Logic fillFieldNewEmail(String newEmail) {
        newEmailField().setValue(newEmail);
        return this;
    }

    @Step("Fills field confirm Email {confirmEmail}. Profile_setting_page_Logic")
    public Profile_setting_page_Logic fillFieldConfirmEmail(String confirmEmail) {
        confirmEmailField().setValue(confirmEmail);
        return this;
    }

    @Step("Click save password button. Profile_setting_page_Logic")
    public Profile_setting_page_Logic clickSvePassBtn() {
        savePassBtn().click();
        return this;
    }

    @Step("Click save new Email button. Profile_setting_page_Logic")
    public Profile_setting_page_Logic clickSaveEmailBtn() {
        saveEmailBtn().click();
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

    @Step("Checks for text {expectedText} inside popup subscribe")
    public Profile_setting_page_Logic checkTextInsidePopUpSubscribe(String expectedText) {
        textInsidePopUpSubscribe().shouldHave(matchesText(expectedText));
        return this;
    }

    @Step(":from Profile_setting_page_Logic")
    public Main_page_Logic logOutClick() {
        new Main_page_Logic().logOutClick();
        return page(Main_page_Logic.class);
    }

    @Step(":from Profile_setting_page_Logic")
    public Profile_setting_page_Logic checkForTextInBlockTopTitle(String expectedText) {
        new Profile_addresses_page_Logic().checkForTextInBlockTopTitle(expectedText);
        return this;
    }

    @Step(":from Profile_setting_page_Logic")
    public Profile_setting_page_Logic checkPresenceClientID() {
        new Profile_addresses_page_Logic().checkPresenceClientID();
        return this;
    }

    @Step(":from Profile_setting_page_Logic")
    public Profile_setting_page_Logic checkPresenceHeaderBlockAndElementInside() {
        new Profile_addresses_page_Logic().checkPresenceHeaderBlockAndElementInside();
        return this;
    }

    @Step(":from Profile_setting_page_Logic")
    public Profile_setting_page_Logic checkNamePageAndPresenceIcon(String expectedName) {
        new Profile_addresses_page_Logic().checkNamePageAndPresenceIcon(expectedName);
        return this;
    }

    @Step("Checks presence fields in change pass block. Profile_setting_page_Logic")
    public Profile_setting_page_Logic checkPresenceFieldsInChangePassBlock() {
        checkPresenceChangePassBlock();
        oldPassField().shouldBe(visible);
        newPassField().shouldBe(visible);
        confirmPassField().shouldBe(visible);
        savePassBtn().shouldBe(visible);
        return this;
    }

    @Step("Checks presence fields in change email block. Profile_setting_page_Logic")
    public Profile_setting_page_Logic checkPresenceFieldsInChangeEmailBlock() {
        checkPresenceChangeEmailBlock();
        newEmailField().shouldBe(visible);
        confirmEmailField().shouldBe(visible);
        saveEmailBtn().shouldBe(visible);
        return this;
    }

    @Step("Click subscribe checkbox. Profile_setting_page_Logic")
    public Profile_setting_page_Logic clickSubscribeCheckbox() {
        subscribeCheckbox().click();
        return this;
    }

    @Step("Checks that the subscribe checkbox is selected. Profile_setting_page_Logic")
    public Profile_setting_page_Logic checkThatSubscribeCheckboxIsSelected() {
        Assert.assertTrue(subscribeCheckbox().isSelected());
        return this;
    }
}
