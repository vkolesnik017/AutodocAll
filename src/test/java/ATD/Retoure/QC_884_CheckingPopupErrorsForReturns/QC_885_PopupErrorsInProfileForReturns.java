package ATD.Retoure.QC_884_CheckingPopupErrorsForReturns;

import ATD.DataBase;
import ATD.Product_page;
import ATD.Profile_page;
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

public class QC_885_PopupErrorsInProfileForReturns {

  private Product_page productPage = new Product_page();
  private Profile_page profilePage = new Profile_page();
  private DataBase db = new DataBase();

  private String idUserAws = "13781002";
  private String orderNumber;
  private String mail = "QC_885_retoure@mailinator.com";

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
  @Description(value = "The test checks for pop-up errors when clicking on the submit button without the selected product for return in profile")
  public void testPopupErrorsInProfileForReturns(String route) throws SQLException {
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
    open(route + "/" + db.getRouteByRouteName(getShopFromRoute(route), "profile_orders"));
    profilePage.clickBestelldetailsButton(orderNumber)
            .clickReturnOrReplaceItemButton()
            .chekingToAppearPopupErrorsOfReturn();
  }

  @AfterMethod
  public void setStatusTestToOrder() {
    new Order_aws(orderNumber).setStatusOrderToTestbestellungen();
    close();
  }

}
