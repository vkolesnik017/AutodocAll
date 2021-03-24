package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class Profile_garage_page {

    SelenideElement myVehiclesBlock() {
        return $x("//a[@data-ga-action='Sidebar_MyVehicles']");
    }

    SelenideElement btnAddedAuto() {
        return $x("//a[contains(@class,'add-car-link')]");
    }

    SelenideElement selectorBlock() {
        return $(byId("selector-my-garage"));
    }

    SelenideElement trucksTab() {
        return $x("//li[@class='popup-selector__tabs-item select-lkw ']");
    }

    SelenideElement activeCarTab() {
        return $x("//li[@class='popup-selector__tabs-item select-pkw active']");
    }

    SelenideElement activeTrucksTab() {
        return $x("//li[@class='popup-selector__tabs-item select-lkw active']");
    }

    SelenideElement activeMotoTab() {
        return $x("//li[@class='popup-selector__tabs-item select-moto active']");
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

    SelenideElement btnSearchOfSelector() {
        return $x("//a[@class='truck_submit js--lkw_selector-btn-submit-garage']");
    }

    SelenideElement closeSelector() {
        return $x("//div[@class='popup-selector__content active']/div[@class='popup-selector__content-close']");
    }

    SelenideElement brandInput() {
        return $x("//div[@class='popup-selector__content active']//select[@name='maker_id']");
    }

    SelenideElement modelInput() {
        return $x("//div[@class='popup-selector__content active']//select[@name='model_id']");
    }

    SelenideElement motorInput() {
        return $x("//div[@class='popup-selector__content active']//select[@name='car_id']");
    }

    //Locator is suitable for LKW and Moto types
    SelenideElement btnSearchInSelector() {
        return $x("//a[contains(@class,'search_button')]");
    }

    SelenideElement addedAutoBlock() {
        return $x("//div[@id='profile-cars-list']//li");
    }

    SelenideElement imageOfAddedAuto() {
        return $x("//div[@class='car_info']/img");
    }

    SelenideElement btnSearchOfAtoParts() {
        return $x("//div[@class='buttons profile_buttons']/a[1]");
    }

    SelenideElement btnEditOfAddedAuto() {
        return $x("//div[@class='buttons profile_buttons']/a[2]");
    }

    ElementsCollection btnEditOfAllAddedAuto() {
        return $$x("//div[@class='buttons profile_buttons']/a[2]");
    }

    SelenideElement titleOfAddedAuto() {return $x("//div[@class='leeft']/a");}

    SelenideElement idOfAddedAuto() {
        return $x("//ul[@class='list_vehicle list']/li");
    }

    SelenideElement addedAutoFromGarageIcon() {
        return $x("//div[@class='header-garage__logged-check']//label");
    }

    SelenideElement countOfAddedAutoInGarageIcon() {return $x("//div[@class='header-garage js-header-garage']/span");}

    SelenideElement btnDeleteAddedAuto() {
        return $x("//div[@class='header-garage__logged-check-del js-remove-car']");
    }

    SelenideElement motoTab() {
        return $x("//li[@class='popup-selector__tabs-item select-moto ']");
    }

    SelenideElement popUpOfGarageIcon() {return $x("//div[@class='header-garage__logged-header']");}

    SelenideElement modelOfVehicleInSelector() {
        return $x("//form[@id='top-select-garage-moto']/div/div[2]/div[2]/select");
    }

    SelenideElement motorOfVehicleInSelector() {
        return $x("//form[@id='top-select-garage-moto']/div/div[2]/div[3]/select");
    }

    SelenideElement markeOfVehicleInSelector() {return $x("//form[@id='top-select-garage-moto']/div/div[2]/div[1]/select");}

    SelenideElement addedVehicleBlock() {
        return $(byId("prof_cars"));
    }

    SelenideElement emptyVehicleList() {
        return $x("//ul[@class='list_vehicle list']");
    }

    SelenideElement btnSearchVehicleInSelector() {return $x("//a[@class='submit search_button ripple-out']");}

    ElementsCollection addedAutoFromPopUpInHeader() {
        return $$x("//div[@class='wrapper-radio']/label");
    }

    ElementsCollection addedVehicleList() {return $$x("//ul[@class='list_vehicle list']/li");}

    SelenideElement carInfoBlock() {
        return $x("//div[@class='car_info']");
    }

    SelenideElement infoInsideTheBlock() {
        return $x("//div[@class='car_info']//div[@class='leeft']");
    }

    ElementsCollection infoInsideTheBlockAllAddedVehicle() {
        return $$x("//div[@class='car_info']//div[@class='leeft']");
    }

    SelenideElement mainLogoInHeader() {return $x("//a[@class='header__logo-main']/img");}

    SelenideElement nameOfUser() {return $x("//span[@class='name']");}

    ElementsCollection vehicleFromViewedHistoryBlockInMyGarage() {return $$x("//div[@class='history-cars-rows']/a[2]");}

    ElementsCollection btnSaveVehicleFromViewedHistory() {return $$x("//a[@class='history-cars-rows__button active js-add-car-to-garage']");}

    ElementsCollection titleOfVehicleFromViewedHistory() {return $$x("//div[@class='history-cars-rows__name']");}

     ElementsCollection btnDeleteVehicleInMyGaragePopUp() {return $$x("//div[contains(@class,'js-remove-car')]");}

    SelenideElement btnAddVehicleInMyGaragePopUp() {return $x("//div[@class='header-garage__notlogged-button js-selector-add-car']");}
    SelenideElement imgBlockInsideCarInfoBlock() {
        return $x("//div[@class='car_info']/img");
    }

    SelenideElement catalogLincInsideСarInfoBlock() {
        return $x("//div[@class='car_info']//div[@class='leeft']/a");
    }

    SelenideElement btnAddedCarFromOrderToGarage() {
        return $x("//a[@class='searched-car se plus-img']/span");
    }

    ElementsCollection availabilityOfAddedVehicleInMyGarage() {return $$x("//div[@id='profile-cars-list']//li");}

    ElementsCollection titleOfVehicleInCarInfoBlock() {return $$x("//div[@class='leeft']/a");}

    ElementsCollection findSpareParts() {return $$x("//div[@class='buttons profile_buttons']/a[1]");}
}
