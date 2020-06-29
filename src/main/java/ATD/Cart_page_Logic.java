package ATD;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import java.sql.SQLException;

import static ATD.CommonMethods.getCurrencyAndVerify;
import static ATD.CommonMethods.getPriceFromElement;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Cart_page_Logic extends Cart_page {

    @Step("Clicking next button. Cart_page")
    public CartAccount_page_Logic nextButtonClick() {
        nextButton().click();
        return page(CartAccount_page_Logic.class);
    }

    @Step("Clicking next button. Cart_page")
    public CartAddress_page_Logic clickBtnNextAndTransitionOnAddressPage() {
        nextButton().click();
        return page(CartAddress_page_Logic.class);
    }

    @Step(":in Cart_page")
    public Cart_page_Logic counterIncreaseForPaired(String startValue) {
        new CommonMethods().checkingCounterIncreaseForPaired(startValue, fieldWithQuantityOfProducts(), counterPlusBtn());
        return this;
    }

    @Step(":in Cart_page")
    public Cart_page_Logic counterDecreaseForPaired(String startValue) {
        new CommonMethods().checkingCounterDecreaseForPaired(startValue, fieldWithQuantityOfProducts(), counterMinusBtn());
        return this;
    }

    @Step("Click uncover characteristics for first product and get his characteristics. Cart_page")
    public ElementsCollection getCharacteristicsOfProduct() {
        uncoverCharacteristics().click();
        return $$(".info__description>li").shouldHave(sizeGreaterThan(4));
    }

    @Step("Make price for minimum order for CH. Cart_page")
    public Cart_page makePriceForMinimumOrderForCH(String shop) {
        if(shop.equals("CH")) {
            if (!closeDeliveryLimitPopupForCH().isDisplayed()) {
                sleep(2000);
            }
            if (closeDeliveryLimitPopupForCH().isDisplayed()) {
                closeDeliveryLimitPopupForCH().click();
                while (nextBtnIsNotActiveForCH().isDisplayed()) {
                    counterPlusBtn().click();
                    sleep(500);
                }
            }
        }
        return this;
    }

    @Step("Make and check limit price for free delivery. Cart_page")
    public Cart_page makeAndCheckLimitPriceForFreeDelivery(float deliveryLimit) {
        // An increase in the quantity of products for checking the limit of free delivery
        float totalPrice = getPriceFromElement(totalProductPrice());
        while (!freeDeliveryIcon().isDisplayed() && totalPrice < deliveryLimit) {
            String beforeClickPrice = totalProductPrice().text();
            sleep(1000);
            counterPlusBtn().click();
            totalProductPrice().shouldHave(not(text(beforeClickPrice)));
            totalPrice = getPriceFromElement(totalProductPrice());
            if (totalPrice < deliveryLimit) {
                freeDeliveryIcon().shouldBe(not(visible));
            } else if (totalPrice > deliveryLimit) {
                freeDeliveryIcon().shouldBe(visible);
                break;
            }
        }
        return this;
    }

    @Step("Checks currency on cart page. Cart_page")
    public Cart_page_Logic checkCurrencyOnCartPage(String shop) throws SQLException {
        makePriceForMinimumOrderForCH(shop);
        String expectedCurrency = new DataBase().getCurrency(shop);
        getCurrencyAndVerify(totalOrderPriceInHead(), "orderPriceInHead", shop, expectedCurrency);
        getCurrencyAndVerify(priceOfAllProducts(), "priceOfAllProducts", shop, expectedCurrency);
        getCurrencyAndVerify(totalOrderPrice(), "totalOrderPrice", shop, expectedCurrency);
        getCurrencyAndVerify(productPrice(), "productPrice", shop, expectedCurrency);
        getCurrencyAndVerify(totalProductPrice(), "totalProductPrice", shop, expectedCurrency);
        return this;
    }

    @Step("Checks currency on cart page from discount block. Cart_page")
    public Cart_page_Logic checkCurrencyOnCartPageFromDiscountBlock(String shop) throws SQLException {
        if (discountBlock().isDisplayed()) {
            String expectedCurrency = new DataBase().getCurrency(shop);
            getCurrencyAndVerify(priceWithoutDiscount(), "priceWithoutDiscount", shop, expectedCurrency);
            getCurrencyAndVerify(priceWithDiscount(), "priceWithDiscount", shop, expectedCurrency);
            getCurrencyAndVerify(discount(), "discount", shop, expectedCurrency);
        }
            return this;

    }

    @Step("Check of id {idOfProduct} added product to basket. Cart_page")
    public Cart_page_Logic checkOfIdAddedProductInBasket(String idOfProduct) {
        idOfAddedProduct().shouldHave(attribute("data-article_id", idOfProduct));
        return this;
    }

    @Step("Checks the presence of pop up delivery limit. Cart_page")
    public Cart_page_Logic checkPresencePopUpDeliveryLimit() {
        popupDeliveryLimitCartPage().shouldBe(visible);
        return this;
    }

    @Step("Close popup delivery limit. Cart_page")
    public Cart_page_Logic closePopUpDeliveryLimitCartPage() {
        closePopupDeliveryLimitCartPage().click();
        sleep(2000);
        return this;
    }

    @Step("Deletes goods from cart when click on the (Artikel entfernen) button in the delivery pop-up. Cart_page")
    public Cart_page_Logic deleteGoodsInDeliveryPopupCartPage(){
        btnDeleteGoodsInDeliveryPopupCartPage().click();
        return this;
    }

    @Step("Click the button (Einkauf fortsetzen) in the delivery pop-up. Cart_page")
    public Cart_page_Logic clickBtnContinueShoppingInDeliveryPopupCartPage(){
        btnContinueShoppingInDeliveryPopupCartPage().click();
        return this;
    }

    @Step("Click the button (Adresse ändern) in the delivery pop-up. Cart_page")
    public CartAddress_page_Logic clickBtnChangeAddressInDeliveryPopupCartPageCartPage() {
        btnChangeAddressInDeliveryPopupCartPage().click();
        return page(CartAddress_page_Logic.class);
    }

    //The method is used when delivering goods to FR
    @Step("Checks for absence btn (Einkauf fortsetzen) in the delivery pop-up. Cart_page")
    public Cart_page_Logic checkAbsenceBtnContinueShoppingInDeliveryPopupCartPage() {
        popupCountryDeliveryLimitCartPage().shouldBe(visible);
        btnContinueShoppingInDeliveryPopupCartPage().shouldNotBe(visible);
        return this;
    }

    @Step("Checks the absence of good by id {idProducts} in cart page. Cart_page")
    public Cart_page_Logic checkAbsenceGoodInCartPage(String idProducts) {
        productsIDLocator(idProducts).shouldNotBe(visible);
        return this;
    }


    @Step("Checks the presence of good by id {idProducts} in cart page. Cart_page")
    public Cart_page_Logic checkPresenceGoodInCardPage(String idProducts) {
        productsIDLocator(idProducts).shouldBe(visible);
        return this;
    }

    @Step("Delete goods from cart page. Cart_page")
    public Main_page_Logic deleteGoodFromCartPage() {
        deleteGoodsBtn().click();
        confirmationDeleteGoodsBtn().click();
        closeBtnPopupOfEmptyBasket().click();
        return page(Main_page_Logic.class);
    }

    @Step("Check that the basket is empty. Cart_page")
    public Cart_page_Logic checkEmptyCart() {
        emptyCart().shouldBe(visible);
        return this;
    }

    @Step("Get name title product. Cart_page")
    public String getNameTitleProduct() {
      return titleNameProduct().getText();
    }

    @Step("Checks presence bonus sticker. Cart_page")
    public Cart_page_Logic checkPresenceBonusSticker() {
        bonusSticker().shouldBe(visible);
        return this;
    }

    @Step("Check article number of product. Cart_page")
    public Cart_page_Logic checkArticleNumberOfProduct(String productArticle) {
        articleNumber().shouldHave(text(productArticle));
        return this;
    }

    @Step("Checks absence bonus sticker. Cart_page")
    public Cart_page_Logic checkAbsenceBonusSticker() {
        bonusSticker().shouldNotBe(visible);
        return this;
    }
}
