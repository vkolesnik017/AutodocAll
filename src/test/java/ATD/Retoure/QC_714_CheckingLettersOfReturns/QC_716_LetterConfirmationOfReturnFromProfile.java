package ATD.Retoure.QC_714_CheckingLettersOfReturns;

import ATD.DataBase;
import ATD.Product_page;
import ATD.Profile_page;
import ATD.SetUp;
import AWS.Order_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import mailinator.Mailinator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;

public class QC_716_LetterConfirmationOfReturnFromProfile {

  private String idUserAws = "13751304";
  private String orderNumber = null;
  private String mail = "QC_716_retoure@mailinator.com";

  private Product_page productPage = new Product_page();
  private Mailinator mailinator = new Mailinator();
  private DataBase db = new DataBase();

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
  @Description(value = "Verification of the letter \"Confirmation of receipt of the application\" from profile")
  public void testLetterConfirmationOfReturnFromProfile(String route) throws SQLException {
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
            .setStatusOrderToVersendetVorkasse()
            .addDeliveryConditionGLS();
    open(route + "/" + db.getRouteByRouteName(getShopFromRoute(route), "profile_orders"));
    new Profile_page().clickBestelldetailsButton(orderNumber)
            .clickReturnOrReplaceItemButton()
            .clickCheckbox()
            .chooseRandomCauseReturnInSelect()
            .fillInFormForMessage()
            .addFileIfIsDisplayedFileBlock()
            .clickSendenButtonWithCorrectData();
    mailinator.openEmail(mail)
            .letterInfo(1).shouldHave(text("moments ago")).shouldHave(text("Ihre Reklamation zur Bestellnummer: ".concat(orderNumber)));
  }

  @AfterMethod
  public void setStatusTestToOrder() {
    new Order_aws(orderNumber).setStatusOrderToTestbestellungen();
  }

}
