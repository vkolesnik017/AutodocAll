package ATD;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static ATD.CommonMethods.mailRandom;
import static ATD.CommonMethods.testNumberThatPutOrderInTest;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.sleep;

public class Contact_static_page_Logic extends Contact_static_page {

    public String fillRequiredFieldsNoOrderAndCheckBehavior(String qc) {
        String mail = qc + mailRandom();
        openedTabTitle().shouldHave(text("Ich habe noch keine Bestellung aufgegeben"));
        mailFieldNoOrder().setValue(mail);
        articleFieldNoOrder().setValue("10000/1");
        firstProductInArticleAutocomplete().click();
        addingAnotherProductBtn().shouldBe(appear);
        sendenButton().click();
        successPopup().shouldBe(appear);
        successPopupCloseButton().click();
        openedTabTitle().shouldHave(text("Ich habe noch keine Bestellung aufgegeben"));
        return mail;
    }

    public Contact_static_page_Logic switchToOrderTab() {
        openedTabTitle().shouldHave(text("Ich habe noch keine Bestellung aufgegeben"));
        orderTab().click();
        openedTabTitle().shouldHave(text("Ich habe schon einen Auftrag erteilt"));
        return this;
    }

    public String fillRequiredFieldsOrderAndCheckBehavior(String qc) {
        String mail = qc + mailRandom();

        orderFormOrderField().setValue("7916699");
        orderFormTelField().setValue(testNumberThatPutOrderInTest);
        orderEmailInput().setValue(mail);
        sendenButton().click();
        successPopup().shouldBe(appear);
        successPopupCloseButton().click();
        openedTabTitle().shouldHave(text("Ich habe schon einen Auftrag erteilt"));
        return mail;
    }

    public Contact_static_page_Logic checkingDatenschutzerklarungLinkBehavior(SelenideElement orderDatenschutzerklarungLink) {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(orderDatenschutzerklarungLink, "underline solid rgb(0, 0, 0)");
        return this;
    }

    @Step("Checks for the presence of a Contacts block and elements inside it")
    public Contact_static_page_Logic checkContactsBlock() {
        contactBlock().shouldBe(visible);
        logoBlock().shouldBe(visible);
        infoDescriptionBlock().shouldBe(visible);
        addressBlock().shouldBe(visible);
        addressField().shouldBe(visible);
        emailField().shouldBe(visible);
        webSiteField().shouldBe(visible);
        return this;
    }

    @Step("Checks for the presence of a Already Place Order block and elements inside it")
    public Contact_static_page_Logic checkAlreadyPlacedOrderBlock() {
        haveOrderBlock().shouldNotBe(visible);
        haveOrderButton().click();
        haveOrderBlock().shouldBe(visible);
        submitOrderButton().click();
        orderIdError().shouldBe(visible);
        phoneNumberError().shouldBe(visible);
        emailError().shouldBe(visible);
        return this;
    }

    @Step("Checks for the presence of a Non-Placed Order block and elements inside it")
    public Contact_static_page_Logic checkNonPlacedOrderBlock() {
        haveNoOrderButton().click();
        haveNoOrderBlock().shouldBe(visible);
        submitNoOrderButton().click();
        noOrderEmailError().shouldBe(visible);
        noOrderDetailError().shouldBe(visible);
        return this;
    }
}
