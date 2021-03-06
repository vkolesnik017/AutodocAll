package ATD;

import Common.DataBase;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ATD.CommonMethods.getCurrentShopFromJSVarInHTML;
import static ATD.CommonMethods.waitingElementVisibility;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class CartAddress_page_Logic extends CartAddress_page {

    @Step("Filling fields. CartAddress_page")
    public CartAddress_page_Logic fillAllFieldsForShipping(String shop) {
        checkCorrectTextAndFillInput(fieldName(), "autotest");
        checkCorrectTextAndFillInput(fieldLastName(), "autotest");
        checkCorrectTextAndFillInput(fieldStreet(), "autotest");
        checkCorrectTextAndFillInput(fieldHouse(), "autotest");
        chooseDeliveryCountryForShipping(shop);
        fillInPostalCode("default");
        checkCorrectTextAndFillInput(fieldCity(), "autotest");
        checkCorrectTextAndFillInput(fieldTelephoneShipping(), "200+002");
        return this;
    }

    @Step("Filling fields. CartAddress_page")
    public CartAddress_page_Logic fillAllFieldsForShipping(String name, String surname, String street,
                                                           String house, String shop, String city, String telNum) {
        checkCorrectTextAndFillInput(fieldName(), name);
        checkCorrectTextAndFillInput(fieldLastName(), surname);
        checkCorrectTextAndFillInput(fieldStreet(), street);
        checkCorrectTextAndFillInput(fieldHouse(), house);
        chooseDeliveryCountryForShipping(shop);
        fillInPostalCode("default");
        checkCorrectTextAndFillInput(fieldCity(), city);
        checkCorrectTextAndFillInput(fieldTelephoneShipping(), telNum);
        return this;
    }

    @Step("Filling fields for Billing. CartAddress_page")
    public CartAddress_page_Logic fillAllFieldsForBilling(String shop) {
        checkCorrectTextAndFillInput(fieldNameBilling(), "autotest");
        checkCorrectTextAndFillInput(fieldLastNameBilling(), "autotest");
        checkCorrectTextAndFillInput(fieldStreetBilling(), "autotest");
        checkCorrectTextAndFillInput(fieldHouseBilling(), "autotest");
        chooseDeliveryCountryForBilling(shop);
        fillInPostalCodeForBilling("default");
        checkCorrectTextAndFillInput(fieldCityBilling(), "autotest");
        checkCorrectTextAndFillInput(fieldTelephoneBilling(), "200+002");
        return this;
    }

    @Step("Filling fields for Billing. CartAddress_page")
    public CartAddress_page_Logic fillAllFieldsForBilling(String name, String surname, String street,
                                                          String house, String shop, String city, String telNum) {
        checkCorrectTextAndFillInput(fieldNameBilling(), name);
        checkCorrectTextAndFillInput(fieldLastNameBilling(), surname);
        checkCorrectTextAndFillInput(fieldStreetBilling(), street);
        checkCorrectTextAndFillInput(fieldHouseBilling(), house);
        chooseDeliveryCountryForBilling(shop);
        fillInPostalCodeForBilling("default");
        checkCorrectTextAndFillInput(fieldCityBilling(), city);
        checkCorrectTextAndFillInput(fieldTelephoneBilling(), telNum);
        return this;
    }

    @Step("Fill in all fields with default values and also fill fields Shop {shop}, Index {index}, Firm {name Form} and City {city} for Shipping. CartAddress_page")
    public CartAddress_page_Logic fillAllFieldsAndFirmForShipping(String shop, String index, String nameFirm, String city) {
        checkCorrectTextAndFillInput(fieldName(), "autotest");
        checkCorrectTextAndFillInput(fieldLastName(), "autotest");
        checkCorrectTextAndFillInput(fieldStreet(), "autotest");
        checkCorrectTextAndFillInput(fieldHouse(), "autotest");
        fillingPostalCodeFieldJSForShipping(index);
        checkCorrectTextAndFillInput(fieldCity(), city);
        chooseDeliveryCountryForShipping(shop);
        checkCorrectTextAndFillInput(fieldTelephoneShipping(), "200+002");
        if (!fieldFirm().isDisplayed()) {
            checkboxFirmShipping().click();
        }
        checkCorrectTextAndFillInput(fieldFirm(), nameFirm);
        return this;
    }

    @Step("Fill in all fields with default values and also fill fields Name {name}, Surname {surname}, Street {street}, " +
            "House{house}, Shop {shop}, Index {index}, Firm {name Form} and City {city} for Shipping. CartAddress_page")
    public CartAddress_page_Logic fillAllFieldsAndDefaultPostalCode(String name, String surname, String street,
                                                                    String house, String shop, String nameFirm, String city) {
        checkCorrectTextAndFillInput(fieldName(), name);
        checkCorrectTextAndFillInput(fieldLastName(), surname);
        checkCorrectTextAndFillInput(fieldStreet(), street);
        checkCorrectTextAndFillInput(fieldHouse(), house);
        fillingPostalCodeOrDefaultFieldJSForShipping("default");
        checkCorrectTextAndFillInput(fieldCity(), city);
        chooseDeliveryCountryForShipping(shop);
        checkCorrectTextAndFillInput(fieldTelephoneShipping(), "200+002");
        if (!fieldFirm().isDisplayed()) {
            checkboxFirmShipping().click();
        }
        checkCorrectTextAndFillInput(fieldFirm(), nameFirm);
        return this;
    }

    @Step("Fill field tax number {taxNumber} for Shipping. CartAddress_page")
    public CartAddress_page_Logic fillFieldIdCompanyShipping(String taxNumber) {
        checkCorrectTextAndFillInput(fieldIdCompanyShipping(), taxNumber);
        return this;
    }

    @Step("Fill field name firm {firmName} for Shipping. CartAddress_page")
    public CartAddress_page_Logic fillFieldFirmNameForShipping(String firmName) {
        if (!fieldFirm().isDisplayed()) {
            checkboxFirmShipping().click();
        }
        checkCorrectTextAndFillInput(fieldFirm(), firmName);
        return this;
    }

    @Step("Fill in the company ID {expectedID} field for the delivery country where ID is needed {expectedShop}. CartAddress_page")
    public CartPayments_page_Logic fillInCompanyIdFieldForCountryWhereIdNeeded(String actualShop, String expectedShop, String expectedID) {
        if (actualShop.equals(expectedShop)) {
            if (fieldIdCompanyShipping().isDisplayed()) {
                fillFieldIdCompanyShipping(expectedID)
                        .nextBtnClick();
            } else if (!fieldIdCompanyShipping().isDisplayed()) {
                nextBtnClick();
            }
            if (continueBtnInPopupAboutWrongCompany().isDisplayed()) {
                clickBtnContinueInPopupAboutWrongCompany();
            }
        } else {
            nextBtnClick();
        }
        return page(CartPayments_page_Logic.class);
    }

    @Step("Fill field telephone number {telNum} for Shipping. CartAddress_page")
    public CartAddress_page_Logic fillFieldTelNumForShipping(String telNum) {
        fieldTelephoneShipping().shouldBe(visible);
        checkCorrectTextAndFillInput(fieldTelephoneShipping(), telNum);
        return this;
    }

    @Step("Fill field telephone number {telNum} for Billing. CartAddress_page")
    public CartAddress_page_Logic fillFieldTelNumForBilling(String telNum) {
        fieldTelephoneBilling().shouldBe(visible);
        checkCorrectTextAndFillInput(fieldTelephoneBilling(), telNum);
        return this;
    }

    @Step("Fill in all fields with default values and also fill fields Shop {shop}, Index {index}, Firm {nameFirm}, City {city} for Billing. CartAddress_page")
    public CartAddress_page_Logic fillAllFieldsAndFirmForBilling(String shopBilling, String indexBilling, String cityBilling, String nameFirmBilling) {
        if (!billingForm().isDisplayed()) {
            billingCheckBox().click();
        }
        checkCorrectTextAndFillInput(fieldNameBilling(), "autotest");
        checkCorrectTextAndFillInput(fieldLastNameBilling(), "autotest");
        checkCorrectTextAndFillInput(fieldStreetBilling(), "autotest");
        checkCorrectTextAndFillInput(fieldHouseBilling(), "autotest");
        fillingPostalCodeFieldJSForBilling(indexBilling);
        checkCorrectTextAndFillInput(fieldCityBilling(), cityBilling);
        chooseDeliveryCountryForBilling(shopBilling);
        checkCorrectTextAndFillInput(fieldTelephoneBilling(), "200+002");
        if (!fieldFirmBilling().isDisplayed()) {
            checkboxFirmBilling().click();
        }
        checkCorrectTextAndFillInput(fieldFirmBilling(), nameFirmBilling);
        return this;
    }

    @Step("Fill field tax number {taxNumber} for Billing. CartAddress_page")
    public CartAddress_page_Logic fillFieldIdCompanyBilling(String taxNumber) {
        checkCorrectTextAndFillInput(fieldIdCompanyBilling(), taxNumber);
        return this;
    }

    @Step("Click checkbox consent to call. CartAddress_page")
    public CartAddress_page_Logic clickCheckBoxConsentToCall() {
        checkBoxConsentToCall().shouldBe(visible).click();
        return this;
    }

    @Step("Checks what the checkbox and text 'Consent to call' are missing. CartAddress_page")
    public CartAddress_page_Logic checkAbsenceConsentToCall() {
        checkBoxConsentToCall().shouldNotBe(visible);
        textConsentToCall().shouldNotBe(visible);
        return this;
    }

    @Step("Checks that the checkbox and the text 'Consent to Call' are present.. CartAddress_page")
    public CartAddress_page_Logic checkPresenceConsentToCall() {
        checkBoxConsentToCall().shouldBe(visible);
        textConsentToCall().shouldBe(visible);
        return this;
    }


    @Step("Checking correct text in input field. CartAddress_page")
    public void checkCorrectTextAndFillInput(SelenideElement element, String correctText) {
        Configuration.fastSetValue = false;
        if (!element.getValue().equals(correctText)) {
            element.clear();
            element.setValue(correctText);
        }
    }

    @Step("Logo click. CartAddress_page")
    public Main_page_Logic logoClick() {
        $(By.xpath("//div[@class='cart-page-head__logo']")).click();
        return page(Main_page_Logic.class);
    }

    @Step("Next button click. CartAddress_page")
    public CartPayments_page_Logic nextBtnClick() {
        nextButton().shouldBe(visible).click();
        sleep(5000);
        return page(CartPayments_page_Logic.class);
    }

    @Step("Filling fiscal code field. CartAddress_page")
    public CartAddress_page_Logic fillInFiscalCode() {
        if (fieldFiscalCode().isDisplayed()) {
            fieldFiscalCode().clear();
            fieldFiscalCode().setValue("1111");
        }
        return this;
    }

    @Step("Filling postal code {postalCodeOrCodeDefault}. CartAddress_page")
    public CartAddress_page_Logic fillInPostalCode(String postalCodeOrCodeDefault) {
        if (postalCodeOrCodeDefault.equals("default")) {
            String currentShop = getCurrentShopFromJSVarInHTML();
            switch (currentShop) {
                case "DK":
                    postalCodeOrCodeDefault = "1234";
                    break;
                case "NL":
                    postalCodeOrCodeDefault = "1234 AA";
                    break;
                case "PT":
                    postalCodeOrCodeDefault = "1234-567";
                    break;
                default:
                    postalCodeOrCodeDefault = "12345";
                    break;
            }
        }
        fieldPostalCodeForShipping().clear();
        fieldPostalCodeForShipping().click();
        checkCorrectTextAndFillInput(fieldPostalCodeForShipping(), postalCodeOrCodeDefault);
        return this;
    }

    @Step("Filling postal code {postalCodeOrCodeDefault} for Billing. CartAddress_page")
    public CartAddress_page_Logic fillInPostalCodeForBilling(String postalCodeOrCodeDefault) {
        if (postalCodeOrCodeDefault.equals("default")) {
            String currentShop = getCurrentShopFromJSVarInHTML();
            switch (currentShop) {
                case "DK":
                    postalCodeOrCodeDefault = "1234";
                    break;
                case "NL":
                    postalCodeOrCodeDefault = "1234 AA";
                    break;
                case "PT":
                    postalCodeOrCodeDefault = "1234-567";
                    break;
                default:
                    postalCodeOrCodeDefault = "12345";
                    break;
            }
        }
        fieldPostalCodeForBilling().clear();
        fieldPostalCodeForBilling().click();
        checkCorrectTextAndFillInput(fieldPostalCodeForBilling(), postalCodeOrCodeDefault);
        return this;
    }

    // Enters characters one by one
    @Step("Filling postal code {sendPostalCode}. CartAddress_page")
    public CartAddress_page_Logic fillingPostalCodeField(String sendPostalCode) {
        fieldPostalCodeForShipping().click();
        char[] array = sendPostalCode.toCharArray();
        for (char anArray : array) {
            String send = String.valueOf(anArray);
            sleep(1000);
            getWebDriver().findElement(By.id("form_lPlz")).sendKeys(send);
        }
        return this;
    }

    @Step("Filling postal code {sendPostalCode} for shipping. CartAddress_page")
    public CartAddress_page_Logic fillingPostalCodeFieldJSForShipping(String sendPostalCode) {
        fieldPostalCodeForShipping().waitUntil(appear, 10000);
        JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
        js.executeScript("arguments[0].value='" + sendPostalCode + "';", fieldPostalCodeForShipping());
        return this;
    }

    @Step("Filling postal code {sendPostalCode} for billing. CartAddress_page")
    public CartAddress_page_Logic fillingPostalCodeFieldJSForBilling(String sendPostalCode) {
        fieldPostalCodeForBilling().waitUntil(appear, 10000);
        JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
        js.executeScript("arguments[0].value='" + sendPostalCode + "';", fieldPostalCodeForBilling());
        return this;
    }

    @Step("Filling postal code {postalCodeOrCodeDefault} or default for shipping. CartAddress_page")
    public CartAddress_page_Logic fillingPostalCodeOrDefaultFieldJSForShipping(String postalCodeOrCodeDefault) {
        if (postalCodeOrCodeDefault.equals("default")) {
            String currentShop = getCurrentShopFromJSVarInHTML();
            switch (currentShop) {
                case "AT":
                    postalCodeOrCodeDefault = "4321";
                    break;
                case "DK":
                    postalCodeOrCodeDefault = "1234";
                    break;
                case "NL":
                    postalCodeOrCodeDefault = "1234 AA";
                    break;
                case "PT":
                    postalCodeOrCodeDefault = "1234-567";
                    break;
                case "GR":
                    postalCodeOrCodeDefault = "123 45";
                    break;
                default:
                    postalCodeOrCodeDefault = "12345";
                    break;
            }
        }
        fieldPostalCodeForShipping().waitUntil(appear, 10000);
        JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
        js.executeScript("arguments[0].value='" + postalCodeOrCodeDefault + "';", fieldPostalCodeForShipping());
        return this;
    }

    @Step("Filling postal code {postalCodeOrCodeDefault} or default for shipping. CartAddress_page")
    public CartAddress_page_Logic fillingPostalCodeOrDefaultFieldJSForShipping(String postalCodeOrCodeDefault, String shop) {
        if (postalCodeOrCodeDefault.equals("default")) {
            switch (shop) {
                case "AT":
                    postalCodeOrCodeDefault = "4321";
                    break;
                case "DK":
                    postalCodeOrCodeDefault = "1234";
                    break;
                case "NL":
                    postalCodeOrCodeDefault = "1234 AA";
                    break;
                case "PT":
                    postalCodeOrCodeDefault = "1234-567";
                    break;
                case "GR":
                    postalCodeOrCodeDefault = "123 45";
                    break;
                default:
                    postalCodeOrCodeDefault = "12345";
                    break;
            }
        }
        fieldPostalCodeForShipping().waitUntil(appear, 10000);
        JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
        js.executeScript("arguments[0].value='" + postalCodeOrCodeDefault + "';", fieldPostalCodeForShipping());
        return this;
    }

    @Step("Choosing delivery country {country} for shipping. CartAddress_page")
    public CartAddress_page_Logic chooseDeliveryCountryForShipping(String country) {
        if (country.equals("EN")) country = "GB";
        if (country.equals("LD")) country = "LU";
        countryInSelectorForShipping(country).shouldBe(visible).click();
        return this;
    }

    @Step("Choosing delivery country {country} for sipping and get name country. CartAddress_page")
    public String chooseDeliveryCountryAndGetNameCountry(String country) {
        if (country.equals("EN")) country = "GB";
        if (country.equals("LD")) country = "LU";
        countryInSelectorForShipping(country).shouldBe(visible).click();
        String nameCountry = countryInSelectorForShipping(country).getText();
        String actualNameCountry;
        if (nameCountry.equals("Portugal ")) {
            actualNameCountry = nameCountry.replaceAll(" ", "");
        } else {
            actualNameCountry = nameCountry;
        }
        return actualNameCountry;
    }

    @Step("Choosing delivery country {country} for billing. CartAddress_page")
    public CartAddress_page_Logic chooseDeliveryCountryForBilling(String country) {
        if (country.equals("EN")) country = "GB";
        if (country.equals("LD")) country = "LU";
        countryInSelectorForBilling(country).shouldBe(visible).click();
        return this;
    }

    @Step("Choosing delivery country {country} and Filling postal code {sendPostalCode} for shipping and billing. CartAddress_page")
    public CartAddress_page_Logic chooseDeliveryCountryAndFillingPostalCode(String countryShipping, String sendPostalCodeShipping, String countryBilling, String sendPostalCodeBilling) {
        chooseDeliveryCountryForShipping(countryShipping);
        fillingPostalCodeFieldJSForShipping(sendPostalCodeShipping);
        if (!billingForm().isDisplayed()) {
            billingCheckBox().click();
        }
        chooseDeliveryCountryForBilling(countryBilling);
        fillingPostalCodeFieldJSForBilling(sendPostalCodeBilling);
        return this;
    }


    @Step("Click checkbox for open billing form. CartAddress_page")
    public CartAddress_page_Logic clickCheckboxForOpenBilling() {
        billingCheckBox().waitUntil(visible,3000);
        if (!billingForm().isDisplayed()) {
            billingCheckBox().click();
        }
        return this;
    }

    @Step("Click checkbox for close billing form. CartAddress_page")
    public CartAddress_page_Logic clickCheckboxForCloseBilling() {
        if (billingForm().isDisplayed()) {
            billingCheckBox().click();
        }
        return this;
    }

    @Step("Checks presence popup with an error about the wrong company. CartAddress_page")
    public CartAddress_page_Logic checkPresencePopupErrorAboutWrongCompany() {
        popupErrorAboutWrongCompany().shouldBe(visible);
        return this;
    }

    @Step("Click button Fix in popup about wrong company. CartAddress_page")
    public CartAddress_page_Logic clickBtnFixInPopupAboutWrongCompany() {
        BtnFixInPopupAboutWrongCompany().click();
        return this;
    }

    @Step("Click button Continue in popup about wrong company. CartAddress_page")
    public CartPayments_page_Logic clickBtnContinueInPopupAboutWrongCompany() {
        if (!continueBtnInPopupAboutWrongCompany().isDisplayed()) {
            nextBtnClick();
        } else {
            continueBtnInPopupAboutWrongCompany().click();
        }
        return page(CartPayments_page_Logic.class);
    }

    @Step("Click button EinkaufFortsetzen from popup with an error about the wrong company. CartAddress_page")
    public CartPayments_page_Logic clickBtnEinkaufFortsetzenFromPopupErrorAboutWrongCompany() {
        btnEinkaufFortsetzenFromPopupErrorAboutWrongCompany().click();
        return page(CartPayments_page_Logic.class);
    }

    //CONVID TEST
    @Step("Get text from tooltip COVID-19. CartAddress_page")
    public String getTextFromTooltipCOVID19() {
        tooltipCOVID19().shouldBe(visible);
        return tooltipCOVID19().getText();
    }

    @Step("Checking of blocking plz {sendPostalCode} for country {country} with split billing and shipping. CartAddress_page")
    public CartAddress_page_Logic checkBlockingPLZForCountry(String countryShipping, String sendPostalCodeShipping, String countryBilling, String sendPostalCodeBilling) {
        chooseDeliveryCountryForShipping(countryShipping);
        fillingPostalCodeFieldJSForShipping(sendPostalCodeShipping);
        if (!billingForm().isDisplayed()) {
            billingCheckBox().click();
        }
        chooseDeliveryCountryForBilling(countryBilling);
        fillingPostalCodeFieldJSForBilling(sendPostalCodeBilling);
        nextBtnClick();
        tooltipCOVID19().shouldBe(visible);
        return this;
    }

    @Step("Checking block plz for country {countryCheck} on skin {skin}. CartAddress_page")
    public CartAddress_page_Logic checkingCOVID19Block(String countryCheck, String[] shopPlz, String file, String skin) throws IOException {
        chooseDeliveryCountryForShipping(countryCheck);
        for (String plz : shopPlz) {
            if (countryCheck.equals("IT") || countryCheck.equals("PT")) {
                List<String> plzForChek = new ArrayList<>();
                if (countryCheck.equals("IT")) plzForChek = parsingAndCheckCOVIDBlockPlzForIT(plz);
                if (countryCheck.equals("PT")) plzForChek = parsingAndCheckCOVIDBlockPlzForPT(plz);
                for (String plzForShop : plzForChek) {
                    checkingAppearingCOVIDTooltip(countryCheck, plzForShop, file, skin);
                }
            } else checkingAppearingCOVIDTooltip(countryCheck, plz, file, skin);
        }
        return this;
    }

    @Step("Parsing plz {plz} and checking block for IT shop. CartAddress_page")
    private List<String> parsingAndCheckCOVIDBlockPlzForIT(String plz) {
        List<String> plzForIT = new ArrayList<>();
        if (plz.contains("-")) {
            int beginIndex = Integer.parseInt(plz.substring(0, plz.indexOf("-")));
            int endIndex = Integer.parseInt(plz.substring(plz.indexOf("-") + 1));
            for (int i = beginIndex; i <= endIndex; i++) {
                StringBuilder plzParce = new StringBuilder(String.valueOf(i));
                while (plzParce.length() < 5) {
                    plzParce.insert(0, "0");
                }
                plzForIT.add(String.valueOf(plzParce));
            }
        } else {
            StringBuilder plzParce = new StringBuilder(String.valueOf(plz));
            while (plzParce.length() < 5) {
                plzParce.insert(0, "0");
            }
            plzForIT.add(String.valueOf(plzParce));
        }
        return plzForIT;
    }

    @Step("Parsing plz {plz} and checking block for PT shop. CartAddress_page")
    public List<String> parsingAndCheckCOVIDBlockPlzForPT(String plz) {
        List<String> plzForPT = new ArrayList<>();
        int firstPlzValue = Integer.parseInt(plz.substring(0, plz.indexOf("-")));
        int beginIndex = Integer.parseInt(plz.substring(plz.indexOf("-"), 8));
        int endIndex = Integer.parseInt(plz.substring(plz.lastIndexOf("-") + 1, 17));
        for (int i = beginIndex; i <= endIndex; i++) {
            StringBuilder plzParce = new StringBuilder(String.valueOf(i));
            while (plzParce.length() < 3) {
                plzParce.insert(0, "0");
            }
            plzForPT.add(firstPlzValue + "-" + String.valueOf(plzParce));
        }
        return plzForPT;
    }

    @Step("Checking appearing COVID Tooltip for country {countryCheck} with {plz} on skin {skin} . CartAddress_page")
    private CartAddress_page_Logic checkingAppearingCOVIDTooltip(String countryCheck, String plz, String
            file, String skin) throws IOException {
        fillingPostalCodeFieldJSForShipping(plz);
        nextBtnClick();
        try {
            tooltipCOVID19().waitUntil(appear, 10000);
            if (!tooltipCOVID19().getText().contains("COVID")) {
                sleep(2000);
                fieldPostalCodeForShipping().click();
                nextBtnClick();
            }
            tooltipCOVID19().shouldHave(text("COVID"));
        } catch (ElementNotFound redirectOnPaymentsPage) {
            System.err.println(plz + " err");
            new CommonMethods().writerInFile(file, true, "Country check: " + countryCheck + " PLZ: " + plz + " On skin: " + skin);
            back();
        }
        return this;
    }

    @Step("Checking COVID-19 tooltip translate for country {countryCheck} with PLZ {plz} on shop {shop}. CartAddress_page")
    public CartAddress_page_Logic checkingCOVID19TooltipTranslate(String countryCheck, String plz, String shop) throws
            SQLException {
        chooseDeliveryCountryForShipping(countryCheck);
        fillingPostalCodeFieldJSForShipping(plz);
        nextBtnClick();
        tooltipCOVID19().waitUntil(appear, 5000);
        String plzPopupText = getTextFromTooltipCOVID19();
        Assert.assertEquals(plzPopupText, new DataBase("ATD").getTranslate("convir_translate", shop, "addres"), "Error plz:" + plz);
        return this;
    }

    @Step("Checks that the address fields are empty. CartAddress_page")
    public CartAddress_page_Logic checkThatAddressFieldsAreEmpty() {
        fieldName().shouldHave(attribute("value", ""));
        fieldLastName().shouldHave(attribute("value", ""));
        fieldStreet().shouldHave(attribute("value", ""));
        fieldHouse().shouldHave(attribute("value", ""));
        fieldPostalCodeForShipping().shouldHave(attribute("value", ""));
        fieldCity().shouldHave(attribute("value", ""));
        fieldTelephoneShipping().shouldHave(attribute("value", ""));
        return this;
    }

    @Step("Checks that country selected by default corresponds to the shop. CartAddress_page")
    public CartAddress_page_Logic checkDefaultCountryInSelector(String shop) {
        countryInSelectorForShipping(shop).shouldHave(attribute("selected"));
        return this;
    }

    @Step("Checks presence shipping form. CartAddress_page")
    public CartAddress_page_Logic checkPresenceShippingForm() {
        shippingForm().shouldBe(visible);
        return this;
    }

    @Step("Check correct text {expectedText} in personal number tooltip. CartAddress_page")
    public CartAddress_page_Logic checkCorrectTextInPersonalNumberTooltip(String expectedText) {
        personalNumberBlock().shouldBe(visible);
        infoLabelForPersonalNumber().hover();
        textFromPersonalNumberTooltip().shouldBe(visible).shouldHave(text(expectedText));
        return this;
    }

    @Step("Filling field personal number {expectedText}. CartAddress_page")
    public CartAddress_page_Logic fillingFieldPersonalNumber(String expectedText) {
        personalNumberBlock().shouldBe(visible);
        inputPersonalNumber().setValue(expectedText);
        return this;
    }

    @Step("Click get My Address button. CartAddress_page")
    public CartAddress_page_Logic clickGetMyAddressBtn() {
        getMyAddressBtn().click();
        sleep(2000);
        return this;
    }

    @Step("Check correct text {expectedText} in error message for personal number. CartAddress_page")
    public CartAddress_page_Logic checkCorrectTextInErrorMessage(String expectedText) {
        sleep(2000);
        if (!errorMessage().isDisplayed()) {
            nextBtnClick();
            errorMessage().shouldHave(text(expectedText));
        }
        errorMessage().shouldHave(text(expectedText));
        return this;
    }

    @Step("Check correct text {expectedText} in error tooltip for postal cod. CartAddress_page")
    public CartAddress_page_Logic checkCorrectTextInErrorInErrorTooltipForPostalCod(SelenideElement element, String expectedText) {
        element.shouldBe(visible);
        element.shouldHave(text(expectedText));
        return this;
    }

    @Step("Checks presence element {expectedElement}. CartAddress_page")
    public CartAddress_page_Logic checkPresenceElement(SelenideElement expectedElement) {
        expectedElement.shouldBe(visible);
        return this;
    }

    @Step("Checks absence element {expectedElement}. CartAddress_page")
    public CartAddress_page_Logic checkAbsenceElement(SelenideElement expectedElement) {
        expectedElement.shouldNotBe(visible);
        return this;
    }

    @Step("Get zip mask and compares with expected {expectedMask} for Shipping. CartAddress_page")
    public CartAddress_page_Logic getZipMasksAndComparesWithExpectedForShipping(String expectedMask) {
        String zipMask = fieldPostalCodeForShipping().getAttribute("placeholder");
        Assert.assertEquals(zipMask, expectedMask);
        return this;
    }

    @Step("Get zip mask and compares with expected {expectedMask} for Billing. CartAddress_page")
    public CartAddress_page_Logic getZipMasksAndComparesWithExpectedForBilling(String expectedMask) {
        String zipMask = fieldPostalCodeForBilling().getAttribute("placeholder");
        Assert.assertEquals(zipMask, expectedMask);
        return this;
    }

    @Step("Get the previously entered zip code for shipping and compares with expected {expectedZipCode}. CartAddress_page")
    public CartAddress_page_Logic getZipCodeForShippingAndComparesWithExpected(String expectedZipCode) {
        String zipCode = fieldPostalCodeForShipping().getAttribute("value");
        Assert.assertEquals(zipCode, expectedZipCode);
        return this;
    }

    @Step("Get the previously entered zip code for billing and compares with expected {expectedZipCode}. CartAddress_page")
    public CartAddress_page_Logic getZipCodeForBillingAndComparesWithExpected(String expectedZipCode) {
        String zipCode = fieldPostalCodeForBilling().getAttribute("value");
        Assert.assertEquals(zipCode, expectedZipCode);
        return this;
    }

    @Step("Checks validation for the number of characters being entered into postcode field" +
            "for shipping and billing form. CartAddress_page")
    public CartAddress_page_Logic checkValidationEnteredNumberIntoZipCodeField(String shop, String zipCode,
                                                                               String expectedZipCodeShipping,
                                                                               String expectedZipCodeBilling) {
        chooseDeliveryCountryForShipping(shop);
        fillingPostalCodeField(zipCode);
        nextBtnClick()
                .clickBtnReturnTheAddressPage();
        getZipCodeForShippingAndComparesWithExpected(expectedZipCodeShipping);
        clickCheckboxForOpenBilling();
        getZipCodeForBillingAndComparesWithExpected(expectedZipCodeBilling);
        clickCheckboxForCloseBilling();
        return this;
    }

    @Step("Check presence fiscal code block in shipping form. CartAddress_page")
    public CartAddress_page_Logic checkPresenceFiscalCodBlockInShippingForm() {
        fiscalCodeBlockInSippingForm().shouldBe(visible);
        return this;
    }

    @Step("Click checkbox for open fiscal code field. CartAddress_page")
    public CartAddress_page_Logic clickCheckboxForOpenFiscalCodeField() {
        if (!fieldFiscalCode().isDisplayed()) {
            checkboxFiscalCode().click();
        }
        fieldFiscalCode().shouldBe(visible);
        return this;
    }

    @Step("Click checkbox for open fiscal code field for Billing block. CartAddress_page")
    public CartAddress_page_Logic clickCheckboxForOpenFiscalCodeFieldForBilling() {
        if (!fieldFiscalCodeBilling().isDisplayed()) {
            checkboxFiscalCodeBilling().click();
        }
        fieldFiscalCodeBilling().shouldBe(visible);
        return this;
    }

    @Step("Click checkbox for closed fiscal code field. CartAddress_page")
    public CartAddress_page_Logic clickCheckboxForClosedFiscalCodeField() {
        if (fieldFiscalCode().isDisplayed()) {
            checkboxFiscalCode().click();
        }
        fieldFiscalCode().shouldNotBe(visible);
        return this;
    }

    @Step("Filling field fiscal code {expectedText}. CartAddress_page")
    public CartAddress_page_Logic fillingFieldFiscalCode(String expectedText) {
        fieldFiscalCode().setValue(expectedText);
        return this;
    }

    @Step("Filling field fiscal code {expectedText} Billing block. CartAddress_page")
    public CartAddress_page_Logic fillingFieldFiscalCodeBilling(String expectedText) {
        fieldFiscalCodeBilling().setValue(expectedText);
        return this;
    }

    @Step("Check presence a note with address restrictions and correct text inside block {expectedText}. CartAddress_page")
    public CartAddress_page_Logic checkPresenceNotesAndTextInsideBlock(String expectedText) {
        notesWithAddressRestrictions().shouldBe(visible);
        notesWithAddressRestrictions().shouldHave(text(expectedText));
        return this;
    }

    @Step("Checks absence safe order block after change delivery country. CartAddress_page")
    public CartAddress_page_Logic checkAbsenceSafeOrderBlockAfterChangeDeliveryCountry(String[] listOfCountry) {
        Cart_page_Logic cart_page_logic = new Cart_page_Logic();
        checkCorrectTextAndFillInput(fieldTelephoneShipping(), "200+002");
        for (int i = 0; i < listOfCountry.length; i++) {
            chooseDeliveryCountryForShipping(listOfCountry[i])
                    .nextBtnClick()
                    .choosePayPal()
                    .nextBtnClick()
                    .checkAbsenceSafeOrderBlock()
                    .clickBtnReturnToCartPage();
            waitingElementVisibility(cart_page_logic.orderSummeryBlock(), 5);
            cart_page_logic.checkAbsenceSafeOrderBlock()
                           .nextButtonClick();
            waitingElementVisibility(new CartAddress_page_Logic().shippingForm(), 5);
        }
        return this;
    }

    @Step("Checks steps basket on page cart addres. CartAddress_page")
    public CartAddress_page_Logic checkStepsBasketOnPageCartAddress() {
        secondStep().shouldBe(visible);
        thirdStep().shouldBe(visible);
        fourthStep().shouldBe(visible);
        return this;
    }

    @Step("Clear fields postal cod for billing and sipping block. CartAddress_page")
    public CartAddress_page_Logic clearFieldsPostalCod() {
        fieldPostalCodeForShipping().shouldBe(visible).click();
        checkCorrectTextAndFillInput(fieldPostalCodeForShipping(), "");
        fieldPostalCodeForBilling().shouldBe(visible).click();
        checkCorrectTextAndFillInput(fieldPostalCodeForBilling(), "");
        return this;
    }


    @Step("Check text for checkbox fiscal code.Billing or Shipping. CartAddress_page")
    public CartAddress_page_Logic checkTextForCheckboxFiscalCode(SelenideElement textFiscalCode, String shop) {
        switch (shop) {
            case "IT":
                textFiscalCode.shouldHave(exactText("Codice fiscale (Opzionale)"));
                break;
            case "PT":
                textFiscalCode.shouldHave(exactText("NIF (Opcional)"));
                break;
            case "RO":
                if (textFiscalCode.isDisplayed()) {
                    textFiscalCode.shouldHave(exactText("Num??r personal de identificare (Facultativ)"));
                }else if (!textFiscalCode.isDisplayed()) {
                    textFiscalCodeInBillingForm2().shouldHave(exactText("Num??r personal de identificare (Facultativ)"));
                }
                break;
        }
        return this;
    }

    @Step("Check visible / invisible fiscal code block (Billing or shipping block). CartAddress_page")
    public CartAddress_page_Logic checkPresenceCheckboxFiscalCode(SelenideElement fiscalCodeBlock, boolean visibleElement) {
        if (visibleElement == true) {
            fiscalCodeBlock.shouldHave(attribute("style", "display: flex;"));
        } else if (visibleElement == false) {
            fiscalCodeBlock.shouldHave(attribute("style", "display: none;"));
        }
        return this;
    }

    @Step("Click checkbox firm and open field for Shipping or Billing block. CartAddress_page")
    public CartAddress_page_Logic clickCheckboxFirmAndOpenField(SelenideElement checkBoxFirm, SelenideElement fieldFirm) {
        checkBoxFirm.shouldBe(visible).click();
        fieldFirm.shouldBe(visible);
        return this;
    }

    @Step("Click checkbox firm and close field for Shipping or Billing block. CartAddress_page")
    public CartAddress_page_Logic clickCheckboxFirmAndCloseField(SelenideElement checkBoxFirm, SelenideElement fieldFirm) {
        checkBoxFirm.shouldBe(visible).click();
        fieldFirm.shouldNotBe(visible);
        return this;
    }

    @Step("Check presence field fiscal code for Shipping block . CartAddress_page")
    public CartAddress_page_Logic checkPresenceFieldFiscalCodeForShipping(boolean fieldVisibleOrNot) {
        if (fieldVisibleOrNot == true) {
            fieldFiscalCode().shouldBe(visible);
        }else if (fieldVisibleOrNot == false)
            fieldFiscalCode().shouldNotBe(visible);
        return this;
    }

    @Step("Check presence field fiscal code for Billing block. CartAddress_page")
    public CartAddress_page_Logic checkPresenceFieldFiscalCodeForBilling(boolean fieldVisibleOrNot) {
        if (fieldVisibleOrNot == true) {
            fieldFiscalCodeBilling().shouldBe(visible);
        }else if (fieldVisibleOrNot == false)
            fieldFiscalCodeBilling().shouldNotBe(visible);
        return this;
    }

    @Step("Check presence error tooltip Fiscal Code field for Shipping. CartAddress_page")
    public CartAddress_page_Logic checkPresenceErrorTooltipFiscalCodeFieldForShipping() {
        errorTooltipFiscalCodeFieldForShipping().shouldBe(visible);
        Assert.assertFalse(errorTooltipFiscalCodeFieldForShipping().text().isEmpty());
        return this;
    }

    @Step("Check presence error tooltip Fiscal Code field for Billing. CartAddress_page")
    public CartAddress_page_Logic checkPresenceErrorTooltipFiscalCodeFieldForBilling() {
        errorTooltipFiscalCodeFieldForBilling().shouldBe(visible);
        Assert.assertFalse(errorTooltipFiscalCodeFieldForBilling().text().isEmpty());
        return this;
    }

    @Step ("Check presence text in fields for Shipping or Billing block. CartAddress_page")
    public CartAddress_page_Logic checkPresenceTextInFieldsForShippingOrBilling(SelenideElement element, boolean visibleText ) {
        if (visibleText == true) {
            Assert.assertFalse(element.getValue().isEmpty());
        }else if (visibleText == false) {
            Assert.assertTrue(element.getValue().isEmpty());
        }
        return this;
    }



}
