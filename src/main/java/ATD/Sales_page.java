package ATD;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static ATD.CommonMethods.mailRandom;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

public class Sales_page {

    private SelenideElement datenschutzerklarungLink() {
        return $(By.cssSelector("#privacy_policy1>a"));
    }

    private SelenideElement sendFormMailField() {
        return $(By.xpath("//input[@id='subscr']"));
    }

    private SelenideElement submitMailButton() {
        return $(By.id("news-yes"));
    }

    private SelenideElement subscribeCheckbox() {
        return $(By.name("isSubscribe"));
    }

    private SelenideElement sendMailFormSuccesPopup() {
        return $(By.id("news_subscribe"));
    }

    private SelenideElement sendMailFormSuccesPopupCloseBtn() {
        return $(By.xpath("//div[@class='buttons']//div[@class='buttons-inner']/a"));
    }


    public Sales_page checkingDatenschutzerklarungLinkBehavior() {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(datenschutzerklarungLink());
        return this;
    }

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
