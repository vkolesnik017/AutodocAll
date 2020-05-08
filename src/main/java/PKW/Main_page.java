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

}
