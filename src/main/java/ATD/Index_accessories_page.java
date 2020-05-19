package ATD;


import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;



class Index_accessories_page {

    SelenideElement blockFeatures() {
        return $x("//div[@class='features__wrapp']");
    }

    SelenideElement blockDelivery() {
        return $x("//div[@class='delivery']");
    }

    SelenideElement blockTopBrands() {
        return $x("//div[@class='top-brands']");
    }

    SelenideElement blockTopProducts() {
        return  $x("//div[@class='top-product-block']");
    }




}
