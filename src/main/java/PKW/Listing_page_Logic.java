package PKW;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static Common.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.or;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;
import static org.testng.Assert.assertEquals;


public class Listing_page_Logic extends Listing_page {

    @Step("Checks presence add to cart button for a specific item {productID}. Listing_page")
    public Listing_page_Logic checkPresenceAddToCartBtnForSpecificItem(String productID) {
        addToCartBtnForSpecificItem(productID).scrollIntoView(("{block: \"center\"}")).shouldBe(visible);
        return this;
    }

    @Step("Method checks that expected text is present in title of all products on listing. Listing_page")
    public Listing_page_Logic checkProductTitleOnListing(String expectedTextInTitle, Boolean shouldHaveTextOrNotHave, ElementsCollection titleViewMode) {
        titleViewMode.shouldHave(sizeGreaterThan(0));
        for (int i = 0; i < titleViewMode.size(); i++) {
            if (shouldHaveTextOrNotHave) {
                titleViewMode.get(i).shouldHave(text(expectedTextInTitle));
            } else {
                titleViewMode.get(i).shouldNotHave(text(expectedTextInTitle));
            }
        }
        return this;
    }

    @Step("Method checks that expected text is present in title of all products on listing with two conditions. Listing_page")
    public Listing_page_Logic checkProductTitleOnListingWithTwoExpectedTexts(String expectedTextInTitle, String secondExpText, Boolean shouldHaveTextOrNotHave, ElementsCollection titleViewMode) {
        titleViewMode.shouldHave(sizeGreaterThan(0));
        for (int i = 0; i < titleViewMode.size(); i++) {
            if (shouldHaveTextOrNotHave) {
                titleViewMode.get(i).shouldHave(or("condition", text(expectedTextInTitle), text(secondExpText)));
            } else {
                titleViewMode.get(i).shouldNotHave(or("condition", text(expectedTextInTitle), text(secondExpText)));
            }
        }
        return this;
    }

    @Step("Wait until preloader disappear. Listing_page")
    public Listing_page_Logic waitUntilPreloaderDisappear() {
        preloader().waitUntil(attribute("style", "display: none;"), 20000);
        return this;
    }

    @Step("Click first product on listing. Listing_page")
    public PKW.Product_page_Logic clickProductOnListing() {
        firstProductTitleOnListing().click();
        return page(Product_page_Logic.class);
    }

    @Step("Check listing with selecting brand. Listing_page")
    public Listing_page_Logic checkListingWithSelectingFilterByBrand(String brandId, String brandName) {
        moreBtnInBrandBloc().click();
        brandsOfBrandBlock(brandId).hover().click();
        waitUntilPreloaderDisappear();
        for (int i = 0; i < titleOfProductInListing().size(); i++) {
            String name = titleOfProductInListing().get(i).getText();
            String actualName = name.replace(name.substring(name.indexOf(" ")), "");
            assertEquals(brandName, actualName);
        }
        return this;
    }

    @Step("Checks the name of the feature state {expectedCharacteristicName} if the article number matches the expected one {expectedArticleNum}. Listing_page")
    public Listing_page_Logic checkNameOfFeatureStateIfArticleNumMatchesExpectedOne(String expectedArticleNum, String expectedCharacteristicName, String nameCharacteristic) {
        for (int i = 0; i < productArticlesInListing().size(); i++) {
            String articleNum = productArticlesInListing().get(i).getText().replaceAll("Art. Nr.: ", "");
            if (expectedArticleNum.equals(articleNum)) {
                if (moreBtnInProduct().isDisplayed()) {
                    moreBtnInProduct().click();
                }
                String characteristicName = nameProductCharacteristicINParametersBlock(nameCharacteristic).getText();
                assertEquals(expectedCharacteristicName, characteristicName);
            }
        }
        return this;
    }

    @Step("Check absence expected article num {expectedArtNum}. Listing_page")
    public Listing_page_Logic checkAbsenceArticleNum(String expectedArtNum) {
        for (int i = 0; i < productArticlesInListing().size(); i++) {
            productArticlesInListing().get(i).scrollIntoView(("{block: \"center\"}")).shouldNot(text(expectedArtNum));
        }
        return this;
    }

    @Step("Goes to the product page and checks that the name of the characteristic 'Zustand' feature is as expected {expectedCharacteristicName}. Listing_page")
    public Listing_page_Logic goToProductPageAndCheckThatNameOfCharacteristicFeatureIsExpected(String expectedArticleNum, String expectedCharacteristicName, String nameCharacteristic) {
        for (int i = 0; i < productArticlesInListing().size(); i++) {
            String articleNum = productArticlesInListing().get(i).getText().replaceAll("Art. Nr.: ", "");
            if (expectedArticleNum.equals(articleNum)) {
                searchPageTitle().scrollTo();
                clickProductOnListing();
                String characteristicName = new Product_page().nameProductCharacteristic(nameCharacteristic).getText();
                assertEquals(expectedCharacteristicName, characteristicName);
                back();
            }
        }
        return this;
    }

