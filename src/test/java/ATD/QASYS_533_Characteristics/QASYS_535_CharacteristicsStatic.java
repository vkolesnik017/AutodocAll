package ATD.QASYS_533_Characteristics;

import ATD.*;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static ATD.CommonMethods.getShopFromRoute;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class QASYS_535_CharacteristicsStatic {

  private String articleProduct = "Artikelnummer: 82B0691";
  private String articleProductForSearchListing = "82B0691";

  private Product_page productPage = new Product_page();
  private Listing_page listingPage = new Listing_page();
  private CommonMethods commonMethods = new CommonMethods();
  private DataBase db = new DataBase();

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
  @Description(value = "Test-1. Checks availability characteristics on product page")
  public void testAvailabilityCharacteristicsOnProductPage(String route) {
    ArrayList<String> expectedCharacteristics = new ArrayList<>();
    expectedCharacteristics.add("Einbauseite:\\nHinterachse");
    expectedCharacteristics.add("Durchmesser \\[mm]:\\n230");
    expectedCharacteristics.add("Bremsscheibenart:\\nVoll");
    expectedCharacteristics.add("Zentrierungsdurchmesser \\[mm]:\\n65,0");
    expectedCharacteristics.add("Bohrbild/Lochzahl:\\n05/06");
    expectedCharacteristics.add("Lochkreis-Ø \\[mm]:\\n100,0");
    expectedCharacteristics.add("Bremsscheibendicke \\[mm]:\\n9,0");

    ElementsCollection actualCharacteristics = productPage.openProductPageById(route, "7998901")
            .getCharacteristicsOfProduct();
   commonMethods.compareCharacteristics(actualCharacteristics, expectedCharacteristics);
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-2. Checks availability characteristics on tecdoc listing")
  public void testAvailabilityCharacteristicsOnTecDocListing(String route) throws SQLException {

    ArrayList<String> expectedCharacteristics = new ArrayList<>();
    expectedCharacteristics.add("");
    expectedCharacteristics.add("Antriebsart:\\nAllrad permanent");
    expectedCharacteristics.add("für PR-Nummer:\\n1KE");
    expectedCharacteristics.add("Einbauseite:\\nHinterachse");
    expectedCharacteristics.add("Durchmesser \\[mm]:\\n239");
    expectedCharacteristics.add("Bremsscheibenart:\\nVoll");
    expectedCharacteristics.add("Zentrierungsdurchmesser \\[mm]:\\n65");
    expectedCharacteristics.add("Lochanzahl:\\n5");
    expectedCharacteristics.add("Felge Lochzahl:\\n5");
    expectedCharacteristics.add("Bohrung-Ø 2 \\[mm]:\\n6,6");
    expectedCharacteristics.add("Bohrung-Ø 1 \\[mm]:\\n15,75");
    expectedCharacteristics.add("Lochkreis-Ø \\[mm]:\\n100");
    expectedCharacteristics.add("Bremsscheibendicke \\[mm]:\\n8,9");
    expectedCharacteristics.add("Mindestdicke \\[mm]:\\n7");
    expectedCharacteristics.add("Innendurchmesser \\[mm]:\\n132,5");
    expectedCharacteristics.add("Höhe \\[mm]:\\n33,4");
    expectedCharacteristics.add("Bohrung-Ø \\[mm]:\\n15,8");

    open(route + "/" + db.getRouteByRouteName(getShopFromRoute(route), "category_car_list"));
    ElementsCollection actualCharacteristics = listingPage.getCharacteristicsDesiredProduct(articleProduct);
    commonMethods.compareCharacteristics(actualCharacteristics, expectedCharacteristics);
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-3. Checks availability characteristics on search listing")
  public void testAvailabilityCharacteristicsOnSearchListing(String route) throws SQLException {
    ArrayList<String> expectedCharacteristics = new ArrayList<>();
    expectedCharacteristics.add("");
    expectedCharacteristics.add("Antriebsart:\\nAllrad permanent");
    expectedCharacteristics.add("für PR-Nummer:\\n1KE");
    expectedCharacteristics.add("Einbauseite:\\nHinterachse");
    expectedCharacteristics.add("Durchmesser \\[mm]:\\n239");
    expectedCharacteristics.add("Bremsscheibenart:\\nVoll");
    expectedCharacteristics.add("Zentrierungsdurchmesser \\[mm]:\\n65");
    expectedCharacteristics.add("Lochanzahl:\\n5");
    expectedCharacteristics.add("Felge Lochzahl:\\n5");
    expectedCharacteristics.add("Bohrung-Ø 2 \\[mm]:\\n6,6");
    expectedCharacteristics.add("Bohrung-Ø 1 \\[mm]:\\n15,75");
    expectedCharacteristics.add("Lochkreis-Ø \\[mm]:\\n100");
    expectedCharacteristics.add("Bremsscheibendicke \\[mm]:\\n8,9");
    expectedCharacteristics.add("Mindestdicke \\[mm]:\\n7");
    expectedCharacteristics.add("Innendurchmesser \\[mm]:\\n132,5");
    expectedCharacteristics.add("Höhe \\[mm]:\\n33,4");
    expectedCharacteristics.add("Bohrung-Ø \\[mm]:\\n15,8");

    open(route + "/" + db.getRouteByRouteName(getShopFromRoute(route), "search10"));
    ElementsCollection actualCharacteristics = listingPage.getCharacteristicsDesiredProductForSearch(articleProductForSearchListing);
    commonMethods.compareCharacteristics(actualCharacteristics, expectedCharacteristics);
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-4. Checks availability characteristics on oen listing")
  public void testAvailabilityCharacteristicsOnOenListing(String route) throws SQLException {

    ArrayList<String> expectedCharacteristics = new ArrayList<>();
    expectedCharacteristics.add("Einbauseite:\\nHinterachse");
    expectedCharacteristics.add("Durchmesser \\[mm]:\\n239");
    expectedCharacteristics.add("Bremsscheibenart:\\nVoll");
    expectedCharacteristics.add("Zentrierungsdurchmesser \\[mm]:\\n65");
    expectedCharacteristics.add("Lochanzahl:\\n5");
    expectedCharacteristics.add("Felge Lochzahl:\\n5");
    expectedCharacteristics.add("Bohrung-Ø 2 \\[mm]:\\n6,6");
    expectedCharacteristics.add("Bohrung-Ø 1 \\[mm]:\\n15,75");
    expectedCharacteristics.add("Lochkreis-Ø \\[mm]:\\n100");
    expectedCharacteristics.add("Bremsscheibendicke \\[mm]:\\n8,9");
    expectedCharacteristics.add("Mindestdicke \\[mm]:\\n7");
    expectedCharacteristics.add("Innendurchmesser \\[mm]:\\n132,5");
    expectedCharacteristics.add("Höhe \\[mm]:\\n33,4");
    expectedCharacteristics.add("Bohrung-Ø \\[mm]:\\n15,8");

    open(route + "/" + db.getRouteByRouteName(getShopFromRoute(route), "category_oen4"));
    ElementsCollection actualCharacteristics = listingPage.getCharacteristicsDesiredProduct(articleProduct);
    commonMethods.compareCharacteristics(actualCharacteristics, expectedCharacteristics);
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-5. Checks availability characteristics in basket")
  public void testAvailabilityCharacteristicsInBasket(String route) {

    ArrayList<String> expectedCharacteristics = new ArrayList<>();
    expectedCharacteristics.add("Einbauseite:\\nHinterachse");
    expectedCharacteristics.add("Durchmesser \\[mm]:\\n239");
    expectedCharacteristics.add("Bremsscheibenart:\\nVoll");
    expectedCharacteristics.add("Zentrierungsdurchmesser \\[mm]:\\n65");
    expectedCharacteristics.add("Lochanzahl:\\n5");
    expectedCharacteristics.add("Felge Lochzahl:\\n5");
    expectedCharacteristics.add("Bohrung-Ø 2 \\[mm]:\\n6,6");
    expectedCharacteristics.add("Bohrung-Ø 1 \\[mm]:\\n15,75");
    expectedCharacteristics.add("Lochkreis-Ø \\[mm]:\\n100");
    expectedCharacteristics.add("Bremsscheibendicke \\[mm]:\\n8,9");
    expectedCharacteristics.add("Mindestdicke \\[mm]:\\n7");
    expectedCharacteristics.add("Innendurchmesser \\[mm]:\\n132,5");
    expectedCharacteristics.add("Höhe \\[mm]:\\n33,4");
    expectedCharacteristics.add("Bohrung-Ø \\[mm]:\\n15,8");

    ElementsCollection actualCharacteristics =
    productPage.openProductPageById(route, "7999323")
            .addProductToCart()
            .closePopupOtherCategoryIfYes()
            .cartClick()
            .getCharacteristicsOfProduct();
    commonMethods.compareCharacteristics(actualCharacteristics, expectedCharacteristics);
  }

  public void tearDown() {
    close();
  }
}
