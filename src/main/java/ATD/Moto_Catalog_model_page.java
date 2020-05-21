package ATD;

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
        return $x("//a[@class='submit search_button']");
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
}
