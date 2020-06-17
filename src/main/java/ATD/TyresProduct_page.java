package ATD;


import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TyresProduct_page {

    SelenideElement deliveryLink() { return $(".product-inkl-info > a"); }

    SelenideElement productPrice() { return $(".product-new-price"); }

    SelenideElement faqBlock() { return $(".product-leave-feedback"); }

    SelenideElement ratingBlock() { return $(".product-block__price-block__rating"); }

    SelenideElement paymentMethodsBlock() { return $(".pay-method"); }

    SelenideElement deliveryMethodsBlock() { return $(".delivery-method"); }

    SelenideElement topsellerBlock() { return $x("(//div[@class='top-product-block'])[1]"); }

    SelenideElement addToBasketTopsellerBlockButton() { return $(".top-product-block .product-list__item--reifen .still_add_to_basket"); }

    SelenideElement productFronTopsellerBlock() { return $(".top-product-block .product-list__item--reifen .product-list__item__image > a"); }

    SelenideElement reifenlabelBlock() { return $(".product-reifen-label"); }

    SelenideElement priceInBasketPopup() { return $(".cart-items-block .row-price"); }

    SelenideElement numberOfProductsInBasketPopup() { return $(".cart-items-block__title > span"); }

    SelenideElement priceOnProductPage() { return $(".product-new-price"); }

    SelenideElement numberOfProductsOnProductPage() { return $x("//*[@class='product-count count']/input[@type='text']"); }

    SelenideElement basketBlock() { return  $(".header-cart__link"); }

    SelenideElement tyresHorizontalSelector() { return $(".atd-carselector__tyres"); }

    SelenideElement tyresPhotoBlock() { return $(".product-block__image"); }

    SelenideElement tyresProductTitle() { return $(".product-block__description__title"); }

    SelenideElement tyresDeliveryBlock() { return $(".tyres-delivery"); }

    SelenideElement productInTopsellerBlock() { return $x("//*[@data-attr-type='analogs']/li"); }

    SelenideElement reviewsBlock() { return $(".product-feedback"); }

    SelenideElement relatedProductsBlock() { return $("#related-products"); }

    SelenideElement addToBasketButton() { return $(".product-button"); }
}
