package ATD.Listings.QC_423_ListingOEN;

import ATD.Main_page_logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.openPage;
import static ATD.CommonMethods.waitWhileRouteBecomeExpected;
import static ATD.SetUp.setUpBrowser;

public class QC_424_GoingToRouteCategoryOenFromSearch {

  private Main_page_logic mainPageLogic = new Main_page_logic();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "route", parallel = true)
  Object[] dataProvider() {
    return new SetUp().setUpShop("prod", "DE");
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Going to route category_oen from search bar")
  public void testGoingToRouteCategoryOenFromSearch(String route) {
    openPage(route);
    mainPageLogic.inputTextInSearchBar("1j0615124a")
            .clickTooltipInSearchByExactText("OEN 1J0615124A");
    waitWhileRouteBecomeExpected("category_oen");
  }

}
