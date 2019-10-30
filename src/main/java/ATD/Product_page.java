package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class Product_page {

  SelenideElement grayButton() {
    return $(byCssSelector(".button.not_active>a"));
  }

  SelenideElement emailFieldInPopUpAvailable() {
    return $(byId("form_AvailabilityReminder[email]"));
  }

  SelenideElement sendenButtonInPopUpAvailable() {
    return $(byCssSelector(".popup-available__button"));
  }

  SelenideElement checkboxInPopUpAvailable() {
    return $(byCssSelector(".popup-available__label"));
  }

  SelenideElement closeSuccessPopUpAvailable() {
    return $(byXpath("//div[@class='popup_top']//a[@class='close']"));
  }

}
