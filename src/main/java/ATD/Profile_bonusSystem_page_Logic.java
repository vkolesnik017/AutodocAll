package ATD;

import io.qameta.allure.Step;

import java.sql.SQLException;

import static ATD.CommonMethods.getCurrencyAndVerify;
import static com.codeborne.selenide.Condition.*;

public class Profile_bonusSystem_page_Logic extends Profile_bonusSystem_page {

    @Step("Checks currency on header bonus label. Profile_bonusSystem_page")
    public Profile_bonusSystem_page_Logic checkCurrencyOnHeaderBonusLabel(String shop) throws SQLException {
        String expectedCurrency = new DataBase().getCurrency(shop);
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
}
