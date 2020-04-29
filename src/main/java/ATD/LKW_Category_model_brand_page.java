package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class LKW_Category_model_brand_page {

    SelenideElement listingOfProducts() {
        return $x("//ul[@class='list_products ']");
    }
}
