package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.page;

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
}
