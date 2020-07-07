package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class Profile_plus_page_Logic extends Profile_plus_page {

    @Step("visibility of users name .Profile_plus_page")
    public Profile_plus_page_Logic visibilityOfUsersName() {
        nameOfUser().shouldBe(visible);
        return this;
    }

    @Step("Transition to profile addresses page. Profile_plus_page")
    public Profile_addresses_page_Logic goToProfileAddressesPage() {
        profileAddressBtn().click();
        return page(Profile_addresses_page_Logic.class);
    }

    @Step("Transition to profile bank page. Profile_plus_page")
    public Profile_bank_page_Logic goToProfileBankPage() {
        profileBankBtn().click();
        return page(Profile_bank_page_Logic.class);
    }

    @Step("Transition to setting page. Profile_plus_page")
    public Profile_setting_page_Logic goToSettingPage() {
        profileSettingBtn().click();
        return page(Profile_setting_page_Logic.class);
    }

    @Step("Transition to bonus system page. Profile_plus_page")
    public Profile_bonusSystem_page_Logic goToBonusSystemPage() {
        profileBonusSystemBtn().click();
        return page(Profile_bonusSystem_page_Logic.class);
    }


    @Step("Checks absence bonus label. Profile_plus_page")
    public Profile_plus_page_Logic checkAbsenceBonusLabel() {
        profileBonusSystemBtn().shouldNotBe(visible);
        return this;
    }

    @Step("Transition to deposit page. Profile_plus_page")
    public Profile_deposit_page_Logic goToDepositPage() {
        profileDepositBtn().click();
        return page(Profile_deposit_page_Logic.class);
    }

    @Step("Transition to My Order page. Profile_plus_page")
    public Profile_orders_page_Logic goToMyOrdersPage() {
        profileMyOrderBtn().click();
        return page(Profile_orders_page_Logic.class);
    }

    @Step("Transition to My vehicles block. Profile_plus_page")
    public Profile_garage_page_Logic goToMyVehiclesBlock() {
        myVehiclesBlock().click();
        return page(Profile_garage_page_Logic.class);
    }

    @Step("check count of added vehicles in garage at header  . Profile_plus_page")
    public Profile_plus_page_Logic checkCountOfAddedVehiclesInGarageAtHeader(int countOfVehicle) {
        int addedVehicle = Integer.parseInt(countOfAddedVehicleInGarageIcon().getText());
        Assert.assertTrue(addedVehicle > countOfVehicle);
        return this;
    }


    @Step("check visible added vehicle in popUp of garage in header  . Profile_plus_page")
    public Profile_plus_page_Logic checkVisibleAddedVehicleInPopUpOfGarageHeader(int expectedSize) {
        countOfAddedVehicleInGarageIcon().click();
        visibleAddedVehicleInGarageAtHeader().shouldHaveSize(expectedSize);
        garagePopUpInHeader().shouldBe(visible);
        return this;
    }


    @Step("go to garage block through popUp in header by click on link More  . Profile_plus_page")
    public Profile_garage_page_Logic goToGarageBlockThroughPopUpInHeader() {
        linkMoreInPopUpOfGarageInHeader().shouldBe(visible).click();
        return page(Profile_garage_page_Logic.class);
    }



}
