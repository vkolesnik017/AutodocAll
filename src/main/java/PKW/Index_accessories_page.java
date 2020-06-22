package PKW;


import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;


public class Index_accessories_page {


    SelenideElement titleNamePage() {
        return $x("//h1[@class='page-title']");
    }

    SelenideElement blockTopBrands() {
        return $x("//div[@class='brand-block']");
    }

    SelenideElement firstBrandInBlockTopBrands() {
        return $x("//div[@class='brand-block']//a[@class='brand-block__wrap-item'][1]");
    }


    SelenideElement firstProductInBlockTopProducts() {
        return $x("//section[@class='top-product-block  listing-grid-block__items']//div[contains(@class,'slick-active')][1]");
    }

    SelenideElement btnDetailsInPopupBlockTopProducts() {
        return $x("//div[contains(@class,'slick-active')][1]//span[@class='link']");
    }


}
