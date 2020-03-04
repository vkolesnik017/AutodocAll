package ATD;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static ATD.CommonMethods.waitWhileRouteBecomeExpected;
import static ATD.CommonMethods.waitingWhileLinkBecomeExpected;

public class Category_car_list_page_Logic extends Category_car_list_page {

  @Step("Verify name route equals category_car_list. Category_car_list_page")
  public Category_car_list_page_Logic verifyNameRouteEqualsCategoryCarList() {
    waitWhileRouteBecomeExpected("category_car_list");
    return this;
  }

  //form soft 404

    @Step("Checking behavior of soft form 404.Using mail {mail} Category_car_list_page")
    public Category_car_list_page_Logic checkingBehaviorSoft404(String mail) {
        mailFieldSoftForm().setValue(mail);
        submitBtnSoftForm().click();
        errPopupSoftForm().shouldHave(Condition.text("Um fortzufahren bestätigen Sie bitte Ihr Newsletter-Abo"));
        closeErrPopupSoftForm().click();
        subscribeCheckboxSoftForm().click();
        submitBtnSoftForm().click();
        successPopupSoftForm().shouldBe(Condition.appear);
        closeSuccessPopupSoftForm().shouldHave(Condition.text("Einkauf fortsetzen")).click();
        waitingWhileLinkBecomeExpected("https://www.autodoc.de/ersatzteile/genesis/g90/g90/123335-3-3-t-gdi");
        return this;
    }

    @Step(":soft 404 form. Category_car_list_page")
    public Category_car_list_page_Logic checkingDatenschutzerklarungLinkBehaviorSoftForm() {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(datenschutzerklarungLinkSoftForm(), "underline solid rgb(0, 0, 0)");
        return this;
    }
}
