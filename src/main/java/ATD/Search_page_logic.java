package ATD;

import io.qameta.allure.Step;

import static ATD.CommonMethods.waitWhileRouteBecomeExpected;

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


}
