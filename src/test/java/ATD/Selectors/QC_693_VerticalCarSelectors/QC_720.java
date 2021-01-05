package ATD.Selectors.QC_693_VerticalCarSelectors;

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

import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.*;

public class QC_720 {

  private Main_page_Logic mainPageLogic = new Main_page_Logic();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0",false);
  }

  @DataProvider(name = "routes", parallel = true)
  Object[] dataProvider() throws SQLException {
    return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main","category_maker_brand,category_maker,categories_maker,category_maker2");
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
  public void close() {
    closeWebDriver();
  }
}
