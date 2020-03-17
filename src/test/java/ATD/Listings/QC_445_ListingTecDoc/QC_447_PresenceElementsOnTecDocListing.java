package ATD.Listings.QC_445_ListingTecDoc;

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

public class QC_447_PresenceElementsOnTecDocListing {

  private Listing_page listingPage = new Listing_page();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "route")
  Object[] dataProvider() throws SQLException {
    return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_car_list8");
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Checks presence of required elements on TecDoc listing")
  public void testPresenceElementsOnTecDocListing(String route) {
    openPage(route);
    listingPage.titleOfListing().shouldHave(
            text("Aktuelle Angebote zu Ölfilter für VW Golf IV Schrägheck (1J1) 1.4 16V Benzin 75 PS"));
    listingPage.tecDocBlockOfLinkingCategories().shouldBe(visible);
  }
  @AfterMethod
  private void tearDown() {
    close();
  }
}
