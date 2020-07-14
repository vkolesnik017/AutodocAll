package ATD;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.sizeLessThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;

public class Profile_garage_page_Logic extends Profile_garage_page {

    @Step("openSelectorBlock .Profile_garage_page")
    public Profile_garage_page_Logic openSelectorBlock() {
        btnAddedAuto().click();
        selectorBlock().shouldBe(visible);
        return this;
    }

    @Step("select Truck in selector .Profile_garage_page")
    public Profile_garage_page_Logic selectTruckInSelector(String brand, String model, String motor) {
        trucksTab().click();
        markeOfTruckInSelector().shouldBe(visible).selectOptionByValue(brand);
        modelOfTruckInSelector().shouldBe(visible).selectOptionByValue(model);
        motorOfTruckInSelector().shouldBe(visible).selectOptionByValue(motor);
        btnSearchOfSelector().click();
        return this;
    }

    @Step("presence added auto .Profile_garage_page")
    public Profile_garage_page_Logic presenceAddedAuto() {
        addedAutoBlock().shouldBe(visible);
        return this;
    }

    @Step("check elements of added auto .Profile_garage_page")
    public Profile_garage_page_Logic checkElementsOfAddedAuto(String brandOfAuto, String link) {
        imageOfAddedAuto().shouldBe(visible);
        titleOfAddedAuto().shouldHave(text(brandOfAuto)).shouldHave(attribute("href", link));
        btnSearchOfAtoParts().shouldHave(text("Ersatzteile suchen")).shouldHave(attribute("href", link));
        btnEditOfAddedAuto().shouldBe(visible).shouldHave(text("Ändern"));
        return this;
    }

    @Step("check count of added auto in garage icon .Profile_garage_page")
    public Profile_garage_page_Logic comparisonOfAddedVehiclesFromMyGarageAndHeader() {
        Assert.assertEquals(1, Integer.parseInt(countOfAddedAutoInGarageIcon().getText()));
        return this;
    }

    @Step("check PopUp with added auto .Profile_garage_page")
    public Profile_garage_page_Logic checkPopUpWithAddedAuto() {
        String idAutoFromVehicleList = getIdOfAddedAuto();
        countOfAddedAutoInGarageIcon().click();
        popUpOfGarageIcon().shouldBe(visible);
        addedAutoFromGarageIcon().shouldHave(attribute("for", idAutoFromVehicleList));
        return this;
    }

    @Step("get id of added auto .Profile_garage_page")
    public String getIdOfAddedAuto() {
        String idOfAuto = idOfAddedAuto().getAttribute("data-id");
        return idOfAuto;
    }

    @Step("delete of added auto .Profile_garage_page")
    public Profile_garage_page_Logic deleteOfAddedAuto() {
        btnDeleteAddedAuto().shouldBe(visible).click();
        addedAutoFromGarageIcon().shouldNotBe(visible);
        return this;
    }

    @Step("select motorcycle block in selector() .Profile_garage_page")
    public Profile_garage_page_Logic selectMotoBlockInSelector() {
        motoTab().click();
        return this;
    }

    @Step("select vehicle in selector .Profile_garage_page")
    public Profile_garage_page_Logic selectVehicleInSelector(String brand, String model, String motor) {
        markeOfVehicleInSelector().shouldBe(visible).selectOptionByValue(brand);
        modelOfVehicleInSelector().shouldBe(visible).selectOptionByValue(model);
        motorOfVehicleInSelector().shouldBe(visible).selectOptionByValue(motor);
        btnSearchVehicleInSelector().click();
        return this;
    }

    @Step("check list of added vehicle  .Profile_garage_page")
    public Profile_garage_page_Logic checkListOfAddedVehicle(int sizeOfVehicleList) {
        addedVehicleBlock().shouldBe(visible);
        addedVehicleList().shouldHaveSize(sizeOfVehicleList);
        return this;
    }

    @Step("check presence of vehicles in my garage block and header  .Profile_garage_page")
    public Profile_garage_page_Logic checkPresenceOfVehiclesInMyGarageBlockAndHeader() {
        List<Integer> idOfVehiclesFromBlock = new ArrayList<>();
        List<Integer> idOfVehiclesFromHeader = new ArrayList<>();
        addedIdOfVehicleToList(idOfVehiclesFromBlock, addedVehicleList(), "data-id");
        countOfAddedAutoInGarageIcon().shouldBe(visible).click();
        popUpOfGarageIcon().shouldBe(visible);
        addedIdOfVehicleToList(idOfVehiclesFromHeader, addedAutoFromPopUpInHeader(), "for");
        Assert.assertEquals(idOfVehiclesFromBlock, idOfVehiclesFromHeader);
        return this;
    }

