package ATD;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import static ATD.CommonMethods.*;
import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Main_page_Logic extends Main_page {

    @Step("Checking number of product in cart. Main_page")
    void checkingNumberOfProductInCart(int expectedNumber) {
        int actualNumber = Integer.parseInt(numberOfProductInCart().getText());
        Assert.assertEquals(actualNumber, expectedNumber);
    }

    @Step("The method verifies that no duplicates in the dropdown menu with tips when entered text {searchText} in search bar. Main_page")
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

    @Step("Login in header with mail {mail}. Main_page")
    public Profile_page_Logic loginFromHeader(String mail) {
        loginBtnInHeader().click();
        mailFieldLogin().setValue(mail);
        passFieldLogin().setValue(password);
        submitBtnLogin().click();
        return page(Profile_page_Logic.class);
    }

    @Step("Clicking log out in header. Main_page")
    Main_page_Logic logOutClick() {
        logoutButton().click();
        return this;
    }

    @Step("Clicking profile in header. Main_page")
    public Profile_page_Logic profileBtnClickInHeader() {
        profileBtn().click();
        return page(Profile_page_Logic.class);
    }

    // Search bar
    @Step("Input text in search bar. Main_page")
    public Main_page_Logic inputTextInSearchBar(String text) {
        searchBar().setValue(text);
        return this;
    }

    @Step("Click tooltip in search by exact text {exactTooltipText}. Main_page")
    public Search_page_Logic clickTooltipInSearchByExactText(String exactTooltipText) {
        tooltipToSearch().shouldBe(visible);
        tooltipsToSearch().filter(exactText(exactTooltipText)).shouldHaveSize(1).get(0).click();
        return page(Search_page_Logic.class);
    }

    @Step("Use search with: {searchArticle}. Main_page")
    public Search_page_Logic useSearch(String searchArticle) {
        inputTextInSearchBar(searchArticle)
                .searchButton().click();
        return page(Search_page_Logic.class);
    }

    @Step("The method verifies that generics are under synonyms when entered text {searchText} in search bar. Main_page")
    public Main_page checkingThatGenericsAreUnderSynonymsInSearchTooltips(String searchText) {
        ElementsCollection tooltipsToSearch = inputTextInSearchBar(searchText).tooltipsToSearch().shouldHave(sizeNotEqual(0));
        for (int i = 0; i < tooltipsToSearch.size(); i++) {
            String hint = tooltipsToSearch.get(i).hover().getText().replaceAll("[^0-9]", "");
            boolean areTheNumbers = hint.matches("-?\\d+(\\.\\d+)?");
            if (areTheNumbers && i + 1 < tooltipsToSearch.size()) {
                tooltipsToSearch.get(i + 1).shouldHave(matchText("[0-9]").because("Not all generics are displayed under synonyms in tooltips to search"));
            }
        }
        return this;
    }

    // Menu in header
    @Step("Clicking LKW category. Main_page")
    public LKW_main_page_Logic clickLkwCategory() {
        $("[data-ga-action='LKW']").click();
        return page(LKW_main_page_Logic.class);
    }

    @Step("Clicking moto category. Main_page")
    public Moto_main_page clickMotoCategory() {
        $("[data-ga-action='MOTO']").click();
        return page(Moto_main_page.class);
    }

    @Step("Clicking tyres category. Main_page")
    public Tyres_page clickTiresCategory() {
        $(byCssSelector("[data-ga-action='23208']")).click();
        return page(Tyres_page.class);
    }

    @Step("Clicking instruments category. Main_page")
    public Index_instruments_page clickInstrumentsCategory() {
        $("[data-ga-action='36000']").click();
        return page(Index_instruments_page.class);
    }

    @Step("Clicking accessories category. Main_page")
    public Index_accessories_page clickAccessoriesCategory() {
        $("[data-ga-action='33000']").click();
        return page(Index_accessories_page.class);
    }

    @Step("Clicking oil engine category. Main_page")
    public CarParts_EngineOil_page clickEngineOilCategory() {
        $("[data-ga-action='12094']").click();
        return page(CarParts_EngineOil_page.class);
    }

    @Step("Clicking filters category. Main_page")
    public CarParts_Filters_page clickFiltersCategory() {
        $("[data-ga-action='10105']").click();
        return page(CarParts_Filters_page.class);
    }

    @Step("Clicking brake system category. Main_page")
    public CarParts_BrakeSystem_page clickBrakeSystemCategory() {
        $("[data-ga-action='10106']").click();
        return page(CarParts_BrakeSystem_page.class);
    }

    @Step("Clicking engine category. Main_page")
    public CarParts_Engine_page clickEngineCategory() {
        $(".header-i.header-i--engine").click();
        return page(CarParts_Engine_page.class);
    }

    @Step("Cart clicking. Main_page")
    public Cart_page cartClick() {
        cartIcon().click();
        return page(Cart_page.class);
    }

    // Selector

    @Step("Close tooltip in car selector. Main_page")
    public Main_page_Logic closeTooltipInCarSelector() {
        tooltipInCarSelectorCloseBtn().shouldBe(visible);
        sleep(2000);
        tooltipInCarSelectorCloseBtn().click();
        tooltipInCarSelectorCloseBtn().shouldNot(visible);
        return this;
    }

    // Login popup
    @Step("Sign in from login popup. Main_page")
    public Profile_page_Logic loginUserFromMain(String login) {
        loginBtnInHeader().click();
        emailInputInLoginPopup().setValue(login);
        passwordInputInLoginPopup().setValue(password);
        loginBtnInPopUp().click();
        return page(Profile_page_Logic.class);
    }

    // Registration popup
    @Step("Open registration popup. Main_page")
    public Main_page_Logic openRegistrationPopup() {
        loginBtnInHeader().click();
        registrationButtonInLoginPopup().click();
        return this;
    }

    @Step("Filling required fields for registration. Main_page")
    public Main_page_Logic fillRequiredFieldsForRegistration(String firstName, String secondName, String mail, Boolean checkbox) {
        datenschutzerklarungTextInRegPopup().shouldBe(visible);
        vornameInRegForm().setValue(firstName);
        nameInRegForm().setValue(secondName);
        mailInRegForm().setValue(mail);
        if (checkbox) checkboxInRegForm().click();
        passStepInRegForm().click();
        return this;
    }

    @Step("Filling password fields and click registration. Main_page")
    public Profile_page_Logic fillPasswordFieldsAndClickRegistration() {
        newPassRegForm().setValue(password);
        newPassConfirmRegForm().setValue(password);
        registrationBtnRegForm().click();
        return page(Profile_page_Logic.class);
    }

    @Step(":registration form. Main_page")
    public Main_page_Logic checkingDatenschutzerklarungLinkBehaviorRegistrationForm() {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(datenschutzerklarungLinkInRegPopup(), "underline solid rgb(50, 103, 214)");
        return this;
    }

    // Selector kba

    // This method only for DE
    @Step("Fill in KBA fields. Main_page")
    public Main_page_Logic fillNumberKba(String numberForFirstField, String numberForSecondField) {
        sleep(4000); // added sleep SITES-7691
        firstFieldKBA().setValue(numberForFirstField);
        secondFieldKBA().setValue(numberForSecondField);
        return this;
    }

    // This method for all shop, except DE
    @Step("Fill in KBA field. Main_page")
    public Main_page_Logic fillNumberKba(String kbaNumber) {
        sleep(3000); // added sleep SITES-7691
        firstFieldKBA().setValue(kbaNumber);
        return this;
    }

    @Step("Click search KBA button. Main_page")
    public Maker_car_list_page_Logic clickKbaBtn() {
        selectorKbaBtn().click();
        return page(Maker_car_list_page_Logic.class);
    }

    @Step("Click link \"Was ist eine Schlüsselnummer?\" and check appears info KBA popup. Main_page")
    public Main_page_Logic clickLinkAndCheckAppearsInfoKbaPopup() {
        arrowInBrandSelectorVerticalCar().waitUntil(visible, 30000);
        linkInfoKba().click();
        kbaPopup().shouldBe(visible);
        return this;
    }

    // Car selector popup
    @Step("Choose brand in car selector popup. Main_page")
    public Main_page_Logic chooseBrandInCarSelectorPopup(String brandName) {
        brandSelectorInCarSelectorPopup().selectOption(brandName);
        Wait().until(webDriver -> brandSelectorInCarSelectorPopup().getSelectedText().equals(brandName));
        return this;
    }

    @Step("Choose model in car selector popup. Main_page")
    public Main_page_Logic chooseModelInPopupSelectorForChooseCar(String modelNumberValue) {
        modelSelectorInCarSelectorPopup().selectOptionByValue(modelNumberValue);
        return this;
    }

    @Step("Click reset button in car selector popup. Main_page")
    public Main_page_Logic resetCarSelectorPopup() {
        resetCarBtnInCarSelectorPopup().click();
        resetCarBtnInCarSelectorPopup().shouldBe(not(visible));
        return this;
    }

    // Vertical car selector popup

    @Step("Method close car selector tooltip if it is present on page. Main_page")
    public void closeCarSelectorTooltipIfVisible() {
        if (tooltipInCarSelectorCloseBtn().is(visible)) {
            tooltipInCarSelectorCloseBtn().click();
        }
    }

    // The method needed for pages where the vertical car selector is hidden by default
    @Step("Open vertical car selector if it hidden. Main_page")
    public Main_page_Logic openVerticalCarSelectorIfItHidden() {
        if (!brandSelectorInVerticalCarSelector().isDisplayed()) {
            hiddenVerticalSelector().click();
        }
        return this;
    }

    @Step("Choose brand in vertical car selector. Main_page")
    public Main_page_Logic chooseBrandInVerticalCarSelector(String brandName) {
        openVerticalCarSelectorIfItHidden();
        brandSelectorInVerticalCarSelector().selectOption(brandName);
        Wait().until(webDriver -> brandSelectorInVerticalCarSelector().getSelectedText().equals(brandName));
        return this;
    }

    @Step("Choose model in vertical car selector. Main_page")
    public Main_page_Logic chooseModelInVerticalCarSelector(String modelNumberValue) {
        modelSelectorInVerticalCarSelector().selectOptionByValue(modelNumberValue);
        sleep(1500);
        return this;
    }

    @Step("Choose type in vertical car selector. Main_page")
    private Main_page_Logic chooseTypeInVerticalCarSelector(String typeNumberValue) {
        typeSelectorInVerticalCarSelector().selectOptionByValue(typeNumberValue);
        return this;
    }

    @Step("Choose brand, model, type in vertical car selector. Main_page")
    public Main_page_Logic chooseBrandModelTypeInSelector(String brandName, String modelNumberValue, String typeNumberValue) {
        chooseBrandInVerticalCarSelector(brandName);
        chooseModelInVerticalCarSelector(modelNumberValue);
        chooseTypeInVerticalCarSelector(typeNumberValue);
        return this;
    }

    @Step("Click reset button in vertical car selector. Main_page")
    public Main_page_Logic resetVerticalCarSelector() {
        resetBtnInVerticalCarSelector().click();
        resetBtnInVerticalCarSelector().shouldBe(not(visible));
        return this;
    }

    @Step("Click search button in vertical car selector when NOT selected all fields. Main_page")
    public Main_page_Logic clickSearchBtnInVerticalSelectorWhenNotSelectedFields() {
        searchBtnInVerticalSelector().click();
        return this;
    }

    @Step("Click search button in vertical car selector when SELECTED all fields, for redirect to catalog page. Main_page")
    public Categories_page_Logic clickSearchBtnInVerticalSelectorWhenSelectedAllFields() {
        searchBtnInVerticalSelector().click();
        return page(Categories_page_Logic.class);
    }


    // GDPR footer
    @Step("Scrolling to footer subscribe block. Main_page")
    public Main_page_Logic scrollToFooterSubscribeBlock() {
        footerForm().scrollTo();
        footerForm().shouldBe(Condition.visible);
        return this;
    }

    @Step(":in review form on Main_page")
    public Main_page_Logic checkingDatenschutzerklarungLinkBehaviorInReviewsForm() {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(datenschutzLinkInSubscribeBlock(), "none solid rgb(0, 104, 215)");
        return this;
    }

    @Step("Checking error popup with unclick checkbox in footer subscribe block. Main_page")
    public Main_page_Logic checkingErrorPopupUnclickCheckbox(String qc) {
        String mail = qc + mailRandom();
        subscriptionMailField().setValue(mail);
        subscriptionButton().click();
        subscriptionErrPopup().shouldHave(Condition.text("Um fortzufahren bestätigen Sie bitte Ihr Newsletter-Abo"));
        subscriptionPopupClose().click();
        return this;
    }

    @Step("Checking success popup with click checkbox in footer subscribe block. Main_page")
    public String checkingSuccessPopupClickCheckbox(String qc) {
        String mail = qc + mailRandom();
        subscriptionMailField().setValue(mail);
        subscriptionMailCheckbox().click();
        subscriptionButton().click();
        subscriptionSuccessPopup().shouldHave(Condition.text("Herzlichen Dank! Viel Spaß beim Shoppen!"));
        subscriptionPopupClose().click();
        return mail;
    }

    @Step("Checks application links. Main_page")
    public Main_page_Logic checkApplicationLinks(String appUrl) {
        CommonMethods commonMethods = new CommonMethods();
        footerForm().scrollTo();
        appGoogleButton().click();
        commonMethods.checkingUrl(appUrl);
        appAppleButton().click();
        commonMethods.checkingUrl(appUrl);
        return this;
    }

    //ÜBER AUTODOC

    public About_us_page clickAboutUs() {
        $(By.xpath("//*[@class='footer__links']/div[1]/ul/li[1]/a")).click();
        return page(About_us_page.class);
    }

    public Impressum_static_page_Logic clickImpressum() {
        $(By.xpath("//*[@class='footer__links']/div[1]/ul/li[2]/a")).click();
        return page(Impressum_static_page_Logic.class);
    }

    public Vacancies_static_page_Logic clickVacancies() {
        $(By.xpath("//*[@class='footer__links']/div[1]/ul/li[3]/a")).click();
        return page(Vacancies_static_page_Logic.class);
    }

    public Bonusprogramm_page clickBonusprogramm() {
        $(By.xpath("//*[@class='footer__links']/div[1]/ul/li[4]/a")).click();
        return page(Bonusprogramm_page.class);
    }

    public Sponsorship_static_page clickSponsorship() {
        $(By.xpath("//*[@class='footer__links']/div[1]/ul/li[5]/a")).click();
        return page(Sponsorship_static_page.class);
    }

    public Partnership_static_page_Logic clickPartnership() {
        $(By.xpath("//*[@class='footer__links']/div[1]/ul/li[5]/a")).click();
        return page(Partnership_static_page_Logic.class);
    }

    public Presse_page clickPresse() {
        $(By.xpath("//*[@class='footer__links']/div[1]/ul/li[6]/a")).click();
        return page(Presse_page.class);
    }

    public MobileApp_static_page_Logic clickMobileApp() {
        $(By.xpath("//*[@class='footer__links']/div[1]/ul/li[7]/a")).click();
        return page(MobileApp_static_page_Logic.class);
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

    public Agb_static_page_Logic clickAgb() {
        $(By.xpath("//*[@class='footer__links']/div[2]/ul/li[5]/a")).click();
        return page(Agb_static_page_Logic.class);
    }

    public Widerruf_static_page_Logic clickWiderruf() {
        $(By.xpath("//*[@class='footer__links']/div[2]/ul/li[7]/a")).click();
        return page(Widerruf_static_page_Logic.class);
    }

    public Datenschutz_page_Logic clickDatenschutz() {
        $(By.xpath("//*[@class='footer__links']/div[2]/ul/li[8]/a")).click();
        return page(Datenschutz_page_Logic.class);
    }

    //KUNDENSERVICE
    public Zahlung_static_page_Logic clickZahlung() {
        $(By.xpath("//*[@class='footer__links']/div[3]/ul/li[1]/a")).click();
        return page(Zahlung_static_page_Logic.class);
    }

    public Versand_static_page_Logic clickVersand() {
        $(By.xpath("//*[@class='footer__links']/div[3]/ul/li[2]/a")).click();
        return page(Versand_static_page_Logic.class);
    }

    public Contact_static_page_Logic clickContact() {
        $(By.xpath("//*[@class='footer__links']/div[3]/ul/li[3]/a")).click();
        return page(Contact_static_page_Logic.class);
    }

    public Retouren_page clickRetouren() {
        $(By.xpath("//*[@class='footer__links']/div[3]/ul/li[4]/a")).click();
        return page(Retouren_page.class);
    }

    public Austauschartikel_static_page_Logic clickAustauschartikel() {
        $(By.xpath("//*[@class='footer__links']/div[3]/ul/li[5]/a")).click();
        return page(Austauschartikel_static_page_Logic.class);
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


    @Step("Checks transition to footer links and checks URLs. Main_page")
    public Main_page_Logic checkTransitionToLinksOfStaticPage(String route) throws SQLException {
        String shop = getShopFromRoute(route);
        CommonMethods commonMethods = new CommonMethods();
        DataBase db = new DataBase();
        footerForm().scrollTo();
        clickAboutUs();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticAboutUs"));
        clickImpressum();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticImpressum"));
        clickVacancies();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "vacancies"));
        clickBonusprogramm();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "bonus_system"));
