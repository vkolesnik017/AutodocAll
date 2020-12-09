package ATD.Listings.QC_445_ListingTecDoc;

import ATD.ListingTecDocSoft404_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_479_AvailabilityRequestForm {

  private ListingTecDocSoft404_page_Logic listingTecDocSoft404PageLogic = new ListingTecDocSoft404_page_Logic();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "route")
  Object[] dataProvider() throws SQLException {
    return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list11");
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Checks availability request form")
  public void testAvailabilityRequestForm(String route) {
    openPage(route);
    listingTecDocSoft404PageLogic.sendRequestForm();
  }

  @AfterMethod
  private void close() {
    closeWebDriver();
  }
}
