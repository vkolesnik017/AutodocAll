package ATD;

import io.qameta.allure.Step;

import static ATD.CommonMethods.waitWhileRouteBecomeExpected;

import io.qameta.allure.Step;


public class Search_page_Logic extends Search_page {

  @Step("Verify name route equals search")
  public Search_page_Logic verifyNameRouteEqualsSearch() {
    waitWhileRouteBecomeExpected("search");
    return this;
  }

  @Step("Close popup of choose car")
  public Search_page_Logic closePopupOfChooseCar() {
    closeBtnPopupOfChooseCar().click();
    return this;
  }

  @Step("Switch on second page")
  public Search_page_Logic switchOnSecondPage() {
    secondListingPage().scrollTo();
    closePopupOfChooseCar().secondListingPage().click();
    return this;
  }


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

