package ATD.Selectors.QC_663_HorizontalCarSelector;

import Common.DataBase;
import ATD.Product_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.usualIdProduct;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;

public class QC_675_ResetHorizontalCarSelector {

  private Product_page_Logic product_page_logic = new Product_page_Logic();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "route", parallel = true)
  Object[] dataProvider() {
    return new SetUp("ATD").setUpShop("prod", "DE");
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Reset horizontal car selector")
  public void testResetHorizontalCarSelected(String route) throws SQLException {
    String urlWithSelectedCar = route + "/" + new DataBase("ATD").getRouteByRouteName("DE", "category_car_list");
    open(urlWithSelectedCar);
    product_page_logic.openProductPageById(route, usualIdProduct)
            .resetHorizontalCarSelector();
    assertEquals(product_page_logic.brandSelector().getSelectedText(), "Marke wählen");
    assertEquals(product_page_logic.modelSelector().getSelectedText(), "Modell wählen");
    assertEquals(product_page_logic.typeSelector().getSelectedText(), "Motor (Typ) wählen");
  }
  @AfterMethod
  public void close() {
    closeWebDriver();
  }
}
