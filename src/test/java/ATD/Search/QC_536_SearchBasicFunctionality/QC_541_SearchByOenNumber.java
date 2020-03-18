package ATD.Search.QC_536_SearchBasicFunctionality;

import ATD.*;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class QC_541_SearchByOenNumber {

  private String oenNumber = "zzmf18861";

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
  @Description(value = "The test verifies that at the listing have only products with OEN number ZZMF18861 after search by text ZZMF18861")
  public void testSearchByOenNumber(String route) {
    open(route);
    new Main_page_Logic().useSearch(oenNumber);
    new Listing_page_Logic().checksProductTitlesContainExpectedTextGoingAllPagination(oenNumber);
  }
  @AfterMethod
  private void tearDown() {
    close();
  }
}
