package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Profile_bank_page {

    SelenideElement fieldReceiver() {
        return $x("//input[@id='form_AccOwner']");
    }

    SelenideElement fieldIBAN() {
        return $x("//input[@id='form_AccIBAN']");
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
}
