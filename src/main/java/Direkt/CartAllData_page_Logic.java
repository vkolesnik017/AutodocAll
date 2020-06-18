package Direkt;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.page;

public class CartAllData_page_Logic extends CartAllData_page {

    @Step("Next button click. CartAllData_page")
    public Payment_handler_page_Logic nextBtnClick() {
        nextBtn().click();
        return page(Payment_handler_page_Logic.class);
    }


}
