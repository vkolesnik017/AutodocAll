package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;

public class Tyres_dimension_page_Logic extends Tyres_dimension_page {

    @Step("Displaying customer feedback PopUp .Tyres_dimension_page")
    public Tyres_dimension_page_Logic displayingCustomerFeedbackPopUp() {
        productsList().shouldBe(visible);
        btnOutOfStock().get(0).click();
        feedBackPopUp().should(appear);
        return this;
    }

    @Step("displaying of popUp pop-up error about invalid email when trying to send mail for feedback with selected checkbox. Tyres_dimension_page")
    public Tyres_dimension_page_Logic displayingOfPopUPAboutInvalidEmailAndWithCheckbox(String email) {
        feedBackPopUp().shouldBe(visible);
        setValueInEmailFieldOfPopUp(email);
        clickOnBtnSubscription();
        popUPAboutInvalidEmail().should(appear).shouldHave(text("Das Feld E-mail muss eine gültige E-Mail-Adresse enthalten."));
        return this;
    }

    @Step("set value in email field of PopUp .Tyres_dimension_page")
    public Tyres_dimension_page_Logic setValueInEmailFieldOfPopUp(String email) {
        emailFieldOfFeedBackPopUp().shouldBe(visible).setValue(email);
        return this;
    }

    @Step("click on Subscription button .Tyres_dimension_page")
    public Tyres_dimension_page_Logic clickOnBtnSubscription() {
        btnSendOfFeedBackPopUp().click();
        return this;
    }
}
