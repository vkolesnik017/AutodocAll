package ATD.Listings.QC_458_ListingViewModes;

import Common.DataBase;
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

import static ATD.CommonMethods.getShopFromRoute;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class QC_464_CompareProductsBetweenListingViewModesOnACC {

  private DataBase db = new DataBase("ATD");
  private Listing_page_Logic listingPage = new Listing_page_Logic();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "route")
  Object[] dataProvider() {
    return new SetUp("ATD").setUpShop("prod", "DE");
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
  @AfterMethod
  private void close() {
    closeWebDriver();
  }
}
