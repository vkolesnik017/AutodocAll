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

    public SelenideElement logoutButton(){
        return  $(byCssSelector(".logout_but"));
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
    SelenideElement footerForm() {
        return $(By.xpath("//footer"));
    }

    public Contact_static_page clickContact() {
        $(By.xpath("//*[@class='footer__links']/div[3]/ul/li[3]/a")).click();
        return page(Contact_static_page.class);
    }

    public Zahlung_static_page clickZahlung() {
        $(By.xpath("//*[@class='footer__links']/div[3]/ul/li[1]/a")).click();
        return page(Zahlung_static_page.class);
    }

    public Datenschutz_page clickDatenschutz() {
        $(By.xpath("//*[@class='footer__links']/div[2]/ul/li[7]/a")).click();
        return page(Datenschutz_page.class);
    }

    public Versand_static_page clickVersand() {
        $(By.xpath("//*[@class='footer__links']/div[3]/ul/li[2]/a")).click();
        return page(Versand_static_page.class);
    }

    public Widerruf_static_page clickWiderruf() {
        $(By.xpath("//*[@class='footer__links']/div[2]/ul/li[6]/a")).click();
        return page(Widerruf_static_page.class);
    }

    public Vacancies_static_page clickVacancies() {
        $(By.xpath("//*[@class='footer__links']/div[1]/ul/li[3]/a")).click();
        return page(Vacancies_static_page.class);
    }

    public Impressum_static_page clickImpressum() {
        $(By.xpath("//*[@class='footer__links']/div[1]/ul/li[2]/a")).click();
        return page(Impressum_static_page.class);
    }

    public Agb_static_page clickAgb() {
        $(By.xpath("//*[@class='footer__links']/div[2]/ul/li[5]/a")).click();
        return page(Agb_static_page.class);
    }

    public MobileApp_static_page clickMobileApp() {
        $(By.xpath("//*[@class='footer__links']/div[1]/ul/li[8]/a")).click();
        return page(MobileApp_static_page.class);
    }

    public Partnership_static_page clickPartnership() {
        $(By.xpath("//*[@class='footer__links']/div[1]/ul/li[6]/a")).click();
        return page(Partnership_static_page.class);
    }

    public Sponsorship_static_page clickSponsorship() {
        $(By.xpath("//*[@class='footer__links']/div[1]/ul/li[5]/a")).click();
        return page(Sponsorship_static_page.class);
    }

    public Austauschartikel_static_page clickAustauschartikel() {
        $(By.xpath("//*[@class='footer__links']/div[3]/ul/li[5]/a")).click();
        return page(Austauschartikel_static_page.class);
    }
}
