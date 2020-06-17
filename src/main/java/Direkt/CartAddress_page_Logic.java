package Direkt;

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

import static Direkt.CommonMethods.checkingContainsUrl;
import static Direkt.CommonMethods.testTel;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class CartAddress_page_Logic extends CartAddress_page {

    @Step("Next button click. CartAddress_page")
    public CartPayments_page_Logic nextBtnClick() {
        nextButton().scrollTo();
        nextButton().click();
        return page(CartPayments_page_Logic.class);
    }

    //SHIPPING FORM
    @Step("Filling Shipping Tel. CartAddress_page")
    public CartAddress_page_Logic fillingShippingTel() {
        if (!shippingTelInput().getValue().equals(testTel)) shippingTelInput().setValue(testTel);
        return this;
    }

    @Step("Choosing delivery country {country} in shipping form. CartAddress_page")
    public CartAddress_page_Logic chooseShippingDeliveryCountry(String country) {
        if (country.equals("EN")) {
            country = "GB";
        }
        shippingCountryInSelector(country).shouldBe(visible).click();
        return this;
    }

    @Step("Filling Shipping form required fields. CartAddress_page")
    public CartAddress_page_Logic fillingShippingReqField(String country, String plz, String ort, Boolean firm) {
        checkCorrectTextAndFillInput(shippingSurnameInput(), "autotest");
        checkCorrectTextAndFillInput(shippingNameInput(), "autotest");
        if(firm){
            if(shippingFirmInput().is(visible)) {
                checkCorrectTextAndFillInput(shippingFirmInput(), "autotest");
            }else{
                shippingFirmCheckbox().click();
                checkCorrectTextAndFillInput(shippingFirmInput(), "autotest");
            }
        }else {
            if(shippingFirmInput().is(visible))shippingFirmCheckbox().click();
        }
        chooseShippingDeliveryCountry(country);
        checkCorrectTextAndFillInput(shippingPLZInput(), plz);
        checkCorrectTextAndFillInput(shippingOrtInput(), ort);
        checkCorrectTextAndFillInput(shippingStreetInput(), "autotest");
        checkCorrectTextAndFillInput(shippingHouseInput(), "autotest");
        fillingShippingTel();
        return this;
    }

    @Step("Checking same address checkbox is checked. CartAddress_page")
    public CartAddress_page_Logic checkingSameAddressCheckbox() {
        chekboxSameAddress().scrollTo();
        if (!chekboxSameAddress().is(checked)) chekboxSameAddress().click();
        return this;
    }


    //BILLING FORM
    @Step("Filling Billing Tel. CartAddress_page")
    public CartAddress_page_Logic fillingBillingTel() {
        if (!billingTelInput().getValue().equals(testTel)) billingTelInput().setValue(testTel);
        return this;
    }


    @Step("Filling postal code {sendPostalCode}. CartAddress_page")
    public CartAddress_page_Logic fillingPostalCodeField(String sendPostalCode) {
        postalCodeField().click();
        postalCodeField().clear();
        char[] array = sendPostalCode.toCharArray();
        for (char anArray : array) {
            String send = String.valueOf(anArray);
            sleep(1000);
            getWebDriver().findElement(By.id("form_rPlz")).sendKeys(send);
        }
        return this;
    }

    @Step("Filling postal code {sendPostalCode}. CartAddress_page")
    public CartAddress_page_Logic fillingPostalCodeFieldJS(String sendPostalCode) {
        postalCodeField().waitUntil(appear, 10000);
        JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
        js.executeScript("arguments[0].value='" + sendPostalCode + "';", postalCodeField());
        return this;
    }

    @Step("Choosing delivery country {country}. CartAddress_page")
    public CartAddress_page_Logic chooseDeliveryCountry(String country) {
        if (country.equals("EN")) {
            country = "GB";
        }
        countryInSelector(country).shouldBe(visible).click();
        return this;
    }


    @Step("Choosing delivery country {country} and Filling postal code {sendPostalCode} for shipping and billing. CartAddress_page")
    public CartAddress_page_Logic chooseDeliveryCountryAndFillingPostalCode(String countryBilling, String sendPostalCodeBilling, String countryShipping, String sendPostalCodeShipping) {
        CartShipping_page_Logic cartShipping_page_logic = new CartShipping_page_Logic();
        billingCheckBox().click();
        chooseDeliveryCountry(countryBilling);
        fillingPostalCodeFieldJS(sendPostalCodeBilling);
        nextButton().click();
        cartShipping_page_logic.chooseDeliveryCountryForShipping(countryShipping);
        cartShipping_page_logic.fillingPostalCodeFieldJSForShipping(sendPostalCodeShipping);
        cartShipping_page_logic.nextBtnClick();
        checkingContainsUrl("/basket/payments.html");
        new CartPayments_page_Logic().clickBtnReturnTheAddressPage();
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


    //CONVID TEST
    @Step("Get text from tooltip COVID-19. CartAddress_page")
    public String getTextFromTooltipCOVID19() {
        popupCOVID19().shouldBe(visible);
        return popupCOVID19().getText();
    }

    @Step("Close popup COVID19. CartAddress_page")
    public CartAddress_page closePopupCOVID19() {
        closeBtnPopupCOVID19().click();
        return this;
    }

    @Step("Checking of blocking plz {sendPostalCode} for country {country} with split billing and shipping. CartAddress_page")
    public CartAddress_page_Logic checkBlockingPLZForCountry(String countryBilling, String sendPostalCodeBilling, String countryShipping, String sendPostalCodeShipping) {
        CartShipping_page_Logic cartShipping_page_logic = new CartShipping_page_Logic();
        if (billingCheckBox().isSelected()) {
            billingCheckBox().click();
        }
        chooseDeliveryCountry(countryBilling);
        fillingPostalCodeFieldJS(sendPostalCodeBilling);
        nextButton().click();
        cartShipping_page_logic.chooseDeliveryCountryForShipping(countryShipping);
        cartShipping_page_logic.fillingPostalCodeFieldJSForShipping(sendPostalCodeShipping);
        cartShipping_page_logic.nextBtnClick();
        popupCOVID19().shouldBe(visible);
        closePopupCOVID19();
        back();
        return this;
    }

    @Step("Checking block plz for country {countryCheck} on skin {skin}. CartAddress_page")
    public CartAddress_page_Logic checkingCOVID19Block(String countryCheck, String[] shopPlz, String file, String skin) throws IOException {
        chooseDeliveryCountry(countryCheck);
        for (String plz : shopPlz) {
            List<String> plzForChek = new ArrayList<>();
            if (countryCheck.equals("IT")) plzForChek = parsingAndCheckCOVIDBlockPlzForIT(plz);
            if (countryCheck.equals("PT")) plzForChek = parsingAndCheckCOVIDBlockPlzForPT(plz);
            for (String plzForShop : plzForChek) {
                checkingAppearingCOVIDTooltip(countryCheck, plzForShop, file, skin);
            }
            checkingAppearingCOVIDTooltip(countryCheck, plz, file, skin);
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
        fillingPostalCodeFieldJS(plz);
        System.out.println(plz);
        nextBtnClick();
        try {
            popupCOVID19().waitUntil(appear, 10000);
            if (!popupCOVID19().getText().contains("COVID")) {
                sleep(2000);
                closePopupCOVID19();
                postalCodeField().click();
                nextBtnClick();
            }
            popupCOVID19().shouldHave(text("COVID"));
            closePopupCOVID19();
        } catch (ElementNotFound redirectOnPaymentsPage) {
            System.err.println(plz + " err");
            new CommonMethods().writerInFile(file, true, "Country check: " + countryCheck + " PLZ: " + plz + " On skin: " + skin);
            back();
        }
        return this;
    }

    @Step("Checking COVID-19 tooltip translate for country {countryCheck} with PLZ {plz} on shop {shop}. CartAddress_page")
    public CartAddress_page_Logic checkingCOVID19TooltipTranslate(String countryCheck, String plz, String shop) throws SQLException {
        chooseDeliveryCountry(countryCheck);
        fillingPostalCodeFieldJS(plz);
        nextBtnClick();
        String plzPopupText = getTextFromTooltipCOVID19();
        Assert.assertEquals(plzPopupText, new DataBase().getTranslate("convir_translate", shop, "addres"), "Error plz:" + plz);
        closePopupCOVID19();
        return this;
    }
}
