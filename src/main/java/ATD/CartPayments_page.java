package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class CartPayments_page {

  public SelenideElement payPalBtn() {
    return $(byId("paypal"));
  }

  public CartPayments_page choosePayPal() {
    payPalBtn().click();
    return this;
  }

  SelenideElement nextBtn() {
    return $(byCssSelector("[id='apply_payment']>a"));
  }

  public CartAllData_page nextBtnClick() {
    nextBtn().click();
    return page(CartAllData_page.class);
  }
}
