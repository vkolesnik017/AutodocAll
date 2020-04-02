package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class LKW_Categories_page {
    SelenideElement tecDocCatalog() {
        return $x("//div[@class='car-parts-categories']");
    }

    SelenideElement headlineInHeader() {
        return $x("//div[@class='car-parts-categories__title']/h2");
    }

    SelenideElement titleOfCatalog() {
        return $x("//div[@class='car-parts-categories__title']");
    }
}
