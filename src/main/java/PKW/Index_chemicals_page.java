package PKW;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;


public class Index_chemicals_page {

    SelenideElement blockSeoTextWithDescription() {
        return $x("//div[@class='text_description']");
    }

    SelenideElement separateCategoryInMainCatalogCategories() {
        return $x("//div[@class='category-block']//a[@class='category-block__item ga-click'][1]");
    }

}
