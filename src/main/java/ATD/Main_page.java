package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;


public class Main_page {

    //Header
    public SelenideElement logoInHeader() {
        return $(".header__logo-main");
    }

    public SelenideElement loginBtnInHeader() {
        return $(byCssSelector(".sigin_btn>a"));
    }

    // When user signIn
    SelenideElement profileBtn() {
        return $(By.xpath("//a[@class='header-i header-i--user']"));
    }

    SelenideElement mailFieldLogin() {
        return $(By.id("login_top_email"));
    }

    SelenideElement passFieldLogin() {
        return $(By.xpath("//input[@type='password']"));
    }

    SelenideElement submitBtnLogin() {
        return $(By.xpath("//a[@class='enter submit']"));
    }

    public SelenideElement searchBar() {
        return $(byId("search"));
    }

    private By tooltipsToSearch = byCssSelector(".autocomplete-suggestions>div");

    public SelenideElement tooltipToSearch() {
        return $(tooltipsToSearch);
    }

    public ElementsCollection tooltipsToSearch() {
        return $$(tooltipsToSearch);
    }

    public SelenideElement infoIconForSearch() {
        return $(".header-i.header-i--info.inf");
    }

    public SelenideElement infoPopupForSearch() {
        return $(".ex_popup.ex_popup_shown");
    }

    public SelenideElement searchButton() {
        return $(byCssSelector("#search_form>a"));
    }

    public SelenideElement logoutButton() {
        return $(byCssSelector(".logout_but"));
    }

    public SelenideElement cartIcon() {
        return $(byCssSelector(".header-cart__count"));
    }

    public SelenideElement totalPriceInCart() {
        return $(".header-cart__amount");
    }

    public SelenideElement numberBasket() {
        return $(byCssSelector(".code"));
    }

    public SelenideElement promotionBanner() {
        return $(".promotion_header_image");
    }

    public SelenideElement clockInPromotionBanner() {
        return $(".flip-clock-wrapper");
    }

    public SelenideElement textAboveClockInPromotionBanner() {
        return $(".promotion_header_text_reminder");
    }

    public SelenideElement closeBtnOfPromotionBanner() {
        return $(".close_pop_up ");
    }

    public SelenideElement menuCatalogInHeader() {
        return $(".menu-catalog>a");
    }

    public SelenideElement listCategoriesOfCatalog() {
        return $(byXpath("//*[@class='menu-category__first-lvl']//a"));
    }

