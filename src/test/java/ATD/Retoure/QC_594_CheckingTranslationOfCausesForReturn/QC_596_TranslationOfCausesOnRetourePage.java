package ATD.Retoure.QC_594_CheckingTranslationOfCausesForReturn;

import ATD.DataBase;
import ATD.Product_page;
import ATD.Retouren_page;
import ATD.SetUp;
import AWS.Order_aws;
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
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class QC_596_TranslationOfCausesOnRetourePage {

  private Product_page productPage = new Product_page();
  private Retouren_page retourenPage = new Retouren_page();
  private DataBase db = new DataBase();

  private String idUserAws = "13798320";
  private String orderNumber;
  private String mail = "QC_596_retoure@mailinator.com";

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
  @Description(value = "Checking translation of causes on the retoure page")
  public void testTranslationOfCausesOnRetourePage(String route) throws SQLException {
    orderNumber = productPage.openProductPageById(route, idPfandProduct)
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
            .addDeliveryConditionGLS();
    open(route + "/" + db.getRouteByRouteName(getShopFromRoute(route), "return_return"));
    retourenPage.findOrder("11111", orderNumber)
            .checkingTranslateOfCausesForReturn();
  }

  @AfterMethod
  public void setStatusTestToOrder() {
    new Order_aws(orderNumber).setStatusOrderToTestbestellungen();
    close();
  }

}
