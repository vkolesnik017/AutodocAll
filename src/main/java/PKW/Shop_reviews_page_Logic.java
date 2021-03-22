package PKW;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static PKW.CommonMethods.mailRandom;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;


public class Shop_reviews_page_Logic extends Shop_reviews_page {


    @Step("Click on btn reply in comment block. Shop_reviews_page")
    public Shop_reviews_page_Logic clickBtnReplyInCommentBlock() {
        btnReply().scrollIntoView(false).click();
        return this;
    }

    @Step("Click on btn rate site in rating block. Shop_reviews_page")
    public Shop_reviews_page_Logic clickBtnRateSiteInRatingBlock() {
        btnRateSite().click();
        return this;
    }

    @Step("Filling fields and checking behavior in popup for Reply . Shop_reviews_page")
    public String fillRequiredFieldsAndCheckBehaviorInPopupForReply(String qc) {
        String mail = qc + mailRandom();
        popupAnswerNameField().setValue("AutoTest");
        popupAnswerEmailField().setValue(mail);
        popupAnswerSubscriptionCheckbox().click();
        popupAnswerCommentField().setValue("AutoTest AutoTest");
        popupAnswerBtnSend().click();
        successPopup().shouldHave(text("Es wird nach einer Überprüfung veröffentlicht."));
        successPopupCloseButton().click();
        return mail;
    }

    @Step(":on Shop_reviews_page_Logic")
    public Shop_reviews_page_Logic checkingDatenschutzerklarungLinkBehavior(SelenideElement orderDatenschutzerklarungLink) {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(orderDatenschutzerklarungLink, "underline solid rgb(0, 0, 0)");
        return this;
    }

    @Step(":on Shop_reviews_page_Logic")
    public String fillRequiredFieldsPopupRatingFormAndCheckBehavior(String qc) {
        String mail =  new Product_page_Logic().fillRequiredFieldsRatingFormAndCheckBehavior(qc);
        return mail;
    }

    @Step("Filling fields in review form and checking behavior. Shop_reviews_page")
    public String fillRequiredFieldsReviewFormAndCheckBehavior(String qc) {
        String mail = qc + mailRandom();
        nameFieldInReviewForm().setValue("AutoTest");
        emailFieldInReviewForm().setValue(mail);
        checkboxSubscribeAcceptInReviewForm().click();
        massageFieldInReviewForm().setValue("AutoTest AutoTest");
        btnSendInReviewForm().click();
        successPopup().shouldHave(text("Wir haben eine Bestätigungs-E-Mail an die von Ihnen angegebene Adresse gesendet"));
        successPopupCloseButton().click();
        return mail;
    }

    @Step("Scroll to review Block. Shop_reviews_page")
    public Shop_reviews_page_Logic scrollToReviewBlock() {
        reviewBlock().scrollTo();
        reviewBlock().shouldBe(appear);
        return this;
    }







}
