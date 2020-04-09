package TSP;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class CartShipping_page_Logic extends CartShipping_page {

    @Step(" :from. CartShipping_page")
    public CartPayments_page_Logic nextBtnClick() {
        new CartAddress_page_Logic().nextBtnClick();
        return page(CartPayments_page_Logic.class);
    }

    @Step("Choosing delivery country {country} for shipping. CartShipping_page")
    public CartShipping_page_Logic chooseDeliveryCountryForShipping(String country) {
        if (country.equals("EN")) {
            country = "GB";
        }
        countryInSelectorForShipping(country).shouldBe(visible).click();
        return this;
    }

    @Step("Filling postal code {sendPostalCode} for shipping. CartShipping_page")
    public CartShipping_page_Logic fillingPostalCodeFieldJSForShipping(String sendPostalCode) {
        postalCodeFieldForShipping().waitUntil(appear, 10000);
        JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
        js.executeScript("arguments[0].value='" + sendPostalCode + "';", postalCodeFieldForShipping());
        return this;
    }
}
