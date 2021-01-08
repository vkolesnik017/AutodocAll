package ATD.Selectors.QC_663_HorizontalCarSelector;

import ATD.Main_page_Logic;
import ATD.Product_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.usualIdProduct;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_681 {

  private Product_page_Logic product_page_logic = new Product_page_Logic();
  private Main_page_Logic main_page_logic = new Main_page_Logic();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0", false);
  }

  @DataProvider(name = "route", parallel = true)
  Object[] dataProvider() {
    return new SetUp("ATD").setUpShop("prod", "DE");
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Appears tooltip the choose brand for first time enter session")
  public void testAppearsTooltipChooseBrandFirstTimeEnterSession(String route) {
    product_page_logic.openProductPageById(route, usualIdProduct);
    main_page_logic.closeTooltipInCarSelector();
  }
  @AfterMethod
  public void close() {
    closeWebDriver();
  }
}
