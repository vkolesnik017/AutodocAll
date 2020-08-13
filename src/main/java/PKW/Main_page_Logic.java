package PKW;


import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static PKW.CommonMethods.mailRandom;
import static PKW.CommonMethods.password;
import static com.codeborne.selenide.Condition.*;
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
        subscriptionSuccessPopup().shouldHave(text("Viel Spaß beim Shoppen!"));
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

    @Step("Click the FAQ link. Main_page")
    public FAQ_static_page_Logic clickFooterFaqLink() {
        footerFaqLink().click();
        return page(FAQ_static_page_Logic.class);
    }

    @Step("Click the Uber Uns link. Main_page")
    public Uber_uns_static_page_Logic clickFooterUberUnsLink() {
        footerUberUnsLink().click();
        return page(Uber_uns_static_page_Logic.class);
    }

    @Step("Click the Widerruf. Main_page")
    public Widerruf_static_page_Logic clickFooterWiderrufLink() {
        footerWiderrufLink().click();
        return page(Widerruf_static_page_Logic.class);
    }

    @Step("Click the Zahlung. Main_page")
    public Zahlung_static_page_Logic clickFooterZahlungLink() {
        footerZahlungLink().click();
        return page(Zahlung_static_page_Logic.class);
    }
}
