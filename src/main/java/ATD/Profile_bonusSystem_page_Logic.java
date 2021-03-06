package ATD;

import Common.DataBase;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;

import static ATD.CommonMethods.getCurrencyAndVerify;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.sleep;

public class Profile_bonusSystem_page_Logic extends Profile_bonusSystem_page {

    @Step("Checks currency on header bonus label. Profile_bonusSystem_page")
    public Profile_bonusSystem_page_Logic checkCurrencyOnHeaderBonusLabel(String shop) throws SQLException {
        String expectedCurrency = new DataBase("ATD").getCurrency(shop);
        getCurrencyAndVerify(headerBonusAmountCurrency(), "headerBonusAmountCurrency()", shop, expectedCurrency);
        return this;
    }

    @Step("Checks presence bonus label. Profile_bonusSystem_page")
    public Profile_bonusSystem_page_Logic checkPresenceBonusLabel() {
        bonusLabel().shouldBe(visible);
        return this;
    }

    @Step("Checks presence bonuses combustion date. Profile_bonusSystem_page")
    public Profile_bonusSystem_page_Logic checkPresenceBonusesCombustionDate() {
        bonusesCombustionDate().shouldBe(visible);
        return this;
    }

    @Step("Checks presence bonus table. Profile_bonusSystem_page")
    public Profile_bonusSystem_page_Logic checkPresenceBonusTable() {
        bonusTable().shouldBe(visible);
        return this;
    }

    @Step("Checks presence bonus program block. Profile_bonusSystem_page")
    public Profile_bonusSystem_page_Logic checkPresenceBonusProgramBlock() {
        bonusProgramBlock().shouldBe(visible);
        return this;
    }

    @Step("Checks the number of bonuses in the table. Profile_bonusSystem_page")
    public Profile_bonusSystem_page_Logic checkNumberOfBonusInTable(int numberOfBonus) {
        listOfBonusInTable().shouldHaveSize(numberOfBonus);
        return this;
    }

    @Step("Checks the presence and operation of the pagination block. Profile_bonusSystem_page")
    public Profile_bonusSystem_page_Logic checkPaginationBlock() {
        paginationBloc().shouldBe(visible);
        secondPageLink().shouldHave(attribute("href")).click();
        sleep(2000);
        secondPageLink().shouldNotBe(visible);
        firstLinkPage().shouldHave(attribute("href")).click();
        sleep(2000);
        firstLinkPage().shouldNotBe(visible);
        secondPageLink().shouldHave(attribute("href"));
        pageLinkNext().shouldHave(attribute("href")).click();
        sleep(2000);
        pageLinkNext().shouldNotBe(visible);
        pageLinkPrev().shouldHave(attribute("href")).click();
        sleep(2000);
        pageLinkPrev().shouldNotBe(visible);
        pageLinkNext().shouldHave(attribute("href"));
        return this;
    }

    @Step(":from Profile_bonusSystem_page_Logic")
    public Profile_bonusSystem_page_Logic checkForTextInBlockTopTitle(String expectedText) {
        new Profile_plus_page_Logic().checkForTextInBlockTopTitle(expectedText);
        return this;
    }

    @Step(":from Profile_bonusSystem_page_Logic")
    public Profile_bonusSystem_page_Logic checkPresenceClientID() {
        new Profile_plus_page_Logic().checkPresenceClientID();
        return this;
    }

    @Step(":from Profile_bonusSystem_page_Logic")
    public Profile_bonusSystem_page_Logic checkPresenceHeaderBlockAndElementInside() {
        new Profile_plus_page_Logic().checkPresenceHeaderBlockAndElementInside();
        return this;
    }

    @Step("Checks that the currency of the added bonus matches the shop. Profile_bonusSystem_page")
    public Profile_bonusSystem_page_Logic checkCurrencyAccruedBonusInTable(String shop) throws SQLException {
        String expectedCurrency = new DataBase("ATD").getCurrency(shop);
        String actualCurrencyBonus = accruedBonusInTable().getText().replaceAll(".+[0-9].", "");
        Assert.assertEquals(expectedCurrency, actualCurrencyBonus);
        return this;
    }
}
