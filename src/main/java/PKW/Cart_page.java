package PKW;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.*;

public class Cart_page {

    SelenideElement nextButton() {
        return $(byCssSelector(".next-step"));
    }

    SelenideElement counterPlusBtn() {
        return $(byCssSelector(".plus"));
    }

    SelenideElement nameProduct() {
        return $x("//td[@class='info']/h3");
    }

    SelenideElement valueQuantityCounter() {
        return $x("//div[@class='qty changable']/input");
    }

    SelenideElement idAddedProduct() {
        return $x("//div[@class='cart-page-listing']//tr[@data-article_id]");
    }

    SelenideElement safeOrderCheckbox() {
        return $x("//input[@name='security_delivery']");
    }

    // locators only for CH
    SelenideElement closeDeliveryLimitPopupForCH() {
        return $x("//a[@class='close_popup close continue_shopping']");
    }

    SelenideElement nextBtnIsNotActiveForCH() {
        return $(byCssSelector(".noclicked"));
    }

    SelenideElement emptyCart() {
        return $x("//div[@class='cart-page-listing__empty-cart']");
    }

    SelenideElement addedProductList() {return $x("//div[@class='cart-page-listing']");}

    SelenideElement volumeOfAddedProduct() {return $x("//td[@class='info']//span[@class='desc']");}

    SelenideElement btnMoreInfoOfProduct() {return $x("//a[@class='open']");}

    ElementsCollection titleOfCharacteristicInInfoBlockOfProduct() {return $$x("//ul[@class='info__description']/li/span[1]"); }

    ElementsCollection valueOfCharacteristicInInfoBlockOfProduct() {return $$x("//ul[@class='info__description']/li/span[2]"); }

    public SelenideElement payPalLabel() {
        return $x("//img[contains(@src,'paypal.png')]");
    }

    public SelenideElement visaLabel() {
        return $x("//img[contains(@src,'visa')]");
    }

    public SelenideElement masterCardLabel() {
        return $x("//img[contains(@src,'new_cart_payments/mc')]");
    }

    public SelenideElement sofortLabel() {
        return $x("//img[contains(@src,'sofort')]");
    }

    public SelenideElement przelewy24Label() {
        return $x("//img[contains(@src,'przelewy24')]");
    }

    public SelenideElement klarnaLabel() {
        return $x("//img[contains(@src,'klarna')]");
    }

    public SelenideElement epsLabel() {
        return $x("//img[contains(@src,'eps_at.png')]");
    }

    public SelenideElement idealLabel() {
        return $x("//img[contains(@src,'idl')]");
    }

    public SelenideElement multibancoLabel() {
        return $x("//img[contains(@src,'multibanco')]");
    }

    public SelenideElement cartaSiLabel() {
        return $x("//img[contains(@src,'carta_si')]");
    }

    public SelenideElement postePayLabel() {
        return $x("//img[contains(@src,'poste_pay')]");
    }

    public SelenideElement discoverLabel() {
        return $x("//img[contains(@src,'discover')]");
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
