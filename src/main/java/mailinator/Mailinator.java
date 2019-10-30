package mailinator;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Mailinator {

  // The Mailinator Email System. It is Email Workflow Testing tool.

  public SelenideElement letter(int numberLetter) {
    return $(byXpath("//*[contains(@class,'pointer')]/../tr["+ numberLetter +"]"));
  }

  public Mailinator openEmail(String email) {
    open("https://www.mailinator.com");
    $(byId("inboxfield")).setValue(email).pressEnter();
    return this;
  }
}
