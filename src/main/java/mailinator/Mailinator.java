package mailinator;

import ATD.PasswordRecovery_page_Logic;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

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
      return $x("//table[@class='to-column-table to-column-table--border']//p[2][@class='to-column-table__text']");
    }

    private SelenideElement secondFirmNameInEmail() {
        return $x("//table[@class='to-column-table']//p[2][@class='to-column-table__text']");
    }

    private SelenideElement namePhysicalPersonInEmail() {
        return $x("//table[@class='to-column-table to-column-table--border']//p[@class='to-column-table__text']/following-sibling::p");
    }

    private SelenideElement linkToDeliveryPage() {
        return $x("//td[@class='content__cell']//a");
    }

    private ElementsCollection allLinkToDeliveryPage() {
        return $$x("//div[@style='background-color: white ; padding: 15px']//a");
    }

    private SelenideElement trackingNumber() {
        return $x("//td[@class='content__cell']//div//b[2]");
    }




    @Step("Transition to delivery page and get tracking number from url. Mailinator")
    public ArrayList<String> transitionToDeliveryPageAndGetTrackingNumFromUrlInMail() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < allLinkToDeliveryPage().size(); i++) {
            allLinkToDeliveryPage().get(i).click();
            switchTo().window(1);
            String number = url().replaceAll(".+=([0-9]{2,}).*", "$1");
            list.add(number);
            closeWindow();
            switchTo().window(0);
            refresh();
            openLetter(1);
        }
        return list;
    }

    @Step("Get tracking number. Mailinator")
    public String getTrackingNumberFromMail() {
        return String.valueOf(trackingNumber().getText());
    }

    @Step("Transition to delivery page ang get URL. Mailinator")
    public String transitionToDeliveryPageAndGetUrlFromMail() {
        linkToDeliveryPage().click();
        switchTo().window(1);
        String deliveryPageURL = url();
        closeWindow();
        switchTo().window(0);
        return deliveryPageURL;
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

    @Step("Checks text {namePhysicalPerson} in physical person name in email. Mailinator")
    public Mailinator checkNamePhysicalPersonInEmail(String namePhysicalPerson) {
        namePhysicalPersonInEmail().shouldHave(text(namePhysicalPerson));
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
    public Float getTotalPriceInEmail() {
        String realPrice = infoTotalPriceInEmail().getText();
        realPrice = realPrice.replaceAll("[^0-9,]", "");
        realPrice = realPrice.replaceAll(",",".");
        Float totalPrice = Float.parseFloat(realPrice);
        return totalPrice;
    }

    @Step("Get unit price in email. Mailinator")
    public Float getUnitPriceInEmail() {
        String realPrice = unitPrice().getText();
        realPrice = realPrice.replaceAll("[^0-9.]", "").replace(",", ".");
        Float unitPrice = Float.parseFloat(realPrice);
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
        sleep(5000);
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

    @Step("Click FAQ email Confirm")
    public Mailinator clickFAQemailConfirm() {
        linkFAQemailConfirm().waitUntil(visible, 5000)
                                .click();
        return this;
    }
}
