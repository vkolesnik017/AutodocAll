package ATD.QASYS_539_Selectors;

import ATD.DataBase;
import ATD.Product_page_Logic;
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

  private Product_page_Logic product_page_logic = new Product_page_Logic();

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
    product_page_logic.openProductPageById(route, idProductForThisTest);
    product_page_logic.selectorSearchBtn().click();
    product_page_logic.errorTooltipOfBrandSelector().shouldBe(visible);
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-2 Checks appears error what not selected model when used horizontal selector with empty value")
  public void testErrorThatModelCarNotSelected(String route) {
    product_page_logic.openProductPageById(route, idProductForThisTest);
    product_page_logic.chooseBrandInSelector("VW");
    product_page_logic.selectorSearchBtn().click();
    product_page_logic.errorToolTipOfModelSelector().shouldBe(visible);
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-3 Checks appears error what not selected type when used horizontal selector with empty value")
  public void testErrorThatTypeCarNotSelected(String route) {
    product_page_logic.openProductPageById(route, idProductForThisTest);
    product_page_logic.chooseBrandInSelector("VW");
    product_page_logic.chooseModelInSelector("4644");
    product_page_logic.selectorSearchBtn().click();
    product_page_logic.errorToolTipOfTypeSelector().shouldBe(visible);
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-4 Checks use of cars in the selector when choosing a brand + model + type")
  public void testUseOfSelectorWhenChoosingBrandModelType(String route) {
    product_page_logic.openProductPageById(route, idProductForThisTest);
    product_page_logic.chooseBrandModelTypeInSelector("CITROЁN", "393", "20249");
    product_page_logic.selectorSearchBtn().click();
    product_page_logic.infoBlockWithSelectedCar().shouldHave(text("CITROЁN AX 1.4 Allure"));
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-5 Checks use of cars in the selector when choosing existing KBA")
  public void testUseOfSelectorWhenChoosingExistingKBA(String route) {
    product_page_logic.openProductPageById(route, idProductForThisTest);
    product_page_logic.fillNumberKba("0603", "419");
    product_page_logic.selectorKbaBtn().click();
    product_page_logic.selectorKbaBtn().shouldBe(not(visible));
    checkingContainsUrl("ersatzteile/vw/golf/golf-iv-1j1/8799-1-4-16v");
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-6 Checks appears error what not filled kba")
  public void testErrorThatKbaNotFilled(String route) {
    product_page_logic.openProductPageById(route, idProductForThisTest);
    product_page_logic.selectorKbaBtn().click();
    product_page_logic.errorToolTipOfKbaSelector().shouldBe(visible);
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-7 Checks selector reset when selected car")
  public void testSelectorReset(String route) throws SQLException {
    String urlWithSelectedCar = route + "/" + new DataBase().getRouteByRouteName("DE", "category_car_list");
    open(urlWithSelectedCar);
    product_page_logic.openProductPageById(route, idProductForThisTest);
    product_page_logic.resetBtnSelector().click();
    product_page_logic.resetBtnSelector().shouldBe(not(visible));
    assertEquals(product_page_logic.getChosenValueFromSelector(product_page_logic.brandSelector()), "Marke wählen");
    assertEquals(product_page_logic.getChosenValueFromSelector(product_page_logic.modelSelector()), "Modell wählen");
    assertEquals(product_page_logic.getChosenValueFromSelector(product_page_logic.typeSelector()), "Motor (Typ) wählen");
  }

  @AfterTest
  private void tearDown() {
    close();
  }
}
