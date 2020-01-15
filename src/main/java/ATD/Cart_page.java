package ATD;


import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static ATD.CommonMethods.getPriceFromElement;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;

public class Cart_page {

    private SelenideElement nextButton() {
        return $(byCssSelector(".next-step"));
    }

    @Step
    public CartAccount_page nextButtonClick() {
        nextButton().click();
        return page(CartAccount_page.class);
    }

    public SelenideElement fieldWithQuantityOfProducts() {
        return $(byCssSelector(".qty>input"));
    }

    private SelenideElement counterPlusBtn() {
        return $(byCssSelector(".plus"));
    }

    private SelenideElement counterMinusBtn() {
        return $(byCssSelector(".minus"));
    }

    public SelenideElement freeDeliveryIcon() {
        return $(byCssSelector(".free_icon"));
    }

    public SelenideElement emptyCart() {
        return $(byCssSelector(".cart-page-listing__empty-cart"));
    }

    // locators only for CH
    private SelenideElement closeDeliveryLimitPopupForCH() {
        return $(byCssSelector(".delivery-limit-popup>a"));
    }

    private SelenideElement nextBtnIsNotActiveForCH() {
        return $(byCssSelector(".noclicked"));
    }

    // locators of prices with Currencies
    public SelenideElement totalOrderPriceInHead() {
        return $(byCssSelector(".cart-page-head__price>b"));
    }

    public SelenideElement productPrice() {
        return $(byCssSelector(".price"));
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

    public SelenideElement priceWithoutDiscount() {
        return $(byXpath("//*[@id='promo-footer']//p/b[1]"));
    }

    public SelenideElement priceWithDiscount() {
        return $(byXpath("//*[@id='promo-footer']//p/b[2]"));
    }

    public SelenideElement discount() {
        return $(byXpath("//*[@id='promo-footer']//span/b"));
    }

    @Step
    public Cart_page makePriceForMinimumOrderForCH() {
        if (closeDeliveryLimitPopupForCH().isDisplayed()) {
            closeDeliveryLimitPopupForCH().click();
            while (nextBtnIsNotActiveForCH().isDisplayed()) {
                counterPlusBtn().click();
                sleep(500);
            }
        }
        return this;
    }

    @Step
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

    @Step
    public Cart_page counterIncrease(String startValue) {
        new CommonMethods().checkingCounterIncrease(startValue, fieldWithQuantityOfProducts(), counterPlusBtn());
        return this;
    }

    @Step
    public Cart_page counterDecrease(String startValue) {
        new CommonMethods().checkingCounterDecrease(startValue, fieldWithQuantityOfProducts(), counterMinusBtn());
        return this;
    }

}
