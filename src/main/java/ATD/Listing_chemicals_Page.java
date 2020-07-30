package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Listing_chemicals_Page {


    SelenideElement titleNameCategory() {
        return $x("//h2[@class='title_count_search']");
    }

    SelenideElement blockBrands() {
        return $x("//div[@id='selected-instalation__slider']");
    }

    SelenideElement blockBreadCrumbs() {
        return $x("//div[@class='steps breadcrumbs']");
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

    SelenideElement blockProductQuantity() {
        return $x("//div[@class='product_count']");
    }

    SelenideElement topPaginationBlock() {
        return $x("//div[@class='page_list'][1]/div[@class='pagination']");
    }

    SelenideElement lowerPaginationBlock() {
        return $x("//div[@class='page_list'][2]/div[@class='pagination']");
    }

    SelenideElement btnPageThirdInPagination() {
        return $x("//div[@class='page_list'][1]//span[4]");
    }

    SelenideElement btnForReturnOnFirstPageInPagination() {
        return $x("//div[@class='page_list'][1]//span[@class='first']");
    }

    SelenideElement btnPreviousInPagination() {
        return $x("//div[@class='page_list'][1]//span[@class='previous']");
    }

    SelenideElement btnNextInPagination() {
        return $x("//div[@class='page_list'][1]//span[@class='next'][1]");
    }

    SelenideElement btnLastInPagination() {
        return $x("//div[@class='page_list'][1]//span[@class='next'][2]");
    }

    SelenideElement firstVisibleBrand() {
        return $x("//div[@id='selected-instalation__slider']//li[contains(@class, 'slick-active')][1]");
    }

    SelenideElement secondVisibleBrand() {
        return $x("//div[@id='selected-instalation__slider']//li[contains(@class, 'slick-active')][2]");
    }

    SelenideElement sevenVisibleBrand() {
        return $x("//div[@id='selected-instalation__slider']//li[contains(@class, 'slick-active')][7]");
    }

    SelenideElement firstVisibleBrandToGetData() {
        return $x("//div[@id='selected-instalation__slider']//li[contains(@class, 'slick-active')][1]/input");
    }

    SelenideElement secondVisibleBrandToGetData() {
        return $x("//div[@id='selected-instalation__slider']//li[contains(@class, 'slick-active')][2]/input");
    }

    SelenideElement sevenVisibleBrandToGetData() {
        return $x("//div[@id='selected-instalation__slider']//li[contains(@class, 'slick-active')][7]/input");
    }

    SelenideElement secondVisibleBrandImg() {
        return $x("//div[@id='selected-instalation__slider']//li[contains(@class, 'slick-active')][2]/label/img");
    }

    SelenideElement sevenVisibleBrandImg() {
        return $x("//div[@id='selected-instalation__slider']//li[contains(@class, 'slick-active')][7]/label/img");
    }

    ElementsCollection titleNameProductsFromListing() {
        return $$x("//div[@class='js-listing-wrap']//div[@class='name']//*[contains(@class,'ga-click')]");
    }

    SelenideElement titleCategoriesBlockInSidebar() {
        return $x("//div[@class='block categories blue topSubCats']/b[text()]");
    }

    SelenideElement firstCategoryInSidebar() {
        return $x("//div[@class='block categories blue topSubCats']//li[1]");
    }

    SelenideElement blockSeoText() {
        return $x("//div[@class='block_youtube_video']/span[@class='more_text']");
    }

    SelenideElement blockGeneric() {
        return $x("//div[@class='js-filter-criteria-top']");
    }

    ElementsCollection allGenericsInGenericBlock() {
        return $$x("//div[@class='filter-generics-tecdoc__content']//label[contains(@class,'filter-generics-tecdoc__item')]");
    }

    SelenideElement firstGenericInGenericsBlock() {
        return $x("//div[@class='filter-generics-tecdoc__content']//label[contains(@class,'slick-active')]");
    }

    SelenideElement firstGenericByIndexInGenericsBlock() {
        return $x("//div[@class='filter-generics-tecdoc__content']//label[@data-slick-index='0']");
    }

    SelenideElement secondGenericInGenericsBlock() {
        return $x("//div[@class='filter-generics-tecdoc__content']//label[contains(@class,'slick-active')][2]");
    }

    SelenideElement firstGenericInGenericsBlockToGetData() {
        return $x("//div[@class='filter-generics-tecdoc__content']//label[contains(@class,'slick-active')]/input");
    }

    SelenideElement secondGenericInGenericsBlockToGetData() {
        return $x("//div[@class='filter-generics-tecdoc__content']//label[contains(@class,'slick-active')][2]/input");
    }

    SelenideElement btnPreviousInGenericBlock() {
        return $x("//div[@class='js-filter-criteria-top']//span[contains(@class,'prev slick-arrow')]");
    }

    SelenideElement btnNextInGenericBlock() {
        return $x("//div[@class='js-filter-criteria-top']//span[contains(@class,'next slick-arrow')]");
    }

    SelenideElement btnResetAllInGenericsBlock() {
        return $x("//div[@class='js-filter-criteria-top']//label[@class='filter-generics-tecdoc__all show_all  ']");
    }

    SelenideElement mainProductsBlock() {
        return $x("//ul[@class='list_products ']");
    }

    ElementsCollection mainProductsList() {
        return $$x("//ul[@class='list_products ']/li");
    }

    SelenideElement btnResetAllFilterInResetBlock() {
        return $x("//div[@class='reset-buttons']//li[contains(@class,'reset-buttons__all')]");
    }

    SelenideElement firstBtnResetFilterInResetBlock() {
        return $x("//div[@class='reset-buttons']//li[1]//div[@class='unpick-js reset-buttons__icon']");
    }

    SelenideElement secondBtnResetFilterInResetBlock() {
        return $x("//div[@class='reset-buttons']//li[2]//div[@class='unpick-js reset-buttons__icon']");
    }



}
