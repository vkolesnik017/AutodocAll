package PKW;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;

public class Cart_page_Logic extends Cart_page{

    @Step("Clicking next button. Cart_page")
    public CartAccount_page_Logic nextButtonClick() {
        nextButton().click();
        return page(CartAccount_page_Logic.class);
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

    @Step("Check that the basket is empty. Cart_page")
    public Cart_page_Logic checkEmptyCart() {
        emptyCart().shouldBe(visible);
        return this;
    }
}
