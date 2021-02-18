package mailinator;

import ATD.PasswordRecovery_page_Logic;
import Common.DataBase;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Common.CommonMethods.checkingContainsUrl;
import static Common.CommonMethods.waitWhileRouteBecomeExpected;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
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

    public ElementsCollection collectionSubjectLetter() {
        return $$x("//td[@class='subject']//span[@class='subject']//a//span");
    }

    public SelenideElement subjectLetter(int numberLetter) {
        return $x("(//td[@class='subject']/span[3]/a/span)[" + numberLetter + "]");
    }

    public SelenideElement letter(int numberLetter) {
        return $x("(//td[@class='subject'])[" + numberLetter + "]");
    }

    public SelenideElement letterWithOrderNumber(String orderNumber) {
        return $x("//td[@class='subject']//span[contains(text(),'" + orderNumber + "')]");
    }

    public SelenideElement unreadLetter() {
        return $x("(//span[@title='Непрочитанные '])[1]");
    }

    //Letter
    public SelenideElement linkFAQemailConfirm() {
        return $(".btn-wrapper > a");
    }

    private SelenideElement linkInRestorePasswordLetter() {
        return $x("//a[contains(@href,'recovery')]");
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
        return $x("//td[@style='padding: 0']//a[@target='_blank']");
    }

    private ElementsCollection allLinkToDeliveryPage() {
        return $$x("//td[@style='padding: 0']//a[@target='_blank']");
    }

    private SelenideElement trackingNumber() {
        return $x("//b[2]");
    }

    private SelenideElement blockOfRequisites() {
        return $x("//table[@class='info-inline green-border']//tbody//tr//td");
    }

    private SelenideElement toolBarBlock() {
        return $(byId("messagetoolbar"));
    }

    private ElementsCollection elementsOfToolbarBeforeLetters() {
        return $$x("//div[@id='messagetoolbar']/*[self::a or self::span]").filter(visible);
    }


    //Locators for old mail service Mailinator
    public SelenideElement letterInfo(int numberLetter) {
        return $(byXpath("//*[contains(@class,'ng-scope')]/../tr[" + numberLetter + "]"));
    }

    private ElementsCollection countOfLetters() {
        return $$x("//table[@id='messagelist']/tbody/tr");
    }

    private SelenideElement btnDeleteLetter() {
        return $x("//a[@class='button delete']");
    }

    private SelenideElement tableOfLetters() {
        return $(byId("messagelistcontainer"));
    }

    public SelenideElement letterMailinator(int numberLetter) {
        return $x("(//tr[@class='ng-scope'])[" + numberLetter + "]");
    }

    public SelenideElement btnConfirmSubscriptions() {
        return $x("//td[@class='es-footer-body-text']/a");
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
        for (int i = 0; i < collectionSubjectLetter().size(); i++) {
            String textNameLetter = collectionSubjectLetter().get(i).getText();
            if (textNameLetter.contains(orderNumber)) {
                collectionSubjectLetter().get(i).click();
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
        String textNameLetter = null;
        for (int i = 0; i < collectionSubjectLetter().size(); i++) {
            textNameLetter = collectionSubjectLetter().get(i).getText();
            if (textNameLetter.equals(expectedTextNameLetter + " " + orderNumber)) {
                collectionSubjectLetter().get(i).click();
                switchTo().frame("messagecontframe");
                break;
            }
        }
        assert textNameLetter != null;
        if (!textNameLetter.equals(expectedTextNameLetter + " " + orderNumber)) {
            Assert.fail("Letter not found");
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
        String requisitesInMail = blockOfRequisites().getText().replaceAll(" ", "").replaceAll("\n", "").toLowerCase();
        String requisitesInSuccessPage = expectedRequisites.replaceAll(",", ".").replaceAll(" €", "")
                .replaceAll(" ", "").replaceAll("\n", "").toLowerCase();
        Assert.assertEquals(requisitesInMail, requisitesInSuccessPage);
        return this;
    }

    @Step("Compares the text of the requisites in the email with expected requisites. WebMail")
    public WebMail compareExpectedRequisitesWithActual(String shop) throws SQLException {
        String requisitesInMail = blockOfRequisites().getText().replaceAll("\n", " ");
        List<String> requisitesForUniqueCountries;
        List<String> requisitesForOther;
        if (shop.equals("AT") || shop.equals("CH") || shop.equals("CZ") || shop.equals("DK") || shop.equals("EN") || shop.equals("HU") || shop.equals("NO") || shop.equals("PL") ||
                shop.equals("RO") || shop.equals("SE") || shop.equals("BG")) {
            requisitesForUniqueCountries = new DataBase("PKW").getNameRequisitesMethod("bank_requisites_pkw", shop, "Owner",
                    "Account number", "Sort Code", "Bank", "IBAN", "BIC_SWIFT");
            for (String a : requisitesForUniqueCountries) {
                Assert.assertTrue(requisitesInMail.contains(a));
            }
        } else {
            requisitesForOther = new DataBase("PKW").getNameRequisitesMethod("bank_requisites_pkw", "other", "Owner",
                    "Account number", "Sort Code", "Bank", "IBAN", "BIC_SWIFT");
            for (String a : requisitesForOther) {
                Assert.assertTrue(requisitesInMail.contains(a));
            }
        }
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
        return trackingNumber().getText();
    }

    @Step("Transition to delivery page ang get URL. WebMail")
    public String transitionToDeliveryPageAndGetUrlFromMail(String deliveryService) {
        String deliveryPageURL = null;
        if (deliveryService.equals("DHL")) {
            linkToDeliveryPage().click();
            switchTo().window(1);
            deliveryPageURL = url().replaceAll("&rfn.+", "").replaceAll("html?.+.=", "");
            closeWindow();
            switchTo().window(0);
        } else {
            linkToDeliveryPage().click();
            switchTo().window(1);
            deliveryPageURL = url();
            closeWindow();
            switchTo().window(0);
        }
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
        regularDeliveryPriceInEmail().shouldHave(text(regularDeliveryPrice.replaceAll(",",".")));
        return this;
    }

    @Step("Checks delivery costs for a Country {deliveryCostForCountry} or Region {deliveryCostForRegion}. WebMail")
    public WebMail checkDeliveryCostForCountryOrRegion(float deliveryCostForRegion, float deliveryCostForCountry, String shop) {
        if (shop.equals("DE")) {
            checkRegularDeliveryPriceInEmail(String.valueOf(deliveryCostForRegion));
        }
        if (shop.equals("Germany")) {
            checkRegularDeliveryPriceInEmail(String.valueOf(deliveryCostForRegion));
        }
        if (shop.equals("LI")) {
            checkRegularDeliveryPriceInEmail(String.valueOf(deliveryCostForCountry));
        }
        if (shop.equals("Liechtenstein")) {
            checkRegularDeliveryPriceInEmail(String.valueOf(deliveryCostForCountry));
        }
        return this;
    }

    @Step("Get total price in email. WebMail")
    public Float getTotalPriceInEmail() {
        String realPrice = infoTotalPriceInEmail().getText();
        realPrice = realPrice.replaceAll("[^0-9.]", "");
        realPrice = realPrice.replaceAll(",", ".");
        return Float.parseFloat(realPrice);
    }

    @Step("Get unit price in email. WebMail")
    public Float getUnitPriceInEmail() {
        String realPrice = unitPrice().getText();
        realPrice = realPrice.replaceAll("[^0-9.]", "").replace(",", ".");
        return Float.parseFloat(realPrice);
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
        letterMailinator(numberLetter).waitUntil(visible,20000);
        letterMailinator(numberLetter).shouldBe(appear);
        letterMailinator(numberLetter).click();
        switchTo().frame("html_msg_body");
        return this;
    }

    @Step("Check letter info text in old mail service Mailinator. WebMail")
    public WebMail checkLetterInfoText(int letterNumber, String expectedText1, String expectedText2) {
        letterInfo(letterNumber).waitUntil(visible,20000).shouldHave(text(expectedText1)).shouldHave(text(expectedText2));
        return this;
    }

    @Step("Click btn Confirm Subscriptions in letter Mailinator. WebMail")
    public WebMail clickBtnConfirmSubscriptions() {
        btnConfirmSubscriptions().shouldBe(visible).click();
        if (btnConfirmSubscriptions().isDisplayed()) {
            btnConfirmSubscriptions().shouldBe(visible).click();
        }
        switchTo().window(1);
        checkingContainsUrl("confirm_promo_subscription");
        waitWhileRouteBecomeExpected("main");
        return this;
    }

    @Step("get total count of letters. WebMail")
    public int getTotalCountOfLetters() {
        tableOfLetters().shouldBe(visible);
        return countOfLetters().size();
    }

    @Step("delete all letters. WebMail")
    public WebMail deleteAllLetters() {
        if (countOfLetters().get(0).isDisplayed()) {
            countOfLetters().get(0).click();
        }
        while (btnDeleteLetter().isDisplayed()) {
            btnDeleteLetter().click();
        }
        return this;
    }

    @Step("Opens email service with logged user. WebMail")
    public WebMail openMailWithLoggedUser() {
        open("https://webmail.autodoc.de/");
        return this;
    }


    @Step("presence of toolbar elements. WebMail")
    public WebMail presenceOfToolbarElements() {
        toolBarBlock().shouldBe(visible);
        for (int i = 0; i < elementsOfToolbarBeforeLetters().size(); i++) {
            elementsOfToolbarBeforeLetters().get(i).shouldBe(visible);
        }
        return this;
    }
}
