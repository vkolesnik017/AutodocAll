package ATD.Selectors.QC_693_VerticalCarSelectors;

import Common.DataBase;
import ATD.Main_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class QC_700 {

  private Main_page_Logic mainPageLogic = new Main_page_Logic();
  private DataBase db = new DataBase("ATD");

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0", false);
  }

    @DataProvider(name = "route", parallel = true)
  Object[] dataProvider4() {
    return new SetUp("ATD").setUpShop("prod", "DE");
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Reset vertical car selector on route ACC")
  public void testResetCarOnRouteAcc(String route) throws SQLException {
    String urlWithSelectedCar = route + "/" + db.getRouteByRouteName("DE", "category_car_list");
    String listingAcc = route + "/" + db.getRouteByRouteName("DE", "listing_accessories");
    open(urlWithSelectedCar);
    open(listingAcc);
    mainPageLogic.resetVerticalCarSelector()
            .brandSelectorInVerticalCarSelector().shouldNot(visible);
  }
  @AfterMethod
  public void close() {
    closeWebDriver();
  }
}
