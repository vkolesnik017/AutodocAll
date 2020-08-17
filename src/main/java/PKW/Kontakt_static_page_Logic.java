package PKW;

import io.qameta.allure.Step;
import org.testng.Assert;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.attribute;

public class Kontakt_static_page_Logic extends Kontakt_static_page {

    @Step("Checks the title, contact form visibility, Send button functionality, error pop-up visibility, close pop-up button, info tooltip, validation message . Kontakt_static_page")
    public Kontakt_static_page_Logic checkElementsOnThePage() {

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
