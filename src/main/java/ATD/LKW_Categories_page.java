package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

public class LKW_Categories_page {
    SelenideElement tecDocCatalog() {
        return $x("//div[@class='car-parts-categories']");
    }

    SelenideElement headlineInHeader() {
        return $x("//div[@class='car-parts-categories__title']/h2");
    }

    SelenideElement titleOfCatalog() {
        return $x("//div[@class='car-parts-categories__title']");
    }


    SelenideElement verticalTruckSelectorInCloseCondition() {
        return $x("//div[contains(@class,'catalog-title__change-car')]");
    }

    SelenideElement verticalTruckSelectorInOpenCondition() {
        return $x("//div[@class='block-select-car block-select-car--sidebar']");
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

    SelenideElement resetBtnInVerticalCarSelectorInOpenCondition() {
        return $x("//a[@class='block-select-car__update hidden js--btn_reset_form']");
    }

    SelenideElement headlineOfTopProductsBlock() {return $x("//div[@class='truck-car-parts-top-product__title']");}

    SelenideElement topBLock() {return $x("//div[@class='truck-car-parts-top-product']");}

    ElementsCollection productsOfTopBlock() {return $$x("//div[contains(@class,'product-list__row')]/ul/li");}

    SelenideElement topProductsBlock() {return $x("//div[@class='truck-car-parts-top-product']");}

    ElementsCollection additionInfoBlockOfTopProduct() {return $$x("//div[@class='product-list__item__popup']");}

    ElementsCollection imageOfTopProduct() {return $$x("//*[self::span[contains(@class,'ga-click')] or self::a[contains(@class,'ga-click')]]/img");}

    SelenideElement closeCookiesPopUp() {return $x("//div[@class='block-cookies__close']");}

    ElementsCollection titleOfTopProduct() {return $$x("//div[@class='product-list__item__title']/span");}

    ElementsCollection linkDetails() {return $$x("//div[@class='item-table-box__details']/span");}

    ElementsCollection parentCategories() {return $$x("//div[@class='car-parts-categories__item-link']");}

    ElementsCollection childCategoriesPopUpOfParentCategory() {return $$x("//div[@class='car-parts-categories-modal__wrapper']");}

    ElementsCollection titleOfParentCategories() {return $$x("//div[@class='car-parts-categories__item-name']");}

    ElementsCollection imageOfParentCategories() {return $$x("//div[@class='car-parts-categories__item-link']/div/img");}

    ElementsCollection visibleChildCategoryFirstLevel() {return $$x("//div[contains(@class,'car-parts-categories-modal__level1')]/ul[@class='car-parts-categories-modal__link-list']/li").filter(visible);}

    ElementsCollection visibleIntermediateCategoryFirstLevel() {return $$x("//div[@class='car-parts-categories-modal__level1 js-height-compare']/ul[@class='car-parts-categories-modal__cat-list']/li").filter(visible);}

    ElementsCollection imageOfVisibleChildCategoriesFirstLevel() {return $$x("//div[@class='car-parts-categories-modal__level1 js-height-compare js-init-height']/ul[@class='car-parts-categories-modal__link-list']/li/*[self::a or self::span]/img");}

    ElementsCollection titleOfVisibleChildCategoriesFirstLevel() {return $$x("//div[@class='car-parts-categories-modal__level1 js-height-compare js-init-height']/ul[@class='car-parts-categories-modal__link-list']/li/*[self::a or self::span]/span");}

    ElementsCollection intermediateChildCategoriesFirstLevel(int position) {return $$x("(//div[contains(@class,'car-parts-categories-modal__level1')])["+position+"]/ul[@class='car-parts-categories-modal__cat-list']/li");}

    SelenideElement secondLevelBlock() {return $x("//li[@class='active']/div[2]");}

    SelenideElement thirdLevelBlock() {return $x("//div[@class='car-parts-categories-modal__level3 js-height-compare js-init-height']");}

    ElementsCollection childCategoriesSecondLevel(int position) {return $$x("(//div[contains(@class,'car-parts-categories-modal__level1')])["+position+"]//div[contains(@class,'car-parts-categories-modal__level2')]//li").filter(visible);}

   ElementsCollection childCategoriesFirstLevel() {return $$x("//div[@class='car-parts-categories-modal__level1 js-height-compare js-init-height']/ul[@class='car-parts-categories-modal__link-list']/li/*[self::a or self::span]");}

    ElementsCollection childCategoriesFirstLevelForCheck() {return $$x("//div[@class='car-parts-categories-modal__level1 js-height-compare']/ul[@class='car-parts-categories-modal__link-list']/li");}

    ElementsCollection visibleChildCategorySecondLevel() {return $$x("//div[contains(@class,'car-parts-categories-modal__level2')]/ul[@class='car-parts-categories-modal__link-list']/li");}

    ElementsCollection visibleIntermediateCategorySecondLevel() {return $$x("//div[@class='car-parts-categories-modal__level2 js-height-compare js-init-height']/ul[@class='car-parts-categories-modal__cat-list']/li");}

    ElementsCollection intermediateChildCategoriesSecondLevel() {return $$x("//div[contains(@class,'car-parts-categories-modal__level2')]/ul[@class='car-parts-categories-modal__cat-list']/li").filter(visible);}

    ElementsCollection childCategoriesThirdLevel() {return $$x("//div[@class='car-parts-categories-modal__level3 js-height-compare js-init-height']/div/*[self::a or self::span]");}

    ElementsCollection visibleChildCategoriesSecondLevel() {return $$x("//div[@class='car-parts-categories-modal__level2 js-height-compare js-init-height']/ul[@class='car-parts-categories-modal__link-list']/li");}

    ElementsCollection imageOfVisibleChildCategoriesSecondLevel() {return $$x("//div[@class='car-parts-categories-modal__level2 js-height-compare js-init-height']/ul[@class='car-parts-categories-modal__link-list']/li//img");}

    ElementsCollection titleOfVisibleChildCategoriesSecondLevel() {return $$x("//div[@class='car-parts-categories-modal__level2 js-height-compare js-init-height']/ul[@class='car-parts-categories-modal__link-list']/li/*[self::a or self::span]/span");}
}
