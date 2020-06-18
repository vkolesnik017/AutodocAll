package Direkt;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class CartAccount_page {

    SelenideElement emailFieldInLoginForm() {
        return $(byXpath("//div[@id='block-form-auth']//input[@name='email']"));
    }

    SelenideElement passwordFieldInLoginForm() {
        return $(byXpath("//div[@id='block-form-auth']//input[@name='password']"));
    }

    SelenideElement loginButton() {
        return $(By.xpath("//div[@id='block-form-auth']//button[@class='cart-account__button']"));
    }
}
