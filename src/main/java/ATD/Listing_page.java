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

import static com.codeborne.selenide.Condition.or;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

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

    public SelenideElement produktreiheFilterCheckbox() { return $x("//*[@id='mCSB_2_container']/li[2]"); }

    public SelenideElement produktreiheFilterAttribute() {return $(By.xpath("//*[@id='mCSB_2_container']/li[2]/label")); }

    public SelenideElement langeFilterCheckbox() { return $x("//*[@id='mCSB_2_container']/li[8]"); }

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

    // locator for search listing

    public SelenideElement titleOfSearchListing() {
        return $(".title_count_search");
    }

    public SelenideElement blockOfHelpSearchProducts() {
        return $(".filter-not-found__title");
    }

    public SelenideElement blockOfLinkingCategory() {
        return $(".sidebar-category");
    }

    //For Oem listing
    public SelenideElement firstBrandButtonOemListing() { return $(By.xpath("//*[@id='selected-instalation__slider']/ul/li[1]")); }

    public SelenideElement secondBrandButtonOemListing() { return $(By.xpath("//*[@id='selected-instalation__slider']/ul/li[2]")); }

    public SelenideElement firstBrandNameOemListing() { return $(By.xpath("//*[@id='selected-instalation__slider']/ul/li[1]//img")); }

    public SelenideElement sideFilterOenAttribute() { return $x("//*[@class='model_list_oem']/li[2]/label/a"); }

    public SelenideElement sideFilterOenCheckbox() { return $x("//*[@class='model_list_oem']/li[2]/label"); }

    public SelenideElement sideFilterOenAttribute2() { return $x("//*[@class='model_list_oem']/li[1]/label/a"); }

    public SelenideElement titleOnOemListing() {
        return $(".title_count_search>h2");
    }

    public SelenideElement oemNumberBlock() {
        return $(".oem-number");
    }

    public SelenideElement oemDescriptionBlock() {
        return $(".oem-number__desc");
    }

    public SelenideElement oemAnalogBlock() {
        return $(".oem-number__analog");
    }

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

    public SelenideElement filterBySideBack() { return $(By.xpath("//*[@class='installation-side__filter back-side']")); }

    public SelenideElement filterBySideLKW() { return $(By.xpath("//*[@class='installation-side__filter front-side']")); }

    //By generic filters locators

    public SelenideElement firstGeneric() { return $(By.xpath("//div[contains(@class,'filter-generics-tecdoc__list js-filter-generics-tecdoc')]//label[1]/div[2]")); }

    public SelenideElement secondGeneric() { return $(By.xpath("//div[contains(@class,'filter-generics-tecdoc__list js-filter-generics-tecdoc')]//label[2]/div[2]")); }

    public SelenideElement fourthGeneric() { return $(By.xpath("//div[contains(@class,'filter-generics-tecdoc__list js-filter-generics-tecdoc')]//label[4]/div[2]")); }

    //Locators for tile mode listings

    public ElementsCollection productTitleInTileMode() { return $$(By.cssSelector(".rec_prod_title.small_text")); }

    public ElementsCollection priceOfAllProductsOnPageInTile() { return $$(By.xpath("//*[@class='rpp_price']")); }

    public SelenideElement showListingInTileModeButton() { return $(By.xpath("//*[@class='sortby js-change-view-block']/span[3]")); }

    //Locators for all listings

    public ElementsCollection priceOfAllProductsOnPageInList() { return $$(By.xpath("//p[@class='actual_price']")); }

    public SelenideElement secondListingPage() { return $(By.xpath("//*[@class='pagination']/span[3]/a")); }

    public ElementsCollection sideFilterAttribute() { return $$(By.xpath("//*[@class='subname']")); }

    public SelenideElement preloader() { return $(By.cssSelector(".preloader_wrapper")); }

    public ElementsCollection productTitleInListMode() { return $$(By.cssSelector(".name")); }

    public ElementsCollection produktreiheProductAttributeTecdocRoute() { return $$x("//*[@class='important' and contains(span, 'Produktreihe')]/span[2]"); }

    public ElementsCollection produktreiheProductAttributeGenericRoute() { return $$(By.xpath("//*[@class='w_search no_margin']/preceding-sibling::li//*[@class='important' and contains(span, 'Produktreihe')]/span[2]")); }

    public SelenideElement productsForOtherCars() { return $x("//*[@class='w_search no_margin']"); }

    public ElementsCollection langeProductAttributeTecdocRoute() { return $$x("//*[@class='important' and contains(span,'Länge [mm]')]/span[2]"); }

    public ElementsCollection langeProductAttributeGenericRoute() { return $$x("//*[@class='w_search no_margin']/preceding-sibling::li//*[@class='important' and contains(span,'Länge [mm]')]/span[2]"); }

    public ElementsCollection verschleißwarnkontaktProductAttributeTecdocRoute() { return $$x("//*[@class='important' and contains(span,'Verschleißwarnkontakt:')]/span[2]"); }

    public ElementsCollection verschleißwarnkontaktProductAttributeGenericRoute() { return $$x("//*[@class='w_search no_margin']/preceding-sibling::li//*[@class='important' and contains(span,'Verschleißwarnkontakt:')]/span[2]"); }

    public ElementsCollection wischblattausfuhrungProductAttributeTecdocRoute() { return $$x("//*[@class='important' and contains(span,'Wischblattausführung')]/span[2]"); }

    public ElementsCollection wischblattausfuhrungProductAttributeGenericRoute() { return $$x("//*[@class='w_search no_margin']/preceding-sibling::li//*[@class='important' and contains(span,'Wischblattausführung')]/span[2]"); }

    public ElementsCollection hoheProductAttributeTecdocRoute() { return $$x("//*[@class='important' and contains(span,'Höhe 1 [mm]:')]/span[2]"); }

    public ElementsCollection hoheProductAttributeGenericRoute() { return $$x("//*[@class='w_search no_margin']/preceding-sibling::li//*[@class='important' and contains(span,'Höhe 1 [mm]:')]/span[2]"); }

    public ElementsCollection einbauseiteProductAttributeTecdocRoute() {return $$x("//*[@class='important' and contains(span, 'Einbauseite:')]/span[2]"); }

    public ElementsCollection einbauseiteProductAttributeGenericRoute() {return $$x("//*[@class='w_search no_margin']/preceding-sibling::li//*[@class='important' and contains(span, 'Einbauseite:')]/span[2]"); }

    public SelenideElement closeBtnPopupOfChooseCar() {
        return $(".back");
    }

    @Step("Method gets price of all products on listing and parse it into float")
    public List<Float> getAllPricesOnListingPage(ElementsCollection listingViewModeLocator) {
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
        Set<String> uniqueBrandSet = new LinkedHashSet<>();
        for (SelenideElement aTitleViewMode : titleViewMode) {
            String brandName = aTitleViewMode.text().split(" ")[0];
            uniqueBrandSet.add(brandName);
        }
        Assert.assertTrue(uniqueBrandSet.size() >= numberOfUniqueBrands);
    }

    @Step("Method gets brand from product title")
    public void getBrandFromTitle(String expectedTextInTitle, int brandPositionInAlt, Boolean shouldHaveTextOrNotHave, ElementsCollection titleViewMode) {
        for(int i = 0; i < titleViewMode.size(); i++) {
            if (shouldHaveTextOrNotHave) {
                titleViewMode.get(i).shouldHave(text(expectedTextInTitle.split(" ")[brandPositionInAlt]));
            } else {
                titleViewMode.get(i).shouldNotHave(text(expectedTextInTitle.split(" ")[brandPositionInAlt]));
            }
        }
    }

    @Step("Method checks product attribute on listing")
    public void checkProductAttributeOnListing(String attributeSelectedInSideFilter, ElementsCollection productAttributeOnListing) {
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
        for (int i = 0; i < productAttributeOnListing.size(); i++) {
            $$(".rec_products_block").get(i).hover();
            productAttributeOnListing.get(i).shouldHave(text(attributeSelectedInSideFilter));
            langeFilterCheckbox().hover();
        }
    }
}
