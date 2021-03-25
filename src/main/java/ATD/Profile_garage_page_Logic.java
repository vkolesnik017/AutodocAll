package ATD;

import Common.DataBase;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Common.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.sizeLessThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Profile_garage_page_Logic extends Profile_garage_page {

    @Step("openSelectorBlock .Profile_garage_page")
    public Profile_garage_page_Logic openSelectorBlock() {
        btnAddedAuto().click();
        selectorBlock().shouldBe(visible);
        return this;
    }

    @Step("Click tracks tab in selector. Profile_garage_page")
    public Profile_garage_page_Logic clickTrucksTab() {
        trucksTab().click();
        return this;
    }

    @Step("select Truck in selector .Profile_garage_page")
    public Profile_garage_page_Logic selectTruckInSelector(String brand, String model, String motor) {
        markeOfTruckInSelector().shouldBe(visible).selectOptionByValue(brand);
        modelOfTruckInSelector().shouldBe(visible).selectOptionByValue(model);
        motorOfTruckInSelector().shouldBe(visible).selectOptionByValue(motor);
        btnSearchOfSelector().click();
        return this;
    }

    @Step(": for Profile_garage_page")
    public Profile_garage_page_Logic selectVehicleCarInSelector(String brandName, String modelNumberValue, String typeNumberValue) {
        new Main_page_Logic().chooseBrandModelTypeInSelector(brandName, modelNumberValue, typeNumberValue);
        btnSearchInSelector().click();
        return this;
    }

    @Step("presence added auto .Profile_garage_page")
    public Profile_garage_page_Logic presenceAddedAuto() {
        addedAutoBlock().shouldBe(visible);
        return this;
    }

    @Step("Checks absence added auto. Profile_garage_page")
    public Profile_garage_page_Logic checkAbsenceAddedAuto() {
        addedAutoBlock().shouldNotBe(visible);
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
        catalogLincInsideСarInfoBlock().shouldHave(attribute("href"));
        infoInsideTheBlock().shouldHave(text(expectedText));
        imgBlockInsideCarInfoBlock().shouldBe(visible);
        return this;
    }

    @Step("Get text with information about the added vehicle. Profile_garage_page")
    public ArrayList<String> getTextWithInformationAboutAddedVehicle() {
        ArrayList<String> infoText = new ArrayList<>();
        ElementsCollection infoInsideTheBlock = infoInsideTheBlockAllAddedVehicle();
        String text = null;
        for (SelenideElement info : infoInsideTheBlock) {
            text = info.getText();
            infoText.add(text);
        }
        return infoText;
    }

    @Step("Checks text with added vehicle information with text in selector. Profile_garage_page")
    public Profile_garage_page_Logic checkTextWithAddedVehicleWithTextInSelector() {
        ArrayList<String> infoTextSelector = new ArrayList<>();
        ElementsCollection infoInsideTheBlock = infoInsideTheBlockAllAddedVehicle();
        ElementsCollection editBtn = btnEditOfAllAddedAuto();
        String infoText, brand, model, motor;
        for (int i = 0; i < infoInsideTheBlock.size(); i++) {
            infoText = infoInsideTheBlock.get(i).getText().toUpperCase();
            editBtn.get(i).click();
            sleep(3000);
            brand = brandInput().getText().toUpperCase();
            model = modelInput().getText().toUpperCase();
            motor = motorInput().getText().toUpperCase();
            infoTextSelector.add(brand);
            infoTextSelector.add(model);
            infoTextSelector.add(motor);
            for (int j = 0; j < infoTextSelector.size(); j++) {
                Assert.assertTrue(infoText.contains(infoTextSelector.get(j)));
            }
            infoTextSelector.clear();
            closeSelector().click();
            sleep(3000);
        }
        return this;
    }

    @Step("Edit all vehicle. Profile_garage_page")
    public Profile_garage_page_Logic editAllVehicle() {
        ElementsCollection editBtn = btnEditOfAllAddedAuto();
        for (int i = 0; i < editBtn.size(); i++) {
            editBtn.get(i).click();
            sleep(3000);
            if (activeCarTab().isDisplayed()) {
                selectVehicleCarInSelector("AUDI", "433", "1040");
            }
            if (activeTrucksTab().isDisplayed()) {
                selectTruckInSelector("132", "5549", "1008471");
            }
            if (activeMotoTab().isDisplayed()) {
                selectVehicleInSelector("4057", "12027", "101565");
            }
            sleep(3000);
        }
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

    @Step("check Count of added vehicle in My garage block. Profile_garage_page")
    public Profile_garage_page_Logic checkCountOfAddedVehicleInMyGarageBlock(int expectedSizeOfAddedVehicle) {
        availabilityOfAddedVehicleInMyGarage().shouldHaveSize(expectedSizeOfAddedVehicle);
        return this;
    }

    @Step("check title in car info block. Profile_garage_page")
    public Profile_garage_page_Logic checkTitleInCarInfoBlock(String titleOfVehicle) {
        titleOfVehicleInCarInfoBlock().get(0).shouldHave(text(titleOfVehicle));
        return this;
    }

    @Step("transition To Catalog. Profile_garage_page")
    public Profile_garage_page_Logic transitionToCatalog(String expectedRoutes) throws SQLException {
        DataBase db = new DataBase("ATD");
        List<String> routes = Arrays.asList(expectedRoutes.split(","));
        for (int i = 0; i < routes.size(); i++) {
            findSpareParts().get(i).shouldBe(visible).click();
            switch (i) {
                case 0:
                    checkingContainsUrl(db.getRouteByRouteName("DE", routes.get(i)));
                    break;
                case 1:
                    checkingContainsUrl(db.getRouteByRouteName("DE", routes.get(i)));
                    break;
                case 2:
                    checkingContainsUrl(db.getRouteByRouteName("DE", routes.get(i)));
                    break;
                default:
                    break;
            }
            back();
        }

        return this;
    }

}
