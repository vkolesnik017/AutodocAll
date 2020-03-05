package ATD;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Sales_page {

    SelenideElement datenschutzerklarungLink() {
        return $(By.cssSelector("#privacy_policy1>a"));
    }

    SelenideElement sendFormMailField() {
        return $(By.xpath("//input[@id='subscr']"));
    }

    SelenideElement submitMailButton() {
        return $(By.id("news-yes"));
    }

    SelenideElement subscribeCheckbox() {
        return $(By.name("isSubscribe"));
    }

    SelenideElement sendMailFormSuccesPopup() {
        return $(By.id("news_subscribe"));
    }

    SelenideElement sendMailFormSuccesPopupCloseBtn() {
        return $(By.xpath("//div[@class='buttons']//div[@class='buttons-inner']/a"));
    }

}
