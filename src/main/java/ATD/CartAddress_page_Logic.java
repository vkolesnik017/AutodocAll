package ATD;

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
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class CartAddress_page_Logic extends CartAddress_page {

    @Step("Filling fields. CartAddress_page")
    public CartAddress_page_Logic fillAllFields(String shop) {
        checkCorrectTextAndFillInput(vorname(), "autotest");
        checkCorrectTextAndFillInput(nameIn(), "autotest");
        checkCorrectTextAndFillInput(strasse(), "autotest");
        checkCorrectTextAndFillInput(deliveryHouse(), "autotest");
        fillInPostalCode("default");
        checkCorrectTextAndFillInput(ort(), "autotest");
        chooseDeliveryCountryForShipping(shop);
        checkCorrectTextAndFillInput(telephon(), "200+002");
        return this;
    }

    @Step("Fill in all fields with default values and also fill fields Shop {shop}, Index {index}, Firm {name Form} and City {city} for Shipping. CartAddress_page")
    public CartAddress_page_Logic fillAllFieldsAndFirmForShipping(String shop, String index, String nameFirm, String city) {
        checkCorrectTextAndFillInput(vorname(), "autotest");
        checkCorrectTextAndFillInput(nameIn(), "autotest");
        checkCorrectTextAndFillInput(strasse(), "autotest");
        checkCorrectTextAndFillInput(deliveryHouse(), "autotest");
        fillingPostalCodeFieldJSForShipping(index);
        checkCorrectTextAndFillInput(ort(), city);
        chooseDeliveryCountryForShipping(shop);
        checkCorrectTextAndFillInput(telephon(), "200+002");
        if (!fieldFirm().isDisplayed()) {
            checkboxFirmShipping().click();
        }
        checkCorrectTextAndFillInput(fieldFirm(), nameFirm);
        return this;
    }

    @Step("Fill field tax number {taxNumber} for Shipping. CartAddress_page")
    public CartAddress_page_Logic fillFieldIdCompanyShipping(String taxNumber) {
        checkCorrectTextAndFillInput(idCompanyShipping(), taxNumber);
        return this;
    }

    @Step("Fill field telephone number {telNum} for Shipping. CartAddress_page")
    public CartAddress_page_Logic fillFieldTelNumForShipping(String telNum) {
        checkCorrectTextAndFillInput(telephon(), telNum);
        return this;
    }

    @Step("Fill in all fields with default values and also fill fields Shop {shop}, Index {index}, Firm {nameFirm}, City {city} for Billing. CartAddress_page")
    public CartAddress_page_Logic fillAllFieldsAndFirmForBilling(String shopBilling, String indexBilling, String cityBilling, String nameFirmBilling) {
        if (!billingForm().isDisplayed()) {
            billingCheckBox().click();
        }
        checkCorrectTextAndFillInput(vornameBilling(), "autotest");
        checkCorrectTextAndFillInput(nameInBilling(), "autotest");
        checkCorrectTextAndFillInput(strasseBilling(), "autotest");
        checkCorrectTextAndFillInput(paymentHouseBilling(), "autotest");
        fillingPostalCodeFieldJSForBilling(indexBilling);
        checkCorrectTextAndFillInput(ortBilling(), cityBilling);
        chooseDeliveryCountryForBilling(shopBilling);
        checkCorrectTextAndFillInput(telephonBilling(), "200+002");
        if (!fieldFirmBilling().isDisplayed()) {
            checkboxFirmBilling().click();
        }
        checkCorrectTextAndFillInput(fieldFirmBilling(), nameFirmBilling);
        return this;
    }

    @Step("Fill field tax number {taxNumber} for Billing. CartAddress_page")
    public CartAddress_page_Logic fillFieldIdCompanyBilling(String taxNumber) {
        checkCorrectTextAndFillInput(idCompanyBilling(), taxNumber);
        return this;
    }


    @Step("Checking correct text in input field. CartAddress_page")
    private void checkCorrectTextAndFillInput(SelenideElement element, String correctText) {
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
        nextButton().click();
        sleep(5000);
        return page(CartPayments_page_Logic.class);
    }

    @Step("Filling fiscal code field. CartAddress_page")
    public CartAddress_page_Logic fillInFiscalCode() {
        if (fiscalCodeField().isDisplayed()) {
            fiscalCodeField().clear();
            fiscalCodeField().setValue("1111");
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
        postalCodeFieldForShipping().clear();
        postalCodeFieldForShipping().click();
        checkCorrectTextAndFillInput(postalCodeFieldForShipping(), postalCodeOrCodeDefault);
        return this;
    }

    @Step("Filling postal code {sendPostalCode}. CartAddress_page")
    public CartAddress_page_Logic fillingPostalCodeField(String sendPostalCode) {
        postalCodeFieldForShipping().click();
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
        postalCodeFieldForShipping().waitUntil(appear, 10000);
        JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
        js.executeScript("arguments[0].value='" + sendPostalCode + "';", postalCodeFieldForShipping());
        return this;
    }

    @Step("Filling postal code {sendPostalCode} for billing. CartAddress_page")
    public CartAddress_page_Logic fillingPostalCodeFieldJSForBilling(String sendPostalCode) {
        postalCodeFieldForBilling().waitUntil(appear, 10000);
        JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
        js.executeScript("arguments[0].value='" + sendPostalCode + "';", postalCodeFieldForBilling());
        return this;
    }

    @Step("Choosing delivery country {country} for shipping. CartAddress_page")
    public CartAddress_page_Logic chooseDeliveryCountryForShipping(String country) {
        if (country.equals("EN")) country = "GB";
        if (country.equals("LD")) country = "LU";
        countryInSelectorForShipping(country).shouldBe(visible).click();
        return this;
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


    @Step("Click checkbox billing. CartAddress_page")
    public CartAddress_page_Logic clickCheckboxBilling() {
        if (!billingForm().isDisplayed()) {
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
        fixBtnInPopupAboutWrongCompany().click();
        return this;
    }

    @Step("Click button Continue in popup about wrong company. CartAddress_page")
    public CartPayments_page_Logic clickBtnContinueInPopupAboutWrongCompany() {
        continueBtnInPopupAboutWrongCompany().click();
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
                postalCodeFieldForShipping().click();
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
        Assert.assertEquals(plzPopupText, new DataBase().getTranslate("convir_translate", shop, "addres"), "Error plz:" + plz);
        return this;
    }

    @Step("Checks that the address fields are empty. CartAddress_page")
    public CartAddress_page_Logic checkThatAddressFieldsAreEmpty() {
        vorname().shouldHave(attribute("value", ""));
        nameIn().shouldHave(attribute("value", ""));
        strasse().shouldHave(attribute("value", ""));
        deliveryHouse().shouldHave(attribute("value", ""));
        postalCodeFieldForShipping().shouldHave(attribute("value", ""));
        ort().shouldHave(attribute("value", ""));
        telephon().shouldHave(attribute("value", ""));
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
        return this;
    }

    @Step("Check correct text {expectedText} in error message for personal number. CartAddress_page")
    public CartAddress_page_Logic checkCorrectTextInErrorMessage(String expectedText) {
        errorMessageForPersonalNumber().shouldBe(visible);
        errorMessageForPersonalNumber().shouldHave(text(expectedText));
        return this;
    }

    @Step("Get zip mask and compares with expected {expectedMask}. CartAddress_page")
    public CartAddress_page_Logic getZipMasksAndComparesWithExpected(String expectedMask) {
        String zipMask = postalCodeFieldForShipping().getAttribute("placeholder");
        Assert.assertEquals(zipMask, expectedMask);
        return this;
    }

    @Step("Get the previously entered zip code and compares with expected {expectedZipCode}. CartAddress_page")
    public CartAddress_page_Logic getZipCodeAndComparesWithExpected(String expectedZipCode) {
        String zipCode = postalCodeFieldForShipping().getAttribute("value");
        Assert.assertEquals(zipCode, expectedZipCode);
        return this;
    }

}
