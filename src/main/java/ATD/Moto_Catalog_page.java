package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
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

    SelenideElement headlineBlockInSelector() {return $x("//div[@class='block-select-car__head-car']");}

    SelenideElement imageInHeadlineInSelector() {return $x("//div[@class='block-select-car__head-car']/span[1]");}

    SelenideElement textOfHeadlineInSelector() {return $x("//div[@class='block-select-car__head-car']/span[2]");}
}
