package ATD.Listings.QC_458_ListingViewModes;

import ATD.Listing_page;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;

public class QC_461_CompareProductsBetweenListingViewModesOnOEN {

  private Listing_page listingPage = new Listing_page();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "route")
  Object[] dataProvider() throws SQLException {
    return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_oen2");
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Comparing products between listing modes and check presence elements for OEN listing")
  public void testCompareProductsBetweenListingViewModesOnOEN(String route) {
    openPage(route);
    listingPage.checksImportantElementsOnOenListing()
            .compareProductsOrderBetweenListModeAndTileMode()
            .checksImportantElementsOnOenListing();
  }

}
