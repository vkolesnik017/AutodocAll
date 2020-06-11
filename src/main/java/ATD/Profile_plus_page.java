package ATD;

import com.codeborne.selenide.SelenideElement;

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
}
