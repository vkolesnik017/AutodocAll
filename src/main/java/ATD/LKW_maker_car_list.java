package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;


class LKW_maker_car_list {

    SelenideElement tecDocCatalog() {
        return $x("//div[@class='car-parts-categories__list']");
    }

    SelenideElement inputSearchInHeader() {
        return $(byId("search"));
    }

    SelenideElement dropMenuOfSearchFieldInHeader() {
        return $x("//div[@class='autocomplete-suggestions']");
    }

    ElementsCollection productsInDropMenuOfSearchField() {
        return $$x("//div[@class='autocomplete-suggestion']");
    }

    SelenideElement imageOfTruckInHeadLine() {
        return $x("//div[@class='catalog-title__block']/img");
    }

    SelenideElement titleOfTruckInHeadLine() {
        return $x("//div[@class='catalog-title__block']//h2");
    }

    SelenideElement infoLinkInHeadline() {
        return $(byId("selected_car_info"));
    }

    SelenideElement infoPopUp() {
        return $x("//div[@class='selected_car_info_popap']");
    }

    SelenideElement titleOfInfoPopUp() {
        return $x("//div[@class='selected_car_info_popap']//b[1]");
    }

    SelenideElement verticalTruckSelectorInOpenState() {
        return $x("//div[contains(@class,'home-select-car--dropdown')]");
    }

    SelenideElement verticalTruckSelectorInCloseState() {
        return $x("//div[contains(@class,'catalog-title__change-car')]");
    }

    SelenideElement verticalTruckSelectorInCloseStateSecond() {
        return $x("//div[@class='catalog-title__change-car ']");
    }

    SelenideElement tooltipOfVerticalCarSelector() {
        return $x("//div[@class='validation-tooltip popup-error-select']");
    }

    SelenideElement markeInVerticalCarSelector() {return $(byName("maker_id"));}

    SelenideElement modelInVerticalCarSelector() {return $(byName("model_id"));}

    SelenideElement motorInVerticalCarSelector() {return $(byName("car_id"));}

    SelenideElement btnSearchInVerticalCarSelector() {
        return $x("//a[@class='truck_submit js--lkw_selector-btn-submit']");}

    SelenideElement tooltipForFieldInVerticalCarSelector() { return $x("//div[@class='validation-tooltip popup-error-select']");}

    SelenideElement logoInHeader() {return $x("//a[@class='header__logo-main']/img");}

    SelenideElement iconOfTruckInHeadlineOfSelector() {return $x("//span[@class='car-icon']");}

    SelenideElement titleOfTruckInHeadlineOfSelector() {return $x("//div[@class='block-select-car__head-car']/span[2]");}

    SelenideElement resetBtnInVerticalCarSelector() {return $x("//a[@class='block-select-car__update hidden js--btn_reset_form']");}

    SelenideElement imageOfBrandInMainHeadline() {return $x("//div[@class='catalog-title__block']/img");}

    SelenideElement mainHeadline() {return $x("//div[@class='catalog-title__block']/h2");}

    SelenideElement garageIconInHeader() {
        return $x("//div[@class='header-garage js-header-garage']");
    }

    ElementsCollection urlsOfAddedVehicleInPopUpOfGarageFromSelector() {return $$x("//div[@class='header-garage__logged-check']//a");}

    ElementsCollection radioBtnOfAddedVehicleInPopUpOfGarageFromSelector() {return $$x("//input[@name='radio-car']");}

    ElementsCollection mainCategoriesInHeader() {return $$x("//ul[@class='header__nav-list']/li/a");}

    SelenideElement popUpOfGarageInHeader() {
        return $x("//div[contains(@class,'header-garage__notlogged')]");
    }

    ElementsCollection urlsOfAddedVehicleInPopUpOfGarageInHeader() {
        return $$x("//div[contains(@class,'header-garage__notlogged')]//a[contains(@class,'history-cars-rows__link')]");
    }

    SelenideElement garageIconInHeaderActive() {return $x("//div[@class='header-garage js-header-garage active']");}

    SelenideElement btnAddedVehicleOfMyGaragePopUp() {return $x("//div[@class='header-garage__history-button js-selector-add-car']/button");}

    SelenideElement selectorFromMyGarageBlock() {return $(byId("selector-my-garage"));}

    SelenideElement motoTab() {return $x("//li[@class='popup-selector__tabs-item select-moto ']");}

    SelenideElement markeOfVehicleInSelector() {return $x("//form[@id='top-select-garage-moto']/div/div[2]/div[1]/select");}

    SelenideElement modelOfVehicleInSelector() {return $x("//form[@id='top-select-garage-moto']/div/div[2]/div[2]/select");}

    SelenideElement motorOfVehicleInSelector() {return $x("//form[@id='top-select-garage-moto']/div/div[2]/div[3]/select");}

    SelenideElement btnSearchVehicleInSelector() {return $x("//a[@class='submit search_button ripple-out']");}

    ElementsCollection parentCategories() {return $$x("//div[@class='car-parts-categories__item-link']");}

    ElementsCollection titleOfParentCategories() {return $$x("//div[@class='car-parts-categories__item-name']");}

    ElementsCollection imageOfParentCategories() {return $$x("//div[@class='car-parts-categories__item-link']/div/img");}

    ElementsCollection childCategoriesPopUpOfParentCategory() {return $$x("//div[@class='car-parts-categories-modal__wrapper']");}

    ElementsCollection childCategoriesFirstLevel() {return $$x("//div[@class='car-parts-categories-modal__level1 js-height-compare js-init-height']/ul[@class='car-parts-categories-modal__link-list']/li/*[self::a or self::span]");}

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

    SelenideElement mainFormOfSelector() {return $x("//div[@class='home-select-car home-select-car--dropdown home-select-car--lkw']");}

    ElementsCollection visibleChildCategoriesPopUpOfParentCategory() {return $$x("//div[@class='car-parts-categories-modal__wrapper']").filter(visible);}

    ElementsCollection popUpOfParentCategories() {return $$x("//div[@class='car-parts-categories-modal__content']");}

    ElementsCollection visibleChildCategories() {return $$x("//ul[@class='car-parts-categories-modal__link-list']//a").filter(visible);}

    SelenideElement seoText() {return $(".lkw-seo-desc");}
}
