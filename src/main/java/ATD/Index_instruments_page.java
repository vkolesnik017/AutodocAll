package ATD;

import com.codeborne.selenide.SelenideElement;
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

}
