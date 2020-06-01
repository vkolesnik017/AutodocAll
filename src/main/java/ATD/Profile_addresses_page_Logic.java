package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.sleep;

public class Profile_addresses_page_Logic extends Profile_addresses_page {

    @Step("Check presence billing address block. Profile_addresses_page")
    public Profile_addresses_page_Logic checkPresenceBillingAddressBlock() {
        billingAddressBlock().shouldBe(visible);
        return this;
    }

    @Step("Check presence delivery address block. Profile_addresses_page")
    public Profile_addresses_page_Logic checkPresenceDeliveryAddressBlock() {
        deliveryAddressBlock().shouldBe(visible);
        return this;
    }

    @Step("Click shipping edit button. Profile_addresses_page")
    public Profile_addresses_page_Logic clickShippingEditButton() {
        shippingEditButton().click();
        return this;
    }

    @Step("Checks presence shipping address form. Profile_addresses_page")
    public Profile_addresses_page_Logic checkPresenceShippingAddressForm() {
        shippingAddressForm().shouldBe(visible);
        return this;
    }

    @Step("Checks absence shipping address form. Profile_addresses_page")
    public Profile_addresses_page_Logic checkAbsenceShippingAddressForm() {
        sleep(2000);
        shippingAddressForm().shouldNotBe(visible);
        return this;
    }

    @Step("Click on the cancel button. Profile_addresses_page")
    public Profile_addresses_page_Logic clickBackupCancelBtn() {
        backupCancelBtn().click();
        return this;
    }
}