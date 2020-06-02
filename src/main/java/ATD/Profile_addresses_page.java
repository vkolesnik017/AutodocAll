package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Profile_addresses_page {

    SelenideElement billingAddressBlock() {
        return $x("//div[@class='adrese_fields']//ul[1]");
    }

    SelenideElement billingBlockName() {
        return $x("//div[@class='adrese_fields']//div[@class='box_title'][1]");
    }

    SelenideElement billingEditButton() {
        return $x("//ul[1]//a[@class='btn']");
    }

    SelenideElement deleteBillingAddressBtn() {
        return $x("//ul[1]//a[@ng-click='address.delete()']");
    }

    SelenideElement addBillingAddressBtn() {
        return $x("//*[@id='content']/div[2]/div[1]/div[3]/a[1]");
    }

    SelenideElement deliveryAddressBlock() {
        return $x("//div[@class='adrese_fields']//ul[2]");
    }

    SelenideElement deliveryBlockName() {
        return $x("//div[@class='adrese_fields']//div[@class='box_title'][2]");
    }

    SelenideElement deliveryEditButton() {
        return $x("//ul[2]//a[@class='btn']");
    }

    SelenideElement deliveryAddressForm() {
        return $x("//div[@class='left']//form");
    }

    SelenideElement deleteDeliveryAddressBtn() {
        return $x("//ul[2]//a[@ng-click='address.delete()']");
    }

    SelenideElement addDeliveryAddressBtn() {
        return $x("//*[@id='content']/div[2]/div[1]/div[3]/a[2]");
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

    SelenideElement nameOfClient() {
        return $(byXpath("//div[@class='name_cash']//span[@class='name']"));
    }

    SelenideElement depositResultLabel() {
        return $x("//span[@class='memb_balance']");
    }

    SelenideElement depositAmount() {
        return $x("//span[@class='green']");
    }

    SelenideElement namePage() {
        return $x("//div[@class='title']");
    }

    SelenideElement saveBtn() {
        return $x("//div[@class='save']");
    }

    SelenideElement useBtnAsMainAddress() {
        return $x("//a[@ng-click='setMain(editor, address)']");
    }

    SelenideElement backupCancelBtn() {
        return $x("//a[@class='btn address-cancel r']");
    }

    SelenideElement popUpUpdate() {
        return $x("//div[@id='popup_update']");
    }

    SelenideElement closePopUpUpdate() {
        return $x("//div[@class='buttons-inner']");
    }

    SelenideElement fieldName() {
        return $x("//input[@name='address[name]']");
    }

    SelenideElement fieldSurname() {
        return $x("//input[@name='address[surname]']");
    }

    SelenideElement fieldAddressStreet() {
        return $x("//input[@name='address[street]']");
    }

    SelenideElement fieldAddressHouse() {
        return $x("//input[@name='address[house]']");
    }

    SelenideElement fieldAddressComment() {
        return $x("//input[@name='address[comment]']");
    }

    SelenideElement fieldPostalCode() {
        return $x("//input[@name='address[postcode]']");
    }

    SelenideElement  fieldCity() {
        return $x("//input[@name='address[city]']");
    }

    SelenideElement countrySelector() {
        return $x("//select[@name='address[countryId]']");
    }

    SelenideElement fieldPhone() {
        return $x("//input[@name='address[phone]']");
    }

    SelenideElement mainAddressLabel() {
        return $x("//a[@ng-if='address.isMain()']");
    }
}
