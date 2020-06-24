package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import static ATD.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;

public class Profile_deposit_page_Logic extends Profile_deposit_page{

    @Step("Get deposit balance. Profile_deposit_page_Logic")
    public Float getDepositBalance() {
        String sumDeposit = depositBalance().getText().replaceAll("[^0-9,]", "").replaceAll(",", ".");
        return Float.valueOf(sumDeposit);
    }

    @Step("Checks presence deposit table. Profile_deposit_page_Logic")
    public Profile_deposit_page_Logic checkDepositTable() {
        depositTable().shouldBe(visible);
        return this;
    }

    @Step("Opens a PDF document with deposit operations. Profile_deposit_page_Logic")
    public Profile_deposit_page_Logic openPdfDocWithDepositOperation() {
        pdfDocDepositLink().click();
        switchTo().window(1);
        checkingContainsUrl("/print-deposit/");
        Assert.assertFalse($x("//div[@id='content']").isDisplayed());
        return this;
    }

    @Step("Checks the number of deposit in the table. Profile_deposit_page_Logic")
    public Profile_deposit_page_Logic checkNumberOfDepositInTable(int numberOfBonus) {
        listOfDepositInTable().shouldHaveSize(numberOfBonus);
        return this;
    }

    @Step(":for Profile_deposit_page_Logic")
    public Profile_deposit_page_Logic checkPaginationBlock() {
        new Profile_bonusSystem_page_Logic().checkPaginationBlock();
        return this;
    }

    @Step(":from Profile_deposit_page_Logic")
    public Profile_deposit_page_Logic checkForTextInBlockTopTitle(String expectedText) {
        new Profile_addresses_page_Logic().checkForTextInBlockTopTitle(expectedText);
        return this;
    }

    @Step(":from Profile_deposit_page_Logic")
    public Profile_deposit_page_Logic checkPresenceClientID() {
        new Profile_addresses_page_Logic().checkPresenceClientID();
        return this;
    }

    @Step(":from Profile_deposit_page_Logic")
    public Profile_deposit_page_Logic checkPresenceHeaderBlockAndElementInside() {
        new Profile_addresses_page_Logic().checkPresenceHeaderBlockAndElementInside();
        return this;
    }

    @Step("Checks presence title page. Profile_deposit_page_Logic")
    public Profile_deposit_page_Logic checkPresenceTitlePage() {
        titlePage().shouldBe(visible);
        return this;
    }

    @Step("Checks presence columns in a table with deposit transaction and data inside. Profile_deposit_page_Logic")
    public Profile_deposit_page_Logic checkPresenceColumnAndDataInsideTableWithDepositTransaction() {
        columnTypeOfTransaction().shouldBe(visible);
        typeOfTransactionInsideTable().shouldBe(visible);
        columnData().shouldBe(visible);
        dataInsideTable().shouldBe(visible);
        columnQuantity().shouldBe(visible);
        quantityInsideTable().shouldBe(visible);
        columnBalance().shouldBe(visible);
        balanceInsideTable().shouldBe(visible);
        columnSerialNum().shouldBe(visible);
        serialNumInsideTable().shouldBe(visible);
        columnPdf().shouldBe(visible);
        pdfInsideTable().shouldBe(visible);
        return this;
    }
}
