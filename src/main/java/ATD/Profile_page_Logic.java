package ATD;

import com.codeborne.selenide.Condition;

public class Profile_page_Logic extends Profile_page {

    public Profile_page_Logic checkingAutodocPlusActive() {
        autodocLinkActiveFirst().shouldBe(Condition.appear);
        return this;
    }

    public Profile_page_Logic checkingAppearingNameOfClient() {
        nameOfClient().shouldBe(Condition.visible);
        return this;
    }

    public Profile_page_Logic clickSetting() {
        settingsTabBtn().click();
        return this;
    }

    public Profile_page_Logic checkingCheckedCheckbox() {
        einstellungenCheckbox().shouldHave(Condition.attribute("checked", "true"));
    return this;
    }

    public Profile_page_Logic checkingUncheckedCheckbox() {
        einstellungenCheckbox().shouldHave(Condition.attribute("checked", "false"));
        return this;
    }
}
