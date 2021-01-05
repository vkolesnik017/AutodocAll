package ATD.Listings.QC_445_ListingTecDoc;

import ATD.Maker_car_list_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_446 {

  private Maker_car_list_page_Logic makerCarListPage = new Maker_car_list_page_Logic();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0", false);
  }

  @DataProvider(name = "route")
  Object[] dataProvider() throws SQLException {
    return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "maker_car_list3");
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Going to route TecDoc (category_car_list)")
  public void testGoingToRouteTecDoc(String route) {
    openPage(route);
    makerCarListPage.clickOilFilterCategoryLink()
            .verifyNameRouteEqualsCategoryCarList();
  }
  @AfterMethod
  private void close() {
    closeWebDriver();
  }
}
