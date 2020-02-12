package ATD;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

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

  @Step("Click search KBA button with correct filled in fields")
  public Catalog_page clickKbaBtn() {
    selectorKbaBtn().click();
    return page(Catalog_page.class);
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
