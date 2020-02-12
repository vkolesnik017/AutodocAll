package ATD.Retoure.QC_714_CheckingLettersOfReturns;

import ATD.Product_page;
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

import static ATD.CommonMethods.idPfandProduct;
import static ATD.CommonMethods.password;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.close;

public class QC_784_LetterConfirmationOfReturnFromAws {

  private Product_page productPage = new Product_page();
  private Mailinator mailinator = new Mailinator();

  private String idUserAws = "13767378";
  private String orderNumber = null;
  private String mail = "QC_784_retoure@mailinator.com";

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
  @Description(value = "Verification of the letter \"Confirmation of receipt of the application\" from AWS")
  public void testLetterConfirmationOfReturnFromAws(String route) {
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
            .addDeliveryConditionGLS()
            .openPopupOfAddReclamation()
            .chooseRandomCauseReturnInSelect()
            .fillInFormForMessageReture()
            .clickSaveReclamationButton();
    mailinator.openEmail(mail)
            .letterInfo(1).shouldHave(text("moments ago")).shouldHave(text("Ihre Reklamation zur Bestellnummer: ".concat(orderNumber)));
  }

  @AfterMethod
  public void setStatusTestToOrder() {
    new Order_aws(orderNumber).setStatusOrderToTestbestellungen();
    close();
  }

}