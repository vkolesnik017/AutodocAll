package ATD;


import io.qameta.allure.Step;

import static ATD.CommonMethods.mailRandom;
import static com.codeborne.selenide.Condition.appear;

public class Sales_page_Logic extends Sales_page {

    @Step(":on Sales_page")
    public Sales_page_Logic checkingDatenschutzerklarungLinkBehavior() {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(datenschutzerklarungLink(), "underline solid rgb(0, 0, 0)");
        return this;
    }

    @Step("Filling fields and checking behavior of send mail form. Sales_page")
    public String fillingFieldsAndCheckBehaviorSendMailForm() {
        String mail = "qc517_" + mailRandom();
        sendFormMailField().setValue(mail);
        subscribeCheckbox().click();
        submitMailButton().click();
        sendMailFormSuccesPopup().shouldBe(appear);
        sendMailFormSuccesPopupCloseBtn().click();
        return mail;
    }

}
