package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class PasswordRecovery_page {

  SelenideElement passwordField() {
    return $(byId("new_pass"));
  }

  SelenideElement passwordConfirmField() {
    return $(byId("new_pass_confirm"));
  }

  SelenideElement sendButton() {
    return $(byId("recovery_btn_submit"));
  }

  SelenideElement closePopupOfSuccessPasswordRecovery() {
    return $(byXpath("//*[@id='popup_update']//a"));
  }

  public Profile_page fillPasswordFieldsAndClickSubmit(String password) {
    passwordField().setValue(password);
    passwordConfirmField().setValue(password);
    sendButton().click();
    closePopupOfSuccessPasswordRecovery().click();
    return page(Profile_page.class);
  }
}
