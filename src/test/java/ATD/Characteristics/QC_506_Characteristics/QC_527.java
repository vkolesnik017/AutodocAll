package ATD.Characteristics.QC_506_Characteristics;

import ATD.Category_name_brand_page_Logic;
import ATD.CommonMethods;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_527 {

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0", false);
  }

  @DataProvider(name = "test1", parallel = true)
  Object[] test1() throws SQLException {
    return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_name6,category_name_brand2");
  }

  @Test(dataProvider = "test1")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Checks presence recovery characteristic on mini card in top block")
  public void testPresenceRecoveryCharacteristicsOnMiniCard(String route) {
    openPage(route);
    String expecetedChar = "Zustand  Wiederaufbereitet";
    new CommonMethods().checksOutputRecoveryCharacteristicInBlocksOfTopProducts(expecetedChar);
  }


  @DataProvider(name = "routesWithArticle", parallel = true)
  Object[] dataProviderWithArticle() throws SQLException {
    return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_name_brand7,category_maker_brand5");
  }

  @Test(dataProvider = "routesWithArticle")
  @Flaky
  @Owner(value = "Kolesnik")
  @Description(value = "Test checks presence Refurbished characteristic in listings and product page")
  public void testChecksPresenceRefurbishedCharacteristicInListingsAndProductPageWithArticle(String route) {
    openPage(route);

    new Category_name_brand_page_Logic().presenceRefurbishedCharacteristicInTopProductWithArticle("Zustand Wiederaufbereitet", "R");
  }
  @DataProvider(name = "routesWithCharacteristic", parallel = true)
  Object[] dataProviderWithCharacteristic() throws SQLException {
    return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_name_brand10,category_maker_brand6");
  }

  @Test(dataProvider = "routesWithCharacteristic")
  @Flaky
  @Owner(value = "Kolesnik")
  @Description(value = "Test checks presence Refurbished characteristic in listings and product page")
  public void testChecksPresenceRefurbishedCharacteristicInListingsAndProductPageWithCharacteristic(String route) {
    openPage(route);

    new Category_name_brand_page_Logic().presenceRefurbishedCharacteristicInTopProductWithCharacteristic("DELCO REMY","Ergänzende Info: Remy Remanufactured","Zustand Wiederaufbereitet");
}

  @AfterMethod
  private void close() {
    closeWebDriver();
  }

}
