package ATD;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static ATD.CommonMethods.getTextFromUnVisibleElement;
import static Common.CommonMethods.checkingContainsUrl;
import static Common.CommonMethods.getNameRouteFromJSVarInHTML;
import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;

public class Listing_page_Logic extends Listing_page {
    //Locators for all list mode listings

    @Step("Gets all the characteristics of the desired product from listing {productArticle}. Listing_page")
    // example String for productArticle = Artikelnummer: V99-75-0011 , don't fits for search listing when used search by article product
    public ElementsCollection getCharacteristicsDesiredProduct(String productArticle) {
        while (characteristicsOfProduct(productArticle).size() < 10) {
            nextPageButton().click();
        }
        return characteristicsOfProduct(productArticle).shouldHave(sizeGreaterThan(10));
    }

    @Step("Gets any characteristic of the desired product from listing. Listing_page")
    public String getCharacteristicDesiredProduct(String productArticle, String nameCharacteristic ) {
        return characteristicOfProduct(productArticle, nameCharacteristic).getText();
    }

    @Step("Gets title of the desired product from listing. Listing_page")
    public String getTitleDesiredProduct(String productArticle) {
        return titleDesiredProduct(productArticle).getText();
    }


    @Step("Method gets price of all products on listing and parse it into float. Listing_page")
    private List<Float> getAllPricesOnListingPage(ElementsCollection listingViewModeLocator) {
        List<Float> listOfFloatPrices = new ArrayList<>();
        for (SelenideElement price : listingViewModeLocator) {
            String priceProduct = price.getText();
            priceProduct = priceProduct.substring(0, priceProduct.indexOf("€")).trim().replace(",", ".");
            float parsingToFloat = Float.parseFloat(priceProduct);
            listOfFloatPrices.add(parsingToFloat);
        }
        return listOfFloatPrices;
    }

    @Step("Method checks if prices on listing are in range {maxPriceToCheck} - {maxPriceToCheck} of price filter. Listing_page")
    public void checkPricesRange(Float minPriceToCheck, Float maxPriceToCheck, ElementsCollection listingViewModeLocator) {
        List<Float> price = getAllPricesOnListingPage(listingViewModeLocator);
        for (int i = 0; i < price.size() - 1; i++) {
            if (price.get(i) >= minPriceToCheck && price.get(i) <= maxPriceToCheck) {
            } else {
                Assert.fail("Prices on listing are NOT in range of price filter. Range: " + minPriceToCheck + " - " + maxPriceToCheck);
            }
        }
        System.out.println("Prices on listing are in range of price filter. Range: " + minPriceToCheck + " - " + maxPriceToCheck);
    }

    @Step("Method checks that filter by side applied to products on listing. Listing_page")
    public void checkFilterBySide(String attributeText) {
        for (int i = 0; i < sideFilterAttribute().size() - 1; i++) {
            sideFilterAttribute().get(i).shouldHave(text(attributeText));
        }
    }

