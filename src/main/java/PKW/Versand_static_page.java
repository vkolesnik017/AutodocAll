package PKW;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

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

    SelenideElement deliveryPageContent() {
        return $x("//main[@id='content']");
    }

    SelenideElement deliveryPageBox() {
        return $x("//*[@class='delivery-page__box']");
    }

    SelenideElement deliveryPageDeliveryIcons() {
        return $x("//div[@class='box-delivery-icons']");
    }

    SelenideElement deliveryPageBoxCountryText() {
        return $x("//div[@class='box-delivery-country-text']");
    }

    SelenideElement deliveryPageBoxCountryFlag() {
        return $x("//div[@class='box-delivery-country-text']//span//img");
    }

    SelenideElement deliveryPageBoxFeatureOne() {
        return $x("//div[@class='box-delivery-features__col']");
    }

    SelenideElement deliveryPageBoxFeatureTwo() {
        return $x("//div[@class='box-delivery-features__col multiple']");
    }

    SelenideElement deliveryPageBoxFeatureThree() {
        return $x("//div[@class='box-delivery-features__col box-tyre']");
    }

    SelenideElement deliveryPageBoxFeatureText() {
        return $x("//div[@class='box-delivery-features__note']");
    }

    SelenideElement deliveryPageBoxFeatureTextBottom() {
        return $x("//div[@class='box-delivery-bottom-text']");
    }

    SelenideElement countryBlockTitle() {
        return $x("//div[@class='country-block__title']");
    }

    SelenideElement countryBlockItemsButton() {
        return $x("//div[@class='country-block__items__button']");
    }

    SelenideElement countryBlockOtherCountry() {
        return $x("//div[@class='country-block__other-country']");
    }

    SelenideElement iconCar() {
        return $x("//span[@class='icon icon-car']");
    }

    SelenideElement iconLock() {
        return $x("//span[@class='icon icon-lock']");
    }

    SelenideElement iconTime() {
        return $x("//span[@class='icon icon-hours']");
    }

    ElementsCollection featureBlockTextForIcon() {
        return $$x("//div[@class='delivery-page__features__item']");
    }

    SelenideElement featureBlockTextForOneIcon() {
        return $x("//div[@class='delivery-page__features__item']");
    }

    SelenideElement deliveryTextBlock() {
        return $x("//div[@class='delivery-page__simple-text']");
    }

    SelenideElement lieferzeitTextBlock() {
        return $x("//div[@class='delivery-page__time__title']");
    }

    SelenideElement lieferzeitBlockLink() {
        return $x("//div[@class='delivery-page__time__title']//a");
    }

    SelenideElement aufLagerImage() {
        return $x("//div[@class='delivery-page__time__item delivery-page__time__lager']//span[@class='icon']");
    }

    SelenideElement nichtAufLagerImage() {
        return $x("//div[@class='delivery-page__time__item delivery-page__time__envelope']//span[@class='icon']");
    }

    SelenideElement nichtAufLagerTextBlock() {
        return $x("//div[@class='delivery-page__time__item delivery-page__time__envelope']");
    }

    SelenideElement aufLagerTextBlock() {
        return $x("//div[@class='delivery-page__time__item delivery-page__time__lager']");
    }

    SelenideElement faqTitleOne() {
        return $x("//div[@class='delivery-page__faq']//div[@class='delivery-page__faq__title']");
    }

    ElementsCollection faqQuestions() {
        return $$x("//p[@class='item-title']");
    }

    SelenideElement faqQuestionsActive() {
        return $x("//p[@class='item-title active']");
    }

    SelenideElement faqAnswersOne() {
        return $x("//div[@class='item-content open']");
    }

    ElementsCollection faqAnswers() {
        return $$x("//div[@class='item-content open']");
    }

    SelenideElement deliveryFaqText() {
        return $x("//div[@class='delivery-page__faq__text']");
    }

    SelenideElement carImage() {
        return $x("//span[@class='car-image']");
    }

    SelenideElement returnTextTitle() {
        return $x("//div[@class='delivery-page__easy-return__title']");
    }

    SelenideElement returnTextSubTitle() {
        return $x("//div[@class='delivery-page__easy-return__subtitle']");
    }

    SelenideElement returnCircle1() {
        return $x("//div[@class='delivery-page__easy-return__circle1']");
    }

    SelenideElement returnCircle2() {
        return $x("//div[@class='delivery-page__easy-return__circle2']");
    }

    SelenideElement returnCircle3() {
        return $x("//div[@class='delivery-page__easy-return__circle3']");
    }

    ElementsCollection questionsBlockItem() {
        return $$x("//div[@class='delivery-page__question-block__item']");
    }

    SelenideElement questionsBlockItemOne() {
        return $x("//div[@class='delivery-page__question-block__item']");
    }

    SelenideElement questionBlockList() {
        return $x("//div[@class='delivery-page__question-block__list']");
    }

    SelenideElement heavyGoodsTextBlock() {
        return $x("//div[@class='delivery-page__heavy-goods']");
    }

    SelenideElement blockSpergutOne() {
        return $x("//div[@class='delivery-page__heavy-goods__col'][1]//span");
    }

    SelenideElement blockSpergutTwo() {
        return $x("//div[@class='delivery-page__heavy-goods__col'][2]//span");
    }

    SelenideElement blockSpergutOneCheckingText() {
        return $x("//div[@class='delivery-page__heavy-goods__col'][1]//p");
    }

    SelenideElement blockSpergutTwoCheckingText() {
        return $x("//div[@class='delivery-page__heavy-goods__col'][2]//p");
    }

    SelenideElement firstTab() {
        return $x("//li[@data-tab='tab-1']");
    }

    SelenideElement secondTab() {
        return $x("//li[@data-tab='tab-2']");
    }

    SelenideElement blocksCountryPriceTextOne() {
        return $x("//div[@class='delivery-page__heavy-price__list__col']//li//span");
    }

    ElementsCollection secondTabContentImageFlag() {
        return $$x("//div[@class='delivery-page__heavy-price__list__col']//li//p//img");
    }

    ElementsCollection blocksCountryPriceText() {
        return $$x("//div[@class='delivery-page__heavy-price__list__col']//li//span");
    }

    ElementsCollection blocksTextWithPrice() {
        return $$x("//div[@class='delivery-page__heavy-price__col']//span");
    }

    SelenideElement blocksTextWithPriceOne() {
        return $x("//div[@class='delivery-page__heavy-price__col']");
    }

    SelenideElement listWithTheCountriesAndPriceTabTwo() {
        return $x("//div[@class='delivery-page__heavy-price__list__col']");
    }

    SelenideElement mehrButton() {
        return $x("//div[@class='delivery-page__heavy-price__title']//a");
    }

    SelenideElement expandTextAfterMehr() {
        return $x("//div[@class='delivery-page__heavy-price__list__col delivery-page__heavy-price__list__heavy']");
    }

    ElementsCollection deliveryPriceWithCountryMehr() {
        return $$x("//li[@class='inverse-text']");
    }

    SelenideElement deliveryPriceWithCountryMehrOne() {
        return $x("//li[@class='inverse-text']//span");
    }

    SelenideElement faqTitleSecond() {
        return $x("//*[@class='delivery-page__faq delivery-page__faq--freq']//div[@class='delivery-page__faq__title']");
    }

    SelenideElement backButton() {
        return $x("//*[@class='delivery-page__button']");
    }

    SelenideElement downloadPdfButton() {
        return $x("//*[@class='delivery-page__links']//a[2]");
    }

    SelenideElement downloadAcrobatButton() {
        return $x("//*[@class='delivery-page__links']//a[3]");
    }

    ElementsCollection flagsOfCountries() {
        return $$x("//div[@class='country-block__list__col']/ul/li//img");
    }

    ElementsCollection titleOfCountries() {
        return $$x("//div[@class='country-block__list__col']/ul/li/p/span[1]");
    }

    ElementsCollection pricesOfCountries() {
        return $$x("//div[@class='country-block__list__col']/ul/li/span");
    }

    ElementsCollection countryNameOnSite() {
        return $$x("//div[@class='country-block__other-country']//li//p[1]//span");
    }

    SelenideElement countryInBox() {
        return $x("//*[@class='box-delivery-country-text']//img[contains(@alt,'United Kingdom')]");
    }

    SelenideElement countryTable() {
        return $x("//div[@class='country-block__list__col']");
    }

    ElementsCollection countryItemForCount() {
        return $$x("//div[@class='country-block__list__col']//li");
    }

    SelenideElement openFormButton() {
        return $x("//div[@class='country-block__other-country__button']//a");
    }

    SelenideElement formForDeliveryToNewCountries() {
        return $x("//div[@class='delivery-page__form-popup']");
    }

    SelenideElement sendFormButton() {
        return $x("//div[@class='form-row__button']//a");
    }

    SelenideElement nameFieldVersandForm() {
        return $x("//*[@name='Vorname']");
    }

    SelenideElement emailFieldVersandForm() {
        return $x("//*[@id='delivery_page_callback_form']//*[@name='Email']");
    }

    SelenideElement phoneFieldVersandForm() {
        return $x("//*[@id='delivery_page_callback_form']//*[@name='Phone']");
    }

    SelenideElement textWithErrorMessageVersandForm() {
        return $x("//*[@class='delivery-tabs__error callback_error']");
    }

    SelenideElement checkboxVersandForm() {
        return $x("//*[@name='Delivery_subscribe_accept']");
    }

    SelenideElement successPopUpVersandForm() {
        return $x("//*[@class='popup_inner']");
    }

    SelenideElement closeButtonPopUpVersandForm() {
        return $x("//*[@class='popup_inner']//*[@class='buttons-inner']//a");
    }
}
