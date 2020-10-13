package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
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

    SelenideElement loginBtnFromFaceBook() {
        return $x("//a[@class='social-auth__link']//img");
    }

    SelenideElement emailFieldForFB() {
        return $x("//input[@id='email']");
    }

    SelenideElement passFieldFB() {
        return $x("//input[@id='pass']");
    }

    SelenideElement loginBtnFB() {
        return $x("//input[@name='login']");
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

    public SelenideElement headerSearchSubmitBtn() {
        return $x("//a[contains(@class,'header-search')]");
    }

    private By tooltipsToSearch = byCssSelector(".autocomplete-suggestions>div");

    public SelenideElement tooltipToSearch() {
        return $(tooltipsToSearch);
    }

    public ElementsCollection tooltipsToSearch() {
        return $$(tooltipsToSearch);
    }

    public SelenideElement counterQuantityProductsInSearch() {
        return $x("//div[@class='autocomplete-suggestions']//span[@class='right']");
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

    public SelenideElement discountBox() {
        return $(By.id("discount-box"));
    }

    public SelenideElement discountBoxClock() {
        return $(By.id("js-discount-box-timer"));
    }

    SelenideElement headerTop() {
        return $(By.cssSelector(".header__top"));
    }

    SelenideElement headerGarageIcon() {
        return $x("//div[@class='header-garage js-header-garage']");
    }

    SelenideElement headerGarageTooltip() {
        return $x("//div[@class='header-garage__notlogged  header-garage-drop active']");
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

    public SelenideElement closePopUPInvalidEmailForLogin() {
        return $x("//*[contains(text(),'Ihre E-mail wurde nicht gefunden')]/../..//a[@class='close']");
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


    // Menu in header
    SelenideElement LkwCategory() {
        return $x("//a[@class='header-i header-i--truck ga-click']");
    }

    SelenideElement motoCategory() {
        return $x("//a[@class='header-i header-i--moto ga-click']");
    }

    SelenideElement tiresCategory() {
        return $x("//a[@class='header-i header-i--tyre ga-click']");
    }

    SelenideElement instrumentsCategory() {
        return $x("//a[@class='header-i header-i--tool ga-click']");
    }

    SelenideElement accessoriesCategory() {
        return $x("//a[@class='header-i header-i--accessories ga-click']");
    }

    SelenideElement engineOilCategory() {
        return $x("//a[@class='header-i header-i--oil ga-click']");
    }

    SelenideElement filtersCategory() {
        return $x("//a[@class='header-i header-i--filter ga-click']");
    }

    SelenideElement brakeSystemCategory() {
        return $x("//a[@class='header-i header-i--brackes ga-click']");
    }

    SelenideElement engineCategory() {
        return $x("//a[@class='header-i header-i--engine ga-click']");
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

    //ÜBER AUTODOC

    SelenideElement hilfeCenterLink() {
        return $x("(//div[@class='footer__links-block']//span[@target='_blank'])[2]");
    }

    SelenideElement aboutUsLink() {
        return $x("//a[@data-ga-action='about_us_big']");
    }

    SelenideElement impressumLink() {
        return $x("//a[@data-ga-action='imprint']");
    }

    SelenideElement vacanciesLink() {
        return $x("//a[@data-ga-action='vacancies']");
    }

    SelenideElement bonusprogrammLink() {
        return $x("//a[@data-ga-action='bonus_system']");
    }

    SelenideElement sponsoringLinc() {
        return $x("//a[@data-ga-action='sponsorship']");
    }

    SelenideElement partnershipLink() {
        return $x("//a[@data-ga-action='partnership']");
    }

    SelenideElement presseLink() {
        return $x("//a[@data-ga-action='presse']");
    }

    SelenideElement mobileAppLink() {
        return $x("//a[@data-ga-action='mobile_app']");
    }

    SelenideElement autodocPlusLink() {
        return $x("//span[@data-ga-action='footer']");
    }

    SelenideElement autodocPartners() {
        return $x("//span[@data-ga-action='Partners']");
    }

    //HILFE & SUPPORT

    SelenideElement autodocClubLink() {
        return $x("//a[@data-ga-action='club']");
    }

    SelenideElement blogLink() {
        return $x("//a[@data-ga-action='blog']");
    }

    SelenideElement videoTutorialsLink() {
        return $x("//*[@class='footer__links']/div[2]/ul/li[4]/a");
    }

    SelenideElement altolentsorgungLink() {
        return $x("//a[@data-ga-action='waste_oil_disposal']");
    }

    SelenideElement agbLink() {
        return $x("//a[@data-ga-action='terms']");
    }

    SelenideElement agb_plusLink() {
        return $x("//span[@data-ga-action='plus_agb']");
    }

    SelenideElement widerrufLink() {
        return $x("//a[@data-ga-action='revocation']");
    }

    SelenideElement datenschutzLink() {
        return $x("//a[@data-ga-action='confidential']");
    }

    //KUNDENSERVICE

    SelenideElement zahlungLink() {
        return $x("//a[@data-ga-action='payment']");
    }

    SelenideElement versandLink() {
        return $x("//a[@data-ga-action='delivery']");
    }

    SelenideElement contactLink() {
        return $x("//a[@data-ga-action='contact']");
    }

    SelenideElement retourenLink() {
        return $x("//a[@data-ga-action='return_return']");
    }

    SelenideElement austauschartikelLink() {
        return $x("//a[@data-ga-action='article_change']");
    }

    //TOP PRODUKTE

    SelenideElement beleuchtungLink() {
        return $x("//a[@data-ga-action='lighting']");
    }

    SelenideElement stobdampferLink() {
        return $x("//a[@data-ga-action='shock-absorber']");
    }

    SelenideElement kupplungssatzLink() {
        return $x("//a[@data-ga-action='clutch-kit']");
    }

    SelenideElement querlenkerLink() {
        return $x("//a[@data-ga-action='suspension-arm']");
    }

    SelenideElement radlagerLink() {
        return $x("//a[@data-ga-action='wheel-bearing']");
    }

    SelenideElement autopflegeLink() {
        return $x("//a[@data-ga-action='chemical']");
    }

    SelenideElement nachHerstellerEinkaufenLink() {
        return $x("//a[@data-ga-action='spares']");
    }

    SelenideElement nachModellEinkaufenLink() {
        return $x("//*[@class='footer__links']/div[4]/ul/li[8]/a");
    }

    SelenideElement sucheNachAutomodelleLink() {
        return $x("//*[@class='footer__links']/div[4]/ul/li[9]/a");
    }

    SelenideElement autoteileHerstellerLink() {
        return $x("//*[@class='footer__links']/div[4]/ul/li[10]/a");
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

    public SelenideElement tecDocCatalogMainPage() {
        return $(byId("parts"));
    }

    public SelenideElement childCategoryOnMainPage(String category) {
        return $x("//ul[@class='ersats_listing']//span[contains(text(),'" + category + "')]");
    }

    SelenideElement chooseModelTooltipInCarSelector() {
        return $(".tooltiptext-line");
    }

    SelenideElement popUpPrivacyPolicy() {
        return $x("//div[@class='popup-privacy-policy']");
    }

    SelenideElement checkBoxPopUpPrivacyPolicy() {
        return $x("//*[@class='popup-privacy-policy__check']//a/..");
    }

    SelenideElement successBtnInPopUpPrivacyPolicy() {
        return $x("//button[@id='privacy_policy_agree']");
    }

    SelenideElement btnAddedAutoInPopUpOfMyGarageBlock() {
        return $x("//div[@class='header-garage__notlogged-button js-selector-add-car']/span");
    }

    SelenideElement selectorFromMyGarageBlock() {
        return $(byId("selector-my-garage"));
    }

    SelenideElement btnSearchOfSelectorFromMyGarage() {
        return $x("//a[@class='submit search_button ripple-out']/span");
    }

    SelenideElement countOfVehicleInMyGarageIcon() {
        return $x("//span[@class='header-garage__count']");
    }

    SelenideElement blackBackground() {
        return $x("//div[@class='overlay black hidden'][@style='display: block;']");
    }

    SelenideElement btnCloseSelectorFromMyGaragePopUp() {
        return $x("//div[@class='popup-selector__content active']/div[1]");
    }

    SelenideElement countOfVehicleInIconOfGarageInHeader() {
        return $x("//span[@class='header-garage__count header-garage__count--added']");
    }

    SelenideElement popUpOfGarageInHeader() {
        return $x("//div[@class='header-garage__logged-header']");
    }

    SelenideElement idOfVehicleInGaragePopUp(String idOfVehicle) {
        return $x("//div[@class='wrapper-radio']/label[@for='" + idOfVehicle + "']");
    }

    SelenideElement btnMoreSpareParts() {
        return $x("//span[@class='all link']");
    }

    ElementsCollection linksInTopsBlock() {
        return $$x("//ul[@class='tabs']//li");
    }

    SelenideElement blockOfBrandsOfTopBlock() {
        return $x("//a[contains(text(),'Beliebteste Automarken')]/ancestor::div[@class='top_all']//ul[@class='cars_list']");
    }

    SelenideElement btnMoreSparePartsOfTopBrands() {
        return $x("//a[contains(text(),'Beliebteste Automarken')]/ancestor::div[@class='top_all']//a[@class='all']");
    }

    SelenideElement allSparePartsLink() {
        return $x("//span[@class='truck-home-parts__show-all link']");
    }

    SelenideElement iconOfWishList() {
        return $x("//span[@class='header__wishes link']");
    }

    SelenideElement blockSocialNetworks() {
        return $x("//*[@class='footer__social']");
    }

    ElementsCollection imagesInTheSocialNetworksBlock() {
        return $$x("//*[@class='image link ga-click']//img[@alt]");
    }

    SelenideElement textBigBlockSocialNetworks() {
        return $x("//*[@class='footer__social-text']//*[@class='text']");
    }

    ElementsCollection textHeadingTheSocialNetworksBlock() {
        return $$x("//*[@class='heading']");
    }

    ElementsCollection linksUnderTheSocialNetworksBlock() {
        return $$x("//*[@class='footer__social']//*[@class='link_arrow link ga-click']");
    }

    SelenideElement blockLinkUnderTheSocialNetworksBlock() {
        return $x("//*[@class='footer__social']");
    }

    ElementsCollection allLinksInFooter() { return $$x("//*[@class='footer__links']//a").filter(visible); }

    SelenideElement titleOfBrandsBlock() {return $x("//div[@class='title']/h3");}

    ElementsCollection visibleTopImageBrands() {return $$x("//div[@class='top_brands']//li//img");}

    SelenideElement topBrandsBlock() {return $x("//div[@class='top_brands']");}

    ElementsCollection visibleTopBrands() {return $$x("//div[@class='top_brands']//li/a");}

    SelenideElement infoTextOfCheckBockAtRegForm() {return $x("//label[@for='isSubscribe_header_modal']");}

    SelenideElement infoTextUnderCheckBockAtRegForm() {return $x("//div[@class='subscribe-text']");}
}
