package PKW;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class Supplier_brand_parts_page {

    SelenideElement topProductsBlock() {
        return $(byId("topSeller"));
    }

    ElementsCollection visibleArtNumOfTopProducts() {
        return $$x("//span[@class='pkw-related__item-art']").filter(visible);
    }

    SelenideElement forwardOfTopBlock() {
        return $x("//div[@id='topSeller']//a[@class='bx-next']");
    }

}
