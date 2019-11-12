package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class CartAllData_page {

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

}
