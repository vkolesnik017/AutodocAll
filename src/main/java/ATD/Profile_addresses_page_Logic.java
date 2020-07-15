package ATD;

import com.codeborne.selenide.CollectionCondition;
import io.qameta.allure.Step;
import org.testng.Assert;

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

    @Step("Click delivery edit button. Profile_addresses_page")
    public Profile_addresses_page_Logic clickDeliveryEditButton() {
        deliveryEditButton().click();
        return this;
    }

    @Step("Click billing edit button. Profile_addresses_page")
    public Profile_addresses_page_Logic clickBillingEditButton() {
        billingEditButton().click();
        return this;
    }

    @Step("Checks presence shipping address form. Profile_addresses_page")
    public Profile_addresses_page_Logic checkPresenceShippingAddressForm() {
        addressForm().shouldBe(visible);
        return this;
    }

    @Step("Checks absence shipping address form. Profile_addresses_page")
    public Profile_addresses_page_Logic checkAbsenceShippingAddressForm() {
        sleep(2000);
        addressForm().shouldNotBe(visible);
        return this;
    }

    @Step("Click on the cancel button. Profile_addresses_page")
    public Profile_addresses_page_Logic clickBackupCancelBtn() {
        sleep(2000);
        backupCancelBtn().click();
        return this;
    }

    @Step(":from Profile_addresses_page")
    public Profile_addresses_page_Logic checkForTextInBlockTopTitle(String expectedText) {
        new Profile_plus_page_Logic().checkForTextInBlockTopTitle(expectedText);
        return this;
    }

    @Step(":from Profile_addresses_page")
    public Profile_addresses_page_Logic checkPresenceClientID() {
        new Profile_plus_page_Logic().checkPresenceClientID();
        return this;
    }

    @Step(":from Profile_addresses_page")
    public Profile_addresses_page_Logic checkPresenceHeaderBlockAndElementInside() {
        new Profile_plus_page_Logic().checkPresenceHeaderBlockAndElementInside();
        return this;
    }

    @Step("Checks name page {expectedName} and presence icon. Profile_addresses_page")
    public Profile_addresses_page_Logic checkNamePageAndPresenceIcon(String expectedName) {
        namePage().shouldBe(visible);
        namePage().shouldHave(text(expectedName));
        return this;
    }

    @Step("Checks billing address block and its elements. Profile_addresses_page")
    public Profile_addresses_page_Logic checkBillingAddressBlockAndItsElement() {
        billingBlockName().shouldBe(visible);
        billingAddressBlock().shouldBe(visible);
        billingEditButton().shouldBe(visible);
        deleteBillingAddressBtn().shouldBe(visible);
        addBillingAddressBtn().shouldBe(visible);
        return this;
    }

    @Step("Checks delivery address block and its elements. Profile_addresses_page")
    public Profile_addresses_page_Logic checkDeliveryAddressBlockAndItsElement() {
        deliveryBlockName().shouldBe(visible);
        deliveryAddressBlock().shouldBe(visible);
        deliveryEditButton().shouldBe(visible);
        deleteDeliveryAddressBtn().shouldBe(visible);
        addDeliveryAddressBtn().shouldBe(visible);
        return this;
    }

    @Step("Checks presence buttons under form edit address. Profile_addresses_page")
    public Profile_addresses_page_Logic checkPresenceBtnUnderFormEditAddress() {
        saveBtn().shouldBe(visible);
        useBtnAsMainAddress().shouldBe(visible);
        backupCancelBtn().shouldBe(visible);
        return this;
    }

    @Step("Click use button as main address. Profile_addresses_page")
    public Profile_addresses_page_Logic clickUseBtnAsMainAddress() {
        sleep(2000);
        useBtnAsMainAddress().click();
        return this;
    }

    @Step("Click save button. Profile_addresses_page")
    public Profile_addresses_page_Logic clickSaveBtn() {
        saveBtn().click();
        return this;
    }

    @Step("Checks presence and close pop up update. Profile_addresses_page")
    public Profile_addresses_page_Logic checkPresenceAndClosePopUpUpdate() {
        popUpUpdate().shouldBe(visible);
        closePopUpUpdate().click();
        return this;
    }

    @Step("Delete delivery address. Profile_addresses_page")
    public Profile_addresses_page_Logic deleteDeliveryAddress() {
        deleteDeliveryAddressBtn().click();
        return this;
    }

    @Step("Delete billing address. Profile_addresses_page")
    public Profile_addresses_page_Logic deleteBillingAddress() {
        deleteBillingAddressBtn().click();
        return this;
    }

    @Step("Click add delivery address button. Profile_addresses_page")
    public Profile_addresses_page_Logic clickBtnAddDeliveryAddress() {
        addDeliveryAddressBtn().click();
        return this;
    }

    @Step("Click add billing address button. Profile_addresses_page")
    public Profile_addresses_page_Logic clickBtnAddBillingAddress() {
        addBillingAddressBtn().click();
        return this;
    }

    @Step("Filling fields address. Profile_addresses_page")
    public Profile_addresses_page_Logic fillingFieldsAddress(String name, String surname, String street, String house,
                                                             String comment, String postalCode, String city, String phone) {
        addressForm().shouldBe(visible);
        fieldName().setValue(name);
        fieldSurname().setValue(surname);
        fieldAddressStreet().setValue(street);
        fieldAddressHouse().setValue(house);
        fieldAddressComment().setValue(comment);
        fieldPostalCode().setValue(postalCode);
        fieldCity().setValue(city);
        fieldPhone().setValue(phone);
        return this;
    }

    @Step("Checks presence main address label. Profile_addresses_page")
    public Profile_addresses_page_Logic checkPresenceMainAddressLabel() {
        mainAddressLabel().shouldBe(visible);
        return this;
    }

    @Step("Checks that the radio button Herr is active. Profile_addresses_page")
    public Profile_addresses_page_Logic checkThatRadioBtnHerrIsActive() {
        Assert.assertTrue(radioBtnHerr().isSelected());
        return this;
    }

    @Step("Choosing delivery country {country} in address form. Profile_addresses_page")
    public Profile_addresses_page_Logic chooseCountryInAddressForm(String country) {
        countryFromSelector(country).shouldBe(visible).click();
        return this;
    }

    @Step("Get the number of user billing addresses. Profile_addresses_page")
    public int getNumberOfUserBillingAddress() {
        return dataUsersInBillingAddressBlock().size();
    }

    @Step("Checks that the number of billing addresses {oldCountAddressUser} has increased. Profile_addresses_page")
    public Profile_addresses_page_Logic checkThatNumberOfBillingAddressHasIncreased(int oldCountAddressUser) {
        dataUsersInBillingAddressBlock().shouldHave(CollectionCondition.sizeGreaterThan(oldCountAddressUser));
        return this;
    }


    @Step("Get the number of user delivery addresses. Profile_addresses_page")
    public int getNumberOfUserDeliveryAddress() {
        return dataUsersInDeliveryAddressBlock().size();
    }

    @Step("Checks that the number of delivery addresses has increased. Profile_addresses_page")
    public Profile_addresses_page_Logic checkThatNumberOfDeliveryAddressHasIncreased(int oldCountAddressUser) {
        dataUsersInDeliveryAddressBlock().shouldHave(CollectionCondition.sizeGreaterThan(oldCountAddressUser));
        return this;
    }

    @Step("Checks absence address delivery. Profile_addresses_page")
    public Profile_addresses_page_Logic checkAbsenceAddressDelivery() {
        unitWithoutDeliveryAddressData().shouldBe(visible);
        return this;
    }

    @Step("Checks absence billing address. Profile_addresses_page")
    public Profile_addresses_page_Logic checkAbsenceBillingAddress() {
        unitWithoutBillingAddressData().shouldBe(visible);
        return this;
    }

    @Step("Get daily delivery addresses. Profile_addresses_page")
    public String getDailyDeliveryAddress() {
        return String.valueOf(userDeliveryAddressData().getText());
    }

    @Step("Get daily billing addresses. Profile_addresses_page")
    public String getDailyBillingAddress() {
        return String.valueOf(userBillingAddressData().getText());
    }

    @Step("Get name tab addresses. Profile_addresses_page")
    public String getNameTabAddresses() {
        return profileAddressBtn().getText();
    }
}