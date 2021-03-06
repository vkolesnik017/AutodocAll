package AWS.Retoure;

import AWS.Order_aws;
import AWS.UsersSettings_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.*;
import java.sql.SQLException;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.*;

public class QC_710 {

  private String idUserAws = "13803213";
  private String mail = "QC_710_retoure@mailinator.com";

  private UsersSettings_aws usersSettingsAws = new UsersSettings_aws();
  private Order_aws orderAws = new Order_aws("28005811");

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0", false);
  }

  @BeforeMethod
  void  setStatusVersendetToOrder() {
    orderAws.openOrderInAwsWithLogin()
            .setStatusOrderToVersendetVorkasse(); sleep(2000);
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
    orderAws.openOrderInAwsWithoutLogin()
            .openPopupOfAddReclamation()
            .checkingTranslateOfCausesForReturn(language);
  }

  @AfterMethod
  public void close() {
    closeWebDriver();
  }

  @AfterClass
  public void setStatusTestToOrder() {
    orderAws.openOrderInAwsWithLogin()
            .setStatusOrderToTestbestellungen();
  }
}
