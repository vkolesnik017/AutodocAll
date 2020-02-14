package ATD;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static ATD.CommonMethods.waitWhileRouteBecomeExpected;
import static com.codeborne.selenide.Selenide.$;

public class Catalog_page {

  @Step("Verify name route equals maker_car_list")
  public Catalog_page verifyNameRouteEqualsMakerCarList() {
    waitWhileRouteBecomeExpected("maker_car_list");
    return this;
  }

  public SelenideElement linkForCategoryOilFilter() {
    return $("[data-ga-action='10359']");
  }
}
