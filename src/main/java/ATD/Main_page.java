package ATD;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static ATD.CommonMethods.*;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

class Main_page {

    //Header
    SelenideElement loginBtnInHeader() {
        return $(byCssSelector(".sigin_btn>a"));
    }

    SelenideElement searchBar() {
        return $(byId("search"));
    }

    SelenideElement searchButton() {
        return $(byCssSelector("#search_form>a"));
    }

    SelenideElement logoutButton(){
        return  $(byCssSelector(".logout_but"));
    }

    // Login popup
    SelenideElement emailInputInLoginPopup() {
        return $(byXpath("//input[@name='Email']"));
    }

    SelenideElement passwordInputInLoginPopup() {
        return $(By.xpath("//input[@name='Password']"));
    }

    SelenideElement loginBtnInPopUp() {
        return $(byXpath("//*[@id='login_top']//*[@class='button']"));
    }

    SelenideElement forgotPasswordLink() {
        return $(byCssSelector(".versegen>span"));
    }

    SelenideElement registrationButtonInLoginPopup() {
        return $(byXpath("//form[@id='login_top']/p/a"));
    }

    SelenideElement closePopUpInvalidDataForLogin() {
        return $(byXpath("//*[@class='popup ']//*[contains(text(),'passen nicht zusammen!')]/..//a"));
    }

    // Password recovery popup
    SelenideElement emailFieldInPasswordRecoveryPopUp() {
        return $(byId("recovery-email"));
    }

    SelenideElement sendBtnInPasswordRecoveryPopUp() {
        return $(byXpath("//*[@class='rs_pass pass-recovery']/a[2]"));
    }

    SelenideElement closePopupMessageSentForChangePassword() {
        return $(byXpath("//*[@class='popup ']//*[contains(text(),'Um Ihr Passwort zu ändern')]/..//a"));
    }

    Main_page fillRequiredFieldsForRegistration(String firstName, String secondName, String mail) {
        $(By.xpath("//input[@id='form_rVorname']")).setValue(firstName);
        $(By.xpath("//input[@id='rName']")).setValue(secondName);
        $(By.xpath("//input[@id='email']")).setValue(mail);
        $(By.xpath("//a[@class='register_step']")).click();
        return this;
    }

    Profile_page fillPasswordFieldsAndClickRegistration() {
        $(By.xpath("//input[@name='new_pass']")).setValue(password);
        $(By.xpath("//input[@name='new_pass_confirm']")).setValue(password);
        $(By.xpath("//div[@class='button register_submit fast']/a")).click();
        return page(Profile_page.class);
    }

    Search_page useSearch(String searchArticle) {
        searchBar().setValue(searchArticle);
        searchButton().click();
        return page(Search_page.class);
    }

    Cart_page cartClick() {
        $(By.xpath("//a[@class='header-cart__link']")).click();
        return page(Cart_page.class);
    }

    // Footer
    SelenideElement footerForm() {
        return $(By.xpath("//footer"));
    }

    Contact_static_page clickContact() {
        $(By.xpath("//*[@class='footer__links']/div[3]/ul/li[3]/a")).click();
        return page(Contact_static_page.class);
    }

    Zahlung_static_page clickZahlung() {
        $(By.xpath("//*[@class='footer__links']/div[3]/ul/li[1]/a")).click();
        return page(Zahlung_static_page.class);
    }

    Datenschutz_page clickDatenschutz() {
        $(By.xpath("//*[@class='footer__links']/div[2]/ul/li[7]/a")).click();
        return page(Datenschutz_page.class);
    }

    Versand_static_page clickVersand() {
        $(By.xpath("//*[@class='footer__links']/div[3]/ul/li[2]/a")).click();
        return page(Versand_static_page.class);
    }

    Widerruf_static_page clickWiderruf() {
        $(By.xpath("//*[@class='footer__links']/div[2]/ul/li[6]/a")).click();
        return page(Widerruf_static_page.class);
    }

    Vacancies_static_page clickVacancies() {
        $(By.xpath("//*[@class='footer__links']/div[1]/ul/li[3]/a")).click();
        return page(Vacancies_static_page.class);
    }

    Impressum_static_page clickImpressum() {
        $(By.xpath("//*[@class='footer__links']/div[1]/ul/li[2]/a")).click();
        return page(Impressum_static_page.class);
    }

    Agb_static_page clickAgb() {
        $(By.xpath("//*[@class='footer__links']/div[2]/ul/li[5]/a")).click();
        return page(Agb_static_page.class);
    }

    MobileApp_static_page clickMobileApp() {
        $(By.xpath("//*[@class='footer__links']/div[1]/ul/li[8]/a")).click();
        return page(MobileApp_static_page.class);
    }

    Partnership_static_page clickPartnership() {
        $(By.xpath("//*[@class='footer__links']/div[1]/ul/li[6]/a")).click();
        return page(Partnership_static_page.class);
    }

    Sponsorship_static_page clickSponsorship() {
        $(By.xpath("//*[@class='footer__links']/div[1]/ul/li[5]/a")).click();
        return page(Sponsorship_static_page.class);
    }

    Austauschartikel_static_page clickAustauschartikel() {
        $(By.xpath("//*[@class='footer__links']/div[3]/ul/li[5]/a")).click();
        return page(Austauschartikel_static_page.class);
    }
}
