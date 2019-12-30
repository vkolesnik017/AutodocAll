package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;

public class ListingTecDocSoft404_page {

  public SelenideElement blockOfNoFindProduct() {
    return $("#no_product_find");
  }

  public SelenideElement blockWithCategories() {
    return $(".maintenance-block");
  }

  public SelenideElement blockWithTopProducts() {
    return $(".top-small-products");
  }

  public SelenideElement emailForm() {
    return $(byId("form_email"));
  }

  public SelenideElement newsletterSubscriptionCheckbox() {
    return $(byId("subscribe_on"));
  }

  public SelenideElement submitBtn() {
    return $(byName("submit"));
  }

  public SelenideElement successPopupAfterSubscribeOnProductsForCar() {
    return $(".popup_email_not");
  }

  public SelenideElement errorTooltipOfEmailField() {
    return $(".wrong_soft");
  }

  public SelenideElement popupErrorConfirmYourNewsletter() {
    return $(byId("popup_update"));
  }
}
