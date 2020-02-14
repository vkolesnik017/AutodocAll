package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.ex.ElementShould;
import com.codeborne.selenide.ex.UIAssertionError;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;



import static ATD.CommonMethods.mailRandom;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class Product_page {

  @Step("Checking number of product in cart")
  public Product_page checkingNumberOfProductInCart(int expectedNumber) {
    new Main_page().checkingNumberOfProductInCart(expectedNumber);
    return this;
  }

  @Step
  public Product_page openProductPageById(String route, String idProduct) {
    open(route + "/a/" + idProduct);
    return this;
  }

  @Step
  public Cart_page cartClick() {
    new Main_page().cartClick();
    return page(Cart_page.class);
  }

  //common locators
  private SelenideElement quantityOnBasketIcon() {
    return $x("//div[@class='header-cart__info']//span[1]");
  }

  private SelenideElement numberBasket() {
    return $(byCssSelector(".code"));
  }

  public SelenideElement cartIcon() {
    return $(byCssSelector(".header-cart__count"));
  }

  private SelenideElement buyButton() {
    return $(byCssSelector(".product-button>a"));
  }

  private SelenideElement closeBtnOfPopupOtherCategory() {
    return $(byCssSelector(".popup-other-cat__close"));
  }

  public SelenideElement grayButton() {
    return $(byCssSelector(".button.not_active>a"));
  }

  public SelenideElement infoBlockWithSelectedCar() {
    return $(".car-match-block>p>b");
  }

  public SelenideElement uncoverCharactericticBtn() {    return $(".show-more-button>span"); }

  public SelenideElement coverCharacteristicBtn() { return $(".show-more-button > .active"); }

  public SelenideElement pfandBlock() {
    return $(".product-eco-block");
  }

  public SelenideElement freeDeliveryIcon() {
    return $(".features-gratis-icon");
  }

  public SelenideElement safeOrderIcon() {
    return $(".features-safe-icon");
  }

  public SelenideElement days14ForReturnOfGoodsIcon() {
    return $(".features-tage-icon");
  }

  public SelenideElement years2OnWarrantyIcon() {
    return $(".features-guarantee-icon");
  }

  public SelenideElement paymentMethodsBlock() {
    return $(".pay-method__items");
  }

  public SelenideElement deliveryServicesBlock() {
    return $(".delivery-method");
  }

  public SelenideElement carSelectorBlock() { return $(".atd-carselector__content"); }

  public SelenideElement minimizedCharactericticBlock() { return $(".show-more-height"); }

  public SelenideElement maximizedCharacteristicBlock() { return $(".height"); }

  public SelenideElement featuresBlock() { return $(".product-block__description__features"); }

  public SelenideElement jahreIcon() { return $(".features-guarantee-icon > span > b"); }

  public SelenideElement tagePopup() { return $(".tage > div"); }

  public SelenideElement tageIcon() { return $(".features-tage-icon > span > b"); }

  public SelenideElement safeOrderIconColor() { return $(".features-safe-icon > span > b"); }

  public SelenideElement safeOrderPopup() { return $(".safe_order  > div"); }

  public SelenideElement productImageBlock() { return $(".im-prod-zoom"); }

  public SelenideElement imageInSlider() { return $x("//*[@class='product-thumbnail-slider']/li[2]"); }

  public SelenideElement closeFullImagePreview() { return $(".fancybox-close"); }

  public SelenideElement fullProductImage() { return $(".fancybox-skin"); }

  public SelenideElement productImageSlider() { return $(".product-block__image-slider"); }

  public SelenideElement priceBlock() { return $(".product-block__price-block__price"); }

  public SelenideElement brandButtonOnImage() { return $(".brand.btn"); }

  public SelenideElement versandkostenButton() { return $(".product-inkl-info > a"); }

  public SelenideElement raitingBlock() { return $(".product-block__price-block__rating"); }

  public SelenideElement feedbackBlock() { return $(".product-feedback"); }

  public SelenideElement similarPropertiesBlock() { return  $(".product-same-specification"); }

  public SelenideElement linkInSimilarPropertiesBlock() { return $(".product-same-specification__item > p > a"); }

  public SelenideElement minicardsBlock() { return $(".product-same-artikel-tyres"); }

  public SelenideElement productInMinicard() { return $x("//*[contains(@class,'product-list__item active')]"); }

  public SelenideElement tetleMiniCardBlock() { return $(".top-product-block__title"); }

  public SelenideElement characteristicsInMinicard() { return $(".item-table-box"); }

  public SelenideElement videoBlock() { return $(".product-video-tutorial__video"); }

  public SelenideElement pdfTutorialsBlock() { return $(".product-video-tutorial__tutorials"); }

  private ElementsCollection pdfLinksForDownload() { return $$(".tutorial-text > a"); }

  @Step("Checks that links contain pdf file")
  public void checkPdfLinksForDownload() {
    for (int i = 0; i < pdfLinksForDownload().size(); i++) {
      Assert.assertTrue(pdfLinksForDownload().get(i).attr("href").contains(".pdf"), "Document for tutorial downloading is not in .pdf format");
    }
  }

  //breadcrumbs locators
  public SelenideElement breadcrumbsBlock() { return $(".search_mod_n"); }

  public SelenideElement breadcrumbsCategoryDropdownButton() { return $x("//*[@class='step_3 active parts_step_3']//i"); }

  public SelenideElement breadcrumbsFirstCategoryFromDropdown() { return $x("//*[@id='mCSB_3_container']//li[2]"); }

  public SelenideElement breadcrumbsCategoryButton() { return $(".parts_step_3"); }

  public SelenideElement breadcrumbsFirstParentCategoryFromDropdown() { return $x("//*[@id='mCSB_2_container']//li[2]"); }

  public SelenideElement breadcrumbsParentCategoryDropdownButton() { return $x("//*[@class='step_2 active parts_step_2']//i"); }

  public SelenideElement breadcrumbsParentCategotyButton() { return $(".parts_step_2"); }

  public SelenideElement breadcrumbsCatalogButton() { return $(".parts_step_1"); }

  //locators for FAQ block
  public SelenideElement faqSubmitButton() { return $("#sended_btn2"); }

  public SelenideElement faqNameInput() { return $("#zum_name2"); }

  public SelenideElement faqEmailInput() { return $("#zum_email2"); }

  public SelenideElement faqMessageInput() { return $("#zum_message2"); }

  public SelenideElement faqPopup() { return $("#popup_update"); }

  public SelenideElement faqPopupText() { return $(".ttl"); }

  public SelenideElement faqPopupClose() { return $(".buttons-inner"); }

  public SelenideElement validationNameInputFAQ() { return $("#zum_name2.invalid"); }

  public SelenideElement validationEmailInputFAQ() { return $("#zum_email2.invalid"); }

  public SelenideElement validationMessageInputFAQ() { return $("#zum_message2.invalid"); }

  public SelenideElement vinInfo() { return $(".product-leave-feedback__wrap__row > a"); }

  public SelenideElement vinInfoDropdown() { return $(".vin-info-dropdown"); }

  //locators for Reviews block
  public SelenideElement reviewsNameInput() { return $("#form_rating_name"); }

  public SelenideElement reviewsEmailInput() { return $("#form_rating_email"); }

  public SelenideElement reviewsMessageInput() { return $("#form_rating_message"); }

  public SelenideElement validationNameInputReviews() { return $(".rating_name"); }

  public SelenideElement validationEmailInputReviews() { return $(".rating_email"); }

  public SelenideElement validationSubscribeCheckbox() { return $(".subscribe_accept"); }

  public SelenideElement validationMessageInputReviews() { return $(".rating_message"); }

  public SelenideElement reviewsSubmitButton() { return $(".btn_leave_review_from_page"); }

  public SelenideElement subscribeAcceptCheckbox() { return $("#subscribe_accept"); }

  //locators for compatibility block
  public SelenideElement compatibleCarBrand() { return $(".accordion-button > span"); }

  public SelenideElement firstBrandInCompabilityList() { return $x("//*[@class='details_info_box']/div[1]//a"); }

  public SelenideElement secondBrandInCompabilityList() { return $x("//*[@class='details_info_box']/div[2]//a"); }

  public SelenideElement thirdBrandInCompabilityList() { return $x("//*[@class='details_info_box']/div[3]//a"); }

  public SelenideElement fourthBrandInCompabilityList() { return $x("//*[@class='details_info_box']/div[4]//a"); }

  public SelenideElement firstModelInFirstBrandInCompatibilityList() { return $x("//*[@class='details_info_box']/div[1]/div/div[2]"); }

  public SelenideElement carListInFirstModelCompabilityList() { return $x("//*[@class='dropdown_list']"); }

  public SelenideElement compatibleCarInCompabilityList() { return $x("//*[@class='accordion-selected']"); }

  public SelenideElement productOnListing() { return $x("//*[@class='name']"); }

  public SelenideElement incompatibilityMessage() { return $(".car-match-block--false"); }

  public void checkTextIsVisibleOnPage(String textToCheck) { $x("//*[contains(text(),'" + textToCheck + "')]").shouldBe(visible); }


  @Step
  public Product_page uncoverCharacteristics() {
    uncoverCharactericticBtn().click();
    return this;
  }

  private SelenideElement heavyCargoLink() {
    return $(By.xpath("//p[@class='product-inkl-info']/a[2]"));
  }

  @Step
  public Product_page checkingHeavyCargoLinkTransition() {
    heavyCargoLink().click();
    new CommonMethods().checkingUrlAndCloseTab("services/versand#surcharge");
    return this;
  }

  @Step
  public Product_page addProductToCart() {
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

  @Step
  public Product_page checkQuantityOnBasketIconEquals(int quantityInCart) {
    quantityOnBasketIcon().shouldHave(exactText(String.valueOf(quantityInCart)));
    return this;
  }

  @Step
  public Product_page checkNumberBasketAndRefreshPageIfNot() {  // TODO Бывает при открытии страницы не подгружается номер корзины и товар не добавляется в корзину, причина не известна, что бы стабилизировать тесты добавлен этот метод
    try {
      numberBasket().shouldBe(visible);
    } catch (ElementShould e) {
      refresh();
      numberBasket().shouldBe(visible);
    }
    return this;
  }

  @Step
  public Product_page closePopupOtherCategoryIfYes() {
    try {
      closeBtnOfPopupOtherCategory().waitUntil(visible, 2500);
      closeBtnOfPopupOtherCategory().click();
      closeBtnOfPopupOtherCategory().shouldBe(not(visible));
    } catch (ElementNotFound ignored) {
    }
    return this;
  }

  @Step("Gets all characteristics product")
  public ElementsCollection getCharacteristicsOfProduct() {
    return $$(".product-block__description__info>ul>li").shouldHave(sizeGreaterThan(10));
  }

  // locators of prices with Currencies
  public SelenideElement priceWithoutDiscount() {
    return $(byCssSelector(".product-old-price>span"));
  }

  public SelenideElement productPrice() {
    return $(byCssSelector(".product-new-price"));
  }

  public SelenideElement productInfoUnderPrice() {
    return $(byCssSelector(".product-inkl-info"));
  }

  // locators in popup of cart
  public SelenideElement firstProductPriceInPopupOfCart() {
    return $(byCssSelector(".row-price"));
  }

  @Step
  public Product_page checksPresentProductInCartPopup() {
    cartIcon().hover();
    firstProductPriceInPopupOfCart().shouldBe(visible);
    return this;
  }

  public SelenideElement totalPriceInPopupOfCart() {
    return $(byCssSelector(".row-right>p"));
  }

  // locators in popup of gray button for subscription for product which is not stock
  public SelenideElement emailFieldInPopUpOfGrayBtn() {
    return $(byId("form_AvailabilityReminder[email]"));
  }

  public SelenideElement sendButtonInPopUpOfGrayBtn() {
    return $(byCssSelector(".popup-available__button"));
  }

  public SelenideElement checkboxInPopUpOfGrayBtn() {
    return $(byCssSelector(".popup-available__label"));
  }

  public SelenideElement closeSuccessPopUpOfGrayBtn() {
    return $(byXpath("//div[@class='popup_top']//a[@class='close']"));
  }

  //Methods and locators for Selector Horizontal
  @Step("Choose brand in selector")
  public Product_page chooseBrandInSelector(String brandName) {
    brandSelector().find("optgroup").should(exist);
    brandSelector().selectOption(brandName);
    return this;
  }

  @Step("Choose model in selector")
  public Product_page chooseModelInSelector(String modelNumberValue) {
    modelSelector().find("optgroup").should(exist);
    modelSelector().selectOptionByValue(modelNumberValue);
    return this;
  }

  @Step("Choose type in selector")
  private Product_page chooseTypeInSelector(String typeNumberValue) {
    typeSelector().find("optgroup").should(exist);
    typeSelector().selectOptionByValue(typeNumberValue);
    typeSelector().shouldHave(value(typeNumberValue));
    return this;
  }

  @Step("Choose brand, model, type in horizontal selector")
  public Product_page chooseBrandModelTypeInSelector(String brandName, String modelNumberValue, String typeNumberValue) {
    chooseBrandInSelector(brandName);
    chooseModelInSelector(modelNumberValue);
    chooseTypeInSelector(typeNumberValue);
    return this;
  }

  @Step
  // return current chosen value from selector
  public String getChosenValueFromSelector(SelenideElement selector) {
    return executeJavaScript("return arguments[0].selectedOptions[0].innerText", selector);
  }

  public SelenideElement brandSelector() {
    return $("#form_maker_id");
  }

  public SelenideElement modelSelector() {
    return $("#form_model_id");
  }

  public SelenideElement typeSelector() {
    return $("#form_car_id");
  }

  public SelenideElement selectorSearchBtn() {
    return $(".search_button");
  }

  public SelenideElement errorTooltipOfBrandSelector() {
    return $(byId("selector-error-tooltip"));
  }

  public SelenideElement errorToolTipOfModelSelector() {
    return $(byId("selector-error-tooltip-model"));
  }

  public SelenideElement errorToolTipOfTypeSelector() {
    return $(byId("selector-error-tooltip-car"));
  }

  public SelenideElement errorToolTipOfKbaSelector() {
    return $(byId("kba-error-tooltip"));
  }

  public SelenideElement resetBtnSelector() {
    return $(byId("reset_selector_form"));
  }

  //Methods and locators for Selector kba
  @Step
  public Product_page fillNumberKba(String numberForFirstField, String numberForSecondField) {
    $(byId("kba1")).setValue(numberForFirstField);
    $(byId("kba2")).setValue(numberForSecondField);
    return this;
  }

  public SelenideElement selectorKbaBtn() {
    return $(".kba_submit");
  }

  // locators for OEN block
  public ElementsCollection linksInOenNumbersBlock() {
    return $$x("//*[@class='oem-list']//li");
  }

  public SelenideElement boldOenText() { return $(".text-semibold"); }

  public SelenideElement linkInOemBlock() { return $x("//*[@class='oem-list']//li/a"); }

  // locator for counter
  private SelenideElement counterValue() {
    return $(By.xpath("//input[@class=' qty_2']"));
  }

  private SelenideElement counterPlus() {
    return $(By.xpath("//a[@class='ga-click plus add']"));
  }

  private SelenideElement counterMinus() {
    return $(By.xpath("//a[@class='ga-click minus remove']"));
  }

  public Product_page counterIncrease(String startValue) {
    new CommonMethods().checkingCounterIncrease(startValue, counterValue(), counterPlus());
    return this;
  }

  public Product_page counterDecrease(String startValue) {
    new CommonMethods().checkingCounterDecrease(startValue, counterValue(), counterMinus());
    return this;
  }

  //locators adn methods for body products FR
  private SelenideElement addToCartBtnFR() {
    return $(By.xpath("//div[@class='product-button button not_active out-of-stock']/a"));
  }

    public Product_page clickAddToCartAndCheckPopupFR() {
        addToCartBtnFR().click();
        emailFieldInPopUpOfGrayBtn().shouldBe(appear);
        return this;
    }

    //locators and methods for FAQ form
    private SelenideElement faqForm() {
        return $(By.id("faq"));
    }

    private SelenideElement faqFormNameField() {
        return $(By.id("zum_name2"));
    }

    private SelenideElement faqFormMailField() {
        return $(By.id("zum_email2"));
    }

    private SelenideElement faqFormCommentField() {
        return $(By.id("zum_message2"));
    }

    private SelenideElement faqFormSendenBtn() {
        return $(By.id("sended_btn2"));
    }

    private SelenideElement faqFormSuccesPopup() {
        return $(By.id("popup_update"));
    }

    private SelenideElement faqFormSuccesPopupCloseBtn() {
        return $(By.xpath("//div[@id='popup_update']//div[@class='buttons-inner']/a"));
    }

    private SelenideElement datenschutzerklarungLink() {
        return $(By.xpath("//div[@id='faq']//a[@title='Datenschutzerklärung']"));
    }

    public Product_page scrollToFaqForm() {
        faqForm().scrollTo();
        faqForm().shouldBe(visible);
        return this;
    }

    public Product_page checkingDatenschutzerklarungLinkBehavior() {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(datenschutzerklarungLink());
        return this;
    }

    public String fillingFieldsAndCheckBehaviorFaqForm() {
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
