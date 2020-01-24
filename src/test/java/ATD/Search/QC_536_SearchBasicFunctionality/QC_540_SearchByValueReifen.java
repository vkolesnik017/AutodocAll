package ATD.Search.QC_536_SearchBasicFunctionality;

import ATD.Main_page;
import ATD.SetUp;
import ATD.Tyres_page;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.checkingContainsUrl;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.open;

public class QC_540_SearchByValueReifen {

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
  @Description(value = "The test verifies redirect to tires catalog after search by text reifen")
  public void testSearchByValueReifen(String route) {
    open(route);
    new Main_page().useSearch("Reifen");
    checkingContainsUrl("https://www.autodoc.de/reifen");
  }
}
