package ATD.Characteristics.QC_526_RecoveryCharacteristics;

import ATD.Listing_page;
import ATD.Product_page_Logic;
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
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Selenide.close;

public class QC_528_PresenceRecoveryCharacteristicsOnListingsAndProductPage {

  private Listing_page listingPage = new Listing_page();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "test2", parallel = true)
  Object[] test2() throws SQLException {
    return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "search11,category_oen5,category_car_list13");
  }

  @Test(dataProvider = "test2")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Checks presence recovery characteristics on listings")
  public void testPresenceRecoveryCharacteristicsOnListings(String route) {
    openPage(route);
    listingPage.recoveryCharacteristics().shouldHave(sizeGreaterThanOrEqual(1));
  }

  @DataProvider(name = "route")
  Object[] dataProvider() {
    return new SetUp().setUpShop("prod", "DE");
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Checks presence recovery characteristics on product page")
  public void testtestPresenceRecoveryCharacteristicsOnProductPage(String route) {
    new Product_page_Logic().openProductPageById(route, "1099441")
            .uncoverCharacteristics()
            .getCharacteristicsOfProduct().filter(matchText("Zustand  \\nWiederaufbereitet")).shouldHaveSize(1);
  }

  @AfterMethod
  private void teatDown() {
    close();
  }
}
