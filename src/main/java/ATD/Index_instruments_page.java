package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;


class Index_instruments_page {

    SelenideElement titleMainPage() {
        return $x("//h1[@class='special-catalog__title']");
    }

    SelenideElement productInBlockTop10() {
        return $x("//div[@class='rec_products_wrap'][1]//div[@class='rec_products_block'][1]//span[@class='link ga-click']");
    }

    SelenideElement titleTop10ProductsBlock() {
        return $x("//div[@class='top-product-block best-product__income']/h2[text()]");
    }

    SelenideElement titleTop6ProductsBlock() {
        return $x("//div[@class='top-product-block']//h2[text()]");
    }

    SelenideElement subTitleMainPage() {
        return $x("//div[@class='special-catalog__subtitle']");
    }

    SelenideElement blockTopBrands() {
        return $x("//div[@class='top-brands']");
    }

    SelenideElement blockFeatures() {
        return $x("//div[@class='features__wrapp']");
    }

    SelenideElement blockDelivery() {
        return $x("//div[@class='delivery']//ul[@class='delivery__wrapp']");
    }

    SelenideElement subtitleBlockTop10() {
        return $x("//div[@class='top-product__text']");
    }

    ElementsCollection miniCardsInTopProductsBlock() {
        return $$x("//div[@class='top-product']//div[@class='product-list__item active']");
    }

    ElementsCollection miniCardsInTop10ProductsBlock() {
        return $$x("//div[@class='top-product-block best-product__income']//div[@class='rec_products_block']");
    }


}
