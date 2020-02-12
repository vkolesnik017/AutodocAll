package ATD;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static ATD.CommonMethods.mailRandom;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

public class Blog_page {

    private SelenideElement datenschutzerklarungLink() {
        return $(By.cssSelector("#privacy_policy_blog>a"));
    }

    private SelenideElement sendFormMailField() {
        return $(By.cssSelector("#blog_subscribe_form>input"));
    }

    private SelenideElement subscribeCheckbox() {
        return $(By.id("isSubscribe"));
    }

    private SelenideElement submitMailButton() {
        return $(By.xpath("//button[@class='send']"));
    }

    private SelenideElement sendMailFormSuccesPopup() {
        return $(By.id("news_subscribe"));
    }

    private SelenideElement sendMailFormSuccesPopupCloseBtn() {
        return $(By.xpath("//div[@class='buttons']//div[@class='buttons-inner']/a"));
    }



    public Blog_page checkingDatenschutzerklarungLinkBehavior() {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(datenschutzerklarungLink(), "underline solid rgb(0, 0, 0)");
        return this;
    }


    public String fillingFieldsAndCheckBehaviorSendMailForm() {
        String mail = "qc515_" + mailRandom();
        sendFormMailField().setValue(mail);
        subscribeCheckbox().click();
        submitMailButton().click();
        sendMailFormSuccesPopup().shouldBe(appear);
        sendMailFormSuccesPopupCloseBtn().click();
        return mail;
    }

}
