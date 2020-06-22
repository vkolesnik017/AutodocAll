package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class Moto_Catalog_model_page {

    SelenideElement motoSelectorBlock() {return $x("//div[contains(@class,'home-select-car--moto')]");}

    SelenideElement motoSelectorMainForm() {return $(byId("selector-wrapper"));}

    SelenideElement motoSelectorInCloseCondition() {return $x("//div[@class='catalog-title__change-car ']");}

    SelenideElement brandOfMotoSelector() {
        return $(byId("form_maker_id"));
    }

    SelenideElement btnSearchAtSelector() {
        return $x("//a[contains(@class,'search_button')]");
    }

    SelenideElement toolTipForBrandFieldInSelector() {
        return $(byId("selector-error-tooltip"));
    }

    SelenideElement toolTipForModelFieldInSelector() {
        return $(byId("selector-error-tooltip-model"));
    }

    SelenideElement modelOfMotoSelector() {
        return $(byId("form_model_id"));
    }

    SelenideElement toolTipForMotorFieldInSelector() {
        return $(byId("form_car_id"));
    }

    SelenideElement motorOfMotoSelector() {
        return $(byId("form_car_id"));
    }

    SelenideElement mainFormOfSelector() {return $(byId("selector-wrapper"));}

    SelenideElement btnResetOfSelector() {return $(byId("reset_selector_form"));}

    ElementsCollection breadCrumbsLinks() {return $$x("//div[@class='steps breadcrumbs']/ul/li");}

    SelenideElement iconOfMotoBrandInBreadCrumbs() {return $x("//li[@class='step_1 active parts_step_1']//img");}

    SelenideElement titleOfMotoBrandInBreadCrumbs() {return $x("//li[@class='step_1 active parts_step_1']//a");}

    SelenideElement mainHeadline() {return $x("//div[@class='catalog-title__block']/h2");}

    ElementsCollection parentsCategoriesOfTecDocCatalog() {return $$x("//div[@class='car-parts-categories__item-link']");}

    ElementsCollection  childCategoriesFirstLevelBlock() {return $$x("//div[@class='car-parts-categories-modal__level1 js-height-compare']");}

    ElementsCollection childCategoriesFirstLevel(int position) {return $$x("(//div[contains(@class,'car-parts-categories-modal__level1')])["+position+"]/ul[@class='car-parts-categories-modal__link-list']//a");}

}
