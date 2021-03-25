package PKW;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;


public class Listing_page {

    SelenideElement searchPageTitle() {
        return $x("//div[@class='search-page__title-wrapper']");
    }

    public SelenideElement preloader() {
        return $x("//body/div[@class='preloader_wrapper']");
    }

    SelenideElement moreBtnInBrandBloc() {
        return $x("//a[@data-less='Schließen']");
    }

    SelenideElement brandsOfBrandBlock(String idOfBrand) {
        return $x("//input[@id='cb-brand-" + idOfBrand + "']/..");
    }

    ElementsCollection titleOfProductInListing() {
        return $$x("//div[@class='description']/*[self::a or self::span][1]");
    }

    ElementsCollection productArticlesInListing() {
        return $$x("//div[@class='nr']/span[1]");
    }

    SelenideElement moreBtnInProduct() {
        return $x("//span[@class='show-full-product-params-text']");
    }

    SelenideElement nameProductCharacteristicINParametersBlock(String nameCharacteristic) {
        return $x("//span[contains(@class,'rc')][contains(text(),'" + nameCharacteristic + "')]");
    }

    SelenideElement firstProductTitleOnListing() {
        return $x("(//div[@class='description']//a)[1]");
    }
}
