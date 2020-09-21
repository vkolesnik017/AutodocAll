package mailinator;

import ATD.PasswordRecovery_page_Logic;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class WebMail {

    //Static password for WebMail service
    public static String passwordForMail = "chXh3fJhRWeCh7Eqwn";

    //Main page mail in service
    public SelenideElement fieldUser() {
        return $x("//input[@name='_user']");
    }

    public SelenideElement fieldPassword() {
        return $x("//input[@name='_pass']");
    }

    public SelenideElement submitBtn() {
        return $x("//input[@id='rcmloginsubmit']");
    }

    public SelenideElement exitEmailBTN() {
        return $x("//a[@class='button-logout']");
    }

    //Subject
    public ElementsCollection allLetter() {
        return $$x("//td[@class='subject']");
    }

    public ElementsCollection subjectLetter() {
        return $$x("//td[@class='subject']//span[@class='subject']//a//span");
    }

    public SelenideElement letter(int numberLeeter) {
        return $x("(//td[@class='subject'])["+ numberLeeter +"]");
    }

    public SelenideElement letterWithOrderNumber(String orderNumber) {
        return $x("//td[@class='subject']//span[contains(text(),'" + orderNumber + "')]");
    }

    public SelenideElement unreadLetter() {
        return $x("(//span[@id='msgicnrcmrowMTE'])[1]");
    }

    //Letter
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
        return $x("//table[@class='content']//a[1]");
    }

    private ElementsCollection allLinkToDeliveryPage() {
        return $$x("//table[@class='content']//a");
    }

    private SelenideElement trackingNumber() {
        return $x("//b[2]");
    }

    private SelenideElement blockOfRequisites() {
        return $x("//table[@class='info-inline green-border']//tbody//tr//td");
    }

    //Locators for old mail service Mailinator
    public SelenideElement letterInfo(int numberLetter) {
        return $(byXpath("//*[contains(@class,'pointer')]/../tr[" + numberLetter + "]"));
    }



    @Step("Opens email service. WebMail")
    public WebMail openMail(String email, String password) {
        open("https://webmail.autodoc.de/");
        fieldUser().setValue(email);
        fieldPassword().setValue(password);
        submitBtn().click();
        return this;
    }

    @Step("Exit of the mail. WebMail")
    public WebMail exitOfMail() {
        exitEmailBTN().click();
        return this;
    }

    @Step("Opens a letter at the specified serial number. WebMail")
    public WebMail openLetter(int numberLetter) {
        letter(numberLetter).shouldBe(appear);
        letter(numberLetter).click();
        switchTo().frame("messagecontframe");
        return this;
    }

    @Step("Checks and opens a letter with the order number{orderNumber}. WebMail")
    public WebMail checkAndOpenLetterWithOrderNumber(String orderNumber) {
        sleep(3000);
        for (int i = 0; i < subjectLetter().size(); i++) {
            String textNameLetter = subjectLetter().get(i).getText();
            if (textNameLetter.contains(orderNumber)) {
                subjectLetter().get(i).click();
                switchTo().frame("messagecontframe");
                break;
            } else {
                Assert.fail("Latter order " + orderNumber + " not found");
            }
        }
        return this;
    }

    @Step("Checks and opens a letter with specific specified parameters{expectedTextNameLetter}, {orderNumber}. WebMail")
    public WebMail checkAndOpenLetterInfoText(String expectedTextNameLetter, String orderNumber) {
        sleep(3000);
        for (int i = 0; i < subjectLetter().size(); i++) {
            String textNameLetter = subjectLetter().get(i).getText();
            if (textNameLetter.equals(expectedTextNameLetter + " " + orderNumber)) {
                subjectLetter().get(i).click();
                switchTo().frame("messagecontframe");
                break;
            } else {
                Assert.fail("Letter not found");
            }
        }
        return this;
    }

    @Step("Checks presence unread first letter. WebMail")
    public WebMail checkPresenceUnderFirstLetter() {
        unreadLetter().waitUntil(visible, 10000);
        return this;
    }

    @Step("Compares the text of the requisites in the email with expected requisites. WebMail")
    public WebMail comparesTextOfRequisitesInMailWithExpectedRequisites(String expectedRequisites) {
        String requisitesInMail = blockOfRequisites().getText().replaceAll(" ", "").replaceAll("\n","").toLowerCase();
        String requisitesInSuccessPage = expectedRequisites.replaceAll(" ","").replaceAll("\n","").toLowerCase();
        Assert.assertEquals(requisitesInMail, requisitesInSuccessPage);
        return this;
    }

    @Step("Transition to delivery page and get tracking number from url. WebMail")
    public ArrayList<String> transitionToDeliveryPageAndGetTrackingNumFromUrlInMail() {
        ArrayList<String> list = new ArrayList<>();
        sleep(5000);
        for (int i = 0; i < allLinkToDeliveryPage().size(); i++) {
            allLinkToDeliveryPage().get(i).click();
            switchTo().window(1);
            String number = url().replaceAll(".+=([0-9]{2,}).*", "$1");
            list.add(number);
            closeWindow();
            switchTo().window(0);
            switchTo().frame("messagecontframe");
        }
        return list;
    }

    @Step("Get tracking number. WebMail")
    public String getTrackingNumberFromMail() {
        String trackingNum = trackingNumber().getText();
        return trackingNum;
    }

    @Step("Transition to delivery page ang get URL. WebMail")
    public String transitionToDeliveryPageAndGetUrlFromMail() {
        linkToDeliveryPage().click();
        switchTo().window(1);
        String deliveryPageURL = url();
        closeWindow();
        switchTo().window(0);
        return deliveryPageURL;
    }

    @Step("Checks text {firmName} in first company name in email. WebMail")
    public WebMail checkFirstFirmNameInEmail(String firmName) {
      firstFirmNameInEmail().shouldHave(text(firmName));
      return this;
    }

    @Step("Checks text {firmName} in second company name in email. WebMail")
    public WebMail checkSecondFirmNameInEmail(String firmName) {
        secondFirmNameInEmail().shouldHave(text(firmName));
        return this;
    }

    @Step("Checks text {namePhysicalPerson} in physical person name in email. WebMail")
    public WebMail checkNamePhysicalPersonInEmail(String namePhysicalPerson) {
        namePhysicalPersonInEmail().shouldHave(text(namePhysicalPerson));
        return this;
    }

    @Step("Checks for text containing VAT percentage in email. WebMail")
    public WebMail checkTextContainingVatPercentageInEmail(String textWithPercentageOfVAT) {
        percentageOfVatInEmail().shouldHave(text(textWithPercentageOfVAT));
        return this;
    }

    @Step("Checks absence text containing VAT percentage in email. WebMail")
    public WebMail checkAbsenceVatPercentageInEmail() {
        percentageOfVatInEmail().shouldNotBe(visible);
        return this;
    }

    @Step("Checks regular delivery price. WebMail")
    public WebMail checkRegularDeliveryPriceInEmail(String regularDeliveryPrice) {
        regularDeliveryPriceInEmail().shouldHave(text(regularDeliveryPrice));
        return this;
    }

    @Step("Get total price in email. WebMail")
    public Float getTotalPriceInEmail() {
        String realPrice = infoTotalPriceInEmail().getText();
        realPrice = realPrice.replaceAll("[^0-9,]", "");
        realPrice = realPrice.replaceAll(",",".");
        Float totalPrice = Float.parseFloat(realPrice);
        return totalPrice;
    }

    @Step("Get unit price in email. WebMail")
    public Float getUnitPriceInEmail() {
        String realPrice = unitPrice().getText();
        realPrice = realPrice.replaceAll("[^0-9.]", "").replace(",", ".");
        Float unitPrice = Float.parseFloat(realPrice);
        return unitPrice;
    }

    @Step("Clicks in recovery password link in letter. WebMail")
    public PasswordRecovery_page_Logic clickLinkRecoveryPasswordInLetter() {
        linkInRestorePasswordLetter().click();
        switchTo().window("recovery");
        new PasswordRecovery_page_Logic().checkPresenceContactForm();
        return page(PasswordRecovery_page_Logic.class);
    }

    @Step("Click FAQ email Confirm")
    public WebMail clickFAQemailConfirm() {
        linkFAQemailConfirm().waitUntil(visible, 5000)
                .click();
        return this;
    }

    @Step("Open old mail service Mailinator. WebMail")
    public WebMail openMail(String email) {
        open("https://www.mailinator.com");
        $(byId("addOverlay")).setValue(email).pressEnter();
        return this;
    }

    @Step("Open letter in old mail service Mailinator. WebMail")
    public WebMail openLetterInOldMailServiceMailinator(int numberLetter) {
        sleep(5000);
        letter(numberLetter).shouldBe(appear);
        letter(numberLetter).click();
        switchTo().frame("msg_body");
        sleep(5000);
        return this;
    }

    @Step("Check letter info text in old mail service Mailinator. WebMail")
    public WebMail checkLetterInfoText(int letterNumber, String expectedText1, String expectedText2) {
        letterInfo(letterNumber).shouldHave(text(expectedText1)).shouldHave(text(expectedText2));
        return this;
    }
}