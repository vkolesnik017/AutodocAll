package ATD;

import com.codeborne.selenide.Condition;

import static ATD.CommonMethods.getNameRouteFromJSVarInHTML;
import static com.codeborne.selenide.Selenide.Wait;
import static com.codeborne.selenide.Selenide.page;
import static org.testng.AssertJUnit.assertNull;

public class Profile_page_Logic extends Profile_page {

    public Main_page_Logic logOut() {
        new Main_page_Logic().logOutClick();
        Wait().until(WebDriver -> getNameRouteFromJSVarInHTML().equals("main"));
        return page(Main_page_Logic.class);
    }

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
        String nullCheckbox = einstellungenCheckbox().getAttribute("checked");
        assertNull(nullCheckbox);
        return this;
    }

    public Profile_page_Logic clickCheckboxInSetting() {
        einstellungenCheckbox().click();
        return this;
    }

    public Profile_page_Logic checkingPopupAfterClickCheckbox() {
        popupAfterCheckedCheckbox().shouldHave(Condition.text("Vielen Dank!"));
        closePopupAfterCheckedCheckbox().click();
        einstellungenActive().shouldBe(Condition.appear);
        return this;
    }
}
