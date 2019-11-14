package AWS;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class ProductSearch_aws {

  public final String urlPage = "https://aws.autodoc.de/products/search";

  SelenideElement dangerousCargoSelector() {
    return $(byId("form_filterSearch[hazardEnabled]"));
  }

  SelenideElement inStockSelector() {
    return $(byId("form_filterSearch[onStorage]"));
  }

  SelenideElement searchBtn() {
    return $(byId("form_submit"));
  }

  SelenideElement idProductsInTable() {
    return $(byXpath("//*[@id='order_products_list']//tr/td[1]"));
  }

  SelenideElement dangerousProductsColumn() {
    return $(byXpath("//*[@id='order_products_list']//tr/td[22]"));
  }

  public SelenideElement articleProductsInTable() {
    return $(byXpath("//*[@id='order_products_list']//tr/td[3]"));
  }

  @Step
  public String chooseFilterForDangerousProductsAndGetId() {
    dangerousCargoSelector().selectOption("Включен");
    inStockSelector().selectOption("есть");
    searchBtn().click();
    dangerousProductsColumn().shouldHave(Condition.text("yes"));
    return idProductsInTable().getText();
  }
}
