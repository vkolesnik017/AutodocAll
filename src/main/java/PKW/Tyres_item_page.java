package PKW;

import com.codeborne.selenide.SelenideElement;

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

}

