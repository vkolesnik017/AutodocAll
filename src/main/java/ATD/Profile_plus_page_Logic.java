package ATD;

import Common.CommonMethods;
import Common.DataBase;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static Common.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Profile_plus_page_Logic extends Profile_plus_page {

    @Step("Get client ID(digit). Profile_plus_page")
    public String getDigitClientId() {
        String digit = new Profile_page_Logic().getDigitClientId();
        return digit;
    }

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
        myVehiclesBtn().click();
        return page(Profile_garage_page_Logic.class);
    }

    @Step("Transition to wishList block. Profile_plus_page")
    public Profile_wishlist_page_Logic goToWishListBlock() {
        wishListBtn().shouldBe(visible).click();
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
        nameOfUser().shouldBe(visible);
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

    @Step(": for Profile_plus_page")
    public Profile_plus_page_Logic clickOnGarageIconInHeader() {
        new Main_page_Logic().clickOnGarageIconInHeader();
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

            String nameBtn = btnOpenInputForAddVinNumInPopUpOfGarageInHeader().getText();
        btnOpenInputForAddVinNumInPopUpOfGarageInHeader().shouldHave(text(nameBtn)).click();
        inputForAddVinNumInPopUpOfGarageInHeader().shouldBe(visible).setValue(vinNum);
        btnAddVinNumInPopUpOfGarageInHeader().shouldBe(visible).click();
        addedVinNumInPopUpOfGarageInHeader().shouldHave(text(vinNum));
        return this;
    }

    @Step("Check presence unsubscribe button. Profile_plus_page")
    public Profile_plus_page_Logic checkPresenceUnsubscribeButton() {
        unsubscribeButton().shouldBe(visible);
        return this;
    }

    @Step("Check presence unsubscribe button and click on it. Profile_plus_page")
    public Profile_plus_page_Logic clickBtnUnsubscribeButton() {
        unsubscribeButton().shouldBe(visible);
        unsubscribeButton().click();
        unsubscribePopUp().shouldBe(visible);
        return this;
    }

    @Step("Get the end of the premium account. Profile_plus_page")
    public String getEndDateOfPremiumAccount() {
        return premiumAccountExpirationDate().getText().replaceAll("(.+\\s)(\\d.+)(\\s.+)", "$2");
    }

    @Step("Checks presence expected first text {expectedText} inside unsubscribe popup. Profile_plus_page")
    public Profile_plus_page_Logic checkPresenceExpectedFirstTextInsideUnsubscribePopUp(SelenideElement locator, String expectedText) {
        locator.shouldHave(exactText(expectedText));
        return this;
    }

    @Step("Click on the No button to close the popup unsubscribe. Profile_plus_page")
    public Profile_plus_page_Logic clickBtnNoToClosePopUpUnsubscribe() {
        btnNoInUnsubscribePopUp().click();
        return this;
    }

    @Step("go to the Catalog route from the popup of garage in header. Profile_plus_page")
    public Maker_car_list_page_Logic checkRedirectToCatalogRoute() throws SQLException {
        btnToCatalogRouteInPopUp().click();
        CommonMethods.checkingContainsUrl(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE","main", "maker_car_list18"));
        return page(Maker_car_list_page_Logic.class);
    }


    @Step("check work deleting vin num in popup of garage in header. Profile_plus_page")
    public Profile_plus_page_Logic checkWorkDeletingVinNumInPopupOfGarage(String vinNum) {
        String nameBtn = btnOpenInputForAddVinNumInPopUpOfGarageInHeader().getText();
        btnOpenInputForAddVinNumInPopUpOfGarageInHeader().click();
        inputForAddVinNumInPopUpOfGarageInHeader().shouldBe(visible).clear();
        btnAddVinNumInPopUpOfGarageInHeader().shouldBe(visible).click();
        addedVinNumInPopUpOfGarageInHeader().shouldNotHave(text(vinNum));
        btnOpenInputForAddVinNumInPopUpOfGarageInHeader().shouldHave(text(nameBtn));
        return this;
    }

    @Step("presence Ready to send sticker. Profile_plus_page")
    public Profile_plus_page_Logic presenceReadyToSendSticker() {
        readyToSendSticker().shouldBe(visible);
        return this;
    }

    @Step("presence Priority order status. Profile_plus_page")
    public Profile_plus_page_Logic presencePriorityOrderStatus(int size) {
        headlineOfPriorityOrderStatus().shouldHaveSize(size);
        return this;
    }

    @Step("absence of Safe Order block. Profile_plus_page")
    public Profile_plus_page_Logic absenceSOBlock() {
        for (int i = 0; i < headlineOfPriorityOrderStatus().size(); i++) {
            headlineOfPriorityOrderStatus().get(i).shouldNotHave(text("20 %"));
        }
        return this;
    }

    @Step("absence Of Safe Order option. Profile_plus_page")
    public Profile_plus_page_Logic absenceOfSoOption() {
        List<String> iconTitle = servicePackageIcons().stream().map(n -> n.getAttribute("src").replaceAll("^.+\\/", "").replaceAll("\\..+$", "")).collect(Collectors.toList());
        Assert.assertFalse(iconTitle.contains("return-icon"));
        return this;
    }

    @Step("Checks that when you switch the subscription limit, the prices for packages change. Profile_plus_page")
    public Profile_plus_page_Logic checkPriceSwitchingOnSubscriptionPacks() {
        ArrayList<String> priceForYear = new ArrayList<>();
        ArrayList<String> priceForMonthly = new ArrayList<>();
        activeYearPackSwitch().shouldHave(attribute("checked"));
        switchMonthlyPack().shouldBe(visible).click();
        priceForMonthly.add(priceForBasicPack().getText());
        switchYearPack().shouldBe(visible).click();
        priceForYear.add(priceForBasicPack().getText());
        Assert.assertNotEquals(priceForYear, priceForMonthly);
        priceForYear.clear();
        priceForMonthly.clear();

        switchMonthlyPack().shouldBe(visible).click();
        priceForMonthly.add(priceForOptimalPack().getText());
        switchYearPack().shouldBe(visible).click();
        priceForYear.add(priceForOptimalPack().getText());
        Assert.assertNotEquals(priceForYear, priceForMonthly);
        priceForYear.clear();
        priceForMonthly.clear();

        switchMonthlyPack().shouldBe(visible).click();
        priceForMonthly.add(priceForProfPack().getText());
        switchYearPack().shouldBe(visible).click();
        priceForYear.add(priceForProfPack().getText());
        Assert.assertNotEquals(priceForYear, priceForMonthly);
        priceForYear.clear();
        priceForMonthly.clear();

        switchMonthlyPack().shouldBe(visible).click();
        priceForMonthly.add(priceForExpertPack().getText());
        switchYearPack().shouldBe(visible).click();
        priceForYear.add(priceForExpertPack().getText());
        Assert.assertNotEquals(priceForYear, priceForMonthly);
        priceForYear.clear();
        priceForMonthly.clear();
        return this;
    }

    @Step("Checking the function of the Packet Activation button. Profile_plus_page")
    public Profile_plus_page_Logic checkFunctionOfPackActivationButton() {
        activationPackBtn().shouldHaveSize(4);
        for (int i = 0; i < activationPackBtn().size(); i++) {
            activationPackBtn().get(i).click();
            checkingContainsUrl("subscription/billing");
            back();
        }
        activationTrialPackBtn().click();
        checkingContainsUrl("subscription/billing");
        return this;
    }

    @Step("Checks presence elements in header Autodoc plus section. Profile_plus_page")
    public Profile_plus_page_Logic checkElementsInHeaderAtdPlusSection() {
        visibilityOfUsersName();
        logoInPlusBlock().shouldBe(visible);
        titleTextInPlusBlock().shouldBe(visible);
        textInPlusBlock().shouldBe();
        activeYearPackSwitch().shouldHave(attribute("checked"));
        switchMonthlyPack().shouldBe(visible);
        switchYearPack().shouldBe(visible);
        return this;
    }

    @Step("Checks presence elements in Basic plus-subscription blocks. Profile_plus_page")
    public Profile_plus_page_Logic checkElementInBasicPlusBlock(String expectedNamePack, String expectedNoteOnPrice) {
        basicPackBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        nameBasicPack().shouldBe(visible).shouldHave(text(expectedNamePack));
        priceForBasicPack().shouldBe(visible);
        noteOnPricePerBasicPack().shouldBe(visible).shouldHave(text(expectedNoteOnPrice));
        blockWithBasicPackOptions().shouldBe(visible);
        activationBasicPackBtn().shouldBe(visible).shouldHave(attribute("href"));
        return this;
    }

    @Step("Checks presence unavailable options in Basic plus-subscription blocks. Profile_plus_page")
    public Profile_plus_page_Logic checkUnavailableOptionsInBasicPack() {
        basicPackBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        allOptionIconInBasicPack().shouldHaveSize(6);
        unavailableOption2xInBasicPack().shouldBe(visible);
        unavailableOptionDiscountInBasicPack().shouldBe(visible);
        unavailableOptionSubscribeInBasicPack().shouldBe(visible);
        return this;
    }

    @Step("Checks presence elements in Optimal plus-subscription blocks. Profile_plus_page")
    public Profile_plus_page_Logic checkElementInOptimalPlusBlock(String expectedNamePack, String expectedNoteOnPrice) {
        optimalPackBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        nameOptimalPack().shouldBe(visible).shouldHave(text(expectedNamePack));
        priceForOptimalPack().shouldBe(visible);
        noteOnPricePerOptimalPack().shouldBe(visible).shouldHave(text(expectedNoteOnPrice));
        blockWithOptimalPackOptions().shouldBe(visible);
        activationOptimalPackBtn().shouldBe(visible).shouldHave(attribute("href"));
        activationTrialPackBtn().shouldBe(visible).shouldHave(attribute("href"));
        return this;
    }

    @Step("Checks presence unavailable options in Optimal plus-subscription blocks. Profile_plus_page")
    public Profile_plus_page_Logic checkUnavailableOptionsInOptimalPack() {
        optimalPackBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        allOptionIconInOptimalPack().shouldHaveSize(6);
        unavailableOptionDiscountInOptimalPack().shouldBe(visible);
        unavailableOptionSubscribeInOptimalPack().shouldBe(visible);
        return this;
    }

    @Step("Checks presence elements in Professional plus-subscription blocks. Profile_plus_page")
    public Profile_plus_page_Logic checkElementInProfPlusBlock(String expectedNamePack, String expectedNoteOnPrice) {
        profPackBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        nameProfPack().shouldBe(visible).shouldHave(text(expectedNamePack));
        priceForProfPack().shouldBe(visible);
        noteOnPricePerProfPack().shouldBe(visible).shouldHave(text(expectedNoteOnPrice));
        blockWithProfPackOptions().shouldBe(visible);
        activationProfPackBtn().shouldBe(visible).shouldHave(attribute("href"));
        return this;
    }

    @Step("Checks presence elements in Expert plus-subscription blocks. Profile_plus_page")
    public Profile_plus_page_Logic checkElementInExpertPlusBlock(String expectedNamePack, String expectedNoteOnPrice) {
        expertPackBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        nameExpertPack().shouldBe(visible).shouldHave(text(expectedNamePack));
        priceForExpertPack().shouldBe(visible);
        noteOnPricePerExpertPack().shouldBe(visible).shouldHave(text(expectedNoteOnPrice));
        blockWithExpertPackOptions().shouldBe(visible);
        mainIconExpertPack().shouldBe(visible);
        activationExpertPackBtn().shouldBe(visible).shouldHave(attribute("href"));
        return this;
    }

    @Step("Checks absence Subscribe option in all plus-subscription blocks for FR shop. Profile_plus_page")
    public Profile_plus_page_Logic checkAbsencePersonalDiscountOption() {
        basicPackBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        unavailableOptionSubscribeInBasicPack().shouldNotBe(visible);
        optimalPackBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        unavailableOptionSubscribeInOptimalPack().shouldNotBe(visible);
        profPackBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        subscribeIconOptionInProfPack().shouldNotBe(visible);
        expertPackBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        subscribeIconExpertInProfPack().shouldNotBe(visible);
        return this;
    }
}
