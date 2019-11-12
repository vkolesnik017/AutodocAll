package ATD;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class CartAccount_page {


    // locators in registration form
    public SelenideElement registrationFormEmailInput() {
        return $(By.xpath("//form[@class='registerForm']//input[@name='Email']"));
    }

    public SelenideElement registrationFormPasswordInput() {
        return $(By.xpath("//form[@class='registerForm']//input[@name='Password']"));
    }

    public SelenideElement registrationButton() {
        return $(byCssSelector(".register"));
    }

    public CartAddress_page registrationFormNextBtnClick() {
        registrationButton().click();
        return page(CartAddress_page.class);
    }

    // locators in login form
    public SelenideElement emailFieldInLoginForm() {
        return $(byXpath("//*[@class='loginForm']//*[@id='form_Email']"));
    }

    public SelenideElement passwordFieldInLoginForm() {
        return $(byXpath("//*[@class='loginForm']//*[@id='form_Password']"));
    }

    public SelenideElement forgotPasswordLink() {
        return $(byCssSelector(".signin-user-box-form__recovery>a"));
    }

    public SelenideElement loginButton() {
        return $(byCssSelector(".login"));
    }

    public CartAddress_page signIn(String email, String password) {
        emailFieldInLoginForm().setValue(email);
        passwordFieldInLoginForm().setValue(password);
        loginButton().click();
        return page(CartAddress_page.class);
    }

    //locators in password recovery popup
    public SelenideElement emailFieldInPasswordRecoveryPopUp() {
        return $(byId("recovery-email"));
    }

    public SelenideElement sendBtnInPasswordRecoveryPopUp() {
        return $(byCssSelector(".submit.color"));
    }

    public SelenideElement closePopupMessageSentForChangePassword() {
        return $(byXpath("//*[@class='cart-popup']//*[contains(text(),'Um Ihr Passwort zu ändern')]/../..//a"));
    }
}
