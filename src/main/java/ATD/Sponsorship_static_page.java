package ATD;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static ATD.CommonMethods.mailRandom;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class Sponsorship_static_page {

    public SelenideElement sponsorsHeader() {
        return $(By.cssSelector(".sponsors-header"));
    }

    public SelenideElement raceGallery() {
        return $(By.xpath("//*[@class='race-gallery__wrapper']"));
    }

    public SelenideElement mapText() {
        return $(By.xpath("//*[@class='add_part']/p"));
    }

    public SelenideElement sendShipForm() {
        return $(By.cssSelector("#send_ship"));
    }

    public SelenideElement submitShipDataButton() {
        return $(By.cssSelector("#submit_ship_data"));
    }

    public SelenideElement emailError() {
        return $(By.cssSelector(".email_error"));
    }

    private SelenideElement datenschutzerklarungLink() {
        return $(By.xpath("//label[@id='privacy_policy_sponsorship']/a"));
    }

    private SelenideElement sendShipFormMailField() {
        return $(By.xpath("//div[@class='row inp_t']//input[@id='email']"));
    }

    private SelenideElement sendShipFormSendenBtn() {
        return $(By.id("submit_ship_data"));
    }

    private SelenideElement sendShipFormSuccesPopup() {
        return $(By.id("popup_update"));
    }

    private SelenideElement sendShipFormSuccesPopupCloseBtn() {
        return $(By.xpath("//div[@id='popup_update']//div[@class='buttons-inner']/a"));
    }


    public Sponsorship_static_page scrollToSendShipForm() {
        sendShipForm().scrollTo();
        sendShipForm().shouldBe(visible);
        return this;
    }

    public Sponsorship_static_page checkingDatenschutzerklarungLinkBehavior() {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(datenschutzerklarungLink());
        return this;
    }

    public String fillingFieldsAndCheckBehaviorSendShipForm() {
        String mail = "qc525_" + mailRandom();
        sendShipFormMailField().setValue(mail);
        sendShipFormSendenBtn().click();
        sendShipFormSuccesPopup().shouldBe(appear);
        sendShipFormSuccesPopupCloseBtn().click();
        return mail;
    }
}
