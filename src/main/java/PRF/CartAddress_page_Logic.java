package PRF;

import ATD.DataBase;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import java.io.IOException;
import java.sql.SQLException;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class CartAddress_page_Logic extends CartAddress_page {

    @Step("Next button click. CartAddress_page")
    public CartPayments_page_Logic nextBtnClick() {
        nextButton().scrollTo();
        nextButton().click();
        return page(CartPayments_page_Logic.class);
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

    //CONVID TEST
    @Step("Get text from tooltip COVID-19. CartAddress_page")
    public String getTextFromTooltipCOVID19() {
        errorMessageCOVID19().shouldBe(visible);
        return errorMessageCOVID19().getText();
    }

    private CartAddress_page_Logic parsingCOVID19PlzForIT(String plz, String shop) throws SQLException, IOException {
        int beginIndex = Integer.parseInt(plz.substring(0, plz.indexOf("-")));
        int endIndex = Integer.parseInt(plz.substring(plz.indexOf("-") + 1));
        for (int i = beginIndex; i <= endIndex; i++) {
            StringBuilder plzParce = new StringBuilder(String.valueOf(i));
            while (plzParce.length() < 5) {
                plzParce.insert(0, "0");
            }
//            checkingCOVID19TooltipTranslate(String.valueOf(plzParce), shop);
        }
        return this;
    }

    @Step("Checking COVID-19 tooltip translate for country {countryCheck} with PLZ {plz} on shop {shop}. CartAddress_page")
    public CartAddress_page_Logic checkingCOVID19TooltipTranslate(String countryCheck, String plz, String shop) throws SQLException{
        chooseDeliveryCountry(countryCheck);
        fillingPostalCodeFieldJS(plz);
        nextBtnClick();
        String plzPopupText = getTextFromTooltipCOVID19();
        Assert.assertEquals(plzPopupText, new DataBase().getTranslate("convir_translate", shop, "addres"), "Error plz:" + plz);
        return this;
    }
}
