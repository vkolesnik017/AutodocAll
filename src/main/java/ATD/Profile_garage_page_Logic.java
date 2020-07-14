package ATD;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static ATD.CommonMethods.checkingContainsUrl;
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

    @Step(":from Profile_garage_page")
    public Profile_garage_page_Logic checkForTextInBlockTopTitle(String expectedText) {
        new Profile_plus_page_Logic().checkForTextInBlockTopTitle(expectedText);
        return this;
    }

    @Step(":from Profile_garage_page")
    public Profile_garage_page_Logic checkPresenceClientID() {
        new Profile_plus_page_Logic().checkPresenceClientID();
        return this;
    }

    @Step(":from Profile_garage_page")
    public Profile_garage_page_Logic checkPresenceHeaderBlockAndElementInside() {
        new Profile_plus_page_Logic().checkPresenceHeaderBlockAndElementInside();
        return this;
    }

    @Step(":from Profile_garage_page")
    public Profile_garage_page_Logic checkNamePageAndPresenceIcon(String expectedName) {
        new Profile_addresses_page_Logic().checkNamePageAndPresenceIcon(expectedName);
        return this;
    }

    @Step("Checks presence selector add car. Profile_garage_page")
    public Profile_garage_page_Logic checkPresenceSelectorAddCar() {
        btnAddedAuto().shouldBe(visible);
        btnAddedAuto().shouldHave(attribute("href"));
        return this;
    }

    @Step("Checks the text {expectedText} of an empty vehicle list. Profile_garage_page")
    public Profile_garage_page_Logic checkTextOfEmptyVehicleList(String expectedText) {
        emptyVehicleList().shouldHave(text(expectedText));
        return this;
    }

    @Step("Checks presence info car block and elements inside it {expectedText}. Profile_garage_page")
    public Profile_garage_page_Logic checkPresenceInfoCarBlock(String expectedText) {
        carInfoBlock().shouldBe(visible);
        infoInsideTheBlock().shouldHave(attribute("href"));
        infoInsideTheBlock().shouldHave(text(expectedText));
        imgBlockInsideCarInfoBlock().shouldBe(visible);
        return this;
    }

    @Step("Checks transition to catalog page {expectedURL} fom car info block. Profile_garage_page")
    public Maker_car_list_page_Logic checkTransitionToCatalogFromCarInfoBlock(String expectedURL) {
        catalogLincInsideСarInfoBlock().click();
        checkingContainsUrl(expectedURL);
        return page(Maker_car_list_page_Logic.class);
    }

    @Step("Click button added car from order to my garage. Profile_garage_page")
    public Profile_garage_page_Logic clickBtnAddedCarFromOrderToGarage() {
        btnAddedCarFromOrderToGarage().click();
        return this;
    }

    @Step("Open popup my garage in header. Profile_garage_page")
    public Profile_garage_page_Logic openPopUpMyGarageInHeader() {
        countOfAddedAutoInGarageIcon().click();
        popUpOfGarageIcon().shouldBe(visible);
        return this;
    }
}
