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
import static org.testng.Assert.assertEquals;

public class QC_698_ResetCarSelector {

  private Main_page_Logic mainPageLogic = new Main_page_Logic();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0", false);
  }

  @DataProvider(name = "routes", parallel = true)
  Object[] dataProvider() throws SQLException {
    return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main","main,category_name,categories,category_name_brand");
  }

  @Test(dataProvider = "routes")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Reset vertical car selector")
  public void testResetCarSelector(String route) {
    open(route);
    mainPageLogic.chooseBrandInVerticalCarSelector("VW")
            .resetVerticalCarSelector();
    assertEquals(mainPageLogic.brandSelectorInVerticalCarSelector().getSelectedText(), "Marke wählen");
  }
  @AfterMethod
  public void close() {
    closeWebDriver();
  }
}
