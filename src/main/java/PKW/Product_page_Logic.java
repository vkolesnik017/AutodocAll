package PKW;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.ex.ElementShould;
import com.codeborne.selenide.ex.UIAssertionError;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;


import java.util.ArrayList;
import java.util.List;

import static PKW.CommonMethods.mailRandom;

import static PKW.CommonMethods.*;
import static PKW.CommonMethods.checkingContainsUrl;

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

    @Step("Click on btn open popup for sending review. Product_page")
    public Product_page_Logic clickBtnOpenPopupForReview() {
        btnOpenRatingForm().click();
        return this;
    }

    @Step("Click btn Ask Question in Faq Form. Product_page")
    public Product_page_Logic clickBtnAskQuestionInFaqForm() {
        btnAskQuestionFaqForm().click();
        return this;
    }

    @Step("Click btn faq tab. Product_page")
    public Product_page_Logic clickBtnFaqTab() {
        btnFaqTab().scrollIntoView(false).click();
        return this;
    }

    @Step("Filling fields in rating form and checking behavior. Product_page")
    public String fillRequiredFieldsRatingFormAndCheckBehavior(String qc) {
        String mail = qc + mailRandom();
        nameFieldInRatingForm().setValue("AutoTestRating");
        emailFieldInRatingForm().setValue(mail);
        checkboxSubscribeAcceptInRatingForm().click();
        massageFieldInRatingForm().setValue("AutoTest for task");
        btnSendCommentFromPopupRatingForm().click();
        successPopup().shouldHave(text("Es wird nach einer Überprüfung veröffentlicht"));
        successPopupCloseButton().click();
        return mail;
    }

    @Step("Filling fields in FAQ form and checking behavior. Product_page")
    public String fillRequiredFieldsFaqFormAndCheckBehavior(String qc) {
        String mail = qc + mailRandom();
        nameFieldFaqForm().setValue("AutoTest");
        mailFormFaqForm().setValue(mail);
        massageFormFaqForm().setValue("AutoTest AutoTest");
        btnSendFaqForm().click();
        successPopup().shouldHave(text("Vielen Dank für Ihre Frage! Wir bearbeiten Ihre Frage so schnell es geht"));
        successPopupCloseButton().click();
        return mail;
    }

    @Step("presence of Phrase about compatibility product and vehicle .Product_page")
    public Product_page_Logic presenceOfPhraseAboutCompatibilityProductAndVehicle() {
        phraseAboutCompatibilityProductAndVehicle().shouldBe(visible);
        return this;
    }

    @Step("Sending the empty form, checking the validation messages in the FAQ form .Product_page")
    public Product_page_Logic checkingTheValidationMessageSendingEmptyForm() {
        btnFaqTab().click();
        btnAskQuestionFaqForm().click();
        formFaq().shouldBe(visible);
        btnSendFaqForm().click();
        validationNameMessage().shouldBe(visible);
        validationEmailMessage().shouldBe(visible);
        validationTextMessage().shouldBe(visible);
        return this;
    }


    @Step("presence of basket pop-Up .Product_page")
    public Product_page_Logic presenceOfBasketPopUp() {
        basketPopUp().shouldBe(visible);
        basketPopUp().shouldNotBe(visible);
        return this;
    }

    @Step("Adding product to basket. Product_page")
    public Product_page_Logic addProductToBasket() {
         buyButton().shouldBe(visible).click();
        presenceOfBasketPopUp();
        return this;
    }



    @Step("check presence of Refurbished characteristic in TOP product. Product_page")
    public Product_page_Logic checkOfRefValueInTopCharacteristicBlock() {
                for (int i=0; i<visibleTopProducts().size();i++){
                    visibleArtNumOfTopProducts().get(i).hover();
                }

    @Step("Checking for the presence of elements in the top block on the product page .Product_page")
    public Product_page_Logic presenceOfTheElementsInTheTopBlockOnTheProductPage() throws SQLException {

        CommonMethods commonMethods = new CommonMethods();

        carSelectorAll().shouldBe(visible);
        productName().shouldBe(visible);
        Assert.assertFalse(productName().text().isEmpty());
        productArticleNumber().shouldBe(visible);
        Assert.assertFalse(productArticleNumber().text().isEmpty());
        productTextUnderArticleNumber().shouldBe(visible);
        Assert.assertFalse(productTextUnderArticleNumber().text().isEmpty());
        productDiscount().shouldBe(visible).shouldHave(Condition.attribute("alt"));
        productTextDiscount().shouldBe(visible);
        Assert.assertFalse(productTextDiscount().text().isEmpty());
        productRatingStars().shouldBe(visible);
        productRatingText().shouldBe(visible);
        Assert.assertFalse(productRatingText().text().isEmpty());
        productTextUnderPrice().shouldBe(visible);
        Assert.assertFalse(productTextUnderPrice().text().isEmpty());
        productVersandLinkUnderPrice().click();
        commonMethods.checkingUrlAndCloseTab(new DataBase().getRouteByRouteName("DE", "static_versand"));

        productButtonBuyWithCount().shouldBe(visible);
        productCountButton().shouldBe(clickable);
        productActualLagerInfo().shouldBe(visible);
        productInfoAboutDoubleParts().shouldBe(visible);
        Assert.assertFalse(productInfoAboutDoubleParts().text().isEmpty());
        productInfoBlock().shouldBe(visible);
        Assert.assertFalse(productInfoBlock().text().isEmpty());
        return this;
    }

    @Step("Checking for the presence of elements in the characteristic block on the product page .Product_page")
    public Product_page_Logic presenceOfTheElementsInTheCharacteristicBlockOnTheProductPage() throws SQLException {

        CommonMethods commonMethods = new CommonMethods();

        characteristicBlock().shouldBe(visible);
        Assert.assertFalse(characteristicBlock().text().isEmpty());
        tabTwoInTheBlock().shouldBe(clickable);
        blockWithRelatedProductsCollapsed().shouldBe(visible);
        mehrButton().click();
        blockWithRelatedProductsExpanded().shouldBe(visible);
        schliebenButton().click();
        blockWithRelatedProductsExpanded().shouldNotBe(visible);
        blockWithVersandLinkTwo().shouldBe(visible);
        Assert.assertFalse(blockWithVersandLinkTwo().text().isEmpty());
        VersandLinkTwo().scrollIntoView(false).click();
        commonMethods.checkingUrlAndCloseTab(new DataBase().getRouteByRouteName("DE", "static_versand"));
        return this;
    }

    @Step("Сhecking for the presence of elements in the bottom block on the product page .Product_page")
    public Product_page_Logic presenceOfTheElementsInTheBottomBlockOnTheProductPage() throws SQLException {

        moreItemsBlock().shouldBe(visible);
        Assert.assertFalse(moreItemsBlock().text().isEmpty());
        pkwPaymentsItem().shouldBe(visible);
        moreItemsBlockFirstLink().click();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "product3"));
        back();
        return this;
    }

    @Step("Checking for the presence of elements in the bottom block on the product page .Product_page")
    public Product_page_Logic checkingTheTransitionToTheBrandProductPageAfterClickingTheBrandLogo() throws SQLException {

        productBrandIcon().shouldHave(Condition.attribute("alt"));
        productBrandIconLink().scrollIntoView(false).click();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "supplier"));
        back();
        return this;
    }

    @Step("Checking the redirection to the OEN listing after clicking the OEN tab .Product_page")
    public Product_page_Logic checkingTheTransitionToTheOENListingPageAfterClickingTheOenTab() {
        oenNummerTab().click();
        firstLinkOenNummer().scrollIntoView(false).click();
        waitWhileRouteBecomeExpected("oe_number");
        return this;
    }

    @Step("Checking the displaying to the OEN numbers with the selected car in the selector .Product_page")
    public Product_page_Logic checkingTheDisplayingTheOENNumbers() {
        firstProductOnTheListing().click();
        oenNummerTab().click();
        firstLinkOenNummerForCarSelector().shouldHave(text("AUDI"));

        return this;
    }
}
