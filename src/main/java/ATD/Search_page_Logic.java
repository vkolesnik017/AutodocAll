package ATD;

import io.qameta.allure.Step;


public class Search_page_Logic extends Search_page {


    @Step("Click on the button (Tell me when the product appears). Search_page")
    public Search_page_Logic clickButtonProductById (String idProduct){
        buttonProductById(idProduct).click();
        return this;
    }

    @Step ("Subscriptions for product that are not in stock. Search_page")
    public Search_page_Logic sendRequestByGrayButtonFromSearchPage (String email){
        emailFieldInPopUpOfGrayBtn().setValue(email);
        checkboxInPopUpOfGrayBtn().click();
        sendButtonInPopUpOfGrayBtn().click();
        closeSuccessPopUpOfGrayBtn().click();
        return this;
    }


}