    @Step("added id of vehicle to list  .Profile_garage_page")
    public Profile_garage_page_Logic addedIdOfVehicleToList(List<Integer> list, ElementsCollection idOfVehicle, String attribure) {
        for (int i = 0; i < idOfVehicle.size(); i++) {
            list.add(Integer.parseInt(idOfVehicle.get(i).shouldBe(visible).getAttribute(attribure)));
        }
        return this;
    }

    @Step("check list of added vehicle  .Profile_garage_page")
    public Main_page_Logic transitionToMainPage() {
        mainLogoInHeader().click();
        return page(Main_page_Logic.class);
    }

    @Step("Get name tab My vehicle. Profile_garage_page")
    public String getNameTabMyVehicles() {
        return myVehiclesBlock().getText();
    }

    @Step("visibility of users name .Profile_garage_page")
    public Profile_garage_page_Logic visibilityOfUsersName() {
        nameOfUser().shouldBe(visible);
        return this;
    }

    @Step("check title of added vehicle .Profile_garage_page")
    public Profile_garage_page_Logic checkTitleOfAddedVehicle(String link) {
        titleOfAddedAuto().shouldHave(attribute("href", link));
        return this;
    }

    @Step("check presence of vehicles in viewed history block of My garage PopUp .Profile_garage_page")
    public Profile_garage_page_Logic checkPresenceOfVehiclesInViewedHistoryBlockOfMyGaragePopUp(List<String> list, int countOfAddedVehicle) {
        List<String> vehicleFromList = new ArrayList<>();
        for (int i = 0; i < countOfAddedVehicle; i++) {
            vehicleFromList.add(list.get(i));
        }
        List<String> vehicleFromViewedHistory = new ArrayList<>();
        for (int i = 0; i < vehicleFromViewedHistoryBlockInMyGarage().size(); i++) {
            vehicleFromViewedHistory.add(vehicleFromViewedHistoryBlockInMyGarage().get(i).getAttribute("href"));
        }
        Assert.assertTrue(vehicleFromList.containsAll(vehicleFromViewedHistory));
        return this;
    }

    @Step("added vehicle from viewed history to My garage pop-Up .Profile_garage_page")
    public Profile_garage_page_Logic addedVehicleFromViewedHistoryToMyGaragePopUp() {
        int sizeOfVehicleInMyGarage = 0;
        int ss = vehicleFromViewedHistoryBlockInMyGarage().size();
        String currentAddedVehicle;
        List<String> titleOfVehicleFromMyGarage = new ArrayList<>();
        for (int i = 0; i < ss; i++) {
            sizeOfVehicleInMyGarage = addedAutoFromPopUpInHeader().size();
            currentAddedVehicle = titleOfVehicleFromViewedHistory().get(0).getText();
            btnSaveVehicleFromViewedHistory().get(0).click();
            addedAutoFromPopUpInHeader().shouldHave(sizeGreaterThan(sizeOfVehicleInMyGarage));
            for (int j = 0; j < addedAutoFromPopUpInHeader().size(); j++) {
                titleOfVehicleFromMyGarage.add(addedAutoFromPopUpInHeader().get(j).getText());
            }
            Assert.assertTrue(titleOfVehicleFromMyGarage.contains(currentAddedVehicle));
            titleOfVehicleFromMyGarage.clear();
        }
        return this;
    }

    @Step("clear My garage list in Pop-Up .Profile_garage_page")
    public Profile_garage_page_Logic clearMyGarageListInPopUp() {
        int sizeOfPresenceVehicleInMyGarage = 0;
        while (btnDeleteVehicleInMyGaragePopUp().get(0).isDisplayed()) {
            sizeOfPresenceVehicleInMyGarage = btnDeleteVehicleInMyGaragePopUp().size();
            btnDeleteVehicleInMyGaragePopUp().get(0).click();
            btnDeleteVehicleInMyGaragePopUp().shouldHave(sizeLessThan(sizeOfPresenceVehicleInMyGarage));
        }
        btnAddVehicleInMyGaragePopUp().shouldBe(visible);
        return this;
    }
}
