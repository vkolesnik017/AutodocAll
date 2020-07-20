package ATD;

import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Main_page_mob_Logic extends Main_page_mob {

    @Step("Cart clicking. Main_page_mob")
    public Cart_page_mob_Logic cartClick() {
        cartIcon().click();
        return page(Cart_page_mob_Logic.class);
    }

    @Step("Closing first appearing popup. Main_page_mob")
    public Main_page_mob_Logic closeFirstPopup() {
        firstPopup().click();
        switchTo().window(1);
        Wait().until(WebDriver -> url().contains("apps.apple.com"));
        switchTo().window(0);
        return this;
    }

    @Step("Closing first appearing popup after transition on mob site. Main_page_mob")
    public Main_page_mob_Logic closeFirstPopupAfterTransitionOnMob(String Url1, String Url2) {
        firstPopup().click();
        Wait().until(WebDriver -> url().contains(Url1));
        back();
        Wait().until(WebDriver -> url().contains(Url2));
        return this;
    }


    @Step("Clicking signIn in menu. Main_page_mob")
    public Login_page_mob_Logic clickSignInInMenu() {
        menuBtn().click();
        sleep(2000);
        signInBtnInMenu().click();
        return page(Login_page_mob_Logic.class);
    }

    @Step("Clicking menu button and go to profile page. Main_page_mob")
    public Profile_page_mob_Logic goToProfilePage() {
        menuBtn().click();
        sleep(2000);
        signInBtnInMenu().click();
        return page(Profile_page_mob_Logic.class);
    }
}
