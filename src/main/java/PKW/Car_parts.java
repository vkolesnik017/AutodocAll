package PKW;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;


public class Car_parts {

    SelenideElement linkDatenschutzerklärungInSoft404Block() {
        return $x("//label[@id='privacy_policy1']/a");
    }

    SelenideElement soft404Block() {
        return $x("//div[@class='notification-form-block']");
    }

    SelenideElement subscriptionMailFieldInSoft404Block() {
        return $(byXpath("//input[@id='form_Soft404[subscribe-email]']"));
    }

    SelenideElement subscriptionMailCheckboxInSoft404Block() {
        return $(byXpath("//div[@class='check-row']/input[@id='subscribe_on']"));
    }

    SelenideElement subscriptionButtonInSoft404Block() {
        return $(byXpath("//button[@class='subscribe-btn']"));
    }

    SelenideElement subscriptionSuccessPopup() {
        return $(byXpath("//div[@id='popup_update']//div[@class='txt']"));
    }

    SelenideElement subscriptionPopupClose() {
        return $(byXpath("//div[@class='popup ']//*[@class='close']/span[2]"));
    }


}
