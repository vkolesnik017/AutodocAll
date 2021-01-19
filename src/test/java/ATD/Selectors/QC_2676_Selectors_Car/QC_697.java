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

import static ATD.CommonMethods.checkingContainsUrl;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.*;

public class QC_697 {

  private Main_page_Logic mainPageLogic = new Main_page_Logic();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0", false);
  }

  @DataProvider(name = "routes", parallel = true)
  Object[] dataProvider() throws SQLException {
    return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main","main,categories");
  }

  @Test(dataProvider = "routes")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Use vertical car selector when choose brand model type, redirect to catalog page")
  public void testUseCarSelectorWhenChooseBrandModelType(String route) {
    open(route);
    mainPageLogic.chooseBrandModelTypeInSelector("CITROЁN", "393", "20249")
            .clickSearchBtnInVerticalSelectorWhenSelectedAllFields();
    checkingContainsUrl("ersatzteile/citroen/ax/ax-za/20249-1-4-allure");
  }

    @DataProvider(name = "routes2", parallel = true)
  Object[] dataProvider3() throws SQLException {
    return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main","category_name,category_name_brand");
  }

  @Test(dataProvider = "routes2")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Use vertical car selector when choose brand model type, redirect to category_name_brand")
  public void testUseCarSelectorWhenChooseBrandModelType2(String route) {
    open(route);
    mainPageLogic.chooseBrandModelTypeInSelector("CITROЁN", "393", "20249")
            .clickSearchBtnInVerticalSelectorWhenSelectedAllFields();
    checkingContainsUrl("/citroen/ax/ax-za/20249-1-4-allure");
  }
  @AfterMethod
  public void close() {
    closeWebDriver();
  }
}
