package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

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

    SelenideElement motorInVerticalCarSelector() {return $(byName("model_id"));}

    SelenideElement btnSearchInVerticalCarSelector() {
        return $x("//a[@class='truck_submit js--lkw_selector-btn-submit']");}

    SelenideElement tooltipForFieldInVerticalCarSelector() { return $x("//div[@class='validation-tooltip popup-error-select']");}

    SelenideElement logoInHeader() {return $x("//a[@class='header__logo-main']/img");}

    SelenideElement iconOfTruckInHeadlineOfSelector() {return $x("//span[@class='car-icon']");}

    SelenideElement titleOfTruckInHeadlineOfSelector() {return $x("//div[@class='block-select-car__head-car']/span[2]");}

    SelenideElement resetBtnInVerticalCarSelector() {return $x("//a[@class='block-select-car__update hidden js--btn_reset_form']");}


}
