package PKW;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Listing_page {

    SelenideElement addToCartBtnForSpecificItem(String productID) {
        return $x("//div[@id='" + productID + "']/a[contains(@onclick,('AddToCart'))]");
    }

    SelenideElement searchPageTitle() {
        return $x("//div[@class='search-page__title-wrapper']");
    }

    public SelenideElement preloader() {
        return $x("//body/div[@class='preloader_wrapper']");
    }

    SelenideElement listProductBlock() {
        return $x("//div[@class='listing_items']");
    }

    ElementsCollection activeBtnAddToBasket() {
        return $$x("//div[@class='basket_btn button active_red_button ']");
    }

    ElementsCollection titleOfProductIOnListing() {
        return $$x("//div[@class='prod_link']/*[self::a or self::span]");
    }

    ElementsCollection priceOfProduct() {
        return $$x("//div[@class='price']");
    }

    ElementsCollection btnAddToBasketWithBrand(String brand) {
        return $$x("//div[@class='basket_btn button active_red_button '][@data-brand-name='" + brand + "']");
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

    //Filters locators (Side/Criteria/Brand)

    SelenideElement blockOfBrandFilters() {
        return $x("//div[@class='listing-brand-filters__list-brands brand-filters-selected-brand delay-overflow']");
    }

    SelenideElement blockOfBrandFiltersSearchListing() {
        return $x("//div[@class='listing-brand-filters__list-brands delay-overflow']");
    }

    SelenideElement blockOfBySideFilters() { // By side installation TecDoc
        return $x("//div[@data-filter-id='criteria_100']/div[@class='installation-side__content']");
    }

    SelenideElement blockOfBySideFiltersSearchListing() { // By side installation Search listing
        return $x("//div[@class='installation-side__content']");
    }

    SelenideElement blockOfBySideFiltersSecond() { // By second criteria side of installation
        return $x("//div[@class='installation-side__wrap slick-slide slick-current slick-active']/div[1]");
    }

    SelenideElement blockOfPagination() {
        return $x("//div[@class='pagination']");
    }

    public SelenideElement showListingInTableModeButton() {
        return $x("//*[@class='change_view_block js-change-view-block']/span[2]");
    }

    public SelenideElement showListingInGridModeButton() {
        return $x("//*[@class='change_view_block js-change-view-block']/span[3]");
    }

    //Locators for Table mode listings

    ElementsCollection articleProductsInTableMode() {
        return $$x("//td[@class='ab']");
    }

    //Locators for Grid mode listings

    ElementsCollection articleProductsInGridMode() {
        return $$x("//span[@class='pkw-related__item-art']");
    }

}