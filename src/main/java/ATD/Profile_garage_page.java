package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class Profile_garage_page {

    SelenideElement myVehiclesBlock() {return $x("//a[@data-ga-action='Sidebar_MyVehicles']");}

    SelenideElement btnAddedAuto() {
        return $x("//a[contains(@class,'add-car-link')]");
    }

    SelenideElement selectorBlock() {
        return $(byId("selector-my-garage"));
    }

    SelenideElement trucksTab() {
        return $x("//li[@class='popup-selector__tabs-item select-lkw ']");
    }

    SelenideElement activeTrucksTab() {
        return $x("//li[@class='popup-selector__tabs-item select-lkw active']");
    }

    SelenideElement markeOfTruckInSelector() {
        return $x("//div[@class='block-select-car tooltip-block tooltip-block--left']/div[2]/select");
    }

    SelenideElement modelOfTruckInSelector() {
        return $x("//div[@class='block-select-car tooltip-block tooltip-block--left']/div[3]/select");
    }

    SelenideElement motorOfTruckInSelector() {
        return $x("//div[@class='block-select-car tooltip-block tooltip-block--left']/div[4]/select");
    }

    SelenideElement btnSearchOfSelector() {return $x("//a[@class='truck_submit js--lkw_selector-btn-submit-garage']");}

    SelenideElement addedAutoBlock() {return $x("//div[@id='profile-cars-list']//li");}

    SelenideElement imageOfAddedAuto() {return $x("//div[@class='car_info']/img");}

    SelenideElement titleOfAddedAuto() {return $x("//div[@class='leeft']/a");}

    SelenideElement btnSearchOfAtoParts() {return $x("//div[@class='buttons profile_buttons']/a[1]");}

    SelenideElement btnEditOfAddedAuto() {return $x("//div[@class='buttons profile_buttons']/a[2]");}

    SelenideElement countOfAddedAutoInGarageIcon() {return $x("//div[@class='header-garage js-header-garage']/span");}

    SelenideElement idOfAddedAuto() {return $x("//ul[@class='list_vehicle list']/li");}

    SelenideElement addedAutoFromGarageIcon() {return $x("//div[@class='header-garage__logged-check']//label");}

    SelenideElement popUpOfGarageIcon() {return $x("//div[@class='header-garage__logged-header']");}

    SelenideElement btnDeleteAddedAuto() {return $x("//div[@class='header-garage__logged-check-del js-remove-car']");}

    SelenideElement motoTab() {return $x("//li[@class='popup-selector__tabs-item select-moto ']");}

    SelenideElement markeOfVehicleInSelector() {return $x("//form[@id='top-select-garage-moto']/div/div[2]/div[1]/select");}

    SelenideElement modelOfVehicleInSelector() {return $x("//form[@id='top-select-garage-moto']/div/div[2]/div[2]/select");}

    SelenideElement motorOfVehicleInSelector() {return $x("//form[@id='top-select-garage-moto']/div/div[2]/div[3]/select");}

    SelenideElement btnSearchVehicleInSelector() {return $x("//a[@class='submit search_button ripple-out']");}

    SelenideElement addedVehicleBlock() {return $(byId("prof_cars"));}

    ElementsCollection addedVehicleList() {return $$x("//ul[@class='list_vehicle list']/li");}

    ElementsCollection addedAutoFromPopUpInHeader() {return $$x("//div[@class='wrapper-radio']/label");}

    SelenideElement mainLogoInHeader() {return $x("//a[@class='header__logo-main']/img");}
}
