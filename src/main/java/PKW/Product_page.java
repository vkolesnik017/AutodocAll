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
}
