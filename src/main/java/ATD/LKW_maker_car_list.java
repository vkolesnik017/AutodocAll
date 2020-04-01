package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
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
    SelenideElement infoLinkInHeadline() {return $(byId("selected_car_info"));}

    SelenideElement infoPopUp() {return $x("//div[@class='selected_car_info_popap']");}

    SelenideElement titleOfInfoPopUp() {return $x("//div[@class='selected_car_info_popap']//b[1]");}
}
