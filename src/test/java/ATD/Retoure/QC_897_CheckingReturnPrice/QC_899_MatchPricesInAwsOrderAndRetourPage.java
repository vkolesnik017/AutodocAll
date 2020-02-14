package ATD.Retoure.QC_897_CheckingReturnPrice;

import ATD.*;
import AWS.Order_aws;
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
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.assertEquals;

public class QC_899_MatchPricesInAwsOrderAndRetourPage {

  private Product_page_Logic product_page_logic = new Product_page_Logic();
  private Retouren_page retourenPage = new Retouren_page();
  private DataBase db = new DataBase();

  private String idUserAws = "13784381";
  private String orderNumber;
  private String mail = "QC_899_retoure@mailinator.com";

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
  @Description(value = "Verify that product prices match the order on AWS and on the retour page")
  public void testMatchPricesInAwsOrderAndRetourPage(String route) throws SQLException {
    orderNumber = product_page_logic.openProductPageById(route, idPfandProduct)
            .addProductToCart()
            .closePopupOtherCategoryIfYes()
            .cartClick()
            .nextButtonClick()
            .signIn(mail, password)
            .nextBtnClick()
            .chooseVorkasse()
            .nextBtnClick()
            .nextBtnClick()
            .getOrderNumber();
    Float productPriceInAwsOrder = new Order_aws(orderNumber).openOrderInAwsWithLogin()
            .checkOrderHasTestStatus()
            .setStatusOrderToVersendetVorkasse()
            .addDeliveryConditionGLS()
            .getSellingProductPrice();
    open(route + "/" + db.getRouteByRouteName(getShopFromRoute(route), "return_return"));
    Float productPriceOnRetourenPage = retourenPage.findOrder("11111", orderNumber)
            .getProductPriceForReturn();
    assertEquals(productPriceInAwsOrder, productPriceOnRetourenPage);
  }

  @AfterMethod
  public void setStatusTestToOrder() {
    new Order_aws(orderNumber).setStatusOrderToTestbestellungen();
    close();
  }

}
