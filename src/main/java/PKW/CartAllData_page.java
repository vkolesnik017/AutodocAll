package PKW;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CartAllData_page {


    SelenideElement nextBtn() {
        return $(byCssSelector(".order-summary__button"));
    }

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

    public SelenideElement visaLabel() {
        return $x("//img[contains(@src,'visa.png')]");
    }

    public SelenideElement masterCardLabel() {
        return $x("//img[contains(@src,'mc.png')]");
    }

    public SelenideElement sofortLabel() {
        return $x("//img[contains(@src,'directbank.png')]");
    }

    public SelenideElement przelewy24abel() {
        return $x("//img[contains(@src,'przelewy24')]");
    }

    public SelenideElement klarnaLabel() {
        return $x("//img[contains(@src,'klarna')]");
    }

    public SelenideElement epsLabel() {
        return $x("//img[contains(@src,'epsbank.png')]");
    }

    public SelenideElement trustlyLabel() {
        return $x("//img[contains(@src,'trustly')]");
    }
}
