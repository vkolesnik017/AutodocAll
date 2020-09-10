package ATD.Listings.QC_458_ListingViewModes;

import ATD.Listing_page_Logic;
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

public class QC_460_CompareProductsBetweenListingViewModesOnTecDoc {


  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "route")
  Object[] dataProvider() throws SQLException {
    return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list");
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Comparing products between listing modes and check presence elements for Tectdoc listing")
  public void testCompareProductsBetweenListingViewModesOnTecDoc(String route) {
    openPage(route);
    new Listing_page_Logic().checksImportantElementsOnTecDocListing()
            .compareProductsOrderBetweenListModeAndTileMode()
            .checksImportantElementsOnTecDocListing();
  }

  @AfterMethod
  private void close() {
    closeWebDriver();
  }
}
