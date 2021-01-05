package ATD.Retoure.QC_714_CheckingLettersOfReturns;

import ATD.Product_page_Logic;
import Common.SetUp;
import AWS.Order_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import mailinator.WebMail;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.idPfandProduct;
import static ATD.CommonMethods.password;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_784 {

  private Product_page_Logic product_page_logic = new Product_page_Logic();
  private WebMail webMail = new WebMail();

  private String idUserAws = "13767378";
  private String orderNumber = null;
  private String mail = "QC_784_retoure@mailinator.com";

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
  @Description(value = "Verification of the letter \"Confirmation of receipt of the application\" from AWS")
  public void testLetterConfirmationOfReturnFromAws(String route) {
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
            .addDeliveryConditionGLS("0", "GLS")
            .openPopupOfAddReclamation()
            .chooseRandomCauseReturnInSelect()
            .fillInFormForMessageReture()
            .clickSaveReclamationButton();
    webMail.openMail(mail)
            .letterInfo(1).shouldHave(text("moments ago")).shouldHave(text("Ihre Reklamation zur Bestellnummer: ".concat(orderNumber)));
  }

  @AfterMethod
  public void setStatusTestToOrder() {
    new Order_aws(orderNumber).setStatusOrderToTestbestellungen();
    closeWebDriver();
  }

}
