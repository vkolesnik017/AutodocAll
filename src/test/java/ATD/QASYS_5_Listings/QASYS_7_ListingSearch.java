package ATD.QASYS_5_Listings;

import ATD.*;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.getCurrentShopFromJSVarInHTML;
import static ATD.CommonMethods.getShopFromRoute;
import static ATD.CommonMethods.waitingWhileLinkBecomeExpected;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class QASYS_7_ListingSearch {

  private Main_page mainPage = new Main_page();
  private Listing_page listingPage = new Listing_page();
  private Search_page searchPage = new Search_page();
  private DataBase db = new DataBase();

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
  @Description(value = "Test-1. Going to search listing from search bar")
  public void testGoingToSearchListingFromSearchBar(String route) throws SQLException {
    open(route);
    String generic = "Bremscheiben";
    mainPage.searchBar().setValue(generic);
    mainPage.hintsForSearch().shouldBe(visible);
    $(byText(generic)).click();
    listingPage.titleOnListing().shouldBe(visible);
    waitingWhileLinkBecomeExpected(route + "/" + db.getRouteByRouteName(getCurrentShopFromJSVarInHTML(), "search6"));
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-2. Checks the presence of required elements on search listing")
  public void testPresenceElementsInSearchListing(String route) throws SQLException {
    open(route + "/" + db.getRouteByRouteName(getShopFromRoute(route), "search6"));
    listingPage.titleOnListing().shouldBe(visible);
    searchPage.blockOfHelpSearchProducts().shouldBe(visible);
    searchPage.blockOfLinkingCategory().shouldBe(visible);
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-3. Checks use switching of page on search listing")
  public void testUseSwitchingOfPageOnSearchListing(String route) throws SQLException {
    String url = route + "/" + db.getRouteByRouteName(getShopFromRoute(route), "search6");
    open(url);
    listingPage.secondListingPage().scrollTo();
    listingPage.closeBtnPopupOfChooseCar().click();
    listingPage.secondListingPage().click();
    waitingWhileLinkBecomeExpected(url.concat("&page=2"));
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-4. Checks presence additional listing on search listing")
  public void testPresenceAdditionalListingInSearchListing(String route) throws SQLException {
    open(route + "/" + db.getRouteByRouteName(getShopFromRoute(route), "search7"));
    listingPage.titleOnListing().shouldBe(visible);
    listingPage.productsForOtherCars().shouldBe(visible).shouldHave(text("zuendkerzen"));
  }
}
