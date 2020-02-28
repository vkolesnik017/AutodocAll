package ATD.Search.QC_536_SearchBasicFunctionality;

import ATD.Listing_page;
import ATD.Main_page;
import ATD.Main_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.open;

public class QC_537_SearchByBrand {

  private Listing_page listingPage = new Listing_page();

  private String brandName = "RIDEX";

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "route")
  Object[] dataProvider() throws SQLException {
    return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "maker_car_list4");
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "The test verifies that at the listing have only products of brand ridex after search by text RIDEX")
  public void testSearchByBrand(String route) {
    open(route);
    new Main_page_Logic().useSearch(brandName);
    listingPage.checksProductTitlesContainExpectedTextGoingAllPagination(brandName);
  }
}
