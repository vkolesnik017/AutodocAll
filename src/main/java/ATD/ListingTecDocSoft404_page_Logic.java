package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;

public class ListingTecDocSoft404_page_Logic extends ListingTecDocSoft404_page {

  @Step("Fill in form and send request")
  public ListingTecDocSoft404_page sendRequestForm() {
    emailForm().setValue("testatd@gmail.com");
    newsletterSubscriptionCheckbox().click();
    submitBtn().click();
    successPopupAfterSubscribeOnProductsForCar().shouldHave(text("Mit großer Freude informieren wir Sie, sobald die Produkte auf Lager sind"));
    return this;
  }

  @Step("Check validation email field")
  public ListingTecDocSoft404_page_Logic checkValidationEmail() {
    submitBtn().click();
    errorTooltipOfEmailField().shouldHave(text("Bitte geben Sie eine gültige E-mail Adresse an"));
    return this;
  }

  @Step("Check validation checkbox newsletter subscribe")
  public ListingTecDocSoft404_page_Logic checkValidationCheckboxNewsletter() {
    emailForm().setValue("testatd@gmail.com");
    submitBtn().click();
    popupErrorConfirmYourNewsletter().shouldHave(text("Um fortzufahren bestätigen Sie bitte Ihr Newsletter-Abo"));
    return this;
  }

}
