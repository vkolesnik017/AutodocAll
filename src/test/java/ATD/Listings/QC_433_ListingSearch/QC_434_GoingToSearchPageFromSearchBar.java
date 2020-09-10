package ATD.Listings.QC_433_ListingSearch;

import ATD.Main_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_434_GoingToSearchPageFromSearchBar {

  private Main_page_Logic mainPageLogic = new Main_page_Logic();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "route", parallel = true)
  Object[] dataProvider() {
    return new SetUp("ATD").setUpShop("prod", "DE");
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Going to search page from search bar")
  public void testGoingToSearchPageFromSearchBar(String route) {
    openPage(route);
    mainPageLogic.inputTextInSearchBar("Bremscheiben")
            .clickTooltipInSearchByExactText("Bremscheiben")
            .verifyNameRouteEqualsSearch();
  }
  @AfterMethod
  private void close() {
    closeWebDriver();
  }
}
