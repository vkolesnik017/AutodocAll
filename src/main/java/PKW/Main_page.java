package PKW;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Main_page {

    public SelenideElement cartIcon() {
        return $x("//a[@class='show_cart ga-click']");
    }

    SelenideElement loginBtnInHeader() {
        return $x("//div[@class='header__login']//span");
    }

    SelenideElement mailFieldLogin() {
        return $(By.id("login_top_email"));
    }

    SelenideElement passFieldLogin() {
        return $x("//input[@name='Password_fake']");
    }

    SelenideElement passFieldLoginForEntering() {
        return $x("//input[@name='Password']");
    }

    SelenideElement submitBtnLogin() {
        return $x("//div[@class='submit btn']");
    }

    SelenideElement loginCompletePopUp() {
        return $x("//div[@id='login_complete']");
    }

    SelenideElement btnCloseLoginCompletePopUp() {
        return $x("//div[@id='login_complete']//a[@class='close']");
    }

    SelenideElement registrationBtn() {
        return $x("//a[@id='reg_pop']");
    }

    SelenideElement mailFieldInRegistration() {
        return $x("//input[@id='email']");
    }

    SelenideElement passwordFieldInRegistration() {
        return $x("//input[@id='Password']");
    }

    SelenideElement confirmPassFieldInRegistration() {
        return $x("//input[@id='new_pass_confirm']");
    }

    SelenideElement registrationSubmitBtn() {
        return $x("//a[@class='register_submit']");
    }

    SelenideElement popupSuccessAuthorization() {
        return $x("//div[@id='login_complete']");
    }

    SelenideElement brandSelectorInVerticalCarSelector() {
        return $("#form_maker_id");
    }

    SelenideElement modelSelectorInVerticalCarSelector() {
        return $("#form_model_id");
    }

    SelenideElement typeSelectorInVerticalCarSelector() {
        return $("#form_car_id");
    }

    SelenideElement btnSearchOfSelector() {
        return $x("//div[@id='selector-wrapper']//div[@class='mainblock-search__button']/a");
    }


}
