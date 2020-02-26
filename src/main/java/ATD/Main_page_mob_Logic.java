package ATD;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Main_page_mob_Logic extends Main_page_mob {

    public Main_page_mob_Logic closeFirstPopup() {
        firstPopup().click();
        Wait().until(WebDriver -> url().contains("apps.apple.com"));
        back();
        return this;
    }

    public Login_page_mob_Logic clickSignInInMenu() {
        menuBtn().click();
        signInBtnInMenu().click();
        return page(Login_page_mob_Logic.class);
    }

    public Profile_page_mob_Logic goToProfilePage() {
        menuBtn().click();
        signInBtnInMenu().click();
        return page(Profile_page_mob_Logic.class);
    }
}
