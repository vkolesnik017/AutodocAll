package ATD.Selectors.QC_771_RegKbaSelector;

import ATD.Product_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.checkingContainsUrl;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.*;

public class QC_672_UseHorizontalSelectorWhenChosenExistingKBA {

  private Product_page_Logic product_page_logic = new Product_page_Logic();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "route", parallel = true)
  Object[] dataProvider() {
    return new SetUp("ATD").setUpShop("prod", "DE");
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Use horizontal selector when chosen existing kna number")
  public void testUseHorizontalSelectorWhenChosenExistingKBA(String route) {
    product_page_logic.openProductPageById(route, "0019946");
    refresh();
    sleep(3000);
    product_page_logic.fillNumberKba("0603", "419")
            .clickKbaBtn()
            .verifyNameRouteEqualsMakerCarList();
    checkingContainsUrl("ersatzteile/vw/golf/golf-iv-1j1/8799-1-4-16v");
  }
  @AfterMethod
  public void close() {
    closeWebDriver();
  }
}
