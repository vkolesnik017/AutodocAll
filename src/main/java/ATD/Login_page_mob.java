package ATD;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

class Login_page_mob {

    SelenideElement emailField() {
        return $(By.xpath("//form[@id='login_top']//input[@type='email']"));
    }

    SelenideElement passField() {
        return $(By.xpath("//form[@id='login_top']//input[@type='password']"));
    }

    SelenideElement loginBtn() {
        return $(By.id("btn-login-top"));
    }

    SelenideElement footerPopup() {
        return $(By.xpath("//div[@class='cps_app hidden position_bottom']/a[1]"));
    }

    SelenideElement loginBtnForFaceBook() {
        return $x("//div[@class='box active']//a[@class='social-auth__link']");
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

    SelenideElement popupOnLoginPage() {
        return $x("//div[@class='cps_app position_bottom']/a/img");
    }


}
