package ATD.Selectors.QC_771_RegKbaSelector;

import Common.DataBase;
import ATD.Main_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.SQLException;
import static ATD.CommonMethods.getCurrentShopFromJSVarInHTML;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.*;

public class QC_772_RegKbaSelector {

  private Main_page_Logic mainPageLogic = new Main_page_Logic();
  private DataBase db = new DataBase("ATD");

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "routeAndKbaNumbers", parallel = true)
  Object[] dataProvider() {
    return new SetUp("ATD").setUpShop("prod", "FI, FR, IT, DK, SE, NO, NL, PT, CH, EN");
  }

  @Test(dataProvider = "routeAndKbaNumbers")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Checks redirect when kba number is entered, except DE")
  public void testRedirectWhenKbaIsEntered(String route) throws SQLException {
    openPage(route);
    refresh();
    String kba = db.getKba(getCurrentShopFromJSVarInHTML());
    mainPageLogic.fillNumberKba(kba)
            .clickKbaBtnAndClosePopupSelectorIfVisible(kba)
            .verifyNameRouteEqualsMakerCarList();
  }

  @DataProvider(name = "routeAndKbaNumberForDE")
  Object[] dataProvider2() {
    return new SetUp("ATD").setUpShop("prod", "DE");
  }

  @Test(dataProvider = "routeAndKbaNumberForDE")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Checks redirect when kba number is entered on DE")
  public void testRedirectWhenKbaIsEnteredForDE(String route) throws SQLException {
    openPage(route);
    refresh();
    String kba = db.getKba(getCurrentShopFromJSVarInHTML());
    mainPageLogic.fillNumberKba(kba.split(" ")[0], kba.split(" ")[1])
            .clickKbaBtn()
            .verifyNameRouteEqualsMakerCarList();
  }

  @AfterMethod
  public void close() {
    closeWebDriver();
  }
}
