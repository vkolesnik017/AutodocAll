package ATD;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.Wait;
import static ATD.CommonMethods.mailRandom;
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

  @Step("Click search KBA button")
  public Catalog_page clickKbaBtn() {
    selectorKbaBtn().click();
    return page(Catalog_page.class);
  }

  //Car selector popup
  @Step("Choose brand in car selector popup")
  public Main_page_logic chooseBrandInCarSelectorPopup(String brandName) {
    brandSelectorInCarSelectorPopup().selectOption(brandName);
    try {
      Wait().until(webDriver -> brandSelectorInCarSelectorPopup().getSelectedText().equals(brandName));
    } catch (TimeoutException e) {
      Assert.fail("Brand name doesn't equals: " + brandName);
    }
    return this;
  }

  @Step("Choose model in car selector popup")
  public Main_page_logic chooseModelInPopupSelectorForChooseCar(String modelNumberValue) {
    modelSelectorInCarSelectorPopup().selectOptionByValue(modelNumberValue);
    return this;
  }

  @Step("Click reset button in car selector popup")
  public Main_page_logic resetSelector() {
    resetCarBtnInCarSelectorPopup().click();
    resetCarBtnInCarSelectorPopup().shouldBe(not(visible));
    return this;
  }


  // GDPR footer
  public Main_page_logic scrollToFooterSubscribeBlock() {
      footerForm().scrollTo();
      footerForm().shouldBe(Condition.visible);
      return this;
  }

    public Main_page_logic checkingDatenschutzerklarungLinkBehaviorInReviewsForm() {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(clickDatenschutzInSubscribeBlock(), "none solid rgb(0, 104, 215)");
        return this;
    }

    public Main_page_logic checkingErrorPopupUnclickCheckbox(String qc) {
        String mail = qc + mailRandom();
        subscriptionMailField().setValue(mail);
        subscriptionButton().click();
        subscriptionErrPopup().shouldHave(Condition.text("Um fortzufahren bestätigen Sie bitte Ihr Newsletter-Abo"));
        subscriptionPopupClose().click();
      return this;
    }

    public String checkingErrorPopupClickCheckbox(String qc) {
        String mail = qc + mailRandom();
        subscriptionMailField().setValue(mail);
        subscriptionMailCheckbox().click();
        subscriptionButton().click();
        subscriptionSuccessPopup().shouldHave(Condition.text("Herzlichen Dank! Viel Spaß beim Shoppen!"));
        subscriptionPopupClose().click();
        return mail;
    }

}
