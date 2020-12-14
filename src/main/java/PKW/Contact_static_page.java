package PKW;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byXpath;
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

    SelenideElement blockKontaktForm() {
        return $(byXpath("//*[@class='contact-tabs__block']"));
    }

    SelenideElement titleKontaktForm() {
        return $(byXpath("//div[@class='contact-tabs']//*[@class='contact-content__title']"));
    }

    SelenideElement sendButtonForm() {
        return $(byXpath("//*[@id='contact_page_callback_form']/div/div[1]/div[8]")); }

    SelenideElement fehlerPopupForm() {
        return $(byXpath("//*[@class='popup ']")); }

    SelenideElement switchToTabTwo() {
        return $(byXpath("//*[@for='content-tab-2']")); }

    SelenideElement sendButtonFormTabTwo() {
        return $(byXpath("//*[@id='contact_page_callback_form']/div/div[2]/div[11]")); }

    SelenideElement closeTextButtonPopup() {
        return $(byXpath("//*[@class='close']//*[contains(text(),'Schließen')]")); }

    SelenideElement closeButtonPopup() {
        return $(byXpath("//*[@class='popup_top']//*[@class='close']")); }

    SelenideElement validationMessageOrder() {
        return $(byXpath("//*[@data-name='error_Order[orderId]']")); }

    SelenideElement validationMessageNomer() {
        return $(byXpath("//*[@data-name='error_Order[phone]']")); }

    SelenideElement validationMessageEmail() {
        return $(byXpath("//*[@data-name='error_Order[email]']")); }

    SelenideElement vinCodeHoverButtonTooltip() {
        return $(byXpath("//*[@class='contact-tabs__vin-title']//*[@class='contact-tabs__show']")); }

    SelenideElement vinCodeTooltip() {
        return $(byXpath("//*[@class='contact-tabs__tooltip hidden']")); }

    SelenideElement validationMessageEmailSecondTab() {
        return $(byXpath("//*[@data-name='error_NoOrder[email]']")); }

    SelenideElement validationMessageDetailsNomerSecondTab() {
        return $(byXpath("//*[@data-name='error_NoOrder[article][0][articleNo]']")); }

    SelenideElement contactInfoBlock() {
        return $(byXpath("//*[@class='contact-cont']")); }

    SelenideElement activeEmail() {
        return $(byXpath("//*[@class='info__email']//a")); }
}
