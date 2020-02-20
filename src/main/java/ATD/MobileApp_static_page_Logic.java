package ATD;


import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static ATD.CommonMethods.mailRandom;

public class MobileApp_static_page_Logic extends MobileApp_static_page {

    @Step(":registration form. MobileApp_static_page")
    public MobileApp_static_page_Logic checkingDatenschutzerklarungLinkBehavior() {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(datenschutzerklarungLink(), "underline solid rgb(0, 0, 0)");
        return this;
    }

    public String fillingFieldsAndCheckBehaviorSubscribeForm(String qc){
        String mail = qc + mailRandom();
        mailFieldInSubscribeForm().setValue(mail);
        checkboxInSubscribeForm().click();
        getMailBtnInSubscribeForm().click();
        successPopupInSubscribeForm().shouldHave(Condition.text("Herzlichen Glückwünsch! Jetzt haben Sie einen 10% Rabatt für Ihren ersten Einkauf über die Handy-App."));
        successPopupCloseBtnInSubscribeForm().click();
        mailFieldInSubscribeForm().shouldBe(Condition.visible);
        return mail;
    }

}