    SelenideElement numberOfProductInCart() {
        return $x("//div[@class='header-cart__name ga-click']/span[1]");
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

    public SelenideElement closeBtnOfLoginPopup() {
        return $(".close_log_on");
    }

    // Registration popup
    SelenideElement datenschutzerklarungLinkInRegPopup() {
        return $(By.cssSelector("#privacy_policy_header_modal>a"));
    }

    SelenideElement datenschutzerklarungTextInRegPopup() {
        return $(By.cssSelector("#privacy_policy_header_modal"));
    }

    SelenideElement vornameInRegForm() {
        return $(By.xpath("//input[@id='form_rVorname']"));
    }

    SelenideElement nameInRegForm() {
        return $(By.xpath("//input[@id='rName']"));
    }

    SelenideElement mailInRegForm() {
        return $(By.xpath("//input[@id='email']"));
    }

    SelenideElement checkboxInRegForm() {
        return $(By.xpath("//label[@for='isSubscribe_header_modal']"));
    }

    SelenideElement passStepInRegForm() {
        return $(By.xpath("//a[@class='register_step']"));
    }

    SelenideElement newPassRegForm() {
        return $(By.xpath("//input[@name='new_pass']"));
    }

    SelenideElement newPassConfirmRegForm() {
        return $(By.xpath("//input[@name='new_pass_confirm']"));
    }

    SelenideElement registrationBtnRegForm() {
        return $(By.xpath("//div[@class='button register_submit fast']/a"));
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

    // Footer
    SelenideElement footerForm() {
        return $(By.xpath("//div[@id='footer']"));
    }

    SelenideElement appGoogleButton() {
        return $(byXpath("//div[@class='footer__app-icon']/span[1]/img"));
    }

    SelenideElement appAppleButton() {
        return $(byXpath("//div[@class='footer__app-icon']/span[1]/img"));
    }

    SelenideElement subscriptionButton() {
        return $(byXpath("//button[@id='news_yes_footer']"));
    }

    SelenideElement subscriptionMailField() {
        return $(byXpath("//input[@id='subscr_footer']"));
    }

    SelenideElement subscriptionErrTooltip() {
        return $(byXpath("//div[@class='wrong_footer']/p"));
    }

    SelenideElement subscriptionMailCheckbox() {
        return $(byXpath("//div[@class='check']/label/span"));
    }

    SelenideElement subscriptionSuccessPopup() {
        return $(byXpath("//div[@id='news_subscribe']//h3"));
    }

    SelenideElement subscriptionErrPopup() {
        return $(byXpath("//div[@class='txt error_msg']"));
    }

    SelenideElement subscriptionPopupClose() {
        return $(byXpath("//div[@class='popup ']//*[@class='close']/span[2]"));
    }

    SelenideElement techAllianceBlock() {
        return $x("//div[@class='footer__bottom']/div[@class='footer__bottom-first']");
    }

    SelenideElement workTimeBlock() {
        return $x("//div[@class='footer__bottom']/div[@class='footer__bottom-center']");
    }

    SelenideElement copyrightBlock() {
        return $x("//div[@class='footer__bottom']//div[@class='copyright']");
    }

    SelenideElement facebookButton() {
        return $x("//ul[@class='social-inline']/li[1]/span");
    }

    SelenideElement youtubeButton() {
        return $x("//ul[@class='social-inline']/li[2]/span");
    }

    SelenideElement instagramButton() {
        return $x("//ul[@class='social-inline']/li[3]/span");
    }

    SelenideElement datenschutzLinkInSubscribeBlock() {
        return $(By.cssSelector("#privacy_policy_footer>span"));
    }

    //KUNDENSERVICE INTERNATIONAL

    SelenideElement languageSelector() {
        return $(By.cssSelector(".footer-language__select"));
    }

    SelenideElement dropdownCountry() {
        return $x("//div[@class='footer-language__country-list mCustomScrollbar _mCS_1'] [@style='visibility: visible;']");
    }

    // Vertical car selector popup

    public SelenideElement brandSelectorInVerticalCarSelector() {
        return $("#form_maker_id");
    }

    SelenideElement arrowInBrandSelectorVerticalCar() {
        return $x("//div[@id='maker-select']/span[@class='arrow']");
    }

    SelenideElement modelSelectorInVerticalCarSelector() {
        return $("#form_model_id");
    }

    SelenideElement typeSelectorInVerticalCarSelector() {
        return $("#form_car_id");
    }

    SelenideElement searchBtnInVerticalSelector() {
        return $(".search_button");
    }

    public SelenideElement errorTooltipOfBrandSelector() {
        return $(byId("selector-error-tooltip"));
    }

    public SelenideElement errorToolTipOfModelSelector() {
        return $(byId("selector-error-tooltip-model"));
    }

    public SelenideElement errorToolTipOfTypeSelector() {
        return $(byId("selector-error-tooltip-car"));
    }

    SelenideElement resetBtnInVerticalCarSelector() {
        return $(byId("reset_selector_form"));
    }

    public SelenideElement tooltipInCarSelectorCloseBtn() {
        return $(".tooltiptext-close");
    }

    SelenideElement hiddenVerticalSelector() {
        return $(".catalog-title__change-car");
    }

    //Selector kba
    SelenideElement firstFieldKBA() {
        return $(byId("kba1"));
    }

    SelenideElement secondFieldKBA() {
        return $(byId("kba2"));
    }

    SelenideElement selectorKbaBtn() {
        return $(".kba_submit");
    }

    SelenideElement linkInfoKba() {
        return $(".block-select-kba__info>a");
    }

    SelenideElement kbaPopup() {
        return $(".kba_popup_example");
    }

    //Car selector popup
    public SelenideElement headingInCarSelectorPopup() {
        return $(".popup-kba-error>p");
    }

    public SelenideElement blockWithDropdownsOfChooseCarInCarSelectorPopup() {
        return $x("//form[@id='top-select-popup']//*[@id='selector-wrapper']");
    }

    public SelenideElement headingCarInCarSelectorPopup() {
        return $x("//form[@id='top-select-popup']//*[@class='block-select-kba__title']/span");
    }

    public SelenideElement blockWithKbaFieldsInCarSelectorPopup() {
        return $x("//form[@id='top-select-popup']//div[@class='block-select-kba__row']");
    }

    public SelenideElement linkForTooltipInCarSelectorPopup() {
        return $("#kba_tooltip");
    }

    public SelenideElement tooltipInCarSelectorPopup() {
        return $(".kba_tooltip>img");
    }

    public SelenideElement suchenCarBtnInCarSelectorPopup() {
        return $x("//form[@id='top-select-popup']//a[@class='submit search_button']");
    }

    public SelenideElement brandSelectorInCarSelectorPopup() {
        return $x("//form[@id='top-select-popup']//select[@id='form_maker_id']");
    }

    SelenideElement modelSelectorInCarSelectorPopup() {
        return $x("//form[@id='top-select-popup']//select[@id='form_model_id']");
    }

    public SelenideElement errorTooltipForChooseBrandInCarSelectorPopup() {
        return $x("//form[@id='top-select-popup']//div[@id='selector-error-tooltip']");
    }

    public SelenideElement errorTooltipForChooseModelInCarSelectorPopup() {
        return $x("//form[@id='top-select-popup']//div[@id='selector-error-tooltip-model']");
    }

    public SelenideElement errorTooltipForChooseTypeInCarSelectorPopup() {
        return $x("//form[@id='top-select-popup']//div[@id='selector-error-tooltip-car']");
    }

    SelenideElement resetCarBtnInCarSelectorPopup() {
        return $x("//form[@id='top-select-popup']//a[@id='reset_selector_form']");
    }

    public SelenideElement closeBtnInCarSelectorPopup() {
        return $(".popup-kba-error>a");
    }


}
