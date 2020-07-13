package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Profile_bank_page {

    SelenideElement profileBankBtn() {
        return $x("//a[@data-ga-action='Sidebar_BankDetails']");
    }

    SelenideElement fieldReceiver() {
        return $x("//input[@id='form_AccOwner']");
    }

    SelenideElement fieldIBAN() {
        return $x("//input[@id='form_AccIBAN']");
    }

    SelenideElement fieldKTO() {
        return $x("//input[@id='form_AccKTO']");
    }

    SelenideElement fieldBLZ() {
        return $x("//input[@id='form_AccBLZ']");
    }

    SelenideElement fieldSwift() {
        return $x("//input[@id='form_AccBIC']");
    }

    SelenideElement fieldBank() {
        return $x("//input[@id='form_AccBank']");
    }

    SelenideElement btnAddNewBankInfo() {
        return $x("//a[@class='btn addNewBankInfo']");
    }

    SelenideElement currentBankBlock() {
        return $x("//li[@id='currentBank']");
    }

    SelenideElement nameReceiverInCurrentBankBlock() {
        return $x("//div[@id='AccOwner']");
    }

    SelenideElement ibanNumInCurrentBankBlock() {
        return $x("//div[@id='AccIBAN']");
    }

    SelenideElement saveFormBtn() {
        return $x("//a[@class='btn bank_submit save']");
    }

    SelenideElement deleteBankDataBtn() {
        return $(".remove_bank_data");
    }

    SelenideElement bankInfoForm() {
        return $x("//form[@id='bank_info']");
    }

    SelenideElement cancelChangeBtn() {
        return $x("//a[@class='btn edit']//span");
    }
}
