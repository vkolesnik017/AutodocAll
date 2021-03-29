package ATD;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static ATD.CommonMethods.password;

import static ATD.CommonMethods.mailinatorMailRandom;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CartAccount_page_Logic extends CartAccount_page {


    @Step("Filling fields in registration form. CartAccount_page")
    public String fillingRegistrationFields(String qc) {
        String mail = qc + mailinatorMailRandom();
        registrationFormEmailInput().setValue(mail);
        registrationFormPasswordInput().setValue(password);
        registrationFormNextBtnClick();
        return mail;
    }

    @Step("Check registration from cart with {mail}. CartAccount_page")
    public CartAddress_page_Logic registrationFromCart(String mail) {
        registrationFormEmailInput().setValue(mail);
        registrationFormPasswordInput().setValue(password);
        registrationFormNextBtnClick();
        return page(CartAddress_page_Logic.class);
    }

    @Step("clicking Next button in registration form. CartAccount_page")
    public CartAddress_page registrationFormNextBtnClick() {
        registrationButton().click();
        return page(CartAddress_page.class);
    }

    @Step(":registration form. CartAccount_page")
    public CartAccount_page_Logic checkingDatenschutzerklarungLinkBehaviorRegistrationForm() {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(datenschutzerklarungLink(), "underline solid rgb(50, 103, 214)");
        return this;
    }

    @Step("Login with email: {email} and password: {password}. CartAccount_page")
    public CartAddress_page_Logic signIn(String email, String password) {
        emailFieldInLoginForm().shouldBe(visible).setValue(email);
        passwordFieldInLoginForm().shouldBe(visible).setValue(password);
        loginButton().shouldBe(visible).click();
        if (errorPopUpWhenLogin().isDisplayed()) {
            closeErrorPopUpBtn().shouldBe(visible).click();
            emailFieldInLoginForm().shouldBe(visible).setValue(email);
            passwordFieldInLoginForm().shouldBe(visible).setValue(password);
            loginButton().shouldBe(visible).click();
        }
        return page(CartAddress_page_Logic.class);
    }

    @Step("Check registration from cart and back to Main page. CartAccount_page")
    public Main_page_Logic registrationFromCartAndBackToMainPage(String mail) {
        CartAddress_page_Logic cartAddress_page = new CartAddress_page_Logic();
        registrationFormEmailInput().setValue(mail);
        registrationFormPasswordInput().setValue(password);
        registrationFormNextBtnClick();
        cartAddress_page.nextButton().shouldBe(Condition.visible);
        cartAddress_page.logoClick();
        return page(Main_page_Logic.class);
    }

    @Step("Password Recovery Request from cart account page. CartAccount_page")
    public CartAccount_page_Logic passwordRecoveryRequestFromCart(String mail) {
        forgotPasswordLink().click();
        emailFieldInPasswordRecoveryPopUp().setValue(mail);
        sendBtnInPasswordRecoveryPopUp().click();
        closePopupMessageSentForChangePassword().click();
        closePopupMessageSentForChangePassword().shouldBe(not(visible));
        return this;
    }

    @Step("Checks the pop-up with the error 'E-mail not found' when recovering the password. CartAccount_page")
    public CartAccount_page_Logic checkPopUpWithErrorWhenRecoveringPass(String mail, String expectedText) {
        forgotPasswordLink().click();
        emailFieldInPasswordRecoveryPopUp().setValue(mail);
        sendBtnInPasswordRecoveryPopUp().click();
        checkTextFromErrorPopUpForRegisteringAndRecovery(expectedText);
        closeErrorPopup();
        return this;
    }

    @Step("Login from facebook {mail}, {password}. CartAccount_page")
    public CartAddress_page_Logic signInFromFB(String mail, String pass) {
        facebookLoginBtn().click();
        switchTo().window(1);
        emailFieldForFB().setValue(mail);
        passFieldFB().setValue(pass);
        loginBtnFB().click();
        try {
            privacyPolicyBtnFB().shouldBe(visible);
            privacyPolicyBtnFB().click();
        } catch (Throwable e){
            System.out.println("Privacy policy is not visible");
            e.printStackTrace();
        }
        switchTo().window(0);
        return page(CartAddress_page_Logic.class);
    }

    @Step("Password recovery from popup Email already exists. CartAccount_page")
    public CartAccount_page_Logic recoveryPassFromPopUpEmailAlreadyExists(String mail) {
        errorPopUpForRegisteringAndRecovery().shouldBe(visible);
        passwordRecoveryLinc().click();
        passwordRecoveryPopUp().shouldBe(visible);
        emailFieldInPasswordRecoveryPopUp().setValue(mail);
        sendingEmailBtnForPasswordChange().click();
        closePopupMessageSentForChangePassword().click();
        return this;
    }

    @Step("Checks text {expectedText} from popup error when registration. CartAccount_page")
    public CartAccount_page_Logic checkTextFromErrorPopUpForRegisteringAndRecovery(String expectedText) {
        errorPopUpForRegisteringAndRecovery().shouldHave(text(expectedText));
        return this;
    }

    @Step("Checks text {expectedText} from popup error when login. CartAccount_page")
    public CartAccount_page_Logic checkTextFromErrorPopUpWhenLogin(String expectedText) {
        errorPopUpWhenLogin().shouldHave(text(expectedText));
        return this;
    }

    @Step("Close error popup. CartAccount_page")
    public CartAccount_page_Logic closeErrorPopup() {
        closeErrorPopUpBtn().click();
        return this;
    }

    @Step("Click btn return to basket. CartAccount_page")
    public Cart_page_Logic clickBtnReturnToBasket() {
        btnReturnToCart().shouldBe(visible).click();
        return page(Cart_page_Logic.class);
    }


    @Step("check and click text blocks in registration form. CartAccount_page")
    public CartAccount_page_Logic checkAndClickTextBlockInRegForm() {
        infoTextUnderCheckBockAtRegForm().shouldBe(visible).shouldHave(text("Jetzt abonnieren! Sparen Sie noch mehr!"));
        infoTextOfCheckBockAtRegForm().shouldBe(visible).shouldHave(text("Ja, ich möchte E-Mail-Newsletter mit Sonderangeboten erhalten. Ich kann den Newsletter jederzeit abbestellen.")).click();
        return this;
    }
}
