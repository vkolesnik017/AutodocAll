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
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.testng.Assert.assertEquals;

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

    @Step("Login in header with mail {mail} and transition to profile plus page. Main_page")
    public Profile_plus_page_Logic loginAndTransitionToProfilePlusPage(String mail) {
        loginBtnInHeader().click();
        mailFieldLogin().setValue(mail);
        passFieldLogin().setValue(password);
        submitBtnLogin().click();
        new Profile_page().nameOfClient().shouldBe(visible);
        return page(Profile_plus_page_Logic.class);
    }

    @Step("Login in header with mail {mail} and password {pass}. Main_page")
    public Profile_page_Logic loginFromHeader(String mail, String pass) {
        loginBtnInHeader().click();
        mailFieldLogin().setValue(mail);
        passFieldLogin().setValue(pass);
        submitBtnLogin().click();
        return page(Profile_page_Logic.class);
    }

    @Step("Login from facebook {mail}, {password}. Login_page_mob")
    public Profile_page_Logic signInFromFB(String mail, String pass) {
        loginBtnInHeader().click();
        loginBtnFromFaceBook().click();
        switchTo().window(1);
        emailFieldForFB().setValue(mail);
        passFieldFB().setValue(pass);
        loginBtnFB().click();
        switchTo().window(0);
        return page(Profile_page_Logic.class);
    }

    //in order to try to log in with old password
    @Step("Login in header with old password. Main_page")
    public Main_page_Logic loginWithOldPassword(String mail) {
        logOutClick();
        loginFromHeader(mail);
        closePopUpInvalidDataForLogin().click();
        return this;
    }

    //to enter with a new password
    @Step("Login with new password {newPassword}. Main_page")
    public Profile_plus_page_Logic loginWithNewPassword(String newPassword) {
        passwordInputInLoginPopup().setValue(newPassword);
        loginBtnInPopUp().click();
        new Profile_page().nameOfClient().shouldBe(visible);
        return page(Profile_plus_page_Logic.class);
    }

    @Step("Login with new Email {newEmail}. Main_page")
    public Profile_plus_page_Logic loginWithNewEmail(String newEmail) {
        emailInputInLoginPopup().setValue(newEmail);
        loginBtnInPopUp().click();
        new Profile_page().nameOfClient().shouldBe(visible);
        return page(Profile_plus_page_Logic.class);
    }

    @Step("Clicking log out in header. Main_page")
    public Main_page_Logic logOutClick() {
        logoutButton().click();
        return this;
    }

    @Step("Clicking profile in header. Main_page")
    public Profile_page_Logic profileBtnClickInHeader() {
        profileBtn().click();
        return page(Profile_page_Logic.class);
    }

    @Step("Clicking profile plus in header. Main_page")
    public Profile_plus_page_Logic profilePlusBtnClickInHeader() {
        profileBtn().click();
        return page(Profile_plus_page_Logic.class);
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
        LkwCategory().click();
        return page(LKW_main_page_Logic.class);
    }

    @Step("Clicking moto category. Main_page")
    public Moto_main_page clickMotoCategory() {
        motoCategory().click();
        return page(Moto_main_page.class);
    }

    @Step("Clicking tyres category. Main_page")
    public Tyres_page clickTiresCategory() {
        tiresCategory().click();
        return page(Tyres_page.class);
    }

    @Step("Clicking instruments category. Main_page")
    public Index_instruments_page clickInstrumentsCategory() {
        instrumentsCategory().click();
        return page(Index_instruments_page.class);
    }

    @Step("Clicking accessories category. Main_page")
    public Index_accessories_page clickAccessoriesCategory() {
        accessoriesCategory().click();
        return page(Index_accessories_page.class);
    }

    @Step("Clicking oil engine category. Main_page")
    public CarParts_EngineOil_page clickEngineOilCategory() {
        engineOilCategory().click();
        return page(CarParts_EngineOil_page.class);
    }

    @Step("Clicking filters category. Main_page")
    public CarParts_Filters_page clickFiltersCategory() {
        filtersCategory().click();
        return page(CarParts_Filters_page.class);
    }

    @Step("Clicking brake system category. Main_page")
    public CarParts_BrakeSystem_page clickBrakeSystemCategory() {
        brakeSystemCategory().click();
        return page(CarParts_BrakeSystem_page.class);
    }

    @Step("Clicking engine category. Main_page")
    public CarParts_Engine_page clickEngineCategory() {
        engineCategory().click();
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

    @Step("Check model chose tooltip in selector. Main_page")
    public Main_page_Logic checkModelChooseTooltipInSelector() {
        chooseModelTooltipInCarSelector().shouldHave(text("Fahrzeuginformationen hinzufügen, um passende Teile zu finden"));
        refresh();
        chooseModelTooltipInCarSelector().shouldNotBe(visible);
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

    @Step("Click About Us link in the footer. Main_page")
    public About_us_page clickAboutUs() {
        aboutUsLink().click();
        return page(About_us_page.class);
    }

    @Step("Click Impressum link in the footer. Main_page")
    public Impressum_static_page_Logic clickImpressum() {
        impressumLink().click();
        return page(Impressum_static_page_Logic.class);
    }


    @Step("Click Vacancies link in the footer. Main_page")
    public Vacancies_static_page_Logic clickVacancies() {
        vacanciesLink().click();
        return page(Vacancies_static_page_Logic.class);
    }

    @Step("Click Bonusprogramm link in the footer. Main_page")
    public Bonusprogramm_page clickBonusprogramm() {
        bonusprogrammLink().click();
        return page(Bonusprogramm_page.class);
    }

    @Step("Click Sponsoring link in the footer. Main_page")
    public Sponsoring_static_page_Logic clickSponsoringLink() {
        sponsoringLinc().click();
        return page(Sponsoring_static_page_Logic.class);
    }

    @Step("Click Partnership link in the footer. Main_page")
    public Partnership_static_page_Logic clickPartnership() {
        partnershipLink().click();
        return page(Partnership_static_page_Logic.class);
    }

    @Step("Click Presse link in the footer. Main_page")
    public Presse_page clickPresse() {
        presseLink().click();
        return page(Presse_page.class);
    }

    @Step("Click MobileApp link in the footer")
    public MobileApp_static_page_Logic clickMobileApp() {
        mobileAppLink().click();
        return page(MobileApp_static_page_Logic.class);
    }

    @Step("Click AutodocPlus link in the footer. Main_page")
    public AutodocPlus_page_Logic clickAutodocPlus() {
        autodocPlusLink().click();
        return page(AutodocPlus_page_Logic.class);
    }

    //HILFE & SUPPORT

    @Step("Click AutodocClub link in the footer. Main_page")
    public AutodocClub_page clickAutodocClub() {
        autodocClubLink().click();
        return page(AutodocClub_page.class);
    }

    @Step("Click Blog link in the footer. Main_page")
    public Blog_page clickBlog() {
        blogLink().click();
        return page(Blog_page.class);
    }

    @Step("Click VideoTutorials link in the footer. Main_page")
    public VideoTutorials_page clickVideoTutorials() {
        videoTutorialsLink().click();
        return page(VideoTutorials_page.class);
    }

    @Step("Click Hinweise zur Altölentsorgung link in the footer. Main_page")
    public Altolentsorgung_page clickAltolentsorgung() {
        altolentsorgungLink().click();
        return page(Altolentsorgung_page.class);
    }

    @Step("Click AGB link in the footer. Main_page")
    public Agb_static_page_Logic clickAgb() {
        agbLink().click();
        return page(Agb_static_page_Logic.class);
    }

    @Step("Click PLUS-AGB link in the footer. Main_page")
    public Agb_plus_static_page_Logic clickAgbPlus() {
        agb_plusLink().click();
        return page(Agb_plus_static_page_Logic.class);
    }

    @Step("Click Widerruf link in the footer. Main_page")
    public Widerruf_static_page_Logic clickWiderruf() {
        widerrufLink().click();
        return page(Widerruf_static_page_Logic.class);
    }

    @Step("Click Datenschutz link in the footer. Main_page")
    public Datenschutz_page_Logic clickDatenschutz() {
        datenschutzLink().click();
        return page(Datenschutz_page_Logic.class);
    }

    //KUNDENSERVICE

    @Step("Click Zahlung link in the footer. Main_page")
    public Zahlung_static_page_Logic clickZahlung() {
        zahlungLink().click();
        return page(Zahlung_static_page_Logic.class);
    }

    @Step("Click Versand link in the footer. Main_page")
    public Versand_static_page_Logic clickVersand() {
        versandLink().click();
        return page(Versand_static_page_Logic.class);
    }

    @Step("Click Contact link in the footer. Main_page")
    public Contact_static_page_Logic clickContact() {
        contactLink().click();
        closeCookiesFooterMessage();
        return page(Contact_static_page_Logic.class);
    }

    @Step("Click Retouren link in the footer. Main_page")
    public Retouren_page clickRetouren() {
        retourenLink().click();
        return page(Retouren_page.class);
    }

    @Step("Click Austauschartikel link in the footer. Main_page")
    public Austauschartikel_static_page_Logic clickAustauschartikel() {
        austauschartikelLink().click();
        closeCookiesFooterMessage();
        return page(Austauschartikel_static_page_Logic.class);
    }

    //TOP PRODUKTE

    @Step("Click Beleuchtung link in the footer. Main_page")
    public Beleuchtung_page clickBeleuchtung() {
        beleuchtungLink().click();
        return page(Beleuchtung_page.class);
    }

    @Step("Click Stobdampfer link in the footer. Main_page")
    public Stobdampfer_page clickStobdampfer() {
        stobdampferLink().click();
        return page(Stobdampfer_page.class);
    }

    @Step("Click Querlenker link in the footer. Main_page")
    public Querlenker_page clickQuerlenker() {
        querlenkerLink().click();
        return page(Querlenker_page.class);
    }

    @Step("Click Radlager link in the footer. Main_page")
    public Radlager_page clickRadlager() {
        radlagerLink().click();
        return page(Radlager_page.class);
    }

    @Step("Click Kupplungssatz link in the footer. Main_page")
    public Kupplungssatz_page clickKupplungssatz() {
        kupplungssatzLink().click();
        return page(Kupplungssatz_page.class);
    }

    @Step("Click Autopflege link in the footer. Main_page")
    public Autopflege_page clickAutopflege() {
        autopflegeLink().click();
        return page(Autopflege_page.class);
    }

    @Step("Click Nach Hersteller Einkaufen link in the footer. Main_page")
    public Ersatzteile_page clickNachHerstellerEinkaufen() {
        nachHerstellerEinkaufenLink().click();
        return page(Ersatzteile_page.class);
    }

    @Step("Click Nach Modell Einkaufen link in the footer. Main_page")
    public ErsatzteileModels_page clickNachModellEinkaufen() {
        nachModellEinkaufenLink().click();
        return page(ErsatzteileModels_page.class);
    }

    @Step("Click Suche Nach Automodelle link in the footer. Main_page")
    public ErsatzteileCars_page clickSucheNachAutomodelle() {
        sucheNachAutomodelleLink().click();
        return page(ErsatzteileCars_page.class);
    }

    @Step("Click Autoteile Hersteller link in the footer. Main_page")
    public AutoteileHersteller_page_Logic clickAutoteileHersteller() {
        autoteileHerstellerLink().click();
        return page(AutoteileHersteller_page_Logic.class);
    }


    @Step("Checks transition to footer links and checks URLs. Main_page")
    public Main_page_Logic checkTransitionToLinksOfStaticPage(String route) throws SQLException {
        String shop = getShopFromRoute(route);
        CommonMethods commonMethods = new CommonMethods();
        DataBase db = new DataBase();
        footerForm().scrollTo();
        //ÜBER AUTODOC
        clickAboutUs();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticAboutUs"));
        clickImpressum();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticImpressum"));
        clickVacancies();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "vacancies"));
        clickBonusprogramm();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "bonus_system"));
        clickPartnership();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticPartnership"));
        clickPresse();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticPresse"));
        clickMobileApp();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticMobileApp"));
        clickAutodocPlus();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "service_packages"));
        //HILFE & SUPPORT
        clickAutodocClub();
        commonMethods.checkingUrlAndCloseTab(db.getRouteByRouteName(getCurrentShopFromJSVarInHTML(), "club_main") + "/?reg-modal=");
        clickBlog();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "info_section_index"));
        clickVideoTutorials();
        commonMethods.checkingUrlAndCloseTab(db.getRouteByRouteName(getCurrentShopFromJSVarInHTML(), "club_main") + "/manuals");
        clickAltolentsorgung();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticAltolentsorgung"));
        clickAgb();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticAgb"));
        clickAgbPlus();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticAgdPlus"));
        clickWiderruf();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticWiderruf"));
        clickDatenschutz();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticDatenschutz"));
        //KUNDENSERVICE
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
        //TOP PRODUKTE
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
        clickNachHerstellerEinkaufen();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "makers"));
        clickNachModellEinkaufen();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "hub_groups"));
        clickSucheNachAutomodelle();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "hub_models"));
        clickAutoteileHersteller();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "autoteileHersteller"));
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

    @Step("Checks for a discount block. Main_page")
    public Main_page_Logic checkDiscountBlock() {
        discountBox().shouldBe(visible);
        discountBoxClock().shouldBe(visible);
        return this;
    }

    @Step("Checks the header block and elements inside it. Main_page")
    public Main_page_Logic checkHeaderTopBlock() {
        headerTop().shouldBe(visible);
        logoInHeader().shouldBe(visible);
        headerGarageIcon().shouldBe(visible);
        loginBtnInHeader().shouldBe(visible);
        return this;
    }

    @Step("Checks the transition to the main page through the logo in the Header. Main_page")
    public Main_page_Logic checkTransitionToMainPageThroughTheLogoInHeader() {
        logoInHeader().shouldHave(not(attribute("href")));
        clickVersand();
        logoInHeader().shouldHave(attribute("href")).click();
        logoInHeader().shouldHave(not(attribute("href")));
        return this;
    }

    @Step("Checks the appearance of login pop-up after clicking on the login button in the Header. Main_page")
    public Main_page_Logic checkAppearanceOfLoginPopUpAfterClickingOnLoginButtonInHeader() {
        loginBtnInHeader().click();
        loginBtnInPopUp().shouldBe(visible);
        closeBtnOfLoginPopup().click();
        loginBtnInPopUp().shouldBe(not(visible));
        return this;
    }

    @Step("Checks the appearance of pop-up when you hover over the image of the garage in the Header. Main_page")
    public Main_page_Logic checkAppearanceOfPopUpWhenHoverOverImageOfGarageInHeader() {
        headerGarageIcon().click();
        headerGarageTooltip().shouldBe(visible);
        return this;
    }

    @Step("Checks catalog menu in the Header. Main_page")
    public Main_page_Logic checkCatalogMenuInHeader() {
        menuCatalogInHeader().hover();
        listCategoriesOfCatalog().shouldBe(not(visible));
        menuCatalogInHeader().click();
        listCategoriesOfCatalog().shouldBe(visible);
        logoInHeader().hover();
        listCategoriesOfCatalog().shouldBe(not(visible));
        menuCatalogInHeader().hover();
        listCategoriesOfCatalog().shouldBe(visible);
        return this;
    }

    @Step("Checks pop-up block with hints when clicking on a search in Header. Main_page")
    public Main_page_Logic checkPopUpBlockWithHintsWhenClickingOnSearch() {
        searchBar().click();
        tooltipToSearch().shouldBe(visible);
        logoInHeader().click();
        tooltipToSearch().shouldBe(not(visible));
        return this;
    }

    @Step("Checks info popup for search in header. Main_page")
    public Main_page_Logic checkInfoPopUpForSearch() {
        infoIconForSearch().click();
        infoPopupForSearch().shouldBe(visible);
        infoPopupForSearch().click();
        infoPopupForSearch().shouldBe(not(visible));
        return this;
    }

    @Step("Check presence of categories in the header. Main_page")
    public Main_page_Logic checkPresenceCategoriesInHeader() {
        LkwCategory().shouldBe(visible);
        motoCategory().shouldBe(visible);
        tiresCategory().shouldBe(visible);
        instrumentsCategory().shouldBe(visible);
        accessoriesCategory().shouldBe(visible);
        engineOilCategory().shouldBe(visible);
        filtersCategory().shouldBe(visible);
        brakeSystemCategory().shouldBe(visible);
        engineCategory().shouldBe(visible);
        return this;
    }

    @Step("Checks navigate categories in Header_nav block. Main_page")
    public Main_page_Logic checkNavigateCategoriesInHeaderNavBlock() throws SQLException {
        DataBase db = new DataBase();
        String shop = getCurrentShopFromJSVarInHTML();
        clickLkwCategory();
        checkingContainsUrl(db.getRouteByRouteName(shop, "lkw_main"));
        clickMotoCategory();
        checkingContainsUrl(db.getRouteByRouteName(shop, "moto_main"));
        clickTiresCategory();
        checkingContainsUrl(db.getRouteByRouteName(shop, "tyres"));
        clickInstrumentsCategory();
        checkingContainsUrl(db.getRouteByRouteName(shop, "index_instruments"));
        clickAccessoriesCategory();
        checkingContainsUrl(db.getRouteByRouteName(shop, "index_accessories"));
        clickEngineOilCategory();
        checkingContainsUrl(db.getRouteByRouteName(shop, "engine_oil"));
        clickFiltersCategory();
        checkingContainsUrl(db.getRouteByRouteName(shop, "filters"));
        clickBrakeSystemCategory();
        checkingContainsUrl(db.getRouteByRouteName(shop, "brake_system"));
        clickEngineCategory();
        checkingContainsUrl(db.getRouteByRouteName(shop, "engine"));
        return this;
    }

    @Step("Checks registration from login button in header. Main_page")
    public Profile_plus_page_Logic registrationFromLoginButton(String mail) {
        Profile_page profile_page = new Profile_page();
        loginBtnInHeader().click();
        registrationButtonInLoginPopup().click();
        String firstName = firstNameRandom();
        String secondName = secondNameRandom();
        fillRequiredFieldsForRegistration(firstName, secondName, mail, false);
        fillPasswordFieldsAndClickRegistration();
        profile_page.nameOfClient().shouldHave(Condition.text(firstName));
        return page(Profile_plus_page_Logic.class);
    }

    @Step("Logs out of the account and logs in as a previously registered user. Main_page")
    public Main_page_Logic logOuAndLoginWithUser(String mail) {
        logoutButton().click();
        loginBtnInHeader().click();
        emailInputInLoginPopup().setValue(mail);
        passwordInputInLoginPopup().setValue(password);
        loginBtnInPopUp().click();
        logoutButton().shouldBe(Condition.visible);
        return this;
    }

    @Step("Password Recovery Request. Main_page")
    public Main_page_Logic passwordRecoveryRequest(String mail) {
        loginBtnInHeader().click();
        forgotPasswordLink().click();
        emailFieldInPasswordRecoveryPopUp().setValue(mail);
        sendBtnInPasswordRecoveryPopUp().click();
        closePopupMessageSentForChangePassword().click();
        closePopupMessageSentForChangePassword().shouldBe(not(visible));
        return this;
    }

    @Step("Checks presence pop up invalid data for login and close it. Main_page")
    public Main_page_Logic closePopUpInvalidData() {
        closePopUpInvalidDataForLogin().shouldBe(visible);
        closePopUpInvalidDataForLogin().click();
        return this;
    }

    @Step("Checks presence pop up invalid Email for login and close it. Main_page")
    public Main_page_Logic closeInvalidEmailPopUPForLogin() {
        closePopUPInvalidEmailForLogin().shouldBe(visible);
        closePopUPInvalidEmailForLogin().click();
        return this;
    }

    @Step("Confirms the rules of privacy policy in the popup that appears. Main_page")
    public Main_page_Logic confirmPrivacyPolicyInPopUp() {
        popUpPrivacyPolicy().shouldBe(visible);
        executeJavaScript("arguments[0].click();", checkBoxPopUpPrivacyPolicy());
        successBtnInPopUpPrivacyPolicy().click();
        return this;
    }

    @Step("Check Generic Name In Search Tooltip. Main_page")
    public Main_page_Logic checkGenericNameInSearchTooltip(String genericName) {
        tooltipToSearch().shouldHave(text(genericName));
        return this;
    }

    @Step("go to catalog .Main_page")
    public Maker_car_list_page_Logic goToCatalog() {
        searchBtnInVerticalSelector().click();
        return page(Maker_car_list_page_Logic.class);
    }

    @Step("open Selector from My garage block . Main_page")
    public Main_page_Logic openSelectorFromMyGarageBlock() {
        btnAddedAutoInPopUpOfMyGarageBlock().shouldBe(visible).click();
        blackBackground().shouldBe(visible);
        selectorFromMyGarageBlock().shouldBe(visible);
        return this;
    }

    @Step("select vehicle in selector of my garage . Main_page")
    public Maker_car_list_page_Logic selectVehicleInSelectorOfMyGarage(String marke, String model, String motor) {
        brandSelectorInVerticalCarSelector().shouldBe(visible).selectOptionByValue(marke);
        modelSelectorInVerticalCarSelector().selectOptionByValue(model);
        typeSelectorInVerticalCarSelector().selectOptionByValue(motor);
        btnSearchOfSelectorFromMyGarage().click();
        return page(Maker_car_list_page_Logic.class);
    }

    @Step("select product from hint of Search field. Main_page")
    public Product_page_Logic selectProductFromHintOfSearchField(String artNumOfProduct) {
        tooltipToSearch().shouldBe(visible);
        for (int i = 0; i < tooltipsToSearch().size(); i++) {
            if (tooltipsToSearch().get(i).has(text(artNumOfProduct))) {
                tooltipsToSearch().get(i).click();
            }
        }
        return page(Product_page_Logic.class);
    }

    @Step("check Count Of vehicle in My garage icon . Main_page")
    public Main_page_Logic checkCountOfVehicleInMygarageIcon(int sizeOfVehicle) {
        headerGarageIcon().shouldBe(visible);
        Assert.assertEquals(Integer.parseInt(countOfVehicleInMyGarageIcon().getText()), sizeOfVehicle);
        return this;
    }

    @Step("open My garage pop-Up . Main_page")
    public Main_page_Logic openMyGaragePopUp() {
        headerGarageIcon().shouldBe(visible).click();
        btnAddedAutoInPopUpOfMyGarageBlock().shouldBe(visible);
        return this;
    }

    @Step("close Selector from My garage block . Main_page")
    public Main_page_Logic closeSelectorFromMyGarageBlock() {
        btnCloseSelectorFromMyGaragePopUp().shouldBe(visible).click();
        selectorFromMyGarageBlock().shouldNotBe(visible);
        return this;
    }
}
