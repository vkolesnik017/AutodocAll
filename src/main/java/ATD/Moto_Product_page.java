package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class Moto_Product_page {

    SelenideElement motoSelectorBlock() {
        return $x("//div[@class='atd-carselector']");
    }

    SelenideElement brandOfMotoSelector() {
        return $(byId("form_maker_id"));
    }

    SelenideElement btnSearchAtSelector() {
        return $x("//button[@class='block-select-car__button submit search_button']");
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

    SelenideElement motorOfMotoSelector() {
        return $(byId("form_car_id"));
    }

    SelenideElement toolTipForMotorFieldInSelector() {
        return $(byId("selector-error-tooltip-car"));
    }

    SelenideElement infoPopUp() {
        return $x("//div[@class='popup_content']");
    }

    SelenideElement btnSearchWithSelectedMoto() {
        return $x("//button[contains(@class,'search_button ')]");
    }

    SelenideElement productCompatibilityHeader() {
        return $x("//p[contains(text(),'Dieses Produkt passt zu Ihrem ')]");
    }

    SelenideElement motoBrandFromInfoMessage() {
        return $x("//div[@class='car-match-block car-match-block--moto']//b");
    }

    SelenideElement btnResetOfSelector() {
        return $(byId("reset_selector_form"));
    }

    SelenideElement compatibilityMotoBlock() {
        return $x("//div[@class='product-info-block-accordion js--roll-up']");
    }

    SelenideElement linkOfCompatibilityMotoAndProduct() {
        return $x("//div[@class='accordion-selected']");
    }

       ElementsCollection listOfMotoAtCompatibilityBlock() {
       return $$x("//div[@class='product-info-block-accordion js--roll-up']/div[contains(@class,'product-info-block--moto')]//div[@class='accordion-content']/ul/li/b").filter(visible);}


    SelenideElement compatibilityMotoBrand() {
        return $x("//div[@class='product-info-block-accordion js--roll-up']/div[contains(@class,'product-info-block--moto')]//div[@class='accordion-button']/i");
    }

    SelenideElement titleOfProduct() {
        return $x("//div[@class='product-block__description__title product-block__equal-height-wrap']/h2/span");
    }

    SelenideElement  compatibilityModelBlock() {return $x("//div[@class='accordion-button active']/following-sibling::div/ul");}

    ElementsCollection breadCrumbsLinks() {return $$x("//div[@class='steps breadcrumbs']/ul/li//a");}

    SelenideElement iconOfCatalogBrandInBreadCrumbs() {return $x("//li[@class='step_1 active parts_step_1']//img");}

    SelenideElement horizontalMotoSelector() {return $x("//div[@class='atd-carselector__content']");}

    SelenideElement btnCloseTooltipOfBrandFieldAtSelector() {return $x("//div[@class='tooltiptext-close js-tooltiptext-close']");}

    SelenideElement motoLinkFromCompatibilityBlock() {return $x("//div[@class='accordion-selected']/p");}

    SelenideElement incompatibilityMotoMessage() {return $x("//div[@class='car-match-block car-match-block--moto car-match-block--false']/p");}

    SelenideElement  breadCrumbsBlock() {return $x("//div[@class='steps breadcrumbs']");}

    ElementsCollection motoTitleFromCompatibilityBlock() {return $$x("//div[@class='product-info-block-accordion js--roll-up']/div[contains(@class,'product-info-block--moto')]//div[@class='accordion-button']/i/following-sibling::span");}

}
