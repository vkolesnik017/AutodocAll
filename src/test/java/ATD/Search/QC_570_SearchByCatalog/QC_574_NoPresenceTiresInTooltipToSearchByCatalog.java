package ATD.Search.QC_570_SearchByCatalog;

import ATD.Categories_page_Logic;
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

public class QC_574_NoPresenceTiresInTooltipToSearchByCatalog {

  private Categories_page_Logic categoriesPageLogic = new Categories_page_Logic();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "route")
  Object[] test1() throws SQLException {
    return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "maker_car_list6");
  }
  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "No presence tires in tooltip to search by catalog")

  public void testQC_574_NoPresenceTiresInTooltipToSearchByCatalog(String route) {
    String valueToSearch = "reifen";
    openPage(route);
    categoriesPageLogic.inputTextInSearchBarByCatalog(valueToSearch)
            .checkThatNoTooltipInSearchByCatalog(valueToSearch);
  }

}
