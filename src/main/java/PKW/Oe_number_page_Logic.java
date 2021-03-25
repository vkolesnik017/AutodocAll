package PKW;

import io.qameta.allure.Step;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class Oe_number_page_Logic extends Oe_number_page {

    @Step("Checking text in Title Page.Oe_number_page")
    public String checksTextInTitlePage() {
        return titlePage().getText();
    }

    @Step("Check additional listing.Oe_number_page")
    public Oe_number_page_Logic checkListingBrand(int sizeOfCheckingBrand, String brand) {
        List<Double> priceRidex = new ArrayList<>();
        List<Double> priceOfAnotherBrands = new ArrayList<>();
        for (int i = 0; i < sizeOfCheckingBrand; i++) {
            titleOfProductInOenListing().get(i).shouldHave(text(brand));
            priceRidex.add(Double.parseDouble(priceOfProduct().get(i).getText().replaceAll("[^0-9,]", "").replace(",", ".")));
        }
        for (int i = sizeOfCheckingBrand; i < activeBtnAddToBasket().size(); i++) {
            titleOfProductInOenListing().get(i).shouldNotHave(text(brand));
            priceOfAnotherBrands.add(Double.parseDouble(priceOfProduct().get(i).getText().replaceAll("[^0-9,]", "").replace(",", ".")));
        }
        Assert.assertEquals(priceRidex, getExpectedSortedPrices(priceRidex));
        Assert.assertEquals(priceOfAnotherBrands, getExpectedSortedPrices(priceOfAnotherBrands));
        return this;
    }

    @Step("Get Expected sorted prices.Oe_number_page")
    public List<Double> getExpectedSortedPrices(List<Double> pricesList) {
        List<Double> expectedSortedPrices = new ArrayList<>(pricesList);
        Collections.sort(expectedSortedPrices);
        return expectedSortedPrices;
    }

    @Step("Get size of active Products with brand.Oe_number_page")
    public int getSIzeOfActiveProductsWithBrand(String brand) {
        listProductBlock().shouldBe(visible);
        int countOfActiveExactBrand = btnAddToBasketWithBrand(brand).size();
        return countOfActiveExactBrand;
    }
}