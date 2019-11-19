package ATD;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class CartAllData_page {

  @Step
  public SelenideElement searchProductByID(String idProduct) {
    return $(byCssSelector("[data-article_id='" + idProduct + "']"));
  }

  public SelenideElement addressInfo() {
    return $(byCssSelector(".info-user-cart__info"));
  }

  // locator only for CH
  public SelenideElement vatPriceInHead() {
    return $(byXpath("//*[contains(@class,'top')]/div[4]/span[2]"));
  }

  public SelenideElement vatPriceInTotalOrder() {
    return $(byXpath("//*[@class='alldata-bottom']//div[6]/span[2]"));
  }

  // locators of prices with Currencies
  public SelenideElement totalOrderPriceInHead() {
    return $(byXpath("//*[contains(@class,'top')]/div[2]/span[2]"));
  }

  public SelenideElement productPrice() {
    return $(byCssSelector(".price"));
  }

  public SelenideElement totalProductPrice() {
    return $(byCssSelector(".total-price"));
  }

  public SelenideElement priceOfAllProducts() {
    return $(byXpath("//*[@class='order-summary ']/div[2]/span[2]"));
  }

  public SelenideElement deliveryPrice() {
    return $(byXpath("//*[@class='order-summary ']/div[3]/span[2]"));
  }

  public SelenideElement totalOrderPrice() {
    return $(byXpath("//*[@class='alldata-bottom']//*[contains(@class,'total')]/span[2]"));
  }

  public SelenideElement priceOfSafeOrder() {
    return $(byCssSelector(".bestelen-block__col>label"));
  }

  // locators of popup of dangerous product
  public SelenideElement popupOfDangerousProduct() {
    return $(byCssSelector(".delivery-limit-popup"));
  }

  public SelenideElement closePopupBtn() {
    return $(byCssSelector(".close_popup.close"));
  }

  public SelenideElement areaOutOfPopup() {
    return $(byId("overlay"));
  }

  public SelenideElement deleteProductBtnInPopup() {
    return $(byCssSelector(".delete_items"));
  }

  public SelenideElement changeAddressBtnInPopup() {
    return $(byCssSelector(".change_address"));
  }

}
