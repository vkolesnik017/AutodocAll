package mailinator;

import ATD.PasswordRecovery_page;
import ATD.PasswordRecovery_page_Logic;
import com.codeborne.selenide.SelenideElement;

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

  public Mailinator openEmail(String email) {
    open("https://www.mailinator.com");
    $(byId("addOverlay")).setValue(email).pressEnter();
    return this;
  }

  public Mailinator openLetter(int numberLetter) {
    sleep(3000);
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
