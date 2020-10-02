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

    SelenideElement idOfAddedProduct() {
        return $x("//div[@class='cart-page-listing']//tr[2]");
    }

    SelenideElement imageProduct() {
        return $x("//div[@class='image__product ']");
    }

    public SelenideElement deliveryAddressInfo() {
        return $x(" //*[@id='cart-page']/div[2]/div[1]/div[1]/div[2]");
    }

    public SelenideElement payersAddressInfo() {
        return $x("//*[@id='cart-page']/div[2]/div[1]/div[2]/div[2]");
    }

    SelenideElement btnOpenInfoOfProduct() {
        return $x("//div[@class='info__more-info']/a[1]");
    }

    SelenideElement infoOfProductBlock() {
        return $x("//div[@class='info__more-info']/ul");
    }

    public SelenideElement freeDeliveryIcon() {
        return $(byXpath("//div[@class='order-summary ']//span[@class='free_icon order-delivery']"));
    }

    SelenideElement labelVAT() {
        return $x("//*[@class='alldata-bottom']//*[contains(@class,'total')]//i");
    }

    SelenideElement fieldWithQuantityOfProducts() {
        return $(byCssSelector(".qty>input"));
    }

    SelenideElement counterValue(String idGood) {
        return $x("//tr[@data-article_id='" + idGood + "']//input[@class='item_qty qty_1']");
    }

    SelenideElement counterPlusBtn() {
        return $(byCssSelector(".plus"));
    }

    SelenideElement counterPlus(String idGoodFromBtnPlus) {
        return $x("//tr[@data-article_id='" + idGoodFromBtnPlus + "']//a[@class='ga-click plus changeQty']");
    }

    SelenideElement counterMinusBtn() {
        return $(byCssSelector(".minus"));
    }

    SelenideElement counterMinus(String idGoodFromBtnMinus) {
        return $x("//tr[@data-article_id='" + idGoodFromBtnMinus + "']//a[@class='ga-click minus changeQty']");
    }

    SelenideElement nextBtn() {
        return $(byCssSelector(".order-summary__button"));
    }

    public SelenideElement payPalBtn() {
        return $x("//div[@class='order-summary__button alldata-submit']");
    }

    SelenideElement returnToPageCartAddress() {
        return $x("//div[@class='cart-page-steps']//ul//li[2]//a");
    }

    SelenideElement returnToCartPage() {
        return $x("//li[@class='complete link first_step']/a");
    }

    // locator only for CH
    public SelenideElement vatPriceInHead() {
        return $(byXpath("//*[contains(@class,'top')]/div[4]/span[2]"));
    }

    public SelenideElement vatPriceInTotalOrder() {
        return $(byXpath("//*[@class='alldata-bottom']//div[6]/span[2]"));
    }

    SelenideElement percentageOfVat() {
        return $x("//div[@class='order-summary__row order-summary__row--total']//i");
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

    SelenideElement safeOrderInUpperBlockWithSummery(String priceSO) {
        return $x("(//div[@class='order-summary__row']/span[contains(text(),'" + priceSO + "')])[1]");
    }

    SelenideElement safeOrderCostFromHeavyLoadsProduct() {
        return $x("//div[@class='order-summary ']//div[5]//span[2]");
    }

    SelenideElement safeOrderFromOrderSummaryBlock() {
        return $x("//div[@class='order-summary ']//div[4]//span[2]");
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
        return $(By.xpath("//div[@class='cart-popup js-error-popup close-redirect']"));
    }

    SelenideElement closeBtnPopupOfEmptyBasket() {
        return $(By.xpath("//a[@class='color close_popup']"));
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

    SelenideElement deleteGoodFromAllDataPage(String idGood) {
        return $x("//tr[@data-article_id='" + idGood + "']//td[1]//a");
    }

    SelenideElement deleteGoodBtn() {
        return $x("//a[@class='delBtn']");
    }

    SelenideElement btnConfirmProductDelete() {
        return $x("//a[@class='submit']");
    }

    SelenideElement btnOpenUpperBlockWithSummary() {
        return $x("//div[@class='order-summary__open']/a");
    }

    SelenideElement btnApplyBonus() {
        return $x("//div[@class='activate-bonus']/a");
    }

    SelenideElement openDiscountBlock() {
        return $x("//div[@class='alldata-discount']/a");
    }

    SelenideElement fieldForInputDiscount() {
        return $x("//div[@class='alldata-discount__form']/input");
    }

    SelenideElement btnApplyDiscount() {
        return $x("//div[@class='alldata-discount__form']/a[@class='button']");
    }

    SelenideElement bonusCheckboxInOrderSummary() {
        return $x("//div[@class='order-summary ']//input[@class='checkbox-bonus']");
    }

    SelenideElement BtnConfirmApplyDiscount() {
        return $x("//div[@class='popup-content__buttons']/a");
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

    public SelenideElement americanExpressLabel() {
        return $x("//img[contains(@src,'ae.png')]");
    }

    public SelenideElement epsLabel() {
        return $x("//img[contains(@src,'epsbank.png')]");
    }

    public SelenideElement przelewy24abel() {
        return $x("//img[contains(@src,'przelewy24')]");
    }

    public SelenideElement multibancoLabel() {
        return $x("//img[contains(@src,'multibanco')]");
    }

    public SelenideElement masterCashLabel() {
        return $x("//img[contains(@src,'mistercash.png')]");
    }

    public SelenideElement klarnaLabel() {
        return $x("//img[contains(@src,'klarna')]");
    }

    public SelenideElement idealLabel() {
        return $x("//img[contains(@src,'be2bill_ideal')]");
    }

    public SelenideElement sofortLabel() {
        return $x("//img[contains(@src,'directbank.png')]");
    }

    public SelenideElement trustlyLabel() {
        return $x("//img[contains(@src,'trustly')]");
    }

    public SelenideElement preloader() {
        return $(By.cssSelector(".preloader_wrapper"));
    }

    public SelenideElement artNumOfProduct() {return $x("//div[@class='info__nr']");}
}
