package ATD;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.sql.SQLException;

import static ATD.CommonMethods.getCurrentShopFromJSVarInHTML;
import static com.codeborne.selenide.Condition.visible;
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
        chooseDeliveryCountry(shop);
        checkCorrectTextAndFillInput(telephon(), "200+002");
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
    public Main_page logoClick() {
        $(By.xpath("//div[@class='cart-page-head__logo']")).click();
        return page(Main_page.class);
    }

    @Step("Next button click. CartAddress_page")
    public CartPayments_page_Logic nextBtnClick() {
        nextButton().click();
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
        postalCodeField().clear();
        postalCodeField().click();
        checkCorrectTextAndFillInput(postalCodeField(), postalCodeOrCodeDefault);
        return this;
    }

    @Step("Filling postal code {sendPostalCode}. CartAddress_page")
    public CartAddress_page_Logic fillingPostalCodeField(String sendPostalCode) {
//        postalCodeField().click();
//        postalCodeField().clear();
//        sleep(2000);
        postalCodeField().click();
        char[] array = sendPostalCode.toCharArray();
        for (char anArray : array) {
            String send = String.valueOf(anArray);
            sleep(1000);
            getWebDriver().findElement(By.id("form_lPlz")).sendKeys(send);
        }
        return this;
    }

    public void checkingCOVID19Tooltip(String countryCheck, String[] shopPlz, String shop) throws SQLException {
        chooseDeliveryCountry(countryCheck);
        for (String plz : shopPlz) {
            checkingCOVID19TooltipTranslate((plz), shop);
            if (countryCheck.equals("IT")) {
                int beginIndex = Integer.parseInt(plz.substring(0, plz.indexOf("-")));
                int endIndex = Integer.parseInt(plz.substring(plz.indexOf("-") + 1));
                for (int i = beginIndex; i <= endIndex; i++) {
                    checkingCOVID19TooltipTranslate(String.valueOf(i), shop);
                }
            }
        }
    }

    private void checkingCOVID19TooltipTranslate(String plz, String shop) throws SQLException {
        fillingPostalCodeField(plz);
        nextBtnClick();
        String plzPopupText = getTextFromTooltipCOVID19();
        Assert.assertEquals(plzPopupText, new DataBase().getTranslate("convir_translate", shop, "addres"));
    }

    @Step("Choosing delivery country {country}. CartAddress_page")
    public CartAddress_page_Logic chooseDeliveryCountry(String country) {
        if (country.equals("EN")) {
            country = "GB";
        }
        countryInSelector(country).shouldBe(visible).click();
        return this;
    }

    @Step("Click checkbox billing. CartAddress_page")
    public CartAddress_page_Logic clickCheckboxBilling() {
        billingCheckbox().click();
        return this;
    }


    @Step("Checks presence popup with an error about the wrong company. CartAddress_page")
    public CartAddress_page_Logic checkPresencePopupErrorAboutWrongCompany() {
        popupErrorAboutWrongCompany().shouldBe(visible);
        return this;
    }

    @Step("Click button EinkaufFortsetzen from popup with an error about the wrong company. CartAddress_page")
    public CartPayments_page_Logic clickBtnEinkaufFortsetzenFromPopupErrorAboutWrongCompany() {
        btnEinkaufFortsetzenFromPopupErrorAboutWrongCompany().click();
        return page(CartPayments_page_Logic.class);
    }

    @Step("Get text from tooltip COVID-19. CartAddress_page")
    public String getTextFromTooltipCOVID19() {
        tooltipCOVID19().shouldBe(visible);
        String textFromSite = tooltipCOVID19().getText();
        return textFromSite;
    }
}
