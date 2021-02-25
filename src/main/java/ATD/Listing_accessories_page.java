package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;



public class Listing_accessories_page {

    SelenideElement logoAutodoc() {
        return $x("//a[@class='header__logo-main']");
    }

    SelenideElement listingTitle() {
        return $x("//div[@class='cont']//h2[text()]");
    }

    SelenideElement selectedBrand() {
        return $x("//div[@id='selected-instalation__slider']//li[@class='active slick-slide slick-current slick-active']");
    }

    SelenideElement titleCategoriesBlockInSidebar() {
        return $x("//div[@class='block categories blue topSubCats']//b[text()]");
    }

    SelenideElement firstCategoryInSidebar() {
        return $x("//div[@class='block categories blue topSubCats']//li[1]//span[text()]");
    }

    ElementsCollection categoriesInSidebar() {
        return $$x("//div[@class='block categories blue topSubCats']/ul[@class='filetree cat_tree treeview']/li");
    }

    SelenideElement blockProductQuantity () {
        return $x("//div[@class='product_count']//span[@class='js-products-qty']");
    }

    SelenideElement blockChangePositionProductsOnListOrGrid() {
        return $x("//div[@class='filter_select ']");
    }

    SelenideElement btnChangePositionProductsAsGrid() {
        return $x("//div[@class='filter_select ']//span[@url][2]");
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

    public SelenideElement fourthBreadCrumb() {
        return $x("//div[@class='steps breadcrumbs']//li[@class='step_4 active parts_step_4']//a");
    }

    public SelenideElement fiveBreadCrumb() {
        return $x("//div[@class='steps breadcrumbs']//li[@class='step_5 active parts_step_5']//a");
    }

    SelenideElement breadcrumbsBlock() {
        return $x("//div[@class='steps breadcrumbs']");
    }

    ElementsCollection breadCrumbs() {
        return $$x("//div[@class='steps breadcrumbs']//li");
    }


    SelenideElement firstVisibleBrand() {
        return $x("//div[@id='selected-instalation__slider']//li[@data-slick-index='0']");
    }

    SelenideElement secondVisibleBrand() {
        return $x("//div[@id='selected-instalation__slider']//li[@data-slick-index='1']");
    }

    SelenideElement sevenVisibleBrand() {
        return $x("//div[@id='selected-instalation__slider']//li[@data-slick-index='6']");
    }

    SelenideElement firstVisibleBrandToGetData() {
        return $x("//div[@id='selected-instalation__slider']//li[contains(@class, 'slick-active')][1]/input");
    }

    SelenideElement sevenVisibleBrandToGetData() {
        return $x("//div[@id='selected-instalation__slider']//li[contains(@class, 'slick-active')][7]/input");
    }

    SelenideElement secondVisibleBrandToGetData() {
        return $x("//div[@id='selected-instalation__slider']//li[contains(@class, 'slick-active')][2]/input");
    }


    SelenideElement firstVisibleBrandImg() {
        return $x("//div[@id='selected-instalation__slider']//li[contains(@class, 'slick-active')][1]/label/img");
    }

    SelenideElement secondVisibleBrandImg() {
        return $x("//div[@id='selected-instalation__slider']//li[contains(@class, 'slick-active')][2]/label/img");
    }

    ElementsCollection titleNameProductsFromListing() {
        return $$x("//div[@class='listing-wrap']//div[@class='name']/a[@class='ga-click']");
    }

    SelenideElement titleNameProductFromListing() {
        return $x("//div[@class='listing-wrap']//div[@class='name']/a[@class='ga-click']");
    }

    SelenideElement btnPrevInBlockBrands() {
        return $x("//div[@id='selected-instalation__slider']//a[@class='prev slick-arrow']");
    }

    SelenideElement btnNextInBlockBrands() {
        return $x("//div[@id='selected-instalation__slider']//a[@class='next slick-arrow']");
    }

    SelenideElement blockPagination() {
        return $x("//div[@class='page_list'][1]/div[@class='pagination']");
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


    ElementsCollection mainProductsList() {
        return $$x("//ul[@class='list_products ']/li");
    }
}
