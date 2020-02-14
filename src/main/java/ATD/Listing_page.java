package ATD;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static ATD.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.or;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;

public class Listing_page {

    //Side filters locators

    public SelenideElement minPriceValue() { return $(By.xpath("//*[@name='minprice']")); }

    public SelenideElement maxPriceValue() { return $(By.xpath("//*[@name='maxprice']")); }

    public SelenideElement minPriceMapping() { return $(By.xpath("//*[@class='min-price']")); }

    public SelenideElement maxPriceMapping() { return $(By.xpath("//*[@class='max-price']")); }

    public SelenideElement priceFilterSubmitButton() { return $(By.xpath("//*[@class='btn-submit range-price-js ']")); }

    public SelenideElement resetPriceFilerButton() { return $(By.xpath("//*[@class='btn-reset range-price-reset-js']")); }

    public SelenideElement priceFilterBlock() { return $(By.cssSelector(".js-price-range-filter")); }

    public SelenideElement gelochtAttribute() { return $(By.xpath("//*[@class='filter-disk sidebar_block_radio js-criteria-filter  js-filter-wrapper js-filter-criteria_232']/div/ul/li[1]")); }

    public SelenideElement produktreiheFilterCheckbox() { return $x("//*[@id='mCSB_4_container']/li[2]"); }

    public SelenideElement produktreiheFilterCheckboxLKW() { return $(".js-filter-criteria_30056 li:nth-child(2) > label"); }

    public SelenideElement produktreiheFilterAttribute() {return $(By.xpath("//*[@id='mCSB_4_container']/li[2]/label")); }

    public SelenideElement langeFilterCheckbox() { return $x("//*[@id='mCSB_2_container']/li[8]"); }

    public SelenideElement langeFilterCheckboxLKW() { return $x("//*[@id='mCSB_2_container']/li[2]/label"); }

    public SelenideElement langeFilterAttribute() { return $x("//*[@id='mCSB_2_container']/li[8]/label"); }

    public SelenideElement wischblattausfuhrungFilterCheckbox() { return $x("//*[@id='mCSB_3_container']/li[1]"); }

    public SelenideElement wischblattausfuhrungFilterAttribute() { return $x("//*[@id='mCSB_3_container']/li[1]/label"); }

    public SelenideElement langeFilterCheckbox2() { return $x("//*[@id='mCSB_2_container']/li[2]"); }

    public SelenideElement langeFilterAttribute2() { return $x("//*[@id='mCSB_2_container']/li[2]/label"); }

    public SelenideElement activeSideFilter() { return $x("//*[@class='filter-disk sidebar_block_radio js-criteria-filter  js-filter-wrapper js-filter-criteria_203']//li[1]/label"); }

    public SelenideElement activeSideFilter2() { return $x("//*[@class='filter-disk sidebar_block_radio js-criteria-filter  js-filter-wrapper js-filter-criteria_203']//li[2]/label"); }

    public SelenideElement activeSideFilterLkw() { return $x("//*[@class='filter-disk sidebar_block_radio js-criteria-filter  js-filter-wrapper js-filter-criteria_339']//ul/li[1]/label"); }

    public SelenideElement activeSideFilterLkwHohe() { return $x("//*[@class='filter-disk sidebar_block_radio js-criteria-filter  js-filter-wrapper js-filter-criteria_339']//ul/li[1]/label"); }

    public SelenideElement activeSideFilterLkwCheckbox() { return $x("//*[@class='filter-disk sidebar_block_radio js-criteria-filter  js-filter-wrapper js-filter-criteria_203']//li[1]"); }

    public SelenideElement activeSideFilterAttributeLkw2() { return $x("//*[@class='filter-disk sidebar_block_radio js-criteria-filter  js-filter-wrapper js-filter-criteria_339']//ul/li[2]"); }

    public SelenideElement hoheThirdSideFilterButton() { return $x("//*[contains(@class,'filter-criteria_209')]//li[3]"); }

