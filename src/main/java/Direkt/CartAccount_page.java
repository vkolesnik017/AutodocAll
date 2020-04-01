package Direkt;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class CartAccount_page {

    SelenideElement emailFieldInLoginForm() {
        return $(byXpath("//*[@class='loginForm']//*[@id='form_Email']"));
    }

    SelenideElement passwordFieldInLoginForm() {
        return $(byXpath("//*[@class='loginForm']//*[@id='form_Password']"));
    }

    SelenideElement loginButton() {
        return $(byCssSelector(".login"));
    }
}
