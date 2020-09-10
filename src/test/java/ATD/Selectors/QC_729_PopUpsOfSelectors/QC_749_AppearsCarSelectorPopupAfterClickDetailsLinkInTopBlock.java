package ATD.Selectors.QC_729_PopUpsOfSelectors;

import ATD.CommonMethods;
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

import static ATD.CommonMethods.universalElementOfBuyBtnForAllPages;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class QC_749_AppearsCarSelectorPopupAfterClickDetailsLinkInTopBlock {

  private Main_page_Logic mainPageLogic = new Main_page_Logic();
  private CommonMethods commonMethods = new CommonMethods();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "routes", parallel = true)
  Object[] dataProvider() throws SQLException {
    return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main","category_name,category_maker,category_group,category_model,category_name_brand,category_group_brand");
  }

  @Test(dataProvider = "routes")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Appears car selector popup after click details link in top block")
  public void testAppearsCarSelectorPopupAfterClickDetailsLinkInTopBlock(String route) {
    open(route);
    commonMethods.scrollToBlockOfTopProducts();
    universalElementOfBuyBtnForAllPages().hover();
    sleep(1000);
    commonMethods.detailsButtonInTopProductsBlock().click();
    mainPageLogic.blockWithDropdownsOfChooseCarInCarSelectorPopup().shouldBe(visible);
  }

  @AfterMethod
  public void close() {
    closeWebDriver();
  }
}
