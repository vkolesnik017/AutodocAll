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
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class QC_722_DisappearsTooltipAfterRefreshPage {

  private Main_page_Logic mainPageLogic = new Main_page_Logic();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "routes", parallel = true)
  Object[] dataProvider() throws SQLException {
    return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main","main,category_name,category_name_brand");
  }

  @Test(dataProvider = "routes")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Disappears tooltip after refresh page")
  public void testDisappearsTooltipAfterRefreshPage(String route) {
    open(route);
    mainPageLogic.tooltipInCarSelectorCloseBtn().shouldBe(visible);
    refresh();
    mainPageLogic.tooltipInCarSelectorCloseBtn().shouldBe(not(visible));
  }

  @AfterMethod
  public void close() {
    closeWebDriver();
  }
}
