package ATD;

import io.qameta.allure.Step;

import static ATD.CommonMethods.waitWhileRouteBecomeExpected;

// Не создавался класс Category_car_list_page, так как в нём пока нет надобности

public class Category_car_list_page_Logic {

  @Step("Verify name route equals category_car_list")
  public Category_car_list_page_Logic verifyNameRouteEqualsCategoryCarList() {
    waitWhileRouteBecomeExpected("category_car_list");
    return this;
  }

}
