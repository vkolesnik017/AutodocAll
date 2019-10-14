package ATD;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static ATD.CommonMethods.testMail;
import static ATD.CommonMethods.testNumberThatPutOrderInTest;
import static com.codeborne.selenide.Selenide.$;

class Contact_static_page {

    /*
    Page has two tab. First tab will be not active when page will be open and it name "order", second one which active "noOrder".
     */

    //Body
    public SelenideElement noOrderTab() {
        return $(By.xpath("//div[@class='contact-page__form']/div[@data-tab='no_order']"));
    }

    SelenideElement orderTab() {
        return $(By.xpath("//div[@class='contact-page__form']/div[@data-tab='order']"));
    }

    SelenideElement openedTabTitle() {
        return $(By.xpath("//span[@class='open-tab']"));
    }

    SelenideElement privacyPolicyCheckbox() {
        return $(By.xpath("//div[@class='dropdown-wrapper open-tab']//div[@class='privacy_policy_checkbox']/input"));
    }

    SelenideElement tooltipPrivacyPolicyCheckbox() {
        return $(By.xpath("//div[@class='dropdown-wrapper open-tab']//div[@class='contact-page__form-error privacy-policy-error']"));
    }

    SelenideElement privacyPolicyDatenschutzerklarungLink() {
        return $(By.xpath("//div[@class='dropdown-wrapper open-tab']//div[@class='privacy_policy_checkbox']/label/a"));
    }

    SelenideElement sendenButton() {
        return $(By.xpath("//div[@class='dropdown-wrapper open-tab']/div[last()]/button"));
    }

    SelenideElement successPopup() {
        return $(By.xpath("//div[@class='popup ']"));
    }

    SelenideElement successPopupButton() {
        return $(By.xpath("//div[@class='popup_content']//a"));
    }

    Contact_static_page fillRequiredFieldsNoOrder() {
        $(By.xpath("//input[@name='NoOrder[email]']")).setValue(testMail);
        $(By.xpath("//input[@class='article_no article_no_input']")).setValue("10000/1");
        $(By.xpath("//div[@class='autocomplete-suggestion'][@data-index=0]")).click();
        return this;
    }

    Contact_static_page fillRequiredFieldsOrder() {
        $(By.xpath("//input[@name='Order[email]']")).setValue(testMail);
        $(By.xpath("//input[@name='Order[orderId]']")).setValue("1111+TEST");
        $(By.xpath("//input[@name='Order[phone]']")).setValue(testNumberThatPutOrderInTest);
        return this;
    }

}
