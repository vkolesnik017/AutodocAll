package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Listing_instruments_page {

    SelenideElement titleNameCategory() {
        return $x("//h2[@class='title_count_search']");
    }

    SelenideElement titleNameProductFromListing() {
        return $x("//div[@class='js-listing-wrap']//div[@class='name']//*[contains(@class,'ga-click')]");
    }

    SelenideElement seoTextBlock() {
        return $x("//div[@class='block_youtube_video']");
    }

    SelenideElement btnReturnOnFirstPageInPagination() {
        return $x("//div[@class='page_list'][1]//span[@class='first']");
    }

    SelenideElement btnPreviousPageInPagination() {
        return $x("//div[@class='page_list'][1]//span[contains(@class,'previous')]");
    }

    SelenideElement btnNextPageInPagination() {
        return $x("//div[@class='page_list'][1]//span[@class='next'][1]");
    }

    SelenideElement btnLastPageInPagination() {
        return $x("//div[@class='page_list'][1]//span[@class='next'][2]");
    }

    SelenideElement btnSecondPageInPagination() {
        return $x("//div[@class='page_list'][1]//span[3]");
    }

    SelenideElement topPaginationBlock() {
        return $x("//div[@class='page_list'][1]/div[@class='pagination']");
    }

    SelenideElement lowerPaginationBlock() {
        return $x("//div[@class='page_list'][2]/div[@class='pagination']");
    }

    SelenideElement productsCountBlock() {
        return $x("//div[@class='product_count']");
    }

    SelenideElement blockChangePositionProductsOnListOrGrid() {
        return $x("//div[@class='filter_select ']");
    }

    SelenideElement btnChangePositionProductsAsList() {
        return $x("//div[@class='filter_select ']//span[@class='list']");
    }

    SelenideElement btnChangePositionProductsAsGrid() {
        return $x("//div[@class='filter_select ']//span[@class='tile']");
    }

    SelenideElement listingProductsDisplayedAsList() {
        return $x("//div[@class='listing-wrap']//ul[@class='list_products ']");
    }

    SelenideElement listingProductsDisplayedAsGrid() {
        return $x("//div[@class='listing-wrap']//div[@class='sub_catalog_grid']");
    }

    SelenideElement firstBreadCrumb() {
        return $x("//div[@class='steps breadcrumbs']//li[@class='step_1 active parts_step_1']");
    }

    SelenideElement secondBreadCrumb() {
        return $x("//div[@class='steps breadcrumbs']//li[@class='step_2 active parts_step_2']");
    }

    SelenideElement thirdBreadCrumb() {
        return $x("//div[@class='steps breadcrumbs']//li[@class='step_3 active parts_step_3']//a");
    }

    SelenideElement blockBreadCrumbs() {
        return $x("//div[@class='steps breadcrumbs']");
    }

    SelenideElement blockMainProducts() {
        return $x("//ul[@class='list_products ']");
    }

    ElementsCollection mainProductsList() {
        return $$x("//ul[@class='list_products ']/li");
    }


    SelenideElement idProductInBtnAddBasket() {
        return $x("//div[@class='button ']");
    }

    SelenideElement counterValueInQuantityCounter() {
        return $x("//li[@data-id='0'][1]//div[@class='count']/input");
    }

    SelenideElement btnPlusInQuantityCounter() {
        return $x("//li[@data-id='0'][1]//a[@class='ga-click plus add']");
    }

    SelenideElement btnMinusInQuantityCounter() {
        return $x("//li[@data-id='0'][1]//a[@class='ga-click minus remove']");
    }

    SelenideElement redBtnAddToBasket() {
        return $x("//div[@class='button ']//a");
    }

    SelenideElement popupBasketAddedProducts() {
        return $x("//div[@class='cart-items-block ']");
    }

}
