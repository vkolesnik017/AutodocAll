package ATD.Listings.QC_458_ListingViewModes;

import ATD.DataBase;
import ATD.Listing_page;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.getShopFromRoute;
import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.open;

public class QC_464_CompareProductsBetweenListingViewModesOnACC {

  private DataBase db = new DataBase();
  private Listing_page listingPage = new Listing_page();

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
  @Description(value = "Comparing products between listing modes and check presence elements for ACC listing")
  public void testCompareProductBetweenListingViewModesOnACC(String route) throws SQLException {
    openPage(route + "/" + db.getRouteByRouteName(getShopFromRoute(route), "maker_car_list3"));
    open(route + "/" + db.getRouteByRouteName(getShopFromRoute(route), "listing_instruments2"));
    listingPage.checksImportantElementsOnListing()
            .compareProductsOrderBetweenListModeAndTileMode()
            .checksImportantElementsOnListing();
  }

}