package AWS;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class Login_aws {

  public static void main(String[] args) {
    new Login_aws().loginInAws();
  }

  private final String email = "autodoc-6731";
  private final String password = "0e8f9863";

  SelenideElement loginField() {
    return $(byId("login"));
  }

  SelenideElement passwordField() {
    return $(byId("password"));
  }

  SelenideElement loginButton() {
    return $(byXpath("//*[@class='btm_b clearfix']/button"));
  }

  public Main_aws loginInAws() {
    open("https://aws.autodoc.de/");
    loginField().setValue(email);
    passwordField().setValue(password);
    loginButton().click();
    loginButton().shouldBe(Condition.not(Condition.visible));
    return page(Main_aws.class);
  }






}
