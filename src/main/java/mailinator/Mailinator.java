package mailinator;

import ATD.PasswordRecovery_page_Logic;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class Mailinator {

  // The Mailinator Email System. It is Email Workflow Testing tool.

  public SelenideElement letterInfo(int numberLetter) {
    return $(byXpath("//*[contains(@class,'pointer')]/../tr["+ numberLetter +"]"));
  }

  public SelenideElement letter(int numberLetter) {
    return $(byXpath("//*[contains(@class,'pointer')]/../tr["+ numberLetter + "]//a"));
  }

  public SelenideElement linkFAQemailConfirm() { return $(".btn-wrapper > a"); }

  private SelenideElement linkInRestorePasswordLetter() {
    return $(byCssSelector(".forgot>a"));
  }

  private SelenideElement infoTotalPriceInEmail() {
    return $x("//p[@class='info-total__price']");
  }

  private SelenideElement regularDeliveryPriceInEmail() {
    return $x("//table[@class='info-total']//tr[3]//td[2]//p");
  }

  private SelenideElement percentageOfVatInEmail() {
    return $x("//table[@class='info-total']//tr[6]//td[2]//p");
  }

  @Step("Checks for text containing VAT percentage in email. Mailinator")
  public Mailinator checkTextContainingVatPercentageInEmail(String textWithPercentageOfVAT) {
    percentageOfVatInEmail().shouldHave(text(textWithPercentageOfVAT));
    return this;
  }

  @Step("Checks regular delivery price. Mailinator")
  public Mailinator checkRegularDeliveryPriceInEmail(String regularDeliveryPrice) {
    regularDeliveryPriceInEmail().shouldHave(text(regularDeliveryPrice));
    return this;
  }

  @Step("Get total price in email. Mailinator")
  public Double getTotalPriceInEmail(){
    String realPrice = infoTotalPriceInEmail().getText();
    realPrice = realPrice.substring(0, realPrice.indexOf(" ")).replaceAll(",",".");
    Double totalPrice = Double.parseDouble(realPrice);
    return totalPrice;
  }

  public Mailinator openEmail(String email) {
    open("https://www.mailinator.com");
    $(byId("addOverlay")).setValue(email).pressEnter();
    return this;
  }

  public Mailinator openLetter(int numberLetter) {
    letter(numberLetter).shouldBe(appear);
    letter(numberLetter).click();
    switchTo().frame("msg_body");
    return this;
  }

  public PasswordRecovery_page_Logic clickLinkRecoveryPasswordInLetter() {
    linkInRestorePasswordLetter().click();
    switchTo().window("recovery");
    return page(PasswordRecovery_page_Logic.class);
  }
}
