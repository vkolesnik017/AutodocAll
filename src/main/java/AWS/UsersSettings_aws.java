package AWS;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class UsersSettings_aws {

  public String urlUsersSettings = "https://aws.autodoc.de/users/6731";

  private SelenideElement languageRadioButton(String language) {
    return $x("//input[@value='"+ language +"']");
  }

  private SelenideElement saveButton() {
    return $(".btn-success");
  }

  public UsersSettings_aws chooseLanguage(String languageDeRuEnPl) {
    if (languageRadioButton(languageDeRuEnPl).is(not(selected))) {
      languageRadioButton(languageDeRuEnPl).click();
      saveButton().click();
      languageRadioButton(languageDeRuEnPl).shouldBe(selected);
      sleep(2000);
    }
    return this;
  }

}