    public SelenideElement hoheFirstSideFilterButton() { return $x("//*[contains(@class,'filter-criteria_209')]//li[1]"); }

    public SelenideElement hoheSecondSideFilterButton() { return $x("//*[contains(@class,'filter-criteria_209')]//li[2]"); }

    public SelenideElement oberflacheSideFilterButton() { return $x("//*[contains(@class,'filter-criteria_599')]//li"); }

    public SelenideElement bremsscheibenartSideFilterButton() { return $x("//*[contains(@class,'filter-criteria_232')]//li"); }

    public SelenideElement durchmesserSideFilterButton() { return $x("//*[contains(@class,'filter-criteria_200')]//li[4]"); }

    public SelenideElement durchmesserSideFilterButtonFirstValue() { return $x("//*[contains(@class,'filter-criteria_200')]//li[1]"); }

    public SelenideElement durchmesserSideFilterButtonSecondValue() { return $x("//*[contains(@class,'filter-criteria_200')]//li[2]"); }

    public SelenideElement furprnummerSideFilterButton() { return $x("//*[contains(@class,'filter-criteria_1197')]//li"); }

    public SelenideElement lochanzahlSideFilterButton() { return $x("//*[contains(@class,'filter-criteria_500')]//li"); }

    public SelenideElement carBrandFilterOem() { return $(".model_list_oem > li > label > input"); }

    //For Oem listing
    public SelenideElement firstBrandButtonOemListing() { return $(By.xpath("//*[@id='selected-instalation__slider']/ul/li[1]")); }

    public SelenideElement secondBrandButtonOemListing() { return $(By.xpath("//*[@id='selected-instalation__slider']/ul/li[2]")); }

    public SelenideElement firstBrandNameOemListing() { return $(By.xpath("//*[@id='selected-instalation__slider']/ul/li[1]//img")); }

    public SelenideElement sideFilterOenAttribute() { return $x("//*[@class='model_list_oem']/li[2]/label/a"); }

    public SelenideElement sideFilterOenCheckbox() { return $x("//*[@class='model_list_oem']/li[2]/label"); }

    public SelenideElement sideFilterOenAttribute2() { return $x("//*[@class='model_list_oem']/li[1]/label/a"); }

    public SelenideElement oemNumberBlock() {
        return $(".oem-number");
    }

    public SelenideElement oemDescriptionBlock() {
        return $(".oem-number__desc");
    }

    public SelenideElement oemAnalogBlock() {
        return $(".oem-number__analog");
    }

    public ElementsCollection carBrandApplicabilityAttribute() { return $$x("//*[@class='list_products ']/li//li[1]/a[@class='pkw-oem__link ga-click']"); }

    //Brand filters locators

    public SelenideElement firstBrandNameInFiler() { return $(By.xpath("//*[@class='slick-track']/li[8]/label/img")); }

    public SelenideElement secondBrandNameInFilter() { return $(By.xpath("//*[@class='slick-track']/li[9]/label/img")); }

    public SelenideElement thirdBrandNameInFilter() { return $(By.xpath("//*[@class='slick-track']/li[10]/label/img")); }

    public SelenideElement fourthBrandNameInFilter() { return $(By.xpath("//*[@class='slick-track']/li[11]/label/img")); }

    public SelenideElement fifthBrandNameInFilter() { return $(By.xpath("//*[@class='slick-track']/li[12]/label/img")); }

    public SelenideElement sixthBrandNameInFilter() { return $(By.xpath("//*[@class='slick-track']/li[13]/label/img")); }

    public SelenideElement firstBrandInFilterButton() { return $(By.xpath("//*[@class='slick-track']/li[8]/label")); }

    public SelenideElement secondBrandInFilterButton() { return $(By.xpath("//*[@class='slick-track']/li[9]/label")); }

    public SelenideElement brandFilterBlock() { return $("#selected-instalation__slider"); }

    //By side filters locators

    public SelenideElement blockOfBySideFilters() { // By side
        return $(".installation-side__content");
    }

    public SelenideElement filterBySideBack() { return $(By.xpath("//*[@class='installation-side__filter back-side']")); }

