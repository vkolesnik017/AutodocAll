package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

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

    @Step("Transition to wishList block. Profile_plus_page")
    public Profile_wishlist_page_Logic goToWishListBlock() {
        wishListBlock().shouldBe(visible).click();
        return page(Profile_wishlist_page_Logic.class);
    }

    @Step("check count of added vehicles in garage at header  . Profile_plus_page")
    public Profile_plus_page_Logic checkCountOfAddedVehiclesInGarageAtHeader(int countOfVehicle) {
        int addedVehicle = Integer.parseInt(countOfAddedVehicleInGarageIcon().getText());
        Assert.assertTrue(addedVehicle > countOfVehicle);
        return this;
    }

    @Step("check count of added vehicles in garage at header from icon. Profile_plus_page")
    public Profile_plus_page_Logic checkCountOfAddedVehiclesInGarageAtHeaderFromIcon(int countOfVehicle) {
        int addedVehicle = Integer.parseInt(countOfAddedVehicleInGarageIcon().getText());
        Assert.assertTrue(addedVehicle == countOfVehicle);
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

    @Step(":from. Profile_plus_page")
    public Main_page_Logic logOutClick() {
        new Main_page_Logic().logOutClick();
        checkingContainsUrl("www.autodoc.de");
        return page(Main_page_Logic.class);
    }

    @Step("Checks absence of button with transition to the bonus page. Profile_plus_page")
    public Profile_plus_page_Logic checkAbsenceBtnWithTransitionToBonusPage() {
        profileBonusSystemBtn().shouldNotBe(visible);
        return this;
    }

    @Step("Checks for text {expectedText} in a block Top Title. Profile_plus_page")
    public Profile_plus_page_Logic checkForTextInBlockTopTitle(String expectedText) {
        topTitleBlock().shouldHave(text(expectedText));
        return this;
    }

    @Step("Checks presence client ID. Profile_plus_page")
    public Profile_plus_page_Logic checkPresenceClientID() {
        clientID().shouldBe(visible);
        return this;
    }

    @Step("Checks presence heder private room block and the elements inside. Profile_plus_page")
    public Profile_plus_page_Logic checkPresenceHeaderBlockAndElementInside() {
        headerPrivateRoomBlock().shouldBe(visible);
        nameOfClient().shouldBe(visible);
        depositResultLabel().shouldBe(visible);
        depositAmount().shouldBe(visible);
        return this;
    }

    @Step("Transition to main page of the site. Profile_plus_page")
    public Main_page_Logic goToMainPage() {
        logoInSiteBar().click();
        return page(Main_page_Logic.class);
    }

    @Step("Logs out of the account and logs in as a previously registered user. Profile_plus_page")
    public Profile_plus_page_Logic logOuAndLoginWithUser(String mail) {
        new Main_page_Logic().logOuAndLoginWithUser(mail);
        return this;
    }


    @Step("Login in header with mail {mail} and transition to profile plus page and go back. Profile_plus_page")
    public Profile_plus_page_Logic loginToProfilePlusPageAndBack(String email) {
        new Main_page_Logic().loginAndTransitionToProfilePlusPage(email).visibilityOfUsersName();
        return this;
    }

    @Step("update of page. Profile_plus_page")
    public Profile_plus_page_Logic updateOfPage() {
        refresh();
        return this;
    }

    @Step("update of page. Profile_plus_page")
    public Profile_plus_page_Logic checkCountOfVehicleInIconOfGarage(String expectedCountOfVehicle) {
        countOfVehicleInIconOfGarageInHeader().shouldBe(visible).shouldHave(exactText(expectedCountOfVehicle));
        return this;
    }

    @Step("click on Garage icon in header. Profile_plus_page")
    public Profile_plus_page_Logic clickOnGarageIconInHeader() {
        headerGarageIcon().shouldBe(visible).click();
        popUpOfGarageInHeader().shouldBe(visible);
        return this;
    }

    @Step("click on Garage icon in header. Profile_plus_page")
    public Profile_plus_page_Logic selectVehicleInGaragePopUp(String idOfVehicle) {
        idOfVehicleInGaragePopUp(idOfVehicle).shouldBe(visible).click();
        return this;
    }

    @Step("check work adding and removing vin num in popup of garage in header. Profile_plus_page")
    public Profile_plus_page_Logic checkWorkAddAndRemoveVinNumInPopupOfGarage(String vinNum) {
        for (int i = 0; i < visibleAddedVehicleInGarageAtHeader().size(); i++) {
            String nameBtn = btnOpenInputForAddVinNumInPopUpOfGarageHeader().get(i).getText();
            btnOpenInputForAddVinNumInPopUpOfGarageHeader().get(i).shouldHave(text(nameBtn)).click();
            inputForAddVinNumInPopUpOfGarageHeader().get(i).shouldBe(visible).setValue(vinNum);
            btnAddVinNumInPopUpOfGarageHeader().get(i).shouldBe(visible).click();
            addedVinNumInPopUpOfGarageHeader().get(i).shouldHave(text(vinNum));
            btnOpenInputForAddVinNumInPopUpOfGarageHeader().get(i).click();
            inputForAddVinNumInPopUpOfGarageHeader().get(i).shouldBe(visible).clear();
            btnAddVinNumInPopUpOfGarageHeader().get(i).shouldBe(visible).click();
            addedVinNumInPopUpOfGarageHeader().get(i).shouldNotHave(text(vinNum));
            btnOpenInputForAddVinNumInPopUpOfGarageHeader().get(i).shouldHave(text(nameBtn));
        }
        return this;
    }
    @Step("check work adding vin num in popup of garage in header. Profile_plus_page")
    public Profile_plus_page_Logic checkWorkAddingVinNumInPopupOfGarage(String vinNum) {
            String nameBtn = btnOpenInputForAddVinNumInPopUpOfGarage().getText();
            btnOpenInputForAddVinNumInPopUpOfGarage().shouldHave(text(nameBtn)).click();
            inputForAddVinNumInPopUpOfGarage().shouldBe(visible).setValue(vinNum);
            btnAddVinNumInPopUpOfGarage().shouldBe(visible).click();
            addedVinNumInPopUpOfGarage().shouldHave(text(vinNum));
        return this;
    }

    @Step("go to the Catalog route from the popup of garage in header. Profile_plus_page")
    public Maker_car_list_page_Logic  checkRedirectToCatalogRoute() throws SQLException {
        btnToCatalogRouteInPopUp().click();
        CommonMethods.checkingContainsUrl(new Common.DataBase("ATD").getFullRouteByRouteName("prod", "DE", "maker_car_list18"));
        return page(Maker_car_list_page_Logic.class);
    }
}
