package ATD;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class Cart_page {

    SelenideElement nextButton() {
        return $(byCssSelector(".next-step"));
    }

    SelenideElement uncoverCharacteristics() {
        return $(".open");
    }

    public SelenideElement fieldWithQuantityOfProducts() {
        return $(byCssSelector(".qty>input"));
    }

    SelenideElement counterPlusBtn() {
        return $(byCssSelector(".plus"));
    }

    SelenideElement counterMinusBtn() {
        return $(byCssSelector(".minus"));
    }

    public SelenideElement freeDeliveryIcon() {
        return $(byCssSelector(".free_icon"));
    }

    public SelenideElement emptyCart() {
        return $(byCssSelector(".cart-page-listing__empty-cart"));
    }

    SelenideElement deleteGoodsBtn() {
        return $x("//a[@class='delBtn']");
    }

    SelenideElement confirmationDeleteGoodsBtn() {
        return $x("//div[@class='popup-content__buttons']//a[2]");
    }

    SelenideElement closeBtnPopupOfEmptyBasket() {
        return $x("//a[@class='color close_popup']");
    }

    // locators only for CH
    SelenideElement closeDeliveryLimitPopupForCH() {
        return $(byCssSelector(".delivery-limit-popup>a"));
    }

    SelenideElement nextBtnIsNotActiveForCH() {
        return $(byCssSelector(".noclicked"));
    }

    // locators of prices with Currencies
    public SelenideElement totalOrderPriceInHead() {
        return $(byCssSelector(".cart-page-head__price>b"));
    }

    public SelenideElement productPrice() {
        return $(byCssSelector(".price"));
    }

    public SelenideElement pfandPriceInProductBlock() {
        return $x("(//td[@class='price'])[2]");
    }

    public SelenideElement totalProductPrice() {
        return $(byCssSelector(".total-price"));
    }

    public SelenideElement priceOfAllProducts() {
        return $(byXpath("//*[@class='order-summary ']/div[2]/span[2]"));
    }

    public SelenideElement totalOrderPrice() {
        return $(byXpath("//*[@class='order-summary__row order-summary__row--total']/span[2]"));
    }

    public SelenideElement pfandPriceInTotalPriceBlock() {
        return $x("//span[contains(text(),'Pfand')]/following-sibling::span");
    }

    public SelenideElement discountBlock() {
        return $x("//*[@id='promo-footer']");
    }

    public SelenideElement priceWithoutDiscount() {
        return $(byXpath("//*[@id='promo-footer']//p/b[1]"));
    }

    public SelenideElement priceWithDiscount() {
        return $(byXpath("//*[@id='promo-footer']//p/b[2]"));
    }

    public SelenideElement discount() {
        return $(byXpath("//*[@id='promo-footer']//span/b"));
    }

    SelenideElement idOfAddedProduct(){return $x("//div[@class='cart-page-listing']//tr[2]");}

    SelenideElement popupDeliveryLimitCartPage() {
        return $x("//div[@class='delivery-limit-popup delivery_limit']");
    }

    SelenideElement popupCountryDeliveryLimitCartPage() {
        return $x("//div[@class='delivery-limit-popup country_delivery_limit']");
    }

    SelenideElement btnDeleteGoodsInDeliveryPopupCartPage(){
        return $x("//a[@class='color close_popup delete_items']");
    }

    SelenideElement btnContinueShoppingInDeliveryPopupCartPage(){
        return $x("//a[@class='color close_popup continue_shopping']");
    }

    SelenideElement closePopupDeliveryLimitCartPage() {
        return $x("//a[@class='close_popup close ']");
    }

    SelenideElement productsIDLocator (String idProducts) {
        return $x("//*[@data-article_id='" + idProducts + "']");
    }

    SelenideElement btnChangeAddressInDeliveryPopupCartPage(){
        return $x("//a[@class='color close_popup change_address']");
    }

    SelenideElement titleNameProduct() {
        return  $x("//div[@class='cart-page-listing']//h3[text()]");
    }

    SelenideElement articleNumber() { return $(".info__nr"); }

    SelenideElement bonusSticker() {
        return $(".bonus-sticker");
    }

    SelenideElement btnMoreInfoOfProduct(String numOfOrder) {return $x("//tr[@data-article_id='"+numOfOrder+"']//td[3]/div[2]/a");}

    ElementsCollection moreInfoBlock() {return $$x("//div[@class='info__more-info']/ul");}

    ElementsCollection characteristicListOfProduct(String numOfOrder) {return $$x("//tr[@data-article_id='"+numOfOrder+"']//div[@class='info__more-info']/ul/li/span[1]");}

    SelenideElement idAddedProduct() {
        return $x("//div[@class='cart-page-listing']//tr[@data-article_id]");
    }

    SelenideElement valueQuantityCounter() {
        return $x("//div[@class='qty changable']/input");
    }

    public SelenideElement payPalLabel() {
        return $x("//img[contains(@src,'paypal.png')]");
    }

    public SelenideElement visaLabel() {
        return $x("//img[contains(@src,'visa.png')]");
    }

    public SelenideElement masterCardLabel() {
        return $x("//img[contains(@src,'mc.png')]");
    }

    public SelenideElement epsLabel() {
        return $x("//img[contains(@src,'eps_at.png')]");
    }

    public SelenideElement masterCashLabel() {
        return $x("//img[contains(@src,'mistercash.png')]");
    }

    public SelenideElement klarnaLabel() {
        return $x("//img[contains(@src,'klarna.png')]");
    }

    public SelenideElement idealLabel() {
        return $x("//img[contains(@src,'idl')]");
    }

    public SelenideElement sofortLabel() {
        return $x("//img[contains(@src,'sofort')]");
    }

    SelenideElement listOfAddedProductsBlock() {return $x("//div[@class='cart-page-listing']");}

    ElementsCollection listOfAddedProducts() {return $$x("//div[@class='cart-page-listing']//tr");}
}
