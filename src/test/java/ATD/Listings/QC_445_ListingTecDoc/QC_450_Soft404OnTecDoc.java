package ATD.Listings.QC_445_ListingTecDoc;

import ATD.ListingTecDocSoft404_page;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.visible;

public class QC_450_Soft404OnTecDoc {

  private ListingTecDocSoft404_page listingTecDocSoft404Page = new ListingTecDocSoft404_page();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "route")
  Object[] dataProvider() throws SQLException {
    return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_car_list9");
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Checks soft 404 on TecDoc")
  public void testSoft404OnTecDoc(String route) {
    openPage(route);
    listingTecDocSoft404Page.blockOfNoFindProduct().shouldBe(visible);
    listingTecDocSoft404Page.blockWithCategories().shouldBe(visible);
    listingTecDocSoft404Page.blockWithTopProducts().shouldBe(visible);
  }

}
