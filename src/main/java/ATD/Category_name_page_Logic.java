package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import static ATD.CommonMethods.waitWhileRouteBecomeExpected;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.WebDriverRunner.url;

// Не создавался класс Category_name_page, так как в нём пока нет надобности

public class Category_name_page_Logic extends Category_name_page {

  @Step("Verify name route equals category_name. Category_name_page")
  public Category_name_page_Logic verifyNameRouteEqualsCategoryName() {
    waitWhileRouteBecomeExpected("category_name");
    return this;
  }

  @Step("check successfully child category page loading. Category_name_page")
  public Category_name_page_Logic checkSuccessfullyChildCategoryLoadingFromMainPage() {
    imageOfChildCategory().shouldBe(visible);
    Assert.assertEquals(url(), "https://www.autodoc.de/autoteile/olfilter-10359");
    return this;
  }

}
