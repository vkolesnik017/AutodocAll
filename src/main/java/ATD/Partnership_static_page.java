package ATD;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Partnership_static_page {

    SelenideElement mainLogo() {
        return $(By.cssSelector(".logo>a>img"));
    }

    SelenideElement title() {
        return $(byCssSelector(".partner_title"));
    }

    SelenideElement logoAboveText() {
        return $(byXpath("//*[@class='col one']/img"));
    }

    SelenideElement mainTextFromPage() {
        return $(By.xpath("//*[@class='col one']/p"));
    }

    SelenideElement firstBlockOfAchievements() {
        return $(By.xpath("//*[@class='col two']"));
    }

    SelenideElement secondBlockOfAchievements() {
        return $(By.xpath("//*[@class='col three']"));
    }

    SelenideElement offerTitle() {
        return $(By.cssSelector(".p_title"));
    }

    SelenideElement leftBlockWithOffer() {
        return $(By.xpath("//*[@class='part_with_us']/ul/li[1]"));
    }

    SelenideElement imageOfLeftBlockWithOffer() {
        return $(By.xpath("//*[@class='part_with_us']/ul/li[1]/div/img"));
    }

    SelenideElement rightBlockWithOffer() {
        return $(By.xpath("//*[@class='part_with_us']/ul/li[2]"));
    }

    SelenideElement imageOfRightBlockWithOffer() {
        return $(By.xpath("//*[@class='part_with_us']/ul/li[2]/div/img"));
    }

    SelenideElement addPartnerBlock() {
        return $(By.cssSelector(".add_part"));
    }

    SelenideElement mapText() {
        return $(By.xpath("//*[@class='add_part']/p"));
    }

    SelenideElement sendShipForm() {
        return $(By.cssSelector("#send_ship"));
    }

    SelenideElement submitShipDataButton() {
        return $(By.cssSelector("#submit_ship_data"));
    }

    SelenideElement emailError() {
        return $(By.cssSelector(".email_error"));
    }

    SelenideElement sendShipFormMailField() {
        return $(By.xpath("//input[@id='email']"));
    }

    SelenideElement datenschutzerklarungLink() {
        return $(By.cssSelector("#privacy_policy_partnership > a"));
    }

    SelenideElement acceptSubscriptionCheckbox() {
        return $x("//div[@class='accept_subscription_checkbox row_checkbox']");
    }

    SelenideElement sendShipFormSuccesPopup() {
        return $(By.id("popup_update"));
    }

    SelenideElement sendShipFormSuccesPopupCloseBtn() {
        return $(By.xpath("//div[@id='popup_update']//div[@class='buttons-inner']/a"));
    }


}