    public SelenideElement filterBySideLKW() { return $(By.xpath("//*[@class='installation-side__filter front-side']")); }

    //By generic filters locators

    public SelenideElement firstGeneric() { return $(By.xpath("//div[contains(@class,'filter-generics-tecdoc__list js-filter-generics-tecdoc')]//label[1]/div[2]")); }

    public SelenideElement secondGeneric() { return $(By.xpath("//div[contains(@class,'filter-generics-tecdoc__list js-filter-generics-tecdoc')]//label[2]/div[2]")); }

    public SelenideElement fourthGeneric() { return $(By.xpath("//div[contains(@class,'filter-generics-tecdoc__list js-filter-generics-tecdoc')]//label[4]/div[2]")); }

    // locators for tecdoc listing
    public SelenideElement tecDocBlockOfLinkingCategories() {
        return $(".teile_catalog");
    }

    public SelenideElement titleOfAdditionalListingForTecDoc() {
        return $(".title_list");
    }

    // this collection finds only products that are in additional listing, suit only for tecdoc listing
    private ElementsCollection imagesProductsInAdditionalListingForTecDoc() {
        return $$x("//*[@class='title_list']/../following-sibling::li//*[@class='image']/span[1]");
    }

    //Locators for tile mode listings

    public ElementsCollection productTitleInTileMode() { return $$(By.cssSelector(".rec_prod_title")); }

    public ElementsCollection priceOfAllProductsOnPageInTile() { return $$(By.xpath("//*[@class='rpp_price']")); }

    public SelenideElement showListingInTileModeButton() { return $(By.xpath("//*[@class='sortby js-change-view-block']/span[3]")); }

    private ElementsCollection articleProductsInTileMode() {
        return $$(".rec_prod_article");
    }

    //Locators for all list mode listings

    @Step("Gets all the characteristics of the desired product from listing {productArticle}")
    // example String for productArticle = Artikelnummer: V99-75-0011 , don't fits for search listing when used search by article product
    public ElementsCollection getCharacteristicsDesiredProduct(String productArticle) {
        return $$x("//*[text()='" + productArticle + "']/../..//*[@class='about']//li").shouldHave(sizeGreaterThan(10));
    }

    public ElementsCollection recoveryCharacteristics() {
        return $$x("//span[@class='rc' and contains(text(),'Wiederaufbereitet')]");
    }

    public SelenideElement pfandBlock() {
        return $(".price_vat_icon p span");
    }

    public SelenideElement listProducts() {
        return $(".list_products ");
    }

    public SelenideElement paginationFirstBlock() {
        return $x("(//*[@class='pagination'])[1]");
    }

    public SelenideElement paginationSecondBlock() {
        return $x("(//*[@class='pagination'])[2]");
    }

    public SelenideElement titleOnListing() {
        return $(".title_count_search");
    }

    public ElementsCollection priceOfAllProductsOnPageInList() { return $$(By.xpath("//p[@class='actual_price']")); }

    public SelenideElement secondListingPage() { return $(By.xpath("//*[@class='pagination']/span[3]/a")); }

    private ElementsCollection sideFilterAttribute() { return $$(By.xpath("//*[@class='subname']")); }

    public SelenideElement preloader() { return $(By.cssSelector(".preloader_wrapper")); }

    public ElementsCollection productTitleInListMode() { return $$(By.cssSelector(".name")); }

    public ElementsCollection produktreiheProductAttributeTecdocRoute() { return $$x("//*[@class='important' and contains(span, 'Produktreihe')]/span[2]"); }

    public ElementsCollection produktreiheProductAttributeTecdocRouteLKW() { return $$x("//span[contains(text(),'Produktreihe:')]/../span[2]"); }

    public ElementsCollection produktreiheProductAttributeGenericRoute() { return $$(By.xpath("//*[@class='w_search no_margin']/preceding-sibling::li//*[@class='important' and contains(span, 'Produktreihe')]/span[2]")); }

