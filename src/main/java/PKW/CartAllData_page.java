package PKW;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CartAllData_page {


    public SelenideElement totalOrderPrice() {
        return $(byXpath("//*[@class='alldata-bottom']//*[contains(@class,'total')]/span[2]"));
    }

    SelenideElement labelVAT() {
        return $x("//*[@class='alldata-bottom']//*[contains(@class,'total')]//i");
    }

    public SelenideElement payPalLabel() {
        return $x("//img[contains(@src,'paypal.png')]");
    }

    public SelenideElement payPalBtn() {
        return $x("//div[@class='order-summary__button alldata-submit']");
    }
}
