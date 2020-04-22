package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

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
        return $x("//a[@class='truck_submit js--lkw_selector-btn-submit']");}

}
