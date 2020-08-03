package ATD;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

public class LKW_Category_car_list_page {
    SelenideElement listingOfProducts() {
        return $x("//ul[@class='list_products ']");
    }

    ElementsCollection breadCrumbsLinks() {
        return $$x("//div[@class='steps breadcrumbs']//li");
    }

    SelenideElement firstLinkOfBreadCrumbsBlock() {
        return $x("//div[@class='steps breadcrumbs']//img");
    }

    SelenideElement secondLinkOfBreadCrumbsBlock() {
        return $x("//li[@class='step_2 active parts_step_2']//a");
    }

    SelenideElement thirdLinkOfBreadCrumbsBlock() {
        return $x("//li[@class='step_3 active parts_step_3']//a");
    }

    SelenideElement fourthLinkOfBreadCrumbsBlock() {
        return $x("//li[@class='step_4 active parts_step_4']//a");
    }

    SelenideElement fifthLinkOfBreadCrumbsBlock() {
        return $x("//li[@class='step_5 active parts_step_5']//a");
    }

    SelenideElement firstLinkOfBreadCrumbsBlockTitleTecDoC() {
        return $x("//div[@class='steps breadcrumbs']//li[1]/span/a");
    }

    SelenideElement listOfProductInTecDocListingBlock() {
        return $x("//ul[@class='list_products ']");
    }

    ElementsCollection productsOnPage() {
        return $$x("//div[@class='image']//span[2]/img");
    }

    SelenideElement imageOfProductTecDocListingBlock(int position) {
        return $x("(//div[@class='image']//span[2]/img)[" + position + "]");
    }

    SelenideElement imageBrandOfProductTecDocListingBlock(int position) {
        return $x("(//div[@class='image']/span[1]/img)[" + position + "]");
    }

    ElementsCollection titleOfProductInTecDocListingBlock() {
        return $$x("//div[@class='name']/*[self::a or self::span][1]");
    }

    SelenideElement nextPagePagination() {
        return $x("//span[@class='next']");
    }

    SelenideElement lastPagePaginator() {
        return $x("//span[@class='next'][2]/a");
    }

    SelenideElement btnOfFirstProductInTecDocListing() {
        return $x("(//div[@class='button '])[1]");
    }

    SelenideElement basketDropMenu() {
        return $x("//div[@class='cart-items-block ']");
    }

    SelenideElement basket() {
        return $x("//a[@class='header-cart__link']");
    }

    SelenideElement articleNumberOfProduct(String titleOfArticle) {
        return $x("//span[contains(text(),'" + titleOfArticle + "')]");
    }

     SelenideElement dynamicCharacteristicInTecDocListingBlock(String titleOfArticle) {
        return $x("//span[contains(text(),'" + titleOfArticle + "')]/ancestor::div[@class='description']//ul[@class='criteria']//span[contains(text(),'Motorcode:')]/following-sibling::span");
    }

    SelenideElement totalAmountOfProductsInTecDocListing() {
        return $x("//div[@class='product_count']/span");
    }

    SelenideElement filterByGenericBlock() {
        return $x("//div[@class='filter-generics-tecdoc__content']");
    }

    ElementsCollection genericsInFilterGenericBlock() {
        return $$x("//label[@class='filter-generics-tecdoc__item ']");
    }

    SelenideElement loaderInTecDocListing() {
        return $x("//div[@class='preloader_wrapper']");
    }

    SelenideElement allFiltersGeneric() {
        return $x("//label[@class='filter-generics-tecdoc__all show_all  ']");
    }

    SelenideElement brandBlock() {
        return $x("//div[@id='selected-instalation__slider']");
    }

    SelenideElement brandsOfBrandBlock(String idOfBrand) {
        return $x("//input[@id='" + idOfBrand + "']/..");
    }

    SelenideElement installationSideBlock() {
        return $x("//div[@class='installation-side__content']");
    }

    ElementsCollection sidesOfInstallation() {
        return $$x("//div[@class='installation-side__content']/div");
    }

    ElementsCollection characteristicsBlock() {
        return $$x("//div[@class='description']");
    }

    SelenideElement descriptionOfCharacteristics(int position) {
        return $x("(//div[@class='description'])[" + position + "]//span[contains(text(),'Einbauseite:')]/following-sibling::*[self::span[contains(text(),'Hinterachse')] or self::span//b[contains(text(),'Hinterachse')]]");
    }

    SelenideElement descriptionOfCharacteristicsFrontSide(int position) {
        return $x("(//div[@class='description'])[" + position + "]//span[contains(text(),'Einbauseite:')]/following-sibling::*[self::span[contains(text(),'Vorderachse')] or self::span//b[contains(text(),'Vorderachse')]]");
    }

