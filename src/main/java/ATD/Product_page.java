package ATD;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class Product_page {

    //common locators
    SelenideElement quantityOnBasketIcon() {
        return $x("//div[@class='header-cart__info']//span[1]");
    }

    SelenideElement numberBasket() {
        return $(byCssSelector(".code"));
    }

    public SelenideElement cartIcon() {
        return $(byCssSelector(".header-cart__count"));
    }

    SelenideElement buyButton() {
        return $(byCssSelector(".product-button>a"));
    }

    SelenideElement productId() {
        return $(byCssSelector(".product-button"));
    }

    SelenideElement closeBtnOfPopupOtherCategory() {
        return $(byCssSelector(".popup-other-cat__close"));
    }

    SelenideElement grayButton() {
        return $(byCssSelector(".button.not_active>a"));
    }

    public SelenideElement infoBlockWithSelectedCar() {
        return $x("//div[@class='car-match-block']/p");
    }

    public SelenideElement uncoverCharactericticBtn() {
        return $(".show-more-button>span");
    }

    public SelenideElement coverCharacteristicBtn() {
        return $(".show-more-button > .active");
    }

    public SelenideElement pfandBlock() {
        return $(".product-eco-block");
    }

    SelenideElement pfandPagelink() {
        return $x("//div[@class='product-eco-block']//a[@target='_blank']");
    }

    SelenideElement freeDeliveryIcon() {
        return $(".gratis-versand__in-card");
    }

    public SelenideElement safeOrderIcon() {
        return $(".features-safe-icon");
    }

    SelenideElement days14ForReturnOfGoodsIcon() {
        return $(".features-tage-icon");
    }

    SelenideElement years2OnWarrantyIcon() {
        return $(".features-guarantee-icon");
    }

    public SelenideElement paymentMethodsBlock() {
        return $(".pay-method__items");
    }

    public SelenideElement deliveryServicesBlock() {
        return $(".delivery-method");
    }

    public SelenideElement carSelectorBlock() {
        return $(".atd-carselector__content");
    }

    public SelenideElement minimizedCharactericticBlock() {
        return $(".show-more-height");
    }

    public SelenideElement maximizedCharacteristicBlock() {
        return $(".height");
    }

    public SelenideElement featuresBlock() {
        return $(".product-block__description__features");
    }

    public SelenideElement jahreIcon() {
        return $(".features-guarantee-icon > span > b");
    }

    public SelenideElement tagePopup() {
        return $(".tage > div");
    }

    public SelenideElement tageIcon() {
        return $(".features-tage-icon > span > b");
    }

    public SelenideElement safeOrderIconColor() {
        return $(".features-safe-icon > span > b");
    }

    public SelenideElement safeOrderPopup() {
        return $(".safe_order  > div");
    }

    public SelenideElement productImageBlock() {
        return $(".im-prod-zoom");
    }

    public SelenideElement imageInSlider() {
        return $x("//*[@class='product-thumbnail-slider']/li[2]");
    }

    public SelenideElement closeFullImagePreview() {
        return $(".fancybox-close");
    }

    public SelenideElement fullProductImage() {
        return $(".fancybox-skin");
    }

    public SelenideElement productImageSlider() {
        return $(".product-block__image-slider");
    }

    public SelenideElement priceBlock() {
        return $(".product-block__price-block__price");
    }

    public SelenideElement brandButtonOnImage() {
        return $(".brand.btn");
    }

    public SelenideElement versandkostenButton() {
        return $(".product-inkl-info > a");
    }

    public SelenideElement raitingBlock() {
        return $(".product-block__price-block__rating");
    }

    public SelenideElement feedbackBlock() {
        return $(".product-feedback");
    }

    public SelenideElement similarPropertiesBlock() {
        return $(".product-same-specification");
    }

    public SelenideElement linkInSimilarPropertiesBlock() {
        return $x("//div[2][@class='product-same-specification__item']//a");
    }

    public SelenideElement minicardsBlock() {
        return $(".product-same-artikel-tyres");
    }

    public SelenideElement productInMinicard() {
        return $x("//*[contains(@class,'product-list__item active')]");
    }

    public SelenideElement tetleMiniCardBlock() {
        return $(".top-product-block__title");
    }

    public SelenideElement characteristicsInMinicard() {
        return $(".item-table-box");
    }

    public SelenideElement videoBlock() {
        return $(".product-video-tutorial__video");
    }

    public SelenideElement pdfTutorialsBlock() {
        return $(".product-video-tutorial__tutorials");
    }

    ElementsCollection pdfLinksForDownload() {
        return $$(".tutorial-text > a");
    }

    SelenideElement einzustellenderElektrodenabstandCharacteristic() { return $x("//span[contains(text(),'einzustellender Elektrodenabstand [mm]:')]"); }

    public SelenideElement mengeCharacteristic() { return $x("//div[@class='product-block__description__info']//span[contains(text(),'Menge:')]"); }

    SelenideElement safetyDataSheet() {
        return $x("//div[@class='safety-data-sheet']/a");
    }


    //breadcrumbs locators
    public SelenideElement breadcrumbsBlock() {
        return $(".search_mod_n");
    }

    public SelenideElement breadcrumbsCategoryDropdownButton() {
        return $x("//*[@class='step_3 active parts_step_3']//i");
    }

    public SelenideElement breadcrumbsFirstCategoryFromDropdown() {
        return $x("//*[@id='mCSB_3_container']//li[2]");
    }

    public SelenideElement breadcrumbsCategoryButton() {
        return $(".parts_step_3");
    }

    public SelenideElement breadcrumbsFirstParentCategoryFromDropdown() {
        return $x("//*[@id='mCSB_2_container']//li[2]");
    }

    public SelenideElement breadcrumbsParentCategoryDropdownButton() {
        return $x("//*[@class='step_2 active parts_step_2']//i");
    }

    public SelenideElement breadcrumbsParentCategotyButton() {
        return $(".parts_step_2");
    }

    public SelenideElement breadcrumbsCatalogButton() {
        return $(".parts_step_1");
    }

    //locators for FAQ block
    public SelenideElement faqSubmitButton() {
        return $("#sended_btn2");
    }

    public SelenideElement faqNameInput() {
        return $("#zum_name2");
    }

    public SelenideElement faqEmailInput() {
        return $("#zum_email2");
    }

    public SelenideElement faqMessageInput() {
        return $("#zum_message2");
    }

    public SelenideElement faqPopup() {
        return $("#popup_update");
    }

    public SelenideElement faqPopupText() {
        return $(".ttl");
    }

    public SelenideElement faqPopupClose() {
        return $(".buttons-inner");
    }

    public SelenideElement validationNameInputFAQ() {
        return $("#zum_name2.invalid");
    }

    public SelenideElement validationEmailInputFAQ() {
        return $("#zum_email2.invalid");
    }

    public SelenideElement validationMessageInputFAQ() {
        return $("#zum_message2.invalid");
    }

    public SelenideElement vinInfo() {
        return $(".product-leave-feedback__wrap__row > a");
    }

    public SelenideElement vinInfoDropdown() {
        return $(".vin-info-dropdown");
    }

    //locators for Reviews Form
    public SelenideElement reviewsNameInput() {
        return $("#form_rating_name");
    }

    public SelenideElement reviewsEmailInput() {
        return $("#form_rating_email");
    }

    public SelenideElement reviewsMessageInput() {
        return $("#form_rating_message");
    }

    public SelenideElement validationNameInputReviews() {
        return $(".rating_name");
    }

    public SelenideElement validationEmailInputReviews() {
        return $(".rating_email");
    }

    public SelenideElement validationSubscribeCheckbox() {
        return $(".subscribe_accept");
    }

    public SelenideElement validationMessageInputReviews() {
        return $(".rating_message");
    }

    public SelenideElement reviewsSubmitButton() {
        return $(".btn_leave_review_from_page");
    }

    public SelenideElement subscribeAcceptCheckbox() {
        return $("#subscribe_accept");
    }

    SelenideElement reviewsForm() {
        return $(By.id("add_review_form"));
    }

    SelenideElement checkboxInReviewsForm() {
        return $(By.xpath("//form[@id='add_review_form']//input[@id='subscribe_accept']"));
    }

    SelenideElement datenschutzerklarungLinkInReviewsForm() {
        return $(By.xpath("//form[@id='add_review_form']//label[@id='privacy_policy_review_form']/a"));
    }

    SelenideElement succesPopup() {
        return $(By.id("popup_update"));
    }

    SelenideElement succesPopupCloseBtn() {
        return $(By.xpath("//div[@id='popup_update']//div[@class='buttons-inner']/a"));
    }

    SelenideElement reviewsFormAnsweredQuestionField() {
        return $(By.xpath("//div[@class='product-feedback_wrap_comments']"));
    }

    SelenideElement reviewsFormAnsweredQuestionNameField() {
        return $(By.xpath("//span[@class='product-comments__user']"));
    }

    SelenideElement reviewsFormAnsweredQuestionCommentField() {
        return $(By.xpath("//div[@class='product-comments__text']"));
    }

    ElementsCollection allReviewsFromDE() { return $$x("//ul[@class='product-comments__list']//*[@data-country='de']"); }

    ElementsCollection allReviewsExceptDE() { return $$x("//ul[@class='product-comments__list']//*[@data-country!='de']"); }


    //locators for compatibility block
    public SelenideElement compatibleCarBrand() {
        return $(".accordion-button > span");
    }

    public SelenideElement firstBrandInCompabilityList() {
        return $x("//*[@class='details_info_box']/div[1]//a");
    }

    public SelenideElement secondBrandInCompabilityList() {
        return $x("//*[@class='details_info_box']/div[2]//a");
    }

    public SelenideElement thirdBrandInCompabilityList() {
        return $x("//*[@class='details_info_box']/div[3]//a");
    }

    public SelenideElement fourthBrandInCompabilityList() {
        return $x("//*[@class='details_info_box']/div[4]//a");
    }

    public SelenideElement firstModelInFirstBrandInCompatibilityList() {
        return $x("//*[@class='details_info_box']/div[1]/div/div[2]");
    }

    public SelenideElement carListInFirstModelCompabilityList() {
        return $x("//*[@class='dropdown_list']");
    }

    public SelenideElement compatibleCarInCompabilityList() {
        return $x("//*[@class='accordion-selected']");
    }

    public SelenideElement productOnListing() {
        return $x("//*[@class='name']");
    }

    public SelenideElement nameProductOnListing() {
        return $x("//div[@class='name']/a");
    }

    public SelenideElement incompatibilityMessage() {
        return $(".car-match-block--false");
    }

    public void checkTextIsVisibleOnPage(String textToCheck) {
        $x("//*[contains(text(),'" + textToCheck + "')]").shouldBe(visible);
    }

    SelenideElement heavyCargoLink() {
        return $(By.xpath("//p[@class='product-inkl-info']/a[2]"));
    }


    // locators of prices with Currencies
    public SelenideElement priceWithoutDiscount() {
        return $(byCssSelector(".product-old-price>span"));
    }

    public SelenideElement discountSum() {
        return $x("//div[@class='product-block__price-block__price']//div[@class='discount']");
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

    public SelenideElement totalPriceInPopupOfCart() {
        return $(byCssSelector(".row-right>p"));
    }

    // locators gray button popup for subscription for product which is not stock
    SelenideElement popupAvailableForm() {
        return $(byClassName("popup-available"));
    }

    SelenideElement emailFieldInPopUpOfGrayBtn() {
        return $(byId("form_AvailabilityReminder[email]"));
    }

    SelenideElement sendButtonInPopUpOfGrayBtn() {
        return $(byCssSelector(".popup-available__button"));
    }

    SelenideElement checkboxInPopUpOfGrayBtn() {
        return $(byCssSelector(".popup-available__label"));
    }

    SelenideElement closeSuccessPopUpOfGrayBtn() {
        return $(byXpath("//div[@class='popup_top']//a[@class='close']"));
    }

    SelenideElement datenschutzerklarungLinkInAvailableForm() {
        return $(byCssSelector("#AvailabilityReminderprivacy_policy>a"));
    }

    SelenideElement sendMailFormSuccesPopup() {
        return $(By.id("news_subscribe"));
    }

    SelenideElement sendMailFormSuccesPopupCloseBtn() {
        return $(By.xpath("//div[@class='buttons']//div[@class='buttons-inner']/a"));
    }


    // Horizontal car selector

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

    SelenideElement resetBtnSelector() {
        return $(byId("reset_selector_form"));
    }

    // Selector kba
    SelenideElement firstFieldKBA() {
        return $(byId("kba1"));
    }

    SelenideElement secondFieldKBA() {
        return $(byId("kba2"));
    }

    SelenideElement selectorKbaBtn() {
        return $(".kba_submit");
    }

    // OEN block
    ElementsCollection linksInOenNumbersBlock() {
        return $$x("//*[@class='oem-list']//li");
    }

    public SelenideElement boldOenText() {
        return $x("//li[@class='text-semibold']");
    }

    public SelenideElement linkInOemBlock() {
        return $x("//*[@class='oem-list']//li/a");
    }

    SelenideElement oenBlockTitle() {return $(".product-info-block__oem__title"); }

    // locator for counter
    SelenideElement counterValuePairedGood() {
        return $(By.xpath("//input[@class=' qty_2']"));
    }

    SelenideElement counterValue() {
        return $x("//input[@class=' qty_1']");
    }

    SelenideElement counterValueCommon() {
        return $x("//div[@class='count product-count']/input");
    }

    SelenideElement counterPlus() {
        return $(By.xpath("//a[@class='ga-click plus add']"));
    }

    SelenideElement counterMinus() {
        return $(By.xpath("//a[@class='ga-click minus remove']"));
    }


    //locators for body products FR
    SelenideElement addToCartBtnFR() {
        return $(By.xpath("//div[@class='product-button button not_active out-of-stock']/a"));
    }

    //locators and methods for FAQ form
    SelenideElement faqForm() {
        return $(By.id("faq"));
    }

    SelenideElement faqFormNameField() {
        return $(By.id("zum_name2"));
    }

    SelenideElement faqFormMailField() {
        return $(By.id("zum_email2"));
    }

    SelenideElement faqFormCommentField() {
        return $(By.id("zum_message2"));
    }

    SelenideElement faqFormSendenBtn() {
        return $(By.id("sended_btn2"));
    }

    SelenideElement faqFormAnsweredQuestionField() {
        return $(By.xpath("//p[@class='question']"));
    }

    SelenideElement faqFormSuccesPopup() {
        return $(By.id("popup_update"));
    }

    SelenideElement faqFormSuccesPopupCloseBtn() {
        return $(By.xpath("//div[@id='popup_update']//div[@class='buttons-inner']/a"));
    }

    SelenideElement datenschutzerklarungLink() {
        return $(By.xpath("//div[@id='faq']//a[@title='Datenschutzerklärung']"));
    }

    //locators for related products popup
    ElementsCollection categoriesInRelatedProductsPopup() { return $$(".popup-other-cat__content-item"); }

    SelenideElement backButtonInRelatedPopup() { return $(".back"); }

    SelenideElement relatedProductsPopup() { return $(".popup-other-cat"); }

    SelenideElement relatedProductPopupGoToCartButton() { return $(".go-cart"); }

    SelenideElement tecdocAlternativeLink() { return $x("(//a[@data-gac='Tecdoc_analog_click'])[1]/span[2]"); }

    SelenideElement tecdocAlternativePrice() { return $x("(//div[@class='product-same-specification__wrap__col'])[1]//div[3]//span/span"); }

    SelenideElement articleNumber() { return $(".subtitle-art-nummer > span"); }

    ElementsCollection analogAddToBasketButtons() { return $$x("//a[@data-gac='Product_analog_click']"); }

    ElementsCollection analogArtikelNumbers() { return $$(".product-list__item__nummer"); }

    ElementsCollection analogProductsTitle() { return $$x("//*[@class='product-list__item active']"); }

    SelenideElement carMatchBlock() { return $x("//*[@class='car-match-block']/p"); }

// locators for instruments products page
    SelenideElement titleProductPageInstruments() {
        return $x("//h2/span[@class='title']");
    }

// locators for chemicals products page
    SelenideElement titleNameProductPageChemicals() {
        return $x("//h2/span[@class='title']");
    }

// locators for accessories page
    SelenideElement titleNameProductPageAccessories() {
        return $x("//h2/span[@class='title']");
    }

    SelenideElement productFitsCar() { return $(".accordion-selected"); }

    SelenideElement productTitle() { return $(".title"); }

    SelenideElement blockApprovalECE() {
        return $x("//div[@class='lamp-usage-text']");
    }

    SelenideElement idOFBtnAddToBasket() { return  $x("//div[@class='product-button button ']");}

    SelenideElement searchBar() {return $(byId("search")); }

    public SelenideElement tooltipToSearch() { return $(".autocomplete-suggestions>div"); }

    public ElementsCollection tooltipsToSearch() { return $$(".autocomplete-suggestions>div"); }


    SelenideElement basketDropMenu() {
        return $x("//div[@class='cart-items-block ']");
    }

    SelenideElement btnGoToCartFromBasketDropMenu() {
        return $x("//div[@class='cart-items-block ']//a[@class='btn ga-click temp-ga-click']");
    }

    SelenideElement basket() {
        return $x("//a[@class='header-cart__link']");
    }

    SelenideElement otherCategoriesPopUp() {return $x("//div[@class='popup-other-cat']");}

    SelenideElement btnCloseOtherCategoriesPopUp() {return $x("//div[@class='popup-other-cat__close']");}

    SelenideElement characteristicZustand() {
        return $x("//div[@class='product-block__description__info']//li[@class='default_ul_li_class']//span[2]");
    }

   SelenideElement compatibilityVehicleBlock() {return $x("//div[contains(text(),'Kompatibel mit folgenden PKWs')]/..");}

   SelenideElement linkOfCompatibilityVehicleAndProduct() {return $x("//div[contains(text(),'Kompatibel mit folgenden PKWs')]/..//div[@class='accordion-selected']/p");}

    ElementsCollection titleOfCharacteristic(){return  $$x("//li[@class='important']/span[1]").filter(Condition.visible);}

    ElementsCollection valueOfCharacteristic() {return $$x("//li[@class='important']/span[2]").filter(Condition.visible);}

    SelenideElement labelAddProductToWishList() {return $x("//div[@class='product-block__top-icons']/span");}

    SelenideElement iconOfWishList() {
        return $x("//span[@class='header__wishes link']");
    }

    SelenideElement characteristicBlock() {return $x("//div[@class='product-block__description__info']");}

    ElementsCollection allCharacteristics() {return $$x("//div[@class='product-block__description__info']//ul/li");}

    SelenideElement titleOfProduct() {return $x("//h2/span[1]");}

    SelenideElement artNumOfProduct() {return $x("//span[@class='subtitle-art-nummer']/span");}

    ElementsCollection attributeOfWarningIcon() {return $$x("//div[@class='product-block']//div[@class='dangerous-goods__icons']/img");}

    SelenideElement signalWordOfDangerousProduct() {return $x("//div[@class='product-block']//div[@class='dangerous-goods__title']");}

    SelenideElement dangerousBlock() {
        return $x("//div[@class='dangerous-goods js-dangerous-goods hide']");
    }

    SelenideElement textFromDangerousBlock() {
        return $x("//div[@class='dangerous-goods__text js-dangerous-goods__text']/span");
    }

    SelenideElement btnMehrFromDangerousBlock() {
        return $x("//a[@class='dangerous-goods__show-more js-dangerous-goods__show-more']");
    }


}
