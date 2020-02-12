package ATD.QASYS_539_Selectors;

import ATD.*;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.*;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
import static ATD.CommonMethods.openVerticalSelectorIfItHidden;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;

public class QASYS_541_VerticalSelectors {

  private String brandToChoose = "CITROЁN";
  private String modelToChoose = "393";
  private String typeToChoose = "20249";

  private Main_page mainPage = new Main_page();
  private DataBase db = new DataBase();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "routes", parallel = true)
  Object[] dataProvider() throws SQLException {
    return new SetUp().setUpShopWithSubroutes("prod", "DE", "main","main,category_name,categories,category_name_brand");
  }

//  @Test(dataProvider = "routes")
//  @Flaky
//  @Owner(value = "Evlentiev")
//  @Description(value = "Test-1 Checks appears error what not selected brand when used vertical selector with empty value")
//  public void testErrorThatBrandCarNotSelected(String route) {
//    open(route);
//    openVerticalSelectorIfItHidden();
//    mainPage.selectorSearchBtn().click();
//    mainPage.errorTooltipOfBrandSelector().shouldBe(visible);
//  }

  @Test(dataProvider = "routes")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-2 Checks appears error what not selected model when used vertical selector with empty value")
  public void testErrorThatModelCarNotSelected(String route) {
    open(route);
    openVerticalSelectorIfItHidden();
    mainPage.chooseBrandInSelector(brandToChoose)
            .selectorSearchBtn().click();
    mainPage.errorToolTipOfModelSelector().shouldBe(visible);
  }

  @Test(dataProvider = "routes")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-3 Checks appears error what not selected type when used vertical selector with empty value")
  public void testErrorThatTypeCarNotSelected(String route) {
    open(route);
    openVerticalSelectorIfItHidden();
    mainPage.chooseBrandInSelector(brandToChoose)
            .chooseModelInSelector(modelToChoose)
            .selectorSearchBtn().click();
    mainPage.errorToolTipOfTypeSelector().shouldBe(visible);
  }

  @DataProvider(name = "routesMainAndCategories", parallel = true)
  Object[] dataProvider2() throws SQLException {
    return new SetUp().setUpShopWithSubroutes("prod", "DE", "main","main,categories");
  }

  @Test(dataProvider = "routesMainAndCategories")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-4 Checks use of cars in the vertical selector when choosing a brand + model + type for routes (main and categories)")
  public void testUseOfSelectorWhenChoosingBrandModelType(String route) {
    open(route);
    openVerticalSelectorIfItHidden();
    mainPage.chooseBrandModelTypeInSelector(brandToChoose, modelToChoose, typeToChoose)
            .selectorSearchBtn().click();
    checkingContainsUrl("ersatzteile/citroen/ax/ax-za/20249-1-4-allure");
    new Catalog_page().catalogBlog().shouldBe(visible);
  }

  @DataProvider(name = "routesCategoryNameAndCategoryNameBrand", parallel = true)
  Object[] dataProvider3() throws SQLException {
    return new SetUp().setUpShopWithSubroutes("prod", "DE", "main","category_name,category_name_brand");
  }

  @Test(dataProvider = "routesCategoryNameAndCategoryNameBrand")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-4 Checks use of cars in the vertical selector when choosing a brand + model + type for routes (category_name and category_name_brand)")
  public void testUseOfSelectorWhenChoosingBrandModelType2(String route) {
    open(route);
    openVerticalSelectorIfItHidden();
    mainPage.chooseBrandModelTypeInSelector(brandToChoose, modelToChoose, typeToChoose)
            .selectorSearchBtn().click();
    checkingContainsUrl("/citroen/ax/ax-za/20249-1-4-allure");
    new Listing_page().priceFilterSubmitButton().shouldBe(visible);
  }

  @Test(dataProvider = "routes")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-5 Checks selector reset")
  public void testSelectorReset(String route) throws SQLException {
    open(route);
    openVerticalSelectorIfItHidden();
    mainPage.chooseBrandInSelector(brandToChoose);
    assertEquals(mainPage.brandSelector().getSelectedText(), brandToChoose);
    mainPage.resetBtnSelector().click();
    mainPage.resetBtnSelector().shouldBe(not(visible));
    assertEquals(mainPage.brandSelector().getSelectedText(), "Marke wählen");
  }

  @Test(dataProvider = "routes")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-6 Checks appears popup kba after click link")
  public void testKbaPopup(String route) {
    open(route);
    openVerticalSelectorIfItHidden();
    mainPage.linkInfoKba().click();
    mainPage.kbaPopup().shouldBe(visible);
  }

  @DataProvider(name = "route", parallel = true)
  Object[] dataProvider4() {
    return new SetUp().setUpShop("prod", "DE");
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-7 Checks car reset on route ACC")
  public void testCarResetOnRouteAcc(String route) throws SQLException {
    String urlWithSelectedCar = route + "/" + db.getRouteByRouteName("DE", "category_car_list");
    String listingAcc = route + "/" + db.getRouteByRouteName("DE", "listing_accessories");
    open(urlWithSelectedCar);
    open(listingAcc);
    mainPage.resetBtnSelector().click();
    mainPage.selectorSearchBtn().shouldBe((visible));
  }

  @AfterMethod
  public void tearDown() {
    close();
  }
}
