package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.page;

public class Main_page_logic extends Main_page {

  //Selector kba

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

  @Step("Click search KBA button with correct filled in fields")
  public Catalog_page clickKbaBtn() {
    selectorKbaBtn().click();
    return page(Catalog_page.class);
  }

}
