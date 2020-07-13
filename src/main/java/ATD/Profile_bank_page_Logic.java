package ATD;

import io.qameta.allure.Step;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.*;

public class Profile_bank_page_Logic extends Profile_bank_page {

    @Step("Filling field receiver {nameReceiver}. Profile_bank_page")
    public Profile_bank_page_Logic fillingFieldReceiver(String nameReceiver) {
        fieldReceiver().setValue(nameReceiver);
        return this;
    }

    @Step("Filling field receiver {ibanNum}. Profile_bank_page")
    public Profile_bank_page_Logic fillingFieldIBAN(String ibanNum) {
        fieldIBAN().setValue(ibanNum);
        return this;
    }

    @Step("Checks presence and close pop up update. Profile_bank_page")
    public Profile_bank_page_Logic checkPresenceAndClosePopUpUpdate() {
        new Profile_addresses_page_Logic().checkPresenceAndClosePopUpUpdate();
        return this;
    }

    @Step("Checks presence current bank block. Profile_bank_page")
    public Profile_bank_page_Logic checkPresenceCurrentBankBlock() {
        currentBankBlock().scrollTo();
        currentBankBlock().shouldBe(visible);
        return this;
    }

    @Step("Click added new bank info button. Profile_bank_page")
    public Profile_bank_page_Logic clickBtnAddNewBankInfo() {
        btnAddNewBankInfo().click();
        return this;
    }

    @Step("Click save form button. Profile_bank_page")
    public Profile_bank_page_Logic clickSaveFormBtn() {
        saveFormBtn().scrollTo();
        saveFormBtn().click();
        return this;
    }

    @Step("Get name receiver in current bank block. Profile_bank_page")
    public String getNameInReceiverInCurrentBankBlock() {
        return String.valueOf(nameReceiverInCurrentBankBlock().getText());
    }

    @Step("Checks for missing text in the Name Receiver and IBAN num fields. Profile_bank_page")
    public Profile_bank_page_Logic checkForMossingText() {
        nameReceiverInCurrentBankBlock().shouldBe(empty);
        ibanNumInCurrentBankBlock().shouldBe(empty);
        return this;
    }

    @Step("Get IBAN num in current bank block. Profile_bank_page")
    public String getIbanNumInCurrentBankBlock() {
        return String.valueOf(ibanNumInCurrentBankBlock().getText());
    }

    @Step("Click delete bank datta button. Profile_bank_page")
    public Profile_bank_page_Logic clickDeleteBankDataBtn() {
        deleteBankDataBtn().click();
        return this;
    }

    @Step("Checks presence bank info form. Profile_bank_page")
    public Profile_bank_page_Logic checkPresenceBankInfoForm() {
        bankInfoForm().shouldBe(visible);
        return this;
    }

    @Step("Checks absence bank info form. Profile_bank_page")
    public Profile_bank_page_Logic checkAbsenceBankInfoForm() {
        bankInfoForm().shouldNotBe(visible);
        return this;
    }

    @Step("Click cancel changes. Profile_bank_page")
    public Profile_bank_page_Logic clickChangeCancelBtn() {
        cancelChangeBtn().scrollTo();
        cancelChangeBtn().click();
        return this;
    }

    @Step("Get name receiver and IBAN num of current bank block. Profile_bank_page")
    public ArrayList getNameReceiverAndIbanNum() {
        ArrayList <String> bankData = new ArrayList<>();
        String nameReceiver = nameReceiverInCurrentBankBlock().getText();
        String ibanNum = ibanNumInCurrentBankBlock().getText();
        bankData.add(nameReceiver);
        bankData.add(ibanNum);
        return bankData;
    }

    @Step(":from Profile_bank_page")
    public Profile_bank_page_Logic checkForTextInBlockTopTitle(String expectedText) {
        new Profile_addresses_page_Logic().checkForTextInBlockTopTitle(expectedText);
        return this;
    }

    @Step(":from Profile_bank_page")
    public Profile_bank_page_Logic checkPresenceClientID() {
        new Profile_addresses_page_Logic().checkPresenceClientID();
        return this;
    }

    @Step(":from Profile_bank_page")
    public Profile_bank_page_Logic checkPresenceHeaderBlockAndElementInside() {
        new Profile_addresses_page_Logic().checkPresenceHeaderBlockAndElementInside();
        return this;
    }

    @Step(":from Profile_bank_page")
    public Profile_bank_page_Logic checkNamePageAndPresenceIcon(String expectedName) {
        new Profile_addresses_page_Logic().checkNamePageAndPresenceIcon(expectedName);
        return this;
    }

    @Step("Checks presence fields in bank Info form. Profile_bank_page")
    public Profile_bank_page_Logic checkPresenceFieldsIbBankInfoForm() {
        fieldReceiver().shouldBe(visible);
        fieldIBAN().shouldBe(visible);
        fieldKTO().shouldBe(visible);
        fieldBLZ().shouldBe(visible);
        fieldSwift().shouldBe(visible);
        fieldBank().shouldBe(visible);
        return this;
    }

    @Step("Checks presence button save and cancel change. Profile_bank_page")
    public Profile_bank_page_Logic checkPresenceBtnSaveAndCancelChange() {
        saveFormBtn().shouldBe(visible);
        cancelChangeBtn().shouldBe(visible);
        return this;
    }

    @Step("Get name tab bank. Profile_bank_page")
    public String getNameTabBank() {
        return profileBankBtn().getText();
    }

}
