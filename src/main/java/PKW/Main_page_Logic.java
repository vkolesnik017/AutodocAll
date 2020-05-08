package PKW;

import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;

public class Main_page_Logic extends Main_page{

    @Step("Cart clicking. Main_page")
    public Cart_page_Logic cartClick() {
        cartIcon().click();
        return page(PKW.Cart_page_Logic.class);
    }

    @Step("Login in header with mail {mail} and {password}. Main_page")
    public Main_page_Logic loginFromHeader(String mail, String pass) {
        loginBtnInHeader().click();
        mailFieldLogin().setValue(mail);
        passFieldLogin().click();
        passFieldLoginForEntering().setValue(pass);
        submitBtnLogin().click();
        loginCompletePopUp().shouldBe(visible);
        btnCloseLoginCompletePopUp().click();
        return page(Main_page_Logic.class);
    }
}
