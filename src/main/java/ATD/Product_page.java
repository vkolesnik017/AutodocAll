package ATD;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.ex.ElementShould;
import com.codeborne.selenide.ex.UIAssertionError;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class Product_page {

  @Step
  public Product_page openProductPageById(String route, String idProduct) {
    open(route + "/a/" + idProduct);
    return this;
  }

  @Step
  public Cart_page cartClick() {
    new Main_page().cartClick();
    return page(Cart_page.class);
  }

  public SelenideElement numberBasket() {
    return $(byCssSelector(".code"));
  }

  public SelenideElement cartIcon() {
    return $(byCssSelector(".header-cart__count"));
  }

  public SelenideElement buyButton() {
    return $(byCssSelector(".product-button>a"));
  }

  SelenideElement closeBtnOfPopupOtherCategory() {
    return $(byCssSelector(".popup-other-cat__close"));
  }

  public SelenideElement grayButton() {
    return $(byCssSelector(".button.not_active>a"));
  }

  @Step
  public Product_page addProductToCart() {
    checkNumberBasketAndRefreshPageIfNot();
    sleep(3000); // TODO для стабилизации. Без слипа иногда добавленный товар исчезает из корзины после перехода в неё, решается в SITES-2830
    buyButton().click();
    try {
      firstProductPriceInPopupOfCart().shouldBe(visible);
    } catch (UIAssertionError e) {
      closePopupOtherCategoryIfYes();
      buyButton().click();
      firstProductPriceInPopupOfCart().shouldBe(visible);
    }
    return this;
  }

  @Step
  public Product_page checkNumberBasketAndRefreshPageIfNot() {  // TODO Бывает при открытии страницы не подгружается номер корзины и товар не добавляется в корзину, причина не известна, что бы стабилизировать тесты добавлен этот метод
    try {
      numberBasket().shouldBe(visible);
    } catch (ElementShould e) {
      refresh();
      numberBasket().shouldBe(visible);
    }
    return this;
  }

  @Step
  public Product_page closePopupOtherCategoryIfYes() {
    try {
      closeBtnOfPopupOtherCategory().waitUntil(visible, 2500);
      closeBtnOfPopupOtherCategory().click();
      closeBtnOfPopupOtherCategory().shouldBe(not(visible));
    } catch (ElementNotFound e) {
    }
    return this;
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

  //Methods and locators for Selector Horizontal
  @Step("Choose brand in selector")
  public Product_page chooseBrandInSelector(String brandName) {
    brandSelector().selectOption(brandName);
    return this;
  }

  @Step("Choose model in selector")
  public Product_page chooseModelInSelector(String modelNumberValue) {
    modelSelector().selectOptionByValue(modelNumberValue);
    return this;
  }

  @Step("Choose type in selector")
  public Product_page chooseTypeInSelector(String typeNumberValue) {
    typeSelector().selectOptionByValue(typeNumberValue);
    return this;
  }

  @Step("Choose brand, model, type in horizontal selector")
  public Product_page chooseBrandModelTypeInSelector(String brandName, String modelNumberValue, String typeNumberValue) {
    chooseBrandInSelector(brandName);
    chooseModelInSelector(modelNumberValue);
    chooseTypeInSelector(typeNumberValue);
    return this;
  }

  @Step
  // return current chosen value from selector
  public String getChosenValueFromSelector(SelenideElement selector) {
    String selectedValue = executeJavaScript("return arguments[0].selectedOptions[0].innerText", selector);
    return selectedValue;
  }

  public SelenideElement brandSelector() {
    return $("#form_maker_id");
  }

  public SelenideElement modelSelector() {
    return $("#form_model_id");
  }

  public SelenideElement typeSelector() {
    return $("#form_car_id");
  }

  public SelenideElement selectorSearchBtn() {
    return $(".search_button");
  }

  public SelenideElement errorTooltipOfBrandSelector() {
    return $(byId("selector-error-tooltip"));
  }

  public SelenideElement errorToolTipOfModelSelector() {
    return $(byId("selector-error-tooltip-model"));
  }

  public SelenideElement errorToolTipOfTypeSelector() {
    return $(byId("selector-error-tooltip-car"));
  }

  public SelenideElement errorToolTipOfKbaSelector() {
    return $(byId("kba-error-tooltip"));
  }

  public SelenideElement infoBlockWithSelectedCar() {
    return $(".car-match-block>p>b");
  }

  public SelenideElement resetBtnSelector() {
    return $(byId("reset_selector_form"));
  }

  //Methods and locators for Selector kba
  @Step
  public Product_page fillNumberKba(String numberForFirstField, String numberForSecondField) {
    $(byId("kba1")).setValue(numberForFirstField);
    $(byId("kba2")).setValue(numberForSecondField);
    return this;
  }

  public SelenideElement selectorKbaBtn() {
    return $(".kba_submit");
  }

}