    public ElementsCollection produktreiheProductAttributeGenericRouteLKW() { return $$(By.xpath("//*[@class='w_search no_margin']/preceding-sibling::li//span[contains(text(),'Produktreihe:')]/../span[2]")); }

    public SelenideElement productsForOtherCars() { return $x("//*[@class='w_search no_margin']"); }

    public ElementsCollection langeProductAttributeTecdocRoute() { return $$x("//*[@class='important' and contains(span,'Länge [mm]')]/span[2]"); }

    public ElementsCollection langeProductAttributeTecdocRouteLKW() { return $$x("//span[contains(text(),'Länge [mm]:')]/../span[2]"); }

    public ElementsCollection langeProductAttributeGenericRoute() { return $$x("//*[@class='w_search no_margin']/preceding-sibling::li//*[@class='important' and contains(span,'Länge [mm]')]/span[2]"); }

    public ElementsCollection langeProductAttributeGenericRouteLKW() { return $$x("//*[@class='w_search no_margin']/preceding-sibling::li//span[contains(text(),'Länge [mm]:')]/../span[2]"); }

    public ElementsCollection wischblattausfuhrungProductAttributeTecdocRoute() { return $$x("//*[@class='important' and contains(span,'Wischblattausführung')]/span[2]"); }

    public ElementsCollection wischblattausfuhrungProductAttributeGenericRoute() { return $$x("//*[@class='w_search no_margin']/preceding-sibling::li//*[@class='important' and contains(span,'Wischblattausführung')]/span[2]"); }

    public ElementsCollection hoheProductAttributeTecdocRoute() { return $$x("//*[contains(text(),'Höhe [mm]:')]/ancestor :: li[1]/span[2]"); }

    public ElementsCollection hoheProductAttributeGenericRoute() { return $$x("//*[@class='w_search no_margin']/preceding-sibling::li//*[contains(text(),'Höhe [mm]:')]/ancestor :: li[1]/span[2]"); }

    public ElementsCollection hoheProductAttributeTecdocRouteLKW() { return $$x("//*[contains(text(),'Höhe 1 [mm]:')]/ancestor :: li[1]/span[2]"); }

    public ElementsCollection hoheProductAttributeGenericRouteLKW() { return $$x("//*[@class='w_search no_margin']/preceding-sibling::li//*[contains(text(),'Höhe 1 [mm]:')]/ancestor :: li[1]/span[2]"); }

    public ElementsCollection einbauseiteProductAttributeTecdocRoute() {return $$x("//*[contains(text(),'Einbauseite:')]/ancestor :: li[1]/span[2]"); }

    public ElementsCollection einbauseiteProductAttributeGenericRoute() {return $$x("//*[@class='w_search no_margin']/preceding-sibling::li//*[contains(text(),'Einbauseite:')]/ancestor :: li[1]/span[2]"); }

    public ElementsCollection oberflacheProductAttributeTecdocRoute() { return $$x("//*[contains(text(),'Oberfläche:')]/ancestor :: li[1]/span[2]"); }

    public ElementsCollection oberflacheProductAttributeGenericRoute() { return $$x("//*[@class='w_search no_margin']/preceding-sibling::li//*[contains(text(),'Oberfläche:')]/ancestor :: li[1]/span[2]"); }

    public ElementsCollection bremsscheibenartProductAttributeTecdocRoute() { return $$x("//*[contains(text(),'Bremsscheibenart:')]/ancestor :: li[1]/span[2]"); }

    public ElementsCollection bremsscheibenartProductAttributeGenericRoute() { return $$x("//*[@class='w_search no_margin']/preceding-sibling::li//*[contains(text(),'Bremsscheibenart:')]/ancestor :: li[1]/span[2]"); }

    public ElementsCollection durchmesserProductAttributeTecdocRoute() { return $$x("//*[contains(text(),'Durchmesser [mm]:')]/ancestor :: li[1]/span[2]"); }

