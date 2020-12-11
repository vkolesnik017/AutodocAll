package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class Tyre_item_page {

    SelenideElement horizontalSelector() {
        return $x("//div[@class='atd-carselector__content atd-carselector__tyres']");
    }

    SelenideElement grayButtonOfProduct() {
        return $x("//div[@class='product-button button not_active out-of-stock']/a");
    }

    SelenideElement popUpAboutLackOfProduct() {
        return $x("//div[@class='popup-available']");
    }

    SelenideElement mpnNumOfProduct() {
        return $x("//div[@class='product-block__description__subtitle']/span[2]");
    }

    SelenideElement artNumOfProductInBasketPopUp() {
        return $x("//div[@class='row-text']/span");
    }

    SelenideElement btnAddProductToBasket() {
        return $x("//div[@class='product-button button ']/a");
    }

    SelenideElement basketDropMenu() {
        return $x("//div[@class='cart-items-block ']");
    }

    SelenideElement basket() {
        return $x("//a[@class='header-cart__link']");
    }

    SelenideElement breadCrumbsBlock() {
        return $x("//div[@class='steps breadcrumbs']");
    }

    ElementsCollection linksOfBreadCrumbs() {
        return $$x("//div[@class='steps breadcrumbs']//ul/li//a");
    }

    SelenideElement eanNumberOfProduct() {
        return $x("//div[@class='product-block__description__subtitle']/span[1]");
    }

    SelenideElement grayButton() {
        return $(byId("product_page_product"));
    }

    SelenideElement popUpNotifyAboutAvailability() {
        return $x("//div[@class='popup-available']");
    }

    SelenideElement labelOfPopUpNotifyAboutAvailability() {
        return $x("//label[@class='popup-available__label']");
    }

    SelenideElement emailFieldOfFeedBackPopUp() {
        return $(byId("form_AvailabilityReminder[email]"));
    }

    SelenideElement btnSendOfFeedBackPopUp() {
        return $x("//input[@class='popup-available__button']");
    }

    SelenideElement errorPopUp() {
        return $x("//div[@class='txt ']");
    }

    SelenideElement btnCloseErrorPopUp() {
        return $x("//div[@class='popup_inner']/div/a");
    }

    ElementsCollection visibleTopProducts() {
        return $$x("//div[@class='product-list__row slick-slide slick-current slick-active']/ul/li");
    }

    SelenideElement deliveryLink() {
        return $(".product-inkl-info > a");
    }

    SelenideElement productPrice() {
        return $(".product-new-price");
    }

    SelenideElement faqBlock() {
        return $(".product-leave-feedback");
    }

    SelenideElement ratingBlock() {
        return $(".product-block__price-block__rating");
    }

    SelenideElement paymentMethodsBlock() {
        return $(".pay-method");
    }

    SelenideElement deliveryMethodsBlock() {
        return $(".delivery-method");
    }

    SelenideElement topsellerBlock() {
        return $x("(//div[@class='top-product-block'])[1]");
    }

    SelenideElement addToBasketTopsellerBlockButton() {
        return $(".top-product-block .product-list__item--reifen .still_add_to_basket");
    }

    SelenideElement productFronTopsellerBlock() {
        return $(".top-product-block .product-list__item--reifen .product-list__item__image > a");
    }

    SelenideElement reifenlabelBlock() {
        return $(".product-reifen-label");
    }

    SelenideElement priceInBasketPopup() {
        return $(".cart-items-block .row-price");
    }

    SelenideElement numberOfProductsInBasketPopup() {
        return $(".cart-items-block__title > span");
    }

    SelenideElement priceOnProductPage() {
        return $(".product-new-price");
    }

    SelenideElement numberOfProductsOnProductPage() {
        return $x("//*[@class='product-count count']/input[@type='text']");
    }

    SelenideElement basketBlock() {
        return $(".header-cart__link");
    }

    SelenideElement tyresHorizontalSelector() {
        return $(".atd-carselector__tyres");
    }

    SelenideElement tyresPhotoBlock() {
        return $(".product-block__image");
    }

    SelenideElement tyresProductTitle() {
        return $(".product-block__description__title");
    }

    SelenideElement tyresDeliveryBlock() {
        return $(".tyres-delivery");
    }

    SelenideElement productInTopsellerBlock() {
        return $x("//*[@data-attr-type='analogs']/li");
    }

    SelenideElement reviewsBlock() {
        return $(".product-feedback");
    }

    SelenideElement relatedProductsBlock() {
        return $("#related-products");
    }

    SelenideElement addToBasketButton() {
        return $(".product-button");
    }

    SelenideElement summerSeasonCheckbox() {
        return $x("//input[@value='sommer']/..");
    }

    SelenideElement daysReturnPeriodProduct() {
        return $x("//li[@class='features-tage-icon']//p");
    }

    SelenideElement iconDaysReturnPeriodProduct() {
        return $x("//li[@class='features-tage-icon']//b");
    }

    SelenideElement returnPeriodProductBlock() {
        return $x("//li[@class='features-tage-icon']");
    }

    SelenideElement addButtonToBasket() {
        return $x("//*[@class='product-button button ']//a[@class='btn still_add_to_basket']");
    }

    SelenideElement seasonLincInCharacteristicsBlock() {
        return $x("//div[contains(@class,'description__info')]//a[@data-ga-action='SEASON']");
    }
}
