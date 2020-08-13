package PKW;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Main_page {

    public SelenideElement cartIcon() {
        return $x("//a[@class='show_cart ga-click']");
    }

    SelenideElement loginBtnInHeader() {
        return $x("//div[@class='header__login']//span");
    }

    SelenideElement mailFieldLogin() {
        return $(By.id("login_top_email"));
    }

    SelenideElement passFieldLogin() {
        return $x("//input[@name='Password_fake']");
    }

    SelenideElement passFieldLoginForEntering() {
        return $x("//input[@name='Password']");
    }

    SelenideElement submitBtnLogin() {
        return $x("//div[@class='submit btn']");
    }

    SelenideElement loginCompletePopUp() {
        return $x("//div[@id='login_complete']");
    }

    SelenideElement btnCloseLoginCompletePopUp() {
        return $x("//div[@id='login_complete']//a[@class='close']");
    }

    SelenideElement registrationBtn() {
        return $x("//a[@id='reg_pop']");
    }

    SelenideElement mailFieldInRegistration() {
        return $x("//input[@id='email']");
    }

    SelenideElement passwordFieldInRegistration() {
        return $x("//input[@id='Password']");
    }

    SelenideElement confirmPassFieldInRegistration() {
        return $x("//input[@id='new_pass_confirm']");
    }

    SelenideElement registrationSubmitBtn() {
        return $x("//a[@class='register_submit']");
    }

    SelenideElement popupSuccessAuthorization() {
        return $x("//div[@id='login_complete']");
    }

    SelenideElement brandSelectorInVerticalCarSelector() {
        return $("#form_maker_id");
    }

    SelenideElement modelSelectorInVerticalCarSelector() {
        return $("#form_model_id");
    }

    SelenideElement typeSelectorInVerticalCarSelector() {
        return $("#form_car_id");
    }

    SelenideElement btnSearchOfSelector() {
        return $x("//div[@id='selector-wrapper']//div[@class='mainblock-search__button']/a");
    }


    // GDPR footer

    SelenideElement footerForm() {
        return $(By.xpath("//div[@class='footer-subscr']"));
    }

    SelenideElement linkDatenschutzerklärungInFooter() {
        return $x("//label[@id='privacy_policy_footer']/a");
    }

    SelenideElement datenschutzerklarungLinkInRegPopup() {
        return $(By.cssSelector("#privacy_policy_header_modal>a"));
    }

    SelenideElement subscriptionMailField() {
        return $(byXpath("//input[@id='subscr_footer']"));
    }

    SelenideElement subscriptionButton() {
        return $(byXpath("//a[@id='news_yes_footer']"));
    }

    SelenideElement subscriptionErrPopup() {
        return $(byXpath("//div[@class='txt']"));
    }

    SelenideElement subscriptionPopupClose() {
        return $(byXpath("//div[@class='popup ']//*[@class='close']/span[2]"));
    }

    SelenideElement subscriptionMailCheckbox() {
        return $(byXpath("//div[@class='footer-subscr__foremail']/input[@id='subscribe_accept_footer']"));
    }

    SelenideElement subscriptionSuccessPopup() {
        return $(byXpath("//div[@id='popup_update']//div[@class='txt']"));
    }

    SelenideElement btnToPRFromPopupAuthorizationSuccessfully() {
        return $x("//div[@id='login_complete']//a[@class='acc']");
    }

    SelenideElement footerFaqLink() {
        return $(byXpath("//*[@class='footer_menu_list4']/a"));
    }

    SelenideElement footerUberUnsLink() {
        return $(byXpath("//*[@class='footer_menu_list5']/a"));
    }

    SelenideElement footerWiderrufLink() {
        return $(byXpath("//*[@class='footer_menu_list7']/a"));
    }
    SelenideElement footerZahlungLink() {
        return $(byXpath("//*[@class='footer_menu_list0']/a"));
    }

    SelenideElement footerImpressumLink() {
        return $(byXpath("//*[@class='footer_menu_list6']/a"));
    }
}
