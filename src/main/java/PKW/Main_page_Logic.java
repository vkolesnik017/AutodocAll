package PKW;


import ATD.Maker_car_list_page_Logic;
import io.qameta.allure.Step;

import static PKW.CommonMethods.mailRandom;
import static PKW.CommonMethods.password;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;

public class Main_page_Logic extends Main_page{

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
    public String fillingRegistrationFields(String qc){
        String mail = qc + mailRandom();
        mailFieldInRegistration().setValue(mail);
        passwordFieldInRegistration().setValue(password);
        confirmPassFieldInRegistration().setValue(password);
        registrationSubmitBtn().click();
        return mail;
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
}
