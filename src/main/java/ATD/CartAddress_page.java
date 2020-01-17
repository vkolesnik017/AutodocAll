package ATD;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static ATD.CommonMethods.getCurrentShopFromJSVarInHTML;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class CartAddress_page {

    private SelenideElement vorname() {
        return $(byId("form_lVorname"));
    }

    private SelenideElement nameIn() {
        return $(byId("form_lName"));
    }

    private SelenideElement strasse() {
        return $(byId("form_lStrasse"));
    }

    private SelenideElement deliveryHouse() {
        return $(byId("form_delivery_house"));
    }

    private SelenideElement ort() {
        return $(byId("form_lOrt"));
    }

    private SelenideElement telephon() {
        return $(byId("form_lTelefon"));
    }


    public SelenideElement currentCountryInSelector() {
        return $(byXpath("//*[@id='form_lLand']/option[@selected]"));
    }

    private SelenideElement postalCodeField() {
        return $(By.id("form_lPlz"));
    }

    private SelenideElement fiscalCodeField() {
        return $(byName("lFiscalCode"));
    }

    public SelenideElement nextButton() {
        return $(byCssSelector(".address-continue>a"));
    }

    private SelenideElement countryInSelector(String country) {
        return $(byXpath("//*[@name='lLand']//*[@data-code='" + country + "']"));
    }


    public CartAddress_page fillAllFields(String shop) {
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

    private void checkCorrectTextAndFillInput(SelenideElement element, String correctText) {
        if (!element.getValue().equals(correctText)) {
            element.clear();
            element.setValue(correctText);
        }
    }

    @Step
    public Main_page logoClick() {
        $(By.xpath("//div[@class='cart-page-head__logo']")).click();
        return page(Main_page.class);
    }

    @Step
    public CartPayments_page nextBtnClick() {
        nextButton().click();
        return page(CartPayments_page.class);
    }

    @Step
    public CartAddress_page fillInFiscalCode() {
        if (fiscalCodeField().isDisplayed()) {
            fiscalCodeField().clear();
            fiscalCodeField().setValue("1111");
        }
        return this;
    }

    @Step()
    public CartAddress_page fillInPostalCode(String postalCodeOrCodeDefault) {
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

        System.out.println(postalCodeOrCodeDefault);
        checkCorrectTextAndFillInput(postalCodeField(), postalCodeOrCodeDefault);
//        postalCodeField().clear();
//        postalCodeField().setValue(postalCodeOrCodeDefault);
        return this;
    }

    @Step
    public CartAddress_page chooseDeliveryCountry(String country) {
        if (country.equals("EN")) {
            country = "GB";
        }
        countryInSelector(country).shouldBe(visible).click();
        return this;
    }

}
