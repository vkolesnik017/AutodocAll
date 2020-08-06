package ATD.Listings.QC_477_ListingSoft404;

import ATD.ListingTecDocSoft404_page_Logic;
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
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_478_NoListProductsOnSoft404 {

  private ListingTecDocSoft404_page_Logic listingTecDocSoft404PageLogic = new ListingTecDocSoft404_page_Logic();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "route")
  Object[] dataProvider() throws SQLException {
    return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_car_list11");
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Checks that no list products on soft 404")
  public void testNoListProductsOnSoft404(String route) {
    openPage(route);
    new ListingTecDocSoft404_page_Logic().checkNoListProductsOn404();
  }

  @AfterMethod
  private void close() {
    closeWebDriver();
  }
}
