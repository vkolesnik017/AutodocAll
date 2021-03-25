package ATD;

import AWS.ProductCard_aws;
import Common.DataBase;
import Common.Excel;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.ex.ElementShould;
import com.codeborne.selenide.ex.UIAssertionError;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import static ATD.CommonMethods.*;
import static Common.CommonMethods.*;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.sizeLessThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Product_page_Logic extends Product_page {

    @Step("Checks that links contain pdf file")
    public void checkPdfLinksForDownload() {
        for (int i = 0; i < pdfLinksForDownload().size(); i++) {
            Assert.assertTrue(pdfLinksForDownload().get(i).attr("url").contains(".pdf"), "Document for tutorial downloading is not in .pdf format");
        }
    }

    @Step("Open product page by route {route} and his ID {idProduct} number. Product_page")
    public Product_page_Logic openProductPageById(String route, String idProduct) {
        openPage(route + "/a/" + idProduct);
        return this;
    }

    @Step("Checking quantity {quantityInCart} on basket icon. Product_page")
    public Product_page_Logic checkQuantityOnBasketIconEquals(int quantityInCart) {
        quantityOnBasketIcon().shouldHave(exactText(String.valueOf(quantityInCart)));
        return this;
    }

    @Step("Checking present product in cart popup. Product_page")
    public Product_page_Logic checksPresentProductInCartPopup() {
        cartIcon().scrollIntoView("{block: \"center\"}").waitUntil(visible, 5000).hover();
        firstProductPriceInPopupOfCart().shouldBe(visible);
        return this;
    }

    @Step("Checking presence pfand block. Product_page")
    public Product_page_Logic checkingPresencePfandBlock() {
        pfandBlock().shouldBe(visible);
        return this;
    }

    @Step("Click btn go to cart from cart Drop Menu. Product_page")
    public CartAccount_page_Logic clickBtnGoToCartFromCartDropMenu() {
        btnGoToCartFromBasketDropMenu().shouldBe(visible).click();
        return page(CartAccount_page_Logic.class);
    }

    // Horizontal car selector

    @Step("Choose brand {brandName} in horizontal car selector. Product_page")
    public Product_page_Logic chooseBrandInHorizontalCarSelector(String brandName) {
        brandSelector().selectOption(brandName);
        Wait().until(webDriver -> brandSelector().getSelectedText().equals(brandName));
        return this;
    }

    @Step("select model be value {modelNumberValue} in horizontal car selector. Product_page")
    public Product_page_Logic selectModelInHorizontalCarSelector(String modelNumberValue) {
        modelSelector().selectOptionByValue(modelNumberValue);
        Wait().until(webDriver -> modelSelector().getSelectedValue().equals(modelNumberValue));
        sleep(2000);
        return this;
    }


    @Step("Choose model be value {modelNumberValue} in horizontal car selector. Product_page")
    public Product_page_Logic chooseModelInHorizontalCarSelector(String modelNumberValue) {
        modelSelector().selectOptionByValue(modelNumberValue);
        sleep(1500);
        return this;
    }

    @Step("Choose type by value {typeNumberValue} in horizontal car selector. Product_page")
    private Product_page_Logic chooseTypeInHorizontalCarSelector(String typeNumberValue) {
        typeSelector().selectOptionByValue(typeNumberValue);
        return this;
    }

    @Step("Choose brand {brandName}, model value {modelNumberValue}, type value {typeNumberValue} in horizontal selector. Product_page")
    public Product_page_Logic chooseBrandModelTypeInHorizontalSelector(String brandName, String modelNumberValue, String typeNumberValue) {
        chooseBrandInHorizontalCarSelector(brandName)
                .chooseModelInHorizontalCarSelector(modelNumberValue)
                .chooseTypeInHorizontalCarSelector(typeNumberValue);
        return this;
    }

    @Step("Click search button in horizontal car selector. Product_page")
    public Product_page_Logic clickSearchBtnInHorizontalSelector() {
        selectorSearchBtn().click();
        return this;
    }

    @Step("Click reset button in horizontal car selector. Product_page")
    public Product_page_Logic resetHorizontalCarSelector() {
        resetBtnSelector().click();
        resetBtnSelector().shouldBe(not(visible));
        return this;
    }

    // Selector kba
    @Step("Fill in KBA fields.First files {numberForFirstField} and second field {numberForSecondField}. Product_page")
    public Product_page_Logic fillNumberKba(String numberForFirstField, String numberForSecondField) {
        firstFieldKBA().setValue(numberForFirstField);
        secondFieldKBA().setValue(numberForSecondField);
        return this;
    }

    @Step("Click search KBA button. Product_page")
    public Maker_car_list_page_Logic clickKbaBtn() {
        selectorKbaBtn().click();
        return page(Maker_car_list_page_Logic.class);
    }

    @Step("Click search KBA button and close popup KBA error if it is visible. Product_page")
    public Maker_car_list_page_Logic clickKbaBtnAndClosePopUpKbaError() {
        selectorKbaBtn().click();
        sleep(3000);
        while (popUpKbaError().isDisplayed()) {
            closePopUpKbaError().click();
            selectorKbaBtn().waitUntil(visible, 5000);
            clickKbaBtn();
            sleep(3000);
        }
        return page(Maker_car_list_page_Logic.class);
    }
    //----------------------------------------------------------------------------------------------------------

    @Step("Checking heavy cargo link transition. Product_page")
    public Product_page_Logic checkingHeavyCargoLinkTransition() {
        heavyCargoLink().click();
        switchTo().window(1);
        checkingContainsUrl("services/versand#surcharge");
        heavyCargoBlock().shouldBe(visible);
        return this;
    }

    @Step(":on Product_page")
    public Product_page_Logic counterIncreaseForPaired(String startValue) {
        new CommonMethods().checkingCounterIncreaseForPaired(startValue, counterValuePairedGood(), counterPlus());
        return this;
    }

    @Step(":on Product_page")
    public Product_page_Logic counterDecreaseForPaired(String startValue) {
        new CommonMethods().checkingCounterDecreaseForPaired(startValue, counterValuePairedGood(), counterMinus());
        return this;
    }

    @Step("Checking expected number {expectedNumber} of product in cart. Product_page")
    public Product_page_Logic checkingNumberOfProductInCart(int expectedNumber) {
        new Main_page_Logic().checkingNumberOfProductInCart(expectedNumber);
        return this;
    }


    @Step(":from Product_page")
    public Cart_page_Logic cartClick() {
        new Main_page_Logic().cartClick();
        return page(Cart_page_Logic.class);
    }

    @Step(":from Product_page")
    public CartAllData_page_Logic cartClickAndReturnAllDataPage() {
        new Main_page_Logic().cartClick();
        return page(CartAllData_page_Logic.class);
    }

    @Step("Checking number basket and refresh page if not. Product_page")
    public Product_page_Logic checkNumberBasketAndRefreshPageIfNot() {  // TODO Бывает при открытии страницы не подгружается номер корзины и товар не добавляется в корзину, причина не известна, что бы стабилизировать тесты добавлен этот метод
        try {
            numberBasket().scrollTo().shouldBe(visible);
        } catch (ElementShould e) {
            refresh();
            numberBasket().scrollTo().shouldBe(visible);
        }
        return this;
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

    @Step("Adding product to basket. Product_page")
    public Product_page_Logic addProductToCart() {
        checkNumberBasketAndRefreshPageIfNot();
        refresh();
        sleep(3000); // TODO для стабилизации. Без слипа иногда добавленный товар исчезает из корзины после перехода в неё, решается в SITES-2830
        closeQuestionsPopupIfYes();
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
    @Step("Clicking gray button and checking appear available form. Product_page")
    public Product_page_Logic clickGrayButtonAndCheckAvailableForm() {
        grayButton().click();
        popupAvailableForm().should(appear);
        return this;
    }

    @Step("Check absence gray button on the page. Product_page")
    public Product_page_Logic checkAbsenceGrayBtnOnPage() {
        if (grayButton().isDisplayed()) {
            refresh();
            grayButton().shouldNotBe(visible);
        }
        return this;
    }

    @Step("Check presence gray button on the page. Product_page")
    public Product_page_Logic checkPresenceGrayBtnOnPage() {
        grayButton().shouldBe(visible);
        return this;
    }

    @Step(":in available form. Product_page")
    public Product_page_Logic checkingDatenschutzerklarungLinkBehaviorInAvailableForm() {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(datenschutzerklarungLinkInAvailableForm(), "underline solid rgb(0, 0, 0)");
        return this;
    }

    @Step("Filling fields and checking behavior of available form. Product_page")
    public String fillingFieldsAndCheckBehaviorAvailableForm(String qc) {
        String mail = qc + mailinatorMailRandom();
        emailFieldInPopUpOfGrayBtn().setValue(mail);
        checkboxInPopUpOfGrayBtn().click();
        sendButtonInPopUpOfGrayBtn().click();
        sendMailFormSuccesPopup().shouldBe(appear);
        sendMailFormSuccesPopupCloseBtn().click();
        return mail;
    }

    // OEN block
    @Step("Click first link in OEN block. Product_page")
    public Product_page_Logic clickFirstLinkInOenBlock() {
        linksInOenNumbersBlock().get(1).click();
        return this;
    }

    @Step("Subscriptions for product that are not in stock. Using mail {email}. Product_page")
    public Product_page_Logic sendRequestByGrayButtonFromProductPage(String email) {
        grayButton().click();
        emailFieldInPopUpOfGrayBtn().setValue(email);
        checkboxInPopUpOfGrayBtn().click();
        sendButtonInPopUpOfGrayBtn().click();
        closeSuccessPopUpOfGrayBtn().click();
        return this;
    }

    @Step("Scroll to OEN block. Product_page")
    public Product_page_Logic scrollToOenBlock() {
        oenBlockTitle().scrollTo();
        return this;
    }

    //Reviews Form
    @Step("Scroll to reviews from and checking appearing. Product_page")
    public Product_page_Logic checkingReviewsForm() {
        reviewsForm().scrollTo();
        reviewsForm().should(appear);
        return this;
    }

    @Step(":in reviews form. Product_page")
    public Product_page_Logic checkingDatenschutzerklarungLinkBehaviorInReviewsForm() {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(datenschutzerklarungLinkInReviewsForm(), "underline solid rgb(0, 0, 0)");
        return this;
    }

    @Step("Filling fields and checking behavior of reviews form. Product_page")
    public String fillingFieldsAndCheckBehaviorReviewsForm(String qc) {
        String mail = qc + mailinatorMailRandom();
        reviewsNameInput().setValue("autotest autotest");
        reviewsEmailInput().setValue(mail);
        reviewsMessageInput().setValue("autotest");
        checkboxInReviewsForm().click();
        reviewsSubmitButton().click();
        succesPopup().shouldBe(appear);
        succesPopupCloseBtn().click();
        return mail;
    }

    @Step("Checking correct shorting name in reviews form. Product_page")
    public Product_page_Logic checkingCorrectShortingNameReviewsForm() {
        refresh();
        reviewsFormAnsweredQuestionField().scrollTo();
        reviewsFormAnsweredQuestionField().shouldBe(visible);
        reviewsFormAnsweredQuestionNameField().shouldHave(text("A.A."));
        reviewsFormAnsweredQuestionCommentField().shouldHave(text("autotest"));
        return this;
    }

    @Step("Check that reviews form doesn't have any answered questions. Product_page")
    public Product_page_Logic checkAnswerQuestionsDoesntAppearReviewsForm() {
        refresh();
        checkingReviewsForm();
        reviewsFormAnsweredQuestionField().shouldNotBe(visible);
        return this;
    }


    //FAQ form
    @Step("Scroll to faq from and checking appearing. Product_page")
    public Product_page_Logic scrollToFaqForm() {
        faqForm().scrollTo();
        faqForm().shouldBe(visible);
        return this;
    }

    @Step(":in faq form. Product_page")
    public Product_page_Logic checkingDatenschutzerklarungLinkBehaviorFaqForm() {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(datenschutzerklarungLink(), "underline solid rgb(0, 0, 0)");
        return this;
    }

    @Step("Click checkbox in FAQ form. Product_page")
    public Product_page_Logic clickCheckboxInFaqForm() {
        faqCheckBox().shouldBe(visible).click();
        return this;
    }

    @Step("Filling fields and checking behavior of faq form. Product_page")
    public String fillingFieldsAndCheckBehaviorFaqForm(String qc) {
        faqFormNameField().setValue("autotest autotest");
        String mail = qc + mailinatorMailRandom();
        faqFormMailField().setValue(mail);
        faqFormCommentField().setValue("autotest");
        faqFormSendenBtn().click();
        faqFormSuccesPopup().shouldBe(appear);
        faqFormSuccesPopupCloseBtn().click();
        return mail;
    }

    @Step("Checking correct shorting name in faq form. Product_page")
    public Product_page_Logic checkingCorrectShortingNameFaqForm() {
        refresh();
        scrollToFaqForm();
        faqFormAnsweredQuestionField().shouldHave(text("A.A.: autotest"));
        return this;
    }

    @Step("Check that faq form doesn't have any answered questions. Product_page")
    public Product_page_Logic checkAnswerQuestionsDoesntAppearFaqForm() {
        refresh();
        scrollToFaqForm();
        faqFormAnsweredQuestionField().shouldNotBe(visible);
        return this;
    }


    @Step("Transition to pfand link from product page. Product_page")
    public Austauschartikel_static_page clickLinkPfandFromProductPage() {
        pfandPagelink().click();
        switchTo().window(1);
        return page(Austauschartikel_static_page.class);
    }

    @Step("Check presence labels with payment methods and advantages. Product_page")
    public Product_page_Logic checkLabelsPaymenMethodstAndAdvantages() {
        freeDeliveryIcon().shouldBe(visible);
        safeOrderIcon().shouldBe(visible);
        days14ForReturnOfGoodsIcon().shouldBe(visible);
        years2OnWarrantyIcon().shouldBe(visible);
        paymentMethodsBlock().shouldBe(visible);
        deliveryServicesBlock().shouldBe(visible);
        return this;
    }

    //compatibility block
    @Step("Clicking uncover characteristics. Product_page")
    public Product_page_Logic uncoverCharacteristics() {
        if (uncoverCharactericticBtn().isDisplayed()) {
            uncoverCharactericticBtn().click();
        }
        return this;
    }

    @Step("Gets all characteristics product. Product_page")
    public ElementsCollection getCharacteristicsOfProduct() {
        return $$(".product-block__description__info>ul>li").shouldHave(sizeGreaterThan(10));
    }

    // methods for body products FR
    @Step("Clicking and adding product to cart and checking popup for FR shop. Product_page")
    public Product_page clickAddToCartAndCheckPopupFR() {
        addToCartBtnFR().click();
        emailFieldInPopUpOfGrayBtn().shouldBe(appear);
        return this;
    }

    @Step("Compares the currency on the product page and in the cart popup. Product_page")
    public Product_page_Logic CompareCurrencyOnProductPageAndInBasketPopup(String shop) throws SQLException {
        String expectedCurrency = new DataBase("ATD").getCurrency(shop);
        if (priceWithoutDiscount().isDisplayed()) {
            getCurrencyAndVerify(priceWithoutDiscount(), "priceWithoutDiscount", shop, expectedCurrency);
        }
        getCurrencyAndVerify(productPrice(), "productPrice", shop, expectedCurrency);
        addProductToCart().closePopupOtherCategoryIfYes().cartIcon().hover();
        getCurrencyAndVerify(firstProductPriceInPopupOfCart(), "productPriceInPopupOfCart", shop, expectedCurrency);
        getCurrencyAndVerify(totalPriceInPopupOfCart(), "totalPriceInPopupOfCart", shop, expectedCurrency);
        return this;
    }

    @Step(":on Product_page")
    public Product_page_Logic checkingCounterIncrease(int increaseCount) {
        new CommonMethods().checkingCounterIncrease(increaseCount, counterValue(), counterPlus());
        return this;
    }

    //methods for related products popup
    @Step("Add product to basket and check related products popup. Product_page")
    public Product_page_Logic checkRelatedProductsPopup(int numberCategories) {
        buyButton().click();
        categoriesInRelatedProductsPopup().shouldHaveSize(numberCategories);
        return this;
    }

    @Step("Check related product popup close button. Product_page")
    public Product_page_Logic checkRelatedProductPopupClose(String route) {
        openPage(route);
        buyButton().click();
        closeBtnOfPopupOtherCategory().click();
        relatedProductsPopup().shouldNotBe(visible);
        closeWebDriver();
        return this;
    }

    @Step("Check related product popup back button. Product_page")
    public Product_page_Logic checkRelatedProductPopupBack(String route) {
        openPage(route);
        buyButton().click();
        backButtonInRelatedPopup().click();
        relatedProductsPopup().shouldNotBe(visible);
        closeWebDriver();
        return this;
    }

    @Step("Check related product popup go to basket button. Product_page")
    public Product_page_Logic checkRelatedProductPopupGoToBasket(String route) {
        openPage(route);
        buyButton().click();
        relatedProductPopupGoToCartButton().click();
        checkingContainsUrl("basket/account");
        closeWebDriver();
        return this;
    }

    @Step("Check Realated Popup Categories. Product_page")
    public Product_page_Logic checkRealatedPopupCategories(String route) {
        openPage(route);
        buyButton().click();
        Random random = new Random();
        relatedProductsPopup().shouldBe(visible);
        SelenideElement randomCategory = categoriesInRelatedProductsPopup().get(random.nextInt(categoriesInRelatedProductsPopup().size()));
        String categotyRef = randomCategory.attr("href");
        randomCategory.click();
        checkingContainsUrl(categotyRef);
        closeWebDriver();
        return this;
    }

    @Step("Check Realated Popup Overlay. Product_page")
    public Product_page_Logic checkRelatedPopupOverlay(String route) {
        openPage(route);
        buyButton().click();
        closeAnyPopupByClickOverlay();
        relatedProductsPopup().shouldNotBe(visible);
        closeWebDriver();
        return this;
    }

    @Step("Check link in alternative block. Product_page")
    public Product_page_Logic checkLinkInAlternativeBlock() {
        String articleNumberInBlock = tecdocAlternativeLink().text().replace("Artikel-Nr.:", "");
        String priceInBlock = tecdocAlternativePrice().text().replace("*", "");
        tecdocAlternativeLink().click();
        productPrice().shouldHave(text(priceInBlock));
        articleNumber().shouldHave(text(articleNumberInBlock));
        return this;
    }

    @Step("Get article number. Product_page")
    public String getArticleNumber() {
        return articleNumber().getText();
    }

    @Step("Check product in stock alternative block. Product_page")
    public Product_page_Logic checkProductInStockAlternativeBlock() {
        for (int i = 0; i < 6; i++) {
            analogAddToBasketButtons().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("Add article number to collection. Product_page")
    public ArrayList<String> addArtikelNumberToCollection() {
        ArrayList<String> listOfArticle = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            String cutArticle = analogArtikelNumbers().get(i).text().replace("Artikelnummer: ", "");
            listOfArticle.add(cutArticle);
        }
        return listOfArticle;
    }

    @Step("Check Analog Product Match Car. Product_page")
    public Product_page_Logic checkAnalogProductMatchCar(String route) {
        for (int i = 0; i < 6; i++) {
            analogProductsTitle().get(i).click();
            carMatchBlock().shouldHave(text("Dieses Produkt passt zu Ihrem VW Golf IV Schrägheck (1J1) 1.4 16V"));
            open(route);
        }
        return this;
    }

    @Step("Check Faq Validation With Empty Fields. Product_page")
    public Product_page_Logic faqValidationEmptyFields() {
        faqSubmitButton().click();
        faqPopup().shouldBe(visible);
        faqPopupText().shouldHave(text("Fehler"));
        faqPopupClose().click();
        validationEmailInputFAQ().shouldBe(visible);
        validationNameInputFAQ().shouldBe(visible);
        validationMessageInputFAQ().shouldBe(visible);
        return this;
    }

    @Step("Check Faq Validation With Name. Product_page")
    public Product_page_Logic faqValidationWithName() {
        faqNameInput().sendKeys("test_atd_faq");
        faqSubmitButton().click();
        faqPopupText().shouldHave(text("Fehler"));
        faqPopupClose().click();
        validationEmailInputFAQ().shouldBe(visible);
        validationNameInputFAQ().shouldNotBe(visible);
        validationMessageInputFAQ().shouldBe(visible);
        faqNameInput().clear();
        return this;
    }

    @Step("Check Faq Validation With Mail. Product_page")
    public Product_page_Logic faqValidationWithMail() {
        faqEmailInput().sendKeys("QC_707_autotestMail@mailinator.com");
        faqSubmitButton().click();
        faqPopupText().shouldHave(text("Fehler"));
        faqPopupClose().click();
        validationEmailInputFAQ().shouldNotBe(visible);
        validationNameInputFAQ().shouldBe(visible);
        validationMessageInputFAQ().shouldBe(visible);
        faqEmailInput().clear();
        return this;
    }

    @Step("Check Faq Validation With Message. Product_page")
    public Product_page_Logic faqValidationWithMessage() {
        faqMessageInput().sendKeys("TEST_MESSAGE");
        faqSubmitButton().click();
        faqPopupText().shouldHave(text("Fehler"));
        faqPopupClose().click();
        validationEmailInputFAQ().shouldBe(visible);
        validationNameInputFAQ().shouldBe(visible);
        validationMessageInputFAQ().shouldNotBe(visible);
        return this;
    }

    @Step("Check vin popup. Product_page")
    public Product_page_Logic checkVinPopup() {
        vinInfo().hover();
        vinInfoDropdown().shouldBe(visible);
        return this;
    }

    @Step("Enter FAQ valid data. Product_page")
    public Product_page_Logic enterFAQValidData(String randomEmail, String faqMessage) {
        faqNameInput().sendKeys("test_atd_faq");
        faqEmailInput().sendKeys(randomEmail);
        faqMessageInput().sendKeys(faqMessage);
        faqSubmitButton().click();
        faqPopupText().shouldHave(text("FAQ"));
        return this;
    }

    @Step("Check review form. Product_page")
    public Product_page_Logic checkReviewForm() {
        reviewsSubmitButton().click();
        faqPopup().shouldBe(visible);
        faqPopupClose().click();
        validationEmailInputReviews().shouldBe(visible);
        validationNameInputReviews().shouldBe(visible);
        validationMessageInputReviews().shouldBe(visible);
        validationSubscribeCheckbox().shouldBe(visible);
        return this;
    }

    @Step("Enter valid review data. Product_page")
    public Product_page_Logic enterValidReviewData(String randomEmail, String reviewMessage) {
        reviewsNameInput().sendKeys("test_atd_reviews");
        reviewsEmailInput().sendKeys(randomEmail);
        reviewsMessageInput().sendKeys(reviewMessage);
        subscribeAcceptCheckbox().click();
        reviewsSubmitButton().click();
        faqPopupText().shouldHave(text("Vielen Dank für Ihre Bewertung!"));
        return this;
    }

    @Step("Check OEM list. Product_page")
    public Product_page_Logic checkOEMlist() {
        productOnListing().click();
        boldOenText().shouldBe(visible);
        return this;
    }

    @Step("checkOEM list without car. Product_page")
    public Product_page_Logic checkOEMlistWithoutCar() {
        boldOenText().shouldNotBe(visible);
        String oenLink = linkInOemBlock().attr("href");
        linkInOemBlock().click();
        waitingWhileLinkBecomeExpected(oenLink);
        return this;
    }

    @Step("presence expected OEN number. Product_page")
    public Product_page_Logic presenceOenNumber(String expectedOenNumber) {
        linkInOemBlock().shouldBe(visible).shouldHave(text(expectedOenNumber));
        return this;
    }

    @Step("Check compatible car and product. Product_page")
    public Product_page_Logic checkCompatibilityCarAndProduct() {
        firstBrandInCompabilityList().shouldHave(text("AUTOBIANCHI")).click();
        firstModelInFirstBrandInCompatibilityList().click();
        carListInFirstModelCompabilityList().shouldBe(visible);
        chooseBrandModelTypeInHorizontalSelector("AUTOBIANCHI", "4822", "16213");
        selectorSearchBtn().click();
        compatibleCarInCompabilityList().shouldBe(visible);
        return this;
    }

    @Step("Check not compatible car and product. Product_page")
    public Product_page_Logic checkNotCompatibilityCarAndProduct() {
        firstBrandInCompabilityList().shouldHave(text("AUTOBIANCHI"));
        secondBrandInCompabilityList().shouldHave(text("FIAT"));
        thirdBrandInCompabilityList().shouldHave(text("LANCIA"));
        fourthBrandInCompabilityList().shouldHave(text("SEAT"));
        chooseBrandModelTypeInHorizontalSelector("VW", "4644", "14881");
        selectorSearchBtn().click();
        checkTextIsVisibleOnPage("Es tut uns leid!");
        checkTextIsVisibleOnPage("Kfz-Ersatzteile für VW 166 SUV Cabrio 1.1 Benzin (24 PS, Bj ab 1942)");
        return this;
    }

    @Step("Check incompstibility message. Product_page")
    public Product_page_Logic checkIncompatibilityMessage() {
        new Main_page_Logic().searchBar().sendKeys("Bremsscheiben");
        new Main_page_Logic().searchButton().click();
        nameProductOnListing().click();
        incompatibilityMessage().shouldBe(visible);
        return this;
    }

    @Step("Check dynamic characteristic. Product_page")
    public Product_page_Logic checkDynamicCharacteristic() {
        einzustellenderElektrodenabstandCharacteristic().shouldNotBe(visible);
        return this;
    }

    @Step("Get product price. Product_page")
    public Float getProductPrice() {
        String productPrice = productPrice().getText().replaceAll("[^0-9,]", "");
        productPrice = productPrice.replaceAll(",", ".");
        return Float.parseFloat(productPrice);
    }

    @Step("Get product ID. Product_page")
    public String getProductId() {
        return productId().getAttribute("id");
    }

    //Method for instruments product page
    @Step("Get title name product page instruments. Product_page")
    public String getTitleNameForProductPageInstruments() {
        return titleProductPageInstruments().getText();
    }

    //Method for chemicals product page
    @Step("Get title name product page chemicals. Product_page")
    public String getTitleNameForProductPageChemicals() {
        return titleNameProductPageChemicals().getText();
    }

    //Method for accessories product page
    @Step("Get title name product page accessories. Product_page")
    public String getTitleNameForProductPageAccessories() {
        return titleNameProductPageAccessories().getText();
    }


    @Step("Get product quanity from counter. Product_page")
    public String getProductQuanity() {
        return counterValueCommon().getValue();
    }

    @Step("Comparing actual and expected characteristics. Product_page")
    public void compareCharacteristics(ElementsCollection actualCharacteristics, List<String> expectedCharacteristics) {
        for (int a = 0; a < 5; a++) {
            String actualCharacteristicsWithoutSpaces = actualCharacteristics.get(a).text().replace(" ", "");
            String expectedCharacteristicsWithoutSpaces = expectedCharacteristics.get(a).replace(" ", "");
            Assert.assertEquals(actualCharacteristicsWithoutSpaces, expectedCharacteristicsWithoutSpaces);
        }
    }

    @Step("Check if reviews DE more than 20. Product_page")
    public Product_page checkIfReviewsDEmoreThan20() {
        allReviewsFromDE().shouldHave(sizeGreaterThan(19));
        allReviewsExceptDE().shouldHaveSize(0);
        return this;
    }

    @Step("Check if reviews DE less than 20. Product_page")
    public Product_page checkIfReviewsDElessThan20() {
        allReviewsFromDE().shouldHave(sizeLessThan(20));
        allReviewsExceptDE().shouldHave(sizeGreaterThan(0));
        return this;
    }

    @Step("Check text in product title. Product_page")
    public Product_page_Logic checkTextInProductTitle(String expextedText) {
        productTitle().shouldHave(text(expextedText));
        return this;
    }

    @Step("Check Product Fits Car Or Go To AWS And Check Universal Applicability. Product_page")
    public ProductCard_aws checkProductFitsCarOrGoToAWS(String expectedCarInCompatibilityBlock) {
        if (!productFitsCar().is(visible)) {
            String productId = getProductId();
            new ProductCard_aws(productId).openProductCardPageAndLogin()
                    .checkUniversalOrPKWApplicability();
        } else {
            productFitsCar().shouldHave(text(expectedCarInCompatibilityBlock));
        }
        return page(ProductCard_aws.class);
    }

    @Step("Checks presence block block approval ECE. Product_page")
    public Product_page_Logic checkPresenceBlockApprovalECE() {
        blockApprovalECE().shouldBe(visible);
        return this;
    }

    @Step("Checks text inside a block approval ECE. Product_page")
    public Product_page_Logic checkTextInsideBlockApprovalECE(String expectedText) {
        blockApprovalECE().shouldHave(text(expectedText));
        return this;
    }

    @Step("Check invisibility of characteristic. Product_page")
    public Product_page_Logic checkInvisibilityOfCharacteristic(SelenideElement characteriticLocator) {
        characteriticLocator.shouldNotBe(visible);
        return this;
    }

    @Step("Click all characteristics button if it is present. Product_page")
    public Product_page_Logic clickAllCharacteristicsButtonIfPresent() {
        if (uncoverCharactericticBtn().is(visible)) {
            uncoverCharactericticBtn().click();
        }
        return this;
    }

    @Step("added current url to list . Product_page")
    public Product_page_Logic addedIdOfBtnAddToBasketToList(List<String> list) {
        idOFBtnAddToBasket().shouldBe(visible);
        list.add(idOFBtnAddToBasket().getAttribute("id"));
        return this;
    }

    @Step("Input text in search bar. Product_page")
    public Product_page_Logic inputTextInSearchBar(String text) {
        searchBar().setValue(text);
        return this;
    }

    @Step("select product from hint of Search field. Product_page")
    public Product_page_Logic selectProductFromHintOfSearchField(String artNumOfProduct) {
        tooltipToSearch().shouldBe(visible);
        for (int i = 0; i < tooltipsToSearch().size(); i++) {
            if (tooltipsToSearch().get(i).has(text(artNumOfProduct))) {
                tooltipsToSearch().get(i).click();
            }
        }
        return this;
    }

    @Step("addedProduct to basket. Product_page")
    public Product_page_Logic addedProductToBasket() {
        buyButton().shouldBe(visible).click();
        basketDropMenu().should(appear);
        basketDropMenu().should(disappear);
        return this;
    }

    @Step("go to basket. Product_page")
    public Cart_page_Logic goToBasket() {
        if (otherCategoriesPopUp().isDisplayed()) {
            btnCloseOtherCategoriesPopUp().click();
            otherCategoriesPopUp().should(disappear);
        }
        basket().click();
        return page(Cart_page_Logic.class);
    }

    @Step("Get id from btn product. Product_page")
    public String getIdFromBtnProduct() {
        return idOFBtnAddToBasket().getAttribute("id");
    }

    @Step("Checks product price on site matches price on alldata page including VAT. Product_page")
    public Product_page_Logic checkProductPriceOnSitesMatchesPriceOnAllDataPageIncludingVat(Float priceWithVatPerAllDataPage, Float priceProductPerProductPage) {
        float res = roundOfTheCost(priceWithVatPerAllDataPage, priceProductPerProductPage);
        Assert.assertEquals(res, priceProductPerProductPage);
        return this;
    }

    @Step("checking the compatibility of goods and cars .Product_page")
    public Product_page_Logic checkCompatibilityProductAndGeneric() {
        breadcrumbsBlock().shouldBe(visible);
        if (compatibilityVehicleBlock().isDisplayed()) {
            linkOfCompatibilityVehicleAndProduct().shouldBe(visible);
        } else {
            String idOfProduct = url().replace(url().replace(url().substring(url().lastIndexOf("/")), ""), "").replaceAll("[^0-9]", "");
            executeJavaScript("window.open('about:blank','_blank')");
            switchTo().window(1);
            new ProductCard_aws(idOfProduct).openProductCardPageAndLogin().checkVehicleLabel();
            switchTo().window(1).close();
            switchTo().window(0);
        }
        return this;
    }

    @Step("Gets all characteristics product. Product_page")
    public List<String> getCharacteristics() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < valueOfCharacteristic().size(); i++) {
            list.add(titleOfCharacteristic().get(i).getText() + " " + valueOfCharacteristic().get(i).getText());
        }
        return list;
    }

    @Step("compare Actual and expected characteristic of product. Product_page")
    public Product_page_Logic compareActualAndExpectedCharacteristic(List<String> expectedCharacteristics, List<String> characteristicsFromProduct) {
        for (int i = 0; i < expectedCharacteristics.size(); i++) {
            for (int j = 0; j < characteristicsFromProduct.size(); j++) {
                Assert.assertTrue(expectedCharacteristics.contains(characteristicsFromProduct.get(j)));
            }
        }
        return this;
    }

    @Step("add product to WishList. Product_page")
    public Product_page_Logic addProductToWishList() {
        labelAddProductToWishList().shouldBe(visible).click();
        labelAddProductToWishList().shouldHave(attribute("class", "product-block__to-wishlist title_btn product-block__to-wishlist--added remove-article"));
        return this;
    }

    @Step("go to WishList page. Search_page")
    public Services_wishList_page_Logic goToWishListPage() {
        iconOfWishList().shouldBe(visible).click();
        return page(Services_wishList_page_Logic.class);
    }

    @Step("presence Refurbished Characteristic on product page. Product_page")
    public Product_page_Logic presenceRefurbishedCharacteristic(String expectedCharacteristic) {
        characteristicBlock().shouldBe(visible);
        List<String> listOfCharacteristic = allCharacteristics().stream().map(list -> getTextFromUnVisibleElement(list).replaceAll("\n", "").replace(" ", "")).collect(Collectors.toList());
        Assert.assertTrue(listOfCharacteristic.contains(expectedCharacteristic.replaceAll(" ", "")));
        return this;
    }

    @Step("presence Refurbished Characteristic in characteristics block. Product_page")
    public Product_page_Logic presenceRefurbishedCharacteristicWithArticle(String titleOfBrand, String expectedCharacteristic, String symbol) {
        characteristicBlock().shouldBe(visible);
        titleOfProduct().shouldBe(visible).shouldHave(text(titleOfBrand));
        String artNumOfProduct = artNumOfProduct().getText();
        Assert.assertTrue(artNumOfProduct.contains(symbol));
        List<String> listOfCharacteristic = allCharacteristics().stream().map(list -> list.getText().replaceAll("\n", "")).collect(Collectors.toList());
        Assert.assertTrue(listOfCharacteristic.contains(expectedCharacteristic));
        return this;
    }

    @Step("Get price without VAT. Product_page")
    public Float getExactPriceWithoutVAT(String vat) {
        float x = 1;
        float priseWithoutVat = 0.0f;
        float oldPrice = Float.parseFloat(priceWithoutDiscount().getText().replaceAll("[^0-9,]", "").replaceAll(",", "."));
        float discountSum = Float.parseFloat(discountSum().getText().replaceAll("\\D", ""));
        if (vat.equals("21")) {
            float percentDiscount = discountSum / 100f;
            float balanceOfPercent = x - percentDiscount;
            float productPrice = balanceOfPercent * oldPrice;
            priseWithoutVat = productPrice / 1.21f;
        }
        return priseWithoutVat;
    }

    @Step("Checking for the absence of the deposit characteristic for goods with a deposit. Product_page")
    public Product_page_Logic checkingAbsenceZustandCharacteristicForGoodsWithDeposit() {
        if (uncoverCharactericticBtn().isDisplayed()) {
            uncoverCharactericticBtn().click();
        }
        if (pfandBlock().isDisplayed()) {
            characteristicZustand().shouldNotBe(visible);
        } else if (!pfandBlock().isDisplayed()) {
            characteristicZustand().shouldBe(visible);
        }
        return this;
    }

    @Step("compare article number of product. Product_page")
    public Product_page_Logic compareArtNumOfProduct(String expectedArtNum) {
        artNumOfProduct().shouldBe(visible);
        Assert.assertEquals(artNumOfProduct().getText(), expectedArtNum);
        return this;
    }

    @Step("Get attribute of Warning icon in product block .Product_page")
    public List<String> getAttributeOfWarningIconInPopUp() {
        List<String> attribute = new ArrayList<>();
        for (int i = 0; i < attributeOfWarningIcon().size(); i++) {
            String attributeFromIcon = attributeOfWarningIcon().get(i).shouldBe(visible).getAttribute("style").replaceAll("background-image: url", "").replace("(", "").replace("\"", "");
            String partOfAtt = attributeFromIcon.replace(attributeFromIcon.substring(attributeFromIcon.lastIndexOf(".")), "");
            attribute.add(partOfAtt);
        }
        return attribute;
    }

    @Step("Get signal word from dangerous product. Product_page")
    public String getSignalWordFromDangerousProduct() {
        return signalWordOfDangerousProduct().getText();
    }

    @Step("Checking presence dangerous block. Product_page")
    public Product_page_Logic checkingPresenceDangerousBlock() {
        dangerousBlock().shouldBe(visible);
        return this;
    }

    @Step("Get text from Dangerous Block. Product_page")
    public String getTextFromDangerousBlock() {
        if (btnMehrFromDangerousBlock().isDisplayed()) {
            btnMehrFromDangerousBlock().click();
        }
        return textFromDangerousBlock().shouldBe(visible).getText();
    }

    @Step("Get name link safety data sheet. Product_page")
    public String getNameLinkSafetyDataSheet() {
        return safetyDataSheet().getText();
    }

    @Step("Click link safety data sheet and get url pdf page after click. Product_page")
    public String clickLinkSafetyDataSheetAndGetUrl() throws IOException {
        safetyDataSheet().shouldBe(visible).click();
        switchTo().window(1);
        String url = getCurrentUrl();
        closeWindow();
        switchTo().window(0);
        return url;
    }

    @Step("Get text from Excel and checking it with safety data sheet table. Product_page")
    public Product_page_Logic getTextFromExcelAndCheckingItWithSafetyDataSheetTable(String file, List<String> hazardStatement) {
        String allText = getTextFromDangerousBlock();
        List<String> numHazardStatement;
        List<String> textHazardStatement;
        numHazardStatement = new Excel().readFromExcel(file, 0);
        textHazardStatement = new Excel().readFromExcel(file, 1);
        for (int h = 0; h < hazardStatement.size(); h++) {
            String hazardIndex = hazardStatement.get(h);
            int indexFromData = numHazardStatement.indexOf(hazardIndex);
            String textFromData = textHazardStatement.get(indexFromData);
            if (!allText.contains(textFromData)) {
                Assert.fail("The text does not contain such a line");
            }
        }
        return this;
    }

    @Step("Checking the presence of the analog block , pictograms and mehr button. Product_page")
    public Product_page_Logic presenceOfTheAnalogBlockWithPictograms() {
        blockWithAlternativeMehrButton().scrollIntoView("{block: \"center\"}").shouldBe(visible, exist);
        blockWithAlternative().shouldBe(visible);
        for (int i = 0; i < pictogramsInBlock().size(); i++) {
            String iconInBlock = pictogramsInBlock().get(i).shouldBe(visible).getAttribute("style");
            blockWithAlternativePictograms().shouldBe(visible).shouldBe(visible, exist).click();
            popUpDangerous().shouldBe(visible);
            String iconInPopUp = pictogramsInPopUp().get(i).shouldBe(visible).getAttribute("style");
            Assert.assertEquals(iconInBlock, iconInPopUp);
            closePopUpButton().shouldBe(visible).click();
        }
        return this;
    }

    @Step("Checking the text and warning word in the  dangerous pop-up. Product_page")
    public Product_page_Logic presenceOfTheTextAndWarningWordInPopUp() {
        blockWithAlternativePictograms().click();
        popUpDangerousTitle().shouldBe(visible);
        Assert.assertFalse(popUpDangerousTitle().text().isEmpty());
        popUpDangerousText().shouldBe(visible);
        Assert.assertFalse(popUpDangerousText().text().isEmpty());
        closePopUpButton().shouldBe(visible).click();
        return this;
    }

    @Step("Closing the questions pop-up on the product page. Product_page")
    public Product_page_Logic closeQuestionsPopupIfYes() {
        try {
            popUpQuestionsProductPage().waitUntil(visible, 2500);
            popUpQuestionsCloseButton().click();
            popUpQuestionsProductPage().shouldBe(not(visible));
        } catch (ElementNotFound ignored) {
            System.out.println("Pop-up is not found");
        }
        return this;
    }

    @Step("Checking the transition to the Versand page after clicking the Versandkosten link . Product_page")
    public Product_page_Logic clickVerandkostenLink() throws SQLException {
        versandkostenButton().click();
        switchTo().window(1);
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "staticVersand"));
        closeWindow();
        switchTo().window(0);
        return this;
    }

    @Step("select  any car from OEN block. Product_page")
    public Category_oen_Page_Logic selectAnyCarFromOenBlock(int position) {
        oenBlockTitle().shouldBe(visible);
        activeOenLinks().get(position).shouldBe(visible).click();
        return page(Category_oen_Page_Logic.class);
    }

    @Step("presence of applicability block. Product_page")
    public Product_page_Logic presenceOfApplicabilityBlock() {
        applicabilityBlock().shouldBe(visible);
        return this;
    }

    @Step("click on any applicability  vehicle. Product_page")
    public Product_page_Logic clickOnAnyApplicabilityVehicle(int position) {
        applicabilityVehicle().get(position).click();
        applicabilityVehicleListBlock().shouldBe(visible).shouldHave(attribute("style", "display: block;"));
        return this;
    }

    @Step("get Marke from applicability vehicle. Product_page")
    public String getMarkeFromApplicabilityVehicle(int position) {
        return fullValueOfApplicabilityVehicle().get(position).attr("data-maker");
    }

    @Step("get Model from applicability vehicle. Product_page")
    public String getModelFromApplicabilityVehicle(int position) {
        return fullValueOfApplicabilityVehicle().get(position).attr("data-model");
    }

    @Step("get Motor from applicability vehicle. Product_page")
    public String getMotorFromApplicabilityVehicle(int position) {
        return fullValueOfApplicabilityVehicle().get(position).attr("data-prod");
    }

    @Step("select car in horizontal selector . Product_page")
    public Product_page_Logic selectCarHorizontalSelector(String brandName, String modelNumberValue, String typeNumberValue) {
        brandSelector().selectOptionByValue(brandName);
        chooseModelInHorizontalCarSelector(modelNumberValue)
                .chooseTypeInHorizontalCarSelector(typeNumberValue);
        return this;
    }

    @Step("presence of car match block. Product_page")
    public Product_page_Logic presenceOfCarMatchBlock() {
        carMatchBlock().waitWhile(visible, 10000);
        return this;
    }

    @Step("presence of compatibility car. Product_page")
    public Product_page_Logic presenceOfCompatibilityCar() {
        compatibilityVehicleBlock().shouldBe(visible);
        return this;
    }

    @Step("click on Third bread crumb link. Product_page")
    public Category_car_list_page_Logic clickOnThirdBreadCrumbLink(int position) {
        breadcrumbsBlock().shouldBe(visible);
        breadCrumbLinks().get(position).shouldBe(visible).hover().click();
        return page(Category_car_list_page_Logic.class);
    }

    @Step("check visible characteristic. Product page")
    public Product_page_Logic checkVisibleCharacteristic() {
        for (int i = 0; i < visibleCharacteristic().size(); i++) {
            visibleCharacteristic().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("checking the alternative name of the product . Product page")
    public Product_page_Logic checkAlternativeTitleOfProduct(String alternativeTitle) {
        titleOfProduct().shouldBe(visible).shouldHave(text(alternativeTitle));
        return this;
    }

    @Step("popup about the incompatibility of car and product. Product page")
    public Product_page_Logic incompatibilityOfCarAndProductPopUp(String expectedText) {
        infoPopUp().shouldBe(visible).shouldHave(text(expectedText));
        return this;
    }

    @Step("visibility of car match block. Product page")
    public Product_page_Logic visibilityOfCarMatchBlock(String car) {
        infoBlockWithSelectedCar().shouldBe(visible).shouldHave(text(car));
        return this;
    }

    @Step("Click banner autodoc club. Product_page")
    public AutodocClub_page_Logic clickBannerAutodocClub() {
        bannerAutodocClub().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        bannerAutodocClub().click();
        return page(AutodocClub_page_Logic.class);
    }

    @Step("get url autodoc club from banner autodoc club. Product_page")
    public String getUrlAutodocClubFromBannerAutodocClub() {
        return bannerAutodocClub().getAttribute("url");
    }

    @Step("compare applicability car. Product page")
    public Product_page_Logic compareApplicabilityCar(String marke, String model) {
        linkOfCompatibilityVehicleAndProduct().shouldBe(visible).shouldHave(text(marke)).shouldHave(text(model.replaceAll("(.+\\))(\\s\\(.+)", "$1")));
        return this;
    }

    @Step(" check location of kit composition block. Product page")
    public Product_page_Logic locationOfKitCompositionBlock() {
        kitCompositionBlock().shouldBe(visible);
        //  kitCompositionBlockUnderPdf().shouldBe(visible);   // УБРАЛ ПРОВЕРКУ, ТАК КАК УБРАЛИ PDF БЛОК
        kitCompositionBlockAboveFeedbBckBlock().shouldBe(visible);
        return this;
    }

    @Step("check products from kit composition block. Product page")
    public Product_page_Logic checkProductsFromKitCompositionBlock() {
        for (int i = 0; i < productsFromKitCompositionBlock().size(); i++) {
            String artNUm = artNumOfProductsFromKitCompositionBlock().get(i).getText();
            productsFromKitCompositionBlock().get(i).shouldBe(visible).click();
            pageReload();
            checkArtNumOfProduct(artNUm);
            back();
            pageReload();
        }
        return this;
    }

    @Step("check article number of product. Product page")
    public Product_page_Logic checkArtNumOfProduct(String artNum) {
        Assert.assertEquals(valueOfArtNumProduct().getText(), artNum);
        return this;
    }

    @Step("presenceTAB block. Product page")
    public Product_page_Logic presenceTabBlock(int countOfLinks) {
        tabBlock().shouldBe(visible);
        linksOfTabBlock().shouldHaveSize(countOfLinks);
        return this;
    }

    @Step("check TAB links. Product page")
    public Product_page_Logic checkTabLinks(String route) {

        for (int i = 0; i < linksOfTabBlock().size(); i++) {
            Long descriptionLin = (Long) (executeJavaScript("return window.pageYOffset;", linksOfTabBlock().get(0)));
            Long oemBlock = (Long) (executeJavaScript("return window.pageYOffset;", linksOfTabBlock().get(1)));
            Long videoBlock = (Long) (executeJavaScript("return window.pageYOffset;", linksOfTabBlock().get(2)));
            Long reviewsBlock = (Long) (executeJavaScript("return window.pageYOffset;", linksOfTabBlock().get(3)));
            Long faqBlock = (Long) (executeJavaScript("return window.pageYOffset;", linksOfTabBlock().get(4)));
            Long usageNumberBlock = (Long) (executeJavaScript("return window.pageYOffset;", linksOfTabBlock().get(5)));
            linksOfTabBlock().get(i).click();
            switch (i) {
                case 0:
                    linksOfTabBlock().get(0).shouldHave(attribute("href", "" + route + "#section1"));
                    Assert.assertNotEquals(descriptionLin, (Long) (executeJavaScript("return window.pageYOffset;", linksOfTabBlock().get(0))));
                    break;
                case 1:
                    linksOfTabBlock().get(1).shouldHave(attribute("href", "" + route + "#section3"));
                    Assert.assertNotEquals(oemBlock, (Long) (executeJavaScript("return window.pageYOffset;", linksOfTabBlock().get(1))));
                    break;
                case 2:
                    linksOfTabBlock().get(2).shouldHave(attribute("href", "" + route + "#section6"));
                    Assert.assertNotEquals(videoBlock, (Long) (executeJavaScript("return window.pageYOffset;", linksOfTabBlock().get(2))));
                    break;
                case 3:
                    linksOfTabBlock().get(3).shouldHave(attribute("href", "" + route + "#section7"));
                    Assert.assertNotEquals(reviewsBlock, (Long) (executeJavaScript("return window.pageYOffset;", linksOfTabBlock().get(3))));
                    break;
                case 4:
                    linksOfTabBlock().get(4).shouldHave(attribute("href", "" + route + "#faq"));
                    Assert.assertNotEquals(faqBlock, (Long) (executeJavaScript("return window.pageYOffset;", linksOfTabBlock().get(4))));
                    break;
                case 5:
                    linksOfTabBlock().get(5).shouldHave(attribute("href", "" + route + "#section8"));
                    Assert.assertNotEquals(usageNumberBlock, (Long) (executeJavaScript("return window.pageYOffset;", linksOfTabBlock().get(5))));
                    break;
                default:
                    break;
            }
        }
        return this;
    }

    @Step("presence Of Interesting article Links. Product page")
    public Product_page_Logic presenceOfInterestingArticleLinks() {
        for (int i = 0; i < interestingArticleLinks().size(); i++) {
            interestingArticleLinks().get(i).shouldBe(visible);
        }

        return this;
    }

    @Step("checking the count of reviews. Product page")
    public Product_page_Logic checkCountOfReviews() {
        headlineOfFeedBackBlock().scrollTo();
        int countFromReviewsLink = Integer.parseInt(linksOfTabBlock().get(3).getText().replaceAll("[^0-9]", "").trim());
        countOfReviewsInHeadOfBlock().shouldBe(visible);
        int countFromReviewsBlock = Integer.parseInt(countOfReviewsInHeadOfBlock().getText().replaceAll("[^0-9]", "").trim());
        Assert.assertEquals(countFromReviewsBlock, countFromReviewsLink);
        reviews().shouldHaveSize(countFromReviewsLink);
        return this;
    }

    @Step("display of Ridex info block. Product page")
    public Product_page_Logic displayRidexInfoBlock() {
        ridexInfoBlock().shouldBe(visible);
        return this;
    }

    @Step("absece of Ridex info block. Product page")
    public Product_page_Logic absenceOfRidexInfoBlock() {
        ridexInfoBlock().shouldNotBe(visible);
        return this;
    }

    @Step("check elements of ridex info block. Product page")
    public Product_page_Logic checkElementsOfRidexInfoBlock() {
        ridexLogo().shouldBe(visible);
        aboutRidexText().shouldBe(visible).shouldHave(exactText("Über RIDEX"));
        btnShowMoreInRidexBlock().shouldBe(visible).shouldHave(exactText("Mehr anzeigen"));
        return this;
    }

    @Step("open Pdf file. Product page")
    public Product_page_Logic openPdfFile() {
        btnShowMoreInRidexBlock().shouldBe(visible).click();
        background().shouldHave(attribute("style", "display: block;"));
        return this;
    }


    @Step("close Pdf file. Product page")
    public Product_page_Logic closePdfFile() {
        Selenide.actions().moveToElement(btnShowMoreInRidexBlock()).click().build().perform();
        return this;
    }

    @Step("presence Price per meter title. Product page")
    public Product_page_Logic presencePricePerMeterTitle() {
        pricePerMeter().shouldBe(visible);
        return this;
    }

    @Step("check location of candles analog block. Product page")
    public Product_page_Logic checkLocationOfCandlesAnalogBlock() {
        locationOfCandlesAnalogBlock().shouldBe(visible).shouldHave(attribute("class", "product-info-block"));
        return this;
    }

    @Step("check elements of candles analog block. Product page")
    public Product_page_Logic checkElementsOfCandlesAnalogBlock() {
        headlineOfCandlesAnalogBlock().shouldBe(visible);
        for (int i = 0; i < brandsInCandlesAnalogBlock().size(); i++) {
            brandsInCandlesAnalogBlock().get(i).shouldBe(visible).shouldNotBe(empty);
            artListInCandlesAnalogBlock().get(i).shouldBe(visible).shouldNotBe(empty);
        }
        return this;
    }

    @Step("display Of Dangerous Info Block. Product page")
    public Product_page_Logic displayOfDangerousInfoBlock() {
        dangerousInfoBlock().shouldBe(visible);
        return this;
    }

    @Step("display Of Dangerous signal word. Product page")
    public Product_page_Logic displayOfDangerousSignalWord(String dangerousWord) {
        dangerousSignalWord().shouldBe(visible).shouldHave(text(dangerousWord));
        return this;
    }

    @Step("compare Dangerous Pictogram With Aws. Product page")
    public Product_page_Logic compareDangerousPictogramWithAws(List<String> listFromAws) {
        List<String> dangerousPictogram = new ArrayList<>(getAttributeOfWarningIconInPopUp());
        Assert.assertEquals(dangerousPictogram, listFromAws);
        return this;
    }

    @Step("compare Dangerous Info Text With Aws. Product page")
    public Product_page_Logic compareDangerousInfoTextWithAws(List<String> dangerousLabel, String file) {
        if (btnMoreOfDangerousInfoBlock().isDisplayed()) {
            btnMoreOfDangerousInfoBlock().click();
        }
        Excel exelFile = new Excel();
        List<String> dangerousInfoText = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        List<String> allDangerousLabels = exelFile.readFromExcel(file, "qc_2811", 1);
        for (int i = 0; i < allDangerousLabels.size(); i++) {
            labels.add(allDangerousLabels.get(i).replaceAll("\\s", ""));
        }
        for (int i = 0; i < dangerousLabel.size(); i++) {
            int labelPosition = labels.indexOf(dangerousLabel.get(i));
            dangerousInfoText.add(exelFile.getCellValueFromExel(file, "qc_2811", 2, labelPosition));
        }
        for (String e : dangerousInfoText) {
            Assert.assertTrue(infoTextOfDangerousInfoBlock().getText().contains(e));
        }
        return this;
    }

    @Step("size Of Breadcrumb links. Product page")
    public Product_page_Logic sizeOfBreadCrumbsLinks(int size) {
        breadcrumbsBlock().shouldBe(visible);
        breadCrumbLinks().shouldHaveSize(size);
        return this;
    }


    @Step("close Marke field tool-tip. Product page")
    public Product_page_Logic closeMarkeFieldToolTip() {
        if (tooltipOfMarkeField().isDisplayed()) {
            tooltipOfMarkeField().click();
        }
        return this;
    }

    @Step("check Transitions of breadcrumbs links. Product page")
    public Product_page_Logic checkTransitionsOfBreadcrumbsLinks(String parentCategory, String mainCategory) throws SQLException {
        DataBase db = new DataBase("ATD");
        clickOnFirstBreadcrumbLink();
        checkingContainsUrl(db.getRouteByRouteName("DE", "categories"));
        back();
        clickOnSecondBreadcrumbLink(parentCategory);
        checkingContainsUrl(db.getRouteByRouteName("DE", "category_name_parent3"));
        back();
        clickOnThirdBreadcrumbLink(mainCategory);
        checkingContainsUrl(db.getRouteByRouteName("DE", "motoroil"));
        back();
        checkFourthBreadcrumbLink();
        return this;
    }

    @Step("click on First breadcrumb link. Product page")
    public Categories_page_Logic clickOnFirstBreadcrumbLink() {
        catalogIconInBreadcrumbLink().shouldBe(visible);
        breadCrumbLinks().get(0).shouldBe(visible).click();
        return page(Categories_page_Logic.class);
    }

    @Step("click on Second breadcrumb link. Product page")
    public Category_name_parent_page_Logic clickOnSecondBreadcrumbLink(String parentCategory) {
        catalogIconInBreadcrumbLink().shouldBe(visible);
        breadCrumbLinks().get(1).shouldBe(visible).shouldHave(text(parentCategory)).click();
        return page(Category_name_parent_page_Logic.class);
    }

    @Step("click on Third breadcrumb link. Product page")
    public Motoroil_page_Logic clickOnThirdBreadcrumbLink(String mainCategory) {
        catalogIconInBreadcrumbLink().shouldBe(visible);
        breadCrumbLinks().get(2).shouldBe(visible).shouldHave(text(mainCategory)).click();
        return page(Motoroil_page_Logic.class);
    }

    @Step("check Fourth breadcrumb link. Product page")
    public Product_page_Logic checkFourthBreadcrumbLink() {
        catalogIconInBreadcrumbLink().shouldBe(visible);
        breadCrumbLinks().get(3).shouldBe(visible).shouldNotHave(attribute("href"));
        return this;
    }

    @Step("Check Faq block without answers. Product_page")
    public Product_page_Logic checkFAQBlockWithoutAnswers() {
        faqBlockWithoutAnswer().shouldBe(visible);
        textAboutQuantityOfAnswersInFAQ().shouldHave(text("0 Fragen"));
        blockVersandInFAQ().shouldBe(visible);
        faqBlockWithAnswer().shouldNotBe(visible);
        return this;
    }

    @Step("check number in the quantity block and block with quantity of product on product page. Product_page")
    public Product_page_Logic checkQuantityBlockVisibilityOnProductPage() {
        countInputOnProduct().shouldBe(visible);
        if (countInputOnProduct().has(attribute("value", "1"))) {
            textAboutCountOnProduct().shouldBe(visible);
        } else {
            textAboutCountOnProduct().shouldNotBe(visible);
        }
        return this;
    }

    @Step("presence of Phrase about compatibility product and vehicle .Product_page")
    public Product_page_Logic presenceOfPhraseAboutCompatibilityProductAndVehicle() {
        if (articleNumber().getText().equals("O33B0001")) {
            phraseAboutCompatibilityProductAndVehicle().shouldNotBe(visible);
        } else phraseAboutCompatibilityProductAndVehicle().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        return this;
    }

    @Step("presence of gluing block .Product_page")
    public Product_page_Logic presenceOfGluingBlock() {
        gluingBlock().shouldBe(visible);
        return this;
    }

    @Step("get Current Volume .Product_page")
    public String getCurrentVolume() {
        return currentVolume().getText();
    }

    @Step("select Volume .Product_page")
    public Product_page_Logic selectVolume(int position) {
        allowableVolumes().get(position).click();
        return this;
    }

    @Step("check Allowable Volume .Product_page")
    public Product_page_Logic checkAllowableVolume(int position, String volume) {
        allowableVolumes().get(position).shouldHave(exactText(volume));
        return this;
    }

    @Step("presence Of Recommended Change Liter Icon .Product_page")
    public Product_page_Logic presenceOfRecommendedChangeLiterIcon(String expectedText) {
        recommendedChangeLiterIcon().shouldBe(visible).shouldHave(text(expectedText));
        return this;
    }

    @Step("get Id Of Gluing Product .Product_page")
    public String getIdOfGluingProduct(int position) {
        return gluingProducts().get(position).attr("id");
    }

    @Step("click On Btn Add Gluing Product To Basket.Product_page")
    public Product_page_Logic clickOnBtnAddGluingProductToBasket(int position) {
        btnAddGluingProductToBasket().get(position).shouldBe(visible).click();
        btnAddGluingProductToBasket().get(position).shouldHave(attribute("class", "select-displacement__btn still_add_to_basket ga-click in_cart"));
        btnAddGluingProductToBasket().get(position).shouldNotHave(attribute("class", "select-displacement__btn still_add_to_basket ga-click in_cart"));
        return this;
    }

    @Step("display Basket Drop Menu. Product_page")
    public Product_page_Logic displayBasketDropMenu() {
        basketDropMenu().should(appear);
        basketDropMenu().should(disappear);
        return this;
    }

    @Step("click on basket in header. Product_page")
    public Cart_page_Logic clickOnBasketInHeader() {
        basket().click();
        return page(Cart_page_Logic.class);
    }

    @Step("close basket pop-up.Product_page")
    public Product_page_Logic closeBasketPopUp() {
        if (btnCloseBasketPopUp().isDisplayed()) {
            btnCloseBasketPopUp().click();
        }
        return this;
    }

    @Step("check Visibility of Basket Drop Menu. Product_page")
    public Product_page_Logic checkVisibilityOfBasketDropMenu() {
        if (basketDropMenu().isDisplayed()) {
            basketDropMenu().waitWhile(visible, 5000);
        }
        return this;
    }

    @Step("check Matching Options Values. Product_page")
    public Product_page_Logic checkMatchingOptionsValues(List<String> values) {
        valuesOfProductUnderTitle().shouldBe(visible);
        List<String> valuesFromProductPage = Arrays.asList(valuesOfProductUnderTitle().getText().split(", "));
        Collections.sort(valuesFromProductPage);
        Collections.sort(values);
        Assert.assertEquals(valuesFromProductPage, values);
        return this;
    }

    @Step("hover on basket in header. Product_page")
    public Product_page_Logic hoverOnBasketInHeader() {
        basket().hover();
        return this;
    }

    @Step("display of Kba Selector Error ToolTip. Product_page")
    public Product_page_Logic displayKbaSelectorErrorToolTip(String errorMessage) {
        selectorKbaBtn().shouldBe(visible).click();
        errorToolTipOfKbaSelector().shouldHave(text(errorMessage));
        return this;
    }

    @Step("Checks presence VAT postscript {expectedVatPostscript}")
    public Product_page_Logic checkPresenceVatPostscript(String expectedVatPostscript) {
        vatPostscript().shouldHave(text(expectedVatPostscript));
        return this;
    }

    @Step("presence of Video Block. Product_page")
    public Product_page_Logic presenceVideoBlock() {
        videoBlock().shouldBe(visible);
        return this;
    }

    @Step("display Of Navigation Arrows Of Video Block. Product_page")
    public Product_page_Logic displayOfNavigationArrowsOfVideoBlock() {
        upNavigationArrowOfVideoBlock().shouldBe(visible);
        downNavigationArrowOfVideoBlock().shouldBe(visible);
        return this;
    }

    @Step("display Of video files. Product_page")
    public Product_page_Logic displayOfVideoFiles() {
        allVideoFiles().shouldHave(sizeGreaterThan(0));
        return this;
    }

    @Step("click On Down Navigation Arrow. Product_page")
    public Product_page_Logic clickOnDownNavigationArrow() {
        videoBlock().scrollTo();
        String idOfSecondVisibleVideoFile = idOfVisibleVideoFiles().get(1).attr("data-id");
        downNavigationArrowOfVideoBlock().click();
        currentIdVideo().shouldHave(attribute("data-id", idOfSecondVisibleVideoFile));
        return this;
    }

    @Step("click On Up Navigation Arrow. Product_page")
    public Product_page_Logic clickOnUpNavigationArrow() {
        videoBlock().scrollTo();
        upNavigationArrowOfVideoBlock().click();
        String idOfFirstVisibleVideoFile = idOfVisibleVideoFiles().get(0).attr("data-id");
        currentIdVideo().shouldHave(attribute("data-id", idOfFirstVisibleVideoFile));
        return this;
    }

    @Step(" presence Of Visible Video File. Product_page")
    public Product_page_Logic presenceOfVisibleVideoFile() {
        for (int i = 0; i < idOfVisibleVideoFiles().size(); i++) {
            idOfVisibleVideoFiles().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("click On Current Video File. Product_page")
    public Product_page_Logic clickOnCurrentVideoFile() {
        btnPlayOfVideoFile().click();
        return this;
    }

    @Step("presence of Faq Block. Product_page")
    public Product_page_Logic presenceFaqBlock() {
        faqForm().shouldBe(visible);
        return this;
    }

    @Step("location Of Faq Elements. Product_page")
    public Product_page_Logic locationOfFaqElements() {
        if (questionFaqBlock().isDisplayed()) {
            locationOfPaymentBlockToFaq().shouldBe(visible).shouldHave(attribute("id", "faq"));
        }
        return this;
    }

    @Step("check Id Of Video File. Product_page")
    public Product_page_Logic checkIdOfVideoFile(String expectedVideoId) {
        currentIdVideo().shouldBe(visible).shouldHave(attribute("data-id", expectedVideoId));
        return this;
    }

    @Step("get id of current video file. Product_page")
    public String getIdOfCurrentVideoFile() {
        String id = currentIdVideo().getAttribute("data-id");
        return id;
    }

    @Step("checking the change of the current video. Product_page")
    public Product_page_Logic checkChangeOfCurrentVideo(String id) {
        currentIdVideo().shouldBe(visible).shouldNotHave(attribute("data-id", id));
        return this;
    }

    @Step("presence Of Selected Brand And Model. Product_page")
    public Product_page_Logic presenceOfSelectedBrandAndModel(String brand, String model) {
        Main_page_Logic mainPage = new Main_page_Logic();
        mainPage.displayAllCategoriesInHeader();
        if (brandSelector().getSelectedValue().equals("0")) {
            chooseBrandInHorizontalCarSelector(brand)
                    .selectModelInHorizontalCarSelector(model).getIdOfCurrentVideoFile();
            refresh();
            mainPage.displayAllCategoriesInHeader();
        } else if (brandSelector().getSelectedText().equals(brand) && modelSelector().getSelectedValue().equals("0")) {
            selectModelInHorizontalCarSelector(model).getIdOfCurrentVideoFile();
            refresh();
            mainPage.displayAllCategoriesInHeader();
        }
        return this;
    }

    @Step("presence of upload file. Product_page")
    public Product_page_Logic presenceOfUploadFile(String uploadFileFromAws) {
        String uploadedFileProductPage = ridexInfoBlock().getAttribute("href").replaceAll(".+\\/", "");
        Assert.assertEquals(uploadedFileProductPage, uploadFileFromAws);
        return this;
    }

    @Step("compare Quantity Of Product And Write To File. Product_page")
    public Product_page_Logic compareQuantityOfProductAndWriteToFile(String expectedQuantity, String idOfProduct, String fileName) throws IOException {
        String countOfProduct = countInputOnProduct().shouldBe(visible).attr("value");
        if (!countOfProduct.equals(expectedQuantity)) {
            new CommonMethods().writerInFile(fileName, true, idOfProduct);
            System.out.println(idOfProduct + " - quantity is different. " + String.format("In file quantity - %s , on product page - %s", expectedQuantity, countOfProduct));
        }
        return this;
    }

    @Step("Check presence grey button from block unit by litre. Product_page")
    public Product_page_Logic checkPresenceGreyButtonFromBlockUnitByLitre() {
        greyButtonFromBlockUnitByLitre().shouldBe(visible);
        return this;
    }

    @Step("display Marke value In Selector. Product_page")
    public Product_page_Logic displayMarkeInSelector(String expectedMarke) {
        Assert.assertEquals(brandSelector().getSelectedValue(), expectedMarke);
        return this;
    }

    @Step("Gets any characteristic of product . Product_page")
    public String getCharacteristicProduct( String nameCharacteristic ) {
        return characteristicOfProduct(nameCharacteristic).getText();
    }

    @Step("Check text product info price. Product_page")
    public Product_page_Logic checkTextProductInfoPrice(String expectedText) {
        productInfoPrice().shouldBe(visible).shouldHave(exactText(expectedText));
        return this;
    }

    @Step("Check absence text product info price. Product_page")
    public Product_page_Logic checkAbsenceTextProductInfoPrice() {
        productInfoPrice().shouldNotBe(visible);
        return this;
    }


}
