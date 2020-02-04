package ATD.Retoure.QC_714_CheckingLetters;

import ATD.DataBase;
import ATD.Product_page;
import ATD.Retouren_page;
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

public class QC_715_LetterConfirmationOfReceiptOfApplicationFromPageRetoure {

  private String idUserAws = "13674919";
  private String orderNumber = null;

  private Product_page productPage = new Product_page();
  private Retouren_page retourenPage = new Retouren_page();
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
  @Description(value = "Verification of the letter \"Confirmation of receipt of the application\" from the page /retoure")
  public void testLetterConfirmationOfReceiptOfApplicationFromPageRetoure(String route) throws SQLException {
    orderNumber = productPage.openProductPageById(route, usualIdProduct)
            .addProductToCart()
            .closePopupOtherCategoryIfYes()
            .cartClick()
            .nextButtonClick()
            .signIn("QC_715_retoure@mailinator.com", password)
            .nextBtnClick()
            .chooseVorkasse()
            .nextBtnClick()
            .nextBtnClick()
            .getOrderNumber();
    new Order_aws(orderNumber).openOrderInAwsWithLogin()
            .setStatusOrderToVersendetVorkasse()
            .addDeliveryConditionGLS();
    open(route + "/" + db.getRouteByRouteName(getShopFromRoute(route), "return_return"));
    retourenPage.findOrder("11111", orderNumber)
            .clickCheckbox()
            .chooseRandomCauseReturnInSelect()
            .fillInFormForMessage()
            .addFileIfIsDisplayedFileBlock()
            .clickSendenButtonWithCorrectData();
    mailinator.openEmail("QC_715_retoure@mailinator.com")
            .letterInfo(1).shouldHave(text("moments ago")).shouldHave(text("Ihre Reklamation zur Bestellnummer: ".concat(orderNumber)));
  }

  @AfterMethod
  public void setStatusTestToOrder() {
    new Order_aws(orderNumber).setStatusOrderToTestbestellungen();
  }

}