    public ElementsCollection durchmesserProductAttributeGenericRoute() { return $$x("//*[@class='w_search no_margin']/preceding-sibling::li//*[contains(text(),'Durchmesser [mm]:')]/ancestor :: li[1]/span[2]"); }

    public ElementsCollection furprnummerProductAttributeTecdocRoute() { return $$x("//*[contains(text(),'für PR-Nummer:')]/ancestor :: li[1]/span[2]"); }

    public ElementsCollection furprnummerProductAttributeGenericRoute() { return $$x("//*[@class='w_search no_margin']/preceding-sibling::li//*[contains(text(),'für PR-Nummer:')]/ancestor :: li[1]/span[2]"); }

    public ElementsCollection lochanzahlProductAttributeTecdocRoute() { return $$x("//*[contains(text(),'Lochanzahl:')]/ancestor :: li[1]/span[2]"); }

    public ElementsCollection lochanzahlProductAttributeGenericRoute() { return $$x("//*[@class='w_search no_margin']/preceding-sibling::li//*[contains(text(),'Lochanzahl:')]/ancestor :: li[1]/span[2]"); }

    public SelenideElement nextPageButton() { return $(".pagination > .next"); }

    public SelenideElement grayButton() { return $x("//*[contains(@class,'not_active')]/a"); }

    private ElementsCollection addToBasketButtons() { return $$x("//*[@class='add_info']/div[2]"); }

    private ElementsCollection redButtons() { return $$x("//*[@class='button ']"); }

    public ElementsCollection vorderachseAttributeInTileMode() { return $$x("//*[@class='product_desc_table_container']//*[contains(text(),'Vorderachse')]"); }

    public ElementsCollection vorderachseAttributeInListMode() { return $$x("//div[@class='about']//*[contains(text(),'Vorderachse')]"); }

    public ElementsCollection productsOnListingInTileMode() { return $$(".rec_products_block"); }

    public ElementsCollection productsOnListingInListMode() { return $$(".description"); }

    @Step("Method gets price of all products on listing and parse it into float")
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

    @Step("Method checks if prices on listing are in range {maxPriceToCheck} - {maxPriceToCheck} of price filter")
    public void checkPricesRange(Float minPriceToCheck, Float maxPriceToCheck, ElementsCollection listingViewModeLocator) {
        List<Float> price = getAllPricesOnListingPage(listingViewModeLocator);
        for (int i = 0; i < price.size()-1; i++) {
            if(price.get(i) >= minPriceToCheck && price.get(i) <= maxPriceToCheck) {
            } else {
                Assert.fail("Prices on listing are NOT in range of price filter. Range: " + minPriceToCheck + " - " + maxPriceToCheck);
            }
        }
        System.out.println("Prices on listing are in range of price filter. Range: " + minPriceToCheck + " - " + maxPriceToCheck);
    }

    @Step("Method checks that filter by side applied to products on listing")
    public void checkFilterBySide(String attributeText) {
        for(int i = 0; i < sideFilterAttribute().size()-1; i++) {
            sideFilterAttribute().get(i).shouldHave(text(attributeText));
        }
    }

    @Step("Method checks that expected text is present in title of all products on listing")
    public void checkProductTitleOnListing(String expectedTextInTitle, Boolean shouldHaveTextOrNotHave, ElementsCollection titleViewMode) {
        titleViewMode.shouldHave(sizeGreaterThan(0));
        for(int i = 0; i < titleViewMode.size(); i++) {
            if (shouldHaveTextOrNotHave) {
               titleViewMode.get(i).shouldHave(text(expectedTextInTitle));
            } else {
                titleViewMode.get(i).shouldNotHave(text(expectedTextInTitle));
            }
        }
    }

    @Step("Method checks that expected text is present in title of all products on listing with two conditions")
    public void checkProductTitleOnListingWithTwoExpectedTexts(String expectedTextInTitle, String secondExpText, Boolean shouldHaveTextOrNotHave, ElementsCollection titleViewMode) {
        titleViewMode.shouldHave(sizeGreaterThan(0));
        for (int i = 0; i < titleViewMode.size(); i++) {
            if (shouldHaveTextOrNotHave) {
                titleViewMode.get(i).shouldHave(or("condition", text(expectedTextInTitle), text(secondExpText)));
            } else {
                titleViewMode.get(i).shouldNotHave(or("condition", text(expectedTextInTitle), text(secondExpText)));
            }
        }
    }

