package PRF;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class CartAccount_page {

    SelenideElement emailFieldInLoginForm() {
        return $(byXpath("//form[contains(@class,'loginForm')]//input[@name='Email']"));
    }

    SelenideElement passwordFieldInLoginForm() {
        return $(byXpath("//form[contains(@class,'loginForm')]//input[@name='Password']"));
    }

    SelenideElement loginButton() {
        return $(byXpath("//a[@class='btn loginButton']"));
    }
}
