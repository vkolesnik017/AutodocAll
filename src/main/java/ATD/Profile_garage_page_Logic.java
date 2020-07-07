package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.*;

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
    public Profile_garage_page_Logic checkCountOfAddedAutoInGarageIcon() {
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
}
