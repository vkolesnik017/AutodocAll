package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class Profile_plus_page {

    SelenideElement logoInSiteBar() {
        return $x("//div[@class='logo border']/a");
    }

    SelenideElement nameOfUser() {return $x("//span[@class='name']");}

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

    SelenideElement myVehiclesBlock() {return $x("//li[@class='vehicle_link ']/a");}

    SelenideElement wishListBlock() {return $x("//li[@class='wishlist_link ']/a");}

    SelenideElement countOfAddedVehicleInGarageIcon() {return $x("//span[@class='header-garage__count header-garage__count--added']");}

    ElementsCollection visibleAddedVehicleInGarageAtHeader() {return $$x("//div[@class='header-garage__logged-check']");}

    SelenideElement garagePopUpInHeader() {return $x("//div[@class='header-garage__logged-check']");}

    SelenideElement linkMoreInPopUpOfGarageInHeader() {return $x("//div[@class='wrapper-helper']/a");}

    SelenideElement topTitleBlock() {
        return $x("//div[@class='top_title']");
    }

    SelenideElement clientID() {
        return $(byXpath("//div[@class='top_title']/span"));
    }

    SelenideElement headerPrivateRoomBlock() {
        return $(".name_cash");
    }

    SelenideElement nameOfClient() {
        return $(byXpath("//div[@class='name_cash']//span[@class='name']"));
    }

    SelenideElement depositResultLabel() {
        return $x("//span[@class='memb_balance']");
    }

    SelenideElement depositAmount() {
        return $x("//span[@class='green']");
    }

    SelenideElement  countOfVehicleInIconOfGarageInHeader() {return $x("//span[@class='header-garage__count header-garage__count--added']");}

    SelenideElement idOfVehicleInGaragePopUp(String idOfVehicle) {return $x("//div[@class='wrapper-radio']/label[@for='"+idOfVehicle+"']");}

    SelenideElement headerGarageIcon(){ return $x("//div[@class='header-garage js-header-garage']"); }

    SelenideElement popUpOfGarageInHeader() {return $x("//div[@class='header-garage__logged-header']");}

    SelenideElement btnOpenInputForAddVinNumInPopUpOfGarage() {return $x("//div[@class='wrapper-vin']/p");}

    ElementsCollection btnOpenInputForAddVinNumInPopUpOfGarageHeader() {
        return $$x("//div[@class='wrapper-vin']/p");
    }

    ElementsCollection inputForAddVinNumInPopUpOfGarageHeader() {
        return $$x("//div[@class='vin-number-drop-input']/input");
    }

    SelenideElement inputForAddVinNumInPopUpOfGarage() {
        return $x("//div[@class='vin-number-drop-input']/input");
    }

    ElementsCollection btnAddVinNumInPopUpOfGarageHeader() {
        return $$x("//div[@class='vin-number-drop-button js-save-vin-number']");
    }

    SelenideElement btnAddVinNumInPopUpOfGarage() {
        return $x("//div[@class='vin-number-drop-button js-save-vin-number']");
    }

    ElementsCollection addedVinNumInPopUpOfGarageHeader() {
        return $$x("//div[@class='wrapper-vin']//span");
    }

    SelenideElement addedVinNumInPopUpOfGarage() {
        return $x("//div[@class='wrapper-vin']//span");
    }

    SelenideElement btnToCatalogRouteInPopUp() {
        return $x("//div[@class='header-garage__logged-check']//a");
    }
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

}
