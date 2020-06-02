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

    SelenideElement backupCancelBtn() {
        return $x("//a[@class='btn address-cancel r']");
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
}
