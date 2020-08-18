package PKW;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class Kontakt_static_page {

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
