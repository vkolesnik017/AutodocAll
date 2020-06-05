package ATD;

import io.qameta.allure.Step;

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

    @Step("Get IBAN num in current bank block. Profile_bank_page")
    public String getIbanNumInCurrentBankBlock() {
        return String.valueOf(ibanNumInCurrentBankBlock().getText());
    }

    @Step("Click delete bank datta button. Profile_bank_page")
    public Profile_bank_page_Logic clickDeleteBankDataBtn() {
        deleteBankDataBtn().click();
        return this;
    }

}
