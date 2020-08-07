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
import static com.codeborne.selenide.Selenide.*;

public class QC_721_AppearsTooltipChooseTypeFirstTimeEnterSession {

  private Main_page_Logic mainPageLogic = new Main_page_Logic();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "routes", parallel = true)
  Object[] dataProvider() throws SQLException {
    return new SetUp().setUpShopWithSubroutes("prod", "DE", "main","category_model,model_maker_list");
  }

  @Test(dataProvider = "routes")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Appears tooltip the choose type for first time enter session")
  public void testAppearsTooltipChooseTypeFirstTimeEnterSession(String route) {
    open(route);
    mainPageLogic.closeTooltipInCarSelector();
  }

  @AfterMethod
  public void close() {
    closeWebDriver();
  }
}
