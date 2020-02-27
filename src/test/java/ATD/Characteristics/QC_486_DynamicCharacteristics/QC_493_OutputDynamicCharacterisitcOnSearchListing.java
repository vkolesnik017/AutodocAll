package ATD.Characteristics.QC_486_DynamicCharacteristics;

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
import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.matchesText;

public class QC_493_OutputDynamicCharacterisitcOnSearchListing {

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
  @Description(value = "Checks output dynamic characteristic on search listing")
  public void testOutputDynamicCharacterisitcOnSearchListing(String route) throws SQLException {
    openPage(route + "/" + db.getRouteByRouteName(getShopFromRoute(route), "search9"));
    String articleProduct = "Artikelnummer: V99-75-0011";
    String desiredCharacteristicRegEx = "einzustellender Elektrodenabstand \\[mm]:\\n0,7";
    listingPage.getCharacteristicsDesiredProduct(articleProduct)
            .filter(matchesText(desiredCharacteristicRegEx)).shouldHaveSize(1);
  }

}