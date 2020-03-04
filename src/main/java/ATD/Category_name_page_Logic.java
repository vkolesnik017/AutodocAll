package ATD;

import io.qameta.allure.Step;

import static ATD.CommonMethods.waitWhileRouteBecomeExpected;

// Не создавался класс Category_name_page, так как в нём пока нет надобности

public class Category_name_page_Logic {

  @Step("Verify name route equals category_name. Category_name_page")
  public Category_name_page_Logic verifyNameRouteEqualsCategoryName() {
    waitWhileRouteBecomeExpected("category_name");
    return this;
  }

}
