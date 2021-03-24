package ATD;

import AWS.CategoriesSynonyms_aws;
import AWS.ProductCard_aws;
import Common.DataBase;
import Common.Excel;
import PKW.Supplier_page_Logic;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import files.Car;
import files.Product;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static ATD.CommonMethods.*;
import static Common.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.CollectionCondition.size;
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

    @Step("Check absence text {expectedArticle} in autocomplete. Main_page")
    public Main_page_Logic checkAbsenceTextInAutocomplete(String expectedArticle, String expectedText) {
        inputTextInSearchBar(expectedArticle);
        textInAutocomplete(expectedText).shouldNotBe(visible);
        return this;
    }

    @Step("Check presence text {expectedArticle} in autocomplete. Main_page")
    public Main_page_Logic checkPresenceTextInAutocomplete(String expectedArticle, String expectedText) {
        inputTextInSearchBar(expectedArticle);
        textInAutocomplete(expectedText).shouldBe(visible);
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
        loginBtnInHeader().shouldBe(visible).click();
        mailFieldLogin().setValue(mail);
        passFieldLogin().setValue(password);
        submitBtnLogin().click();
        new Profile_page().nameOfClient().waitUntil(visible, 5000);
        return page(Profile_plus_page_Logic.class);
    }

    @Step("Login in header with mail {mail} and selected checkbox remember me. Main_page")
    public Profile_plus_page_Logic clickCheckboxRememberMeFromLoginPopup(String mail) {
        loginBtnInHeader().click();
        mailFieldLogin().setValue(mail);
        passFieldLogin().setValue(password);
        checkBoxRememberMeFromLoginPopup().shouldBe(visible).click();
        submitBtnLogin().click();
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
        try {
            privacyPolicyBtnFB().shouldBe(visible);
            privacyPolicyBtnFB().click();
        } catch (Throwable e) {
            System.out.println("Privacy policy is not visible");
            e.printStackTrace();
        }
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

    @Step("Checking the sorting of values in the search string by the entered value. Main_page")
    public Main_page_Logic checksIfHintsInTheSearchFieldMatchByValue() {
        String[] valueForSearch = {"radlager", "motoröl", "spiegel", "felgen"};
        for (int i = 0; i < valueForSearch.length; i++) {
            inputTextInSearchBar(valueForSearch[i]);
            counterQuantityProductsInSearch().waitUntil(visible, 5000);
            Boolean hasFullText = false;
            Boolean hasFirstEnter = false;
            Boolean hasMiddleEnter = false;
            for (int a = 0; a < tooltipsToSearch().size(); a++) {
                int index = tooltipsToSearch().get(a).getText().toLowerCase().indexOf(valueForSearch[i]);
                String name = tooltipsToSearch().get(a).getText().replaceAll("\n", "").replaceAll("\\d+", "").toLowerCase();
                if (name.equals(valueForSearch[i])) {
                    hasFullText = true;
                    if (hasFirstEnter || hasMiddleEnter) {
                        Assert.fail("List does not have a given value");
                    }
                } else if (index == 0) {
                    if (!hasFullText || hasMiddleEnter) {
                        Assert.fail("The line did not start with the given value");
                    }
                    hasFirstEnter = true;
                } else if (index > 0) {
                    if (!hasFullText || !hasFirstEnter) {
                        Assert.fail("The value is not in the middle of the line");
                    }
                    hasMiddleEnter = true;
                } else {
                    Assert.fail("List contains inappropriate value ");
                }
            }
            refresh();
        }
        return this;
    }


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
    public Moto_main_page_Logic clickMotoCategory() {
        motoCategory().click();
        return page(Moto_main_page_Logic.class);
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
    public Cart_page_Logic cartClick() {
        cartIcon().scrollTo().click();
        return page(Cart_page_Logic.class);
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

    // This method only for DE
    @Step("Fill in KBA fields in popup. Main_page")
    public Main_page_Logic fillNumberKbaInPopup(String numberForFirstField, String numberForSecondField) {
        firstFieldKBAInPopup().shouldBe(visible).setValue(numberForFirstField);
        secondFieldKBAInPopup().shouldBe(visible).setValue(numberForSecondField);
        sleep(4000);
        return this;
    }

    @Step("Click link \"Was ist eine Schlüsselnummer?\" and check work KBA popup. Main_page")
    public Main_page_Logic clickLinkAndCheckWorkKbaPopup() {
        linkInfoKba().click();
        kbaPopup().shouldBe(visible);
        btnClosePopup().shouldBe(visible).click();
        kbaPopup().shouldNotBe(visible);
        return this;
    }

    @Step("Checks the presence of all elements from KBA selector. Main_page")
    public Main_page_Logic checkPresenceAllElementsInKbaSelectors() {
        if (hiddenSelectorBlock().isDisplayed()) {
            hiddenSelectorBlock().click();
        }
        titleKbaSelector().shouldHave(exactText("NACH SCHLÜSSELNUMMER"));
        firstFieldKBA().shouldHave(attribute("placeholder", "4-stellig"));
        secondFieldKBA().shouldHave(attribute("placeholder", "3-stellig"));
        textUnderFirstFieldKBA().shouldHave(exactText("ZU 2. ODER ZU 2.1."));
        textUnderSecondFieldKBA().shouldHave(exactText("ZU 3. ODER ZU 2.2."));
        linkInfoKba().shouldHave(exactText("Was ist eine" + " Schlüsselnummer?"));
        selectorKbaBtn().shouldHave(exactText("Suchen"));
        return this;
    }

    // This method for all shop, except DE
    @Step("Fill in KBA field. Main_page")
    public Main_page_Logic fillNumberKba(String kbaNumber) {
        sleep(4000); // added sleep SITES-7691
        firstFieldKBA().setValue(kbaNumber);
        return this;
    }

    @Step("Click search KBA button. Main_page")
    public Maker_car_list_page_Logic clickKbaBtn() {
        selectorKbaBtn().click();
        return page(Maker_car_list_page_Logic.class);
    }

    @Step("Click search KBA button and close popup selector if it visible. Main_page")
    public Maker_car_list_page_Logic clickKbaBtnAndClosePopupSelectorIfVisible(String kbaNumber) {
        selectorKbaBtn().click();
        sleep(3000);
        if (selectorPopup().isDisplayed()) {
            closeBtnInCarSelectorPopup().click();
            selectorPopup().shouldNotBe(visible);
            fillNumberKba(kbaNumber);
            selectorKbaBtn().click();
        }
        return page(Maker_car_list_page_Logic.class);
    }

    @Step("Click search KBA button in popup. Main_page")
    public Maker_car_list_page_Logic clickKbaBtnInPopup() {
        selectorKbaBtnInPopup().shouldBe(visible).click();
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

    @Step("checks absence selector popup. Main_page")
    public Main_page_Logic checkAbsenceSelectorPopup() {
        selectorPopup().shouldNotBe(visible);
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
        resetBtnInVerticalCarSelector().shouldBe(visible).click();
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
        String mail = qc + mailinatorMailRandom();
        subscriptionMailField().setValue(mail);
        subscriptionButton().click();
        subscriptionErrPopup().shouldHave(Condition.text("Um fortzufahren bestätigen Sie bitte Ihr Newsletter-Abo"));
        subscriptionPopupClose().click();
        return this;
    }

    @Step("Checking success popup with click checkbox in footer subscribe block. Main_page")
    public String checkingSuccessPopupClickCheckbox(String qc) {
        String mail = qc + mailinatorMailRandom();
        subscriptionMailField().setValue(mail);
        subscriptionMailCheckbox().click();
        subscriptionButton().click();
        subscriptionSuccessPopup().shouldHave(Condition.text("Wir haben eine E-Mail zur Bestätigung Ihres Abonnements an Ihre E-Mail-Adresse gesendet."));
        subscriptionPopupClose().click();
        return mail;
    }

    @Step("Checks application links. Main_page")
    public Main_page_Logic checkApplicationLinks(String appUrl) {
        CommonMethods commonMethods = new CommonMethods();
        footerForm().scrollTo();
        appGoogleButton().waitUntil(visible, 6000).click();
        commonMethods.checkingUrl(appUrl);
        appAppleButton().waitUntil(visible, 6000).click();
        commonMethods.checkingUrl(appUrl);
        return this;
    }

    //ÜBER AUTODOC

    @Step("Click Hilfe Center link in the footer. Main_page")
    public Hilfe_Center_page clickHilfeCenterLink() {
        hilfeCenterLink().click();
        return page(Hilfe_Center_page.class);
    }

    @Step("Click About Us link in the footer. Main_page")
    public About_us_static_page clickAboutUs() {
        aboutUsLink().click();
        return page(About_us_static_page.class);
    }

    @Step("Click Impressum link in the footer. Main_page")
    public Impressum_static_page_Logic clickImpressum() {
        impressumLink().click();
        return page(Impressum_static_page_Logic.class);
    }


    @Step("Click Job link in the footer. Main_page")
    public Job_static_page_Logic clickJob() {
        jobLink().click();
        return page(Job_static_page_Logic.class);
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
    public Presse_static_page clickPresse() {
        presseLink().click();
        return page(Presse_static_page.class);
    }

    @Step("Click MobileApp link in the footer")
    public MobileApp_static_page_Logic clickMobileApp() {
        mobileAppLink().click();
        return page(MobileApp_static_page_Logic.class);
    }

    @Step("Click AutodocPlus link in the footer. Main_page")
    public Service_plus_packages_page_Logic clickAutodocPlus() {
        autodocPlusLink().click();
        return page(Service_plus_packages_page_Logic.class);
    }

    @Step("Click AutodocPartners link in the footer. Main_page")
    public Partners_static_page_Logic clickAutodocPartners() {
        autodocPartners().click();
        return page(Partners_static_page_Logic.class);
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
        versandLink().scrollTo().hover();
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
        DataBase db = new DataBase("ATD");
        footerForm().scrollTo();
        //ÜBER AUTODOC
        clickAboutUs();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticAboutUs"));
        clickImpressum();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticImpressum"));
        clickJob();
        commonMethods.checkingUrlAndCloseTab(db.getRouteByRouteName(getCurrentShopFromJSVarInHTML(), "job"));
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
        clickAutodocPartners();
        commonMethods.checkingUrlAndCloseTab(db.getRouteByRouteName(getCurrentShopFromJSVarInHTML(), "autodocPartners"));
        //HILFE & SUPPORT
        clickHilfeCenterLink();
        commonMethods.checkingUrlAndCloseTab(db.getRouteByRouteName(getCurrentShopFromJSVarInHTML(), "hilfe_center_main"));
        clickAutodocClub();
        commonMethods.checkingUrlAndCloseTab(db.getRouteByRouteName(getCurrentShopFromJSVarInHTML(), "club_main"));
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
        languageSelector().waitUntil(visible, 5000).click();
        dropdownCountry().shouldBe(visible);
        languageSelector().click();
        dropdownCountry().shouldNotBe(visible);
        sleep(2000);
        return this;
    }

    @Step("Checking countries subscription from footer country list. Main_page")
    public Main_page_Logic checkingCountriesSubscription() throws SQLException {
        for (SelenideElement element : allCountryInLangSelector()) {
            String shopName = element.attr("id");
            shopName = shopName.substring(shopName.indexOf("_") + 1);
            if (shopName.equalsIgnoreCase("lu")) shopName = "ld";
            languageSelector().scrollIntoView(true).click();
            element.waitUntil(visible, 3000).scrollIntoView(true).click();
            new CommonMethods().checkingUrlAndCloseTab(shopName, new DataBase("ATD").getRouteByRouteName(shopName, "main"));
        }
        return this;
    }

    @Step("Checks for successful newsletter subscription in footer. Main_page")
    public Main_page_Logic checkSuccessfulNewsletterSubscription(String mail) {
        footerForm().scrollTo();
        subscriptionMailField().setValue(mail);
        subscriptionMailCheckbox().click();
        subscriptionButton().click();
        subscriptionSuccessPopup().shouldHave(Condition.text(" Wir haben eine E-Mail zur Bestätigung Ihres Abonnements an Ihre E-Mail-Adresse gesendet."));
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
        String facebook = "https://www.facebook.com";
        String youTube = "https://www.youtube.com/channel/UCH1orNkIIGZ1jJRjhgY4JeA";
        String instagram = "https://www.instagram.com";
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

    @Step("Checks block promotional footnotes. Main_page")
    public Main_page_Logic checksBlockPromotionalFootnotes() {
        blockPromotionalFootnotes().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        firstPromotionalFootnotes().hover();
        firstDropdownPromotionalFootnotes().shouldBe(visible);
        firstDropdownPromotionalFootnotes().shouldHave(text("Gilt für ausgewählte Produkte. " +
                "Dieser Prozentsatz kann nach Ablauf der rechts oben angegebenen Zeit entfallen, " +
                "sich erhöhen oder verringern. Er bezieht sich nicht auf einen zuvor ernsthaft geforderten Preis, dazu unter"));
        firstCloseBtnDropdownPromotionalFootnotes().click();
        firstDropdownPromotionalFootnotes().shouldNotBe(visible);
        secondPromotionalFootnotes().hover();
        secondDropdownPromotionalFootnotes().shouldBe(visible);
        secondDropdownPromotionalFootnotes().shouldHave(text("Der durchgestrichene Betrag ist kein zuvor ernsthaft geforderter Preis, " +
                "sondern wird in Echtzeit auf der Grundlage unseres jeweils günstigsten Einkaufspreises berechnet. " +
                "Er kann sich daher tagesaktuell erhöhen oder senken."));
        secondCloseBtnDropdownPromotionalFootnotes().click();
        secondDropdownPromotionalFootnotes().shouldNotBe(visible);
        return this;
    }

    @Step(": footer subscribe block on Main_page")
    public Main_page_Logic checkTransitionToLinkPrivacyPolicy(String route) throws SQLException {
        footerForm().scrollTo();
        datenschutzLinkInSubscribeBlock().shouldBe(visible).click();
        new CommonMethods().checkingUrlAndCloseTab(route + "/" + new DataBase("ATD").getRouteByRouteName(getShopFromRoute(route), "staticDatenschutz"));
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
        DataBase db = new DataBase("ATD");
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
        checkingContainsUrl(db.getRouteByRouteName(shop, "motoroil"));
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
        popUpPrivacyPolicy().waitUntil(visible, 20000);
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

    @Step("Login in header with mail {mail} and transition to profile plus page and go back. Main_page")
    public Main_page_Logic loginToProfilePlusPageAndBack(String email) {
        loginAndTransitionToProfilePlusPage(email).visibilityOfUsersName();
        back();
        return this;
    }

    @Step("update of page. Main_page")
    public Main_page_Logic updateOfPage() {
        refresh();
        return this;
    }

    @Step("update of page. Main_page")
    public Main_page_Logic checkCountOfVehicleInIconOfGarage(String expectedCountOfVehicle) {
        countOfVehicleInIconOfGarageInHeader().shouldBe(visible).shouldHave(exactText(expectedCountOfVehicle));
        return this;
    }

    @Step("click on Garage icon in header. Main_page")
    public Main_page_Logic clickOnGarageIconInHeader() {
        headerGarageIcon().shouldBe(visible).click();
        popUpOfGarageInHeader().shouldBe(visible);
        return this;
    }

    @Step("click on Garage icon in header. Main_page")
    public Main_page_Logic selectVehicleInGaragePopUp(String idOfVehicle) {
        idOfVehicleInGaragePopUp(idOfVehicle).shouldBe(visible).click();
        return this;
    }

    @Step("check expected values in selector. Main_page")
    public Main_page_Logic checkValuesInSelector(String marke, String model, String motor) {
        brandSelectorInVerticalCarSelector().shouldHave(value(marke));
        modelSelectorInVerticalCarSelector().shouldHave(value(model));
        typeSelectorInVerticalCarSelector().shouldHave(value(motor));
        return this;
    }

    @Step("click on More spare parts link of catalog. Main_page")
    public Categories_page_Logic clickOnMoreSparePartsLink() {
        btnMoreSpareParts().shouldBe(visible).click();
        return page(Categories_page_Logic.class);
    }

    @Step("select TOP brands block. Main_page")
    public Main_page_Logic selectTopBrandsBlock() {
        linksInTopsBlock().get(0).shouldBe(visible).click();
        blockOfBrandsOfTopBlock().shouldBe(visible);
        return this;
    }

    @Step("click on More spare parts link of TOP brands block. Main_page")
    public Makers_page_Logic clickOnMoreSparePartsLinkOfTopBrandsBlock() {
        btnMoreSparePartsOfTopBrands().shouldBe(visible).click();
        return page(Makers_page_Logic.class);
    }

    @Step("click on All spare parts link in TOP Parent and Child block. Main_page")
    public LKW_Categories_page_Logic clickOnAllSparePartsLinkInTopParentsBlock() {
        allSparePartsLink().shouldBe(visible).click();
        return page(LKW_Categories_page_Logic.class);
    }

    @Step("go to WishList page. Search_page")
    public Services_wishList_page_Logic goToWishListPage() {
        iconOfWishList().shouldBe(visible).click();
        return page(Services_wishList_page_Logic.class);
    }

    @Step("go to the About us page. About_us_page")
    public About_us_static_page_Logic goToAboutUsPage() {
        aboutUsLink().shouldBe(visible).click();
        return page(About_us_static_page_Logic.class);
    }

    @Step("go to the Presse page. Presse_page")
    public Presse_static_page_Logic goToPressePage() {
        presseLink().shouldBe(visible).click();
        return page(Presse_static_page_Logic.class);
    }

    @Step("Checking the Social Networks in the footer. Main_page")
    public Main_page_Logic checkingPresenceOfTheSocialBlock() {
        blockSocialNetworks().shouldBe(visible);
        return this;
    }

    @Step("Checking the displaying of the image in the Social Network Block. Main_page")
    public Main_page_Logic checkingPresenceOfTheImages() {
        imagesInTheSocialNetworksBlock().filter(visible).shouldHave(size(3));
        return this;
    }

    @Step("Checking the presence of the text in the Social Network Block. Main_page")
    public Main_page_Logic checkingPresenceOfTheText() {
        for (int i = 0; i < 4; i++) {
            textHeadingTheSocialNetworksBlock().get(i).shouldBe(visible);
            Assert.assertFalse(textHeadingTheSocialNetworksBlock().get(i).text().isEmpty());
        }
        textBigBlockSocialNetworks().shouldBe(visible);
        Assert.assertFalse(textBigBlockSocialNetworks().text().isEmpty());
        return this;
    }

    @Step("Checking the changing the color of the text in the Social Network Block. Main_page")
    public Main_page_Logic checkingChangingTheColor() {
        actions().moveToElement(blockLinkUnderTheSocialNetworksBlock().scrollIntoView("{block: \"center\"}").waitUntil(visible, 6000));
        linksUnderTheSocialNetworksBlock().shouldHave(size(3));
        for (int i = 0; i < linksUnderTheSocialNetworksBlock().size(); i++) {
            checkingPresenceAllLinksFooter();
            linksUnderTheSocialNetworksBlock().get(i).hover().shouldHave(cssValue("color", "rgba(244, 125, 47, 1)"));
        }
        return this;
    }

    @Step("Checking the all links in the footer. Main_page")
    public Main_page_Logic checkingPresenceAllLinksFooter() {
        for (int i = 0; i < 10; i++) {
            allLinksInFooter().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("check elements of TOP brands block. Main_page")
    public Main_page_Logic checkElementsOfTopBrandsBlock() {
        titleOfBrandsBlock().shouldBe(visible).shouldHave(text("TOP-MARKEN ERSATZTEILE VON BELIEBTEN AUTOTEILE-HERSTELLERN"));
        visibleTopImageBrands().shouldHaveSize(8);
        for (int i = 0; i < visibleTopImageBrands().size(); i++) {
            visibleTopImageBrands().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("presence of TOP brands block. Main_page")
    public Main_page_Logic presenceOfTopBrandsBlock() {
        topBrandsBlock().shouldBe(visible);
        return this;
    }


    @Step("check transition to brand page by click on all brands. Main_page")
    public Main_page_Logic checkTransitionToBrandPageByAllBrands() {
        String urlOfBrandPage;
        for (int i = 0; i < visibleTopBrands().size(); i++) {
            topBrandsBlock().shouldBe(visible);
            urlOfBrandPage = visibleTopBrands().get(i).getAttribute("href");
            clickOnTopBrands(i);
            checkingContainsUrl(urlOfBrandPage);
            back();
        }
        return this;
    }

    @Step("presence of TOP brands block. Main_page")
    public Supplier_page_Logic clickOnTopBrands(int positionOfTopBrands) {
        visibleTopBrands().get(positionOfTopBrands).click();
        return page(Supplier_page_Logic.class);
    }

    @Step("check and click text blocks in registration form.  Main_page")
    public Main_page_Logic checkTextBlockInRegForm() {
        infoTextUnderCheckBockAtRegForm().shouldBe(visible).shouldHave(text("Jetzt abonnieren! Sparen Sie noch mehr!"));
        infoTextOfCheckBockAtRegForm().shouldBe(visible).shouldHave(text("Ja, ich möchte E-Mail-Newsletter mit Sonderangeboten erhalten. Ich kann den Newsletter jederzeit abbestellen."));
        return this;
    }

    @Step("check product sorting by price netto. Main_page")
    public Main_page_Logic checkProductSortingByPriceNetto(List<String> list) throws SQLException {
        Search_page_Logic searchPage = new Search_page_Logic();
        DataBase db = new DataBase("ATD");
        List<String> activeFirstPage, activeSecondPage, activeThirdPage;
        List<String> notActiveFirstPage, notActiveSecondPage, notActiveThirdPage;
        List<Double> priceNettoFirstPageAws = new ArrayList<>();
        List<Double> priceNettoSecondPageAws = new ArrayList<>();
        List<Double> priceNettoThirdPageAws = new ArrayList<>();
        List<Product> firstPageProduct = new ArrayList<>();
        List<Product> secondPageProduct = new ArrayList<>();
        List<Product> thirdPageProduct = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (!searchBar().isDisplayed()) {
                openPage(db.getFullRouteByRouteName("prod", "DE", "main"));
            }
            useSearch(list.get(i));
            activeFirstPage = searchPage.getIdOfActiveProduct();
            notActiveFirstPage = searchPage.getIdOfNotActiveProduct();
            searchPage.addProductToList(firstPageProduct, productsList()).checkSortingOfProducts(firstPageProduct).goToNextPage();
            checkingContainsUrl("page=2");
            activeSecondPage = searchPage.getIdOfActiveProduct();
            notActiveSecondPage = searchPage.getIdOfNotActiveProduct();
            searchPage.addProductToList(secondPageProduct, productsList()).checkSortingOfProducts(secondPageProduct).goToNextPage();
            checkingContainsUrl("page=3");
            activeThirdPage = searchPage.getIdOfActiveProduct();
            notActiveThirdPage = searchPage.getIdOfNotActiveProduct();
            searchPage.addProductToList(thirdPageProduct, productsList()).checkSortingOfProducts(thirdPageProduct);
            getPriceNettoOfProduct(priceNettoFirstPageAws, activeFirstPage);
            getPriceNettoOfProduct(priceNettoSecondPageAws, activeSecondPage);
            getPriceNettoOfProduct(priceNettoThirdPageAws, activeThirdPage);
            checkPriceNettoFromAWS(priceNettoFirstPageAws, priceNettoSecondPageAws, priceNettoThirdPageAws, list.get(i));
            priceNettoFirstPageAws.clear();
            priceNettoSecondPageAws.clear();
            priceNettoThirdPageAws.clear();
            /*ВРЕМЕННО ОТКЛЮЧЕНА ПРОВЕРКА ДЛЯ СЕРЫХ КНОПОК*/
      /*      getPriceNettoOfProduct(priceNettoFirstPageAws, notActiveFirstPage);
            getPriceNettoOfProduct(priceNettoSecondPageAws, notActiveSecondPage);
            getPriceNettoOfProduct(priceNettoThirdPageAws, notActiveThirdPage);
            checkPriceNettoFromAWS(priceNettoFirstPageAws, priceNettoSecondPageAws, priceNettoThirdPageAws);
            priceNettoFirstPageAws.clear(); priceNettoSecondPageAws.clear(); priceNettoThirdPageAws.clear();*/
            activeFirstPage.clear();
            activeSecondPage.clear();
            activeThirdPage.clear();
            firstPageProduct.clear();
            secondPageProduct.clear();
            thirdPageProduct.clear();
            notActiveFirstPage.clear();
            notActiveSecondPage.clear();
            notActiveThirdPage.clear();
        }
        return this;
    }

    @Step("get price netto of product.  Main_page")
    public Main_page_Logic getPriceNettoOfProduct(List<Double> priceNetto, List<String> idOfProduct) {
        if (idOfProduct.size() > 0) {
            for (int i = 0; i < idOfProduct.size(); i++) {
                priceNetto.add(new ProductCard_aws(idOfProduct.get(i)).openProductCardPageAndLogin().getPriceNetto());
            }
        }
        return this;
    }

    @Step("check price Netto from aws.  Main_page")
    public Main_page_Logic checkPriceNettoFromAWS(List<Double> firstList, List<Double> secondList, List<Double> thirdList, String generic) {
        if (firstList.size() > 0 && secondList.size() > 0 && thirdList.size() > 0) {
            Assert.assertTrue(Collections.max(firstList) <= Collections.min(secondList), String.format("All pages is full, test fall between firstPage(maxValueOfFistPage - %s) and secondPage(minValueOfSecondPage - %s) ", Collections.max(firstList), Collections.min(secondList)));
            Assert.assertTrue(Collections.max(secondList) <= Collections.min(thirdList), String.format("All pages is full, test fall between secondPage(maxValueOfSecondPage - %s) and thirdPage(minValueOfThirdPage - %s) ", Collections.max(secondList), Collections.min(thirdList)));
        } else if (firstList.size() > 0 && secondList.size() > 0 && thirdList.size() == 0) {
            Assert.assertTrue(Collections.max(firstList) <= Collections.min(secondList), String.format("First and Second pages are full, ThirdPage is empty, test fall between firstPage(maxValueOfFistPage - %s) and secondPage(minValueOfSecondPage - %s) ", Collections.max(firstList), Collections.min(secondList)));
        } else if (firstList.size() > 0 && thirdList.size() > 0 && secondList.size() == 0) {
            Assert.assertTrue(Collections.max(firstList) <= Collections.min(thirdList), String.format("First and Third pages are full, SecondPage is empty, test fall between firstPage(maxValueOfFistPage - %s) and thirdPage(minValueOfThirdPage - %s) ", Collections.max(firstList), Collections.min(thirdList)));
        } else if (secondList.size() > 0 && thirdList.size() > 0 && firstList.size() == 0) {
            Assert.assertTrue(Collections.max(secondList) <= Collections.min(thirdList), String.format("Second and Third pages are full, FirstPage is empty, test fall between secondPage(maxValueOfSecondPage - %s) and thirdPage(minValueOfThirdPage - %s) ", Collections.max(secondList), Collections.min(thirdList)));
        }
        return this;
    }


    @Step("Checking the transition to the youtube from  the Social Network Block. Main_page")
    public Main_page_Logic checkingTransitionToTheYoutube() {
        String youtubeUrl = youTubeImageTransition().getAttribute("url");
        youTubeImageTransition().click();
        waitingWhileLinkBecomeExpected(youtubeUrl);
        String currentYouTubeUrl = url();
        Assert.assertEquals(youtubeUrl, currentYouTubeUrl);
        back();
        return this;
    }

    @Step("Checking the transition to the youtube from  the Social Network Block click on the link. Main_page")
    public Main_page_Logic checkingTransitionToTheYoutubeClickOnTheLink() {
        String youtubeUrl = youTubeLinkTransition().getAttribute("url");
        youTubeLinkTransition().click();
        waitingWhileLinkBecomeExpected(youtubeUrl);
        String currentYouTubeUrl = url();
        Assert.assertEquals(youtubeUrl, currentYouTubeUrl);
        return this;
    }

    @Step("Checking the transition to the instagram by click in image from  the Social Network Block. Main_page")
    public Main_page_Logic checkingTransitionToTheInstagramByImage() {
        String instagramImageTransition = instagramImageTransition().getAttribute("url") + "/";
        instagramImageTransition().click();
        waitingWhileLinkBecomeExpected(instagramImageTransition);
        String currentUrl = url();
        Assert.assertEquals(currentUrl, instagramImageTransition);
        back();
        return this;
    }

    @Step("Checking the transition to the instagram by click on link from  the Social Network Block. Main_page")
    public Main_page_Logic checkingTransitionToTheInstagramByLink() {
        String instagramLinkTransition = instagramLinkTransition().getAttribute("url") + "/";
        instagramLinkTransition().click();
        waitingWhileLinkBecomeExpected(instagramLinkTransition);
        String currentUrl = url();
        Assert.assertEquals(currentUrl, instagramLinkTransition);
        back();
        return this;
    }

    @Step("Checking the transition to the autodoc club from  the Social Network Block. Main_page")
    public Main_page_Logic checkingTransitionToTheAutodocClub() {
        String autodocClubImageTransitionURL = autodocClubImageTransition().getAttribute("url");
        autodocClubImageTransition().click();
        waitingWhileLinkBecomeExpected(autodocClubImageTransitionURL);
        return this;
    }

    @Step("Checking the transition to the autodoc club from  the Social Network Block. Main_page")
    public Main_page_Logic checkingTransitionToTheAutodocClubLink() {
        String autodocClubLinkTransitionURL = autodocClubTransition().getAttribute("url");
        autodocClubTransition().click();
        waitingWhileLinkBecomeExpected(autodocClubLinkTransitionURL);
        return this;
    }

    @Step("get all car values from file. Main_page")
    public List<Car> getAllCarValuesFromFile(String file) {
        List<Car> cars = new ArrayList<>();
        List<String> marke = new Excel().readFromExcel(file, "qc_2769", 2);
        List<String> model = new Excel().readFromExcel(file, "qc_2769", 4);
        List<String> motor = new Excel().readFromExcel(file, "qc_2769", 0);

        for (int i = 1; i < marke.size(); i++) {
            Car carPage = new Car();
            carPage.setBrand(marke.get(i));
            carPage.setModel(model.get(i));
            carPage.setMotor(motor.get(i));
            cars.add(carPage);
        }
        return cars;
    }

    @Step("get specific values from file. Main_page")
    public List<Car> getSpecificValuesFromFile(List<Car> file, int start, int end) {
        List<Car> list = new ArrayList<>();
        for (int i = start; i < end; i++) {
            list.add(file.get(i));
        }
        return list;
    }

    @Step("select random car from file. Main_page")
    public Maker_car_list_page_Logic selectRandomCarFromFile(List<Car> file, int random_number) {
        brandSelectorInVerticalCarSelector().selectOptionByValue(file.get(random_number).getBrand());
        modelSelectorInVerticalCarSelector().selectOptionByValue(file.get(random_number).getModel());
        typeSelectorInVerticalCarSelector().selectOptionByValue(file.get(random_number).getMotor());
        searchBtnInVerticalSelector().click();
        return page(Maker_car_list_page_Logic.class);
    }


    @Step("get random number. Main_page")
    public int getRandomNumber(int maxValue) {
        int minValue = 0;
        int random_number = minValue + (int) (Math.random() * maxValue);
        return random_number;
    }

    @Step(": from. Main_page")
    public ArrayList<String> getHrefOrUrlCategoriesThenWriteToList(ElementsCollection categories) {
        return CommonMethods.getHrefOrUrlCategoriesThenWriteToList(categories);
    }

    @Step(":from Main_page")
    public Main_page_Logic checkCategoriesForServerResponses200(List<String> allCategories) throws IOException {
        CommonMethods.checkCategoriesForServerResponses200(allCategories);
        return this;
    }

    @Step("Check correct display tabs in top block. Main_page")
    public Main_page_Logic checkPresenceAllTabInTopBlock() {
        ArrayList<String> tabTopBlock = new ArrayList<>();
        tabTopBlock.add("Beliebteste Automarken");
        tabTopBlock.add("Autoersatzteile");
        tabTopBlock.add("LKW-Ersatzteile");
        tabTopBlock.add("Motorrad-Ersatzteile");
        tabTopBlock.add("Autozubehör");
        linksInTopsBlock().shouldHaveSize(5);
        linksInTopsBlock().get(1).shouldHave(attribute("class", "active"));
        for (int i = 0; i < linksInTopsBlock().size(); i++) {
            String nameTab = linksInTopsBlock().get(i).getText().toLowerCase();
            String nameTabFromList = tabTopBlock.get(i).toLowerCase();
            Assert.assertEquals(nameTab, nameTabFromList);
        }
        return this;
    }

    @Step("Select tab TOP car brands block. Main_page")
    public Main_page_Logic selectTabTopCarBrandsBlock() {
        linksInTopsBlock().get(0).shouldBe(visible).click();
        blockOfBrandsOfTopBlock().shouldBe(visible);
        brandCarInTopBlock().shouldHaveSize(16);
        return this;
    }

    @Step("Check transition by tab LKW in top block. Main_page")
    public Main_page_Logic checkTransitionByTabLkwInTopBlock() throws SQLException {
        linksInTopsBlock().get(2).shouldBe(visible).click();
        switchTo().window(1);
        String urlLkwPage = url().replaceAll("\\/[^\\/]*$", "");
        String expectedLkwUrl = new DataBase("ATD").getFullRouteByRouteName("subprod", "DE", "lkw_main");
        Assert.assertEquals(urlLkwPage, expectedLkwUrl);
        closeWindow();
        switchTo().window(0);
        return this;
    }

    @Step("Check transition by tab Moto in top block. Main_page")
    public Main_page_Logic checkTransitionByTabMotoInTopBlock() throws SQLException {
        linksInTopsBlock().get(3).shouldBe(visible).click();
        switchTo().window(1);
        String urlMotoPage = url().replaceAll("\\/[^\\/]*$", "");
        String expectedMotoUrl = new DataBase("ATD").getFullRouteByRouteName("subprod", "DE", "moto_main");
        Assert.assertEquals(urlMotoPage, expectedMotoUrl);
        closeWindow();
        switchTo().window(0);
        return this;
    }

    @Step("Check transition by tab Accessories in top block. Main_page")
    public Index_accessories_page_Logic checkTransitionByTabAccessoriesInTopBlock() throws SQLException {
        linksInTopsBlock().get(4).shouldBe(visible).click();
        String urlAccessoriesPage = url();
        String expectedAccessoriesUrl = new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "index_accessories");
        Assert.assertEquals(urlAccessoriesPage, expectedAccessoriesUrl);
        return page(Index_accessories_page_Logic.class);
    }


    @Step("get Generics from search bar. Main_page")
    public List<String> getGenericsFromSearchBar() {
        genericsFromTips().get(0).shouldBe(visible);
        List<String> generics = genericsFromTips().stream().map(n -> n.getText()).collect(Collectors.toList());
        return generics;
    }


    @Step("check logic of search suggestions. Main_page")
    public Main_page_Logic checkLogicOfSearchSuggestions(List<String> searchText, String route) {

        List<String> genericsOfHints = new ArrayList<>();
        List<String> synonymsOfHints = new ArrayList<>();
        for (int i = 0; i < searchText.size(); i++) {
            if (!url().equals(route)) {
                openPage(route);
            }
            inputTextInSearchBar(searchText.get(i));
            genericsFromTips().get(0).shouldBe(visible);
            for (int j = 0; j < genericsFromTips().size(); j++) {
                if (genericsFromTips().get(j).getText().matches(".+\n.+")) {
                    genericsOfHints.add(genericsFromTips().get(j).getText().replaceAll("(.+)(\n.+)", "$1"));
                } else {
                    synonymsOfHints.add(genericsFromTips().get(j).getText());
                }
            }
            new CategoriesSynonyms_aws().openSynonymsPageInAws()
                    .checkGenerics(genericsOfHints, synonymsOfHints)
                    .clearCategoriesField()
                    .checkSynonyms(synonymsOfHints);
            genericsOfHints.clear();
            synonymsOfHints.clear();
        }
        return this;
    }

    @Step("get all child categories. Main_page")
    public List<String> getAllChildCategories() {
        List<String> generics = allChildCategories().stream().map(n -> n.getText()).collect(Collectors.toList());
        return generics;
    }

    @Step("click on main logo. Main_page")
    public Main_page_Logic clickOnMainLogo() {
        logoInHeader().click();
        return this;
    }

    @Step("display pop-up About Required Login Field. Main_page")
    public Main_page_Logic displayPopUpAboutRequiredLoginField(String expectedText) {
        infoErrorLoginPopUp().shouldBe(visible);
        infoTextInErrorLoginPopUp().shouldHave(text(expectedText));
        return this;
    }

    @Step("close pop-up About Required Login Field. Main_page")
    public Main_page_Logic closePopUpAboutRequiredLoginField() {
        subscriptionPopupClose().click();
        return this;
    }

    @Step("click on Registration form. Main_page")
    public Main_page_Logic clickOnRegistrationForm() {
        loginBtnInHeader().shouldBe(visible).click();
        return this;
    }

    @Step("click on 'Submit' button of registration form. Main_page")
    public Main_page_Logic clickOnSubmitOfRegistrationForm() {
        submitBtnLogin().shouldBe(visible).click();
        return this;
    }

    @Step("forced click on logo in header. Main_page")
    public Main_page_Logic forcedClickOnLogoInHeader() {
        actions().click(logoInHeader()).perform();
        return this;
    }

    @Step("set eMail in registration form. Main_page")
    public Main_page_Logic setMailInRegistrationForm(String mail) {
        mailFieldLogin().setValue(mail);
        return this;
    }

    @Step("presence Of Tyres Category. Main_page")
    public Main_page_Logic presenceOfTyresCategory() {
        tyresCategory().shouldBe(visible);
        return this;
    }

    @Step("get Top Parent Categories. Main_page")
    public List<String> getTopParentCategories() {
        List<String> categories = new ArrayList<>();
        for (int i = 1; i < topParentCategory().size(); i++) {
            categories.add(topParentCategory().get(i).getText());
        }
        return categories;
    }

    @Step("click on Tyres Category. Main_page")
    public Tyres_page_Logic clickOnTyresCategory() {
        tyresCategory().shouldBe(visible).click();
        return page(Tyres_page_Logic.class);
    }

    @Step("presence Of Covid_19 icon. Main_page")
    public Main_page_Logic presenceOfCovidIcon(String covidText) {
        covidIcon().shouldBe(visible).shouldHave(text(covidText));
        return this;
    }

    @Step("close Covid_19 icon. Main_page")
    public Main_page_Logic closeCovidIcon() {
        btnCloseCovidIcon().click();
        return this;
    }

    @Step("absence of Covid_19 icon. Main_page")
    public Main_page_Logic absenceOfCovidIcon() {
        covidIcon().shouldNotBe(visible);
        return this;
    }

    @Step("display all Categories In Header. Main_page")
    public Main_page_Logic displayAllCategoriesInHeader() {
        for (int i = 0; i < visibleCategoriesInHeader().size(); i++) {
            visibleCategoriesInHeader().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("Hover over the preview basket and check the article item number {expectedArtNum}. Main_page")
    public Main_page_Logic hoverOverPreviewBasketAndCheckArtItemNum(String expectedArtNum) {
        previewBasket().scrollIntoView("{block: \"center\"}").hover();
        articleNumInProductFromPreview().shouldBe(visible).shouldHave(text(expectedArtNum));
        return this;
    }

    @Step("Click on Autodoc Plus link in header. Main_page")
    public Service_plus_packages_page_Logic clickOnAutodocPlusInHeader() {
        autodocPlusLinkInHeader().shouldBe(visible).click();
        return page(Service_plus_packages_page_Logic.class);
    }

    @Step("Check presence  dropdown Plus. Main_page")
    public Main_page_Logic checkPresenceDropdownPlus() {
        autodocPlusLinkInHeader().hover();
        autodocDropdownPlus().waitUntil(appear, 5000);
        return this;
    }

    @Step("check disabled category. Main_page")
    public Main_page_Logic checkDisabledCategory(List<String> languages, String artNum) throws SQLException {

        for (int i = 0; i < languages.size(); i++) {
            String currentLanguage = languages.get(i).replaceAll("^.+\\_", "").toUpperCase();
            if (currentLanguage.equals("EU")) {
                continue;
            } else {
                open(new DataBase("ATD").getFullRouteByRouteName("prod", currentLanguage, "main"));
                useSearch(artNum).absenceOfDisabledProduct(artNum);
            }
        }

        return this;
    }

}