package AWS;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;

public class CurrencyRatesPage_aws {

    public static String currencyRatesPageURL = "https://aws.autodoc.de/products/currency";

    private ElementsCollection exchangeRatesTable() {
        return $$x("//div[@class='col-md-6']");
    }


    @Step("Get the price of a certain currency. CurrencyRatesPage_aws")
    public float getPriceOfCurrency(String currency) {
        float price = 0.0f;
        for (int j = 0; j < exchangeRatesTable().size(); j++) {
            String allExchangeRates = exchangeRatesTable().get(j).getText();
            List<String> list = Arrays.asList(allExchangeRates.split("\n"));

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).contains(currency)) {
                    price = Float.parseFloat(list.get(i).replaceAll(currency, ""));
                }
            }
        }
        return price;
    }


    @Step("Exchange the amount at the desired rate")
    public float exchangeAmountAtDesiredRate(float sum, String currency) {
        float price = getPriceOfCurrency(currency);
        float result = sum * price;
        BigDecimal bigDecimal = new BigDecimal(result);
        BigDecimal roundResult = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_DOWN);
        return Float.valueOf(String.valueOf(roundResult));
    }
}
