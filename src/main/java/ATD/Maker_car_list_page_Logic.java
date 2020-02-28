package ATD;

import io.qameta.allure.Step;

import static ATD.CommonMethods.waitWhileRouteBecomeExpected;
import static com.codeborne.selenide.Selenide.page;

public class Maker_car_list_page_Logic extends Maker_car_list_page {

  @Step("Verify name route equals maker_car_list")
  public Maker_car_list_page_Logic verifyNameRouteEqualsMakerCarList() {
    waitWhileRouteBecomeExpected("maker_car_list");
    return this;
  }

  @Step("Input text in search bar by catalog")
  public Maker_car_list_page_Logic inputTextInSearchBarByCatalog(String text) {
    new Categories_page_Logic().inputTextInSearchBarByCatalog(text);
    return this;
  }

  @Step("Click tooltip in search by catalog by exact text")
  public Category_car_list_page_Logic clickTooltipInSearchByCatalogByExactText(String exactTooltipText) {
    new Categories_page_Logic().clickTooltipInSearchByCatalogByExactText(exactTooltipText);
    return page(Category_car_list_page_Logic.class);
  }

  @Step("Click oil filter category link")
  public Category_car_list_page_Logic clickOilFilterCategoryLink() {
    new Categories_page_Logic().clickOilFilterCategoryLink();
    return page(Category_car_list_page_Logic.class);
  }
}
