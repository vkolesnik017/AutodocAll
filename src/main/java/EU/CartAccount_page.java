package EU;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CartAccount_page {

    SelenideElement emailFieldInLoginForm() {
        return $(byXpath("//div[@class='login_lt']//input[@name='Email']"));
    }

    SelenideElement passwordFieldInLoginForm() {
        return $(byXpath("//div[@class='login_lt']//input[@name='Password']"));
    }

    SelenideElement loginButton() {
        return $x("//div[@class='login_lt']//button[@name='button']");
    }
}
