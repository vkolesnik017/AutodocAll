package ATD;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CartAccount_page {

    // Locators for cart-page-head cart-page-head--steps block
    SelenideElement btnReturnToCart() {
        return $x("//div[@class='cart-page-steps']//li[@class='complete link first_step']/a");
    }

    // locators for registerForm (registration)
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

    public SelenideElement errorPopUpForRegisteringAndRecovery() {
        return $x("//div[@class='cart-popup js-error-popup ']");
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

    SelenideElement infoTextOfCheckBockAtRegForm() {
        return $x("//label[@for='isSubscribe']");
    }

    SelenideElement infoTextUnderCheckBockAtRegForm() {
        return $x("//div[@class='subscribe-text']");
    }

    // locators for loginForm (login)
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

    SelenideElement errorPopUpWhenLogin() {
        return $x("//div[@class='cart-popup  ']");
    }

    public SelenideElement closeErrorPopUpBtn() {
        return $x("//a[@class='color close_popup']");
    }

    // Locators for login with Facebook
    SelenideElement facebookLoginBtn() {
        return $x("//form[@class='loginForm']//a[@class='social-auth__link']//img");
    }

    SelenideElement emailFieldForFB() {
        return $x("//input[@id='email']");
    }

    SelenideElement passFieldFB() {
        return $x("//input[@id='pass']");
    }

    SelenideElement privacyPolicyBtnFB() {
        return $x("//button[@name='__CONFIRM__']");
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
}
