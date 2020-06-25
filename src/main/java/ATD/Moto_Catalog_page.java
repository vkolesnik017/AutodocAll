package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class Moto_Catalog_page {
    protected SelenideElement catalogTecDoc() {
        return $x("//div[@class='car-parts-categories']");
    }

    SelenideElement carCategory() {
        return $x("//a[@class='header-i header-i--car ga-click']");
    }

    public SelenideElement lkwCategory() {
        return $x("//a[@class='header-i header-i--truck ga-click']");
    }

    SelenideElement motoSelectorBlock() {
        return $x("//div[contains(@class,'catalog-title__change-car')]");
    }

    SelenideElement mainFormOfSelector() {
        return $(byId("selector-wrapper"));
    }

    SelenideElement brandOfMotoSelector() {
        return $(byId("form_maker_id"));
    }

    SelenideElement modelOfMotoSelector() {
        return $(byId("form_model_id"));
    }

    SelenideElement motorOfMotoSelector() {
        return $(byId("form_car_id"));
    }

    SelenideElement toolTipForBrandFieldInSelector() {
        return $(byId("selector-error-tooltip"));
    }

    SelenideElement toolTipForModelFieldInSelector() {
        return $(byId("selector-error-tooltip-model"));
    }

    SelenideElement toolTipForMotorFieldInSelector() {
        return $(byId("selector-error-tooltip-car"));
    }

    SelenideElement btnSearchAtSelector() {
        return $x("//a[contains(@class,'search_button')]");
    }

    SelenideElement headlineBlockInSelector() {
        return $x("//div[@class='block-select-car__head-car']");
    }

    SelenideElement imageInHeadlineInSelector() {
        return $x("//div[@class='block-select-car__head-car']/span[1]");
    }

    SelenideElement textOfHeadlineInSelector() {
        return $x("//div[@class='block-select-car__head-car']/span[2]");
    }

    SelenideElement btnResetOfSelector() {
        return $(byId("reset_selector_form"));
    }

    SelenideElement selectorInCloseCondition() {
        return $x("//div[@class='catalog-title__change-car ']");
    }

    SelenideElement mainLogoInHeader() {
        return $x("//a[@class='header__logo-main']/img");
    }

    ElementsCollection breadCrumbsLinks() {
        return $$x("//div[@class='steps breadcrumbs']/ul/li");
    }

    SelenideElement iconOfMotoBrandInBreadCrumbs() {
        return $x("//li[@class='step_1 active parts_step_1']//img");
    }

    SelenideElement titleOfMotoBrandInBreadCrumbs() {
        return $x("//li[@class='step_1 active parts_step_1']//a");
    }

    SelenideElement mainHeadline() {
        return $x("//div[@class='car-parts-categories__title']/h2");
    }

    SelenideElement iconOfBrandInHeadline() {
        return $x("//div[@class='catalog-title__block']/img");
    }

    SelenideElement informationIconAtHeadline() {
        return $(byId("selected_car_info"));
    }

    SelenideElement informationPopUp() {
        return $x("//div[@class='selected_car_info_popap moto-info-popup']");
    }

    SelenideElement mainSearchField() {
        return $(byId("search"));
    }

    SelenideElement hintBlockOfMainSearchField() {
        return $x("//div[@class='autocomplete-suggestions']");
    }

    SelenideElement productInHintBlockOfSearchField() {
        return $x("//div[@class='autocomplete-suggestion']");
    }

    ElementsCollection parentsCategoriesOfTecDocCatalog() {
        return $$x("//div[@class='car-parts-categories__item-link']");
    }

   // ElementsCollection  childCategoriesFirstLevelBlock() {return $$x("//div[@class='car-parts-categories-modal__level1 js-height-compare']");}

    ElementsCollection  childCategoriesFirstLevelBlock() {return $$x("//div[contains(@class,'car-parts-categories-modal__level1')]");}

    ElementsCollection childCategoriesFirstLevel(int position) {return $$x("(//div[contains(@class,'car-parts-categories-modal__level1')])["+position+"]/ul[@class='car-parts-categories-modal__link-list']//a");}


    SelenideElement tecDocCatalog() {return $x("//div[@class='car-parts-categories']");}

    ElementsCollection intermediateChildCategoriesFirstLevel(int position) {return $$x("(//div[contains(@class,'car-parts-categories-modal__level1')])["+position+"]/ul[@class='car-parts-categories-modal__cat-list']/li");}


    SelenideElement childCategoriesSecondLevelBlock() {return $x("//div[@class='car-parts-categories-modal__level2 js-height-compare js-init-height']");}

    ElementsCollection childCategoriesSecondLevelBlockCheck(int position) {return $$x("(//div[contains(@class,'car-parts-categories-modal__level1')])["+position+"]//div[@class='car-parts-categories-modal__cat-name']/following-sibling::div[contains(@class,'car-parts-categories-modal__level2')]");}

    ElementsCollection childCategoriesSecondLevel(int position) {return $$x("(//div[contains(@class,'car-parts-categories-modal__level1')])["+position+"]//div[contains(@class,'car-parts-categories-modal__level2')]//li").filter(visible);}

    ElementsCollection imedCategory() {return $$x("//ul[@class='car-parts-categories-modal__cat-list']/li").filter(visible);}

    ElementsCollection chCategory() {return $$x("//ul[@class='car-parts-categories-modal__link-list']/li").filter(visible);}

    SelenideElement secondLevelBlock() {return $x("//li[@class='active']/div[2]");}

    ElementsCollection topProducts() {return $$x("//div[@class='product-list__row']/ul/li");}

}
