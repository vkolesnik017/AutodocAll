package Direkt;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.page;

public class CartPayments_page_Logic extends CartPayments_page {


    @Step("Next button click. CartPayments_page")
    public CartAllData_page_Logic nextBtnClick() {
        nextBtn().scrollTo();
        nextBtn().click();
        return page(CartAllData_page_Logic.class);
    }

    @Step("Return to the address page. CartPayments_page")
    public CartAddress_page_Logic clickBtnReturnTheAddressPage() {
        btnReturnTheAddressPage().click();
        return page(CartAddress_page_Logic.class);
    }

    @Step("Choosing vorkasse. CartPayments_page")
    public CartPayments_page_Logic clickVorkasse() {
        vorkasse().click();
        return this;
    }

}
