package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import static ATD.CommonMethods.password;
import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;


public class Main_page {

    //Header

    public SelenideElement logoInHeader() {
        return $(".header__logo-main");
    }

    public SelenideElement loginBtnInHeader() {
        return $(byCssSelector(".sigin_btn>a"));
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

    private SelenideElement numberOfProductInCart() {
        return $x("//div[@class='header-cart__name ga-click']/span[1]");
    }

    @Step("Checking number of product in cart")
    public void checkingNumberOfProductInCart(int expectedNumber) {
        int actualNumber = Integer.parseInt(numberOfProductInCart().getText());
        Assert.assertEquals(actualNumber, expectedNumber);
    }

    @Step("The method verifies that no duplicates in the dropdown menu with tips when entered text {searchText} in search bar")
    public Main_page checkingThatNoDuplicatesInTooltipsToSearch() {
        tooltipToSearch().shouldBe(visible);
        ArrayList<String> tooltipsArr = new ArrayList<>();
        HashSet<String> tooltipsSet = new HashSet<>();
        ElementsCollection tooltips = tooltipsToSearch().shouldHave(sizeNotEqual(0));
        for (SelenideElement tooltip : tooltips) {
            String tooltipText = tooltip.getText();
            tooltipsArr.add(tooltipText);
            tooltipsSet.add(tooltipText);
        }
        assertEquals(tooltipsArr.size(), tooltipsSet.size());
        return this;
    }

    @Step("The method verifies that generics are under synonyms when entered text {searchText} in search bar")
    public Main_page checkingThatGenericsAreUnderSynonymsInSearchTooltips(String searchText) {
        ElementsCollection tooltipsToSearch = inputTextInSearchBar(searchText).tooltipsToSearch().shouldHave(sizeNotEqual(0));
        for (int i = 0; i < tooltipsToSearch.size(); i++) {
            String hint = tooltipsToSearch.get(i).hover().getText().replaceAll("[^0-9]", "");
            boolean areTheNumbers = hint.matches("-?\\d+(\\.\\d+)?");
            if (areTheNumbers && i+1 < tooltipsToSearch.size()) {
                tooltipsToSearch.get(i + 1).shouldHave(matchText("[0-9]").because("Not all generics are displayed under synonyms in tooltips to search"));
            }
        }
        return this;
    }

    // Menu in header
    @Step
    public LKW_main_page clickLkwCategory() {
        $("[data-ga-action='LKW']").click();
        return page(LKW_main_page.class);
    }

    @Step
    public Moto_main_page clickMotoCategory() {
        $("[data-ga-action='MOTO']").click();
        return page(Moto_main_page.class);
    }

    @Step
    public Tyres_page clickTiresCategory() {
        $(byCssSelector("[data-ga-action='23208']")).click();
        return page(Tyres_page.class);
    }

    @Step
    public Index_instruments_page clickInstrumentsCategory() {
        $("[data-ga-action='36000']").click();
        return page(Index_instruments_page.class);
    }

    @Step
    public Index_accessories_page clickAccessoriesCategory() {
        $("[data-ga-action='33000']").click();
        return page(Index_accessories_page.class);
    }

    @Step
    public CarParts_EngineOil_page clickEngineOilCategory() {
        $("[data-ga-action='12094']").click();
        return page(CarParts_EngineOil_page.class);
    }

    @Step
    public CarParts_Filters_page clickFiltersCategory() {
        $("[data-ga-action='10105']").click();
        return page(CarParts_Filters_page.class);
    }

    @Step
    public CarParts_BrakeSystem_page clickBrakeSystemCategory() {
        $("[data-ga-action='10106']").click();
        return page(CarParts_BrakeSystem_page.class);
    }

    @Step
    public CarParts_Engine_page clickEngineCategory() {
        $(".header-i.header-i--engine").click();
        return page(CarParts_Engine_page.class);
    }

    @Step("Cart click")
    public Cart_page cartClick() {
        cartIcon().click();
        return page(Cart_page.class);
    }

    @Step
    public Main_page inputTextInSearchBar(String text) {
        searchBar().setValue(text);
        return this;
    }

    @Step("Use search with: {searchArticle}")
    public Search_page useSearch(String searchArticle) {
        inputTextInSearchBar(searchArticle)
                .searchButton().click();
        return page(Search_page.class);
    }

    @Step("click tooltip in search by exact text {exactTooltipText}")
    public Search_page clickTooltipInSearchByExactText(String exactTooltipText) {
        tooltipToSearch().shouldBe(visible);
        tooltipsToSearch().filter(exactText(exactTooltipText)).shouldHaveSize(1).get(0).click();
        return page(Search_page.class);
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

    public Profile_page loginUserFromMain(String login){
        loginBtnInHeader().click();
        emailInputInLoginPopup().setValue(login);
        passwordInputInLoginPopup().setValue(password);
        loginBtnInPopUp().click();
        return page(Profile_page.class);
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

    public SelenideElement subscriptionButton() {
        return $(byXpath("//button[@id='news_yes_footer']"));
    }

    public SelenideElement subscriptionMailField() {
        return $(byXpath("//input[@id='subscr_footer']"));
    }

    public SelenideElement subscriptionErrTooltip() {
        return $(byXpath("//div[@class='wrong_footer']/p"));
    }

    public SelenideElement subscriptionMailCheckbox() {
        return $(byXpath("//div[@class='check']/label/span"));
    }

    public SelenideElement subscriptionSuccessPopup() {
        return $(byXpath("//div[@id='news_subscribe']//h3"));
    }

    public SelenideElement subscriptionErrPopup() {
        return $(byXpath("//div[@class='txt error_msg']"));
    }

    public SelenideElement subscriptionPopupClose() {
        return $(byXpath("//div[@class='popup ']//*[@class='close']/span[2]"));
    }

    public SelenideElement techAllianceBlock() {
        return $x("//div[@class='footer__bottom']/div[@class='footer__bottom-first']");
    }

    public SelenideElement workTimeBlock() {
        return $x("//div[@class='footer__bottom']/div[@class='footer__bottom-center']");
    }

    public SelenideElement copyrightBlock() {
        return $x("//div[@class='footer__bottom']//div[@class='copyright']");
    }

    public SelenideElement facebookButton() {
        return $x("//ul[@class='social-inline']/li[1]/span");
    }

    public SelenideElement youtubeButton() {
        return $x("//ul[@class='social-inline']/li[2]/span");
    }

    public SelenideElement instagramButton() {
        return $x("//ul[@class='social-inline']/li[3]/span");
    }

    public SelenideElement clickDatenschutzInSubscribeBlock() {return $(By.cssSelector("#privacy_policy_footer>span")); }

    @Step("Checking Countries Subscription")
    public void checkingCountriesSubscription() throws SQLException {
        ElementsCollection elements = $$(By.xpath("//div[@class='mCSB_container']/div"));
        for (SelenideElement element : elements) {
            String shopName = element.attr("id");
            shopName = shopName.substring(shopName.indexOf("_") + 1);
            if (shopName.equalsIgnoreCase("lu")) shopName = "ld";
            $(By.xpath("//div[@class='footer-language__select']")).click();
            element.$(By.xpath("./a")).scrollIntoView(true).click();
            new CommonMethods().checkingUrlAndCloseTab(new DataBase().getRouteByRouteName(shopName, "main"));
        }
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
        $(By.xpath("//*[@class='footer__links']/div[1]/ul/li[5]/a")).click();
        return page(Partnership_static_page.class);
    }

    public Presse_page clickPresse() {
        $(By.xpath("//*[@class='footer__links']/div[1]/ul/li[6]/a")).click();
        return page(Presse_page.class);
    }

    public MobileApp_static_page clickMobileApp() {
        $(By.xpath("//*[@class='footer__links']/div[1]/ul/li[7]/a")).click();
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
        $(By.xpath("//*[@class='footer__links']/div[2]/ul/li[7]/a")).click();
        return page(Widerruf_static_page.class);
    }

    public Datenschutz_page clickDatenschutz() {
        $(By.xpath("//*[@class='footer__links']/div[2]/ul/li[8]/a")).click();
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

    public Querlenker_page clickQuerlenker() {
        $(By.xpath("//*[@class='footer__links']/div[4]/ul/li[3]/a")).click();
        return page(Querlenker_page.class);
    }

    public Radlager_page clickRadlager() {
        $(By.xpath("//*[@class='footer__links']/div[4]/ul/li[4]/a")).click();
        return page(Radlager_page.class);
    }

    public Kupplungssatz_page clickKupplungssatz() {
        $(By.xpath("//*[@class='footer__links']/div[4]/ul/li[5]/a")).click();
        return page(Kupplungssatz_page.class);
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

    // Vertical car selector popup

    @Step("Method close car selector tooltip if it is present on page")
    public void closeCarSelectorTooltipIfVisible() {
        if (tooltipInCarSelectorCloseBtn().is(visible)) {
            tooltipInCarSelectorCloseBtn().click();
        }
    }

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
