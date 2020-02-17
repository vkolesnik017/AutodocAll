package ATD.Selectors.QC_693_VerticalCarSelectors;

import ATD.Main_page_logic;
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

public class QC_720_AppearsTooltipChooseModelFirstTimeEnterSession {

  private Main_page_logic mainPageLogic = new Main_page_logic();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "routes", parallel = true)
  Object[] dataProvider() throws SQLException {
    return new SetUp().setUpShopWithSubroutes("prod", "DE", "main","category_maker_brand,category_maker,categories_maker,category_maker2");
  }

  @Test(dataProvider = "routes")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Appears tooltip the choose model for first time enter session")
  public void testAppearsTooltipChooseModelFirstTimeEnterSession(String route) {
    open(route);
    mainPageLogic.closeTooltipInCarSelector();
  }

  @AfterMethod
  private void tearDown() {
    close();
  }

}
