package PKW;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Tyres_item_page {
    SelenideElement returnBlockProductPage() {
        return $x("//*[@class='pkw-plus__elem'][2]");
    }

    SelenideElement returnBlockTextProductPage() {
        return $x("//*[@class='pkw-plus__elem'][2]//*[@class='pkw-plus__text']");
    }

    SelenideElement nameOfTyres() {
        return $x("//*[@class='pkw-product__name']");
    }

    SelenideElement ratingStars() {
        return $x("//*[@class='pkw-product__rowspan js-product-click-stars']");
    }

    SelenideElement reviewsBlockStars() {
        return $x("//*[@class='pkw-more__span_60']");
    }

    SelenideElement reviewsBlockAnchor() {
        return $x("//*[@class='pkw-more__span_60']//a");
    }

    SelenideElement photoOfTyres() {
        return $x("//*[@class='pkw-product__no-slider']//img");
    }

    SelenideElement characteristicsBlock() {
        return $x("//*[@class='pkw-table']");
    }

    SelenideElement priceOfTyres() {
        return $x("//*[@class='pkw-product__price']");
    }

    SelenideElement euReifenlabelBlock() {
        return $x("//*[@class='pkw-tabs__content-inset pkw-tabs__1']");
    }

    SelenideElement priceInBasketPopup() {
        return $x("//*[@class='cart-items-block-price__row cart-items-block-price--total']//*[@class='row-right']//p");
    }

    SelenideElement numberOfProductsOnProductPage() { return $x("//*[@class='pkw-product__input-wrapper qty']//*[@class='pkw-product__input']"); }

    SelenideElement numberOfProductsInBasketPopup() { return $x("//*[@class='cart-items-block__title']/span"); }

    SelenideElement basketBlock() { return $x("//*[@class='cart-items-block ']"); }

    SelenideElement addToBasketButton() {
        return $(byCssSelector(".pkw-product__buy-btn"));
    }

    SelenideElement reviewsMessageBlock() { return $x("//*[@class='reviews reviews-comments-block']"); }


    SelenideElement analogBlock() { return $x("//*[@class='pkw-more pkw-more__related']"); }
}

