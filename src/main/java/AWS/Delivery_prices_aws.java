package AWS;

import Common.DataBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

import static PKW.CommonMethods.getTextFromUnVisibleElement;
import static PKW.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.*;

public class Delivery_prices_aws {

    public static String delivery_prices_aws = "https://aws.autodoc.de/delivery-prices";

    private ElementsCollection countryWithCheckbox() {
        return $$x("//tr/td[7]//input[@checked='checked']/../../td[2]");
    }

    private SelenideElement tableWithCountries() {
        return $x("//*[@class='table table-hover nested-countries sticky-header-middle']");
    }

    private SelenideElement deliveryPrice(String country) {
        return $x("//table[contains(@class,'sticky-header-middle')]//tr//td[text()='" + country + "']/..//td//input[contains(@name,'delivery_cost')]");
    }

    private SelenideElement deliveryCurrency(String country) {
        return $x("//table[contains(@class,'sticky-header-middle')]//tr//td[text()='" + country + "']//..//td[4]");
    }

    private SelenideElement deliveryPriceOnlyForIslandAndRegions(String regions) {
        return $x("//tr//i[contains(text(),'" + regions + "')]/..//parent::tr//input[@class='form-control']");
    }

    @Step("Get delivery price for Island or region {region}. Delivery_prices_aws")
    public float getDeliveryPriceForIslandOrRegion(String region) {
        float deliveryPrice = Float.parseFloat(deliveryPriceOnlyForIslandAndRegions(region).getValue());
        return deliveryPrice;
    }

    @Step("Get delivery price for country {country}. Delivery_prices_aws")
    public float getDeliveryPriceForCountry(String country) {
        float deliveryPrice = Float.parseFloat(deliveryPrice(country).getValue());
        return deliveryPrice;
    }


    @Step("Get delivery price with translation countries {country}. Delivery_prices_aws")
    public float getDeliveryPriceWithTranslationCountries(String country) throws SQLException {
        CurrencyRatesPage_aws currencyRatesPage_aws = new CurrencyRatesPage_aws();
        String actualCountry = new DataBase("ATD").getAWSTranslationCountries(country);
        float deliveryPrice = Float.parseFloat(deliveryPrice(actualCountry).getValue());
        String actualCurrency = deliveryCurrency(actualCountry).getText();
        float actualPrice;
        if (!actualCurrency.equals("EUR")) {
            openPage(currencyRatesPage_aws.currencyRatesPageURL);
            float price = currencyRatesPage_aws.getPriceOfCurrency(actualCurrency);
            float result = deliveryPrice / price;
            BigDecimal bigDecimal = new BigDecimal(result);
            BigDecimal roundResult = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
            actualPrice = Float.valueOf(String.valueOf(roundResult));
        } else {
            actualPrice = deliveryPrice;
        }
        return actualPrice;
    }

    @Step("Get delivery price without translation countries {country}. Delivery_prices_aws")
    public float getDeliveryPriceWithoutTranslationCountries(String country) throws SQLException {
        CurrencyRatesPage_aws currencyRatesPage_aws = new CurrencyRatesPage_aws();
        float deliveryPrice = Float.parseFloat(deliveryPrice(country).getValue());
        String actualCurrency = deliveryCurrency(country).getText();
        float actualPrice;
        if (!actualCurrency.equals("EUR")) {
            openPage(currencyRatesPage_aws.currencyRatesPageURL);
            float price = currencyRatesPage_aws.getPriceOfCurrency(actualCurrency);
            float result = deliveryPrice / price;
            BigDecimal bigDecimal = new BigDecimal(result);
            BigDecimal roundResult = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
            actualPrice = Float.valueOf(String.valueOf(roundResult));
        } else {
            actualPrice = deliveryPrice;
        }
        return actualPrice;
    }


    @Step("Login and open Delivery price page in AWS. Delivery_prices_aws")
    public Delivery_prices_aws openAndLoginDeliveryPriceAwsPage() {
        open(delivery_prices_aws);
        new Login_aws().loginInAws();
        return this;
    }

    @Step("Get name country from delivery price page. Delivery_prices_aws")
    public ArrayList<String> countriesFromDeliveryPricesAwsPage() {
        ArrayList<String> awsCountry = new ArrayList<>();
        tableWithCountries().shouldBe(Condition.visible);
        for (int i = 0; i < countryWithCheckbox().size(); i++) {
            awsCountry.add(getTextFromUnVisibleElement(countryWithCheckbox().get(i)).replaceAll("(.+)(\\s\\~.+)(.+)", "$1"));
        }
        return awsCountry;
    }
}
