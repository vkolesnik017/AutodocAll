package PKW;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import static PKW.CommonMethods.mailRandom;
import static PKW.CommonMethods.testNumberThatPutOrderInTest;
import static com.codeborne.selenide.Condition.*;


public class Contact_static_page_Logic extends Contact_static_page {

    @Step(":on Contact_static_page")
    public Contact_static_page_Logic checkingDatenschutzerklarungLinkBehavior(SelenideElement orderDatenschutzerklarungLink) {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(orderDatenschutzerklarungLink, "underline solid rgb(0, 0, 0)");
        return this;
    }

    @Step("Filling fields in ORDER tab and checking behavior. Contact_static_page")
    public String fillRequiredFieldsOrderAndCheckBehavior(String qc) {
        String mail = qc + mailRandom();
        orderFormIdOrderField().setValue("7916699");
        orderFormTelField().setValue(testNumberThatPutOrderInTest);
        orderFormEmailField().setValue(mail);
        sendenButtonOrderTab().click();
        successPopup().shouldHave(text("Ihre Nachricht wurde an uns übermittelt."));
        successPopupCloseButton().click();
        orderOpenedTab().shouldBe(visible);
        return mail;
    }

    @Step("Switching to ORDER tab. Contact_static_page")
    public Contact_static_page_Logic switchToOrderTab() {
        orderOpenedTab().shouldBe(visible);
        contentTab2().click();
        noOrderOpenedTab().shouldBe(visible);
        return this;
    }

    @Step("Filling fields in NO ORDER tab and checking behavior. Contact_static_page")
    public String fillRequiredFieldsNoOrderAndCheckBehavior(String qc) {
        String mail = qc + mailRandom();
        noOrderOpenedTab().shouldBe(visible);
        noOrderFormEmailField().setValue(mail);
        idDetailFieldNoOrder().setValue("10000/1");
        firstProductInArticleAutocomplete().click();
        addingAnotherIdDetailFieldNoOrder().shouldBe(appear);
        sendenButtonNoOrderTab().click();
        successPopup().shouldHave(text("Ihre Nachricht wurde an uns übermittelt."));
        successPopupCloseButton().click();
        noOrderOpenedTab().shouldBe(visible);
        return mail;
    }
    @Step("Checks the title, contact form visibility, Send button functionality, error pop-up visibility, close pop-up button, info tooltip, validation message . Kontakt_static_page")
    public Contact_static_page_Logic checkElementsOnThePage() {

        blockKontaktForm().shouldBe(visible);
        titleKontaktForm().shouldBe(visible);
        contactInfoBlock().shouldBe(visible);
        activeEmail().shouldHave(attribute("href", "mailto:info@pkwteile.de"));

        sendButtonForm().click();
        fehlerPopupForm().shouldBe(visible);
        Assert.assertFalse(fehlerPopupForm().text().isEmpty());
        closeTextButtonPopup().click();
        sendButtonForm().click();
        closeButtonPopup().click();
        validationMessageOrder().shouldBe(visible).shouldHave(text("Bestellnummer"));
        validationMessageNomer().shouldBe(visible).shouldHave(text("Telefonnummer"));
        validationMessageEmail().shouldBe(visible).shouldHave(text("E-Mail"));

        switchToTabTwo().click();
        sendButtonFormTabTwo().click();
        fehlerPopupForm().shouldBe(visible);
        Assert.assertFalse(fehlerPopupForm().text().isEmpty());
        closeTextButtonPopup().click();
        sendButtonFormTabTwo().click();
        closeButtonPopup().click();
        validationMessageEmailSecondTab().shouldBe(visible).shouldHave(text("E-Mail"));
        validationMessageDetailsNomerSecondTab().shouldBe(visible).shouldHave(text("Ersatzteile"));

        vinCodeHoverButtonTooltip().hover();
        vinCodeTooltip().shouldBe(visible);
        return this;
    }
}
