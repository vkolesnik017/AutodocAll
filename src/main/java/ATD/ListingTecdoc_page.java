package ATD;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ListingTecdoc_page {

    public SelenideElement minPriceValue() { return $(By.xpath("//*[@name='minprice']")); }

    public SelenideElement maxPriceValue() { return $(By.xpath("//*[@name='maxprice']")); }

    public SelenideElement minPriceMapping() { return $(By.xpath("//*[@class='min-price']")); }

    public SelenideElement maxPriceMapping() { return $(By.xpath("//*[@class='max-price']")); }

    public SelenideElement priceFilterSubmitButton() { return $(By.xpath("//*[@class='btn-submit range-price-js ']")); }

    public ElementsCollection priceOfAllProductsOnPageInList() { return $$(By.xpath("//p[@class='actual_price']")); }

    public ElementsCollection priceOfAllProductsOnPageInTile() { return $$(By.xpath("//*[@class='rpp_price']")); }

    public SelenideElement showListingInTileModeButton() { return $(By.xpath("//*[@class='sortby js-change-view-block']/span[3]")); }

    public SelenideElement secondListingPage() { return $(By.xpath("//*[@class='pagination']/span[3]/a")); }

    public SelenideElement resetPriceFilerButton() { return $(By.xpath("//*[@class='btn-reset range-price-reset-js']")); }

    public SelenideElement priceFilterBlock() { return $(By.cssSelector(".js-price-range-filter")); }

    public SelenideElement filterBySide() { return $(By.xpath("//*[@class='installation-side__filter back-side']")); }

    public ElementsCollection sideFilterAttribute() { return $$(By.xpath("//*[@class='subname']")); }

    public SelenideElement filterBySideLKW() { return $(By.xpath("//*[@class='installation-side__filter front-side']"));}

    public SelenideElement gelochtAttribute() { return $(By.xpath("//*[@class='filter-disk sidebar_block_radio js-criteria-filter  js-filter-wrapper js-filter-criteria_232']/div/ul/li[1]")); }

    public SelenideElement filterByBrand() { return $(By.xpath("//*[@class='slick-track']/li[8]/label/img")); }

    public SelenideElement firstBrandInFilterButton() { return $(By.xpath("//*[@class='slick-track']/li[8]/label")); }

    public SelenideElement preloader() { return $(By.cssSelector(".preloader_wrapper")); }

    public SelenideElement firstGeneric() { return $(By.xpath("//div[contains(@class,'filter-generics-tecdoc__list js-filter-generics-tecdoc')]//label[1]/div[2]")); }

    public SelenideElement secondGeneric() { return $(By.xpath("//div[contains(@class,'filter-generics-tecdoc__list js-filter-generics-tecdoc')]//label[2]/div[2]")); }

    public SelenideElement fourthGeneric() { return $(By.xpath("//div[contains(@class,'filter-generics-tecdoc__list js-filter-generics-tecdoc')]//label[4]/div[2]")); }

    public ElementsCollection productTitleInListMode() { return $$(By.cssSelector(".name")); }

    public ElementsCollection productTitleInTileMode() { return $$(By.cssSelector(".rec_prod_title.small_text")); }

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
        for(int i = 0; i < titleViewMode.size()-1; i++) {
            if (shouldHaveTextOrNotHave) {
               titleViewMode.get(i).shouldHave(text(expectedTextInTitle));
            } else {
                titleViewMode.get(i).shouldNotHave(text(expectedTextInTitle));
            }
        }
    }
}
