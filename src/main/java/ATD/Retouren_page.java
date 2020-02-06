package ATD;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.io.File;
import java.sql.SQLException;

import static ATD.CommonMethods.getCurrentShopFromJSVarInHTML;
import static ATD.CommonMethods.getPriceFromElement;
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

  private SelenideElement selectMountedOrNot() {
    return $x("//select[@name='mounting']");
  }

  private ElementsCollection optionsInSelectMountedOrNot() {
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

  private SelenideElement errorPopupForReturn() {
    return $x("//div[@id='popup_update']//h3[text()='Fehler']");
  }

  private SelenideElement closePopupButton() {
    return $x("//div[@id='popup_update']//*[@class='close']");
  }

  private SelenideElement productPriceForReturn() {
    return $x("//div[contains(@class,'popup-retoure__wrap')]//td[4]");
  }

  public Float getProductPriceForReturn() {
    return getPriceFromElement(productPriceForReturn());
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
    if (selectMountedOrNot().isDisplayed()) {
      ElementsCollection options = optionsInSelectMountedOrNot().shouldHave(sizeNotEqual(0));
      int randomOption = (int) (Math.random() * options.size()) + 1;
      selectMountedOrNot().selectOption(randomOption);
    }
    return this;
  }

  @Step("Checking to appear pop up error of return after click send button when do not select product for return for all cause returns")
  public Retouren_page chekingToAppearPopupErrorsOfReturn() {
    ElementsCollection causes = causesReturnInSelect().shouldHave(sizeNotEqual(0));
    for (int cause = 0; cause <= causes.size(); cause++) {
      selectWithCausesReturn().selectOption(cause);
      sleep(2000);
      sendenButton().click();
      errorPopupForReturn().shouldBe(visible);
      closePopupButton().click();
      errorPopupForReturn().shouldBe(not(visible));
    }
    return this;
  }

  @Step("Checking translation of causes on the retoure page")
  public Retouren_page checkingTranslateOfCausesForReturn() throws SQLException {
    ElementsCollection causes = causesReturnInSelect().shouldHave(sizeNotEqual(0));
    for (SelenideElement cause : causes) {
      String valueText = cause.getValue();
      String expectedText = new DataBase().getRetoureCauseTranslate(getCurrentShopFromJSVarInHTML(), valueText);
      cause.shouldHave(exactText(expectedText));
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
