package PKW;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.*;

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
        return $x("//span[@class='popup-review__close']");
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
        return $x("//div[@class='pkw-rateblock__footer']//button");
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


    SelenideElement basketPopUp() {
        return $x("//div[@class='cart-items-block ']");
    }

    ElementsCollection visibleTopProducts() {
        return $$x("//ul[@class='pkw-related']/li").filter(visible);
    }

    ElementsCollection visibleArtNumOfTopProducts() {
        return $$x("//span[@class='pkw-related__item-art']").filter(visible);
    }

    ElementsCollection titleOfCharacteristicOfTopProduct() {
        return $$x("//div[@class='pkw-related__additional-header']//ul/li/span[1]");
    }

    ElementsCollection valueOfCharacteristicOfTopProduct() {
        return $$x("//div[@class='pkw-related__additional-header']//ul/li/span[2]");
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
        return $x("//*[@class='pkw-product__sales']");
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
        return $x("//*[@class='pkw-product__price-after']//a");
    }

    SelenideElement productButtonBuyWithCount() {
        return $x("//*[@class='pkw-product__control button product']");
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

    SelenideElement oenNummerTab() {
        return $x("//*[@class='pkw-tabs__label ga-click'][1]");
    }

    SelenideElement firstLinkOenNummer() {
        return $x("//*[@class='pkw-oem__ul'][1]//li[1]//a");
    }

    SelenideElement firstProductOnTheListing() {
        return $x("//*[@class='prod_link'][1]//a");
    }

    SelenideElement firstLinkOenNummerForCarSelector() {
        return $x("//*[@class='pkw-oem__ul']//a[contains(text(),'AUDI')]");
    }

    SelenideElement btnAddProductToBasket() {
        return $x("//button[contains(@class,'buy-btn basket_btn ')]");
    }

    SelenideElement grayBtn() {
        return $x("//button[contains(@class,'basket_btn not_active')]");
    }

    SelenideElement dropDownPopUpOfBasket() {
        return $x("//div[@class='cart-items-block ']");
    }

    ElementsCollection allCharacteristics() {
        return $$x("//div[@class='pkw-table']/ul/li");
    }

    SelenideElement validationNameMessageReview() {
        return $x("//form[@class='pkw-messagesblock__first-message five_stars_rating_form invalid']//div[@class='rating_name_error error']");
    }

    SelenideElement validationEmailMessageReview() {
        return $x("//form[@class='pkw-messagesblock__first-message five_stars_rating_form invalid']//div[@class='rating_email_error error']");
    }

    SelenideElement validationTextMessageReview() {
        return $x("//form[@class='pkw-messagesblock__first-message five_stars_rating_form invalid']//div[@class='rating_message_error error']");
    }

    SelenideElement validationCheckboxMessageReview() {
        return $x("//form[@class='pkw-messagesblock__first-message five_stars_rating_form invalid']//div[@class='subscribe_accept_error error']");
    }

    SelenideElement productRatingStarsNotFilling() {
        return $x("//*[@class='pkw-product__rating']");
    }

    SelenideElement productRatingBlockAfterClickingOnStars() {
        return $x("//*[@class='pkw-popup__inset']");
    }

    SelenideElement overlayBehindReviewForm() {
        return $x("//*[@class='black_overlay wl_overlay']");
    }

    SelenideElement reviewFormArtikelBewertenButton() {
        return $x("//*[@class='pkw-popup pkw-modal__rating-window test']");
    }

    SelenideElement blockKBANumber() {
        return $x("//*[@class='medium-select top-select']");
    }

    SelenideElement blockRegNumber() {
        return $x("//*[@class='pkw-carselector__content-element'][3]//*[@class='medium-select']");
    }

    SelenideElement blockRegNumberForCH() {
        return $x("//*[@class='pkw-carselector__content-element pkw-carselector__content-element_hoder']//*[@class='medium-select']");
    }

    SelenideElement blockWithReviewsMessage() {
        return $x("//*[@class='product-feedback_wrap_comments']");
    }

    SelenideElement closePopUpButton() {
        return $x("//*[@class='popup-car-selection__close']");
    }

    SelenideElement pricePerMeter() {return $x("//*[contains(text(),'Preis pro Meter')]");}
}
