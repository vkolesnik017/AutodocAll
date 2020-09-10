package ATD;


import Common.DataBase;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.io.File;
import java.sql.SQLException;

import static ATD.CommonMethods.getCurrentShopFromJSVarInHTML;
import static ATD.CommonMethods.getPriceFromElement;
import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Retouren_page_Logic extends Retouren_page{

  @Step("Finding order. Retouren_page")
  public Retouren_page_Logic findOrder(String index, String idOrder) {
    indexField().setValue(index);
    idOrderField().setValue(idOrder);
    suchenButton().click();
    return this;
  }

  // methods for REKLAMATION block

  @Step("Get product price for return. Retouren_page")
  public Float getProductPriceForReturn() {
    return getPriceFromElement(productPriceForReturn());
  }

  @Step("Clicking checkbox. Retouren_page")
  public Retouren_page_Logic clickCheckbox() {
    sleep(3000);
    checkboxesToProducts().click();
    return this;
  }

  @Step("Choose random cause return in selector. Retouren_page")
  public Retouren_page_Logic chooseRandomCauseReturnInSelect() {
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

  @Step("Checking to appear pop up error of return after click send button when do not select product for return for all cause returns. Retouren_page")
  public Retouren_page_Logic chekingToAppearPopupErrorsOfReturn() {
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

  @Step("Checking translation of causes on the retoure page. Retouren_page")
  public Retouren_page_Logic checkingTranslateOfCausesForReturn() throws SQLException {
    ElementsCollection causes = causesReturnInSelect().shouldHave(sizeNotEqual(0));
    for (SelenideElement cause : causes) {
      String valueText = cause.getValue();
      String expectedText = new DataBase("ATD").getTranslate("retoure_translate", getCurrentShopFromJSVarInHTML(), valueText);
      cause.shouldHave(exactText(expectedText));
    }
    return this;
  }

  @Step("Filling message form. Retouren_page")
  public Retouren_page_Logic fillInFormForMessage() {
    formForMessage().setValue("test");
    return this;
  }

  @Step("Adding file if file block is displayed. Retouren_page")
  public Retouren_page_Logic addFileIfIsDisplayedFileBlock() {
    if (fileBlock().isDisplayed()) {
      inputFileBlock().uploadFile(new File("src/main/java/files/Attach_me.odt"));
    }
    return this;
  }

  @Step("Click senden button with correct data and checking success popup. Retouren_page")
  public Retouren_page_Logic clickSendenButtonWithCorrectData() {
    sendenButton().click();
    successPopup().shouldBe(visible);
    return this;
  }


}
