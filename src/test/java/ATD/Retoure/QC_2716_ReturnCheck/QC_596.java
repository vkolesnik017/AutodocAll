package ATD.Retoure.QC_2716_ReturnCheck;

import ATD.*;
import AWS.Order_aws;
import Common.DataBase;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.getShopFromRoute;
import static ATD.CommonMethods.idPfandProduct;
import static ATD.CommonMethods.password;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.*;

public class QC_596 {

  private Product_page_Logic product_page_logic = new Product_page_Logic();
  private Retouren_page retourenPage = new Retouren_page();
  private DataBase db = new DataBase("ATD");

  private String idUserAws = "13798320";
  private String orderNumber;
  private String mail = "QC_596_retoure@mailinator.com";

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0", false);
  }

  @DataProvider(name = "route")
  Object[] dataProvider() {
    return new SetUp("ATD").setUpShop("prod", "DE");
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Checking translation of causes on the retoure page")
  public void testTranslationOfCausesOnRetourePage(String route) throws SQLException {
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
    new Order_aws(orderNumber).openOrderInAwsWithLogin()
            .checkOrderHasTestStatus()
            .setStatusOrderToVersendetVorkasse()
            .addDeliveryConditionGLS("0" , "GLS");
    open(route + "/" + db.getRouteByRouteName(getShopFromRoute(route), "return_return"));
    new Retouren_page_Logic().findOrder("11111", orderNumber)
            .checkingTranslateOfCausesForReturn();
  }

  @AfterMethod
  public void setStatusTestToOrder() {
    new Order_aws(orderNumber).setStatusOrderToTestbestellungen();
    closeWebDriver();
  }

}
