package PKW;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;


public class Contact_static_page {

    SelenideElement orderFormIdOrderField() {
        return $x("//input[@id='form_Order[orderId]']");
    }

    SelenideElement orderFormTelField() {
        return $x("//input[@id='form_Order[phone]']");
    }

    SelenideElement orderFormEmailField() {
        return $x("//input[@id='form_Order[email]']");
    }

    SelenideElement noOrderFormEmailField() {
        return $x("//input[@id='form_NoOrder[email]']");
    }

    public SelenideElement orderDatenschutzerklarungLinkInContacts() {
        return $(By.cssSelector("#Orderprivacy_policy>a"));
    }

    SelenideElement sendenButtonOrderTab() {
        return $x("//div[8]//button[1]");
    }

    SelenideElement sendenButtonNoOrderTab() {
        return $x("//div[11]//button[1]");
    }

    SelenideElement successPopup() {
        return $(By.xpath("//div[@class='popup ']//div[@class='txt']"));
    }

    SelenideElement successPopupCloseButton() {
        return $(By.xpath("//div[@class='popup_content']//a"));
    }

    SelenideElement orderOpenedTab() {
        return $(By.xpath("//div[@class='contact-tabs__content'][1]"));
    }

    SelenideElement noOrderOpenedTab() {
        return $(By.xpath("//div[@class='contact-tabs__content'][2]"));
    }

    SelenideElement contentTab2() {
        return $x("//label[@for='content-tab-2']");
    }

    public SelenideElement noOrderDatenschutzerklarungLinkInContacts() {
        return $(By.cssSelector("#NoOrderprivacy_policy>a"));
    }

    SelenideElement idDetailFieldNoOrder() {
        return $x("//input[@id='form_NoOrder[article][0][articleNo]']");
    }

    SelenideElement addingAnotherIdDetailFieldNoOrder() {
        return $x("//button[@class='add_article_no']");
    }

    SelenideElement firstProductInArticleAutocomplete() {
        return $(By.xpath("//div[@id='contact-page-autocomplete-suggestions-list']//div[@data-index='0']"));
    }



}
