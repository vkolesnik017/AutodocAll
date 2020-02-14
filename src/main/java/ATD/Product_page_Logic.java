package ATD;

import com.codeborne.selenide.ex.ElementShould;
import com.codeborne.selenide.ex.UIAssertionError;
import io.qameta.allure.Step;

import static ATD.CommonMethods.mailRandom;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.Selenide.sleep;

public class Product_page_Logic extends Product_page {


    @Step(":from product page")
    public Cart_page_Logic cartClick() {
      new Main_page().cartClick();
      return page(Cart_page_Logic.class);
    }

    @Step
    public Product_page_Logic checkNumberBasketAndRefreshPageIfNot() {  // TODO Бывает при открытии страницы не подгружается номер корзины и товар не добавляется в корзину, причина не известна, что бы стабилизировать тесты добавлен этот метод
      try {
        numberBasket().shouldBe(visible);
      } catch (ElementShould e) {
        refresh();
        numberBasket().shouldBe(visible);
      }
      return this;
    }

    @Step
    public Product_page_Logic addProductToCart() {
      checkNumberBasketAndRefreshPageIfNot();
      sleep(3000); // TODO для стабилизации. Без слипа иногда добавленный товар исчезает из корзины после перехода в неё, решается в SITES-2830
      buyButton().click();
      try {
        checksPresentProductInCartPopup();
      } catch (UIAssertionError e) {
        closePopupOtherCategoryIfYes();
        buyButton().click();
        checksPresentProductInCartPopup();
      }
      return this;
    }

    //Gray button popup for subscription for product which is not stock
    public Product_page_Logic clickGrayButtonAndCheckAvailableForm() {
        grayButton().click();
        popupAvailableForm().should(appear);
        return this;
    }

    public Product_page_Logic checkingDatenschutzerklarungLinkBehaviorInAvailableForm() {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(datenschutzerklarungLinkInAvailableForm(), "underline solid rgb(0, 0, 0)");
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
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(datenschutzerklarungLinkInReviewsForm(), "underline solid rgb(0, 0, 0)");
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
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(datenschutzerklarungLink(), "underline solid rgb(0, 0, 0)");
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
