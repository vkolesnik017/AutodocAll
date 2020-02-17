package ATD;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Contact_static_page {

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

    public SelenideElement noOrderDatenschutzerklarungLink() {
        return $(By.cssSelector("#NoOrderprivacy_policy>a"));
    }

    public SelenideElement orderDatenschutzerklarungLink() {
        return $(By.cssSelector("#Orderprivacy_policy>a"));
    }

    SelenideElement sendenButton() {
        return $(By.xpath("//div[@class='dropdown-wrapper open-tab']/div[last()]/button"));
    }

    SelenideElement successPopup() {
        return $(By.xpath("//div[@class='popup ']"));
    }

    SelenideElement addingAnotherProductBtn() {
        return $(By.xpath("//div[@class='contact-tabs__add-field']"));
    }

    SelenideElement successPopupCloseButton() {
        return $(By.xpath("//div[@class='popup_content']//a"));
    }

    //Selenide Elements for Static Page test
    SelenideElement logo() {
        return $(By.cssSelector(".logo>a>img"));
    }

    SelenideElement nameFieldNoOrder() {
        return $(By.id("form_NoOrder[name]"));
    }

    SelenideElement mailFieldNoOrder() {
        return $(By.id("form_NoOrder[email]"));
    }

    SelenideElement articleFieldNoOrder() {
        return $(By.id("form_NoOrder[article][0][articleNo]"));
    }

    SelenideElement firstProductInArticleAutocomplete() {
        return $(By.xpath("//div[@id='contact-page-autocomplete-suggestions-list']//div[@data-index='0']"));
    }

    SelenideElement orderFormOrderField() {
        return $(By.id("form_Order[orderId]"));
    }

    SelenideElement orderFormTelField() {
        return $(By.id("form_Order[phone]"));
    }

    SelenideElement orderFormMailField() {
        return $(By.id("form_Order[email]"));
    }


    public SelenideElement contactsBlock() {
        return $(By.cssSelector(".contact-page__info-descroption"));
    }

    public SelenideElement addressBlock() {
        return $(By.cssSelector(".contact-page__info-adress"));
    }

    public SelenideElement orderEmailInput() {
        return $(By.xpath("//input[@name='Order[email]']"));
    }

    public SelenideElement haveOrderButton() {
        return $(By.xpath("//*[@class='contact-page__form']/div[2]/span"));
    }

    public SelenideElement haveOrderBlock() {
        return $(By.xpath("//*[@class='contact-page__form-tab'][@data-tab='order']/div"));
    }

    public SelenideElement submitOrderButton() {
        return $(By.xpath("//*[@class='contact-page__form']/div[2]/div/div[6]/button"));
    }

    public SelenideElement emailError() {
        return $(By.xpath("//*[@class='contact-page__form-error'][@data-name='error_Order[email]']"));
    }

    public SelenideElement orderIdError() {
        return $(By.xpath("//*[@class='contact-page__form-error'][@data-name='error_Order[orderId]']"));
    }

    public SelenideElement phoneNumberError() {
        return $(By.xpath("//*[@class='contact-page__form-error'][@data-name='error_Order[phone]']"));
    }

    public SelenideElement haveNoOrderButton() {
        return $(By.xpath("//*[@class='contact-page__form']/div[3]/span"));
    }

    public SelenideElement haveNoOrderBlock() {
        return $(By.xpath("//*[@class='contact-page__form-tab'][@data-tab='no_order']/div"));
    }

    public SelenideElement submitNoOrderButton() {
        return $(By.xpath("//*[@class='contact-page__form']/div[3]/div/div[8]/button"));
    }

    public SelenideElement noOrderEmailError() {
        return $(By.xpath("//*[@class='contact-page__form-error'][@data-name='error_NoOrder[email]']"));
    }

    public SelenideElement noOrderDetailError() {
        return $(By.xpath("//*[@class='contact-page__form-error'][@data-name='error_NoOrder[article][0][articleNo]']"));
    }

    public SelenideElement noOrderEmailInput() {
        return $(By.xpath("//input[@name='NoOrder[email]']"));
    }
}
