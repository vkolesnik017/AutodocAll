package Ersatz;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.page;

public class Main_page_Logic extends Main_page {

    @Step("Cart clicking. Main_page")
    public Cart_page_Logic cartClick() {
        cartIcon().click();
        return page(Cart_page_Logic.class);
    }
}
