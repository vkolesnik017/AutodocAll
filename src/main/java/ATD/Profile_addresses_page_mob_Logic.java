package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static org.testng.AssertJUnit.assertNull;

public class Profile_addresses_page_mob_Logic extends Profile_addresses_page_mob {

    @Step("Clicking billing Address. Profile_addresses_page_mob")
    public Profile_addresses_page_mob_Logic clickBillingAddress() {
        billingAddress().click();
        return this;
    }

    @Step("Checking checked checkbox. Profile_addresses_page_mob")
    public Profile_addresses_page_mob_Logic checkingCheckedCheckbox() {
        subscribeCheckbox().shouldHave(attribute("checked", "true"));
        return this;
    }

    @Step("Checking unchecked checkbox. Profile_addresses_page_mob")
    public Profile_addresses_page_mob_Logic checkingUnCheckedCheckbox() {
        plzBilling().scrollIntoView(true);
        String nullCheckbox = subscribeCheckbox().getAttribute("checked");
        assertNull(nullCheckbox);
        return this;
    }

    @Step("Clicking checkbox. Profile_addresses_page_mob")
    public Profile_addresses_page_mob_Logic clickCheckbox() {
        plzBilling().scrollIntoView(true);
        clickLocatorSubscribeCheckbox().click();
        return this;
    }

    @Step("Checking success popup. Profile_addresses_page_mob")
    public Profile_addresses_page_mob_Logic checkingSuccessPopup() {
        successPopup().shouldBe(appear).shouldHave(text("Die Daten wurden aktualisiert"));
        return this;
    }

    @Step("Filling fields in billing address. Profile_addresses_page_mob")
    public Profile_addresses_page_mob_Logic fillingFieldsInBillingAddress() {
        ortBilling().setValue("autotest");
        strasseBilling().setValue("autotest");
        hausnummerBilling().setValue("autotest");
        plzBilling().setValue("autotest");
        telBilling().setValue("200+002");
        return this;
    }

    @Step("Filling fields in shipping address. Profile_addresses_page_mob")
    public Profile_addresses_page_mob_Logic fillingFieldsInShippingAddress() {
        ortShipping().setValue("autotest");
        strasseShipping().setValue("autotest");
        hausnummerShipping().setValue("autotest");
        plzShipping().setValue("autotest");
        telShipping().setValue("200+002");
        return this;
    }

    @Step("Filling name and vorname field. Profile_addresses_page_mob")
    public Profile_addresses_page_mob_Logic fillingNameVornameField() {
        name().setValue("autotest");
        vorname().setValue("autotest");
        return this;
    }

    @Step("Clicking submit. Profile_addresses_page_mob")
    public Profile_addresses_page_mob_Logic clickSubmit() {
        submitBtn().click();
        return this;
    }

    @Step("Check presence icon user id. Profile_addresses_page_mob_Logic")
    public Profile_addresses_page_mob_Logic checkPresenceIconUserId() {
        iconUserId().shouldBe(visible);
        return this;
    }


}
