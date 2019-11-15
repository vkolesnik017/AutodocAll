package ATD;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static ATD.CommonMethods.*;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class Main_page {

    //Header
    public SelenideElement loginBtnInHeader() {
        return $(byCssSelector(".sigin_btn>a"));
    }

    private SelenideElement searchBar() {
        return $(byId("search"));
    }

    private SelenideElement searchButton() {
        return $(byCssSelector("#search_form>a"));
    }

    public SelenideElement logoutButton() {
        return $(byCssSelector(".logout_but"));
    }

    SelenideElement cartIcon() {
        return $(byCssSelector(".header-cart__count"));
    }

    @Step
    Cart_page cartClick() {
        cartIcon().click();
        return page(Cart_page.class);
    }

    @Step("Use search with: {searchArticle}")
    public Search_page useSearch(String searchArticle) {
        searchBar().setValue(searchArticle);
        searchButton().click();
        return page(Search_page.class);
    }

    // Menu in header
    SelenideElement tiresTab() {
        return $(byCssSelector("[data-ga-action='23208']"));
    }

    @Step
    public Tyres_page clickTiresTab() {
        tiresTab().click();
        return page(Tyres_page.class);
    }

    // Login popup
    public SelenideElement emailInputInLoginPopup() {
        return $(byXpath("//input[@name='Email']"));
    }

    public SelenideElement passwordInputInLoginPopup() {
        return $(By.xpath("//input[@name='Password']"));
    }

    public SelenideElement loginBtnInPopUp() {
        return $(byXpath("//*[@id='login_top']//*[@class='button']"));
    }

    public SelenideElement forgotPasswordLink() {
        return $(byCssSelector(".versegen>span"));
    }

    public SelenideElement registrationButtonInLoginPopup() {
        return $(byXpath("//form[@id='login_top']/p/a"));
    }

    public SelenideElement closePopUpInvalidDataForLogin() {
        return $(byXpath("//*[@class='popup ']//*[contains(text(),'passen nicht zusammen!')]/..//a"));
    }

    // Password recovery popup
    public SelenideElement emailFieldInPasswordRecoveryPopUp() {
        return $(byId("recovery-email"));
    }

    public SelenideElement sendBtnInPasswordRecoveryPopUp() {
        return $(byXpath("//*[@class='rs_pass pass-recovery']/a[2]"));
    }

    public SelenideElement closePopupMessageSentForChangePassword() {
        return $(byXpath("//*[@class='popup ']//*[contains(text(),'Um Ihr Passwort zu ändern')]/..//a"));
    }

    public Main_page fillRequiredFieldsForRegistration(String firstName, String secondName, String mail) { //TODO вынести локаторы
        $(By.xpath("//input[@id='form_rVorname']")).setValue(firstName);
        $(By.xpath("//input[@id='rName']")).setValue(secondName);
        $(By.xpath("//input[@id='email']")).setValue(mail);
        $(By.xpath("//a[@class='register_step']")).click();
        return this;
    }

    public Profile_page fillPasswordFieldsAndClickRegistration() { //TODO вынести локаторы
        $(By.xpath("//input[@name='new_pass']")).setValue(password);
        $(By.xpath("//input[@name='new_pass_confirm']")).setValue(password);
        $(By.xpath("//div[@class='button register_submit fast']/a")).click();
        return page(Profile_page.class);
    }

    // Footer
    public SelenideElement footerForm() {
        return $(By.xpath("//div[@id='footer']"));
    }
    public SelenideElement appGoogleButton() {
        return $(byXpath("//div[@class='footer__app-icon']/span[1]/img"));
    }
    public SelenideElement appAppleButton() {
        return $(byXpath("//div[@class='footer__app-icon']/span[1]/img"));
    }

    //ÜBER AUTODOC
    public About_us_page clickAboutUs() {
        $(By.xpath("//*[@class='footer__links']/div[1]/ul/li[1]/a")).click();
        return page(About_us_page.class);
    }

    public Impressum_static_page clickImpressum() {
        $(By.xpath("//*[@class='footer__links']/div[1]/ul/li[2]/a")).click();
        return page(Impressum_static_page.class);
    }

    public Vacancies_static_page clickVacancies() {
        $(By.xpath("//*[@class='footer__links']/div[1]/ul/li[3]/a")).click();
        return page(Vacancies_static_page.class);
    }

    public Bonusprogramm_page clickBonusprogramm() {
        $(By.xpath("//*[@class='footer__links']/div[1]/ul/li[4]/a")).click();
        return page(Bonusprogramm_page.class);
    }

    public Sponsorship_static_page clickSponsorship() {
        $(By.xpath("//*[@class='footer__links']/div[1]/ul/li[5]/a")).click();
        return page(Sponsorship_static_page.class);
    }

    public Partnership_static_page clickPartnership() {
        $(By.xpath("//*[@class='footer__links']/div[1]/ul/li[6]/a")).click();
        return page(Partnership_static_page.class);
    }

    public Presse_page clickPresse() {
        $(By.xpath("//*[@class='footer__links']/div[1]/ul/li[7]/a")).click();
        return page(Presse_page.class);
    }

    public MobileApp_static_page clickMobileApp() {
        $(By.xpath("//*[@class='footer__links']/div[1]/ul/li[8]/a")).click();
        return page(MobileApp_static_page.class);
    }

    //HILFE & SUPPORT
    public AutodocClub_page clickAutodocClub() {
        $(By.xpath("//*[@class='footer__links']/div[2]/ul/li[1]/a")).click();
        return page(AutodocClub_page.class);
    }

    public Blog_page clickBlog() {
        $(By.xpath("//*[@class='footer__links']/div[2]/ul/li[2]/a")).click();
        return page(Blog_page.class);
    }

    public VideoTutorials_page clickVideoTutorials() {
        $(By.xpath("//*[@class='footer__links']/div[2]/ul/li[3]/a")).click();
        return page(VideoTutorials_page.class);
    }

    public Altolentsorgung_page clickAltolentsorgung() {
        $(By.xpath("//*[@class='footer__links']/div[2]/ul/li[4]/a")).click();
        return page(Altolentsorgung_page.class);
    }

    public Agb_static_page clickAgb() {
        $(By.xpath("//*[@class='footer__links']/div[2]/ul/li[5]/a")).click();
        return page(Agb_static_page.class);
    }

    public Widerruf_static_page clickWiderruf() {
        $(By.xpath("//*[@class='footer__links']/div[2]/ul/li[6]/a")).click();
        return page(Widerruf_static_page.class);
    }

    public Datenschutz_page clickDatenschutz() {
        $(By.xpath("//*[@class='footer__links']/div[2]/ul/li[7]/a")).click();
        return page(Datenschutz_page.class);
    }

    //KUNDENSERVICE
    public Zahlung_static_page clickZahlung() {
        $(By.xpath("//*[@class='footer__links']/div[3]/ul/li[1]/a")).click();
        return page(Zahlung_static_page.class);
    }

    public Versand_static_page clickVersand() {
        $(By.xpath("//*[@class='footer__links']/div[3]/ul/li[2]/a")).click();
        return page(Versand_static_page.class);
    }

    public Contact_static_page clickContact() {
        $(By.xpath("//*[@class='footer__links']/div[3]/ul/li[3]/a")).click();
        return page(Contact_static_page.class);
    }

    public Retouren_page clickRetouren() {
        $(By.xpath("//*[@class='footer__links']/div[3]/ul/li[4]/a")).click();
        return page(Retouren_page.class);
    }

    public Austauschartikel_static_page clickAustauschartikel() {
        $(By.xpath("//*[@class='footer__links']/div[3]/ul/li[5]/a")).click();
        return page(Austauschartikel_static_page.class);
    }

    //TOP PRODUKTE

    public Beleuchtung_page clickBeleuchtung() {
        $(By.xpath("//*[@class='footer__links']/div[4]/ul/li[1]/a")).click();
        return page(Beleuchtung_page.class);
    }
    public Stobdampfer_page clickStobdampfer() {
        $(By.xpath("//*[@class='footer__links']/div[4]/ul/li[2]/a")).click();
        return page(Stobdampfer_page.class);
    }
    public Kupplungssatz_page clickKupplungssatz() {
        $(By.xpath("//*[@class='footer__links']/div[4]/ul/li[3]/a")).click();
        return page(Kupplungssatz_page.class);
    }
    public Querlenker_page clickQuerlenker() {
        $(By.xpath("//*[@class='footer__links']/div[4]/ul/li[4]/a")).click();
        return page(Querlenker_page.class);
    }
    public Radlager_page clickRadlager() {
        $(By.xpath("//*[@class='footer__links']/div[4]/ul/li[5]/a")).click();
        return page(Radlager_page.class);
    }
    public Autopflege_page clickAutopflege() {
        $(By.xpath("//*[@class='footer__links']/div[4]/ul/li[6]/a")).click();
        return page(Autopflege_page.class);
    }
    public Ersatzteile_page clickSucheNachAutomodelle() {
        $(By.xpath("//*[@class='footer__links']/div[4]/ul/li[7]/a")).click();
        return page(Ersatzteile_page.class);
    }
    public ErsatzteileModels_page clickNachHerstellerEinkaufen() {
        $(By.xpath("//*[@class='footer__links']/div[4]/ul/li[8]/a")).click();
        return page(ErsatzteileModels_page.class);
    }
    public ErsatzteileCars_page clickNachModellEinkaufen() {
        $(By.xpath("//*[@class='footer__links']/div[4]/ul/li[9]/a")).click();
        return page(ErsatzteileCars_page.class);
    }

}
