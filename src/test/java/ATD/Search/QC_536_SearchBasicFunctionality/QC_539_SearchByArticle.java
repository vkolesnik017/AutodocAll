package ATD.Search.QC_536_SearchBasicFunctionality;

import ATD.Listing_page;
import ATD.Main_page;
import ATD.Main_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Condition.have;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;

public class QC_539_SearchByArticle {

  private String articleForSearch = "20049";

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "route")
  Object[] dataProvider() {
    return new SetUp().setUpShop("prod", "DE");
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "The test verifies that at the listing have product with article 20046 after search")
  public void testSearchByArticle(String route) {
    open(route);
    new Main_page_Logic().useSearch(articleForSearch);
    new Listing_page().productTitleInListMode().filter(have(text(articleForSearch))).shouldHave(sizeNotEqual(0));
  }

}
