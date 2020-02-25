package ATD;

import com.codeborne.selenide.Condition;

import static org.testng.AssertJUnit.assertNull;

public class Profile_addresses_page_mob_Logic extends Profile_addresses_page_mob {

    public Profile_addresses_page_mob_Logic clickBillingAddress() {
        billingAddress().click();
        return this;
    }

    public Profile_addresses_page_mob_Logic checkingCheckedCheckbox() {
        subscribeCheckbox().shouldHave(Condition.attribute("checked", "true"));
        return this;
    }

    public Profile_addresses_page_mob_Logic checkingUnCheckedCheckbox() {
        plzBilling().scrollIntoView(true);
        String nullCheckbox = subscribeCheckbox().getAttribute("checked");
        assertNull(nullCheckbox);
        return this;
    }

    public Profile_addresses_page_mob_Logic clickCheckbox() {
        plzBilling().scrollIntoView(true);
        clickLocatorSubscribeCheckbox().click();
        return this;
    }

    public Profile_addresses_page_mob_Logic checkingSuccessPopup() {
        successPopup().shouldBe(Condition.appear).shouldHave(Condition.text("Die Daten wurden aktualisiert"));
        return this;
    }

    public Profile_addresses_page_mob_Logic fillingFieldsInBillingAddress() {
        ortBilling().setValue("autotest");
        strasseBilling().setValue("autotest");
        hausnummerBilling().setValue("autotest");
        plzBilling().setValue("autotest");
        telBilling().setValue("200+002");
        return this;
    }

    public Profile_addresses_page_mob_Logic fillingFieldsInShippingAddress() {
        ortShipping().setValue("autotest");
        strasseShipping().setValue("autotest");
        hausnummerShipping().setValue("autotest");
        plzShipping().setValue("autotest");
        telShipping().setValue("200+002");
        return this;
    }

    public Profile_addresses_page_mob_Logic fillingNameVornameField() {
        name().setValue("autotest");
        vorname().setValue("autotest");
        return this;
    }

    public Profile_addresses_page_mob_Logic clickSubmit() {
        submitBtn().click();
        return this;
    }


}