    @Step("Method checks that expected text is present in title of all products on listing with six conditions")
    public void checkProductTitleOnListingWithSixExpectedTexts(String expectedTextInTitle, String secondExpText, String thirdExpText, String fourthExpText, String fifthExpText,
                                                               String sixthExpText, Boolean shouldHaveTextOrNotHave, ElementsCollection titleViewMode) {
        titleViewMode.shouldHave(sizeGreaterThan(0));
        for(int i = 0; i < titleViewMode.size(); i++) {
            if (shouldHaveTextOrNotHave) {
                titleViewMode.get(i).shouldHave(or("condition", text(expectedTextInTitle), text(secondExpText), text(thirdExpText), text(fourthExpText), text(fifthExpText), text(sixthExpText)));
            } else {
                titleViewMode.get(i).shouldNotHave(or("condition", text(expectedTextInTitle), text(secondExpText), text(thirdExpText)));
            }
        }
    }

    @Step("Method checks unique brands on listing")
    public void checkUniqueBrandsOnListing(int numberOfUniqueBrands, ElementsCollection titleViewMode) {
        titleViewMode.shouldHave(sizeGreaterThan(0));
        Set<String> uniqueBrandSet = new LinkedHashSet<>();
        for (SelenideElement aTitleViewMode : titleViewMode) {
            String brandName = aTitleViewMode.text().split(" ")[0];
            uniqueBrandSet.add(brandName);
        }
        Assert.assertTrue(uniqueBrandSet.size() >= numberOfUniqueBrands);
    }

    @Step("Method gets brand from product title")
    public void getBrandFromTitle(String expectedTextInTitle, int brandPositionInAlt, Boolean shouldHaveTextOrNotHave, ElementsCollection titleViewMode) {
        titleViewMode.shouldHave(sizeGreaterThan(0));
        for(int i = 0; i < titleViewMode.size(); i++) {
            if (shouldHaveTextOrNotHave) {
                titleViewMode.get(i).shouldHave(text(expectedTextInTitle.split(" ")[brandPositionInAlt]));
            } else {
                titleViewMode.get(i).shouldNotHave(text(expectedTextInTitle.split(" ")[brandPositionInAlt]));
            }
        }
    }

    @Step("Method checks product attribute on listing")
    private void checkProductAttributeOnListing(String attributeSelectedInSideFilter, ElementsCollection productAttributeOnListing) {
        productAttributeOnListing.shouldHave(sizeGreaterThan(0));
        for (int i = 0; i < productAttributeOnListing.size(); i++) {
            productAttributeOnListing.get(i).shouldHave(text(attributeSelectedInSideFilter));
        }
    }

    @Step("Method checks product attribute on listing with chosen car and filter (without products for other cars)")
    public void checkProductAttributeOnListingWithCarAndFilter(String characteristic, ElementsCollection productAttributeGenericRoute, ElementsCollection productAttributeTecdocRoute) {
        if(productsForOtherCars().is(visible)) {
            checkProductAttributeOnListing(characteristic, productAttributeGenericRoute);
        } else {
            checkProductAttributeOnListing(characteristic, productAttributeTecdocRoute);
        }
    }

    @Step("Method checks product attribute on listing in tile mode")
    public void checkProductAttributeOnListingInTileMode(String attributeSelectedInSideFilter, ElementsCollection productAttributeOnListing) {
        productAttributeOnListing.shouldHave(sizeGreaterThan(0));
        for (int i = 0; i < productAttributeOnListing.size(); i++) {
            productsOnListingInTileMode().get(i).hover();
            productAttributeOnListing.get(i).shouldHave(text(attributeSelectedInSideFilter));
            showListingInTileModeButton().hover();
        }
    }

