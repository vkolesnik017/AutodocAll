package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Index_chemicals_page {


    SelenideElement catalogFirstGroup() {
        return $x("//div[@class='special-catalog__row'][1]//div[@class='special-catalog__group'][1]");
    }

    SelenideElement catalogCategories() {
        return $x("//div[@class='special-catalog__categories' and @style='display: block;']");
    }

    SelenideElement blockTopBrands() {
        return $x("//div[@class='top-brands']/ul/img");
    }

    SelenideElement blockFeatures() {
        return $x("//div[@class='features__wrapp']");
    }



}
