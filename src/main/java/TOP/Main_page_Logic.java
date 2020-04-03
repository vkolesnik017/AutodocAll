package TOP;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;

public class Main_page_Logic extends Main_page {

    @Step("Transition to cart. Main_page")
    public Cart_page transitionToClick() {
        cartIcon().hover();
        cartBtn().shouldBe(visible);
        cartBtn().click();
        return page(Cart_page.class);
    }
}
