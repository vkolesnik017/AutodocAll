package ATD;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

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

    public void checkPricesRange(Float minPriceToCheck, Float maxPriceToCheck, ElementsCollection listingViewModeLocator) {
        List<Float> price = getAllPricesOnListingPage(listingViewModeLocator);
        for (int i = 0; i < price.size()-1; i++) {
            if(price.get(i) >= minPriceToCheck && price.get(i) <= maxPriceToCheck) {
            } else {
                Assert.fail("Prices on listing are NOT in range of price filter");
            }
        }
        System.out.println("Prices on listing are in range of price filter");
    }
}
