package AWS.Retoure;

import AWS.Order_aws;
import AWS.UsersSettings_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.open;

public class QC_710_TranslationOfCausesInAws {

  private UsersSettings_aws usersSettingsAws = new UsersSettings_aws();

  private String idUserAws = "13803213";
  private String orderNumber = "28005811";
  private String mail = "QC_710_retoure@mailinator.com";

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
    new Order_aws(orderNumber).openOrderInAwsWithLogin()
            .setStatusOrderToVersendetVorkasse();
  }

  @DataProvider(name = "languages")
  private Object[][] languages() {
    return new Object[][] {
      new Object[]{"pl"},
      new Object[]{"de"},
      new Object[]{"en"},
      new Object[]{"ru"}
    };
  }

  @Test(dataProvider = "languages")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Checking translation of causes on the retoure page")
  public void testTranslationOfCausesOnRetourePage(String language) throws SQLException {
    open(usersSettingsAws.urlUsersSettings);
    new UsersSettings_aws().chooseLanguage(language);
    new Order_aws(orderNumber).openOrderInAwsWithoutLogin()
            .openPopupOfAddReclamation()
            .checkingTranslateOfCausesForReturn(language);
  }

  @AfterClass
  public void setStatusTestToOrder() {
    new Order_aws(orderNumber).setStatusOrderToTestbestellungen();
  }
}
