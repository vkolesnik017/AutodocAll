package ATD.Catalog.QC_2626_TecdocCatalog_SidebarCatalog_TopCategories;

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
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_575 {

  private Maker_car_list_page_Logic makerCarListPage = new Maker_car_list_page_Logic();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0", false);
  }

  @DataProvider(name = "routeWithCar")
  Object[] withCar() throws SQLException {
    return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "maker_car_list6");
  }

  @Test(dataProvider = "routeWithCar")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Presence the heading for search of catalog with car")
  public void testPresenceTheHeadingForSearchOfCatalogWithCar(String route) {
    openPage(route);
    makerCarListPage.headingOfSearchByCatalog().shouldHave(exactText(
            "Suchen Sie im Autoteile-Katalog für VW 1.9 TDI 4motion 3B6"));
  }

  @DataProvider(name = "routeWithoutCar")
  Object[] withoutCar() throws SQLException {
    return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "categories");
  }

  @Test(dataProvider = "routeWithoutCar")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Presence the heading for search of catalog without car")
  public void testPresenceTheHeadingForSearchOfCatalogWithoutCar(String route) {
    openPage(route);
    makerCarListPage.headingOfSearchByCatalog().shouldHave(exactText(
            "Suchen Sie im Autoteile-Katalog"));
   }

  @AfterMethod
  public void close() {
    closeWebDriver();
  }
}
