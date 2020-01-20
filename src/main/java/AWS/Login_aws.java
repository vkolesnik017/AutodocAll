package AWS;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class Login_aws {

  private final String email = "autodoc-6731";
  private final String password = "0e8f9863";

  private SelenideElement loginField() {
    return $(byId("login"));
  }

  private SelenideElement passwordField() {
    return $(byId("password"));
  }

  private SelenideElement loginButton() {
    return $(byXpath("//button[@class='btn btn-default btn-sm pull-right']"));
  }

  @Step
  public Main_aws loginInAws() {
    loginField().setValue(email);
    passwordField().setValue(password);
    loginButton().click();
//    loginButton().shouldNotBe(Condition.visible);
    return page(Main_aws.class);
  }

  @Step
  public Main_aws loginInAwsWithOpen() {
    open("https://aws.autodoc.de/login");
    loginField().setValue(email);
    passwordField().setValue(password);
    loginButton().click();
    loginButton().shouldNotBe(visible);
    return page(Main_aws.class);
  }

}
