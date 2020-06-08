package ATD;


import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class TyresProduct_page {

    SelenideElement deliveryLink() { return $(".product-inkl-info > a"); }

    SelenideElement productPrice() { return $(".product-new-price"); }

    SelenideElement faqBlock() { return $(".product-leave-feedback"); }

    SelenideElement ratingBlock() { return $(".product-block__price-block__rating"); }

    SelenideElement paymentMethodsBlock() { return $(".pay-method"); }

    SelenideElement deliveryMethodsBlock() { return $(".delivery-method"); }
}
