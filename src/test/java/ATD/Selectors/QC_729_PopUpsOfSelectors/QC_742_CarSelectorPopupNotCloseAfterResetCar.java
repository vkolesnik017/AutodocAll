package ATD.Selectors.QC_729_PopUpsOfSelectors;

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

public class QC_742_CarSelectorPopupNotCloseAfterResetCar {

  private Main_page_Logic mainPageLogic = new Main_page_Logic();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "routes", parallel = true)
  Object[] dataProvider() throws SQLException {
    return new SetUp().setUpShopWithSubroutes("prod", "DE", "main","main,product");
  }

  @Test(dataProvider = "routes")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Car selector popup not close after reset car")
  public void testCarSelectorPopupNotCloseAfterResetCar(String route) {
    open(route);
    mainPageLogic.fillNumberKba("0000", "000").clickKbaBtn();
    mainPageLogic.chooseBrandInCarSelectorPopup("VW").resetCarSelectorPopup();
    assertEquals(mainPageLogic.brandSelectorInCarSelectorPopup().getSelectedText(), "Marke wählen");
  }
  @AfterMethod
  public void close() {
    closeWebDriver();
  }
}
