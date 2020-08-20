package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;

public class Tyre_item_page_Logic extends Tyre_item_page {

    @Step("presence of horizontal selector .Tyre_item_page")
    public Tyre_item_page_Logic presenceOfHorizontalSelector() {
        horizontalSelector().shouldBe(visible);
        return this;
    }

    @Step("presence of feedBack pop-up by lack of product .Tyre_item_page")
    public Tyre_item_page_Logic presenceOfFeedBackPopUpByLackOfProduct() {
        grayButtonOfProduct().shouldBe(visible).click();
        popUpAboutLackOfProduct().shouldBe(visible);
        return this;
    }
}
