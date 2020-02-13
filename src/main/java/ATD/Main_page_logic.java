package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class Main_page_logic extends Main_page {

  // Selector kba

  // Only for DE
  @Step("Fill in KBA fields")
  public Main_page_logic fillNumberKba(String numberForFirstField, String numberForSecondField) {
    firstFieldKBA().setValue(numberForFirstField);
    secondFieldKBA().setValue(numberForSecondField);
    return this;
  }

  // For all shop, except DE
  @Step("Fill in KBA field")
  public Main_page_logic fillNumberKba(String kbaNumber) {
    firstFieldKBA().setValue(kbaNumber);
    return this;
  }

  @Step("Click search KBA button")
  public Catalog_page clickKbaBtn() {
    selectorKbaBtn().click();
    return page(Catalog_page.class);
  }

  // Car selector popup
  @Step("Choose brand in car selector popup")
  public Main_page_logic chooseBrandInCarSelectorPopup(String brandName) {
    brandSelectorInCarSelectorPopup().selectOption(brandName);
    Wait().until(webDriver -> brandSelectorInCarSelectorPopup().getSelectedText().equals(brandName));
    return this;
  }

  @Step("Choose model in car selector popup")
  public Main_page_logic chooseModelInPopupSelectorForChooseCar(String modelNumberValue) {
    modelSelectorInCarSelectorPopup().selectOptionByValue(modelNumberValue);
    return this;
  }

  @Step("Click reset button in car selector popup")
  public Main_page_logic resetCarSelectorPopup() {
    resetCarBtnInCarSelectorPopup().click();
    resetCarBtnInCarSelectorPopup().shouldBe(not(visible));
    return this;
  }

  // Vertical car selector popup
  @Step("Choose brand in vertical car selector")
  public Main_page_logic chooseBrandInVerticalCarSelector(String brandName) {
      if (hiddenVerticalSelector().isDisplayed()) {
        hiddenVerticalSelector().click();
      }
    brandSelectorInVerticalCarSelector().selectOption(brandName);
    Wait().until(webDriver -> brandSelectorInVerticalCarSelector().getSelectedText().equals(brandName));
    return this;
  }

  @Step("Choose model in vertical car selector")
  public Main_page_logic chooseModelInVerticalCarSelector(String modelNumberValue) {
    modelSelectorInVerticalCarSelector().selectOptionByValue(modelNumberValue);
    sleep(1500);
    return this;
  }

  @Step("Choose type in vertical car selector")
  private Main_page_logic chooseTypeInVerticalCarSelector(String typeNumberValue) {
    typeSelectorInVerticalCarSelector().selectOptionByValue(typeNumberValue);
    return this;
  }

  @Step("Choose brand, model, type in vertical car selector")
  public Main_page_logic chooseBrandModelTypeInSelector(String brandName, String modelNumberValue, String typeNumberValue) {
    chooseBrandInVerticalCarSelector(brandName);
    chooseModelInVerticalCarSelector(modelNumberValue);
    chooseTypeInVerticalCarSelector(typeNumberValue);
    return this;
  }

  @Step("Click reset button in vertical car selector")
  public Main_page_logic resetVerticalCarSelector() {
    resetBtnInVerticalCarSelector().click();
    resetBtnInVerticalCarSelector().shouldBe(not(visible));
    return this;
  }

  @Step("Click search button in vertical car selector when NOT selected all fields")
  public Main_page_logic clickSearchBtnInVerticalSelectorWhenNotSelectedAllFields() {
    searchBtnInVerticalSelector().click();
    return this;
  }

  @Step("Click search button in vertical car selector when SELECTED all fields, for redirect to catalog page")
  public Catalog_page clickSearchBtnInVerticalSelectorWhenSelectedAllFields() {
    searchBtnInVerticalSelector().click();
    return page(Catalog_page.class);
  }

}