//      clickSponsorship();
//      commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticSponsorship"));
        clickPartnership();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticPartnership"));
        clickPresse();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticPresse"));
        clickMobileApp();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticMobileApp"));
        clickAutodocClub();
        commonMethods.checkingUrlAndCloseTab(db.getRouteByRouteName(getCurrentShopFromJSVarInHTML(), "club_main") + "/?_ga=");
        clickBlog();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "info_section_index"));
        clickVideoTutorials();
        commonMethods.checkingUrlAndCloseTab(db.getRouteByRouteName(getCurrentShopFromJSVarInHTML(), "club_main") + "/manuals?_ga=");
        clickAltolentsorgung();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticAltolentsorgung"));
        clickAgb();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticAgb"));
        clickWiderruf();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticWiderruf"));
        clickDatenschutz();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticDatenschutz"));
        clickZahlung();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticZahlung"));
        clickVersand();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticVersand"));
        clickContact();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticContact"));
        clickRetouren();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "return_return"));
        clickAustauschartikel();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticAustauschartikel"));
        clickBeleuchtung();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "category_name_parent2"));
        clickStobdampfer();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "category_name2"));
        clickKupplungssatz();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "category_name3"));
        clickQuerlenker();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "category_name4"));
        clickRadlager();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "category_name5"));
        clickAutopflege();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "index_chemicals"));
        clickSucheNachAutomodelle();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "makers"));
        clickNachHerstellerEinkaufen();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "hub_groups"));
        clickNachModellEinkaufen();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "hub_models"));
        return this;
    }

    @Step("Checks open and close footer droplist with countries. Main_page")
    public Main_page_Logic checkOpenAndCloseDroplistCountries() {
        footerForm().scrollTo();
        languageSelector().click();
        dropdownCountry().shouldBe(visible);
        languageSelector().click();
        dropdownCountry().shouldNotBe(visible);
        sleep(2000);
        return this;
    }

    @Step("Checking countries subscription from footer country list. Main_page")
    public Main_page_Logic checkingCountriesSubscription() throws SQLException {
        ElementsCollection elements = $$(By.xpath("//div[@class='mCSB_container']/div"));
        for (SelenideElement element : elements) {
            String shopName = element.attr("id");
            shopName = shopName.substring(shopName.indexOf("_") + 1);
            if (shopName.equalsIgnoreCase("lu")) shopName = "ld";
            $(By.xpath("//div[@class='footer-language__select']")).click();
            element.$(By.xpath("./a")).scrollIntoView(true).click();
            new CommonMethods().checkingUrlAndCloseTab(new DataBase().getRouteByRouteName(shopName, "main"));
        }
        return this;
    }

    @Step("Checks for successful newsletter subscription in footer. Main_page")
    public Main_page_Logic checkSuccessfulNewsletterSubscription() {
        footerForm().scrollTo();
        subscriptionMailField().setValue(testMail);
        subscriptionMailCheckbox().click();
        subscriptionButton().click();
        subscriptionSuccessPopup().shouldHave(Condition.text("Herzlichen Dank! Viel Spaß beim Shoppen!"));
        subscriptionPopupClose().click();
        return this;
    }

    @Step("Checks tooltip display for invalid email in newsletter subscription form in footer. Main_page")
    public Main_page_Logic checkTooltipForInvalidEmail() {
        footerForm().scrollTo();
        subscriptionButton().click();
        subscriptionErrTooltip().shouldHave(Condition.text("Bitte geben Sie eine gültige E-mail Adresse an"));
        subscriptionMailField().setValue("123456");
        subscriptionButton().click();
        subscriptionErrTooltip().shouldHave(Condition.text("Bitte geben Sie eine gültige E-mail Adresse an"));
        return this;
    }

    @Step("Checks for pop-up with error about non-confirmed newsletter subscription. Main_page")
    public Main_page_Logic checkPopUpNonConfirmedNewsletterSubscription() {
        footerForm().scrollTo();
        subscriptionMailField().setValue(testMail);
        subscriptionButton().click();
        subscriptionErrPopup().shouldHave(Condition.text("Um fortzufahren bestätigen Sie bitte Ihr Newsletter-Abo"));
        subscriptionPopupClose().click();
        return this;
    }

    @Step("Checks link transitions on social networks in footer. Main_page")
    public Main_page_Logic checkTransitionToSocialNetwork() {
        CommonMethods commonMethods = new CommonMethods();
        String facebook = "https://www.facebook.com/autodoc.de/";
        String youTube = "https://www.youtube.com/channel/UCH1orNkIIGZ1jJRjhgY4JeA";
        String instagram = "https://www.instagram.com/autodoc_autoparts/";
        footerForm().scrollTo();
        facebookButton().click();
        commonMethods.checkingUrlAndCloseTab(facebook);
        youtubeButton().click();
        commonMethods.checkingUrlAndCloseTab(youTube);
        instagramButton().click();
        commonMethods.checkingUrlAndCloseTab(instagram);
        return this;
    }

    @Step("Checks text blocks in the footer. Main_page")
    public Main_page_Logic checkTextBlocksInFooter() {
        footerForm().scrollTo();
        techAllianceBlock().shouldBe(Condition.visible);
        workTimeBlock().shouldBe(Condition.visible);
        copyrightBlock().shouldBe(Condition.visible);
        return this;
    }

    @Step(": footer subscribe block on Main_page")
    public Main_page_Logic checkTransitionToLinkPrivacyPolicy(String route) throws SQLException {
        footerForm().scrollTo();
        datenschutzLinkInSubscribeBlock().click();
        new CommonMethods().checkingUrlAndCloseTab(route + "/" + new DataBase().getRouteByRouteName(getShopFromRoute(route), "staticDatenschutz"));
        return this;
    }

    @Step("Check successfully Car main page loading. Main_page")
    public Main_page_Logic checkSuccessfullyMainPageLoading() {
        menuCatalogInHeader().shouldBe(visible);
        Assert.assertTrue(url().contains("https://www.autodoc.de/"));
        return this;
    }

    @Step("Checking that selector is empty. Main_page")
    public Main_page_Logic checkOfEmptyOfVerticalSelector() {
        brandSelectorInVerticalCarSelector().shouldHave(exactValue("0"));
        modelSelectorInVerticalCarSelector().shouldHave(exactValue("0"));
        modelSelectorInVerticalCarSelector().shouldHave(exactValue("0"));
        return this;
    }

    @Step("Select child category on main page. Main_page")
    public Category_name_page_Logic selectChildCategory(String childCategory) {
        tecDocCatalogMainPage().scrollTo();
        childCategoryOnMainPage(childCategory).click();
        return page(Category_name_page_Logic.class);
    }
}