    ElementsCollection showReplacementButton() {
        return $$x("//div[@class='show_alternative__btn ga-click button additional_btn']");
    }

    ElementsCollection popUpWithSubscribeButton() {
        return $$x("//div[@class='button not_active']");
    }

    ElementsCollection analogProductBlock() {
        return $$x("//div[@class='product_description_box_alternative analogs']");
    }

    ElementsCollection productsInAnalogBlock(int position) {
        return $$x("(//div[@class='top-small-products__title'])[" + position + "]/following-sibling::div//div[@class='top-small-products-items__item']");
    }

    ElementsCollection titleOfAnalogBlock() {
        return $$x("//div[@class='top-small-products__title']");
    }

    SelenideElement popUpWithSubscriptionAboutAppearOfProduct() {
        return $x("//div[@class='popup-available']");
    }

    SelenideElement closePopUpWithSubscriptionAboutAppearOfProduct() {
        return $x("//div[@class='popup-available__close ga-popup-available-close']");
    }

    SelenideElement btnOfFirstProductInTecDocListingOfAnalogBlock() {
        return $x("(//div[@class='small-product-button price_box']//a)[1]");
    }

    ElementsCollection activeProductOlifilter() {
        return $$x("//div[@class='button ' and @data-generic-article-id='7']/ancestor::div[@class='price_box']//p[@class='actual_price']");
    }

    ElementsCollection notActiveProductsOlifilter() {
        return $$x("//div[@class='button not_active' and @data-generic-article-id='7']/ancestor::div[@class='price_box']//p[@class='actual_price']");
    }

    ElementsCollection activeProductsDichtungOlifilter() {
        return $$x("//div[@class='button ' and @data-generic-article-id='133']/ancestor::div[@class='price_box']//p[@class='actual_price']");
    }

    ElementsCollection notActiveProductsDichtungOlifilter() {
        return $$x("//div[@class='button not_active' and @data-generic-article-id='133']/ancestor::div[@class='price_box']//p[@class='actual_price']");
    }

    SelenideElement imageOfProductWithArticle(String article) {
        return $x("//span[contains(text(),'" + article + "')]/ancestor::div[@class='all_desc_item']//div[@class='image']/span[2]/img");
    }

    SelenideElement verticalTruckSelector() {
        return $x("//form[@class='js--lkw_selector car-fully-selected']");
    }

    SelenideElement markeInVerticalTruckSelector() {
        return $(byName("maker_id"));
    }

    SelenideElement modelInVerticalTruckSelector() {
        return $(byName("model_id"));
    }

    SelenideElement motorInVerticalTruckSelector() {
        return $(byName("car_id"));
    }

    SelenideElement iconOfTruckInHeadlineOfSelector() {
        return $x("//span[@class='car-icon']");
    }

    SelenideElement titleOfTruckInHeadlineOfSelector() {
        return $x("//div[contains(@class,'block-select-car__head-car--lkw')]/span[2]");
    }

    SelenideElement markeOfVerticalTruckSelector() {
        return $(byName("maker_id"));
    }

    SelenideElement modelOfVerticalTruckSelector() {
        return $(byName("model_id"));
    }

    SelenideElement motorOfVerticalTruckSelector() {
        return $(byName("car_id"));
    }

    SelenideElement buttonSuchenOfVerticaltruckSelector() {
        return $x("//a[@class='truck_submit js--lkw_selector-btn-submit']");
    }

    SelenideElement verticalSelectorInCloseCondition() {
        return $x("//div[@class='catalog-title__change-car ']");
    }

    SelenideElement resetBtnInVerticalCarSelector() {
        return $x("//a[@class='block-select-car__update hidden js--btn_reset_form']");
    }

    SelenideElement imageOfBrandAtMainHeadline() {
        return $x("//div[@class='catalog-title__block']/img");
    }

    SelenideElement brandsFilterBlock() {
        return $x("//div[@class='filter-generics-tecdoc__list js-filter-generics-tecdoc']");
    }

    SelenideElement brandsLinkInSideBar(String idOfBrand) {
        return $x("//div[@id='selected-instalation__slider']//li//label[@for='cb-brand-" + idOfBrand + "']");
    }

    SelenideElement forwardLinkAtBrandsFilter() {
        return $x("//div[@id='selected-instalation__slider']//a[@class='next slick-arrow']");
    }

    SelenideElement genericsBlock() {
        return $x("//div[@class='filter-generics-tecdoc__list js-filter-generics-tecdoc']");
    }

