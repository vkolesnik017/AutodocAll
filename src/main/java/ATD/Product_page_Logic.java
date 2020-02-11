package ATD;

import static ATD.CommonMethods.mailRandom;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;

public class Product_page_Logic extends Product_page {


    //Gray button popup for subscription for product which is not stock
    public Product_page_Logic clickGrayButtonAndCheckAvailableForm() {
        grayButton().click();
        popupAvailableForm().should(appear);
        return this;
    }

    public Product_page_Logic checkingDatenschutzerklarungLinkBehaviorInAvailableForm() {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(datenschutzerklarungLinkInAvailableForm());
        return this;
    }

    public String fillingFieldsAndCheckBehaviorAvailableForm(String qc) {
        String mail = qc + mailRandom();
        emailFieldInPopUpOfGrayBtn().setValue(mail);
        checkboxInPopUpOfGrayBtn().click();
        sendButtonInPopUpOfGrayBtn().click();
        sendMailFormSuccesPopup().shouldBe(appear);
        sendMailFormSuccesPopupCloseBtn().click();
        return mail;
    }

    //Reviews Form
    public Product_page_Logic checkingReviewsForm() {
        reviewsForm().scrollTo();
        reviewsForm().should(appear);
        return this;
    }

    public Product_page_Logic checkingDatenschutzerklarungLinkBehaviorInReviewsForm() {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(datenschutzerklarungLinkInReviewsForm());
        return this;
    }

    public String fillingFieldsAndCheckBehaviorReviewsForm(String qc) {
        String mail = qc + mailRandom();
        reviewsNameInput().setValue("test");
        reviewsEmailInput().setValue(mail);
        reviewsMessageInput().setValue("test");
        checkboxInReviewsForm().click();
        reviewsSubmitButton().click();
        succesPopup().shouldBe(appear);
        succesPopupCloseBtn().click();
        return mail;
    }

    //FAQ form
    public Product_page_Logic scrollToFaqForm() {
        faqForm().scrollTo();
        faqForm().shouldBe(visible);
        return this;
    }

    public Product_page_Logic checkingDatenschutzerklarungLinkBehavior() {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(datenschutzerklarungLink());
        return this;
    }

    public String fillingFieldsAndCheckBehaviorFaqForm(String qc) {
        faqFormNameField().setValue("autotest");
        String mail = "qc535_" + mailRandom();
        faqFormMailField().setValue(mail);
        faqFormCommentField().setValue("autotest");
        faqFormSendenBtn().click();
        faqFormSuccesPopup().shouldBe(appear);
        faqFormSuccesPopupCloseBtn().click();
        return mail;
    }

}
