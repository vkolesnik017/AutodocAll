package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class Profile_plus_page {

    //Locators for the top of the page
    SelenideElement logoInSiteBar() {
        return $x("//div[@class='logo border']/a/img");
    }

    SelenideElement topTitleBlock() {
        return $x("//div[@class='top_title']");
    }

    SelenideElement clientID() {
        return $(byXpath("//div[@class='top_title']/span"));
    }

    SelenideElement headerPrivateRoomBlock() {
        return $(".name_cash");
    }

    SelenideElement nameOfUser() {
        return $x("//span[@class='name']");
    }

    SelenideElement depositResultLabel() {
        return $x("//span[@class='memb_balance']");
    }

    SelenideElement depositAmount() {
        return $x("//span[@class='green']");
    }

    // Locator for member-plus-block
    SelenideElement logoInPlusBlock() {
        return $x("//div[@class='member-plus-block__text']//img");
    }

    SelenideElement titleTextInPlusBlock() {
        return $x("//div[@class='member-plus-block__text']//p[1]");
    }

    SelenideElement textInPlusBlock() {
        return $x("//div[@class='member-plus-block__text']//p[2]");
    }

    //Locators for the Month/Year switch
    SelenideElement activeYearPackSwitch() {
        return $x("//label[contains(@class,'switch-label')]/../input[@id='two']");
    }

    SelenideElement switchYearPack() {
        return $x("//label[@data-type-name='yearly']/span");
    }

    SelenideElement switchMonthlyPack() {
        return $x("//label[@data-type-name='monthly']/span");
    }

    // Side menu locators
    SelenideElement profileAddressBtn() {
        return $x("//a[@data-ga-action='Sidebar_MyAddresses']");
    }

    SelenideElement profileBankBtn() {
        return $x("//a[@data-ga-action='Sidebar_BankDetails']");
    }

    SelenideElement profileSettingBtn() {
        return $x("//a[@data-ga-action='Sidebar_Settings']");
    }

    SelenideElement profileBonusSystemBtn() {
        return $x("//li[@class='bonus_system_link ']/a");
    }

    SelenideElement profileDepositBtn() {
        return $x("//a[@data-ga-action='Sidebar_MyDepositAccount']");
    }

    SelenideElement profileMyOrderBtn() {
        return $x("//a[@data-ga-action='Sidebar_MyOrders']");
    }

    SelenideElement myVehiclesBtn() {
        return $x("//li[@class='vehicle_link ']/a");
    }

    SelenideElement wishListBtn() {
        return $x("//li[@class='wishlist_link ']/a");
    }

    //Locators for My Garage tab items
    SelenideElement countOfAddedVehicleInGarageIcon() {
        return $x("//span[@class='header-garage__count header-garage__count--added']");
    }

    ElementsCollection visibleAddedVehicleInGarageAtHeader() {
        return $$x("//div[@class='header-garage__logged-check']");
    }

    SelenideElement garagePopUpInHeader() {
        return $x("//div[@class='header-garage__logged-check']");
    }

    SelenideElement linkMoreInPopUpOfGarageInHeader() {
        return $x("//div[@class='wrapper-helper']/a");
    }

    SelenideElement countOfVehicleInIconOfGarageInHeader() {
        return $x("//span[@class='header-garage__count header-garage__count--added']");
    }

    SelenideElement idOfVehicleInGaragePopUp(String idOfVehicle) {
        return $x("//div[@class='wrapper-radio']/label[@for='" + idOfVehicle + "']");
    }

    ElementsCollection btnOpenInputForAddVinNumInPopUpOfGarageHeader() {
        return $$x("//div[@class='wrapper-vin']/p");
    }

    SelenideElement btnOpenInputForAddVinNumInPopUpOfGarageInHeader() {
        return $x("//div[@class='wrapper-vin']/p");
    }

    ElementsCollection inputForAddVinNumInPopUpOfGarageHeader() {
        return $$x("//div[@class='vin-number-drop-input']/input");
    }

    SelenideElement inputForAddVinNumInPopUpOfGarageInHeader() {
        return $x("//div[@class='vin-number-drop-input']/input");
    }

    ElementsCollection btnAddVinNumInPopUpOfGarageHeader() {
        return $$x("//div[@class='vin-number-drop-button js-save-vin-number']");
    }

    SelenideElement btnAddVinNumInPopUpOfGarageInHeader() {
        return $x("//div[@class='vin-number-drop-button js-save-vin-number']");
    }

    ElementsCollection addedVinNumInPopUpOfGarageHeader() {
        return $$x("//div[@class='wrapper-vin']//span");
    }

    SelenideElement addedVinNumInPopUpOfGarageInHeader() {
        return $x("//div[@class='wrapper-vin']//span");
    }

    SelenideElement btnToCatalogRouteInPopUp() {
        return $x("//div[@class='header-garage__logged-check']//a");
    }

    // Locators for tab plus service provided the user has a subscription Plus
    SelenideElement unsubscribeButton() {
        return $x("//a[@data-ga-action='unsubscribe_click']");
    }

    SelenideElement unsubscribePopUp() {
        return $x("//div[@class='popup-cancel-status']");
    }

    SelenideElement btnNoInUnsubscribePopUp() {
        return $x("//button[@data-ga-action='unsubscribe_popup_cancel']");
    }

    public SelenideElement firstTextInsideUnsubscribePopUp() {
        return $x("//div[@class='popup-cancel-status__title']/span[1]");
    }

    public SelenideElement secondTextInsideUnsubscribePopUp() {
        return $x("//div[@class='popup-cancel-status__title']/span[2]");
    }

    SelenideElement premiumAccountExpirationDate() {
        return $x("//div[@class='member-plus-block__bottom']/p");
    }

    SelenideElement readyToSendSticker() {
        return $x("//div[@class='member-plus-block__content-row first']");
    }

    ElementsCollection headlineOfPriorityOrderStatus() {
        return $$x("//div[@class='member-plus-block__content-row']/div/div[2]");
    }

    // Locators for Basic plus-subscription blocks
    SelenideElement basicPackBlock() {
        return $x("//div[contains(@class,'basic')]");
    }

    SelenideElement priceForBasicPack() {
        return $x("//div[contains(@class,'basic')]//div[@class='price']");
    }

    SelenideElement noteOnPricePerBasicPack() {
        return $x("//div[contains(@class,'basic')]//div[@class='price']//span");
    }

    SelenideElement blockWithBasicPackOptions() {
        return $x("//div[contains(@class,'basic')]//div[@class='package__middle']");
    }

    SelenideElement nameBasicPack() {
        return $x("(//div[contains(@class,'basic')]//div[@class='package__top']//p)[1]");
    }

    SelenideElement unavailableOption2xInBasicPack() {
        return $x("//div[contains(@class,'basic')]//div[@class='package__middle-icon gray']//img[@alt='2x-icon']");
    }

    SelenideElement unavailableOptionDiscountInBasicPack() {
        return $x("//div[contains(@class,'basic')]//div[@class='package__middle-icon gray']//img[@alt='discount-icon']");
    }

    SelenideElement unavailableOptionSubscribeInBasicPack() {
        return $x("//div[contains(@class,'basic')]//div[@class='package__middle-icon gray']//img[@alt='subscribe-icon']");
    }

    ElementsCollection allOptionIconInBasicPack() {
        return $$x("//div[contains(@class,'basic')]//div[@class='package__middle']//img");
    }

    SelenideElement activationBasicPackBtn() {
        return $x("//div[contains(@class,'basic')]//div[@class='package__bottom']/a");
    }

    // Locators for Optimal plus-subscription blocks
    SelenideElement optimalPackBlock() {
        return $x("//div[contains(@class,'optimal')]");
    }

    SelenideElement priceForOptimalPack() {
        return $x("//div[contains(@class,'optimal')]//div[@class='price']");
    }

    SelenideElement noteOnPricePerOptimalPack() {
        return $x("//div[contains(@class,'optimal')]//div[@class='price']//span");
    }

    SelenideElement blockWithOptimalPackOptions() {
        return $x("//div[contains(@class,'optimal')]//div[@class='package__middle']");
    }

    SelenideElement nameOptimalPack() {
        return $x("(//div[contains(@class,'optimal')]//div[@class='package__top']//p)[1]");
    }

    SelenideElement unavailableOptionDiscountInOptimalPack() {
        return $x("//div[contains(@class,'optimal')]//div[@class='package__middle-icon gray']//img[@alt='discount-icon']");
    }

    SelenideElement unavailableOptionSubscribeInOptimalPack() {
        return $x("//div[contains(@class,'optimal')]//div[@class='package__middle-icon gray']//img[@alt='subscribe-icon']");
    }

    ElementsCollection allOptionIconInOptimalPack() {
        return $$x("//div[contains(@class,'optimal')]//div[@class='package__middle']//img");
    }

    SelenideElement activationOptimalPackBtn() {
        return $x("//div[contains(@class,'optimal')]//div[@class='package__bottom']/a[1]");
    }

    SelenideElement activationTrialPackBtn() {
        return $x("//div[contains(@class,'optimal')]//div[@class='package__bottom']/a[2]");
    }


    // Locators for Prof plus-subscription blocks
    SelenideElement profPackBlock() {
        return $x("//div[contains(@class,'professional')]");
    }

    SelenideElement priceForProfPack() {
        return $x("//div[contains(@class,'professional')]//div[@class='price']");
    }

    SelenideElement noteOnPricePerProfPack() {
        return $x("//div[contains(@class,'professional')]//div[@class='price']//span");
    }

    SelenideElement blockWithProfPackOptions() {
        return $x("//div[contains(@class,'professional')]//div[@class='package__middle']");
    }

    SelenideElement subscribeIconOptionInProfPack() {
        return $x("//div[contains(@class,'professional')]//img[@alt='subscribe-icon']");
    }

    SelenideElement nameProfPack() {
        return $x("(//div[contains(@class,'professional')]//div[@class='package__top']//p)[1]");
    }

    SelenideElement activationProfPackBtn() {
        return $x("//div[contains(@class,'professional')]//div[@class='package__bottom']/a[1]");
    }

    // Locators for Expert plus-subscription blocks
    SelenideElement expertPackBlock() {
        return $x("//div[@class='package package--expert']");
    }

    SelenideElement priceForExpertPack() {
        return $x("//div[contains(@class,'expert')]//div[@class='price']");
    }

    SelenideElement noteOnPricePerExpertPack() {
        return $x("//div[contains(@class,'expert')]//div[@class='price']//span");
    }

    SelenideElement blockWithExpertPackOptions() {
        return $x("//div[contains(@class,'expert')]//div[@class='package__middle']");
    }

    SelenideElement subscribeIconExpertInProfPack() {
        return $x("//div[contains(@class,'expert')]//img[@alt='subscribe-icon']");
    }

    SelenideElement nameExpertPack() {
        return $x("(//div[contains(@class,'expert')]//div[@class='package__top']//p)[1]");
    }

    SelenideElement mainIconExpertPack() {
        return $x("//div[contains(@class,'expert')]//div[@class='package__expert-icon']");
    }

    SelenideElement activationExpertPackBtn() {
        return $x("//div[contains(@class,'expert')]//div[@class='package__bottom']/a[1]");
    }

    // Common locators for all four subscription blocks
    ElementsCollection servicePackageIcons() {
        return $$(".package__middle-icon img");
    }

    ElementsCollection activationPackBtn() {
        return $$x("//a[contains(@class,'move-to-biiling-process button')]");
    }

}
