package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class Category_model_brand_page {

    SelenideElement brandsBlock() {
        return $(".kategorie_top_brands");
    }

    ElementsCollection visibleBrands() {
        return $$(".kategorie_top_brands a").filter(visible);
    }

    SelenideElement btnMoreOfBrandsBlock() {
        return $x("//div[@class='kategorie_top_brands']//div[@class='m_text show']/span");
    }
}
