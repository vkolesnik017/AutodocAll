package ATD.Listings.QC_423_ListingOEN;

import ATD.Listing_page;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.close;

public class QC_426_PresenceElementsOnOenListing {

  private Listing_page listingPage = new Listing_page();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "routeOen", parallel = true)
  Object[] dataProvider2() throws SQLException {
    return new SetUp().setUpShopWithSubroutes("test", "DE", "main", "category_oen");
  }

  @Test(dataProvider = "routeOen")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Presence of required elements on OEN listing")
  public void testPresenceElementsOnOenListing(String route) {
    openPage(route);
    listingPage.titleOfListing().shouldHave(text("Bremsscheibe OE - NUMMER 34116785670"));
    listingPage.oemNumberBlock().shouldBe(visible);
    listingPage.oemDescriptionBlock().shouldBe(visible);
    listingPage.oemAnalogBlock().shouldBe(visible);
  }

  @AfterMethod
  private void tearDown() {
    close();
  }
}
