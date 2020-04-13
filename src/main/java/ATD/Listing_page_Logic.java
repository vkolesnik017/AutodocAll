package ATD;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static ATD.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;

public class Listing_page_Logic extends Listing_page {
    //Locators for all list mode listings

    @Step("Gets all the characteristics of the desired product from listing {productArticle}. Listing_page")
    // example String for productArticle = Artikelnummer: V99-75-0011 , don't fits for search listing when used search by article product
    public ElementsCollection getCharacteristicsDesiredProduct(String productArticle) {
        return $$x("//*[text()='" + productArticle + "']/../..//*[@class='about']//li").shouldHave(sizeGreaterThan(10));
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
    private void checkProductAttributeOnListing(String attributeSelectedInSideFilter, ElementsCollection productAttributeOnListing) {
        productAttributeOnListing.shouldHave(sizeGreaterThan(0));
        for (int i = 0; i < productAttributeOnListing.size(); i++) {
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
        ElementsCollection elementsWithArticles = productArticlesInListing().first(12);
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
                characS.get(i).shouldHave(text(attributeSelectedInSideFilter));
                $(".search_button").hover();
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
            if (price.get(i) <= price.get(i + 1)) {
                System.out.println(price.get(i));
            } else {
                Assert.fail("Products are NOT sorted by price in increasing order");
            }
            System.out.println("Products are sorted by price in increasing order");
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
            nextPageButton().click();
            checkProductTitleOnListing(expectedText, true, productTitleInListMode());
        }
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
        blockOfBySideFilters().shouldBe(visible);
        brandFilterBlock().shouldBe(visible);
        paginationFirstBlock().shouldBe(visible);
        return this;
    }

    @Step("Goes to pfand link from listing page. Listing_page")
    public Austauschartikel_static_page clickLinkPfandFromListing() {
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

    @Step("Add procuct to basket and check related products popup on listing. Listing_page")
    public Listing_page_Logic checkRelatedProductsPopupOnListing(int numberCategories) {
        buyButton().click();
        new Product_page_Logic().categoriesInRelatedProductsPopup().shouldHaveSize(numberCategories);
        return this;
    }

    @Step("Wait until preloader disappear. Listing_page")
    public Listing_page_Logic waitUntilPreloaderDisappear() {
        preloader().waitUntil(attribute("style", "display: none;"), 20000);
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

    @Step("Click filter button. Listing_page")
    public Listing_page_Logic clickFilterButton(SelenideElement filterButton) {
        filterButton.click();
        return this;
    }

    @Step("Get text from filter. Listing_page")
    public String getTextFromElement(SelenideElement element) {
        String textFromElement = element.text();
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
        filterButton.hover().click();
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

    @Step("Check text in element. Listing_page")
    public Listing_page_Logic checkTextInElement(SelenideElement element, String expectedText) {
        element.shouldHave(text(expectedText));
        return this;
    }

    @Step("Check visibility of element. Listing_page")
    public Listing_page_Logic checkVisibilityOfElement(SelenideElement element) {
        element.shouldBe(visible);
        return this;
    }

    @Step("Check element is not visibile. Listing_page")
    public Listing_page_Logic checkElementIsNotVisible(SelenideElement element) {
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

    @Step("Check OEN listing elements. Listing_page")
    public Listing_page_Logic checkOENListingElements() {
        titleOfListing().shouldHave(text("Bremsscheibe OE - NUMMER 34116785670"));
        oemNumberBlock().shouldBe(visible);
        oemDescriptionBlock().shouldBe(visible);
        oemAnalogBlock().shouldBe(visible);
        return this;
    }

    @Step("Check Tecdoc listing elements. Listing_page")
    public  Listing_page_Logic checkTecdocListingElements() {
        titleOfListing().shouldHave(text("Aktuelle Angebote zu Ölfilter für VW Golf IV Schrägheck (1J1) 1.4 16V Benzin 75 PS"));
        tecDocBlockOfLinkingCategories().shouldBe(visible);
        return this;
    }

    @Step("Click show more characteristic button. Listing_page")
    public Listing_page_Logic clickMoreCharacteristicInFilter() {
        moreCharacteristicButtonInFilter().click();
        return this;
    }
}

