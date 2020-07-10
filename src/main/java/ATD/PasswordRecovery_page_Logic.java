package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;

public class PasswordRecovery_page_Logic extends PasswordRecovery_page {

    @Step("Filling fields and click submit. PasswordRecovery_page")
    public Profile_page fillPasswordFieldsAndClickSubmit(String password) {
        passwordField().setValue(password);
        passwordConfirmField().setValue(password);
        sendButton().click();
        closePopupOfSuccessPasswordRecovery().click();
        return page(Profile_page.class);
    }

    @Step("Checks presence contact form. PasswordRecovery_page")
    public PasswordRecovery_page_Logic checkPresenceContactForm() {
        contactForm().shouldBe(visible);
        return this;
    }

    @Step("Click button send. PasswordRecovery_page")
    public PasswordRecovery_page_Logic clickSendBtn() {
        sendButton().click();
        return this;
    }

    @Step("Checks text {expectedText} in popup error when fields are not filled. PasswordRecovery_page")
    public PasswordRecovery_page_Logic checkTextInPopupErrorWhenFieldsNotFilled(String expectedText) {
        clickSendBtn();
        popupError().shouldHave(text(expectedText));
        closePopupErrorBtn().click();
        return this;
    }

    @Step("Checks text {expectedText} in popup error when entering different " +
            "passwords in the Password {pass} and Repeat password {confirmPass} fields. PasswordRecovery_page")
    public PasswordRecovery_page_Logic checkTextInPopupErrorWhenEnteringDifferentPasswords(String pass, String confirmPass, String expectedText) {
        passwordField().setValue(pass);
        passwordConfirmField().setValue(confirmPass);
        clickSendBtn();
        popupError().shouldHave(text(expectedText));
        return this;
    }
}
