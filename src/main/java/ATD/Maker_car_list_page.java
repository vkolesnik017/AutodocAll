package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class Maker_car_list_page {

    public SelenideElement headingOfSearchByCatalog() {
        return $(".page_search_title");
    }

    SelenideElement selectorInCloseCondition() {
        return $(".catalog-title__change-car");
    }

    SelenideElement mainFormOfSelector() {
        return $(byId("selector-wrapper"));
    }

    SelenideElement markeInSelector() {
        return $(byId("form_maker_id"));
    }

    SelenideElement modelInSelector() {
        return $(byId("form_model_id"));
    }

    SelenideElement motorInSelector() {
        return $(byId("form_car_id"));
    }

    SelenideElement btnSearchOfSelector() {
        return $(".search_button");
    }

    SelenideElement garageIconInHeader() {
        return $x("//div[@class='header-garage js-header-garage']");
    }

    SelenideElement popUpOfGarageInHeader() {
        return $x("//div[contains(@class,'header-garage__notlogged')]");
    }

    ElementsCollection urlsOfAddedVehicleInPopUpOfGarageInHeader() {
        return $$x("//div[contains(@class,'header-garage__notlogged')]//a[contains(@class,'history-cars-rows__link')]");
    }

    SelenideElement btnClearVehicleListInPopUpOfGarageIcon() {return $x("//div[@class='header-garage__logged-clear js-clear-recent-car']/span");}
}
