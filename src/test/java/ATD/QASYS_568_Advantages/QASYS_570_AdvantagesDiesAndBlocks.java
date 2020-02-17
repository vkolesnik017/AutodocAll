package ATD.QASYS_568_Advantages;

import ATD.Product_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;

public class QASYS_570_AdvantagesDiesAndBlocks {

  private Product_page_Logic product_page_logic = new Product_page_Logic();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "product", parallel = true)
  Object[] dataProvider() throws SQLException {
    return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "product11");
  }

  @Test(dataProvider = "product")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-1. Checks output block with description advantages")
  public void testOutputBlockWithDescriptionAdvantages(String route) {
    open(route);
    product_page_logic.freeDeliveryIcon().shouldBe(visible);
    product_page_logic.safeOrderIcon().shouldBe(visible);
    product_page_logic.days14ForReturnOfGoodsIcon().shouldBe(visible);
    product_page_logic.years2OnWarrantyIcon().shouldBe(visible);
  }

  @Test(dataProvider = "product")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-2. Checks output block with description payment methods")
  public void testOutputBlockWithDescriptionPaymentMethods(String route) {
    open(route);
    product_page_logic.paymentMethodsBlock().shouldBe(visible);
  }

  @Test(dataProvider = "product")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-3. Checks output block with description delivery services")
  public void testOutputBlockWithDescriptionDeliveryServices(String route) {
    open(route);
    product_page_logic.deliveryServicesBlock().shouldBe(visible);
  }
}
