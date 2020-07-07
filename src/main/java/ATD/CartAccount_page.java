package ATD;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CartAccount_page {


    // locators in registration form
    public SelenideElement registrationFormEmailInput() {
        return $(By.xpath("//form[@class='registerForm']//input[@name='Email']"));
    }

    public SelenideElement registrationFormPasswordInput() {
        return $(By.xpath("//form[@class='registerForm']//input[@name='Password']"));
    }

    SelenideElement registrationButton() {
        return $(byCssSelector(".register"));
    }

    SelenideElement datenschutzerklarungLink() {
        return $(By.cssSelector("#privacy_policy1>a"));
    }

    // locators in login form
    SelenideElement emailFieldInLoginForm() {
        return $(byXpath("//*[@class='loginForm']//*[@id='form_Email']"));
    }

    SelenideElement passwordFieldInLoginForm() {
        return $(byXpath("//*[@class='loginForm']//*[@id='form_Password']"));
    }

    public SelenideElement forgotPasswordLink() {
        return $(byCssSelector(".signin-user-box-form__recovery>a"));
    }

    SelenideElement loginButton() {
        return $(byCssSelector(".login"));
    }

    SelenideElement facebookLoginBtn() {
        return $x("//form[@class='loginForm']//a[@class='social-auth__link']//img");
    }

    SelenideElement emailFieldForFB() {
        return $x("//input[@id='email']");
    }

    SelenideElement passFieldFB() {
        return $x("//input[@id='pass']");
    }

    SelenideElement loginBtnFB() {
        return $x("//input[@name='login']");
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

    public SelenideElement errorPopUp() {
        return $x("//div[@class='cart-popup js-error-popup ']");
    }

    public SelenideElement closeErrorPopUpBtn() {
        return $x("//a[@class='color close_popup']");
    }

    SelenideElement passwordRecoveryLinc() {
        return $x("//a[@class='reg versegen']");
    }

    SelenideElement passwordRecoveryPopUp() {
        return $x("//div[@class='recovery-popup cart-popup']");
    }

    SelenideElement sendingEmailBtnForPasswordChange() {
        return $x("//div[@class='recovery-popup cart-popup']//a[@class='submit color']");
    }
}
