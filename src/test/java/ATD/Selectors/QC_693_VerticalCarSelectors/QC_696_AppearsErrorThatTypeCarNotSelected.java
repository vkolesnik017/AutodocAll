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
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class QC_696_AppearsErrorThatTypeCarNotSelected {

  private Main_page_Logic mainPageLogic = new Main_page_Logic();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "routes", parallel = true)
  Object[] dataProvider() throws SQLException {
    return new SetUp().setUpShopWithSubroutes("prod", "DE", "main","main,category_name,categories,category_name_brand");
  }

  @Test(dataProvider = "routes")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Appears error what not selected type when used vertical selector with empty value")
  public void testAppearsErrorThatTypeCarNotSelected(String route) {
    open(route);
    mainPageLogic.chooseBrandInVerticalCarSelector("CITROЁN")
            .chooseModelInVerticalCarSelector("393")
            .clickSearchBtnInVerticalSelectorWhenNotSelectedFields()
            .errorToolTipOfTypeSelector().shouldHave(
                    exactText("Wählen Sie eine Modifikation aus"));
  }

  @AfterMethod
  public void close() {
    closeWebDriver();
  }
}
