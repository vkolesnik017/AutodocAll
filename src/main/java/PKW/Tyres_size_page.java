package PKW;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Tyres_size_page {

    SelenideElement blockWithProducts() {
        return $x("//div[@class='listing_items js-listing-wrap']");
    }
}
