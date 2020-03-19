package ATD;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Cart_page_Logic extends Cart_page {

    @Step("Clicking next button. Cart_page")
    public CartAccount_page_Logic nextButtonClick() {
        nextButton().click();
        return page(CartAccount_page_Logic.class);
    }

    @Step(":in Cart_page")
    public Cart_page_Logic counterIncrease(String startValue) {
        new CommonMethods().checkingCounterIncrease(startValue, fieldWithQuantityOfProducts(), counterPlusBtn());
        return this;
    }

    @Step(":in Cart_page")
    public Cart_page_Logic counterDecrease(String startValue) {
        new CommonMethods().checkingCounterDecrease(startValue, fieldWithQuantityOfProducts(), counterMinusBtn());
        return this;
    }

    @Step("Click uncover characteristics for first product and get his characteristics. Cart_page")
    public ElementsCollection getCharacteristicsOfProduct() {
        uncoverCharacteristics().click();
        return $$(".info__description>li").shouldHave(sizeGreaterThan(10));
    }

    @Step("Make price for minimum order for CH. Cart_page")
    public Cart_page makePriceForMinimumOrderForCH() {
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
        String expectedCurrency = new DataBase().getCurrency(shop);
        getCurrencyAndVerify(totalOrderPriceInHead(), "orderPriceInHead", shop, expectedCurrency);
        getCurrencyAndVerify(priceOfAllProducts(), "priceOfAllProducts", shop, expectedCurrency);
        getCurrencyAndVerify(totalOrderPrice(), "totalOrderPrice", shop, expectedCurrency);
        getCurrencyAndVerify(productPrice(), "productPrice", shop, expectedCurrency);
        getCurrencyAndVerify(totalProductPrice(), "totalProductPrice", shop, expectedCurrency);
        return this;
    }

    @Step("Checks currency on cart page from discount block. CartPage")
    public Cart_page_Logic checkCurrencyOnCartPageFromDiscountBlock(String shop) throws SQLException {
        String expectedCurrency = new DataBase().getCurrency(shop);
        getCurrencyAndVerify(priceWithoutDiscount(), "priceWithoutDiscount", shop, expectedCurrency);
        getCurrencyAndVerify(priceWithDiscount(), "priceWithDiscount", shop, expectedCurrency);
        getCurrencyAndVerify(discount(), "discount", shop, expectedCurrency);
        return this;
    }

    @Step("Close popup delivery limit. CartPage")
    public Cart_page_Logic closePopUpDeliveryLimitCartPage() {
        popupDeliveryLimitCartPage().shouldBe(visible);
        closePopupDeliveryLimitCartPage().click();
        return this;
    }

    @Step("Deletes goods from cart when click on the (Artikel entfernen) button in the delivery pop-up. CartPage")
    public Cart_page_Logic deleteGoodsInDeliveryPopupCartPage(){
        popupDeliveryLimitCartPage().shouldBe(visible);
        btnDeleteGoodsInDeliveryPopupCartPage().click();
        return this;
    }

    @Step("Click the button (Einkauf fortsetzen) in the delivery pop-up. CartPage")
    public Cart_page_Logic clickBtnContinueShoppingInDeliveryPopupCartPage(){
        popupDeliveryLimitCartPage().shouldBe(visible);
        btnContinueShoppingInDeliveryPopupCartPage().click();
        return this;
    }

    @Step("Click the button (Adresse ändern) in the delivery pop-up. CartPage")
    public CartAddress_page_Logic clickBtnChangeAddressInDeliveryPopupCartPageCartPage() {
        btnChangeAddressInDeliveryPopupCartPage().click();
        return page(CartAddress_page_Logic.class);
    }


    @Step("Checks the absence of goods in cart page. CartPage")
    public Cart_page_Logic checkAbsenceGoodsInCartPage(String idProducts) {
        productsIDLocator(idProducts).shouldNotBe(visible);
        return this;
    }


    @Step("Checks the presence of goods in cart page. CartPage")
    public Cart_page_Logic checkPresenceGoodsInCardPage(String idProducts) {
        productsIDLocator(idProducts).shouldBe(visible);
        return this;
    }
}