    @Step("Check additional listing.Listing_page")
    public Listing_page_Logic checkListingBrand(int sizeOfCheckingBrand, String brand) {
        List<Double> priceRidex = new ArrayList<>();
        List<Double> priceOfAnotherBrands = new ArrayList<>();
        for (int i = 0; i < sizeOfCheckingBrand; i++) {
            titleOfProductIOnListing().get(i).shouldHave(text(brand));
            priceRidex.add(Double.parseDouble(priceOfProduct().get(i).getText().replaceAll("[^0-9,]", "").replace(",", ".")));
        }
        for (int i = sizeOfCheckingBrand; i < activeBtnAddToBasket().size(); i++) {
            titleOfProductIOnListing().get(i).shouldNotHave(text(brand));
            priceOfAnotherBrands.add(Double.parseDouble(priceOfProduct().get(i).getText().replaceAll("[^0-9,]", "").replace(",", ".")));
        }
        assertEquals(priceRidex, getExpectedSortedPrices(priceRidex));
        assertEquals(priceOfAnotherBrands, getExpectedSortedPrices(priceOfAnotherBrands));
        return this;
    }

    @Step("Get Expected sorted prices.Listing_page")
    public List<Double> getExpectedSortedPrices(List<Double> pricesList) {
        List<Double> expectedSortedPrices = new ArrayList<>(pricesList);
        Collections.sort(expectedSortedPrices);
        return expectedSortedPrices;
    }

    @Step("Get size of active Products with brand.Listing_page")
    public int getSizeOfActiveProductsWithBrand(String brand) {
        listProductBlock().shouldBe(visible);
        int countOfActiveExactBrand = btnAddToBasketWithBrand(brand).size();
        return countOfActiveExactBrand;
    }

    @Step("Checks elements on listing (Side filters block, Brand filter block, Pagination blocks). Listing_page")
    public Listing_page_Logic checksElementsOnListing() {
        blockOfBySideFilters().shouldBe(visible);
        blockOfBySideFiltersSecond().shouldBe(visible);
        blockOfBrandFilters().shouldBe(visible);
        blockOfPagination().shouldBe(visible);
        return this;
    }

    @Step("Method saves the order of products on listing, switches to table view and verifies that order of products remains the same. Listing_page")
    public Listing_page_Logic compareProductsOrderBetweenListModeAndTableMode(int sizeProduct) {
        ElementsCollection elementsWithArticles = productArticlesInListing().first(sizeProduct);
        ArrayList<String> articlesInListMode = new ArrayList<>();
        for (SelenideElement nameProduct : elementsWithArticles) {
            String name = nameProduct.text().split(": ")[1];
            articlesInListMode.add(name);
        }
        showListingInTableModeButton().click();
        checkingContainsUrl("?list=table");
        ElementsCollection articlesOnTableMode = articleProductsInTableMode().shouldHave(sizeGreaterThanOrEqual(sizeProduct));
        for (int numberProductName = 0; numberProductName < articlesInListMode.size(); numberProductName++) {
            String articleListMode = articlesInListMode.get(numberProductName);
            String articleNumberTableMode = articlesOnTableMode.get(numberProductName).text().split(": ")[0];
            assertEquals(articleListMode, articleNumberTableMode, "Product order " + articleListMode + " does not match between list mode and table mode");
        }
        return this;
    }

    @Step("Checks elements on search listing (Side filters block, Brand filter block, Pagination blocks). Listing_page")
    public Listing_page_Logic checksElementsOnSearchListing() {
        blockOfBySideFiltersSearchListing().shouldBe(visible);
        blockOfBrandFiltersSearchListing().shouldBe(visible);
        blockOfPagination().shouldBe(visible);
        return this;
    }

    @Step("Method saves the order of products on table, switches to grid view and verifies that order of products remains the same. Listing_page")
    public Listing_page_Logic compareProductsOrderBetweenTableModeAndGridMode(int sizeProduct) {
        ElementsCollection elementsWithArticlesTable = articleProductsInTableMode().first(sizeProduct);
        ArrayList<String> articlesOnTableMode = new ArrayList<>();
        for (SelenideElement nameProduct : elementsWithArticlesTable) {
            String name = nameProduct.text().split(": ")[0];
            articlesOnTableMode.add(name);
        }
        showListingInGridModeButton().click();
        checkingContainsUrl("?list=grid");
        ElementsCollection articlesOnGridMode = articleProductsInGridMode().shouldHave(sizeGreaterThanOrEqual(sizeProduct));
        for (int numberProductName = 0; numberProductName < articlesOnTableMode.size(); numberProductName++) {
            String articleTableMode = articlesOnTableMode.get(numberProductName);
            String articleNumberGridMode = articlesOnGridMode.get(numberProductName).text().split(": ")[1];
            assertEquals(articleTableMode, articleNumberGridMode, "Product order " + articleTableMode + " does not match between table mode and grid mode");
        }
        return this;
    }
}
