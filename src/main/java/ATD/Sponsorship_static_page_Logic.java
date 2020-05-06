package ATD;


import io.qameta.allure.Step;

import static ATD.CommonMethods.mailRandom;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;

public class Sponsorship_static_page_Logic extends Sponsorship_static_page {

    @Step("Scrolling to send ship form and checking visibility. Sponsorship_static_page")
    public Sponsorship_static_page_Logic scrollToSendShipForm() {
        sendShipForm().scrollTo();
        sendShipForm().shouldBe(visible);
        return this;
    }

    @Step(":on Sponsorship_static_page")
    public Sponsorship_static_page_Logic checkingDatenschutzerklarungLinkBehavior() {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(datenschutzerklarungLink(), "underline solid rgb(27, 69, 95)");
        return this;
    }

    @Step("Filling fields and checking behavior of send shipping form. Sponsorship_static_page")
    public String fillingFieldsAndCheckBehaviorSendShipForm() {
        String mail = "qc525_" + mailRandom();
        sendShipFormMailField().setValue(mail);
        sendShipFormSendenBtn().click();
        sendShipFormSuccesPopup().shouldBe(appear);
        sendShipFormSuccesPopupCloseBtn().click();
        return mail;
    }
}
