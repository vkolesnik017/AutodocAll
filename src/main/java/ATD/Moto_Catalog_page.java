package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Moto_Catalog_page {
    protected SelenideElement catalogTecDoc() {
        return $x("//div[@class='car-parts-categories']");
    }

    SelenideElement carCategory() {
        return $x("//a[@class='header-i header-i--car ga-click']");
    }

    public SelenideElement lkwCategory() {
        return $x("//a[@class='header-i header-i--truck ga-click']");
    }
}
