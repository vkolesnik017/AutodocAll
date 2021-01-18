package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class MotoroilBrand_page {

    SelenideElement nameOilInSelector() {
        return $x("//*[@name='brand']//*[@value='castrol']");
    }

    SelenideElement oilSelector() {
        return $x("//*[@class='selector-oil selector-oil--white']");
    }

    SelenideElement brandNameInListing() {
        return $x("//*[@class='js-product-item ovVisLi item_in_stock  '][1]//*[@class='name']//a");
    }
}
