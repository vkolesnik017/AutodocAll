package ATD.Listings.QC_423_ListingOEN;

import ATD.Search_page_Logic;
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
import static com.codeborne.selenide.Selenide.close;

public class QC_439_PresenceAdditionalListingOnSearchPage {

  private Search_page_Logic searchPageLogic = new Search_page_Logic();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "route")
  Object[] dataProvider() throws SQLException {
    return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "search7");
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Checks presence additional listing on search listing")
  public void testPresenceAdditionalListingOnSearchPage(String route) {
    openPage(route);
    searchPageLogic.dividingLineForProductsOtherCategories().shouldHave(
            text("Ergebnisse für zuendkerzen für andere Fahrzeuge"));
  }

  @AfterMethod
  private void tearDown() {
    close();
  }

}