    @Step("Method checks that expected text is present in title of all products on listing after changing the view. Listing_page")
    public Listing_page_Logic checkProductTitleOnListingAfterChangingView(String expectedTextInTitle, ElementsCollection titleViewMode) {
        String name;
        for (int i = 0; i < titleViewMode.size(); i++) {
            name = titleViewMode.get(i).getText().replaceAll("\\.", "");
            String actualName = name.replace(name.substring(name.indexOf(" ")), "");
            Assert.assertTrue(actualName.contains((expectedTextInTitle)));
        }
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

    @Step("Method checks that the listing displays products of the selected brands. Listing_page")
    public Listing_page_Logic checkThatListingDisplaysProductOfOneSelectedBrand(String expectedTextInTitle, Boolean shouldHaveTextOrNotHave, ElementsCollection titleViewMode) {
        if (!resetBlock().isDisplayed()) {
            checkProductTitleOnListing(expectedTextInTitle, shouldHaveTextOrNotHave, titleViewMode);
        } else {
            for (int i = 0; i < imageBrandOfProduct().size(); i++) {
                if (!imageBrandOfProduct().get(i).getAttribute("src").contains(idOfBrandFromHeader().get(0).getAttribute("data-crit-id"))) {
                    Assert.assertTrue(imageBrandOfProduct().get(i).getAttribute("src").contains(idOfBrandFromHeader().get(1).getAttribute("data-crit-id")));
                }
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

    @Step("Method checks that the listing displays products of the selected brands. Listing_page")
    public Listing_page_Logic checkThatListingDisplaysProductOfTwoSelectedBrand(String expectedTextInTitle, String secondExpText, Boolean shouldHaveTextOrNotHave, ElementsCollection titleViewMode) {
        if (!resetBlock().isDisplayed()) {
            checkProductTitleOnListingWithTwoExpectedTexts(expectedTextInTitle, secondExpText, shouldHaveTextOrNotHave, titleViewMode);
        } else {
            for (int i = 0; i < imageBrandOfProduct().size(); i++) {
                if (!imageBrandOfProduct().get(i).getAttribute("src").contains(idOfBrandFromHeader().get(0).getAttribute("data-crit-id"))) {
                    Assert.assertTrue(imageBrandOfProduct().get(i).getAttribute("src").contains(idOfBrandFromHeader().get(1).getAttribute("data-crit-id")));
                }
            }
        }
        return this;
    }


    @Step("Method checks that expected text is present in title of all products on listing with six conditions. Listing_page")
    public void checkProductTitleOnListingWithSixExpectedTexts(String expectedTextInTitle, String secondExpText, String thirdExpText, String fourthExpText, String fifthExpText,
                                                               String sixthExpText, Boolean shouldHaveTextOrNotHave, ElementsCollection titleViewMode) {
        titleViewMode.shouldHave(sizeGreaterThan(0));
        for (int i = 0; i < titleViewMode.size(); i++) {
            if (shouldHaveTextOrNotHave) {
                titleViewMode.get(i).shouldHave(or("condition", text(expectedTextInTitle), text(secondExpText), text(thirdExpText), text(fourthExpText), text(fifthExpText), text(sixthExpText)));
            } else {
                titleViewMode.get(i).shouldNotHave(or("condition", text(expectedTextInTitle), text(secondExpText), text(thirdExpText)));
            }
        }
    }

    @Step("Method checks unique brands on listing. Listing_page")
    public Listing_page_Logic checkUniqueBrandsOnListing(int numberOfUniqueBrands, ElementsCollection titleViewMode) {
        titleViewMode.shouldHave(sizeGreaterThan(0));
        Set<String> uniqueBrandSet = new LinkedHashSet<>();
        for (SelenideElement aTitleViewMode : titleViewMode) {
            String brandName = aTitleViewMode.text().split(" ")[0];
            uniqueBrandSet.add(brandName);
        }
        Assert.assertTrue(uniqueBrandSet.size() >= numberOfUniqueBrands);
        return this;
    }

    @Step("Method checks unique generic on listing. Listing_page")
    public void checkUniqueGenericsOnListing(int numberOfUniqueGenerics, ElementsCollection titleViewMode) {
        titleViewMode.shouldHave(sizeGreaterThan(0));
        Set<String> uniqueGenericSet = new LinkedHashSet<>();
        for (SelenideElement aTitleViewMode : titleViewMode) {
            String brandName = aTitleViewMode.text().split(" ")[1];
            uniqueGenericSet.add(brandName);
            System.out.println(brandName);
        }
        Assert.assertTrue(uniqueGenericSet.size() >= numberOfUniqueGenerics);
    }

    @Step("Method gets brand from product title. Listing_page")
    public Listing_page_Logic getBrandFromTitle(String expectedTextInTitle, int brandPositionInAlt, Boolean shouldHaveTextOrNotHave, ElementsCollection titleViewMode) {
        titleViewMode.shouldHave(sizeGreaterThan(0));
        for (int i = 0; i < titleViewMode.size(); i++) {
            if (shouldHaveTextOrNotHave) {
                titleViewMode.get(i).shouldHave(text(expectedTextInTitle.split(" ")[brandPositionInAlt]));
            } else {
                titleViewMode.get(i).shouldNotHave(text(expectedTextInTitle.split(" ")[brandPositionInAlt]));
            }
        }
        return this;
    }

    @Step("Method checks product attribute on listing. Listing_page")
    public void checkProductAttributeOnListing(String attributeSelectedInSideFilter, ElementsCollection productAttributeOnListing) {
        productAttributeOnListing.shouldHave(sizeGreaterThan(0));
        for (int i = 0; i < productAttributeOnListing.size(); i++) {
            productAttributeOnListing.get(i).shouldHave(text(attributeSelectedInSideFilter));
        }
    }

    @Step("Method checks product attribute on listing with products number. Listing_page")
    public void checkProductAttributeOnListingWithProductsNumber(String attributeSelectedInSideFilter, ElementsCollection productAttributeOnListing, int productsNumber) {
        productAttributeOnListing.shouldHave(sizeGreaterThan(0));
        for (int i = 1; i < productsNumber; i++) {
            productAttributeOnListing.get(i).shouldHave(text(attributeSelectedInSideFilter));
        }
    }

    @Step("Method checks product attribute on listing with chosen car and filter (without products for other cars). Listing_page")
    public Listing_page_Logic checkProductAttributeOnListingWithCarAndFilter(String characteristic, ElementsCollection productAttributeGenericRoute, ElementsCollection productAttributeTecdocRoute) {
        if (productsForOtherCars().is(visible)) {
            productsForOtherCars().waitUntil(visible, 2000);
            checkProductAttributeOnListing(characteristic, productAttributeGenericRoute);
        } else {
            checkProductAttributeOnListing(characteristic, productAttributeTecdocRoute);
        }
        return this;
    }

    @Step("Method checks product attribute on listing with two expected values. Listing_page")
    public Listing_page_Logic checkProductAttributeOnListingWithCarAndFilterWithTwoValues(String characteristic, String secondCaracteristicValue, ElementsCollection productAttributeGenericRoute, ElementsCollection productAttributeTecdocRoute) {
        if (productsForOtherCars().is(visible)) {
            productsForOtherCars().waitUntil(visible, 2000);
            checkProductAttributeOnListingTwoValues(characteristic, secondCaracteristicValue, productAttributeGenericRoute);
        } else {
            checkProductAttributeOnListingTwoValues(characteristic, secondCaracteristicValue, productAttributeTecdocRoute);
        }
        return this;
    }

    @Step("Method checks product attribute on listing with two expected values. Listing_page")
    private void checkProductAttributeOnListingTwoValues(String attributeSelectedInSideFilter, String secondValue, ElementsCollection productAttributeOnListing) {
        productAttributeOnListing.shouldHave(sizeGreaterThan(0));
        for (int i = 0; i < productAttributeOnListing.size(); i++) {
            productAttributeOnListing.get(i).shouldHave(or("values", text(attributeSelectedInSideFilter), (text(secondValue))));
        }
    }

    @Step("Method checks product attribute on listing in tile mode. Listing_page")
    public void checkProductAttributeOnListingInTileMode(String attributeSelectedInSideFilter, ElementsCollection productAttributeOnListing) {
        productAttributeOnListing.shouldHave(sizeGreaterThan(0));
        for (int i = 0; i < productAttributeOnListing.size(); i++) {
            productsOnListingInTileMode().get(i).hover();
            productAttributeOnListing.get(i).shouldHave(text(attributeSelectedInSideFilter));
            showListingInTileModeButton().hover();
        }
    }

    @Step("The method checks that products at listing  are fits for chosen car. The method clicking on every each product and on the product page checks the text in the selected car's information block. Listing_page")
    public void checksThatProductsAtListingAreFitsForChosenCar(String textChosenCar) {
        ElementsCollection productsFromAdditionalTecDocListing = imagesProductsInAdditionalListingForTecDoc();
        productsFromAdditionalTecDocListing.shouldHave(sizeGreaterThan(1));
        for (int product = 0; product < productsFromAdditionalTecDocListing.size(); product++) {
            productsFromAdditionalTecDocListing.get(product).scrollTo().click();
            new Product_page_Logic().infoBlockWithSelectedCar().shouldBe(visible).shouldHave(text(textChosenCar));
            back();
            titleOfAdditionalListingForTecDoc().shouldBe(visible);
            productsFromAdditionalTecDocListing = imagesProductsInAdditionalListingForTecDoc();
            productsFromAdditionalTecDocListing.shouldHave(sizeGreaterThan(1));
        }
    }

    @Step("The method saves the order of products on listing, switches to tile view and verifies that order of products remains the same. Listing_page")
    public Listing_page_Logic compareProductsOrderBetweenListModeAndTileMode() {
        ElementsCollection elementsWithArticles = productArticlesCollection().first(12);
        ArrayList<String> articlesInListMode = new ArrayList<>();
        for (SelenideElement nameProduct : elementsWithArticles) {
            String name = nameProduct.text().split(": ")[1];
            articlesInListMode.add(name);
        }
        showListingInTileModeButton().click();
        checkingContainsUrl("?list=table");
        ElementsCollection articlesOnTileMode = articleProductsInTileMode().shouldHave(sizeGreaterThanOrEqual(10));
        for (int numberProductName = 0; numberProductName < articlesInListMode.size(); numberProductName++) {
            String articleListMode = articlesInListMode.get(numberProductName);
            String articleNumberTileMode = articlesOnTileMode.get(numberProductName).text().split(": ")[1];
            assertEquals(articleNumberTileMode, articleListMode, "Product order " + articleListMode + " does not match between list mode and tile mode");
        }
        return this;
    }

    @Step("Check absence expected article num {expectedArtNum}. Listing_page")
    public Listing_page_Logic checkAbsenceArticleNum(String expectedArtNum) {
        for (int i = 0; i < productArticlesCollection().size(); i++) {
            productArticlesCollection().get(i).scrollIntoView(("{block: \"center\"}")).shouldNot(text(expectedArtNum));
        }
        return this;
    }

    @Step("Method checks product attribute on listing in tile mode. Listing_page")
    public Listing_page_Logic checkProductAttributeOnListingInTileMode2(String attributeSelectedInSideFilter, int productWithFiler) {
        ElementsCollection characS = $$x("//*[contains(text(),'Einbauseite:')]/ancestor :: li[1]/span[2]");
        if (productWithFiler > 0) {
            for (int i = 0; i < productWithFiler; i++) {
                productsOnListingInTileMode().get(i).hover();
                characS.get(i).shouldHave(text(attributeSelectedInSideFilter));
                $(".search_button").hover();
            }
        } else {
            for (int i = 0; i < productsOnListingInTileMode().size(); i++) {
                productsOnListingInTileMode().get(i).hover();
                sleep(1000);
                if (characS.get(i).isDisplayed()) {
                    characS.get(i).shouldHave(text(attributeSelectedInSideFilter));
                    $(".search_button").hover();
                } else {
                    characS.get(i + 1).shouldHave(text(attributeSelectedInSideFilter));
                }
            }
        }
        return this;
    }

    @Step("Method checks product attribute on OEM listing. Listing_page")
    public Listing_page_Logic checkProductCharacteristicOnListingOem(String attributeSelectedInSideFilter, ElementsCollection productAttributeOnListing) {
        for (int i = 0; i < $$(".list_products > li").size(); i++) {
            $$(".product-params-oem").get(i).click();
            productAttributeOnListing.get(i).shouldHave(text(attributeSelectedInSideFilter));
        }
        return this;
    }

    @Step("Method checks add to basket buttons sorting on listing. Listing_page")
    public Listing_page_Logic checkAddToBasketButtonsSorting() {
        for (int i = 0; i < addToBasketButtons().size() - 1; i++) {
            if (addToBasketButtons().get(i).text().contains("VERFÜGBARKEIT")) {
                addToBasketButtons().get(i + 1).shouldHave(text("VERFÜGBARKEIT"));
            }
        }
        return this;
    }

    @Step("Method checks products sorting on listing in increasing order for RIDEX products. Listing_page")
    public Listing_page_Logic checkPriceSortingInIncreasingOrderRidex(ElementsCollection listingViewModeLocator) {
        List<Float> price = getAllPricesOnListingPage(listingViewModeLocator);
        ElementsCollection ridexProducts = $$x("//*[@class='name']/a[contains (text(),'RIDEX')]");
        for (int i = 0; i < ridexProducts.size() - 1; i++) {
            if (firstProductTitleOnListing().has(text("RIDEX"))) {
                if (price.get(i) <= price.get(i + 1)) {
                    System.out.println(price.get(i));
                } else {
                    Assert.fail("Products are NOT sorted by price in increasing order");
                }
                System.out.println("Products are sorted by price in increasing order");
            }
        }
        return this;
    }

    @Step("Method checks products sorting on listing in increasing order for not RIDEX products with one generic. Listing_page")
    public Listing_page_Logic checkPriceSortingInIncreasingOrderNotRidex(ElementsCollection listingViewModeLocator) {
        List<Float> price = getAllPricesOnListingPage(listingViewModeLocator);
        ElementsCollection ridexProducts = $$x("//*[@class='name']/a[contains (text(),'RIDEX')]");
        ElementsCollection grayButtons = $$x("//*[contains(@class,'not_active')]/a");
        if (grayButtons.size() < 1) {
            for (int i = ridexProducts.size(); i < getAllPricesOnListingPage(listingViewModeLocator).size() - 1; i++) {
                if (price.get(i) <= price.get(i + 1)) {
                    System.out.println(price.get(i));
                } else {
                    Assert.fail("Products are NOT sorted by price in increasing order");
                }
                System.out.println("Products are sorted by price in increasing order");
            }
        } else {
            for (int i = redButtons().size(); i < getAllPricesOnListingPage(listingViewModeLocator).size() - 1; i++) {
                if (price.get(i) <= price.get(i + 1)) {
                    System.out.println(price.get(i));
                } else {
                    Assert.fail("Products are NOT sorted by price in increasing order");
                }
                System.out.println("Products are sorted by price in increasing order");
            }
        }
        return this;
    }

    @Step("Method checks products sorting on listing in increasing order for not RIDEX products with two generics. Listing_page")
    public Listing_page_Logic checkPriceSortingInIncreasingOrderNotRidex2generic(ElementsCollection listingViewModeLocator) {
        List<Float> price = getAllPricesOnListingPage(listingViewModeLocator);
        ElementsCollection ridexProducts = $$x("//*[@class='name']/a[contains (text(),'RIDEX')]");
        ElementsCollection notHalterProducts = $$x("//*[@class='name']/a[not (contains (text(),'Halter'))]");
        ElementsCollection halterProducts = $$x("//*[@class='name']/a[contains (text(),'Halter')]");
        if ($$x("//*[contains(@class,'not_active')]/a").size() < 1) {
            if (halterProducts.size() < 1) {
                for (int i = ridexProducts.size(); i < getAllPricesOnListingPage(listingViewModeLocator).size() - 1; i++) {
                    if (price.get(i) <= price.get(i + 1)) {
                        System.out.println(price.get(i));
                    } else {
                        Assert.fail("Products are NOT sorted by price in increasing order");
                    }
                    System.out.println("Products are sorted by price in increasing order");
                }
            } else {
                for (int i = notHalterProducts.size(); i < getAllPricesOnListingPage(listingViewModeLocator).size() - 1; i++) {
                    if (price.get(i) <= price.get(i + 1)) {
                        System.out.println(price.get(i));
                    } else {
                        Assert.fail("Products are NOT sorted by price in increasing order");
                    }
                    System.out.println("Products are sorted by price in increasing order");
                }
            }
        } else {
            for (int i = redButtons().size(); i < getAllPricesOnListingPage(listingViewModeLocator).size() - 1; i++) {
                if (price.get(i) <= price.get(i + 1)) {
                    System.out.println(price.get(i));
                } else {
                    Assert.fail("Products are NOT sorted by price in increasing order");
                }
                System.out.println("Products are sorted by price in increasing order");
            }
        }
        return this;
    }

    // The method can check everything that is contained in the product title by going through all the pages. Fits for all listing (TecDoc, Search, OEN etc...)
    @Step("The method verifies that the product titles on listing contain the expected text {expectedText}. Verification works for all the pagination by switching pages one by one. Listing_page")
    public Listing_page_Logic checksProductTitlesContainExpectedTextGoingAllPagination(String expectedText) {
        checkProductTitleOnListing(expectedText, true, productTitleInListMode());
        while (nextPageButton().is(visible)) {
            nextPageButton().hover().click();
            checkProductTitleOnListing(expectedText, true, productTitleInListMode());
        }
        return this;
    }

    @Step("The method verifies that the product titles on listing contain the expected text on first two pages. Listing_page")
    public Listing_page_Logic checksProductTitlesContainExpectedTextGoingTwoPagesPagination(String expectedText) {
        checkProductTitleOnListing(expectedText, true, productTitleInListMode());
        nextPageButton().click();
        checkProductTitleOnListing(expectedText, true, productTitleInListMode());
        return this;
    }

    @Step("Checks important elements on TecDoc listing (Side filters block, Brand filter block, Pagination blocks). Listing_page")
    public Listing_page_Logic checksImportantElementsOnTecDocListing() {
        blockOfBySideFilters().shouldBe(visible);
        brandFilterBlock().shouldBe(visible);
        paginationFirstBlock().shouldBe(visible);
        paginationSecondBlock().shouldBe(visible);
        return this;
    }

    @Step("Checks important elements on OEN listing (Brand filter block, Pagination blocks, OEN analog block). Listing_page")
    public Listing_page_Logic checksImportantElementsOnOenListing() {
        brandFilterBlock().shouldBe(visible);
        paginationFirstBlock().shouldBe(visible);
        paginationSecondBlock().shouldBe(visible);
        oemAnalogBlock().shouldBe(visible);
        return this;
    }

    // The method fits search and lkw listing
    @Step("Checks important elements on listing (Side filters block, Brand filter block, Pagination block). Listing_page")
    public Listing_page_Logic checksImportantElementsOnListing() {
        if (!blockOfBySideFilters().isDisplayed()) {
            blockOfBySideFiltersInSidebar().shouldBe(visible);
        }
        brandFilterBlock().shouldBe(visible);
        paginationFirstBlock().shouldBe(visible);
        return this;
    }

    @Step("Goes to pfand link from listing page. Listing_page")
    public Austauschartikel_static_page clickLinkPfandFromListing() {
        pfandBlock().scrollTo();
        pfandPagelink().click();
        switchTo().window(1);
        return page(Austauschartikel_static_page.class);
    }

    @Step("Go to First Pfand Product from listing. Listing_page")
    public Product_page_Logic goToFirstPfandProduct() {
        productsWithPfandBlock().click();
        new Product_page_Logic().pfandBlock().shouldBe(visible);
        return page(Product_page_Logic.class);
    }

    @Step("Method checks quantity of rating stars in each product on listing. Listing_page")
    public void checkQuantityOfRatingStarsOnListing(int choosenRatingInFilter, ElementsCollection collectionOfRatingElements) {
        for (int i = 1; i < collectionOfRatingElements.size(); i++) {
            activeRatingStarsInEveryProduct(i).shouldHaveSize(choosenRatingInFilter);
        }
    }

    @Step("Method checks unique ratings on listing. Listing_page")
    public void checkUniqueRatingOnListing(int numberOfUniqueRatings) {
        Set<Integer> uniqueRatingSet = new LinkedHashSet<>();
        for (int i = 1; i < ratingInProductBlock().size(); i++) {
            int rating = activeRatingStarsInEveryProduct(i).size();
            uniqueRatingSet.add(rating);
        }
        Assert.assertTrue(uniqueRatingSet.size() >= numberOfUniqueRatings);
    }

    @Step("Add product to basket and check related products popup on listing. Listing_page")
    public Listing_page_Logic checkRelatedProductsPopupOnListing(int numberCategories) {
        buyButton().click();
        new Product_page_Logic().categoriesInRelatedProductsPopup().shouldHaveSize(numberCategories);
        return this;
    }

    @Step("Wait until preloaded disappear. Listing_page")
    public Listing_page_Logic waitUntilPreloaderDisappear() {
        try {
            preloader().waitUntil(attribute("style", "display: none;"), 50000);
        } catch (ElementNotFound ex) {
            System.out.println("Preloader is not visible");
            ex.printStackTrace();
        }
        return this;
    }

    @Step("Wait until preloader disappear. Listing_page")
    public Listing_page_Logic waitUntilPreloaderDisappearAndSleep(int sleepTime) throws Exception {
        preloader().waitUntil(attribute("style", "display: none;"), 20000);
        Thread.sleep(sleepTime);
        return this;
    }

    @Step("Click Filter By Side Back. Listing_page")
    public Listing_page_Logic clickFilterBySideBack() {
        filterBySideBack().click();
        return this;
    }

    @Step("Click Filter By Side Front. Listing_page")
    public Listing_page_Logic clickFilterBySideFront() {
        filterBySideLKW().click();
        return this;
    }

    @Step("Click Show Listing In Tile Mode Button. Listing_page")
    public Listing_page_Logic clickShowListingInTileModeButton() {
        showListingInTileModeButton().click();
        return this;
    }

    @Step("Click Show Listing In List Mode Button. Listing_page")
    public Listing_page_Logic clickShowListingInListModeButton() {
        showListingInListModeButton().click();
        return this;
    }

    @Step("Click Second Listing Page Button. Listing_page")
    public Listing_page_Logic clickSecondListingPageButton() {
        secondListingPage().click();
        return this;
    }

    @Step("Check Side In Tile Mode LKW. Listing_page")
    public Listing_page_Logic checkSideInTileModeLKW() {
        int vorderachseAttributes = vorderachseAttributeInTileMode().size();
        int producntsOnListing = productsOnListingInTileMode().size();
        Assert.assertEquals(vorderachseAttributes, producntsOnListing);
        return this;
    }

    @Step("Check Side In Tile Mode. Listing_page")
    public Listing_page_Logic checkSideInTileMode(String expectedSide) {
        int productWithFiler = einbauseiteProductAttributeGenericRoute().size();
        clickShowListingInTileModeButton();
        waitUntilPreloaderDisappear();
        checkProductAttributeOnListingInTileMode2(expectedSide, productWithFiler);
        return this;
    }

    @Step("Check By Side Slider. Listing_page")
    public Listing_page_Logic checkBySideSlider() throws InterruptedException {
        blockOfBySideFilters().shouldBe(visible);
        nextButtonInBySideSlider().click();
        secondPageInBySideSlider().shouldBe(visible);
        nextButtonInBySideSliderDisabled().shouldHave(attribute("aria-disabled", "true"));
        Thread.sleep(3000);
        previousButtonInBySideSlider().click();
        firstPageInBySideSlider().shouldBe(visible);
        previousButtonInBySideSliderDisabled().shouldHave(attribute("aria-disabled", "true"));
        return this;
    }

    @Step("Click third hohe value. Listing_page")
    public Listing_page_Logic clickThirdHoheValue() {
        hoheThirdSideFilterButton().click();
        return this;
    }

    @Step("Click first hohe value. Listing_page")
    public Listing_page_Logic clickFirstHoheValue() {
        hoheFirstSideFilterButton().click();
        return this;
    }

    @Step("Click first lochanzahl value. Listing_page")
    public Listing_page_Logic clickFirstLochanzahlValue() throws Exception {
        Thread.sleep(2000);
        lochanzahlSideFilterButton().click();
        return this;
    }

    @Step("Click first first brand name in filter button. Listing_page")
    public Listing_page_Logic clickFirstBrandNameInFilter() {
        firstBrandInFilterButton().click();
        return this;
    }

    @Step("Click first second brand name in filter button. Listing_page")
    public Listing_page_Logic clickSecondBrandNameInFilter() {
        secondBrandInFilterButton().click();
        return this;
    }

    @Step("Click first first brand name in filter button OEM. Listing_page")
    public Listing_page_Logic clickFirstBrandNameOemListing() {
        firstBrandButtonOemListing().click();
        return this;
    }

    @Step("Click first second brand name in filter button OEM. Listing_page")
    public Listing_page_Logic clickSecondBrandNameOemListing() {
        secondBrandButtonOemListing().click();
        return this;
    }

    @Step("Check brand filter with six choosen bands + pagination (LKW). Listing_page")
    public Listing_page_Logic checkBrandFilterWithSixBrandsPagination() {
        String brandName1 = firstBrandNameInFiler().attr("alt").split(" ")[0];
        String brandName2 = secondBrandNameInFilter().attr("alt").split(" ")[0];
        String brandName3 = thirdBrandNameInFilter().attr("alt").split(" ")[0];
        String brandName4 = fourthBrandNameInFilter().attr("alt").split(" ")[0];
        String brandName5 = fifthBrandNameInFilter().attr("alt").split(" ")[0];
        String brandName6 = sixthBrandNameInFilter().attr("alt").split(" ")[0];
        clickSecondListingPageButton();
        checkProductTitleOnListingWithSixExpectedTexts(brandName1, brandName2, brandName3, brandName4, brandName5, brandName6, true, productTitleInListMode());
        return this;
    }

    @Step("Check brand filter with two choosen bands + pagination. Listing_page")
    public Listing_page_Logic checkBrandFilterWithTwoBrandsPagination() {
        String brandName1 = firstBrandNameInFiler().attr("alt").split(" ")[0];
        String brandName2 = secondBrandNameInFilter().attr("alt").split(" ")[0];
        clickSecondListingPageButton();
        checkProductTitleOnListingWithTwoExpectedTexts(brandName1, brandName2, true, productTitleInListMode());
        return this;
    }

    @Step("Check brand filter with two choosen bands + pagination (LKW without car). Listing_page")
    public Listing_page_Logic checkBrandFilterWithTwoBrandsPaginationLKW() {
        String brandName1 = firstBrandNameInFiler().attr("alt").split(" ")[2];
        String brandName2 = secondBrandNameInFilter().attr("alt").split(" ")[2];
        clickSecondListingPageButton();
        checkProductTitleOnListingWithTwoExpectedTexts(brandName1, brandName2, true, productTitleInListMode());
        return this;
    }

    @Step("Get attribute from element. Listing_page")
    public String getAtributeFromElement(SelenideElement element, String attribute) {
        String textFromAttribute = element.attr(attribute);
        return textFromAttribute;
    }

    @Step("Gets the attribute of the element and returns the first word. Listing_page")
    public String getAtributeFromElementAndReturnsFirstWord(SelenideElement element, String attribute) {
        String textFromAttribute = element.attr(attribute);
        String partText = textFromAttribute.replace(textFromAttribute.substring(textFromAttribute.indexOf(" ")), "");
        return partText;
    }

    @Step("Get text or attribute from filter. Listing_page")
    public String getTextOrAttributeFromFilter(SelenideElement firstElement, SelenideElement secondElement, String attribute) {
        String textFromElement = null;
        if (resetBlock().isDisplayed()) {
            textFromElement = firstElement.getText().replaceAll("\\.", "");
        } else {
            textFromElement = secondElement.attr(attribute);
        }
        return textFromElement;
    }

    @Step("Get attribute from element with split. Listing_page")
    public String getAtributeFromElementLKWsearch(SelenideElement element, String attribute) {
        String textFromAttribute = element.attr(attribute).split(" ")[2];
        return textFromAttribute;
    }

    @Step("Click filter button. Listing_page")
    public Listing_page_Logic clickFilterButton(SelenideElement filterButton) {
        filterButton.scrollTo().click();
        return this;
    }

    @Step("set brand filter by id. Listing_page")
    public Listing_page_Logic setBrandFilterById(String id) {
        brandFilterBlock().shouldBe(visible);
        while (!brandFilterById(id).isDisplayed()) {
            forwardOfBrandBlock().click();
        }
        brandFilterById(id).click();
        return this;
    }

    @Step("Get text from filter. Listing_page")
    public String getTextFromElement(SelenideElement element) {
        String textFromElement = element.getText();
        return textFromElement;
    }

    @Step("Get size of collection. Listing_page")
    public int getSizeOfCollection(ElementsCollection collection) {
        int collectionSize = collection.size();
        return collectionSize;
    }

    @Step("Hover on side filter and click button. Listing_page")
    public Listing_page_Logic hoverOnSideFilterAndClick(SelenideElement filterButton) {
        sideJSfilterForm().hover();
        filterButton.scrollTo().click();
        return this;
    }

    @Step("Hover on side filter and click button on search route hohe. Listing_page")
    public Listing_page_Logic hoverOnSideFilterAndClickSearchRouteHohe(SelenideElement filterButton) {
        sideJSfilterForm().hover();
        hoheBlock().hover();
        filterButton.hover().click();
        return this;
    }

    @Step("Check filter is canceled. Listing_page")
    public Listing_page_Logic checkFilterIsCanceled(int filterOn, int filterOff) {
        Assert.assertNotEquals(filterOn, filterOff);
        return this;
    }

    @Step("Check text {expectedText} in element {element}. Listing_page")
    public Listing_page_Logic checkTextInElement(SelenideElement element, String expectedText) {
        element.shouldHave(text(expectedText));
        return this;
    }

    @Step("Check visibility of element {element}. Listing_page")
    public Listing_page_Logic checkVisibilityOfElement(SelenideElement element) {
        element.shouldBe(visible);
        return this;
    }

    @Step("Check element is not visibile {element}. Listing_page")
    public Listing_page_Logic checkElementIsNotVisible(SelenideElement element) {
        sleep(5000);
        element.shouldNotBe(visible);
        return this;
    }

    @Step("Check price and add to basket button sorting with generic. Listing_page")
    public Listing_page_Logic checkOutptuSortingWithGeneric() {
        do {
            checkPriceSortingInIncreasingOrderRidex(priceOfAllProductsOnPageInList());
            checkPriceSortingInIncreasingOrderNotRidex(priceOfAllProductsOnPageInList());
            checkAddToBasketButtonsSorting();
            nextPageButton().click();
        } while (nextPageButton().is(visible));
        return this;
    }

    @Step("Check price and add to basket button sorting with two generic. Listing_page")
    public Listing_page_Logic checkOutptuSortingWithTwoGeneric() {
        do {
            checkPriceSortingInIncreasingOrderRidex(priceOfAllProductsOnPageInList());
            checkPriceSortingInIncreasingOrderNotRidex2generic(priceOfAllProductsOnPageInList());
            checkAddToBasketButtonsSorting();
            nextPageButton().click();
        } while (nextPageButton().is(visible));
        return this;
    }

    @Step("Check gray button not visible. Listing_page")
    public Listing_page_Logic checkGrayButtonNotVisible() {
        do {
            grayButton().shouldNot(exist);
            nextPageButton().click();
        } while (nextPageButton().is(visible));
        return this;
    }

    @Step("Check add to basket buttons sorting with pagination. Listing_page")
    public Listing_page_Logic checkAddToBasketButtonsSortingWithPagination() {
        do {
            checkAddToBasketButtonsSorting();
            nextPageButton().click();
        } while (nextPageButton().is(visible));
        return this;
    }

    @Step("Check sorting of grey button. Listing_page")
    public Listing_page_Logic checkSortingOfGreyButton() {
        checkAddToBasketButtonsSorting();
        while (nextPageButton().isDisplayed()) {
            nextPageButton().click();
            checkAddToBasketButtonsSorting();
        }
        return this;
    }

    @Step("Check OEN listing elements. Listing_page")
    public Listing_page_Logic checkOENListingElements() {
        titleOfListing().shouldHave(text("Bremsscheibe OE - NUMMER 34116785670"));
        oemNumberBlock().shouldBe(visible);
        oemDescriptionBlock().shouldBe(visible);
        oemAnalogBlock().shouldBe(visible);
        return this;
    }

    @Step("Check Tecdoc listing elements. Listing_page")
    public Listing_page_Logic checkTecdocListingElements() {
        titleOfListing().shouldHave(text("Aktuelle Angebote zu Ölfilter für VW Golf IV Schrägheck (1J1) 1.4 16V Benzin 75 PS"));
        tecDocBlockOfLinkingCategories().shouldBe(visible);
        return this;
    }

    @Step("Click show more characteristic button. Listing_page")
    public Listing_page_Logic clickMoreCharacteristicInFilter() {
        moreCharacteristicButtonInFilter().click();
        return this;
    }

    @Step("Scroll And Check Fix Filters In Sidebar. Listing_page")
    public Listing_page_Logic scrollAndCheckFixFiltersInSidebar() {
        ninthProductOnListing().scrollTo();
        sideJSfilterForm().shouldBe(visible);
        return this;
    }

    @Step("Check Filters Fix In Sidebar. Listing_page")
    public Listing_page_Logic checkFiltersFixInSidebar() {
        blockOfBySideFilters().shouldBe(visible);
        brandFilterBlock().shouldBe(visible);
        ratingFilterBlock().shouldBe(visible);
        langeFilterBlockInSidebar().shouldBe(visible);
        scrollAndCheckFixFiltersInSidebar();
        bySideFilterBlockInSidebar().shouldBe(visible);
        brandFilterBlockInSidebar().shouldBe(visible);
        ratingFilterBlock().hover().shouldBe(visible);
        langeFilterBlockInSidebar().hover().shouldBe(visible);
        return this;
    }

    @Step("Check Filters Fix In Sidebar Search Route. Listing_page")
    public Listing_page_Logic checkFiltersFixInSidebarSearchRoute() {
        brandFilterBlock().shouldBe(visible);
        scrollAndCheckFixFiltersInSidebar();
        brandFilterBlockInSidebar().shouldBe(visible);
        return this;
    }

    @Step("Check Filters Fix In Sidebar LKW. Listing_page")
    public Listing_page_Logic checkFiltersFixInSidebarLKW() {
        blockOfBySideFilters().shouldBe(visible);
        brandFilterBlock().shouldBe(visible);
        ratingFilterBlock().shouldBe(visible);
        scrollAndCheckFixFiltersInSidebar();
        bySideFilterBlockInSidebar().shouldBe(visible);
        brandFilterBlockInSidebar().shouldBe(visible);
        ratingFilterBlock().shouldBe(visible);
        return this;
    }

    @Step("Check Filters Fix In Sidebar LKW Search Route. Listing_page")
    public Listing_page_Logic checkFiltersFixInSidebarLKWsearchRoute() {
        brandFilterBlock().shouldBe(visible);
        scrollAndCheckFixFiltersInSidebar();
        brandFilterBlockInSidebar().shouldBe(visible);
        return this;
    }

    @Step("Check Output With Filters By Side Fix In Sidebar. Listing_page")
    public Listing_page_Logic checkOutputWithFiltersBySideFixInSidebar(String characteristic) {
        scrollAndCheckFixFiltersInSidebar();
        bySideFilterInSidebarFront().click();
        waitUntilPreloaderDisappear();
        checkProductAttributeOnListingWithCarAndFilter(characteristic, einbauseiteProductAttributeGenericRoute(), einbauseiteProductAttributeTecdocRoute());
        scrollAndCheckFixFiltersInSidebar();
        bySideFilterInSidebarFront().click();
        waitUntilPreloaderDisappear();
        checkUniqueBrandsOnListing(2, einbauseiteProductAttributeTecdocRoute());
        return this;
    }

    @Step("Check Output With Filters By Side Fix In Sidebar Route LKW. Listing_page")
    public Listing_page_Logic checkOutputWithFiltersBySideFixInSidebarRouteLKW(String characteristic, String secondCharacteristic) {
        scrollAndCheckFixFiltersInSidebar();
        bySideFilterInSidebarFront().click();
        waitUntilPreloaderDisappear();
        checkProductAttributeOnListingWithCarAndFilterWithTwoValues(characteristic, secondCharacteristic, einbauseiteProductAttributeGenericRoute(), einbauseiteProductAttributeTecdocRoute());
        scrollAndCheckFixFiltersInSidebar();
        bySideFilterInSidebarFront().click();
        waitUntilPreloaderDisappear();
        checkUniqueBrandsOnListing(2, einbauseiteProductAttributeTecdocRoute());
        return this;
    }

    @Step("Check Output With Filters By Side Fix In Sidebar. Listing_page")
    public Listing_page_Logic checkOutputWithFiltersBySideFixInSidebarSearchRoute(String characteristic) {
        scrollAndCheckFixFiltersInSidebar();
        closePopupByClickOverlayOnListingSearch();
        bySideFilterInSidebarFrontSearchRoute().click();
        waitUntilPreloaderDisappear();
        checkProductAttributeOnListingWithCarAndFilter(characteristic, einbauseiteProductAttributeGenericRoute(), einbauseiteProductAttributeTecdocRoute());
        scrollAndCheckFixFiltersInSidebar();
        closePopupByClickOverlayOnListingSearch();
        bySideFilterInSidebarFrontSearchRoute().click();
        waitUntilPreloaderDisappear();
        checkUniqueBrandsOnListing(2, einbauseiteProductAttributeTecdocRoute());
        return this;
    }

    @Step("Check Output With Filters By Side Fix In Sidebar Back Side Filter. Listing_page")
    public Listing_page_Logic checkOutputWithFiltersBySideFixInSidebarBackSide(String characteristic) {
        scrollAndCheckFixFiltersInSidebar();
        bySideFilterInSidebarBack().click();
        waitUntilPreloaderDisappear();
        checkProductAttributeOnListingWithCarAndFilter(characteristic, einbauseiteProductAttributeGenericRoute(), einbauseiteProductAttributeTecdocRoute());
        scrollAndCheckFixFiltersInSidebar();
        bySideFilterInSidebarBack().click();
        waitUntilPreloaderDisappear();
        checkUniqueBrandsOnListing(2, einbauseiteProductAttributeTecdocRoute());
        return this;
    }

    @Step("Check Output With Filters By Brand Fix In Sidebar. Listing_page")
    public Listing_page_Logic checkOutputWithFiltersByBrandFixInSidebar(int brandPositionInAlt) {
        scrollAndCheckFixFiltersInSidebar();
        String brand = brandFilterButtonInSidebarName().attr("alt");
        brandFilterButtonInSidebarButton().click();
        waitUntilPreloaderDisappear();
        getBrandFromTitle(brand, brandPositionInAlt, true, productTitleInListMode());
        scrollAndCheckFixFiltersInSidebar();
        brandFilterButtonInSidebarButton().click();
        waitUntilPreloaderDisappear();
        checkUniqueBrandsOnListing(2, productTitleInListMode());
        return this;
    }

    @Step("Check Output With Filters By Brand Fix In Sidebar LKW. Listing_page")
    public Listing_page_Logic checkOutputWithFiltersByBrandFixInSidebarLKW(int brandPositionInAlt) {
        scrollAndCheckFixFiltersInSidebar();
        String brand = brandFilterSecondButtonInSidebarName().attr("alt");
        brandFilterSecondButtonInSidebarButton().click();
        waitUntilPreloaderDisappear();
        getBrandFromTitle(brand, brandPositionInAlt, true, productTitleInListMode());
        scrollAndCheckFixFiltersInSidebar();
        brandFilterButtonInSidebarButton().click();
        waitUntilPreloaderDisappear();
        checkUniqueBrandsOnListing(2, productTitleInListMode());
        return this;
    }

    @Step("Check Output With Filters By Brand Fix In Sidebar Route Search. Listing_page")
    public Listing_page_Logic checkOutputWithFiltersByBrandFixInSidebarRouteSearch(int brandPositionInAlt) {
        scrollAndCheckFixFiltersInSidebar();
        closeSelectorPopup();
        String brand = thirdBrandFilterButtonInSidebarName().attr("alt");
        thirdBrandFilterButtonInSidebarButton().click();
        waitUntilPreloaderDisappear();
        getBrandFromTitle(brand, brandPositionInAlt, true, productTitleInListMode());
        scrollAndCheckFixFiltersInSidebar();
        closeSelectorPopup();
        firstBrandFilterButtonInSidebarButton().click();
        waitUntilPreloaderDisappear();
        getBrandFromTitle(brand, brandPositionInAlt, false, productTitleInListMode());
        return this;
    }

    @Step("Close popup by click overlay. Listing_page")
    public Listing_page_Logic closePopupByClickOverlayOnListingSearch() {
        if (blackOverlay().is(visible)) {
            blackOverlay().click(1, 1);
            blackOverlay().shouldNotBe(visible);
        }
        return this;
    }

    @Step("Close selector popup. Listing_page")
    public Listing_page_Logic closeSelectorPopup() {
        if (popupSelector().isDisplayed()) {
            closePopupSelectorBtn().click();
            popupSelector().shouldNotBe(visible);
        }
        return this;
    }

    @Step("Check Output With Filters By Rating Fix In Sidebar. Listing_page")
    public Listing_page_Logic checkOutputWithFiltersByRatingFixInSidebar() {
        scrollAndCheckFixFiltersInSidebar();
        ratingThreeStarsFilterCheckbox().click();
        waitUntilPreloaderDisappear();
        checkQuantityOfRatingStarsOnListingInPercent(ratingInProductBlock());
        scrollAndCheckFixFiltersInSidebar();
        ratingThreeStarsFilterCheckbox().click();
        waitUntilPreloaderDisappear();
        checkTwoUniqueRatingOnListing();
        return this;
    }

    @Step("Method checks quantity of rating stars in each product on listing. Listing_page")
    public Listing_page_Logic checkQuantityOfRatingStarsOnListingInPercent(ElementsCollection collectionOfRatingElements) {
        for (int i = 1; i < collectionOfRatingElements.size(); i++) {
            activeRatingStarsInEveryProductPercent(i).shouldHave(attribute("style", "width: 60%;"));
        }
        return this;
    }

    @Step("Method checks two unique ratings on listing. Listing_page")
    public Listing_page_Logic checkTwoUniqueRatingOnListing() {
        Assert.assertTrue(fiveRatingStarsInProduct().size() < ratingInProductBlock().size());
        return this;
    }

    @Step("Check output with lange filter fix in sidebar. Listing_page")
    public Listing_page_Logic checkLangeFilterFixInSidebar() {
        scrollAndCheckFixFiltersInSidebar();
        String langeValue = langeSecondButtonInFixedSidebarFilter().text();
        langeSecondButtonInFixedSidebarFilter().hover().click();
        waitUntilPreloaderDisappear();
        checkProductAttributeOnListing(langeValue, langeProductAttributeTecdocRoute());
        scrollAndCheckFixFiltersInSidebar();
        langeFirstButtonInFixedSidebarFilter().hover().click();
        waitUntilPreloaderDisappear();
        checkUniqueBrandsOnListing(2, langeProductAttributeTecdocRoute());
        return this;
    }

    @Step("Check output with furprNummer filter fix in sidebar. Listing_page")
    public Listing_page_Logic checkfurprNummerFilterFixInSidebar() {
        scrollAndCheckFixFiltersInSidebar();
        String nummerValue = furPrnummerFirstButtonInSidebar().text().split(" ")[0];
        closePopupByClickOverlayOnListingSearch();
        furPrnummerFirstButtonInSidebar().hover().click();
        waitUntilPreloaderDisappear();
        checkProductAttributeOnListing(nummerValue, furprnummerProductAttributeTecdocRoute());
        scrollAndCheckFixFiltersInSidebar();
        closePopupByClickOverlayOnListingSearch();
        furPrnummerFirstButtonInSidebar().hover().click();
        waitUntilPreloaderDisappear();
        furprnummerProductAttributeTecdocRoute().shouldHaveSize(0);
        return this;
    }

    @Step("Check output with verschleisswarnkontakt filter fix in sidebar. Listing_page")
    public Listing_page_Logic checkVerschleisswarnkontaktFilterFixInSidebar() {
        scrollAndCheckFixFiltersInSidebar();
        closePopupByClickOverlayOnListingSearch();
        verschleisswarnkontaktFirstButtonInSidebar().hover().click();
        waitUntilPreloaderDisappear();
        checkProductAttributeOnListing("für Verschleißwarnanzeiger vorbereitet", verschleiswarnkontaktProductAttributeTecdocRouteLKW());
        scrollAndCheckFixFiltersInSidebar();
        closePopupByClickOverlayOnListingSearch();
        verschleisswarnkontaktFirstButtonInSidebar().hover().click();
        waitUntilPreloaderDisappear();
        checkUniqueBrandsOnListing(2, verschleiswarnkontaktProductAttributeTecdocRouteLKW());
        return this;
    }

    @Step("Check output with durchmesser filter fix in sidebar. Listing_page")
    public Listing_page_Logic checkDurchmesserFilterFixInSidebar() {
        scrollAndCheckFixFiltersInSidebar();
        String durchmesserValue = durchmesserSecondButtonInSidebar().text();
        durchmesserSecondButtonInSidebar().hover().click();
        waitUntilPreloaderDisappear();
        checkProductAttributeOnListing(durchmesserValue, durchmesserProductAttributeTecdocRoute());
        scrollAndCheckFixFiltersInSidebar();
        durchmesserFirstButtonInSidebar().hover().click();
        waitUntilPreloaderDisappear();
        durchmesserFirstButtonInSidebar().shouldNotBe(selected);
        durchmesserSecondButtonInSidebar().shouldNotBe(selected);
        return this;
    }

    @Step("Check output with bremsscheibenart filter fix in sidebar. Listing_page")
    public Listing_page_Logic checkBremsscheibenartFilterFixInSidebar() {
        scrollAndCheckFixFiltersInSidebar();
        String bremsscheibenartValue = bremsscheibenartFirstButtonInSidebar().text();
        bremsscheibenartFirstButtonInSidebar().hover().click();
        waitUntilPreloaderDisappear();
        checkProductAttributeOnListing(bremsscheibenartValue, bremsscheibenartProductAttributeTecdocRoute());
        scrollAndCheckFixFiltersInSidebar();
        bremsscheibenartFirstButtonInSidebar().hover().click();
        waitUntilPreloaderDisappear();
        checkUniqueBrandsOnListing(2, bremsscheibenartProductAttributeTecdocRoute());
        return this;
    }

    @Step("Get text from generic. Listing_page")
    public String getTextFromGeneric() {
        String text = null;
        if (secondGenericAboveListing().isDisplayed()) {
            text = secondGenericAboveListing().text();
        } else if (secondGenericInSidebar().isDisplayed()) {
            text = secondGenericInSidebar().getText();
        }
        return text;
    }

    @Step("Check first generic filter applying on listing. Listing_page")
    public Listing_page_Logic checkFirstGenericApplying(String genericName) {
        if (secondGenericAboveListing().isDisplayed()) {
            secondGenericAboveListing().click();
            firstGenericAboveListing().shouldHave(text(genericName));
        } else if (secondGenericInSidebar().isDisplayed()) {
            secondGenericInSidebar().click();
            firstGenericInSidebar().shouldHave(text(genericName));
        }
        checkProductTitleOnListing(genericName, true, productTitleInListMode());
        refresh();
        if (firstGenericAboveListing().isDisplayed()) {
            firstGenericAboveListing().shouldHave(text(genericName));
        } else if (firstGenericInSidebar().isDisplayed()) {
            firstGenericInSidebar().shouldHave(text(genericName));
        }
        checkProductTitleOnListing(genericName, true, productTitleInListMode());
        return this;
    }

    @Step("Check second generic filter applying on listing. Listing_page")
    public Listing_page_Logic checkSecondGenericApplying() {
        String routeName = getNameRouteFromJSVarInHTML();
        String secondGenericName = getTextFromGeneric();
        if (secondGenericAboveListing().isDisplayed()) {
            secondGenericAboveListing().click();
        } else if (secondGenericInSidebar().isDisplayed()) {
            secondGenericInSidebar().click();
        }
        waitUntilPreloaderDisappear();
        checkProductTitleOnListing(secondGenericName, true, productTitleInListMode());
        if (!routeName.equals("moto_category_car_list") && (!routeName.equals("moto_category_car_list_model"))) {
            showListingInTileModeButton().click();
            waitUntilPreloaderDisappear();
            checkProductTitleOnListing(secondGenericName, true, productTitleInTileMode());
            allCategoryGeneric().shouldBe(visible);
            allCategoryGeneric().click();
            waitUntilPreloaderDisappear();
            checkUniqueGenericsOnListing(1, productTitleInTileMode());
        } else {
            allCategoryGeneric().shouldBe(visible);
            allCategoryGeneric().click();
            waitUntilPreloaderDisappear();
            checkUniqueGenericsOnListing(1, productTitleInListMode());
        }
        return this;
    }

    @Step("Check second generic filter applying on listing LKW. Listing_page")
    public Listing_page_Logic checkSecondGenericApplyingLKW() {
        String secondGenericName = getTextFromGeneric();
        if (secondGenericAboveListing().isDisplayed()) {
            secondGenericAboveListing().click();
        } else if (secondGenericInSidebar().isDisplayed()) {
            secondGenericInSidebar().click();
        }
        waitUntilPreloaderDisappear();
        checkProductTitleOnListing(secondGenericName, true, productTitleInListMode());
        showListingInTileModeButton().click();
        waitUntilPreloaderDisappear();
        checkProductTitleOnListing(secondGenericName, true, productTitleInTileMode());
        if (firstGenericAboveListing().isDisplayed()) {
            firstGenericAboveListing().click();
        } else if (firstGenericInSidebar().isDisplayed()) {
            firstGenericInSidebar().click();
        }
        waitUntilPreloaderDisappear();
        activeGenericsFilter().shouldNotBe(visible);
        checkUniqueGenericsOnListing(1, productTitleInTileMode());
        return this;
    }

    @Step("Check generic filter applying on listing LKW model. Listing_page")
    public Listing_page_Logic checkGenericFilterApplyingLKWmodelRoute() {
        String genericName = firstGenericInSidebar().text();
        firstGenericInSidebar().click();
        waitUntilPreloaderDisappear();
        firstGenericInSidebar().shouldHave(text(genericName));
        checkProductTitleOnListing(genericName, true, productTitleInListMode());
        refresh();
        firstGenericInSidebar().shouldHave(text(genericName));
        checkProductTitleOnListing(genericName, true, productTitleInListMode());
        secondGenericInSidebar().click();
        waitUntilPreloaderDisappear();
        secondGenericInSidebar().click();
        waitUntilPreloaderDisappear();
        checkProductTitleOnListing(genericName, true, productTitleInListMode());
        showListingInTileModeButton().click();
        waitUntilPreloaderDisappear();
        checkProductTitleOnListing(genericName, true, productTitleInTileMode());
        firstGenericInSidebar().click();
        waitUntilPreloaderDisappear();
        checkUniqueGenericsOnListing(2, productTitleInTileMode());
        return this;
    }

    @Step("Click three rating stars in filter. Listing_page")
    public Listing_page_Logic clickThreeRatingStarsInFilter() {
        ratingThreeStarsFilterCheckbox().scrollTo().click();
        return this;
    }

    @Step("Method checks that every product on listing has three stars rating. Listing_page")
    public Listing_page_Logic checkThreeStarsRatingInEveryProductOnListing() {
        for (int i = 1; i < ratingInProductBlock().size(); i++) {
            activeRatingStarsInEveryProductPercent(i).shouldHave(attribute("style", "width: 60%;"));
        }
        return this;
    }

    @Step("Click four rating stars in filter. Listing_page")
    public Listing_page_Logic clickFourRatingStarsInFilter() {
        ratingFourStarsFilterCheckbox().scrollTo().click();
        return this;
    }

    @Step("Method checks that every product on listing has four stars rating. Listing_page")
    public Listing_page_Logic checkFourStarsRatingInEveryProductOnListing() {
        for (int i = 1; i < ratingInProductBlock().size(); i++) {
            activeRatingStarsInEveryProductPercent(i).shouldHave(attribute("style", "width: 80%;"));
        }
        return this;
    }

    @Step("Click five rating stars in filter. Listing_page")
    public Listing_page_Logic clickFiveRatingStarsInFilter() {
        ratingFiveStarsFilterCheckbox().scrollTo().click();
        return this;
    }

    @Step("Method checks that every product on listing has five stars rating. Listing_page")
    public Listing_page_Logic checkFiveStarsRatingInEveryProductOnListing() {
        for (int i = 1; i < ratingInProductBlock().size(); i++) {
            activeRatingStarsInEveryProductPercent(i).shouldHave(attribute("style", "width: 100%;"));
        }
        return this;
    }

    @Step("Refresh page. Listing_page")
    public Listing_page_Logic refreshPage() {
        refresh();
        return this;
    }

    @Step("Check that rating filter is not present on listing. Listing_page")
    public Listing_page_Logic checkRatingfilterIsNotPresent() {
        ratingFilterBlock().shouldNotBe(visible);
        return this;
    }

    @Step("Click info button. Listing_page")
    public Listing_page_Logic clickInfoButton() {
        infoButton().click();
        return this;
    }

    @Step("Close car info popup. Listing_page")
    public Listing_page_Logic closeCarInfoPopup() {
        closeCarInfoPopupButton().click();
        return this;
    }

    @Step("Check info popup visibility. Listing_page")
    public Listing_page_Logic checkInfoPopupVisibility() {
        carInfoPopup().shouldBe(visible);
        return this;
    }

    @Step("Check info popup invisibility. Listing_page")
    public Listing_page_Logic checkInfoPopupInvisibility() {
        carInfoPopup().shouldNotBe(visible);
        return this;
    }

    @Step("Comparing actual and expected characteristics info popup. Listing_page")
    public void compareCharacteristicsPopup() {
        titleInfoPopup().shouldHave(text("Info: TOYOTA Previa / Estima II (XR30) 2.4 (ACR30)"));
        for (int a = 0; a < expectedCharacteristicInfoPopup().size(); a++) {
            characteristicsFromPopup().get(a).shouldHave(text(expectedCharacteristicInfoPopup().get(a)));
        }
    }

    @Step("Expected characteristics info popup. Listing_page")
    private List<String> expectedCharacteristicInfoPopup() {
        List<String> expectedCharacteristics = new ArrayList<>();
        expectedCharacteristics.add("Manuf. year (from - to):\n08.2000-01.2006");
        expectedCharacteristics.add("Power [kW]:\n115");
        expectedCharacteristics.add("Power [hp]:\n156");
        expectedCharacteristics.add("Cylinder capacity (cc):\n2362");
        expectedCharacteristics.add("Engine type:\nPetrol Engine");
        expectedCharacteristics.add("Car body type:\nMPV");
        expectedCharacteristics.add("Manufacturer's model designation:\nMCR3_, ACR3_, CLR3_");
        expectedCharacteristics.add("Brake type:\nDisc Brake");
        expectedCharacteristics.add("Brake system:\nHydraulic");
        expectedCharacteristics.add("Drive:\nFront Wheel Drive");
        expectedCharacteristics.add("ABS:\nwithout ABS");
        expectedCharacteristics.add("Traction control system:\nwithout ARS");
        expectedCharacteristics.add("Engine code:\n2AZ-FE");
        expectedCharacteristics.add("Model year from:\n...");
        expectedCharacteristics.add("Model year to:\n...");
        return expectedCharacteristics;
    }

    @Step("Get expected characteristics of product on listing. Listing_page")
    public List<String> getExpectedCharacteristcsOfProduct() {
        ArrayList<String> expectedCharacteristics = new ArrayList<>();
        expectedCharacteristics.add(firstCharacteristicInFirstProduct().text());
        expectedCharacteristics.add(secondCharacteristicInFirstProduct().text());
        expectedCharacteristics.add(thirdCharacteristicInFirstProduct().text());
        expectedCharacteristics.add(fourthCharacteristicInFirstProduct().text());
        expectedCharacteristics.add(fifthCharacteristicInFirstProduct().text());
        return expectedCharacteristics;
    }

    @Step("Get expected characteristics of product on listing. Listing_page")
    public List<String> getAllExpectedCharacteristcsOfProduct() {
        ArrayList<String> expectedCharacteristics = new ArrayList<>();
        expectedCharacteristics.addAll(mainCharacteristicsOnListing().texts());
        expectedCharacteristics.addAll(additionalCharacteristicsOnListing().texts());
        return expectedCharacteristics;
    }

    @Step("Click first product on listing. Listing_page")
    public Product_page_Logic clickFirstProductOnListing() {
        firstProductTitleOnListing().click();
        return page(Product_page_Logic.class);
    }

    @Step("Get brand name from first product and click brand button in filter. Listing_page")
    public Listing_page_Logic getBrandFronFirstProductAndClickBrandButtonInFilter() {
        while (!brandButtonInFilterWithGivenName(firstProductAdditionalCharacteristicsOnListing().text().split(" ")[0]).is(visible)) {
            nextButtonInBrandFilter().click();
        }
        brandButtonInFilterWithGivenName(firstProductAdditionalCharacteristicsOnListing().text().split(" ")[0]).click();
        waitUntilPreloaderDisappear();
        return this;
    }

    @Step("Check generic and article number in first product. Listing_page")
    public Listing_page_Logic checkGenericAndArticleInFirstProduct(String expectedTextInTitle, String articleNumber) {
        firstProductTitleOnListing().shouldHave(text(expectedTextInTitle));
        productArticleOnListing().shouldHave(text(articleNumber));
        return this;
    }

    @Step("Check generic and OEM number. Listing_page")
    public Listing_page_Logic checkGenericAndArticleAndOEMnumber(String brandName, String genericName, String articleName, String oemHighlightNumber) {
        listProducts().shouldBe(visible);
        productName(brandName).shouldHave(text(genericName));
        productArticle(brandName).shouldHave(text(articleName));
        productOEMnumber(brandName).shouldHave(text(oemHighlightNumber));
        productAddToBasketButton(brandName).click();
        return this;
    }

    @Step("Scroll to element. Listing_page")
    public Listing_page_Logic scrollToElement(SelenideElement element) {
        element.scrollTo();
        return this;
    }

    @Step("Click brand button in filter with given brand name. Listing_page")
    public Listing_page_Logic clickBrandButtonInFilter(String brandName) {
        while (!brandButtonInFilterWithGivenNameLKWRoute(brandName).is(visible)) {
            nextButtonInBrandFilter().click();
        }
        brandButtonInFilterWithGivenNameLKWRoute(brandName).click();
        waitUntilPreloaderDisappear();
        return this;
    }

    @Step("Check generic and OEM number. Listing_page")
    public Product_page_Logic checkGenericAndArticleAndOEMnumberAndGoToProductPage(String brandName, String genericName, String articleName, String oemHighlightNumber) {
        listProducts().shouldBe(visible);
        productName(brandName).shouldHave(text(genericName));
        productArticle(brandName).shouldHave(text(articleName));
        productOEMnumber(brandName).shouldHave(text(oemHighlightNumber));
        productName(brandName).scrollTo().click();
        return page(Product_page_Logic.class);
    }

    @Step("Check listing with selecting brand. Listing_page")
    public Listing_page_Logic checkListingWithSelectingFilterByBrand(String brandID, String brandName) {
        while (!brandsOfBrandBlock(brandID).isDisplayed()) {
            nextButtonInBrandFilter().click();
            sleep(3000);
        }
        brandsOfBrandBlock(brandID).click();
        waitUntilPreloaderDisappear();
        for (int i = 0; i < titleOfProductInListing().size(); i++) {
            String name = titleOfProductInListing().get(i).getText();
            String actualName = name.replace(name.substring(name.indexOf(" ")), "");
            Assert.assertEquals(brandName, actualName);
        }
        return this;
    }

    @Step("Checks the name of the feature state {expectedCharacteristicName} if the article number matches the expected one {expectedArticleNum}. Listing_page")
    public Listing_page_Logic checkNameOfFeatureStateIfArticleNumMatchesExpectedOne(String expectedArticleNum, String expectedCharacteristicName) {
        for (int i = 0; i < productArticlesCollection().size(); i++) {
            String articleNum = productArticlesCollection().get(i).getText().replaceAll("Artikelnummer: ", "");
            if (expectedArticleNum.equals(articleNum)) {
                String characteristicName = characteristicZustand().getText();
                Assert.assertEquals(expectedCharacteristicName, characteristicName);
            }
        }
        return this;
    }

    @Step("Goes to the product page and checks that the name of the characteristic 'Zustand' feature is as expected {expectedCharacteristicName}. Listing_page")
    public Listing_page_Logic goToProductPageAndCheckThatNameOfCharacteristicFeatureIsExpected(String expectedArticleNum, String expectedCharacteristicName) {
        for (int i = 0; i < productArticlesCollection().size(); i++) {
            String articleNum = productArticlesCollection().get(i).getText().replaceAll("Artikelnummer: ", "");
            if (expectedArticleNum.equals(articleNum)) {
                clickFirstProductOnListing()
                        .uncoverCharacteristics();
                String characteristicName = new Product_page().characteristicZustand().getText();
                Assert.assertEquals(expectedCharacteristicName, characteristicName);
                back();
            }
        }
        return this;
    }

    @Step("check installation sidein characteristics of product {expected installation side}. Listing_page")
    public Listing_page_Logic checkInstallationSide(String expectedInstallationSide) {
        String artNumFirstProduct = artNumOfProductGridType().get(0).getText();
        checkPresenceOfInstallationSide(expectedInstallationSide);
        nextPageButton().click();
        artNumOfProductGridType().get(0).shouldNotHave(exactText(artNumFirstProduct));
        checkPresenceOfInstallationSide(expectedInstallationSide);
        return this;
    }

    @Step("check presence of installation side. Listing_page")
    public Listing_page_Logic checkPresenceOfInstallationSide(String expectedInstallationSide) {
        for (int i = 0; i < artNumOfProductGridType().size(); i++) {
            String allCharacteristics = getTextFromUnVisibleElement(allCharacteristicsOfProductInPopUp(i + 1));
            if (!allCharacteristics.contains("vorne")) {
                Assert.assertTrue(allCharacteristics.contains(expectedInstallationSide));
            }
        }
        return this;
    }

    @Step("check Presence Of Unique Products By Side Filter. Listing_page")
    public Listing_page_Logic checkPresenceOfUniqueProductsBySideFilter() {
        Set<String> uniqueBrandSet = einbauseiteProductAttributeTecdocRoute().stream().map(n -> getTextFromUnVisibleElement(n)).collect(Collectors.toSet());
        Assert.assertTrue(uniqueBrandSet.size() >= 2);
        return this;
    }

    @Step("check product by article. Listing_page")
    public Listing_page_Logic checkProductsByArticle(String articleForSearch) {
        productTitleInListMode().filter(have(text(articleForSearch))).shouldHave(sizeNotEqual(0));
        return this;
    }

    @Step("Checking for the absence of the deposit characteristic for goods with a deposit. Listing_page")
    public Listing_page_Logic checkingAbsenceZustandCharacteristicForGoodsWithDeposit() {
        for (int i = 0; i < cardProducts().size(); i++) {
            if (pfandBlockByIndex(i + 1).isDisplayed()) {
                characteristicZustandByIndex(i + 1).shouldNotBe(visible);
            } else if (!pfandBlockByIndex(i + 1).isDisplayed()) {
                characteristicZustandByIndex(i + 1).shouldBe(visible);
            }
        }
        return this;
    }

    @Step("Click the Ersatz Anzeign Button and checking the Displaying Analog Block with pictograms. Listing_page")
    public Listing_page_Logic checkDisplayingAnalogBlock() {
        ersatzAnzeigenButton().click();
        alternativeBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        for (int i = 0; i < mehrButtonListing().size(); i++) {
            String iconInAlternativeBlock = pictogramsAlternativeBlock().get(i).shouldBe(visible).getAttribute("style");
            executeJavaScript("arguments[0].click();", mehrButtonListingOne());
            popUpDangerousListing().waitUntil(visible, 4000).shouldBe(visible);
            popUpDangerousTitle().shouldBe(visible);
            Assert.assertFalse(popUpDangerousTitle().text().isEmpty());
            popUpDangerousText().shouldBe(visible);
            Assert.assertFalse(popUpDangerousText().text().isEmpty());
            String iconInPopUp = iconInPopUpDangerousListing().shouldBe(visible).getAttribute("style");
            Assert.assertEquals(iconInAlternativeBlock, iconInPopUp);
            closePopUpButton().click();
        }
        return this;
    }

    @Step("check work scroll progress bar. Listing_page")
    public Listing_page_Logic checkWorkScrollProgressBar() {
        scrollProgressBar().shouldHave(cssValue("width", "0px"));
        timeBlockFromFooter().scrollIntoView(true).shouldBe(visible);
        scrollProgressBar().shouldHave(cssValue("width", "1903px"));
        logoAutodoc().scrollIntoView(false).shouldBe(visible);
        scrollProgressBar().shouldHave(cssValue("width", "0px"));
        return this;
    }

    @Step("Check presents VAT postscript {expectedVatPostscript} in card products. Listing_page")
    public Listing_page_Logic checkPresentVatPostscriptInCardProduct(String expectedVatPostscript) {
        By vatPostscript = (byCssSelector(".price-block__inkl"));
        for (int i = 0; i < cardProducts().size(); i++) {
            System.out.println(cardProducts().get(i).find(vatPostscript).shouldHave(text(expectedVatPostscript)));
        }
        return this;
    }

    @Step("Wait while first brand become active. Listing_page")
    public Listing_page_Logic waitWhileFirstBrandBecomeActive() {
        firstBrandNameGetData().shouldHave(attribute("data-checked", "true"));
        return this;
    }

    @Step("Wait while second brand become active. Listing_page")
    public Listing_page_Logic waitWhileSecondBrandBecomeActive() {
        secondBrandNameGetData().shouldHave(attribute("data-checked", "true"));
        return this;
    }

    @Step("set Count Filter. Listing_page")
    public Listing_page_Logic setCountFilterByPosition(int position) {
        countFilter().get(position).click();
        return this;
    }

    @Step("check Listing With Selected Filters. Listing_page")
    public Listing_page_Logic checkListingWithSelectedFilters(String brand, String installationSide, String countOfProduct) {
        for (int i = 0; i < cardProducts().size(); i++) {
            titleOfProductInListing().get(i).shouldHave(text(brand));
            valueOfInstallationSideInDescription().get(i).shouldHave(text(installationSide));
            valueOfCountInDescription().get(i).shouldHave(text(countOfProduct));
        }
        return this;
    }

    @Step("checks for the presence of at least one element (Price per Set or Piece) on listing .Listing_page")
    public Listing_page_Logic checkPresenceLeastOneElementPricePerSetOrPiece(String setOrPiece) {
        if (setOrPiece.equals("set")) {
            productInfoPriceForSet().shouldBe(visible);
        }else if (setOrPiece.equals("piece")) {
            productInfoPriceForPiece().shouldBe(visible);
        }
        return this;
    }

    @Step(":from Listing_page" )
    public Listing_page_Logic checkingChangeDisplayProductsAsListAndGrid() {
        new Listing_accessories_page_Logic().checkingChangeDisplayProductsAsListAndGrid();
        return this;
    }

}
