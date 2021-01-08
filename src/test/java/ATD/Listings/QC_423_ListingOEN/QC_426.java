package ATD.Listings.QC_423_ListingOEN;

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

public class QC_426 {

  private Listing_page_Logic listingPage = new Listing_page_Logic();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0", false);
  }

  @DataProvider(name = "routeOen", parallel = true)
  Object[] dataProvider2() throws SQLException {
    return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_oen");
  }

  @Test(dataProvider = "routeOen")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Presence of required elements on OEN listing")
  public void testPresenceElementsOnOenListing(String route) {
    openPage(route);
    new Listing_page_Logic().checkOENListingElements();
  }

  @AfterMethod
  private void close() {
    closeWebDriver();
  }
}
