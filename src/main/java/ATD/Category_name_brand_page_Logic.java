package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;

public class Category_name_brand_page_Logic extends Category_name_brand_page {

    @Step("check absence of Quantity characteristic in Pop-Up of TOP products. Category_name_brand_pag")
    public Category_name_brand_page_Logic checkAbsenceOfQuantityCharacteristicInTopProducts() {
        for (int i = 0; i < visibleTitleOfTopProducts().size(); i++) {
            titleOfTopProductsBlock().hover();
            visibleTitleOfTopProducts().get(i).shouldBe(visible).hover();
            popUpOfTopProducts().get(i).shouldBe(visible);
            checkAbsenceOfQuantityCharacteristicInPopUpTopProducts(i);
            if (i == 3) {
                activeBtnForwardOfTopProductsBlock().click();
            }
        }
        return this;
    }

    @Step("check absence of Quantity characteristic in PopUp TOP product. Category_name_brand_pag")
    public Category_name_brand_page_Logic checkAbsenceOfQuantityCharacteristicInPopUpTopProducts(int position) {
        List<String> listOfCharacteristic = new ArrayList<>();
        for (int i = 0; i < visibleCharacteristicInPopUpOfTopProducts(position + 1).size(); i++) {
            listOfCharacteristic.add(visibleCharacteristicInPopUpOfTopProducts(position + 1).get(i).getText());
        }
        Assert.assertTrue(!listOfCharacteristic.contains("Menge"));
        listOfCharacteristic.clear();
        return this;
    }
}
