package ATD.QASYS_225_General_Common;

import ATD.Product_page;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class QASYS_379_BlocksOfTopProducts {

  private Product_page productPage = new Product_page();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "routesWithBlocksOfTopProducts", parallel = true)
  Object[] routesWithBlocksOfTopProducts() throws SQLException {
    return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "categories_maker,group_list,group_list_body,group_list_drive," +
            "group_list_fuel,group_list_year,group_list_hp,model_maker_list,model_maker_list_year,model_maker_list_hp,maker_car_list,category_name,category_maker,category_maker_body,category_maker_drive," +
            "category_group,category_group_body,category_group_drive,category_group_fuel,category_group_year,category_model");
  }

  @Test(dataProvider = "routesWithBlocksOfTopProducts")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "TC01 The test checks products not in stock in blocks of top products")
  public void testProductsNotInStockInBlockOfTopProducts(String route) {
    open(route);
    scrollToBlockOfTopProducts();
    checksProductsNotInStockInBlockOfTopProducts();
  }

  @DataProvider(name = "routesWithBlocksOfTopPairedProducts", parallel = true)
  Object[] routesWithBlocksOfTopPairedProducts() throws SQLException {
    return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_name,category_maker,category_maker_body," +
            "category_maker_drive,category_group,category_group_body,category_group_drive,category_group_fuel,category_group_year,category_model");
  }

  @Test(dataProvider = "routesWithBlocksOfTopPairedProducts")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "TC02 The test checks adding paired products to basket from block of top products")
  public void testAddingPairedProductsToBasketFromBlockOfTopProducts(String route) {
    open(route);
    closeCookiesFooterMessage();
    clickOfBuyBtnForAllPages();
    productPage.firstProductPriceInPopupOfCart().shouldBe(visible);
    productPage.cartClick()
            .fieldWithQuantityOfProducts().shouldHave(value("2"));
  }

  @DataProvider(name = "routes", parallel = true)
  Object[] routes() throws SQLException {
    return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_maker,group_list,category_maker_brand");
  }

  @Test(dataProvider = "routes")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "TC03 The test checks presence elements in mini-card in blocks of top products")
  public void testPresenceElementsInMiniCardInBlocksOfTopProducts(String route) {
    open(route);
    scrollToBlockOfTopProducts();
    checksPresenceElementsInMiniCardInBlocksOfTopProducts();
  }

  @AfterMethod
  public void tearDown() {
    close();
  }


}
