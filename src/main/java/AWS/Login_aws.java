package AWS;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class Login_aws {

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

  @Step
  public Main_aws loginInAws() {
    open("https://aws.autodoc.de/");
    loginField().setValue(email);
    System.out.println(email);
    passwordField().setValue(password);
    System.out.println(password);
    sleep(2000);
    loginButton().click();
    loginButton().shouldBe(Condition.not(Condition.visible));
    return page(Main_aws.class);
  }

}
