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
    public Profile_addresses_page_Logic clickDeliveryEditButton() {
        deliveryEditButton().click();
        return this;
    }

    @Step("Checks presence shipping address form. Profile_addresses_page")
    public Profile_addresses_page_Logic checkPresenceShippingAddressForm() {
        deliveryAddressForm().shouldBe(visible);
        return this;
    }

    @Step("Checks absence shipping address form. Profile_addresses_page")
    public Profile_addresses_page_Logic checkAbsenceShippingAddressForm() {
        sleep(2000);
        deliveryAddressForm().shouldNotBe(visible);
        return this;
    }

    @Step("Click on the cancel button. Profile_addresses_page")
    public Profile_addresses_page_Logic clickBackupCancelBtn() {
        sleep(2000);
        backupCancelBtn().click();
        return this;
    }

    @Step("Checks for text {expectedText} in a block Top Title. Profile_addresses_page")
    public Profile_addresses_page_Logic checkForTextInBlockTopTitle(String expectedText) {
        topTitleBlock().shouldHave(text(expectedText));
        return this;
    }

    @Step("Checks presence client ID. Profile_addresses_page")
    public Profile_addresses_page_Logic checkPresenceClientID() {
        clientID().shouldBe(visible);
        return this;
    }

    @Step("Checks presence heder private room block and the elements inside. Profile_addresses_page")
    public Profile_addresses_page_Logic checkPresenceHeaderPrivateRoomBlockAndElementInside() {
        headerPrivateRoomBlock().shouldBe(visible);
        nameOfClient().shouldBe(visible);
        depositResultLabel().shouldBe(visible);
        depositAmount().shouldBe(visible);
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

    @Step("Click add delivery address button. Profile_addresses_page")
    public Profile_addresses_page_Logic clickBtnAddDeliveryAddress()  {
        addDeliveryAddressBtn().click();
        return this;
    }

    @Step("Filling fields address. Profile_addresses_page")
    public Profile_addresses_page_Logic fillingFieldsAddress() {
        deliveryAddressForm().shouldBe(visible);
        fieldName().setValue("autotest");
        fieldSurname().setValue("autotest");
        fieldAddressStreet().setValue("autotest");
        fieldAddressHouse().setValue("autotest");
        fieldAddressComment().setValue("autotest");
        fieldPostalCode().setValue("1111");
        fieldCity().setValue("autotest");
        fieldPhone().setValue("200+002");
        return this;
    }

    @Step("Checks presence main address lable. Profile_addresses_page")
    public Profile_addresses_page_Logic checkPresenceMainAddressLabel() {
        mainAddressLabel().shouldBe(visible);
        return this;
    }
}