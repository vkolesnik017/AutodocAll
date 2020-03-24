package ATD;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CartAllData_page {

    public SelenideElement searchProductByID(String idProduct) {
        return $(byCssSelector("[data-article_id='" + idProduct + "']"));
    }

    public SelenideElement addressInfo() {
        return $(byCssSelector(".info-user-cart__info"));
    }

    public SelenideElement freeDeliveryIcon() {
        return $(byXpath("//*[@class='alldata-bottom']//*[@class='free_icon']"));
    }

    SelenideElement fieldWithQuantityOfProducts() {
        return $(byCssSelector(".qty>input"));
    }

    SelenideElement counterPlusBtn() {
        return $(byCssSelector(".plus"));
    }

    SelenideElement counterMinusBtn() {
        return $(byCssSelector(".minus"));
    }

    SelenideElement nextBtn() {
        return $(byCssSelector(".order-summary__button"));
    }

    // locator only for CH
    public SelenideElement vatPriceInHead() {
        return $(byXpath("//*[contains(@class,'top')]/div[4]/span[2]"));
    }

    public SelenideElement vatPriceInTotalOrder() {
        return $(byXpath("//*[@class='alldata-bottom']//div[6]/span[2]"));
    }

    // locators of prices with Currencies

    public SelenideElement uncoverPriceInHead() {
        return $(".order-summary__open");
    }

    public SelenideElement totalOrderPriceInHead() {
        return $(byXpath("//*[contains(@class,'top')]/div[2]/span[2]"));
    }

    public SelenideElement pfandPriceInHead() {
        return $x("(//span[contains(text(),'Pfand')]/following-sibling::span)[1]");
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

    public SelenideElement pfandPriceInTotalPriceBlock() {
        return $x("(//span[contains(text(),'Pfand')]/following-sibling::span)[2]");
    }

    public SelenideElement priceOfAllProducts() {
        return $(byXpath("//*[@class='order-summary ']/div[2]/span[2]"));
    }

    public SelenideElement deliveryPrice() {
        return $(byXpath("//*[@class='order-summary ']/div[3]/span[2]"));
    }

    public SelenideElement totalOrderPrice() {
        return $(byXpath("//*[@class='alldata-bottom']//*[contains(@class,'total')]/span[2]"));
    }

    SelenideElement heavyLoadsShippingCost(){
        return $x("//div[@class='order-summary ']//div[4]//span[2]");
    }

    SelenideElement safeOrderCost() {
        return $x("//div[@class='order-summary ']//div[5]//span[2]");
    }

    public SelenideElement priceOfSafeOrder() {
        return $(byCssSelector(".bestelen-block__col>label"));
    }

    SelenideElement safeOrderBlock(){
        return $(byCssSelector(".bestelen-block__row"));
    }

    SelenideElement safeOrderCheckbox() {
        return $x("//input[@name='security_delivery']");
    }

    // locators of popup of dangerous product
    public SelenideElement popupOfDangerousProduct() {
        return $(byCssSelector(".delivery-limit-popup"));
    }

    public SelenideElement closePopupBtn() {
        return $(byCssSelector(".close_popup.close"));
    }

    public SelenideElement areaOutOfPopup() {
        return $(byId("overlay"));
    }

    public SelenideElement deleteProductBtnInPopup() {
        return $(byCssSelector(".delete_items"));
    }

    public SelenideElement changeAddressBtnInPopup() {
        return $(byCssSelector(".change_address"));
    }

    SelenideElement popupOfEmptyBasket() {
        return $(By.xpath("//div[@class='cart-popup close-redirect']"));
    }

    SelenideElement closeBtnPopupOfEmptyBasket() {
        return $(By.xpath("//div[@class='cart-popup close-redirect']//a[@class='color close_popup']"));
    }

    //locators for tyres
    SelenideElement tyresAreNotDeliveredToCountryPopup() {
        return $(".delivery_limit_tyres");
    }

    SelenideElement closeTyresNotDeliveredPopupButton() {
        return $("close_popup");
    }

    //Locators for popup Delivery Limit
    SelenideElement popupDeliveryLimitAllDataPage() {
        return $x("//div[@class='delivery-limit-popup new_cart_delivery_limit']");
    }

    SelenideElement btnDeleteGoodsInDeliveryPopupAllDataPage(){
        return $x("//a[@class='color close_popup delete_items']");
    }

    SelenideElement btnChangeAddressInDeliveryPopupAllDataPage(){
        return $x("//a[@class='color close_popup change_address']");
    }

    SelenideElement closePopupDeliveryLimitAllDataPage() {
        return $x("//a[@class='close_popup close ']");
    }

    SelenideElement popupCountryDeliveryLimitAllDataPage() {
        return $x("//div[@class='delivery-limit-popup country_delivery_limit']");
    }

    SelenideElement deleteGoodsFromAllDataPage(String idGoods) {
        return $x("//tr[@data-article_id='" + idGoods + "']//td[1]//a");
    }

    SelenideElement btnConfirmProductDelete() {
        return $x("//a[@class='submit']");
    }
}
