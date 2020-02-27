package ATD;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

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
        return $(By.xpath("//div[@class='cps_app position_bottom']/a[1]"));
    }

}