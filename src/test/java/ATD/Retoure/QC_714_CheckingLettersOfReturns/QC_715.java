package ATD.Retoure.QC_714_CheckingLettersOfReturns;

import ATD.*;
import AWS.Order_aws;
import Common.DataBase;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import mailinator.WebMail;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class QC_715 {

  private String idUserAws = "13674919";
  private String orderNumber = null;
  private String mail = "QC_715_retoure@mailinator.com";

  private Product_page_Logic product_page_logic = new Product_page_Logic();
  private Retouren_page retourenPage = new Retouren_page();
  private WebMail webMail = new WebMail();
  private DataBase db = new DataBase("ATD");


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
  @Description(value = "Verification of the letter \"Confirmation of receipt of the application\" from the page /retoure")
  public void testLetterConfirmationOfReturnFromRetourePage(String route) throws SQLException {
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
            .setStatusOrderToVersendetVorkasse()
            .addDeliveryConditionGLS("0", "GLS");
    open(route + "/" + db.getRouteByRouteName(getShopFromRoute(route), "return_return"));
    new Retouren_page_Logic().findOrder("11111", orderNumber)
            .clickCheckbox()
            .chooseRandomCauseReturnInSelect()
            .fillInFormForMessage()
            .addFileIfIsDisplayedFileBlock()
            .clickSendenButtonWithCorrectData();
    webMail.openMail(mail)
            .letterInfo(1).shouldHave(text("just now")).shouldHave(text("Ihre Reklamation zur Bestellnummer: ".concat(orderNumber)));
  }

  @AfterMethod
  public void setStatusTestToOrder() {
    new Order_aws(orderNumber).setStatusOrderToTestbestellungen();
    closeWebDriver();
  }

}
