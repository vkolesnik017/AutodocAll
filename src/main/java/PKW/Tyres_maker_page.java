package PKW;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Tyres_maker_page {
    SelenideElement linkingBlockByBrands() {
        return $x("//div[@class='top-tyre-brands']");
    }

    SelenideElement brandByName(String titleOfBrand) {
        return $x("//div[@class='top-tyre-brands']//a/img[contains(@alt,'" + titleOfBrand + "')]");
    }

    SelenideElement btnAllBrands() {return $x("//div[@class='type_list_all_brands']/a");}
}
