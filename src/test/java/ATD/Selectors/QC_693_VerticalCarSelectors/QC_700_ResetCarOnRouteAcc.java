package ATD.Selectors.QC_693_VerticalCarSelectors;

import ATD.DataBase;
import ATD.Main_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class QC_700_ResetCarOnRouteAcc {

  private Main_page_Logic mainPageLogic = new Main_page_Logic();
  private DataBase db = new DataBase();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

    @DataProvider(name = "route", parallel = true)
  Object[] dataProvider4() {
    return new SetUp().setUpShop("prod", "DE");
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
