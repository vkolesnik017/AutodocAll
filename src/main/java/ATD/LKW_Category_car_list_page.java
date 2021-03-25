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

    SelenideElement brandBlockInSideBar() {
        return $x("//div[@class='filter-brand-top js-brands-filter js-filter-wrapper  js-filter-50002']");
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
        return $x("(//div[@class='description'])[" + position + "]//span[contains(text(),'Einbauseite:')]/following-sibling::*[self::span[contains(text(),'Vorderachse')] or self::span//b[contains(text(),'Vorderachse')]]");
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
        return $x("//div[@class='filter-brand-top js-brands-filter js-filter-wrapper  js-filter-50002']");
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

    SelenideElement genericsBlockInSideBar() {
        return $x("//div[@class='filter-disk sidebar_block js-filter-wrapper js-filter-generic  criteria-filter-block-js  js-filter-50001']");
    }

    ElementsCollection typeOfGenerics() {
        return $$x("//label[@class='filter-generics-tecdoc__item ']");
    }

    ElementsCollection typeOfGenericsInSideBar() {
        return $$x("//div[contains(text(),'Wählen Sie die gewünschte Teile-Kategorie')]/following-sibling::div/ul//li");
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

    ElementsCollection childCategoriesFirstLevelForCheck() {return $$x("//div[@class='car-parts-categories-modal__level1 js-height-compare']/ul[@class='car-parts-categories-modal__link-list']/li").filter(visible);}

   SelenideElement installationSideBlockInHeader() {return $x("//div[@class='installation-side__content']/div");}

    SelenideElement installationSideBlockInSideBar() {return $x("//div[@class='installation-side__content']");}

    ElementsCollection sidesOfInstallationInSideBar() {return $$x("//div[@class='installation-side__content']/div");}

    ElementsCollection linksOfBrandsFromBlock() {return $$x("//div[@id='selected-instalation__slider']//li/label");}

    SelenideElement listOfProductTableView() {return $x("//div[@class='sub_catalog_grid']");}

    ElementsCollection labelTitleDangerousProducts() {return $$x("//div[@class='rec_products_block js-products-item ']//span[@class='dangerous-listing__show-more']");}

    ElementsCollection labelIconDangerousProducts() {return $$x("//div[@class='rec_products_block js-products-item ']//div[@class='dangerous-listing__icon dangerous-listing__icon-attention'][1]").filter(visible);}

    ElementsCollection dangerousProducts() {return $$x("//span[@class='dangerous-listing__show-more']/ancestor::div[@class='rec_products_block js-products-item ']");}

    ElementsCollection signalWordOfDangerousProduct() {return $$x("//div[contains(@class,'dangerous-listing__title')]");}

    SelenideElement blackBackground() {return $x("//div[@class='overlay black hidden']");}

    SelenideElement warningPopUp() {return $x("//div[@class='popup-dangerous']");}

    SelenideElement titleOfDangerousPopUp() {return $x("//div[@class='popup-dangerous__title']");}

    SelenideElement infoTextOfDangerousPopUp() {return $x("//div[@class='popup-dangerous']//p");}

    ElementsCollection btnAddDangerousProductToWishList() {return $$x("//span[@class='dangerous-listing__show-more']/ancestor::li//span[@class='add-to-wishlist title_btn add-article']");}

    ElementsCollection signalWordOfDangerousProductListingView() {return $$x("//div[contains(@class,'dangerous-listing__title')]");}

    ElementsCollection attributeOfWarningIcon(int positionOfProduct) {return $$x("(//span[@class='dangerous-listing__show-more'])["+positionOfProduct+"]/ancestor::li//div[@class='dangerous-listing__icons']/div");}

    ElementsCollection btnMoreOfDangerousProducts() {return $$x("//span[@class='dangerous-listing__show-more']");}

    ElementsCollection dangerousIconInWarningPopUp() {return $$x("//div[@class='popup-dangerous__icon']").filter(visible);}

    SelenideElement addOfIssueProductBlock() {return $x("//div[@class='w_search no_margin']");}

    ElementsCollection imageOfProductsWithOutAddIssue() {return $$x("//div[@class='w_search no_margin']/preceding-sibling::li//div[@class='image']//span[2]/img");}

    SelenideElement oneImageOfProductTecDocListingBlock(int position) {
        return $x("(//div[@class='w_search no_margin']/preceding-sibling::li//div[@class='image']//span[2]/img)[" + position + "]");
    }

    ElementsCollection genericsFromSideBar() {return $$x("//div[contains(text(),'Wählen Sie die gewünschte Teile-Kategorie')]/following-sibling::div/ul//li//span");}

    SelenideElement forwardOfListing() {return $x("//span[@class='next'][1]/a");}

    ElementsCollection attributeOfBtnAddedToBasket() { return $$x("//div[@class='count']/following-sibling::div");}

    ElementsCollection productsFromListBlock() {return $$x("//ul[@class='list_products ']/li");}

    ElementsCollection activeBrands() {return $$x("//div[@class='slick-track']//*[self::li[@class='slick-slide slick-current slick-active'] or self::li[@class='slick-slide slick-active']]//label");}

    ElementsCollection btnAddProductToWishlist() {return $$x("//span[@class='add-to-wishlist title_btn add-article']");}

    ElementsCollection priceTitle() {return $$x("//div[@class='price-block__inkl']/*[1]");}

    SelenideElement installSideInCharacteristics(int position, String installSide) {
        return $x("(//div[@class='description'])[" + position + "]//span[contains(text(),'Einbauseite:')]/following-sibling::*[self::span[contains(text(),'"+installSide+"')] or self::span//b[contains(text(),'"+installSide+"')]]");
    }

    SelenideElement lampDataValue(String value) {return $x("//div[@class='lampenart-filter__slider']//label[@data-value='"+value+"']");}

    ElementsCollection visibleFilterBrands() {return $$x("//ul[@class='no-margin']//label");}

}
