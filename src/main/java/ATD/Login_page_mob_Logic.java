package ATD;

import io.qameta.allure.Step;

import java.util.NoSuchElementException;

import static ATD.CommonMethods.getNameRouteFromJSVarInHTML;
import static ATD.CommonMethods.password;
import static com.codeborne.selenide.Selenide.Wait;
import static com.codeborne.selenide.Selenide.page;

public class Login_page_mob_Logic extends Login_page_mob {

    @Step("Sign in with mail {mail}. Login_page_mob")
    public Main_page_mob_Logic signIn(String mail) {
        emailField().setValue(mail);
        passField().setValue(password);
        loginBtn().click();
        Wait().until(WebDriver -> getNameRouteFromJSVarInHTML().equals("main"));
        return page(Main_page_mob_Logic.class);
    }

    @Step("Closing footer popup. Login_page_mob")
    public Login_page_mob_Logic closeFooterPopup() {
        try {
            footerPopup().click();
        } catch (NoSuchElementException e) {
            System.out.println("Footer popup doesn't appear");
        }
        return this;
    }

}
