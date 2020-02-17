package ATD.QASYS_5_Listings;

import ATD.Listing_page;
import ATD.Main_page;
import ATD.Product_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.assertEquals;

public class QASYS_6_ListingOEN {

  private Main_page mainPage = new Main_page();
  private Product_page_Logic product_page_logic = new Product_page_Logic();
  private Listing_page listingPage = new Listing_page();

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
  @Description(value = "Test-1. Going to root oe_number from search bar")
  public void testGoingToRootOe_numberFromSearchBar(String route) {
    open(route);
    mainPage.searchBar().setValue("1j0615124a");
    mainPage.tooltipToSearch().shouldBe(visible);
    $(byText("OEN 1J0615124A")).click();
    waitingWhileLinkBecomeExpected("https://www.autodoc.de/autoteile/oem/1j0615124a?search=OEN%201J0615124A");
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-2. Going to root oe_number from OEN block on product page")
  public void testGoingToRootOe_numberFromOenBlockOnProductPage(String route) {
    product_page_logic.openProductPageById(route, "2036775");
    closeCookiesFooterMessage();
    product_page_logic.linksInOenNumbersBlock().get(1).click();
    listingPage.oemNumberBlock().shouldBe(visible);
    assertEquals(getNameRouteFromJSVarInHTML(), "category_oen");
  }

  @DataProvider(name = "routeOen", parallel = true)
  Object[] dataProvider2() throws SQLException {
    return new SetUp().setUpShopWithSubroutes("test", "DE", "main", "category_oen");
  }

  @Test(dataProvider = "routeOen")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-3. Checks the presence of required elements on the OEN listing")
  public void testPresenceElementsInOenListing(String route) {
    open(route);
    listingPage.titleOnListing().shouldHave(text("Bremsscheibe OE - NUMMER 34116785670"));
    listingPage.oemNumberBlock().shouldBe(visible);
    listingPage.oemDescriptionBlock().shouldBe(visible);
    listingPage.oemAnalogBlock().shouldBe(visible);
  }

}
