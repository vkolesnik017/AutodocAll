package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Product_page_mob {

    SelenideElement buyButton() {
        return $(byCssSelector(".product-wrap__btns>a"));
    }

    SelenideElement popupUnbelievableDeals() {
        return $x("//img[contains(@src,'UnbelievableDeals')]");
    }
}
