package ATD;

import io.qameta.allure.Step;
import java.util.NoSuchElementException;
import static ATD.CommonMethods.getNameRouteFromJSVarInHTML;
import static ATD.CommonMethods.password;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

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

    @Step("Login from facebook {mail}, {password}. Login_page_mob")
    public Profile_page_mob_Logic signInFromFB(String mail, String pass) {
        loginBtnForFaceBook().click();
        switchTo().window(1);
        emailFieldForFB().setValue(mail);
        passFieldFB().setValue(pass);
        loginBtnFB().click();
        switchTo().window(0);
        return page(Profile_page_mob_Logic.class);
    }

    @Step("Closing popup after transition on Login page. Login_page_mob")
    public Login_page_mob_Logic closePopupAfterTransitionOnLoginPageMob(String Url1, String Url2) {
        sleep(3000);
        if (popupOnLoginPage().isDisplayed()) {
            popupOnLoginPage().click(1,1);
            Wait().until(WebDriver -> url().contains(Url1));
            back();
            Wait().until(WebDriver -> url().contains(Url2));
        } else {
            System.out.println("Popup is not displayed");
        }
        return this;
    }

}
