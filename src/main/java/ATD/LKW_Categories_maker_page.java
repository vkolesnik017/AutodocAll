package ATD;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

class LKW_Categories_maker_page {

    SelenideElement firstLinkBreadCrumbsBlock() {
        return $x("//li[@class='step_1 active parts_step_1']");
    }

    SelenideElement secondLinkBreadCrumbsBlock() {
        return $x("//li[@class='step_2 not_active parts_step_2']");
    }

    SelenideElement thirdLinkBreadCrumbsBlock() {
        return $x("//li[@class='step_3 not_active parts_step_3']");
    }

    SelenideElement imageOfFirstLinkBreadCrumbs() {
        return $x("//li[@class='step_1 active parts_step_1']//img");
    }

    SelenideElement titleOfFirstLinkBreadCrumbs() {
        return $x("//li[@class='step_1 active parts_step_1']//a/span");
    }

    SelenideElement mainImageBlock() {
        return $x("//div[@class='image-car-model']");
    }

    SelenideElement iconOfCarInMainImageBlock() {
        return $x("//div[@class='image-car-model']/img");
    }

    SelenideElement titleOfTopParentCategoryBlock() {
        return $x("//div[@class='cont']//div[@class='title_list no_border_top'][2]");
    }

    SelenideElement topCategoriesCatalog() {
        return $x("//div[@class='truck-cars-parts']");
    }

    ElementsCollection categoriesOfTopCategoriesCatalog() {
        return $$x("//li[@class='truck-cars-parts__item']");
    }

    SelenideElement imageOfTopCategoryBlock(int position) {
        return $x("(//span[@class='truck-cars-parts__item-image'])[" + position + "]");
    }

    SelenideElement titleOfTopCategoryBlock(int position) {
        return $x("(//span[@class='truck-cars-parts__item-title'])[" + position + "]");
    }

    ElementsCollection linksOfTopCategoryBlock(int position) {
        return $$x("//li[@class='truck-cars-parts__item'][" + position + "]//a[@class='truck-cars-parts__cat-link js--lkw-modal__cat-link']");
    }

    SelenideElement seoTextBlock() {
        return $x("//div[@class='search_n_text']");
    }

    SelenideElement linkBlockTopBrand() {
        return $x("//div[@class='block_links']");
    }

    ElementsCollection linksInTopBrandBlock() {
        return $$x("//div[@class='block_links']/a");
    }

    SelenideElement categoriesInSideBar() {
        return $x("//div[@class='teile_catalog']");
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

    SelenideElement headlineOfTopModelBlockFirstLevel() {
        return $x("//div[@class='title_list no_border_top']/h3");
    }

    SelenideElement headlineOfTopModelBlockSecondLevel() {
        return $x("//div[@class='title_list no_border_top']/b");
    }

    ElementsCollection modelsOfTruckBrandBlock() {
        return $$x("//div[@class='lkw_top_models']//li");
    }

    SelenideElement modelListBlock() {
        return $x("//div[@class='lkw_top_models']/ul");
    }

    ElementsCollection modelsInListBlock() {
        return $$x("//div[@class='lkw_top_models']/ul//li");
    }

    ElementsCollection
    imageOfModelsInListBlock() {
        return $$x("//div[@class='lkw_top_models']/ul//li//img");
    }

    ElementsCollection titleOfModelsInListBlock() {
        return $$x("//div[@class='lkw_top_models']/ul//li//span[2]");
    }

    ElementsCollection yearOfModelsInListBlock() {
        return $$x("//div[@class='lkw_top_models']/ul//li/span");
    }

    SelenideElement markeInVerticalCarSelector() {
        return $(byName("maker_id"));
    }

    SelenideElement resetBtnInVerticalCarSelector() {
        return $x("//a[@class='block-select-car__update hidden js--btn_reset_form']");
    }

    SelenideElement modelOfTruckInTopBlock(String model) {
        return $x("//div[@class='lkw_top_models']//span[contains(text(),'" + model + "')]");
    }

    SelenideElement headlineOfTopProductsBlock() {
        return $x("//div[@class='top-small-products__title']");
    }

    SelenideElement topProductsBlock() {
        return $x("//div[@class='top-small-products']");
    }

    ElementsCollection productsInTopBlock() {
        return $$x("//div[@class='top-small-products__items']/div");
    }

    SelenideElement forwardLinkOfTopBLock() {
        return $x("//a[@class='bx-next']");
    }

    SelenideElement backLinkOfTopBLock() {
        return $x("//a[@class='bx-prev']");
    }

    SelenideElement productsInTopBlockFirstLevel() {
        return $x("//div[@class='top-small-products__items'][1]");
    }

    SelenideElement productsInTopBlockSecondLevel() {
        return $x("//div[@class='top-small-products__items'][2]");
    }

    ElementsCollection btnAddToBasketTopBLock() {
        return $$x("//a[@class='ga-click button still_add_to_basket']").filter(visible);
    }

    SelenideElement basketDropMenu() {
        return $x("//div[@class='cart-items-block ']");
    }

    SelenideElement basket() {
        return $x("//a[@class='header-cart__link']");
    }

    ElementsCollection additionInfoBlockOfTopProduct() {
        return $$x("//div[@class='top-small-products__items']/div//div[@class='rec_prod_info_popup']").filter(visible);
    }

    ElementsCollection imageOfTopProduct() {
        return $$x("//div[@class='top-small-products__items']/div/div/a/img");
    }

    ElementsCollection titleOfTopProduct() {
        return $$x("//div[@class='top-small-products__items']/div/div[2]/span");
    }

    ElementsCollection linkDetails() {
        return $$x("//div[@class='top-small-products__items']//div[@class='rec_prod_info_popup']/div/span");
    }

    SelenideElement closeCookiesPopUp() {
        return $x("//div[@class='block-cookies__close']");
    }
}
