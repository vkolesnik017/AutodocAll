package ATD.Listings.QC_445_ListingTecDoc;

import ATD.ListingTecDocSoft404_page;
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

public class QC_450_Soft404OnTecDoc {

  private ListingTecDocSoft404_page listingTecDocSoft404Page = new ListingTecDocSoft404_page();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0", false);
  }

  @DataProvider(name = "route")
  Object[] dataProvider() throws SQLException {
    return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list9");
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Checks soft 404 on TecDoc")
  public void testSoft404OnTecDoc(String route) {
    openPage(route);
    new ListingTecDocSoft404_page_Logic().check404TecdocListing();
  }

  @AfterMethod
  private void close() {
    closeWebDriver();
  }

}
