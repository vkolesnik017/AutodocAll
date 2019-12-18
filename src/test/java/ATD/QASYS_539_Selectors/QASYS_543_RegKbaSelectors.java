package ATD.QASYS_539_Selectors;

import ATD.DataBase;
import ATD.Main_page;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.getCurrentShopFromJSVarInHTML;
import static ATD.CommonMethods.getNameRouteFromJSVarInHTML;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.assertEquals;

public class QASYS_543_RegKbaSelectors {

  private Main_page mainPage = new Main_page();
  private DataBase db = new DataBase();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "routeAndKbaNumbers", parallel = true)
  Object[] dataProvider() {
    return new SetUp().setUpShop("prod", "FI, FR, IT, DK, SE, NO, NL, PT, CH");
  }

  @Test(dataProvider = "routeAndKbaNumbers")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Checks redirect when kba number is entered, except DE")
  public void testRedirectWhenKbaIsEntered(String route) throws SQLException {
    open(route);
    String kba = db.getKba(getCurrentShopFromJSVarInHTML());
    mainPage.fillNumberKba(kba)
            .clickKbaBtn()
            .catalogBlog().shouldBe(visible);
    assertEquals(getNameRouteFromJSVarInHTML(), "maker_car_list");
  }


  @DataProvider(name = "routeAndKbaNumberForDE")
  Object[] dataProvider2() {
    return new SetUp().setUpShop("prod", "DE");
  }

  @Test(dataProvider = "routeAndKbaNumberForDE")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Checks redirect when kba number is entered on DE")
  public void testRedirectWhenKbaIsEnteredForDE(String route) throws SQLException {
    open(route);
    String kba = db.getKba(getCurrentShopFromJSVarInHTML());
    mainPage.fillNumberKba(kba.split(" ")[0], kba.split(" ")[1])
            .clickKbaBtn()
            .catalogBlog().shouldBe(visible);
    assertEquals(getNameRouteFromJSVarInHTML(), "maker_car_list");
  }


}
