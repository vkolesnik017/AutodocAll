package ATD.Characteristics.QC_486_DynamicCharacteristics;

import ATD.Product_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.idProductWithDynamicChar;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.matchesText;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;

public class QC_494_DynamicCharacteristicIsNotOutputWithoutSelectedCar {

  private Product_page_Logic productPageLogic = new Product_page_Logic();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "route")
  Object[] dataProvider() {
    return new SetUp().setUpShop("prod", "DE");
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Checks dynamic characteristic is not output without selected car")
  public void testDynamicCharacteristicIsNotOutputWithoutSelectedCar(String route) {
    String desiredCharacteristicRegEx = "einzustellender Elektrodenabstand \\[mm]:\\n0,7";
    clearBrowserCache();
    productPageLogic.openProductPageById(route, idProductWithDynamicChar)
            .getCharacteristicsOfProduct()
            .filter(matchesText(desiredCharacteristicRegEx)).shouldHaveSize(0);
  }

  @AfterMethod
  private void close() {
    closeWebDriver();
  }
}
