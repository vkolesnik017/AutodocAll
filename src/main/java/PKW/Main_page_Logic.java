package PKW;

import Common.DataBase;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;

import static Common.CommonMethods.checkingContainsUrl;
import static PKW.CommonMethods.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;

public class Main_page_Logic extends Main_page {

    @Step("Cart clicking. Main_page")
    public Cart_page_Logic cartClick() {
        cartIcon().click();
        return page(PKW.Cart_page_Logic.class);
    }

    @Step("Login in header with mail {mail} and {password}. Main_page")
    public Main_page_Logic loginFromHeader(String mail, String pass) {
        loginBtnInHeader().click();
        mailFieldLogin().setValue(mail);
        passFieldLogin().click();
        passFieldLoginForEntering().setValue(pass);
        submitBtnLogin().click();
        loginCompletePopUp().shouldBe(visible);
        btnCloseLoginCompletePopUp().click();
        return page(Main_page_Logic.class);
    }

    @Step("Opens the registration form in header. Main_page")
    public Main_page_Logic openRegistrationFormInHeader() {
        loginBtnInHeader().click();
        registrationBtn().click();
        return this;
    }

    @Step("Filling fields in registration form. Main_page")
    public String fillingRegistrationFields(String qc) {
        String mail = qc + mailRandom();
        mailFieldInRegistration().setValue(mail);
        passwordFieldInRegistration().setValue(password);
        confirmPassFieldInRegistration().setValue(password);
        registrationSubmitBtn().click();
        return mail;
    }

    @Step("Click btn to private room from Popup Authorization completed successfully. Main_page")
    public Profile_page_Logic clickBtnToPRFromPopupAuthorizationSuccessfully() {
        btnToPRFromPopupAuthorizationSuccessfully().click();
        return page(Profile_page_Logic.class);
    }

    @Step("Checks presence popup success authorization. Main_page")
    public Main_page_Logic checkPresencePopupSuccessAuthorization() {
        popupSuccessAuthorization().shouldBe(visible);
        return this;
    }


    @Step("select vehicle in selector of my garage . Main_page")
    public Maker_car_list_page_Logic selectVehicleInSelector(String marke, String model, String motor) {
        brandSelectorInVerticalCarSelector().shouldBe(visible).selectOptionByValue(marke);
        modelSelectorInVerticalCarSelector().selectOptionByValue(model);
        typeSelectorInVerticalCarSelector().selectOptionByValue(motor);
        btnSearchOfSelector().click();
        return page(Maker_car_list_page_Logic.class);
    }


    @Step("Choose brand in vertical car selector. Main_page")
    public Main_page_Logic chooseBrandInVerticalCarSelector(String brandValue) {
        brandSelectorInVerticalCarSelector().selectOptionByValue(brandValue);
        return this;
    }

    @Step("Choose model in vertical car selector. Main_page")
    public Main_page_Logic chooseModelInVerticalCarSelector(String modelNumberValue) {
        modelSelectorInVerticalCarSelector().selectOptionByValue(modelNumberValue);
        return this;
    }

    @Step("Choose type in vertical car selector. Main_page")
    public Main_page_Logic chooseTypeInVerticalCarSelector(String typeNumberValue) {
        typeSelectorInVerticalCarSelector().selectOptionByValue(typeNumberValue);
        return this;
    }

    @Step("Click search button in vertical car selector when SELECTED all fields, for redirect to catalog page. Main_page")
    public Catalog_page_Logic clickSearchBtnInVerticalSelectorWhenSelectedAllFields() {
        btnSearchOfSelector().click();
        return page(Catalog_page_Logic.class);
    }

    @Step("check error tooltip for mark in selector. Main_page")
    public Main_page_Logic checkErrorTooltipForMarkInSelector() {
        selectorErrorTooltipForMark().shouldBe(visible).shouldHave(exactText("Bitte geben Sie den Hersteller, das Modell und den Typ Ihres Autos an"));
        return this;
    }

    // GDPR footer
    @Step("Scrolling to footer subscribe block. Main_page")
    public Main_page_Logic scrollToFooterSubscribeBlock() {
        footerForm().scrollIntoView(false);
        footerForm().shouldBe(visible);
        return this;
    }


