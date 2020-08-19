package PKW;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Versand_static_page {

    SelenideElement subscriptionMailField() {
        return $(byXpath("//input[@id='subscr_footer']"));
    }

    SelenideElement subscriptionButton() {
        return $(byXpath("//a[@id='news_yes_footer']"));
    }

    SelenideElement subscriptionPopupClose() {
        return $(byXpath("//div[@class='popup ']//*[@class='close']/span[2]"));
    }

    SelenideElement subscriptionMailCheckbox() {
        return $(byXpath("//div[@class='footer-subscr__foremail']/input[@id='subscribe_accept_footer']"));
    }

    SelenideElement subscriptionSuccessPopup() {
        return $(byXpath("//div[@id='popup_update']//div[@class='txt']"));
    }

    SelenideElement footerForm() {
        return $(By.xpath("//div[@class='footer-subscr']"));
    }

    public SelenideElement linkDatenschutzerklarungInFooter() {
        return $x("//label[@id='privacy_policy_footer']/a");
    }

}
