package ATD.Listings.QC_458_ListingSortingAndPrioritization;

import ATD.Listing_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_463 {

  private Listing_page_Logic listingPage = new Listing_page_Logic();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0", false);
  }

  @DataProvider(name = "route")
  Object[] dataProvider() throws SQLException {
    return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list6");
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Comparing products between listing modes and check presence elements for LKW listing")
  public void testCompareProductBetweenListingViewModesOnLKW(String route) {
    openPage(route);
    listingPage.checksImportantElementsOnListing()
            .compareProductsOrderBetweenListModeAndTileMode()
            .checksImportantElementsOnListing();
  }

  @AfterMethod
  private void close() {
    closeWebDriver();
  }

}