    @Step("The method checks that products at listing  are fits for chosen car. The method clicking on every each product and on the product page checks the text in the selected car's information block.")
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

    @Step("The method saves the order of products on listing, switches to tile view and verifies that order of products remains the same")
    public void compareProductsOrderBetweenListModeAndTileMode() {
        ElementsCollection elementsWithArticles = productTitleInListMode().first(12);
        ArrayList<String> articlesInListMode = new ArrayList<>();
        for (SelenideElement nameProduct : elementsWithArticles) {
            String name = nameProduct.text().split("\n")[2].split(": ")[1];
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
    }

    @Step("Method checks product attribute on listing in tile mode")
    public void checkProductAttributeOnListingInTileMode2(String attributeSelectedInSideFilter, int productWithFiler) {
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
    }

    @Step("Method checks product attribute on OEM listing")
    public void checkProductCharacteristicOnListingOem(String attributeSelectedInSideFilter, ElementsCollection productAttributeOnListing) {
        for (int i = 0; i < $$(".list_products > li").size(); i++) {
            $$(".product-params-oem").get(i).click();
            productAttributeOnListing.get(i).shouldHave(text(attributeSelectedInSideFilter));
            System.out.println(productAttributeOnListing.get(i).text());
        }
    }

    @Step("Method checks add to basket buttons sorting on listing")
    public void checkAddToBasketButtonsSorting() {
        for (int i = 0; i < addToBasketButtons().size()-1; i++) {
            if (addToBasketButtons().get(i).text().contains("VERFÜGBARKEIT")) {
                addToBasketButtons().get(i+1).shouldHave(text("VERFÜGBARKEIT"));
            }
        }
    }

    @Step("Method checks products sorting on listing in increasing order for RIDEX products")
    public void checkPriceSortingInIncreasingOrderRidex(ElementsCollection listingViewModeLocator) {
        List<Float> price = getAllPricesOnListingPage(listingViewModeLocator);
        ElementsCollection ridexProducts = $$x("//*[@class='name']/a[contains (text(),'RIDEX')]");
        for (int i = 0; i < ridexProducts.size()-1; i++) {
                if (price.get(i) <= price.get(i + 1)) {
                    System.out.println(price.get(i));
                } else {
                    Assert.fail("Products are NOT sorted by price in increasing order");
            }
            System.out.println("Products are sorted by price in increasing order");
        }
    }

    @Step("Method checks products sorting on listing in increasing order for not RIDEX products with one generic")
    public void checkPriceSortingInIncreasingOrderNotRidex(ElementsCollection listingViewModeLocator) {
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
    }

    @Step("Method checks products sorting on listing in increasing order for not RIDEX products with two generics")
    public void checkPriceSortingInIncreasingOrderNotRidex2generic(ElementsCollection listingViewModeLocator) {
        List<Float> price = getAllPricesOnListingPage(listingViewModeLocator);
        ElementsCollection notHalterProducts = $$x("//*[@class='name']/a[not (contains (text(),'Halter'))]");
        ElementsCollection halterProducts = $$x("//*[@class='name']/a[contains (text(),'Halter')]");
        if ($$x("//*[contains(@class,'not_active')]/a").size() < 1) {
            if(halterProducts.size() < 1) {
                for (int i = halterProducts.size(); i < getAllPricesOnListingPage(listingViewModeLocator).size() - 1; i++) {
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
    }

    // The method can check everything that is contained in the product title by going through all the pages. Fits for all listing (TecDoc, Search, OEN etc...)
    @Step("The method verifies that the product titles on listing contain the expected text {expectedText}. Verification works for all the pagination by switching pages one by one")
    public Listing_page checksProductTitlesContainExpectedTextGoingAllPagination(String expectedText) {
        checkProductTitleOnListing(expectedText, true, productTitleInListMode());
        while (nextPageButton().is(visible)) {
            nextPageButton().click();
            checkProductTitleOnListing(expectedText, true, productTitleInListMode());
        }
        return this;
    }
}
