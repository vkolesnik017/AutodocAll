package ATD;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.sql.SQLException;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Profile_page {

    private Retouren_page retourenPage = new Retouren_page();

    public SelenideElement nameOfClient() {
        return $(byXpath("//div[@class='name_cash']//span[@class='name']"));
    }

    private SelenideElement clientID() {
        return $(byXpath("//div[@class='top_title']/span"));
    }

    public String getDigitClientId() {
        String digit = clientID().getText();
        digit = digit.substring(digit.indexOf(":") + 1).trim();
        return digit;
    }

    // history orders tab (page) (Meine Bestellungen)

    private SelenideElement bestelldetailsButton(String numberOrder) {
        return $x("//td[@class='order-nr' and text()='" + numberOrder + "']/../td[@class='order-data']/a");
    }

    @Step
    public Profile_page clickBestelldetailsButton(String numberOrder) {
        bestelldetailsButton(numberOrder).click();
        return this;
    }

    // details order page, appear after click bestelldetails Button

    private SelenideElement returnOrReplaceItemButton() {
        return $(".returnOrdersPopupCaller");
    }

    @Step
    public Profile_page clickReturnOrReplaceItemButton() {
        // [Artikel zurücksenden oder ersetzen] > button
        returnOrReplaceItemButton().click();
        return this;
    }

    // popup return, the necessary methods are fits from the Retoure_page
    public Float getProductPriceForReturn() {
        return retourenPage.getProductPriceForReturn();
    }

    public Profile_page clickCheckbox() {
        retourenPage.clickCheckbox();
        return this;
    }

    public Profile_page chooseRandomCauseReturnInSelect() {
        retourenPage.chooseRandomCauseReturnInSelect();
        return this;
    }

    public Profile_page checkingTranslateOfCausesForReturn() throws SQLException {
        retourenPage.checkingTranslateOfCausesForReturn();
        return this;
    }

    public Profile_page chekingToAppearPopupErrorsOfReturn() {
        retourenPage.chekingToAppearPopupErrorsOfReturn();
        return this;
    }

    public Profile_page fillInFormForMessage() {
        retourenPage.fillInFormForMessage();
        return this;
    }

    public Profile_page addFileIfIsDisplayedFileBlock() {
        retourenPage.addFileIfIsDisplayedFileBlock();
        return this;
    }

    public Profile_page clickSendenButtonWithCorrectData() {
        retourenPage.clickSendenButtonWithCorrectData();
        return this;
    }


    // settings tab (Einstellungen)
    public SelenideElement settingsTabBtn() {
        return $(byCssSelector(".settings_link"));
    }

    public SelenideElement oldPasswordFiled() {
        return $(byName("old_pass"));
    }

    public SelenideElement newPasswordField() {
        return $(byName("new_pass"));
    }

    public SelenideElement newPasswordConfirmField() {
        return $(byName("new_pass_confirm"));
    }

    public SelenideElement changePasswordBtn() {
        return $(byCssSelector(".password_submit"));
    }

    public SelenideElement closeSuccessfulPasswordChangePopup() {
        return $(byXpath("//*[@class='popup ']//*[contains(text(),'wurden aktualisiert')]/..//a"));
    }

}
