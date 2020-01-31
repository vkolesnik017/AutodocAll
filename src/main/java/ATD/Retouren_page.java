package ATD;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.io.File;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class Retouren_page {

  private SelenideElement indexField() {
    return $(byId("form_index"));
  }

  private SelenideElement idOrderField() {
    return $(byId("form_orderId"));
  }

  private SelenideElement suchenButton() {
    return $(".returnOrder");
  }

  @Step
  public Retouren_page findOrder(String index, String idOrder) {
    indexField().setValue(index);
    idOrderField().setValue(idOrder);
    suchenButton().click();
    return this;
  }

  // locators and methods for REKLAMATION block

  private ElementsCollection causesReturnInSelect() {
    return $$x("//select[@name='causes']/option[position()>1]");
  }

  private SelenideElement selectWithCausesReturn() {
    return $x("//select[@name='causes']");
  }

  private SelenideElement checkboxesToProducts() {
    return $x("//label[@class='checkbox color']//span");
  }

  private SelenideElement selectWithProductCondition() {
    return $x("//select[@name='mounting']");
  }

  private ElementsCollection optionsProductConditionInSelect() {
    return $$x("//select[@name='mounting']/option[position()>1]");
  }

  private SelenideElement formForMessage() {
    return $(byId("form_message"));
  }

  private SelenideElement fileBlock() {
    return $x("//label[@class='file-block']");
  }

  private SelenideElement inputFileBlock() {
    return $x("//label[@class='file-block']/input");
  }

  private SelenideElement sendenButton() {
    return $(".inside-application__submit");
  }

  private SelenideElement successPopup() {
    return $x("//div[@class='popup ']//h3[text()='Vielen Dank!']");
  }

  @Step
  public Retouren_page clickCheckbox() {
    sleep(3000);
    checkboxesToProducts().click();
    return this;
  }

  @Step
  public Retouren_page chooseRandomCauseReturnInSelect() {
      ElementsCollection causes = causesReturnInSelect().shouldHave(sizeNotEqual(0));
      int randomCause = (int) (Math.random() * causes.size()) + 1;
      selectWithCausesReturn().selectOption(randomCause);
      sleep(2000);
    if (selectWithProductCondition().isDisplayed()) {
      ElementsCollection options = optionsProductConditionInSelect().shouldHave(sizeNotEqual(0));
      int randomOption = (int) (Math.random() * options.size()) + 1;
      selectWithProductCondition().selectOption(randomOption);
    }
    return this;
  }

  @Step
  public Retouren_page fillInFormForMessage() {
    formForMessage().setValue("test");
    return this;
  }

  @Step
  public Retouren_page addFileIfIsDisplayedFileBlock() {
    if (fileBlock().isDisplayed()) {
      inputFileBlock().uploadFile(new File("src/main/java/files/Attach_me.odt"));
    }
    return this;
  }

  @Step
  public Retouren_page clickSendenButtonWithCorrectData() {
    sendenButton().click();
    successPopup().shouldBe(visible);
    return this;
  }


}
