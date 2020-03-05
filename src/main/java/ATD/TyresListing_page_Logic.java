package ATD;


import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.page;

public class TyresListing_page_Logic extends TyresListing_page {

    @Step("Adds first product to basket from tyres listing and goes to basket. TyresListing_page")
    public Cart_page_Logic addFirstProductAndGoToCart() {
        new Search_page_Logic().addFirstProductAndGoToCart();
        return page(Cart_page_Logic.class);
    }

    @Step("Adds first product to basket from listing. TyresListing_page")
    public Cart_page_Logic addFirstProductToCart() {
        buyButton().click();
        return page(Cart_page_Logic.class);
    }

    @Step("Gets tyre id. TyresListing_page")
    public String getTyreId() {
        return buyButton().attr("data-id");
    }
}
