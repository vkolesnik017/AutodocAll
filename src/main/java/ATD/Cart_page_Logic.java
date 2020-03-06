package ATD;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

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


}
