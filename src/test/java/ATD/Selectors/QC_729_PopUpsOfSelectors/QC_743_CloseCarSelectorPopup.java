package ATD.Selectors.QC_729_PopUpsOfSelectors;

import Common.DataBase;
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

import static ATD.CommonMethods.closeAnyPopupByClickOverlay;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class QC_743_CloseCarSelectorPopup {

  private Main_page_Logic mainPageLogic = new Main_page_Logic();
  private DataBase dataBase = new DataBase();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "routes")
  Object[] dataProvider() throws SQLException {
    return new SetUp().setUpShopWithSubroutes("prod", "DE", "main","main,category_car_list,product");
  }

  @Test(dataProvider = "routes")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Close car selector popup")
  public void testCloseCarSelectorPopup(String route) {
    open(route);
    mainPageLogic.fillNumberKba("0000", "000").clickKbaBtn();
    mainPageLogic.closeBtnInCarSelectorPopup().click();
    mainPageLogic.closeBtnInCarSelectorPopup().shouldBe(not(visible));
    mainPageLogic.fillNumberKba("0000", "000").clickKbaBtn();
    closeAnyPopupByClickOverlay();
  }

  @Test
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Close car selector popup on accessories route")
  public void testCloseCarSelectorPopupAccessories() throws SQLException {
    openPage("https://autodoc.de/" + dataBase.getRouteByRouteName("DE", "category_car_list"));
    open("https://autodoc.de/" + dataBase.getRouteByRouteName("DE", "listing_accessories3"));
    mainPageLogic.fillNumberKba("0000", "000").clickKbaBtn();
    mainPageLogic.closeBtnInCarSelectorPopup().click();
    mainPageLogic.closeBtnInCarSelectorPopup().shouldBe(not(visible));
    mainPageLogic.fillNumberKba("0000", "000").clickKbaBtn();
    closeAnyPopupByClickOverlay();
  }

  @AfterMethod
  public void close() {
    closeWebDriver();
  }
}