    @Step("Checking error popup with unclick checkbox in footer subscribe block. Main_page")
    public Main_page_Logic checkingErrorPopupUnClickCheckbox(String qc) {
        String mail = qc + mailRandom();
        subscriptionMailField().setValue(mail);
        subscriptionButton().click();
        subscriptionErrPopup().shouldHave(text("Um fortzufahren bestätigen Sie bitte Ihr Newsletter-Abo"));
        subscriptionPopupClose().click();
        return this;
    }

    @Step("Checking success popup with click checkbox in footer subscribe block. Main_page")
    public String checkingSuccessPopupClickCheckbox(String qc) {
        String mail = qc + PKW.CommonMethods.mailRandom();
        subscriptionMailField().setValue(mail);
        subscriptionMailCheckbox().click();
        subscriptionButton().click();
        subscriptionSuccessPopup().shouldHave(text("Wir haben eine E-Mail zur Bestätigung Ihres Abonnements an Ihre E-Mail-Adresse gesendet."));
        subscriptionPopupClose().click();
        return mail;
    }

    @Step("Click btn not found car in Selector. Main_page")
    public Main_page_Logic clickBtnNotFoundCarInSelector() {
        btnNotFoundCarInSelector().click();
        return this;
    }

    @Step("Filling fields in popup From Selector and checking behavior. Main_page")
    public String fillRequiredFieldsInPopupFromSelector(String qc, String vinNumber) {
        String mail = qc + PKW.CommonMethods.mailRandom();
        vinFieldPopupFromSelector().setValue(vinNumber);
        mailFieldPopupFromSelector().setValue(mail);
        checkboxPopupFromSelector().click();
        btnSendPopupFromSelector().click();
        subscriptionSuccessPopup().shouldHave(text("Your message has been sent"));
        subscriptionPopupClose().click();
        return mail;
    }

    @Step(":From Main_page")
    public Main_page_Logic checkingDatenschutzerklarungLinkBehavior(SelenideElement orderDatenschutzerklarungLink) {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(orderDatenschutzerklarungLink, "underline solid rgb(0, 0, 0)");
        return this;
    }

    @Step(":From Main_page")
    public Main_page_Logic checkingPrivacyPolicyLinkBehavior(SelenideElement PrivacyPolicyLink) {
        new CommonMethods().checkingPrivacyPolicyLinkBehavior(PrivacyPolicyLink, "underline solid rgb(0, 0, 0)");
        return this;
    }

    @Step(":registration form. Main_page")
    public Main_page_Logic checkingDatenschutzerklarungLinkBehaviorRegistrationForm() {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(datenschutzerklarungLinkInRegPopup(), "underline solid rgb(17, 57, 188)");
        return this;
    }

    @Step("Click the FAQ link in the footer. Main_page")
    public FAQ_static_page_Logic clickFooterFaqLink() {
        footerFaqLink().click();
        return page(FAQ_static_page_Logic.class);
    }

    @Step("Click the Uber Uns link in the footer. Main_page")
    public Uber_uns_static_page_Logic clickFooterUberUnsLink() {
        footerUberUnsLink().click();
        return page(Uber_uns_static_page_Logic.class);
    }

    @Step("Click the Widerruf link in the footer. Main_page")
    public Widerruf_static_page_Logic clickFooterWiderrufLink() {
        footerWiderrufLink().click();
        return page(Widerruf_static_page_Logic.class);
    }

    // Search bar
    @Step("Input text in search bar. Main_page")
    public Main_page_Logic inputTextInSearchBar(String text) {
        searchBar().setValue(text);
        return this;
    }

    @Step("Click tooltip in search by exact text {exactTooltipText}. Main_page")
    public Oe_number_page_Logic clickTooltipInSearchByExactText(String exactTooltipText) {
        tooltipOfSearch().shouldBe(visible);
        tooltipOfElementsSearch().filter(exactText(exactTooltipText)).shouldHaveSize(1).get(0).click();
        return page(Oe_number_page_Logic.class);
    }

    @Step("Click the Zahlung link in the footer. Main_page")
    public Zahlung_static_page_Logic clickFooterZahlungLink() {
        footerZahlungLink().click();
        return page(Zahlung_static_page_Logic.class);
    }

