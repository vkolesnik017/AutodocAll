package ATD;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static ATD.CommonMethods.getCurrentShopFromJSVarInHTML;
import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Main_page_Logic extends Main_page {


    public Profile_page_Logic loginFromHeader(String mail) {
        loginBtnInHeader().click();
        mailFieldLogin().setValue(mail);
        passFieldLogin().setValue(password);
        submitBtnLogin().click();
        return page(Profile_page_Logic.class);
    }

    Main_page_Logic logOutClick() {
        logoutButton().click();
        return page(Main_page_Logic.class);
    }

    public Profile_page_Logic profileBtnClickInHeader() {
        profileBtn().click();
        return page(Profile_page_Logic.class);
    }

    // Search bar
    @Step("Input text in search bar")
    public Main_page_Logic inputTextInSearchBar(String text) {
        searchBar().setValue(text);
        return this;
    }

    @Step("Click tooltip in search by exact text {exactTooltipText}")
    public Search_page_Logic clickTooltipInSearchByExactText(String exactTooltipText) {
        tooltipToSearch().shouldBe(visible);
        tooltipsToSearch().filter(exactText(exactTooltipText)).shouldHaveSize(1).get(0).click();
        return page(Search_page_Logic.class);
    }

    @Step("Use search with: {searchArticle}")
    public Search_page_Logic useSearch(String searchArticle) {
        inputTextInSearchBar(searchArticle)
                .searchButton().click();
        return page(Search_page_Logic.class);
    }

    @Step("The method verifies that generics are under synonyms when entered text {searchText} in search bar")
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
    // Selector

    @Step("Close tooltip in car selector")
    public Main_page_Logic closeTooltipInCarSelector() {
        tooltipInCarSelectorCloseBtn().shouldBe(visible);
        sleep(2000);
        tooltipInCarSelectorCloseBtn().click();
        tooltipInCarSelectorCloseBtn().shouldNot(visible);
        return this;
    }

    // Registration popup
    @Step("Open registration popup.")
    public Main_page_Logic openRegistrationPopup() {
        loginBtnInHeader().click();
        registrationButtonInLoginPopup().click();
        return this;
    }

    @Step("Filling required fields for registration")
    public Main_page_Logic fillRequiredFieldsForRegistration(String firstName, String secondName, String mail, Boolean checkbox) {
        datenschutzerklarungTextInRegPopup().shouldBe(visible);
        vornameInRegForm().setValue(firstName);
        nameInRegForm().setValue(secondName);
        mailInRegForm().setValue(mail);
        if (checkbox) checkboxInRegForm().click();
        passStepInRegForm().click();
        return this;
    }

    @Step("Filling password fields and click registration")
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
    @Step("Fill in KBA fields")
    public Main_page_Logic fillNumberKba(String numberForFirstField, String numberForSecondField) {
        sleep(4000); // added sleep SITES-7691
        firstFieldKBA().setValue(numberForFirstField);
        secondFieldKBA().setValue(numberForSecondField);
        return this;
    }

    // This method for all shop, except DE
    @Step("Fill in KBA field")
    public Main_page_Logic fillNumberKba(String kbaNumber) {
        sleep(3000); // added sleep SITES-7691
        firstFieldKBA().setValue(kbaNumber);
        return this;
    }

    @Step("Click search KBA button")
    public Maker_car_list_page_Logic clickKbaBtn() {
        selectorKbaBtn().click();
        return page(Maker_car_list_page_Logic.class);
    }

    @Step("Click link \"Was ist eine Schlüsselnummer?\" and check appears info KBA popup")
    public Main_page_Logic clickLinkAndCheckAppearsInfoKbaPopup() {
        arrowInBrandSelectorVerticalCar().waitUntil(visible, 30000);
        linkInfoKba().click();
        kbaPopup().shouldBe(visible);
        return this;
    }

    // Car selector popup
    @Step("Choose brand in car selector popup")
    public Main_page_Logic chooseBrandInCarSelectorPopup(String brandName) {
        brandSelectorInCarSelectorPopup().selectOption(brandName);
        Wait().until(webDriver -> brandSelectorInCarSelectorPopup().getSelectedText().equals(brandName));
        return this;
    }

    @Step("Choose model in car selector popup")
    public Main_page_Logic chooseModelInPopupSelectorForChooseCar(String modelNumberValue) {
        modelSelectorInCarSelectorPopup().selectOptionByValue(modelNumberValue);
        return this;
    }

    @Step("Click reset button in car selector popup")
    public Main_page_Logic resetCarSelectorPopup() {
        resetCarBtnInCarSelectorPopup().click();
        resetCarBtnInCarSelectorPopup().shouldBe(not(visible));
        return this;
    }

    // Vertical car selector popup

    // The method needed for pages where the vertical car selector is hidden by default
    @Step("Open vertical car selector if it hidden")
    public Main_page_Logic openVerticalCarSelectorIfItHidden() {
        if (!brandSelectorInVerticalCarSelector().isDisplayed()) {
            hiddenVerticalSelector().click();
        }
        return this;
    }

    @Step("Choose brand in vertical car selector")
    public Main_page_Logic chooseBrandInVerticalCarSelector(String brandName) {
        openVerticalCarSelectorIfItHidden();
        brandSelectorInVerticalCarSelector().selectOption(brandName);
        Wait().until(webDriver -> brandSelectorInVerticalCarSelector().getSelectedText().equals(brandName));
        return this;
    }

    @Step("Choose model in vertical car selector")
    public Main_page_Logic chooseModelInVerticalCarSelector(String modelNumberValue) {
        modelSelectorInVerticalCarSelector().selectOptionByValue(modelNumberValue);
        sleep(1500);
        return this;
    }

    @Step("Choose type in vertical car selector")
    private Main_page_Logic chooseTypeInVerticalCarSelector(String typeNumberValue) {
        typeSelectorInVerticalCarSelector().selectOptionByValue(typeNumberValue);
        return this;
    }

    @Step("Choose brand, model, type in vertical car selector")
    public Main_page_Logic chooseBrandModelTypeInSelector(String brandName, String modelNumberValue, String typeNumberValue) {
        chooseBrandInVerticalCarSelector(brandName);
        chooseModelInVerticalCarSelector(modelNumberValue);
        chooseTypeInVerticalCarSelector(typeNumberValue);
        return this;
    }

    @Step("Click reset button in vertical car selector")
    public Main_page_Logic resetVerticalCarSelector() {
        resetBtnInVerticalCarSelector().click();
        resetBtnInVerticalCarSelector().shouldBe(not(visible));
        return this;
    }

    @Step("Click search button in vertical car selector when NOT selected all fields")
    public Main_page_Logic clickSearchBtnInVerticalSelectorWhenNotSelectedFields() {
        searchBtnInVerticalSelector().click();
        return this;
    }

    @Step("Click search button in vertical car selector when SELECTED all fields, for redirect to catalog page")
    public Categories_page clickSearchBtnInVerticalSelectorWhenSelectedAllFields() {
        searchBtnInVerticalSelector().click();
        return page(Categories_page.class);
    }


    // GDPR footer
    public Main_page_Logic scrollToFooterSubscribeBlock() {
        footerForm().scrollTo();
        footerForm().shouldBe(Condition.visible);
        return this;
    }

    public Main_page_Logic checkingDatenschutzerklarungLinkBehaviorInReviewsForm() {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(clickDatenschutzInSubscribeBlock(), "none solid rgb(0, 104, 215)");
        return this;
    }

    public Main_page_Logic checkingErrorPopupUnclickCheckbox(String qc) {
        String mail = qc + mailRandom();
        subscriptionMailField().setValue(mail);
        subscriptionButton().click();
        subscriptionErrPopup().shouldHave(Condition.text("Um fortzufahren bestätigen Sie bitte Ihr Newsletter-Abo"));
        subscriptionPopupClose().click();
        return this;
    }

    public String checkingSuccessPopupClickCheckbox(String qc) {
        String mail = qc + mailRandom();
        subscriptionMailField().setValue(mail);
        subscriptionMailCheckbox().click();
        subscriptionButton().click();
        subscriptionSuccessPopup().shouldHave(Condition.text("Herzlichen Dank! Viel Spaß beim Shoppen!"));
        subscriptionPopupClose().click();
        return mail;
    }

    @Step("Checks application links")
    public Main_page_Logic checkApplicationLinks(String appUrl) {
        CommonMethods commonMethods = new CommonMethods();
        footerForm().scrollTo();
        appGoogleButton().click();
        commonMethods.checkingUrl(appUrl);
        appAppleButton().click();
        commonMethods.checkingUrl(appUrl);
        return this;
    }

    @Step("Checks transition to links and checks URLs")
    public Main_page_Logic checkForLinksAndUrls(String route) throws SQLException {
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

    @Step("Checks open and close droplist with countries")
    public Main_page_Logic checkOpenAndCloseDroplistCountries() {
        footerForm().scrollTo();
        languageSelector().click();
        dropdownCountry().shouldBe(visible);
        languageSelector().click();
        dropdownCountry().shouldNotBe(visible);
        sleep(2000);
        return this;
    }

    @Step("Checking Countries Subscription")
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

    @Step("Checks for successful newsletter subscription")
    public Main_page_Logic checkSuccessfulNewsletterSubscription() {
        footerForm().scrollTo();
        subscriptionMailField().setValue(testMail);
        subscriptionMailCheckbox().click();
        subscriptionButton().click();
        subscriptionSuccessPopup().shouldHave(Condition.text("Herzlichen Dank! Viel Spaß beim Shoppen!"));
        subscriptionPopupClose().click();
        return this;
    }

    @Step("Checks displaying of non-valid email tooltip")
    public Main_page_Logic checkTooltipWithErrorMessage() {
        footerForm().scrollTo();
        subscriptionButton().click();
        subscriptionErrTooltip().shouldHave(Condition.text("Bitte geben Sie eine gültige E-mail Adresse an"));
        subscriptionMailField().setValue("123456");
        subscriptionButton().click();
        subscriptionErrTooltip().shouldHave(Condition.text("Bitte geben Sie eine gültige E-mail Adresse an"));
        return this;
    }

    @Step("Checks for pop-up with error about non-confirmed newsletter subscription")
    public Main_page_Logic checkPopUp() {
        footerForm().scrollTo();
        subscriptionMailField().setValue(testMail);
        subscriptionButton().click();
        subscriptionErrPopup().shouldHave(Condition.text("Um fortzufahren bestätigen Sie bitte Ihr Newsletter-Abo"));
        subscriptionPopupClose().click();
        return this;
    }

    @Step("Checks link transitions on social networks")
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

    @Step("Checks text blocks in the footer")
    public Main_page_Logic checkTextBlocksInFooter() {
        footerForm().scrollTo();
        techAllianceBlock().shouldBe(Condition.visible);
        workTimeBlock().shouldBe(Condition.visible);
        copyrightBlock().shouldBe(Condition.visible);
        return this;
    }

    @Step("Checks transition the link Datenschutzerklärung")
    public Main_page_Logic checkTransitionToLink(String route) throws SQLException {
        footerForm().scrollTo();
        clickDatenschutzInSubscribeBlock().click();
        new CommonMethods().checkingUrlAndCloseTab(route + "/" + new DataBase().getRouteByRouteName(getShopFromRoute(route), "staticDatenschutz"));
        return this;
    }
}
