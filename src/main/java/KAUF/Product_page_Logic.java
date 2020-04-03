package KAUF;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;

public class Product_page_Logic extends Product_page {

    @Step(":from Product_page")
    public Cart_page_Logic cartClick() {
        new Main_page_Logic().cartClick();
        return page(Cart_page_Logic.class);
    }

    @Step("Adding product to basket. Product_page")
    public Product_page_Logic addProductToCart() {
        buyButton().click();
        sleep(2000);
        return this;
    }
}
