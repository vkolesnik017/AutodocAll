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

    SelenideElement safeOrderBlock(){
        return $(byCssSelector(".bestelen-block__row"));
    }

    SelenideElement safeOrderCheckbox() {
        return $x("//input[@name='security_delivery']");
    }

    public SelenideElement priceOfSafeOrder() {
        return $(byCssSelector(".bestelen-block__col>label"));
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

    public SelenideElement idealLabel() {
        return $x("//img[contains(@src,'be2bill_ideal')]");
    }

    public SelenideElement multibancoLabel() {
        return $x("//img[contains(@src,'multibanco')]");
    }

    public SelenideElement postPayLabel() {
        return $x("//img[contains(@src,'postpay')]");
    }

    public SelenideElement cartaSiLabel() {
        return $x("//img[contains(@src,'cs')]");
    }

    public SelenideElement discoverLabel() {
        return $x("//img[contains(@src,'discover')]");
    }

    public SelenideElement americanExpressLabel() {
        return $x("//img[contains(@src,'cards/ae')]");
    }

    //locators for checking labels payments method bank
    SelenideElement firstLabelBank() {
        return $x("//img[contains(@src,'wire')]");
    }
    SelenideElement secondLabelBank() {
        return $x("//img[contains(@src,'bank')]");
    }
    SelenideElement thirdLabelBank() {
        return $x("//img[contains(@src,'Finance')]");
    }
    SelenideElement fourthLabelBank() {
        return $x("//img[contains(@src,'Bank')]");
    }
}
