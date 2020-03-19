package ATD;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import java.sql.SQLException;

import static ATD.CommonMethods.getNameRouteFromJSVarInHTML;
import static com.codeborne.selenide.Selenide.Wait;
import static com.codeborne.selenide.Selenide.page;
import static org.testng.AssertJUnit.assertNull;

public class Profile_page_Logic extends Profile_page {
    private Retouren_page_Logic retourenPage = new Retouren_page_Logic();

    @Step(":on Profile_page")
    public Cart_page_Logic cartClick() {
        new Main_page_Logic().cartClick();
        return page(Cart_page_Logic.class);
    }

    @Step("Get client ID(digit). Profile_page")
    public String getDigitClientId() {
        String digit = clientID().getText();
        digit = digit.substring(digit.indexOf(":") + 1).trim();
        return digit;
    }

    @Step("Logout user. Profile_page")
    public Main_page_Logic logOut() {
        new Main_page_Logic().logOutClick();
        Wait().until(WebDriver -> getNameRouteFromJSVarInHTML().equals("main"));
        return page(Main_page_Logic.class);
    }

    @Step("Checking that Autodoc Plus tab active. Profile_page")
    public Profile_page_Logic checkingAutodocPlusActive() {
        autodocLinkActiveFirst().shouldBe(Condition.appear);
        return this;
    }

    @Step("Checking appearing name of client. Profile_page")
    public Profile_page_Logic checkingAppearingNameOfClient() {
        nameOfClient().shouldBe(Condition.visible);
        return this;
    }

    @Step("Click setting tab. Profile_page")
    public Profile_page_Logic clickSetting() {
        settingsTabBtn().click();
        return this;
    }

    @Step("Checking checked checkbox in setting tab. Profile_page")
    public Profile_page_Logic checkingCheckedCheckbox() {
        einstellungenCheckbox().shouldHave(Condition.attribute("checked", "true"));
        return this;
    }

    @Step("Checking unchecked checkbox in setting tab. Profile_page")
    public Profile_page_Logic checkingUncheckedCheckbox() {
        String nullCheckbox = einstellungenCheckbox().getAttribute("checked");
        assertNull(nullCheckbox);
        return this;
    }

    @Step("Click checkbox in setting tab. Profile_page")
    public Profile_page_Logic clickCheckboxInSetting() {
        einstellungenCheckbox().click();
        return this;
    }

    @Step("Checking popup after clicking checkbox in setting tab. Profile_page")
    public Profile_page_Logic checkingPopupAfterClickCheckbox() {
        popupAfterCheckedCheckbox().shouldHave(Condition.text("Vielen Dank!"));
        closePopupAfterCheckedCheckbox().click();
        einstellungenActive().shouldBe(Condition.appear);
        return this;
    }

    // history orders tab (page) (Meine Bestellungen)
    @Step("Clicking Bestelldetails. Profile_page")
    public Profile_page_Logic clickBestelldetailsButton(String numberOrder) {
        bestelldetailsButton(numberOrder).click();
        return this;
    }

    // details order page, appear after click bestelldetails Button
    @Step("Clicking return or replace item button. Profile_page")
    public Profile_page_Logic clickReturnOrReplaceItemButton() {
        // [Artikel zurücksenden oder ersetzen] > button
        returnOrReplaceItemButton().click();
        return this;
    }

    // popup return, the necessary methods are fits from the Retoure_page
    @Step(":on Profile_page")
    public Float getProductPriceForReturn() {
        return retourenPage.getProductPriceForReturn();
    }

    @Step(":on Profile_page")
    public Profile_page_Logic clickCheckbox() {
        retourenPage.clickCheckbox();
        return this;
    }

    @Step(":on Profile_page")
    public Profile_page_Logic chooseRandomCauseReturnInSelect() {
        retourenPage.chooseRandomCauseReturnInSelect();
        return this;
    }

    @Step(":on Profile_page")
    public Profile_page checkingTranslateOfCausesForReturn() throws SQLException {
        retourenPage.checkingTranslateOfCausesForReturn();
        return this;
    }

    @Step(":on Profile_page")
    public Profile_page_Logic chekingToAppearPopupErrorsOfReturn() {
        retourenPage.chekingToAppearPopupErrorsOfReturn();
        return this;
    }

    @Step(":on Profile_page")
    public Profile_page_Logic fillInFormForMessage() {
        retourenPage.fillInFormForMessage();
        return this;
    }

    @Step(":on Profile_page")
    public Profile_page_Logic addFileIfIsDisplayedFileBlock() {
        retourenPage.addFileIfIsDisplayedFileBlock();
        return this;
    }

    @Step(":on Profile_page")
    public Profile_page_Logic clickSendenButtonWithCorrectData() {
        retourenPage.clickSendenButtonWithCorrectData();
        return this;
    }

    @Step(":on Profile_page")
    public Cart_page_Logic cartClick() {
        new Main_page_Logic().cartClick();
        return page(Cart_page_Logic.class);
    }

}
