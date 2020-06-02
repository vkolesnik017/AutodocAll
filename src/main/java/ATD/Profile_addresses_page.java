package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Profile_addresses_page {

    SelenideElement billingAddressBlock() {
        return $x("//div[@class='adrese_fields']//ul[1]");
    }

    SelenideElement deliveryAddressBlock() {
        return $x("//div[@class='adrese_fields']//ul[2]");
    }

    SelenideElement shippingEditButton() {
        return $x("//li[@id='address_item_12154334']//a[1]");
    }

    SelenideElement shippingAddressForm() {
        return $x("//div[@class='left']//form");
    }

    SelenideElement backupCancelBtn() {
        return $x("//a[@class='btn address-cancel r']");
    }
}
