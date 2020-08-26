package PKW;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;


public class Listing_instruments_Page {

    SelenideElement titleCategory() {
        return $x("//h1/span");
    }

    SelenideElement firstBrandInBrandsBlock() {
        return $x("//div[contains(@class,'listing-brand-filters__brand')][1]");
    }

    SelenideElement thirdBrandInBrandsBlock() {
        return $x("//div[contains(@class,'listing-brand-filters__brand')][3]");
    }

    SelenideElement firstBrandInBrandsBlockImg() {
        return $x("//div[@class='listing-brand-filters__list-brands delay-overflow']//div[1]//img");
    }

    SelenideElement thirdBrandInBrandsBlockImg() {
        return $x("//div[@class='listing-brand-filters__list-brands delay-overflow']//div[3]//img");
    }

    SelenideElement brandsBlock() {
        return $x("//div[@class='listing-brand-filters__list-brands delay-overflow']");
    }

    ElementsCollection titleNameProductsFromListing() {
        return $$x("//div[@class='brand-products']//*[contains(@class,'ga-click prod_link')]");
    }

    SelenideElement btnMoreInBrandsBlock() {
        return $x("//div[contains(@class,'listing-brand-filters_more-link')]/a[@class='more_link js-listing-brand-filters']");
    }

    SelenideElement btnLessInBrandsBlock() {
        return $x("//div[contains(@class,'listing-brand-filters_more-link')]/a[@class='more_link js-listing-brand-filters less_link']");
    }

}
