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

import static ATD.CommonMethods.usualIdProduct;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_674 {

  private Product_page_Logic product_page_logic = new Product_page_Logic();

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
  @Description(value = "Appears error that kba number not filled")
  public void testAppearsErrorThatKbaNumberNotFilled(String route) {
    product_page_logic.openProductPageById(route, usualIdProduct)
            .clickKbaBtn();
    product_page_logic.errorToolTipOfKbaSelector().shouldHave(
            text("Geben Sie bitte eine Schlüsselnummer ein, um nach einem Wagen zu suchen"));
  }
  @AfterMethod
  public void close() {
    closeWebDriver();
  }
}
