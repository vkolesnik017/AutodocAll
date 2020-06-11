package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Profile_bonusSystem_page {

    SelenideElement bonusLabel() {
        return $x("//span[@class='deposit']");
    }

    SelenideElement headerBonusAmountCurrency() {
        return $x("//div[@class='header__bonus-amount']//a//span[2]");
    }

    SelenideElement bonusesCombustionDate() {
        return $x("//span[@class='deposit__valid']/span");
    }

    SelenideElement bonusTable() {
        return $x("//div[@class='bonus_table']");
    }

    SelenideElement bonusProgramBlock() {
        return $x("//div[@class='info']/ul");
    }
}
