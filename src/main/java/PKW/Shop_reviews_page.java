package PKW;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Shop_reviews_page {

    SelenideElement reviewBlock() {
        return $x("//div[@class='col_right reply_block_fields']");
    }

    SelenideElement btnReply() {
        return $x("//div[@class='reply']//a[@class='reply-btn btn']");
    }

    SelenideElement popupAnswerNameField() {
        return $x("//input[@id='form_name']");
    }

    SelenideElement popupAnswerEmailField() {
        return $x("//input[@id='form_email']");
    }

    SelenideElement popupAnswerSubscriptionCheckbox() {
        return $x("//input[@id='subscribe_accept_comment']");
    }

    SelenideElement popupAnswerCommentField() {
        return $x("//textarea[@id='form_text']");
    }

    SelenideElement popupAnswerBtnSend() {
        return $x("//div[@class='button_send']/a");
    }

    public SelenideElement popupAnswerLinkDatenschutzerklärung() {
        return $x("//label[@id='privacy_policy_comment']/a");
    }

    SelenideElement successPopup() {
        return $(By.xpath("//div[@class='popup ']//div[@class='txt']"));
    }

    SelenideElement successPopupCloseButton() {
        return $(By.xpath("//div[@class='popup_content']//a"));
    }

    SelenideElement btnRateSite() {
        return $x("//div[@class='col_left']//a[@class='btn shop_review_popup']");
    }

    public SelenideElement ratingFormDatenschutzerklarungLink() {
        return $x("//label[@id='privacy_policy1']/a");
    }


    SelenideElement nameFieldInReviewForm() {
        return $x("//input[@id='rating_name_right']");
    }

    SelenideElement emailFieldInReviewForm() {
        return $x("//input[@id='rating_email_right']");
    }

    SelenideElement checkboxSubscribeAcceptInReviewForm() {
        return $x("//input[@id='subscribe_accept_shop_right']");
    }

    SelenideElement massageFieldInReviewForm() {
        return $x("//textarea[@id='rating_message_right']");
    }

    SelenideElement btnSendInReviewForm() {
        return $x("//a[@class='btn btn_leave_review_from_page']");
    }

    public SelenideElement reviewFormDatenschutzerklarungLink() {
        return $x("//label[@id='privacy_policy_shop_right']/a");
    }

}
