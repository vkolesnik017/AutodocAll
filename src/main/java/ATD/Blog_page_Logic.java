package ATD;


import io.qameta.allure.Step;

import static ATD.CommonMethods.mailinatorMailRandom;
import static com.codeborne.selenide.Condition.appear;

public class Blog_page_Logic extends Blog_page{

    @Step(":on Blog_page")
    public Blog_page_Logic checkingDatenschutzerklarungLinkBehavior() {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(datenschutzerklarungLink(), "underline solid rgb(0, 0, 0)");
        return this;
    }

    @Step("Filling fields in send mail form and checking behavior. Blog_page")
    public String fillingFieldsAndCheckBehaviorSendMailForm() {
        String mail = "qc515_" + mailinatorMailRandom();
        sendFormMailField().setValue(mail);
        subscribeCheckbox().click();
        submitMailButton().click();
        sendMailFormSuccesPopup().shouldBe(appear);
        sendMailFormSuccesPopupCloseBtn().click();
        return mail;
    }

}
