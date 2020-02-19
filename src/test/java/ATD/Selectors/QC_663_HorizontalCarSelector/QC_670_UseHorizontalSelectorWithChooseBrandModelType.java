package ATD.Selectors.QC_663_HorizontalCarSelector;

import ATD.Product_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.text;

public class QC_670_UseHorizontalSelectorWithChooseBrandModelType {

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
  @Description(value = "Use vertical car selector when choose brand model type")
  public void testUseHorizontalSelectorWithChooseBrandModeType(String route) {
    product_page_logic.openProductPageById(route, "10019946")
            .chooseBrandModelTypeInHorizontalSelector("CITROЁN", "393", "20249")
            .clickSearchBtnInHorizontalSelector()
            .infoBlockWithSelectedCar().waitUntil(
            text("Dieses Produkt passt zu Ihrem CITROЁN AX 1.4 Allure"), 20000);
  }

}
