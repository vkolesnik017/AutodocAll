package PKW;


import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.ex.ElementShould;
import com.codeborne.selenide.ex.UIAssertionError;
import io.qameta.allure.Step;

import static PKW.CommonMethods.mailRandom;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Product_page_Logic extends Product_page {

    @Step("Get name title. Product_page")
    public String getNameTitle() {
        return titleProduct().getText();
    }


    @Step("Closing other category popup after adding product in basket. Product_page")
    public Product_page_Logic closePopupOtherCategoryIfYes() {
        try {
            closeBtnOfPopupOtherCategory().waitUntil(visible, 2500);
            closeBtnOfPopupOtherCategory().click();
            closeBtnOfPopupOtherCategory().shouldBe(not(visible));
        } catch (ElementNotFound ignored) {
        }
        return this;
    }

    public Product_page_Logic closeBtnOFPopupReviewIfYes() {
        try {
            closeBtnOFPopupReview().waitUntil(visible, 2500);
            closeBtnOFPopupReview().click();
            closeBtnOFPopupReview().shouldBe(not(visible));
        } catch (ElementNotFound ignored) {
        }
        return this;
    }

    @Step("Checking present product in cart popup. Product_page")
    public Product_page_Logic checksPresentProductInCartPopup() {
        cartIcon().hover();
        firstProductPriceInPopupOfCart().shouldBe(visible);
        return this;
    }

    @Step("Checking number basket and refresh page if not. Product_page")
    public Product_page_Logic checkNumberBasketAndRefreshPageIfNot() {  // TODO Бывает при открытии страницы не подгружается номер корзины и товар не добавляется в корзину, причина не известна, что бы стабилизировать тесты добавлен этот метод
        try {
            numberBasket().shouldBe(visible);
        } catch (ElementShould e) {
            refresh();
            numberBasket().shouldBe(visible);
        }
        return this;
    }

    @Step("Adding product to basket. Product_page")
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

    @Step(":from Product_page")
    public Cart_page_Logic cartClick() {
        new Main_page_Logic().cartClick();
        return page(PKW.Cart_page_Logic.class);
    }



    @Step(":on Product_page")
    public Product_page_Logic checkingDatenschutzerklarungLinkBehavior(SelenideElement orderDatenschutzerklarungLink) {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(orderDatenschutzerklarungLink, "underline solid rgb(0, 0, 0)");
        return this;
    }

    @Step("Filling fields in review form and checking behavior. Product_page")
    public String fillRequiredFieldsReviewFormAndCheckBehavior(String qc) {
        String mail = qc + mailRandom();
        nameFieldInReviewForm().setValue("AutoTest");
        emailFieldInReviewForm().setValue(mail);
        checkboxSubscribeAcceptInReviewForm().click();
        massageFieldInReviewForm().setValue("AutoTest for task QC_1900");
        btnSendInReviewForm().click();
        successPopup().shouldHave(text("Es wird nach einer Überprüfung veröffentlicht"));
        successPopupCloseButton().click();
        return mail;
    }

    @Step("Scroll to reviews block. Product_page")
    public Product_page_Logic scrollToReviewsBlock() {
        reviewsBlock().scrollTo();
        reviewsBlock().shouldBe(appear);
        return this;
    }

}
