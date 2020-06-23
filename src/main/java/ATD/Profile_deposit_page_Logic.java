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
}
