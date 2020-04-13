package TSP;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.page;

public class CartPayments_page_Logic extends CartPayments_page {

    @Step("Return to the address page. CartPayments_page")
    public CartAddress_page_Logic clickBtnReturnTheAddressPage() {
        btnReturnTheAddressPage().click();
        return page(CartAddress_page_Logic.class);
    }
}
