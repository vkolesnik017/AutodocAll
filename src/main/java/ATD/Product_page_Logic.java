package ATD;

import AWS.ProductCard_aws;
import Common.DataBase;
import Common.Excel;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.ex.ElementShould;
import com.codeborne.selenide.ex.UIAssertionError;
import io.qameta.allure.Step;
import org.testng.Assert;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import static ATD.CommonMethods.*;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.sizeLessThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Product_page_Logic extends Product_page {

    @Step("Checks that links contain pdf file")
    public void checkPdfLinksForDownload() {
        for (int i = 0; i < pdfLinksForDownload().size(); i++) {
            Assert.assertTrue(pdfLinksForDownload().get(i).attr("href").contains(".pdf"), "Document for tutorial downloading is not in .pdf format");
        }
    }

    @Step("Open product page by route {route} and his ID {idProduct} number. Product_page")
    public Product_page_Logic openProductPageById(String route, String idProduct) {
        open(route + "/a/" + idProduct);
        return this;
    }

    @Step("Checking quantity {quantityInCart} on basket icon. Product_page")
    public Product_page_Logic checkQuantityOnBasketIconEquals(int quantityInCart) {
        quantityOnBasketIcon().shouldHave(exactText(String.valueOf(quantityInCart)));
        return this;
    }

    @Step("Checking present product in cart popup. Product_page")
    public Product_page_Logic checksPresentProductInCartPopup() {
        cartIcon().hover();
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
    //----------------------------------------------------------------------------------------------------------

    @Step("Checking heavy cargo link transition. Product_page")
    public Product_page_Logic checkingHeavyCargoLinkTransition() {
        heavyCargoLink().click();
        new CommonMethods().checkingUrlAndCloseTab("services/versand#surcharge");
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
    @Step("Clicking gray button and checking appear available form. Product_page")
    public Product_page_Logic clickGrayButtonAndCheckAvailableForm() {
        grayButton().click();
        popupAvailableForm().should(appear);
        return this;
    }

    @Step(":in available form. Product_page")
    public Product_page_Logic checkingDatenschutzerklarungLinkBehaviorInAvailableForm() {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(datenschutzerklarungLinkInAvailableForm(), "underline solid rgb(0, 0, 0)");
        return this;
    }

    @Step("Filling fields and checking behavior of available form. Product_page")
    public String fillingFieldsAndCheckBehaviorAvailableForm(String qc) {
        String mail = qc + mailRandom();
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
        String mail = qc + mailRandom();
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

    @Step("Filling fields and checking behavior of faq form. Product_page")
    public String fillingFieldsAndCheckBehaviorFaqForm(String qc) {
        faqFormNameField().setValue("autotest autotest");
        String mail = qc + mailRandom();
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
        faqPopupText().shouldHave(text("Danke für Ihre Beurteilung."));
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
        BigDecimal result = new BigDecimal(priceWithVatPerAllDataPage);
        BigDecimal formatPriceUp = result.setScale(2, RoundingMode.UP);
        float roundMax = Float.parseFloat(String.valueOf(formatPriceUp));
        BigDecimal formatPriceDOWN = result.setScale(2, RoundingMode.FLOOR);
        float roundMin = Float.parseFloat(String.valueOf((formatPriceDOWN)));
        float res = 0.0f;
        if (priceProductPerProductPage.equals(roundMax)) {
            res = roundMax;
        }
        if (priceProductPerProductPage.equals(roundMin)) {
            res = roundMin;
        }
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
    public String  clickLinkSafetyDataSheetAndGetUrl() throws IOException {
        safetyDataSheet().shouldBe(visible).click();
        switchTo().window(1);
        String url = getCurrentUtl();
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
}