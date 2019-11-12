package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class Product_page {

  public Cart_page cartClick() {
    new Main_page().cartClick();
    return page(Cart_page.class);
  }

  public SelenideElement cartIcon() {
    return $(byCssSelector(".header-cart__count"));
  }

  public SelenideElement buyButton() {
    return $(byCssSelector(".product-button>a"));
  }

  public SelenideElement closeBtnOfPopupOtherCategory() {
    return $(byCssSelector(".popup-other-cat__close"));
  }

  public SelenideElement grayButton() {
    return $(byCssSelector(".button.not_active>a"));
  }

  // locators of prices with Currencies
  public SelenideElement priceWithoutDiscount() {
    return $(byCssSelector(".product-old-price>span"));
  }

  public SelenideElement productPrice() {
    return $(byCssSelector(".product-new-price"));
  }

  public SelenideElement productInfoUnderPrice() {
    return $(byCssSelector(".product-inkl-info"));
  }

  // locators in popup of cart
  public SelenideElement firstProductPriceInPopupOfCart() {
    return $(byCssSelector(".row-price"));
  }

  public SelenideElement totalPriceInPopupOfCart() {
    return $(byCssSelector(".row-right>p"));
  }

  // locators in popup of gray button for subscription for product which is not stock
  public SelenideElement emailFieldInPopUpOfGrayBtn() {
    return $(byId("form_AvailabilityReminder[email]"));
  }

  public SelenideElement sendButtonInPopUpOfGrayBtn() {
    return $(byCssSelector(".popup-available__button"));
  }

  public SelenideElement checkboxInPopUpOfGrayBtn() {
    return $(byCssSelector(".popup-available__label"));
  }

  public SelenideElement closeSuccessPopUpOfGrayBtn() {
    return $(byXpath("//div[@class='popup_top']//a[@class='close']"));
  }

}
