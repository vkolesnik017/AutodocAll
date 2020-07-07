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
}
