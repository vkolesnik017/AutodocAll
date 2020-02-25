package ATD.Selectors.QC_663_HorizontalCarSelector;

import ATD.Product_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.usualIdProduct;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.*;

public class QC_666_AppearsErrorThatModelCarNotSelected {

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
  @Description(value = "Appears error what not selected model when used horizontal selector with empty value")
  public void testAppearsErrorThatModelCarNotSelected(String route) {
    product_page_logic.openProductPageById(route, usualIdProduct)
            .chooseBrandInHorizontalCarSelector("CITROЁN")
            .clickSearchBtnInHorizontalSelector()
            .errorToolTipOfModelSelector().shouldHave(
                    exactText("Wählen Sie ein Modell aus"));
  }
}
