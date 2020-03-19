package ATD;


import com.codeborne.selenide.SelenideElement;

import java.nio.file.attribute.UserPrincipalLookupService;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

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

    public SelenideElement priceWithoutDiscount() {
        return $(byXpath("//*[@id='promo-footer']//p/b[1]"));
    }

    public SelenideElement priceWithDiscount() {
        return $(byXpath("//*[@id='promo-footer']//p/b[2]"));
    }

    public SelenideElement discount() {
        return $(byXpath("//*[@id='promo-footer']//span/b"));
    }

    SelenideElement popupDeliveryLimit() {
        return $x("//div[@class='delivery-limit-popup delivery_limit']");
    }

    SelenideElement btnDeleteGoodsInDeliveryPopup(){
        return $x("//a[@class='color close_popup delete_items']");
    }

    SelenideElement btnContinueShoppingInDeliveryPopup(){
        return $x("//a[@class='color close_popup continue_shopping']");
    }

    SelenideElement closePopupDeliveryLimit() {
        return $x("//a[@class='close_popup close ']");
    }

    SelenideElement productsIDLocator (String idProducts) {
        return $x("//*[@data-article_id='" + idProducts + "']");
    }
}
