package AWS;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.ArrayList;

import static PKW.CommonMethods.getTextFromUnVisibleElement;
import static com.codeborne.selenide.Selenide.*;

public class Delivery_prices_aws {

    public String delivery_prices_aws = "https://aws.autodoc.de/delivery-prices";

    private ElementsCollection countryWithCheckbox() {
        return $$x("//tr/td[7]//input[@checked='checked']/../../td[2]");
    }

    private SelenideElement tableWithCountries() {
        return $x("//*[@class='table table-hover nested-countries sticky-header-middle']");
    }

    private SelenideElement deliveryPrice(String country) {
        return $x("//table[contains(@class,'sticky-header-middle')]//tr//td[text()='" + country + "']/..//td//input[contains(@name,'delivery_cost')]");
    }


    @Step("Get delivery price. Delivery_prices_aws")
    public float getDeliveryPrice(String country) {
       return Float.parseFloat(deliveryPrice(country).getValue());
    }


    @Step("Login in AWS. Delivery_prices_aws")
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
