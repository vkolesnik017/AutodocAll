package mailinator;

import ATD.PasswordRecovery_page_Logic;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class Mailinator {

    // The Mailinator Email System. It is Email Workflow Testing tool.

    public SelenideElement letterInfo(int numberLetter) {
        return $(byXpath("//*[contains(@class,'pointer')]/../tr[" + numberLetter + "]"));
    }

    public SelenideElement letter(int numberLetter) {
        return $(byXpath("//*[contains(@class,'pointer')]/../tr[" + numberLetter + "]//a"));
    }

    public SelenideElement linkFAQemailConfirm() {
        return $(".btn-wrapper > a");
    }

    private SelenideElement linkInRestorePasswordLetter() {
        return $(byCssSelector(".forgot>a"));
    }

    private SelenideElement infoTotalPriceInEmail() {
        return $x("//p[@class='info-total__price']");
    }

    private SelenideElement unitPrice() {
        return $x("//p[@class='info-bot-2col__text-dark']//b");
    }

    private SelenideElement regularDeliveryPriceInEmail() {
        return $x("//table[@class='info-total']//tr[3]//td[2]//p");
    }

    private SelenideElement percentageOfVatInEmail() {
        return $x("//p[contains(text(),'%')]");
    }

    private SelenideElement firstFirmNameInEmail() {
      return $x("//table[@class='to-column-table to-column-table--border']//p[@class='to-column-table__title']/following-sibling::b");
    }

    private SelenideElement secondFirmNameInEmail() {
        return $x("//table[@class='to-column-table']//p[@class='to-column-table__title']/following-sibling::b");
    }

    @Step("Checks text {firmName} in first company name in email. Mailinator")
    public Mailinator checkFirstFirmNameInEmail(String firmName) {
      firstFirmNameInEmail().shouldHave(text(firmName));
      return this;
    }

    @Step("Checks text {firmName} in second company name in email. Mailinator")
    public Mailinator checkSecondFirmNameInEmail(String firmName) {
        secondFirmNameInEmail().shouldHave(text(firmName));
        return this;
    }

    @Step("Checks for text containing VAT percentage in email. Mailinator")
    public Mailinator checkTextContainingVatPercentageInEmail(String textWithPercentageOfVAT) {
        percentageOfVatInEmail().shouldHave(text(textWithPercentageOfVAT));
        return this;
    }

    @Step("Checks absence text containing VAT percentage in email. Mailinator")
    public Mailinator checkAbsenceVatPercentageInEmail() {
        percentageOfVatInEmail().shouldNotBe(visible);
        return this;
    }

    @Step("Checks regular delivery price. Mailinator")
    public Mailinator checkRegularDeliveryPriceInEmail(String regularDeliveryPrice) {
        regularDeliveryPriceInEmail().shouldHave(text(regularDeliveryPrice));
        return this;
    }

    @Step("Get total price in email. Mailinator")
    public Double getTotalPriceInEmail() {
        String realPrice = infoTotalPriceInEmail().getText();
        realPrice = realPrice.substring(0, realPrice.indexOf(" ")).replaceAll(",", ".");
        Double totalPrice = Double.parseDouble(realPrice);
        return totalPrice;
    }

    @Step("Get unit price in email. Mailinator")
    public Double getUnitPriceInEmail() {
        String realPrice = unitPrice().getText();
        realPrice = realPrice.substring(0, realPrice.indexOf(" ")).replaceAll(",", ".");
        Double unitPrice = Double.parseDouble(realPrice);
        return unitPrice;
    }

    @Step("Get total price in email for EN shop. Mailinator")
    public Double getTotalPriceInEmailForENShop() {
        String realPrice = infoTotalPriceInEmail().getText().replace("£ ", "");
        realPrice = realPrice.replaceAll(",",".");
        Double totalPrice = Double.parseDouble(realPrice);
        return totalPrice;
    }

    @Step("Get unit price in email for EN shop. Mailinator")
    public Double getUnitPriceForEnShop() {
        String realPrice = unitPrice().getText().replace("£ ", "");
        realPrice = realPrice.replaceAll(",", ".");
        Double unitPrice = Double.parseDouble(realPrice);
        return unitPrice;
    }


    public Mailinator openEmail(String email) {
        open("https://www.mailinator.com");
        $(byId("addOverlay")).setValue(email).pressEnter();
        return this;
    }

    public Mailinator openLetter(int numberLetter) {
        letter(numberLetter).shouldBe(appear);
        letter(numberLetter).click();
        switchTo().frame("msg_body");
        return this;
    }

    public PasswordRecovery_page_Logic clickLinkRecoveryPasswordInLetter() {
        linkInRestorePasswordLetter().click();
        switchTo().window("recovery");
        return page(PasswordRecovery_page_Logic.class);
    }

    @Step("Check letter info text. Mailinator")
    public Mailinator checkLetterInfoText(int letterNumber, String expectedText1, String expectedText2) {
        letterInfo(letterNumber).shouldHave(text(expectedText1)).shouldHave(text(expectedText2));
        return this;
    }
}
