package PKW;

import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.page;

public class CartAccount_page_Logic extends CartAccount_page {

    @Step("Login with email: {email} and password: {password}. CartAccount_page")
    public CartAddress_page_Logic signIn(String email, String password) {
        emailFieldInLoginForm().setValue(email);
        passwordFieldInLoginForm().setValue(password);
        loginButton().click();
        return page(CartAddress_page_Logic.class);
    }
}
