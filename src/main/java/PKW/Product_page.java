package PKW;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Product_page {

    SelenideElement titleProduct() {
        return $x("//div[@class='pkw-product__row ']//h2");
    }

    public SelenideElement cartIcon() {
        return $(byCssSelector(".header__cart-count"));
    }

    SelenideElement numberBasket() {
        return $(byCssSelector(".code"));
    }

    SelenideElement buyButton() {
        return $(byCssSelector(".pkw-product__buy-btn"));
    }

    // locators in popup of cart
    public SelenideElement firstProductPriceInPopupOfCart() {
        return $(byCssSelector(".row-price"));
    }

    SelenideElement closeBtnOfPopupOtherCategory() {
        return $x("//span[@class='popup-related__close']");
    }

    SelenideElement closeBtnOFPopupReview() {
        return $x("//span[@class='popup-related__close']");
    }

    // locators for reviews block
    SelenideElement reviewsBlock() {
        return $x("//div[@class='pkw-more']");
    }

    SelenideElement nameFieldInReviewForm() {
        return $x("//input[@id='rating_name_right']");
    }

    SelenideElement emailFieldInReviewForm() {
        return $x("//input[@id='rating_email_right']");
    }

    SelenideElement checkboxSubscribeAcceptInReviewForm() {
        return $x("//div[@class='pkw-more__span_40']//input[@id='subscribe_accept']");
    }

    SelenideElement massageFieldInReviewForm() {
        return $x("//div[@class='pkw-more__span_40']//textarea[@id='rating_message_right']");
    }

    SelenideElement btnSendInReviewForm() {
        return $x("//div[@class='pkw-more__span_40']//button[@class='pkw-product__green-btn rating_send']");
    }

    public SelenideElement reviewFormDatenschutzerklarungLink() {
        return $x("//label[@id='privacy_policy_reviews_right']/a");
    }

    SelenideElement successPopup() {
        return $(By.xpath("//div[@class='popup ']//div[@class='txt']"));
    }

    SelenideElement successPopupCloseButton() {
        return $(By.xpath("//div[@class='popup_content']//a"));
    }

    SelenideElement btnOpenRatingForm() {
        return $x("//div[@class='pkw-rateblock__footer']/button");
    }

    public SelenideElement ratingFormDatenschutzerklarungLink() {
        return $x("//label[@id='privacy_policy1']/a");
    }

    SelenideElement nameFieldInRatingForm() {
        return $x("//div[@class='pkw-popup__inset']//input[@id='form_rating_name']");
    }

    SelenideElement emailFieldInRatingForm() {
        return $x("//div[@class='pkw-popup__inset']//input[@id='form_rating_email']");
    }

    SelenideElement checkboxSubscribeAcceptInRatingForm() {
        return $x("//div[@class='pkw-popup__inset']//input[@id='subscribe_accept']");
    }

    SelenideElement massageFieldInRatingForm() {
        return $x("//div[@class='pkw-popup__inset']//textarea[@id='form_rating_message']");
    }

    SelenideElement btnSendCommentFromPopupRatingForm() {
        return $x("//div[@class='pkw-popup__inset']//button[@class='pkw-product__green-btn rating_send']");
    }

    SelenideElement nameProductCharacteristic(String nameCharacteristic) {
        return $x("//li[@class='pkw-table__ul-item']//span[contains(text(),'" + nameCharacteristic + "')]");
    }
    SelenideElement btnAskQuestionFaqForm() {
        return $x("//div[@class='pkw-faq']//button");
    }

    SelenideElement btnFaqTab() {
        return $x("//div[@class='pkw-tabs__header']//label[@class='pkw-tabs__label ga-click faq']");
    }

    SelenideElement nameFieldFaqForm() {
        return $x("//form[@id='comment_reply_form2']//input[@id='form_rating_name']");
    }

    SelenideElement mailFormFaqForm() {
        return $x("//form[@id='comment_reply_form2']//input[@id='form_rating_email']");
    }

    SelenideElement massageFormFaqForm() {
        return $x("//form[@id='comment_reply_form2']//textarea[@id='form_rating_message']");
    }

    SelenideElement btnSendFaqForm() {
        return $x("//form[@id='comment_reply_form2']//button[@class='pkw-product__green-btn_faq']");
    }

    public SelenideElement faqFormDatenschutzerklarungLink() {
        return $x("//form[@id='comment_reply_form2']//a");
    }

    SelenideElement phraseAboutCompatibilityProductAndVehicle() {
        return $x("//p[contains(text(),'Dieses Produkt passt zu Ihrem')]");
    }

    SelenideElement formFaq() {
        return $x("//form[@id='comment_reply_form2']");
    }

    SelenideElement validationNameMessage() {
        return $x("//div[@class='rating_name_error error'][contains(text(),'Bitte alle Felder ausfüllen')]");
    }

    SelenideElement validationEmailMessage() {
        return $x("//form[@id='comment_reply_form2']//*[@class='rating_email_error error'][contains(text(),'Bitte eine gültige E-Mail verwenden')]");
    }

    SelenideElement validationTextMessage() {
        return $x("//div[@class='rating_email_error error'][contains(text(),'Bitte alle Felder ausfüllen')]");
    }

    SelenideElement carSelectorAll() {
        return $x("//div[@class='pkw-carselector']");
    }

    SelenideElement productName() {
        return $x("//*[@class='pkw-product__name']");
    }

    SelenideElement productBrandIcon() {
        return $x("//*[@class='pkw-product__rowspan']//a//img");
    }

    SelenideElement productBrandIconLink() {
        return $x("//*[@class='pkw-product__rowspan']//a");
    }

    SelenideElement productArticleNumber() {
        return $x("//*[@class='pkw-product__artikel']");
    }

    SelenideElement productTextUnderArticleNumber() {
        return $x("//*[@class='pkw-product__row pkw-product__row--noflex']//h3");
    }

    SelenideElement productDiscount() {
        return $x("//*[@class='pkw-product__sales']//img");
    }

    SelenideElement productTextDiscount() {
        return $x("//*[@class='pkw-product__sales-additional']");
    }

    SelenideElement productRatingStars() {
        return $x("//*[@class='pkw-product__rowspan js-product-click-stars']//*[@class='pkw-product__rating']");
    }

    SelenideElement productRatingText() {
        return $x("//*[@class='pkw-product__rating-text']");
    }

    SelenideElement productTextUnderPrice() {
        return $x("//*[@class='pkw-product__price-after']");
    }

    SelenideElement productVersandLinkUnderPrice() {
        return $x("//*[@class='pkw-product__price-after']//*[@title='Versandkosten']");
    }

    SelenideElement productButtonBuyWithCount() {
        return $x("//*[@class='pkw-product__control button product']");
    }

    SelenideElement productButtonBuy() {
        return $x("//*[@class='pkw-product__buy-btn basket_btn ']");
    }

    SelenideElement productCountButton() {
        return $x("//*[@class='pkw-product__input-wrapper qty']");
    }

    SelenideElement productActualLagerInfo() {
        return $x("//*[@class='pkw-product__alternative-button']");
    }

    SelenideElement productInfoAboutDoubleParts() {
        return $x("//*[@class='pkw-product__double']");
    }

    SelenideElement productInfoBlock() {
        return $x("//*[@class='pkw-plus']");
    }

    SelenideElement characteristicBlock() {
        return $x("//*[@class='pkw-more__span_40 pkw-more__max-height']");
    }

    SelenideElement tabTwoInTheBlock() {
        return $x("//*[@for='tab2']");
    }

    SelenideElement blockWithRelatedProductsCollapsed() {
        return $x("//*[@for='tab2']");
    }

    SelenideElement mehrButton() {
        return $x("//*[@class='related-products-box__more readmore-js-toggle']//a");
    }

    SelenideElement blockWithRelatedProductsExpanded() {
        return $x("//*[@class='related-products-box readmore-js-section readmore-js-expanded']");
    }

    SelenideElement schliebenButton() {
        return $x("//*[@class='related-products-box__more related-products-box__more--hide readmore-js-toggle']//a");
    }

    SelenideElement blockWithVersandLinkTwo() {
        return $x("//*[@class='analog_mwst_block']");
    }

    SelenideElement VersandLinkTwo() {
        return $x("//*[@class='analog_mwst_block']//a");
    }

    SelenideElement moreItemsBlock() {
        return $x("//*[@class='more__items']");
    }

    SelenideElement moreItemsBlockFirstLink() {
        return $x("//*[@class='more__items']//a[1]");
    }

    SelenideElement pkwPaymentsItem() {
        return $x("//*[@class='pkw-payment']");
    }
}
