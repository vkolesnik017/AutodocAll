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
}
