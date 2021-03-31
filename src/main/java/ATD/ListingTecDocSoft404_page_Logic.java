package ATD;

import io.qameta.allure.Step;

import static ATD.CommonMethods.testMail;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class ListingTecDocSoft404_page_Logic extends ListingTecDocSoft404_page {

  @Step("Fill in form and send request. ListingTecDocSoft404_page")
  public ListingTecDocSoft404_page sendRequestForm() {
    emailForm().setValue("QC_479_autotestMail@mailinator.com");
    newsletterSubscriptionCheckbox().click();
    submitBtn().click();
    successPopupAfterSubscribeOnProductsForCar().shouldHave(text("Mit großer Freude informieren wir Sie, sobald die Produkte auf Lager sind"));
    return this;
  }

  @Step("Check validation email field. ListingTecDocSoft404_page")
  public ListingTecDocSoft404_page_Logic checkValidationEmail() {
    submitBtn().click();
    errorTooltipOfEmailField().shouldHave(text("Bitte geben Sie eine gültige E-mail Adresse an"));
    return this;
  }

  @Step("Check validation checkbox newsletter subscribe. ListingTecDocSoft404_page")
  public ListingTecDocSoft404_page_Logic checkValidationCheckboxNewsletter() {
    emailForm().setValue(testMail);
    submitBtn().click();
    popupErrorConfirmYourNewsletter().shouldHave(text("Um fortzufahren bestätigen Sie bitte Ihr Newsletter-Abo"));
    return this;
  }

  @Step("Check soft404 on tecdoc listing. ListingTecDocSoft404_page")
  public ListingTecDocSoft404_page_Logic check404TecdocListing() {
    blockOfNoFindProduct().shouldBe(visible);
    blockWithCategories().shouldBe(visible);
    blockWithTopProducts().shouldBe(visible);
    return this;
  }

  @Step("Check no list products on 404. ListingTecDocSoft404_page")
  public ListingTecDocSoft404_page_Logic checkNoListProductsOn404() {
    blockOfNoFindProduct().shouldBe(visible);
    new Listing_page().listProducts().shouldBe(not(visible));
    return this;
  }


}
