package ATD.Search.QC_570_SearchByCatalog;

import ATD.Categories_page_Logic;
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

public class QC_573_NoDeletedCategoriesInTooltipsToSearchByCatalog {

  private Categories_page_Logic categoriesPageLogic = new Categories_page_Logic();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "route")
  Object[] test1() throws SQLException {
    return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "categories");
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "No deleted categories in tooltips to search by catalog")
  public void testNoDeletedCategoriesInTooltipsToSearchByCatalog(String route) {
    openPage(route);
    categoriesPageLogic.inputTextInSearchBarByCatalog("kraftstofffilter")
            .checkThatNoTooltipInSearchByCatalog("kraftstofffilter-gehause");
  }
  @AfterMethod
  public void close() {
    closeWebDriver();
  }
}
