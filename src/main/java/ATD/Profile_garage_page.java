package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class Profile_garage_page {

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

    ElementsCollection addedAutoBlock() {
        return $$x("//div[@id='profile-cars-list']//li");
    }

    SelenideElement btnSearchOfSelector() {return $x("//a[@class='truck_submit js--lkw_selector-btn-submit-garage']");}
}
