package PKW;

import io.qameta.allure.Step;

import static PKW.CommonMethods.getTextFromUnVisibleElement;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;

public class Supplier_brand_parts_page_Logic extends Supplier_brand_parts_page {

    @Step("presence Refurbished Characteristic In TOP product if art number contains expected symbol . Supplier_brand_parts_page")
    public Supplier_brand_parts_page_Logic presenceRefurbishedCharacteristicInTopProductWithArticle(String expectedCharacteristic, String symbol) {
        topProductsBlock().shouldBe(visible).scrollIntoView("{block: \"center\"}");

        new Parts_group_page_Logic().checkCharacteristicOfTopProduct(expectedCharacteristic, symbol);

        for (int i = 0; i < 4; i++) {
            String artNumOfProduct = visibleArtNumOfTopProducts().get(0).getText();
            forwardOfTopBlock().click();
            visibleArtNumOfTopProducts().get(0).shouldNotHave(exactText(artNumOfProduct));
            new Parts_group_page_Logic().checkCharacteristicOfTopProduct(expectedCharacteristic, symbol);
        }
        return this;
    }
}
