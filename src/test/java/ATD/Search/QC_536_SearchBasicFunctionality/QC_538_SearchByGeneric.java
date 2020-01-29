package ATD.Search.QC_536_SearchBasicFunctionality;

import ATD.Listing_page;
import ATD.Main_page;
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

public class QC_538_SearchByGeneric {

  private String genericName = "Stoßdämpfer";
  private String genericNameForCheck = "dämpfer";

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "route")
  Object[] dataProvider() throws SQLException {
    return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "maker_car_list5");
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "The test verifies that at the listing have only products of generic Stoßdämpfer after search by text Stoßdämpfer")
  public void testSearchByGeneric(String route) {
    open(route);
    new Main_page().useSearch(genericName);
    new Listing_page().checksProductTitlesContainExpectedTextGoingAllPagination(genericNameForCheck);
  }
}
