package PKW;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class Main_page {

    public SelenideElement searchBar() {
        return $(byId("search"));
    }

    public SelenideElement headerSearchSubmitBtn() {
        return $x("//a[contains(@class,'search_submit')]");
    }

    public SelenideElement cartIcon() {
        return $x("//a[@class='show_cart ga-click']");
    }

    private By tooltipsToSearch = byCssSelector(".autocomplete-suggestions>div");

    public SelenideElement tooltipToSearch() {
        return $(tooltipsToSearch);
    }

    public ElementsCollection tooltipsToSearch() {
        return $$(tooltipsToSearch);
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

    SelenideElement selectorErrorTooltipForMark() {
        return $x("//div[@id='selector-error-tooltip']/div");
    }

    //Selector
    protected SelenideElement makerNameSelected() {
        return $x("//select[@id='form_maker_id']//option[@selected]");
    }

    protected SelenideElement groupNameSelected() {
        return $x("//select[@id='form_model_id']//option[@selected]/..");
    }

    protected SelenideElement modelNameSelected() {
        return $x("//select[@id='form_model_id']//option[@selected]");
    }

    protected SelenideElement carNameSelected() {
        return $x("//select[@id='form_car_id']//option[@selected]");
    }


    // GDPR footer

    SelenideElement footerForm() {
        return $(By.xpath("//div[@class='footer-subscr']"));
    }

    public SelenideElement linkDatenschutzerklärungInFooter() {
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
        return $(byXpath("//*[@class='footer-main__menu']//a[contains(text(),'FAQ')]"));
    }

    SelenideElement footerUberUnsLink() {
        return $(byXpath("//*[@class='footer_menu_list5']/a"));
    }

    SelenideElement footerWiderrufLink() {
        return $(byXpath("//*[@class='footer-main__menu']//a[contains(text(),'Widerruf')]"));
    }

    SelenideElement footerZahlungLink() {
        return $(byXpath("//*[@class='footer_menu_list0']/a"));
    }

    SelenideElement btnNotFoundCarInSelector() {
        return $x("//form[@id='top-select']/a[@class='form-not-found-car ga-click']");
    }

    SelenideElement vinFieldPopupFromSelector() {
        return $x("//input[@id='form_vin']");
    }

    SelenideElement mailFieldPopupFromSelector() {
        return $x("//input[@id='form_email']");
    }

    SelenideElement checkboxPopupFromSelector() {
        return $x("//div[@class='accept_subscription_checkbox']/label");
    }

    SelenideElement btnSendPopupFromSelector() {
        return $x("//div[@class='not-found-car-popup']//button");
    }

    public SelenideElement privacyPolicyLinkPopupFromSelector() {
        return $x("//label[@id='privacy_policy_not_found_car']//a");
    }

    SelenideElement footerImpressumLink() {
        return $(byXpath("//*[@class='footer-main__menu']//a[contains(text(),'Impressum')]"));
    }

    SelenideElement footerAustauschartikelLink() {
        return $(byXpath("//*[@class='footer_menu_list3']/a"));
    }

    SelenideElement footerKontaktLink() {
        return $(byXpath("//*[@class='footer-main__menu']//a[contains(text(),'Kontakt')]"));
    }

    SelenideElement headerPromotionText() {
        return $x("//*[@class='promotion-header__text-big']");
    }

    SelenideElement headerPromotionTextEnd() {
        return $x("//*[@class='promotion-header__text-end']");
    }

    SelenideElement headerPromotionTime() {
        return $x("//*[@class='promotion-header__timer']");
    }

    SelenideElement headerPromotionContainer() {
        return $x("//*[@class='promotion-header__container']");
    }

    SelenideElement textInTheFooterBottom() {
        return $x("//*[@class='footer-bottom__copy']");
    }

    ElementsCollection promotionTextInTheFooter() {
        return $$x("//*[@class='promotion-text__text']");
    }

    ElementsCollection promotionTextMehrButton() {
        return $$x("//*[@class='promotion-text__text-more js-promotion-text-more']");
    }

    SelenideElement promotionTextMehrButtonSingle() {
        return $x("//*[@class='promotion-text__text']");
    }

    ElementsCollection popUpAfterHoverOnMehrButton() {
        return $$x("//*[@class='promotion-text__text-more-dropdown']");
    }

    SelenideElement ratingLineHeader() {
        return $x("//*[@class='header__top-line-rate']");
    }

    SelenideElement linkRatingLineHeader() {
        return $x("//*[@class='header__top-line-rate-link']");
    }

    SelenideElement ratingStarLineHeader() {
        return $x("//*[@class='header__top-line-rate-star']");
    }

    SelenideElement ratingCountLineHeader() {
        return $x("//*[@class='header__top-line-rate-count']");
    }

    SelenideElement ratingBewertungenHeader() {
        return $x("//*[@class='header__top-line-rate-text']");
    }

    SelenideElement appHeader() {
        return $x("//*[@class='header__top-line-app']");
    }

    SelenideElement appLinkHeader() {
        return $x("//*[@class='header__top-line-app']");
    }

    SelenideElement agbLinkHeader() {
        return $x("//*[@class='header__top-line-link']");
    }

    SelenideElement progressStarHeader() {
        return $x("//*[@class='progress']");
    }

    SelenideElement headerNavigationLine() {
        return $x("//*[@class='header__navigation']");
    }

    SelenideElement headerModelsMenu() {
        return $x("//*[@id='header_menu_models']//*[@class='menu-title-link ga-click']");
    }

    SelenideElement headerModelsDropList() {
        return $x("//*[@class='menu-title-link ga-click active']");
    }

    SelenideElement headerPartsMenu() {
        return $x("//*[@id='parts']//*[@class='menu-title-link ga-click']");
    }

    SelenideElement headerPartsDropList() {
        return $x("//*[@class='menu-title-link ga-click active']");
    }

    SelenideElement headerMenuReifen() {
        return $x("//*[@id='header_menu_tyres']//*[@class='menu-title-link ga-click']");
    }

    SelenideElement headerMenuAutolampen() {
        return $x("//a[@data-ga-action='autolampen']");
    }

    SelenideElement headerMenuMotorol() {
        return $x("//a[@data-ga-action='motoroil']");
    }

    SelenideElement headerMenuChemicals() {
        return $x("//a[@data-ga-action='chemicals']");
    }

    SelenideElement headerMenuAccessories() {
        return $x("//a[@data-ga-action='accessories']");
    }

    SelenideElement headerMenuInstruments() {
        return $x("//a[@data-ga-action='instruments']");
    }

    SelenideElement headerMenuHome() {
        return $x("//li[@class='home']");
    }

    SelenideElement emailInputForSubscribeFooter() {
        return $x("//input[@id='subscr_footer']");
    }

    SelenideElement checkboxForSubscribeFooter() {
        return $x("//input[@id='subscribe_accept_footer']");
    }

    SelenideElement yesButtonForSubscribeFooter() {
        return $x("//a[@id='news_yes_footer']");
    }

    SelenideElement successPopUpForSubscribeFooter() {
        return $x("//div[@id='popup_update']");
    }

    SelenideElement errorTooltipForSubscribeFooter() {
        return $x("//div[@class='wrong_footer']");
    }

    SelenideElement errorPopUpInTheSubscribeFooter() {
        return $x("//div[@class='popup_inner']");
    }

    SelenideElement footerVersandLink() {
        return $(byXpath("//*[@class='footer_menu_list1']/a"));
    }

    SelenideElement footerDateschutzLink() {
        return $(byXpath("//*[@class='footer_menu_list2']/a"));
    }

    SelenideElement basketCounterInHeader() {return $x("//span[@class='header__cart-count-qty count']");}
}
