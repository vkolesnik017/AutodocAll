package ATD.Selectors.QC_2676_Selectors_Car;

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
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class QC_694 {

  private Main_page_Logic mainPageLogic = new Main_page_Logic();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0", false);
  }

  @DataProvider(name = "routes", parallel = true)
  Object[] dataProvider() throws SQLException {
    return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main","main,category_name,categories,category_name_brand,supplier_brand_line");
  }

  @Test(dataProvider = "routes")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Appears error what not selected brand when used vertical selector with empty value")
  public void testAppearsErrorThatBrandCarNotSelected(String route) {
    open(route);
    mainPageLogic.openVerticalCarSelectorIfItHidden()
            .clickSearchBtnInVerticalSelectorWhenNotSelectedFields()
            .errorTooltipOfBrandSelector().shouldHave(
                    exactText("Um Autoteile für Ihren Wagen zu suchen, geben Sie bitte Ihr genaues Automodell an"));
  }

  @AfterMethod
  public void close() {
    closeWebDriver();
  }
}
