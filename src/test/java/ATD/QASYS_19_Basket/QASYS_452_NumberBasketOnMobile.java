package ATD.QASYS_19_Basket;

import ATD.Main_page_mob;
import ATD.SetUp;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class QASYS_452_NumberBasketOnMobile {

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "route", parallel = true)
  Object[] dataProvider() {
    return new SetUp().setUpShop("mob", "DE_m");
  }

  @Owner(value = "Evlentiev")
  @Test(dataProvider = "route")
  @Flaky
  @Description(value = "check number basket on mobile site")
  public void checkNumberBasketOnMobile(String route) {
    open(route);
    new Main_page_mob().numberBasket().shouldBe(Condition.visible);
  }

  @AfterMethod
  private void tearDown() {
    close();
  }
}
