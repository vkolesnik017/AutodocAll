package PKW;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static PKW.CommonMethods.getTextFromUnVisibleElement;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;

public class Parts_group_page_Logic extends Parts_group_page {

    @Step("Get name title page. Parts_group_page")
    public String getNameTitlePage() {
        return titlePage().getText();
    }

    @Step("presence Refurbished Characteristic In TOP product. Parts_group_page")
    public Parts_group_page_Logic presenceRefurbishedCharacteristicInTopProduct(String expectedCharacteristic) {
        topProductsBlock().shouldBe(visible).scrollIntoView("{block: \"center\"}");
        List<String> listOfCharacteristic = new ArrayList<>();
        for (int i = 0; i < allCharacteristicsOfTopProducts().size(); i++) {
            listOfCharacteristic.add(getTextFromUnVisibleElement(allCharacteristicsOfTopProducts().get(i)));
        }
        Assert.assertTrue(listOfCharacteristic.contains(expectedCharacteristic));

        return this;
    }

    @Step("presence Refurbished Characteristic In TOP product if art number contains expected symbol . Parts_group_page")
    public Parts_group_page_Logic presenceRefurbishedCharacteristicInTopProductWithArticle(String expectedCharacteristic, String symbol) {
        topProductsBlock().shouldBe(visible).scrollIntoView("{block: \"center\"}");

        checkCharacteristicOfTopProduct(expectedCharacteristic, symbol);

        for (int i = 0; i < 2; i++) {
            String artNumOfProduct = visibleArtNumOfTopProducts().get(0).getText();
            forwardOfTopBlock().click();
            visibleArtNumOfTopProducts().get(0).shouldNotHave(exactText(artNumOfProduct));
            checkCharacteristicOfTopProduct(expectedCharacteristic, symbol);
        }
        return this;
    }

    @Step("checking characteristic of TOP product. Parts_group_page")
    public Parts_group_page_Logic checkCharacteristicOfTopProduct(String expectedCharacteristic, String symbol) {
        for (int i = 0; i < visibleArtNumOfTopProducts().size(); i++) {
            List<String> characteristics = new ArrayList<>();
            String titleOfBrandImage = visibleImageOfTopBrands().get(i).getAttribute("alt");
            String artNumOfProduct = visibleArtNumOfTopProducts().get(i).getText().replace("Art. Nr. : ", "");
            if (titleOfBrandImage.contains("Henkel Parts") && artNumOfProduct.contains(symbol)) {
                for (int j = 0; j < visibleCharacteristicsOfTopProducts(i + 1).size(); j++) {
                    characteristics.add(getTextFromUnVisibleElement(visibleCharacteristicsOfTopProducts(i + 1).get(j)));
                }
                Assert.assertTrue(characteristics.contains(expectedCharacteristic));
                characteristics.clear();
            }
        }
        return this;
    }

}
