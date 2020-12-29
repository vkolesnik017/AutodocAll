package ATD.Search.QC_570_SearchByCatalog;

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

public class QC_572_GoesToSubCategoryFromCatalogWithCar {

  private Maker_car_list_page_Logic makerCarListPage = new Maker_car_list_page_Logic();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0", false);
  }

  @DataProvider(name = "route")
  Object[] test1() throws SQLException {
    return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "maker_car_list6");
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Goes to subcategory from catalog with car")
  public void testGoesToSubCategoryFromCatalogWithCar(String route) {
    openPage(route);
    String valueForSearch = "Bremsbeläge";
    makerCarListPage.inputTextInSearchBarByCatalog(valueForSearch)
            .clickTooltipInSearchByCatalogByExactText(valueForSearch)
            .verifyNameRouteEqualsCategoryCarList();
  }
  @AfterMethod
  public void close() {
    closeWebDriver();
  }
}
