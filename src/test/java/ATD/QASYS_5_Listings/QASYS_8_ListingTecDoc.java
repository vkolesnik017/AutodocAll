package ATD.QASYS_5_Listings;

import ATD.*;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class QASYS_8_ListingTecDoc {

  private Main_page_logic mainPageLogic = new Main_page_logic();
  private Listing_page listingPage = new Listing_page();
  private DataBase db = new DataBase();
  private ListingTecDocSoft404_page tecDocSoft404Page = new ListingTecDocSoft404_page();

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
  @Description(value = "Test-1. Going to listing tecdoc")
  public void testGoingToListingTecDoc(String route) throws SQLException {
    open(route);
    String shop = getCurrentShopFromJSVarInHTML();
    String kba = db.getKba(shop);
    mainPageLogic.fillNumberKba(kba.split(" ")[0], kba.split(" ")[1])
            .clickKbaBtn()
            .linkForCategoryOilFilter().click();
    waitingWhileLinkBecomeExpected(route + "/" + db.getRouteByRouteName(shop, "category_car_list8"));
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-2. Checks the presence of required elements on tecdoc listing")
  public void testPresenceElementsInListingTecDoc(String route) throws SQLException {
    open(route + "/" + db.getRouteByRouteName(getShopFromRoute(route), "category_car_list8"));
    listingPage.titleOnListing().shouldHave(text("Aktuelle Angebote zu Ölfilter für VW Golf IV Schrägheck (1J1) 1.4 16V Benzin 75 PS"));
    listingPage.tecDocBlockOfLinkingCategories().shouldBe(visible);
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-3. Checks additional tecdoc listing")
  public void testAdditionalTecDocListing(String route) throws SQLException {
    String chosenCar = "FORD C-Max II (DXA/CB7, DXA/CEU) 1.0 EcoBoost";
    open(route + "/" + db.getRouteByRouteName(getShopFromRoute(route), "category_car_list10"));
    closeCookiesFooterMessage();
    listingPage.titleOfAdditionalListingForTecDoc().shouldHave(text(chosenCar));
    listingPage.checksThatProductsAtListingAreFitsForChosenCar(chosenCar);
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-4. Checks soft 404")
  public void testSoft404(String route) throws SQLException {
    open(route + "/" + db.getRouteByRouteName(getShopFromRoute(route), "category_car_list9"));
    tecDocSoft404Page.blockOfNoFindProduct().shouldBe(visible);
    tecDocSoft404Page.blockWithCategories().shouldBe(visible);
    tecDocSoft404Page.blockWithTopProducts().shouldBe(visible);
  }
}
