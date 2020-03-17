package ATD.Selectors.QC_729_PopUpsOfSelectors;

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
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class QC_747_AppearsCarSelectorPopupOnSearchByScrollDown {

  private Main_page_Logic mainPageLogic = new Main_page_Logic();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "routes")
  Object[] dataProvider() throws SQLException {
    return new SetUp().setUpShopWithSubroutes("prod", "DE", "main","search6");
  }

  @Test(dataProvider = "routes")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Appears car selector popup on search by scroll down")
  public void testAppearsCarSelectorPopupOnSearchByScrollDown(String route) {
    open(route);
    executeJavaScript("window.scrollTo(0, 2000)");
    mainPageLogic.blockWithDropdownsOfChooseCarInCarSelectorPopup().shouldBe(visible);
    close();
  }
  @AfterMethod
  private void tearDown() {
    close();
  }
}
