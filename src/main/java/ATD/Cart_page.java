package ATD;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class Cart_page {

    // locators for cart-page-head block
    SelenideElement mainLogo() {
        return $x("//div[@class='cart-page-head__logo']/a/img");
    }

    public SelenideElement totalOrderPriceInHead() {
        return $(byCssSelector(".cart-page-head__price>b"));
    }

    // Locators for cart-page-listing block
    public SelenideElement emptyCart() {
        return $(byCssSelector(".cart-page-listing__empty-cart"));
    }

    SelenideElement listOfAddedProductsBlock() {
        return $x("//div[@class='cart-page-listing']");
    }

    ElementsCollection collectionListOfAddedProducts() {
        return $$x("//div[@class='cart-page-listing']//tr");
    }

    SelenideElement deleteGoodsBtn() {
        return $x("//a[@class='delBtn']");
    }

    SelenideElement deleteDefinitelyGoodsBTN(String idProduct) {
        return $x("//tr[@data-article_id='" + idProduct + "']//a[@class='delBtn']");
    }

    ElementsCollection collectionBtnRemoveProduct() {
        return $$x("//a[@class='delBtn']");
    }

    SelenideElement confirmationDeleteGoodsBtn() {
        return $x("//div[@class='popup-content__buttons']//a[2]");
    }

    SelenideElement closeBtnPopupOfEmptyBasket() {
        return $x("//a[@class='color close_popup']");
    }

    SelenideElement idOfAddedProduct() {
        return $x("//div[@class='cart-page-listing']//tr[2]");
    }

    SelenideElement titleNameProduct() {
        return $x("//div[@class='cart-page-listing']//h3[text()]");
    }

    SelenideElement articleNumber() {
        return $(".info__nr");
    }

    ElementsCollection wishListLabelOfProduct() {
        return $$x("//div[@class='info__more-info']/span");
    }

    ElementsCollection labelAddProductToWishList() {
        return $$x("//span[@class='add-to-wishlist add-article']");
    }

    SelenideElement btnOkOfRemoveProductFromWishListPopUp() {
        return $x("//div[@class='popup-content__buttons']/a[2]");
    }

    SelenideElement btnCloseOfRemoveProductFromWishListPopUp() {
        return $x("//div[@class='popup-content__buttons']/a[1]");
    }

    public SelenideElement productPrice() {
        return $(byCssSelector(".price"));
    }

    public SelenideElement totalProductPrice() {
        return $(byCssSelector(".total-price"));
    }

    public SelenideElement pfandPriceInProductBlock() {
        return $x("(//td[@class='price'])[2]");
    }

    public SelenideElement pfandPriceInProductBlock(String idProduct) {
        return $x("//tr[@data-article_id='" + idProduct + "']//td[@class='price'][2]");
    }

    public SelenideElement vatFromTotalProductPrice(String idProduct) {
        return $x("//tr[@data-article_id='" + idProduct + "']//td[@class='total-price']");
    }

    SelenideElement productsIDLocator(String idProducts) {
        return $x("//*[@data-article_id='" + idProducts + "']");
    }

    SelenideElement idAddedProduct() {
        return $x("//div[@class='cart-page-listing']//tr[@data-article_id]");
    }

    SelenideElement uncoverCharacteristics() {
        return $(".open");
    }

    public SelenideElement fieldWithQuantityOfProducts() {
        return $(byCssSelector(".qty>input"));
    }

    SelenideElement valueQuantityCounter() {
        return $x("//div[@class='qty changable']/input");
    }

    SelenideElement counterPlusBtn() {
        return $(byCssSelector(".plus"));
    }

    SelenideElement counterMinusBtn() {
        return $(byCssSelector(".minus"));
    }

    SelenideElement btnMoreInfoOfProduct(String numOfOrder) {
        return $x("//tr[@data-article_id='" + numOfOrder + "']//td[3]/div[2]/a");
    }

    SelenideElement btnMoreInfoProduct() {
        return $x("//div[@class='info__more-info']//a[1]");
    }

    ElementsCollection collectionMoreInfoBlockInProduct() {
        return $$x("//div[@class='info__more-info']/ul");
    }

    SelenideElement moreInfoBlockInProduct() {
        return $x("//div[@class='info__more-info']/ul");
    }

    SelenideElement characteristicZustandInProduct() {
        return $x("//div[@class='info__more-info']//ul[@style='display: block;']//span[contains(text(),'Zustand')]");
    }

    ElementsCollection characteristicListOfProduct(String numOfOrder) {
        return $$x("//tr[@data-article_id='" + numOfOrder + "']//div[@class='info__more-info']/ul/li/span[1]");
    }

    // locators only for CH shop
    SelenideElement closeDeliveryLimitPopupForCH() {
        return $(byCssSelector(".delivery-limit-popup>a"));
    }

    SelenideElement nextBtnIsNotActiveForCH() {
        return $(byCssSelector(".noclicked"));
    }

    // Locators for bestelen-block (Safe order)
    public SelenideElement safeOrderBlock() {
        return $x("//div[@class='bestelen-block__row']");
    }

    public SelenideElement priceOfSafeOrder() {
        return $(byCssSelector(".bestelen-block__col>label"));
    }

    SelenideElement safeOrderCheckbox() {
        return $x("//input[@name='security_delivery']");
    }

    // Locators for order-summary block
    public SelenideElement orderSummeryBlock() {
        return $x("//div[@class='order-summary ']");
    }

    SelenideElement totalProductPriceInSummeryBlock() {
        return $x("//div[@class='order-summary__row']/span[@class='order-total-price']");
    }

    public SelenideElement priceOfAllProducts() {
        return $(byXpath("//*[@class='order-summary ']/div[2]/span[2]"));
    }

    public SelenideElement totalOrderPrice() {
        return $(byXpath("//*[@class='order-summary__row order-summary__row--total']/span[2]"));
    }

    SelenideElement safeOrderInSummeryBlock() {
        return $x("//span[@class='order-security-delivery']");
    }

    SelenideElement percentageOfVat() {
        return $x("//div[@class='order-summary__row order-summary__row--total']//i");
    }

    public SelenideElement freeDeliveryIcon() {
        return $(byCssSelector(".free_icon"));
    }

    SelenideElement nextButton() {
        return $(byCssSelector(".next-step"));
    }

    public SelenideElement pfandPriceInTotalPriceBlock() {
        return $x("//span[@class='order-money-back']");
    }

    // Locators for promo-footer block
    public SelenideElement discountBlock() {
        return $x("//*[@id='promo-footer']");
    }

    public SelenideElement priceWithoutDiscount() {
        return $(byXpath("//*[@id='promo-footer']//p/b[1]"));
    }

    public SelenideElement priceWithDiscount() {
        return $(byXpath("//*[@id='promo-footer']//p/b[2]"));
    }

    public SelenideElement sunDiscount() {
        return $(byXpath("//*[@id='promo-footer']//span/b"));
    }

    // Locators for alldata-bottom__col (Bonus) block
    SelenideElement bonusSticker() {
        return $(".bonus-sticker");
    }

    // Locators for cart-page-bottom-info__col block
    SelenideElement btnAddProductToWishList() {
        return $(byId("add-all-wishlist"));
    }

    SelenideElement successAddedProductToWishListPopUp() {
        return $x("//div[@class='popup--page popup-success-add-all-wishlist']");
    }

    SelenideElement btnCloseSuccessAddedProductToWishListPopUp() {
        return $x("//a[@class='color close_popup']");
    }

    SelenideElement removeProductFromWishListPopUp() {
        return $x("//div[@class='delete-popup-article-wishlist cart-popup']");
    }

    // Locators for footer-cart-page block
    public SelenideElement payPalLabel() {
        return $x("//img[contains(@src,'paypal.png')]");
    }

    public SelenideElement visaLabel() {
        return $x("//img[contains(@src,'visa')]");
    }

    public SelenideElement masterCardLabel() {
        return $x("//img[contains(@src,'new_cart_payments/mc')]");
    }

    public SelenideElement epsLabel() {
        return $x("//img[contains(@src,'eps_at.png')]");
    }

    public SelenideElement przelewy24Label() {
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
        return $x("//img[contains(@src,'idl')]");
    }

    public SelenideElement sofortLabel() {
        return $x("//img[contains(@src,'sofort')]");
    }

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

    // Locators for all popup in page
    SelenideElement popupDeliveryLimitCartPage() {
        return $x("//div[@class='delivery-limit-popup delivery_limit']");
    }

    SelenideElement popupCountryDeliveryLimitCartPage() {
        return $x("//div[@class='delivery-limit-popup country_delivery_limit']");
    }

    SelenideElement btnDeletedGoodsViaDeliveryPopup() {
        return $x("//a[@class='color close_popup delete_items']");
    }

    SelenideElement btnDeleteGoodsInDeliveryPopupCartPage() {
        return $x("//a[@class='color close_popup delete_items']");
    }

    SelenideElement btnContinueShoppingInDeliveryPopupCartPage() {
        return $x("//a[@class='color close_popup continue_shopping']");
    }

    SelenideElement closePopupDeliveryLimitCartPage() {
        return $x("//a[@class='close_popup close ']");
    }

    SelenideElement btnChangeAddressInDeliveryPopupCartPage() {
        return $x("//a[@class='color close_popup change_address']");
    }
}
