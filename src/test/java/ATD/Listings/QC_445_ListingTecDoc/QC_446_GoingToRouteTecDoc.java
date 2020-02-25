package ATD.Listings.QC_445_ListingTecDoc;

import ATD.Catalog_page;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.CommonMethods.waitWhileRouteBecomeExpected;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.open;

public class QC_446_GoingToRouteTecDoc {

  private Catalog_page catalogPage = new Catalog_page();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "route")
  Object[] dataProvider() throws SQLException {
    return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "maker_car_list3");
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Going to route TecDoc (category_car_list)")
  public void testGoingToRouteTecDoc(String route) {
    openPage(route);
    catalogPage.linkForCategoryOilFilter().click();
    waitWhileRouteBecomeExpected("category_car_list");
  }

}
