package ATD.Characteristics.QC_506_StaticCharacteristics;

import ATD.*;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static ATD.CommonMethods.getShopFromRoute;
import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;

public class QC_509_PresenceStaticCharacteristicsOnTecDocListing {

  private DataBase db = new DataBase();
  private Listing_page_Logic listingPage = new Listing_page_Logic();
  private CommonMethods commonMethods = new CommonMethods();

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
  @Description(value = "Checks presence static characteristics on TecDoc listing")
  public void testPresenceStaticCharacteristicsOnTecDocListing(String route) throws SQLException {

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

    openPage(route + "/" + db.getRouteByRouteName(getShopFromRoute(route), "category_car_list"));
    String articleProduct = "Artikelnummer: 82B0691";
    ElementsCollection actualCharacteristics = listingPage.getCharacteristicsDesiredProduct(articleProduct);
    commonMethods.compareCharacteristics(actualCharacteristics, expectedCharacteristics);
  }


}
