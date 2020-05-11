package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Index_chemicals_page {


    SelenideElement GroupLogicalUnion_MainProductCatalog() {
        return $x("//div[@class='special-catalog__row'][1]//div[@class='special-catalog__group'][1]");
    }

    SelenideElement LogicalUnion_MainProductCatalog() {
        return $x("//div[@class='special-catalog__categories' and @style='display: block;']//li[1]");
    }

    SelenideElement DivTopBrands() {
        return $x("//div[@class='top-brands']/ul/img");
    }
}
