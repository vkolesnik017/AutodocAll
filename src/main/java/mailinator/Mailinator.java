package mailinator;

import ATD.PasswordRecovery_page;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class Mailinator {

  // The Mailinator Email System. It is Email Workflow Testing tool.

  public SelenideElement letter(int numberLetter) {
    return $(byXpath("//*[contains(@class,'pointer')]/../tr["+ numberLetter +"]"));
  }

  public SelenideElement linkInRestorePasswordLetter() {
    return $(byCssSelector(".forgot>a"));
  }

  public Mailinator openEmail(String email) {
    open("https://www.mailinator.com");
    $(byId("inboxfield")).setValue(email).pressEnter();
    return this;
  }

  public Mailinator openLetter(int numberLetter) {
    letter(numberLetter).click(60, 10);
    switchTo().frame("msg_body");
    return this;
  }

  public PasswordRecovery_page clickLinkRecoveryPasswordInLetter() {
    linkInRestorePasswordLetter().click();
    switchTo().window("recovery");
    return page(PasswordRecovery_page.class);
  }
}
