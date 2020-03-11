package ATD;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static ATD.CommonMethods.getCurrentShopFromJSVarInHTML;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class CartAddress_page_Logic extends CartAddress_page{

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
    public CartPayments_page nextBtnClick() {
        nextButton().click();
        return page(CartPayments_page.class);
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

    @Step("Choosing delivery country {country}. CartAddress_page")
    public CartAddress_page_Logic chooseDeliveryCountry(String country) {
        if (country.equals("EN")) {
            country = "GB";
        }
        countryInSelector(country).shouldBe(visible).click();
        return this;
    }

}