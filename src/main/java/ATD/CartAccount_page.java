package ATD;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

class CartAccount_page {

    SelenideElement registrationFormEmailInput() {
        return $(By.xpath("//form[@class='registerForm']//input[@name='Email']"));
    }

    SelenideElement registrationFormPasswordInput() {
        return $(By.xpath("//form[@class='registerForm']//input[@name='Password']"));
    }

    SelenideElement forgotPasswordLink() {
        return $(byCssSelector(".signin-user-box-form__recovery>a"));
    }

    // password recovery popup
    SelenideElement emailFieldInPasswordRecoveryPopUp() {
        return $(byId("recovery-email"));
    }

    SelenideElement sendBtnInPasswordRecoveryPopUp() {
        return $(byCssSelector(".submit.color"));
    }

    SelenideElement closePopupMessageSentForChangePassword() {
        return $(byXpath("//*[@class='cart-popup']//*[contains(text(),'Um Ihr Passwort zu ändern')]/../..//a"));
    }

    CartAddress_page registrationFormNextButtonClick() {
        $(By.xpath("//form[@class='registerForm']//div[@class='button-continue']/a")).click();
        return page(CartAddress_page.class);
    }
}
