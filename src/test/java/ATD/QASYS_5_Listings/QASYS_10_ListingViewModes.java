package ATD.QASYS_5_Listings;

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
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;

public class QASYS_10_ListingViewModes {

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
  @Description(value = "Test-1. Comparing products between listing modes and check presence elements for Tectdoc listing")
  public void testCompareProductsBetweenListingViewModesOnTecdoc(String route) throws SQLException {
    open(route + "/" + db.getRouteByRouteName(getShopFromRoute(route), "category_car_list"));
    listingPage.blockOfBySideFilters().shouldBe(visible);
    listingPage.brandFilterBlock().shouldBe(visible);
    listingPage.paginationFirstBlock().shouldBe(visible);
    listingPage.paginationSecondBlock().shouldBe(visible);
    listingPage.compareProductsOrderBetweenListModeAndTileMode();
    listingPage.blockOfBySideFilters().shouldBe(visible);
    listingPage.brandFilterBlock().shouldBe(visible);
    listingPage.paginationFirstBlock().shouldBe(visible);
    listingPage.paginationSecondBlock().shouldBe(visible);
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-2. Comparing products between listing modes and check presence elements for OEN listing")
  public void testCompareProductsBetweenListingViewModesOnOEN(String route) throws SQLException {
    open(route + "/" + db.getRouteByRouteName(getShopFromRoute(route), "category_oen2"));
    listingPage.brandFilterBlock().shouldBe(visible);
    listingPage.paginationFirstBlock().shouldBe(visible);
    listingPage.paginationSecondBlock().shouldBe(visible);
    listingPage.oemAnalogBlock().shouldBe(visible);
    listingPage.compareProductsOrderBetweenListModeAndTileMode();
    listingPage.brandFilterBlock().shouldBe(visible);
    listingPage.paginationFirstBlock().shouldBe(visible);
    listingPage.paginationSecondBlock().shouldBe(visible);
    listingPage.oemAnalogBlock().shouldBe(visible);
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-3. Comparing products between listing modes and check presence elements for search listing")
  public void testCompareProductsBetweenListingViewModesOnSearch(String route) throws SQLException {
    open(route + "/" + db.getRouteByRouteName(getShopFromRoute(route), "search4"));
    listingPage.blockOfBySideFilters().shouldBe(visible);
    listingPage.brandFilterBlock().shouldBe(visible);
    listingPage.paginationFirstBlock().shouldBe(visible);
    listingPage.compareProductsOrderBetweenListModeAndTileMode();
    listingPage.paginationFirstBlock().shouldBe(visible);
    listingPage.blockOfBySideFilters().shouldBe(visible);
    listingPage.brandFilterBlock().shouldBe(visible);
  }
}
