package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

public class LKW_makers_page {
    SelenideElement iconOfTruckInHeadlineOfSelector() {
        return $x("//span[@class='car-icon']");
    }

    SelenideElement titleOfTruckInHeadlineOfSelector() {
        return $x("//div[contains(@class,'block-select-car__head-car--lkw')]/span[2]");
    }

    SelenideElement markeOfVerticalTruckSelector() {
        return $(byName("maker_id"));
    }

    SelenideElement modelOfVerticalTruckSelector() {
        return $(byName("model_id"));
    }

    SelenideElement motorOfVerticalTruckSelector() {
        return $(byName("car_id"));
    }

    SelenideElement buttonSuchenOfVerticaltruckSelector() {
        return $x("//a[@class='truck_submit js--lkw_selector-btn-submit']");
    }

    SelenideElement brandsListBlock() {
        return $x("//div[@class='cont']");
    }

    ElementsCollection brandsInListBlock() {
        return $$x("//ul[contains(@class,'cars_list')]//li");
    }

    ElementsCollection imageOfBrandsList() {
        return $$x("//ul[contains(@class,'cars_list')]//li//img");
    }

    ElementsCollection titleOfBrandsList() {
        return $$x("//span[@class='name']");
    }

    ElementsCollection modelCountsOfBrandsList() {
        return $$x("//span[@class='count']");
    }

    SelenideElement brandOfTruckInTopBlock(String brand) {
        return $x("//span[contains(text(),'" + brand + "')]");
    }

    ElementsCollection brandsListOfTruck() {
        return $$x("//div[@class='cont']//ul/li");
    }

    ElementsCollection countOfModels() {
        return $$x("//div[@class='cont']//ul/li/a/span[2]");
    }

    SelenideElement popUpOfGarageInHeader() {
        return $x("//div[@class='header-garage__logged-header']");
    }

    SelenideElement idOfVehicleInGaragePopUp(String idOfVehicle) {
        return $x("//div[@class='wrapper-radio']/label[@for='" + idOfVehicle + "']");
    }

    SelenideElement headerGarageIcon() {
        return $x("//div[@class='header-garage js-header-garage']");
    }

    SelenideElement resetBtnInVerticalCarSelector() {
        return $(byId("reset_selector_form"));
    }

    SelenideElement arrowForMarkeFiled() {
        return $x("//div[@class='block-select-car__select filled js--lkw_selector-select_basic active']//span[@class='arrow']");
    }

    SelenideElement mainLogoInHeader() {return $x("//a[@class='header__logo-main']/img");}

    SelenideElement mainHeadline() {return $x("//div[@class='top_title no_image']/h2");}
}
