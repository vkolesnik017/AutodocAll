package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class Category_oen_Page_Logic extends Category_oen_Page {

    @Step("Check additional listing. Category_oen_Page")
    public Category_oen_Page_Logic checkOenListing(int sizeOfCheckingBrand, String brand) {
        List<Double> priceRidex = new ArrayList<>();
        List<Double> priceOfAnotherProducts = new ArrayList<>();
        for (int i = 0; i < sizeOfCheckingBrand; i++) {
            titleOfProductInList().get(i).shouldHave(text(brand));
            priceRidex.add(Double.parseDouble(priceOfProduct().get(i).getText().replaceAll("[^0-9,]", "").replace(",", ".")));
        }
        for (int i = sizeOfCheckingBrand; i < activeBtnAddToBasket().size(); i++) {
            titleOfProductInList().get(i).shouldNotHave(text(brand));
            priceOfAnotherProducts.add(Double.parseDouble(priceOfProduct().get(i).getText().replaceAll("[^0-9,]", "").replace(",", ".")));
        }
        Assert.assertEquals(priceRidex, getExpectedSortedPrices(priceRidex));
        Assert.assertEquals(priceOfAnotherProducts, getExpectedSortedPrices(priceOfAnotherProducts));
        return this;
    }

    @Step("get size of active Products with brand. Category_oen_Page")
    public int getSIzeOfActiveProductsWithBrand(String brand) {
        listProductBlock().shouldBe(visible);
        int countOfActiveExactBrand = btnAddToBasketWithBrand(brand).size();
        return countOfActiveExactBrand;
    }

    @Step("get Expected sorted prices. Category_oen_Page")
    public List<Double> getExpectedSortedPrices(List<Double> pricesList) {
        List<Double> expectedSortedPrices = new ArrayList<>(pricesList);
        Collections.sort(expectedSortedPrices);
        return expectedSortedPrices;
    }
}
