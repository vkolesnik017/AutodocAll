package ATD.Selectors.QC_771_RegKbaSelector;

import ATD.DataBase;
import ATD.Main_page;
import ATD.Main_page_logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.assertEquals;

public class QC_772_RegKbaSelector {

  //  private Main_page mainPage = new Main_page();
  private Main_page_logic mainPageLogic = new Main_page_logic();
  private DataBase db = new DataBase();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "routeAndKbaNumbers", parallel = false)
  Object[] dataProvider() {
    return new SetUp().setUpShop("prod", "FI, FR, IT, DK, SE, NO, NL, PT, CH");
  }

  @Test(dataProvider = "routeAndKbaNumbers")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Checks redirect when kba number is entered, except DE")
  public void testRedirectWhenKbaIsEntered(String route) throws SQLException {
    openPage(route);
    String kba = db.getKba(getCurrentShopFromJSVarInHTML());
    mainPageLogic.fillNumberKba(kba)
            .clickKbaBtn().catalogBlog().shouldBe(visible);
    getNameRouteAndVerifyWithExpected("maker_car_list");
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
    openPage(route);
    String kba = db.getKba(getCurrentShopFromJSVarInHTML());
    mainPageLogic.fillNumberKba(kba.split(" ")[0], kba.split(" ")[1])
            .clickKbaBtn().catalogBlog().shouldBe(visible);
    getNameRouteAndVerifyWithExpected("maker_car_list");
  }
}