    ElementsCollection typeOfGenerics() {
        return $$x("//label[@class='filter-generics-tecdoc__item ']");
    }

    ElementsCollection visibleBrands() {
        return $$x("//li[contains(@class,'slick-active')]//label").filter(visible);
    }

    ElementsCollection parentCategories() {return $$x("//div[@class='car-parts-categories__item-link']");}

    ElementsCollection imageOfParentCategories() {return $$x("//div[@class='car-parts-categories__item-link']/div/img");}

    ElementsCollection childCategoriesPopUpOfParentCategory() {return $$x("//div[@class='car-parts-categories-modal__wrapper']");}

    ElementsCollection childCategoriesFirstLevel() {return $$x("//div[@class='car-parts-categories-modal__level1 js-height-compare js-init-height']/ul[@class='car-parts-categories-modal__link-list']/li/*[self::a or self::span]");}

    ElementsCollection titleOfParentCategories() {return $$x("//div[@class='car-parts-categories__item-name']");}

    ElementsCollection visibleIntermediateCategoryFirstLevel() {return $$x("//div[@class='car-parts-categories-modal__level1 js-height-compare']/ul[@class='car-parts-categories-modal__cat-list']/li").filter(visible);}

    ElementsCollection imageOfVisibleChildCategoriesFirstLevel() {return $$x("//div[@class='car-parts-categories-modal__level1 js-height-compare js-init-height']/ul[@class='car-parts-categories-modal__link-list']/li/*[self::a or self::span]/img");}

    ElementsCollection titleOfVisibleChildCategoriesFirstLevel() {return $$x("//div[@class='car-parts-categories-modal__level1 js-height-compare js-init-height']/ul[@class='car-parts-categories-modal__link-list']/li/*[self::a or self::span]/span");}

    ElementsCollection intermediateChildCategoriesFirstLevel(int position) {return $$x("(//div[contains(@class,'car-parts-categories-modal__level1')])["+position+"]/ul[@class='car-parts-categories-modal__cat-list']/li");}

    SelenideElement secondLevelBlock() {return $x("//li[@class='active']/div[2]");}

    ElementsCollection visibleChildCategorySecondLevel() {return $$x("//div[contains(@class,'car-parts-categories-modal__level2')]/ul[@class='car-parts-categories-modal__link-list']/li");}

    ElementsCollection visibleIntermediateCategorySecondLevel() {return $$x("//div[@class='car-parts-categories-modal__level2 js-height-compare js-init-height']/ul[@class='car-parts-categories-modal__cat-list']/li");}

    ElementsCollection visibleChildCategoriesSecondLevel() {return $$x("//div[@class='car-parts-categories-modal__level2 js-height-compare js-init-height']/ul[@class='car-parts-categories-modal__link-list']/li");}

    ElementsCollection imageOfVisibleChildCategoriesSecondLevel() {return $$x("//div[@class='car-parts-categories-modal__level2 js-height-compare js-init-height']/ul[@class='car-parts-categories-modal__link-list']/li//img");}

    ElementsCollection titleOfVisibleChildCategoriesSecondLevel() {return $$x("//div[@class='car-parts-categories-modal__level2 js-height-compare js-init-height']/ul[@class='car-parts-categories-modal__link-list']/li/*[self::a or self::span]/span");}

    ElementsCollection intermediateChildCategoriesSecondLevel() {return $$x("//div[contains(@class,'car-parts-categories-modal__level2')]/ul[@class='car-parts-categories-modal__cat-list']/li").filter(visible);}

    ElementsCollection childCategoriesThirdLevel() {return $$x("//div[@class='car-parts-categories-modal__level3 js-height-compare js-init-height']/div/*[self::a or self::span]");}

    SelenideElement thirdLevelBlock() {return $x("//div[@class='car-parts-categories-modal__level3 js-height-compare js-init-height']");}

    ElementsCollection childCategoriesFirstLevelForCheck() {return $$x("//div[@class='car-parts-categories-modal__level1 js-height-compare']/ul[@class='car-parts-categories-modal__link-list']/li");}

   SelenideElement installationSideBlockInHeader() {return $x("//div[@class='installation-side__content']/div");}

    SelenideElement installationSideBlockInSideBar() {return $x("//div[@class='filter-disk sidebar_block_radio js-criteria-filter  js-filter-wrapper js-filter-criteria_100']");}

    ElementsCollection sidesOfInstallationInSideBar() {return $$x("//div[@class='filter-disk sidebar_block_radio js-criteria-filter  js-filter-wrapper js-filter-criteria_100']/div[2]/ul//li");}
}
