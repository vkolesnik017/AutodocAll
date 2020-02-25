package ATD.Selectors.QC_663_HorizontalCarSelector;

import ATD.Product_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.checkingContainsUrl;
import static ATD.SetUp.setUpBrowser;

public class QC_672_UseHorizontalSelectorWhenChosenExistingKBA {

  private Product_page_Logic product_page_logic = new Product_page_Logic();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "route", parallel = true)
  Object[] dataProvider() {
    return new SetUp().setUpShop("prod", "DE");
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Use horizontal selector when chosen existing kna number")
  public void testUseHorizontalSelectorWhenChosenExistingKBA(String route) {
    product_page_logic.openProductPageById(route, "0019946")
            .fillNumberKba("0603", "419")
            .clickKbaBtn()
            .verifyNameRouteEqualsMakerCarList();
    checkingContainsUrl("ersatzteile/vw/golf/golf-iv-1j1/8799-1-4-16v");
  }
}
