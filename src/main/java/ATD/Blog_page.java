package ATD;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Blog_page {

    SelenideElement datenschutzerklarungLink() {
        return $(By.cssSelector("#privacy_policy_blog>a"));
    }

    SelenideElement sendFormMailField() {
        return $(By.cssSelector("#blog_subscribe_form>input"));
    }

    SelenideElement subscribeCheckbox() {
        return $(By.id("isSubscribe"));
    }

    SelenideElement submitMailButton() {
        return $(By.xpath("//button[@class='send']"));
    }

    SelenideElement sendMailFormSuccesPopup() {
        return $(By.id("news_subscribe"));
    }

    SelenideElement sendMailFormSuccesPopupCloseBtn() {
        return $(By.xpath("//div[@class='buttons']//div[@class='buttons-inner']/a"));
    }
}
