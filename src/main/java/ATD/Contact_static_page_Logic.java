package ATD;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static ATD.CommonMethods.mailRandom;
import static ATD.CommonMethods.testNumberThatPutOrderInTest;

public class Contact_static_page_Logic extends Contact_static_page {

    public String fillRequiredFieldsNoOrderAndCheckBehavior(String qc) {
        String mail = qc + mailRandom();
        openedTabTitle().shouldHave(Condition.text("Ich habe noch keine Bestellung aufgegeben"));
        mailFieldNoOrder().setValue(mail);
        articleFieldNoOrder().setValue("10000/1");
        firstProductInArticleAutocomplete().click();
        addingAnotherProductBtn().shouldBe(Condition.appear);
        sendenButton().click();
        successPopup().shouldBe(Condition.appear);
        successPopupCloseButton().click();
        openedTabTitle().shouldHave(Condition.text("Ich habe noch keine Bestellung aufgegeben"));
        return mail;
    }

    public Contact_static_page_Logic switchToOrderTab(){
        openedTabTitle().shouldHave(Condition.text("Ich habe noch keine Bestellung aufgegeben"));
        orderTab().click();
        openedTabTitle().shouldHave(Condition.text("Ich habe schon einen Auftrag erteilt"));
        return this;
    }
    public String fillRequiredFieldsOrderAndCheckBehavior(String qc) {
        String mail = qc + mailRandom();

        orderFormOrderField().setValue("7916699");
        orderFormTelField().setValue(testNumberThatPutOrderInTest);
        orderEmailInput().setValue(mail);
        sendenButton().click();
        successPopup().shouldBe(Condition.appear);
        successPopupCloseButton().click();
        openedTabTitle().shouldHave(Condition.text("Ich habe schon einen Auftrag erteilt"));
        return mail;
    }

    public Contact_static_page_Logic checkingDatenschutzerklarungLinkBehavior(SelenideElement orderDatenschutzerklarungLink) {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(orderDatenschutzerklarungLink, "underline solid rgb(0, 0, 0)");
        return this;
    }

}
