package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Profile_plus_page {

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

    SelenideElement countOfAddedVehicleInGarageIcon() {return $x("//span[@class='header-garage__count header-garage__count--added']");}

    ElementsCollection visibleAddedVehicleInGarageAtHeader() {return $$x("//div[@class='header-garage__logged-check']");}

    SelenideElement garagePopUpInHeader() {return $x("//div[@class='header-garage__logged-check']");}

    SelenideElement linkMoreInPopUpOfGarageInHeader() {return $x("//div[@class='wrapper-helper']/a");}


}
