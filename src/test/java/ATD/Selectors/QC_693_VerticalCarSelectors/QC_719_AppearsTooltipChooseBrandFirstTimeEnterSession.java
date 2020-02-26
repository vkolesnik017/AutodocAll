package ATD.Selectors.QC_693_VerticalCarSelectors;

import ATD.Main_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class QC_719_AppearsTooltipChooseBrandFirstTimeEnterSession {

  private Main_page_Logic mainPageLogic = new Main_page_Logic();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "routes", parallel = true)
  Object[] dataProvider() throws SQLException {
    return new SetUp().setUpShopWithSubroutes("prod", "DE", "main","main,category_name,category_name_brand");
  }

  @Test(dataProvider = "routes")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Appears tooltip the choose brand for first time enter session")
  public void testAppearsTooltipChooseBrandFirstTimeEnterSession(String route) {
    open(route);
    mainPageLogic.closeTooltipInCarSelector();
  }

  @AfterMethod
  private void tearDown() {
    close();
  }

}
