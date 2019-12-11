package ATD.QASYS_539_Selectors;

import ATD.DataBase;
import ATD.Product_page;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.assertEquals;

public class QASYS_540_HorizontalSelectors {

  private String idProductForThisTest = "10019946";

  private Product_page productPage = new Product_page();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "route", parallel = true)
  Object[] dataProvider() {
    return new SetUp().setUpShop("prod", "DE");
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-1 Checks appears error what not selected brand when used horizontal selector with empty value")
  public void testErrorThatBrandCarNotSelected(String route) {
    productPage.openProductPageById(route, idProductForThisTest);
    productPage.selectorSearchBtn().click();
    productPage.errorTooltipOfBrandSelector().shouldBe(visible);
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-2 Checks appears error what not selected model when used horizontal selector with empty value")
  public void testErrorThatModelCarNotSelected(String route) {
    productPage.openProductPageById(route, idProductForThisTest);
    productPage.chooseBrandInSelector("VW");
    productPage.selectorSearchBtn().click();
    productPage.errorToolTipOfModelSelector().shouldBe(visible);
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-3 Checks appears error what not selected type when used horizontal selector with empty value")
  public void testErrorThatTypeCarNotSelected(String route) {
    productPage.openProductPageById(route, idProductForThisTest);
    productPage.chooseBrandInSelector("VW");
    productPage.chooseModelInSelector("4644");
    productPage.selectorSearchBtn().click();
    productPage.errorToolTipOfTypeSelector().shouldBe(visible);
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-4 Checks use of cars in the selector when choosing a brand + model + type")
  public void testUseOfSelectorWhenChoosingBrandModelType(String route) {
    productPage.openProductPageById(route, idProductForThisTest);
    productPage.chooseBrandModelTypeInSelector("CITROЁN", "393", "20249");
    productPage.selectorSearchBtn().click();
    productPage.infoBlockWithSelectedCar().shouldHave(text("CITROЁN AX 1.4 Allure"));
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-5 Checks use of cars in the selector when choosing existing KBA")
  public void testUseOfSelectorWhenChoosingExistingKBA(String route) {
    productPage.openProductPageById(route, idProductForThisTest);
    productPage.fillNumberKba("0603", "419");
    productPage.selectorKbaBtn().click();
    productPage.selectorKbaBtn().shouldBe(not(visible));
    checkingContainsUrl("ersatzteile/vw/golf/golf-iv-1j1/8799-1-4-16v");
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-6 Checks appears error what not filled kba")
  public void testErrorThatKbaNotFilled(String route) {
    productPage.openProductPageById(route, idProductForThisTest);
    productPage.selectorKbaBtn().click();
    productPage.errorToolTipOfKbaSelector().shouldBe(visible);
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-7 Checks selector reset when selected car")
  public void testSelectorReset(String route) throws SQLException {
    String urlWithSelectedCar = route + "/" + new DataBase().getRouteByRouteName("DE", "category_car_list");
    open(urlWithSelectedCar);
    productPage.openProductPageById(route, idProductForThisTest);
    productPage.resetBtnSelector().click();
    productPage.resetBtnSelector().shouldBe(not(visible));
    assertEquals(productPage.getChosenValueFromSelector(productPage.brandSelector()), "Marke wählen");
    assertEquals(productPage.getChosenValueFromSelector(productPage.modelSelector()), "Modell wählen");
    assertEquals(productPage.getChosenValueFromSelector(productPage.typeSelector()), "Motor (Typ) wählen");
  }

  @AfterTest
  private void tearDown() {
    close();
  }
}
