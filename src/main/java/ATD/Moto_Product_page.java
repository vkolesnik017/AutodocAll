package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

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

       SelenideElement infoPopUp() {return $x("//div[@class='popup_content']");}

    SelenideElement btnSearchWithSelectedMoto() {return $x("//button[contains(@class,'search_button ')]");}

    SelenideElement productCompatibilityHeader() {return $x("//p[contains(text(),'Dieses Produkt passt zu Ihrem ')]");}

    SelenideElement motoBrandFromInfoMessage(){return $x("//div[@class='car-match-block car-match-block--moto']//b"); }

    SelenideElement btnResetOfSelector() {return $(byId("reset_selector_form"));}

}
