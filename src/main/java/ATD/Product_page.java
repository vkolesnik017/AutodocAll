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

    public SelenideElement buyButton() {
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

    SelenideElement greyButtonFromBlockUnitByLitre() {
        return $x("//a[contains(@class,'select-displacement__btn')]");
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
        return $x("//div[@class='product-block__image']");
    }

    public SelenideElement imageInSlider() {
        return $x("//*[contains(@class,'product-thumbnail-slider')]//div//li[6 ]");
    }

    public SelenideElement closeFullImagePreview() {
        return $x("//div[@class='fancybox-skin']/a[@title='Close']");
    }

    public SelenideElement fullProductImage() {
        return $(".fancybox-image");
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
        return $(".product-inkl-info  a");
    }

    public SelenideElement raitingBlock() {
        return $(".product-block__price-block__rating");
    }

    public SelenideElement feedbackBlock() {
        return $(".product-feedback");
    }

    public SelenideElement similarPropertiesBlock() {
        return $x("//*[@class='product-same-specification__wrap']");
    }

    public SelenideElement linkInSimilarPropertiesBlock() {
        return $x("//a[@data-ga-action='1165812']");
    }

    public SelenideElement videoBlock() {
        return $(".slider-video-block__row");
    }

    public SelenideElement pdfTutorialsBlock() {
        return $(".product-video-tutorial__col");
    }

    ElementsCollection pdfLinksForDownload() {
        return $$x("//div[@class='tutorial-text']//span[1]");
    }

    SelenideElement einzustellenderElektrodenabstandCharacteristic() {
        return $x("//span[contains(text(),'einzustellender Elektrodenabstand [mm]:')]");
    }

    public SelenideElement mengeCharacteristic() {
        return $x("//div[@class='product-block__description__info']//span[contains(text(),'Menge:')]");
    }

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
        return $x("//div[@id='child_categories']//li[2]");
    }

    public SelenideElement breadcrumbsCategoryButton() {
        return $(".parts_step_3");
    }

    public SelenideElement breadcrumbsFirstParentCategoryFromDropdown() {
        return $x("//div[@id='parent_categories']//li[2]");
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

    ElementsCollection breadCrumbLinks() {return $$x("//div[@class='steps breadcrumbs']/ul/li/span/a");}

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

    ElementsCollection allReviewsFromDE() {
        return $$x("//ul[@class='product-comments__list']//*[@data-country='de']");
    }

    ElementsCollection allReviewsExceptDE() {
        return $$x("//ul[@class='product-comments__list']//*[@data-country!='de']");
    }


    //locators for compatibility block
    public SelenideElement compatibleCarBrand() {
        return $(".accordion-button > span");
    }

    public SelenideElement firstBrandInCompabilityList() {
        return $x("//*[@class='accordion-container'][1]/div[1]//a");
    }

    public SelenideElement secondBrandInCompabilityList() {
        return $x("//*[@class='accordion-container'][2]/div[1]//a");
    }

    public SelenideElement thirdBrandInCompabilityList() {
        return $x("//*[@class='accordion-container'][3]/div[1]//a");
    }

    public SelenideElement fourthBrandInCompabilityList() {
        return $x("//*[@class='accordion-container'][4]/div[1]//a");
    }

    public SelenideElement firstModelInFirstBrandInCompatibilityList() {
        return $x("(//*[@class='accordion-content'][1]/ul/li/b[1])[1]");
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
        return $(By.xpath("//div[@class='product-inkl-info']//a[2]"));
    }

    SelenideElement heavyCargoBlock() {
        return $(By.xpath("//*[@class='oversize-block__tabs']//*[@class='oversize-block__tabs-content current tab-content-js']"));
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

    public SelenideElement vatPostscript() {
        return $x("//div[@class='product-inkl-info']//span");
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

    SelenideElement popUpKbaError() {
        return $(".popup-kba-error");
    }

    SelenideElement closePopUpKbaError() {
        return $(".popup-kba-error>a");
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

    SelenideElement oenBlockTitle() {
        return $(".product-info-block__oem__title");
    }

    ElementsCollection activeOenLinks() {return $$x("//div[@class='oem-list__col']//li/a");}

    // locator for counter
    SelenideElement counterValuePairedGood() {
        return $(By.xpath("//input[@id='form_amount']"));
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

    SelenideElement faqCheckBox() {
        return $x("//input[@id='subscribe_accept_faq']");
    }

    //locators for related products popup
    ElementsCollection categoriesInRelatedProductsPopup() {
        return $$(".popup-other-cat__content-item");
    }

    SelenideElement backButtonInRelatedPopup() {
        return $(".back");
    }

    SelenideElement relatedProductsPopup() {
        return $(".popup-other-cat");
    }

    SelenideElement relatedProductPopupGoToCartButton() {
        return $(".go-cart");
    }

    SelenideElement tecdocAlternativeLink() {
        return $x("(//a[@data-gac='Tecdoc_analog_click'])[1]/span[2]");
    }

    SelenideElement tecdocAlternativePrice() {
        return $x("(//div[@class='product-same-specification__wrap__col'])[1]//div[3]//span/span");
    }

    SelenideElement articleNumber() {
        return $(".subtitle-art-nummer > span");
    }

    ElementsCollection analogAddToBasketButtons() {
        return $$x("//a[@data-gac='Product_analog_click']");
    }

    ElementsCollection analogArtikelNumbers() {
        return $$(".product-list__item__nummer");
    }

    ElementsCollection analogProductsTitle() {
        return $$x("//*[@class='product-list__item active']");
    }

    SelenideElement carMatchBlock() {
        return $x("//*[@class='car-match-block']/p");
    }

    SelenideElement titleProduct() {
        return $x("//h2/span[@class='title']");
    }

    SelenideElement productFitsCar() {
        return $(".accordion-selected");
    }

    SelenideElement productTitle() {
        return $(".title");
    }

    SelenideElement blockApprovalECE() {
        return $x("//div[@class='lamp-usage-text']");
    }

    SelenideElement idOFBtnAddToBasket() {
        return $x("//div[@class='product-button button ']");
    }

    SelenideElement searchBar() {
        return $(byId("search"));
    }

    public SelenideElement tooltipToSearch() {
        return $(".autocomplete-suggestions>div");
    }

    public ElementsCollection tooltipsToSearch() {
        return $$(".autocomplete-suggestions>div");
    }


    SelenideElement basketDropMenu() {
        return $x("//div[@class='cart-items-block ']");
    }

    SelenideElement btnGoToCartFromBasketDropMenu() {
        return $x("//div[@class='cart-items-block ']//a[@class='btn ga-click temp-ga-click']");
    }

    SelenideElement basket() {
        return $x("//a[@class='header-cart__link']");
    }

    SelenideElement otherCategoriesPopUp() {
        return $x("//div[@class='popup-other-cat']");
    }

    SelenideElement btnCloseOtherCategoriesPopUp() {
        return $x("//div[@class='popup-other-cat__close']");
    }

    SelenideElement characteristicZustand() {
        return $x("//div[@class='product-block__description__info']//li[@class='default_ul_li_class']//span[2]");
    }

    SelenideElement compatibilityVehicleBlock() {
        return $x("//div[contains(text(),'Kompatibel mit folgenden PKWs')]/..");
    }

    SelenideElement linkOfCompatibilityVehicleAndProduct() {
        return $x("//div[contains(text(),'Kompatibel mit folgenden PKWs')]/..//div[@class='accordion-selected']/p");
    }

    ElementsCollection titleOfCharacteristic() {
        return $$x("//li[@class='important']/span[1]").filter(Condition.visible);
    }

    ElementsCollection valueOfCharacteristic() {
        return $$x("//li[@class='important']/span[2]").filter(Condition.visible);
    }

    SelenideElement labelAddProductToWishList() {
        return $x("//div[@class='product-block__top-icons']/span");
    }

    SelenideElement iconOfWishList() {
        return $x("//span[@class='header__wishes link']");
    }

    SelenideElement characteristicBlock() {
        return $x("//div[@class='product-block__description__info']");
    }

    ElementsCollection allCharacteristics() {
        return $$x("//div[@class='product-block__description__info']//ul/li");
    }

    SelenideElement titleOfProduct() {
        return $x("//h2/span[1]");
    }

    SelenideElement artNumOfProduct() {
        return $x("//span[@class='subtitle-art-nummer']/span");
    }

    ElementsCollection attributeOfWarningIcon() {return $$x("//div[@class='product-block']//div[@class='dangerous-goods__icons']/div");}


    SelenideElement signalWordOfDangerousProduct() {
        return $x("//div[@class='product-block']//div[@class='dangerous-goods__title']");
    }

    SelenideElement dangerousBlock() {
        return $x("//div[@class='dangerous-goods js-dangerous-goods hide']");
    }

    SelenideElement textFromDangerousBlock() {
        return $x("//div[@class='dangerous-goods__text js-dangerous-goods__text']/span");
    }

    SelenideElement btnMehrFromDangerousBlock() {
        return $x("//div[@class='dangerous-goods js-dangerous-goods hide']//a[@data-more='...Mehr']");
    }

    SelenideElement blockWithAlternative() {
        return $x("//*[@class='analog-block']");
    }

    SelenideElement blockWithAlternativePictograms() {
        return $x("//*[@class='dangerous-listing__icons']");
    }

    SelenideElement blockWithAlternativeMehrButton() {
        return $x("//*[@class='dangerous-listing__show-more']");
    }

    SelenideElement popUpDangerous() {
        return $x("//*[@class='popup-dangerous']");
    }

    SelenideElement popUpDangerousTitle() {
        return $x("//*[@class='popup-dangerous__title']");
    }

    SelenideElement popUpDangerousText() {
        return $x("//*[@class='popup-dangerous']//p");
    }

    ElementsCollection pictogramsInPopUp() {
        return $$x("//*[@class='popup-dangerous__icon']");
    }

    ElementsCollection pictogramsInBlock() {
        return $$x("//*[@class='slick-slide slick-current slick-active']//*[@class='dangerous-listing__icon dangerous-listing__icon-attention']");
    }

    SelenideElement closePopUpButton() {
        return $x("//*[@class='popup-dangerous__close js-popup-dangerous__close']");
    }

    SelenideElement popUpQuestionsProductPage() {
        return $x("//*[@class='popup-after-order']");
    }

    SelenideElement popUpQuestionsCloseButton() {
        return $x("//*[@class='popup-after-order__close']");
    }

    SelenideElement applicabilityBlock() {return $x("//div[@class='product-info-block__auto product-info-block--pkw single']");}

    ElementsCollection applicabilityVehicle() {return $$x("//div[@class='accordion-button']//a");}

    SelenideElement applicabilityVehicleListBlock() {return $x("//div[@class='accordion-content']");}

    ElementsCollection fullValueOfApplicabilityVehicle() {return $$x("//div[@class='accordion-content']//b");}

    ElementsCollection visibleCharacteristic() {return $$x("//div[@class='product-block__description__info']/ul/li").filter(visible);}

    SelenideElement infoPopUp() {return $("div.txt ");}

    SelenideElement bannerAutodocClub() {
        return $x("//span[@class='club-link utm_link link']");
    }

    SelenideElement kitCompositionBlock() { return $x("//div[@class='product-info-block__set']");}

    SelenideElement kitCompositionBlockUnderPdf() {return $x("//div[@class='product-info-block__set']/../preceding-sibling::div/div[@class='product-video-tutorial product-video-tutorial-only-pdf']");}

    SelenideElement kitCompositionBlockAboveFeedbBckBlock() {return $x("//div[@class='product-info-block__set']/../following-sibling::div[@class='product-feedback']");}

    ElementsCollection productsFromKitCompositionBlock() { return $$x("//div[@class='product-info-block__set__row']/div[1]/a"); }
    SelenideElement pricePerMeter() {return $x("//*[contains(text(),'Preis pro Meter')]");}

    SelenideElement locationOfCandlesAnalogBlock() {return $x("//div[@id='footer']/preceding-sibling::main[@class='product-page']/div/div[last()-1]");}

    SelenideElement headlineOfCandlesAnalogBlock() {return $(byId("section8"));}

    ElementsCollection brandsInCandlesAnalogBlock() {return $$x("//div[@class='product-info-block__nummer__wrap']//p");}

    ElementsCollection artListInCandlesAnalogBlock() {return $$x("//div[@class='product-info-block__nummer__wrap']//span");}

    ElementsCollection artNumOfProductsFromKitCompositionBlock() { return $$x("//div[@class='product-info-block__set__row']/div[3]"); }

    SelenideElement valueOfArtNumProduct() {return $x("//span[@class='subtitle-art-nummer']/span");}

    SelenideElement tabBlock() {return $x("//div[@class='product-tabs']");}

    ElementsCollection linksOfTabBlock() {return $$x("//div[@class='product-tabs']/a");}

    SelenideElement countOfReviewsInHeadOfBlock() {return $x("//span[@class='product-revs__count']");}

    ElementsCollection reviews() {return $$x("//ul[@class='product-comments__list']/li");}

    SelenideElement ridexInfoBlock() {return $x("//div[@class='product-banner-ridex js-default-open-popup']");}

    SelenideElement ridexLogo() {return $x("//div[@class='product-banner-ridex__img']/img");}

    SelenideElement aboutRidexText() {return $x("//div[@class='product-banner-ridex js-default-open-popup']//div[@class='banner-text']");}

    SelenideElement btnShowMoreInRidexBlock() {return $x("//div[@class='product-banner-ridex js-default-open-popup']//div[@class='banner-btn']");}

    SelenideElement background() {return $x("//div[@class='overlay black hidden']");}

    SelenideElement faqBlockWithoutAnswer() {return $x("//*[@class='product-leave-feedback']");}

    SelenideElement faqBlockWithAnswer() {return $x("//*[@class='product-leave-feedback__wrap__comments']");}

    SelenideElement textAboutQuantityOfAnswersInFAQ() {return $x("//*[@class='product-leave-feedback__wrap__first-title'][1]");}

    SelenideElement blockVersandInFAQ() {return $x("//*[@class='product-leave-feedback']/following-sibling::div");}

    SelenideElement countInputOnProduct() {return $x("//*[@class='count product-count']//input");}

    SelenideElement textAboutCountOnProduct() {return $x("//*[@class='product-set-info']");}

    SelenideElement dangerousInfoBlock() {return $x("//div[contains(@class,'dangerous-goods js-dangerous-goods')]");}

    SelenideElement dangerousSignalWord() {return $x("//div[@class='dangerous-goods__title']");}

    SelenideElement btnMoreOfDangerousInfoBlock() {return $x("//a[@class='dangerous-goods__show-more js-dangerous-goods__show-more']");}

    SelenideElement infoTextOfDangerousInfoBlock() {return $x("//div[@class='dangerous-goods__text js-dangerous-goods__text']/span");}

    SelenideElement catalogIconInBreadcrumbLink() {return $x("//div[@class='steps breadcrumbs']/ul/li//img");}

    SelenideElement tooltipOfMarkeField() {return $x("//div[@class='tooltiptext-close js-tooltiptext-close']");}

    SelenideElement phraseAboutCompatibilityProductAndVehicle() {
        return $x("//*[@class='accordion-selected']");
    }

    SelenideElement gluingBlock() {return $x("//div[@class='add_info price_box']");}

    SelenideElement currentVolume() {return $x("//div[@class='select-displacement select-displacement--selected']//label");}

    ElementsCollection allowableVolumes() {return $$x("//div[@class='add_info price_box']//label/span");}

    SelenideElement recommendedChangeLiterIcon() {return $x("//div[@class='recommended-oil']");}

    ElementsCollection gluingProducts() {return $$x("//div[@class='select-displacement ']");}

    ElementsCollection btnAddGluingProductToBasket() {return $$x("//div[@class='select-displacement ']/a");}

    SelenideElement btnCloseBasketPopUp() {return $x("//div[@class='popup-other-cat__close']");}

    SelenideElement valuesOfProductUnderTitle() {return $x("//span[@class='product-block__description__title-small']");}

    SelenideElement headlineOfFeedBackBlock() {return $x("//div[@class='product-feedback']/div[1]");}

    ElementsCollection interestingArticleLinks() {return $$x("//div[@class='product-anchor-links']//a").filter(visible);}

    SelenideElement upNavigationArrowOfVideoBlock() {return $x("//div[@class='slider-video-block']//button[1]");}

    SelenideElement downNavigationArrowOfVideoBlock() {return $x("//div[@class='slider-video-block']//button[2]");}

    ElementsCollection allVideoFiles() {return $$x("//div[@class='slick-list draggable']/div/div");}

    ElementsCollection idOfVisibleVideoFiles() {return $$x("//*[self::div[@class='slider-item slick-slide slick-current slick-active'] or self::div[@class='slider-item slick-slide slick-active']]/div/div");}

    SelenideElement currentIdVideo() {return $x("//div[@class='slider-video-block']//div[@class='item slick-slide slick-current slick-active']/div/div");}

    SelenideElement btnPlayOfVideoFile()  {return $x("//div[@class='slick-list draggable']//div[@class='play']");}

    SelenideElement questionFaqBlock() {return $x("//p[@class='question']/..");}

    SelenideElement locationOfPaymentBlockToFaq() {return $x("//div[@class='product-versand']/preceding-sibling::div");}

    SelenideElement feedBackBlock() {
        return $x("//div[@class='product-feedback']");
    }

    SelenideElement characteristicOfProduct(String nameCharacteristic) {return $x("//span[contains(text(),'" + nameCharacteristic + "')]/../span[2]");}

    SelenideElement productInfoPrice() { return $x("//p[@class='product-info-price']");}

  }