    @Step("Click the Impressum link in the footer. Main_page")
    public Impressum_static_page_Logic clickFooterImpressumLink() {
        footerImpressumLink().click();
        return page(Impressum_static_page_Logic.class);
    }

    @Step("Click the Austauschartikel link in the footer. Main_page")
    public Austauschartikel_static_page_Logic clickFooterAustauschartikelLink() {
        footerAustauschartikelLink().click();
        return page(Austauschartikel_static_page_Logic.class);
    }

    @Step("Click the Kontakt link in the footer. Main_page")
    public Contact_static_page_Logic clickFooterKontaktLink() {
        footerKontaktLink().click();
        return page(Contact_static_page_Logic.class);
    }

    @Step("Check elements in the promotion block in the header. Main_page")
    public Main_page_Logic checkHeaderPromotionElements() {
        headerPromotionContainer().shouldBe(visible);
        headerPromotionText().shouldBe(visible);
        Assert.assertFalse(headerPromotionText().text().isEmpty());
        headerPromotionTextEnd().shouldBe(visible);
        Assert.assertFalse(headerPromotionTextEnd().text().isEmpty());
        headerPromotionTime().shouldBe(visible);
        return page(Main_page_Logic.class);
    }

    @Step("Check promotion text in the footer of the site. Main_page")
    public Main_page_Logic checkFooterPromotionTextElements() {
        textInTheFooterBottom().shouldBe(visible);
        Assert.assertFalse(textInTheFooterBottom().text().isEmpty());
        for (int i = 0; i < promotionTextInTheFooter().size(); i++) {
            promotionTextInTheFooter().get(i).shouldBe(visible);
            Assert.assertFalse(promotionTextInTheFooter().get(i).text().isEmpty());
            promotionTextMehrButton().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("Check mehr button functionality in the promotion text. Main_page")
    public Main_page_Logic checkMehrButtonFunctionalityInPromotionText() {
        promotionTextMehrButtonSingle().scrollIntoView("{block: \"center\"}");
        for (int i = 0; i < promotionTextMehrButton().size(); i++) {
            promotionTextMehrButton().get(i).shouldBe(visible).hover();
            popUpAfterHoverOnMehrButton().get(i).shouldBe(visible);
            Assert.assertFalse(popUpAfterHoverOnMehrButton().get(i).text().isEmpty());
            promotionTextInTheFooter().get(i).hover();
            popUpAfterHoverOnMehrButton().get(i).shouldNotBe(visible);
        }
        return this;
    }

    @Step("Check rating elements in the header. Main_page")
    public Main_page_Logic checkHeaderRatingElements() throws SQLException {

        ratingLineHeader().shouldBe(visible);
        ratingStarLineHeader().shouldBe(visible);
        progressStarHeader().shouldBe(visible);
        ratingCountLineHeader().shouldBe(visible);
        Assert.assertFalse(headerPromotionText().text().isEmpty());
        ratingBewertungenHeader().shouldBe(visible);
        Assert.assertFalse(ratingBewertungenHeader().text().isEmpty());
        linkRatingLineHeader().click();
        checkingContainsUrl(new DataBase("PKW").getFullRouteByRouteAndSubroute("prod", "DE", "main", "shop_reviews"));
        back();

        return page(Main_page_Logic.class);
    }

    @Step("Check agb and app links in the header. Main_page")
    public Main_page_Logic checkHeaderAppAndAgbLinks() throws SQLException {

        CommonMethods commonMethods = new CommonMethods();

        appHeader().shouldBe(visible);
        Assert.assertFalse(appHeader().text().isEmpty());
        appLinkHeader().click();
        commonMethods.checkingUrlAndCloseTab("autoparts.shop&referrer=af_tranid%3Dy-4ovca32uIymGsNUCSNuw%26pid%3Ddesctop");
        agbLinkHeader().shouldBe(visible);
        Assert.assertFalse(agbLinkHeader().text().isEmpty());
        agbLinkHeader().click();
        checkingContainsUrl(new DataBase("PKW").getFullRouteByRouteAndSubroute("prod", "DE", "main", "static_page_agb"));
        back();

        return page(Main_page_Logic.class);
    }

    @Step("Check header navigation line (navigation by the site). Main_page")
    public Main_page_Logic checkHeaderNavigationLine() throws SQLException {

        headerNavigationLine().shouldBe(visible);
        headerModelsMenu().shouldBe(visible).hover();
        headerModelsDropList().shouldBe(visible);
        headerPartsMenu().shouldBe(visible).hover();
        headerPartsDropList().shouldBe(visible);
        headerMenuReifen().click();
        checkingContainsUrl(new DataBase("PKW").getRouteByRouteName("DE", "main_tyres"));
        back();
        headerMenuAutolampen().click();
        checkingContainsUrl(new DataBase("PKW").getFullRouteByRouteAndSubroute("prod", "DE", "main", "index_autolamp"));
        back();
        headerMenuMotorol().click();
        checkingContainsUrl(new DataBase("PKW").getFullRouteByRouteAndSubroute("prod", "DE", "main", "motoroil"));
        back();
        headerMenuChemicals().click();
        checkingContainsUrl(new DataBase("PKW").getFullRouteByRouteAndSubroute("prod", "DE", "main", "index_chemicals"));
        back();
        headerMenuAccessories().click();
        checkingContainsUrl(new DataBase("PKW").getFullRouteByRouteAndSubroute("prod", "DE", "main", "index_accessories"));
        back();
        headerMenuInstruments().click();
        checkingContainsUrl(new DataBase("PKW").getFullRouteByRouteAndSubroute("prod", "DE", "main", "index_instruments"));
        headerMenuHome().click();
        checkingContainsUrl(new DataBase("PKW").getFullRouteByRouteName("prod", "DE", "main"));
        return page(Main_page_Logic.class);
    }

    @Step("Positive sending the Subscribe form in the footer. Main_page")
    public Main_page_Logic positiveSendingSubscribeForm(String mail) {
        emailInputForSubscribeFooter().setValue(mail);
        checkboxForSubscribeFooter().click();
        yesButtonForSubscribeFooter().click();
        successPopUpForSubscribeFooter().shouldBe(visible);
        return this;
    }

    @Step("Error tooltip in the Subscribe form in the footer. Main_page")
    public Main_page_Logic errorMessageInTheSubscribeForm() {
        yesButtonForSubscribeFooter().click();
        errorTooltipForSubscribeFooter().shouldBe(visible).shouldHave(text("E-mail Adresse"));
        emailInputForSubscribeFooter().setValue("11111111");
        yesButtonForSubscribeFooter().click();
        errorTooltipForSubscribeFooter().shouldBe(visible).shouldHave(text("E-mail Adresse"));
        return this;
    }

    @Step("Error pop-up in the Subscribe form in the footer after sending the form without checking the checkbox. Main_page")
    public Main_page_Logic errorPopUpInTheSubscribeForm(String mail) {
        emailInputForSubscribeFooter().setValue(mail);
        yesButtonForSubscribeFooter().click();
        errorPopUpInTheSubscribeFooter().shouldBe(visible).shouldHave(text("Um fortzufahren bestätigen Sie bitte Ihr Newsletter-Abo"));
        return this;
    }

    @Step("Click the Versand link in the footer. Main_page")
    public Versand_static_page_Logic clickFooterVersandLink() {
        footerVersandLink().click();
        return page(Versand_static_page_Logic.class);
    }

    @Step("Click the AGB link in the header. Main_page")
    public AGB_static_page_Logic clickHeaderAgbLink() {
        agbLinkHeader().click();
        return page(AGB_static_page_Logic.class);
    }

    @Step("Click the datenschutz link in the footer. Main_page")
    public Datenschutz_static_page_Logic clickFooterDatenchutzLink() {
        footerDateschutzLink().click();
        return page(Datenschutz_static_page_Logic.class);
    }

    @Step("check Count Of added  Product In Basket At Header. Main_page")
    public Main_page_Logic checkCountOfProductInBasketAtHeader(int expectedCount) {
        Assert.assertEquals(Integer.parseInt(basketCounterInHeader().getText()), expectedCount);
        return this;
    }
}

