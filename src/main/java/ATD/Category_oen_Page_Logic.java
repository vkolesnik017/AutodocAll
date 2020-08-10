package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.disappear;

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

    @Step("select brand in brands block. Category_oen_Page")
    public Category_oen_Page_Logic selectBrandInBlock(String idOfBrand) {
        brandsFilterBlock().shouldBe(visible);
        while (!brandsLinkInSideBar(idOfBrand).isDisplayed()) {
            forwardLinkAtBrandsFilter().click();
        }
        brandsLinkInSideBar(idOfBrand).shouldBe(visible).click();
        appearsOfLoader();
        return this;
    }

    @Step("appears of Loader .Category_oen_Page")
    public Category_oen_Page_Logic appearsOfLoader() {
        loaderInTecDocListing().should(appear);
        loaderInTecDocListing().should(disappear);
        return this;
    }

    @Step("check listing with selected brands .Category_oen_Page")
    public Category_oen_Page_Logic checkListingWithSelectedBrands(String brands) {
        String brandFromTitleOfProduct;
        List<String> brandsList = new ArrayList<>();
        String[] brand = brands.split("\\,");
        Collections.addAll(brandsList, brand);
        for (int i = 0; i < imageOfBrandInProductBlock().size(); i++) {
            String endOfAttribute = imageOfBrandInProductBlock().get(i).getAttribute("src").replace(imageOfBrandInProductBlock().get(i).getAttribute("src").substring(imageOfBrandInProductBlock().get(i).getAttribute("src").lastIndexOf(".")), "");
            String trimOfEndAttribute = endOfAttribute.replace(endOfAttribute.substring(endOfAttribute.lastIndexOf("/")), "");
            brandFromTitleOfProduct = endOfAttribute.replace(trimOfEndAttribute, "").replaceAll("[^0-9]", "");
            Assert.assertTrue(brandsList.contains(brandFromTitleOfProduct));
        }
        return this;
    }

    @Step("check absence of Quantity characteristic in Product description block .Category_oen_Page")
    public Category_oen_Page_Logic checkAbsenceOfQuantityCharacteristicInProductDescriptionBlock() {
        for (int i = 0; i < descriptionBlockOfProduct().size(); i++) {
            for (int j = 0; j < characteristicListOfProduct(i + 1).size(); j++) {
                characteristicListOfProduct(i + 1).get(j).shouldNotHave(exactText("Menge"));
            }
        }
        return this;
    }
}
