package ATD.QASYS_533_Characteristics;

import ATD.*;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.apache.http.util.Asserts;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class QASYS_536_CharacteristicsRecovery {

  private CommonMethods commonMethods = new CommonMethods();
  private Listing_page listingPage = new Listing_page();
  private Product_page productPage = new Product_page();

  private String expecetedChar = "Zustand  Wiederaufbereitet";

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "test1", parallel = true)
  Object[] test1() throws SQLException {
    return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_name6,category_name_brand2");
  }

  @Test(dataProvider = "test1")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-1. Checks output characteristic recovery on mini card of top block")
  public void testOutputCharacteristicRecoveryOnMiniCardOfTopBlock(String route) {
    open(route);
    commonMethods.cheksOutputRecoveryCharacteristicInBlocksOfTopProducts(expecetedChar);
  }

  @DataProvider(name = "test2", parallel = true)
  Object[] test2() throws SQLException {
    return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "search11,category_oen5,category_car_list13");
  }

  @Test(dataProvider = "test2")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-2. Checks output characteristic recovery on listings")
  public void testOutputCharacteristicRecoveryOnListing(String route) {
    open(route);
    listingPage.recoveryCharacteristics().shouldHave(sizeGreaterThanOrEqual(1));
  }

  @DataProvider(name = "route")
  Object[] dataProvider() {
    return new SetUp().setUpShop("prod", "DE");
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-3. Checks output characteristic recovery in basket")
  public void testOutputCharacteristicsRecoveryInBasket(String route) {
    productPage.openProductPageById(route, "1099441")
            .addProductToCart()
            .closePopupOtherCategoryIfYes()
            .cartClick()
            .getCharacteristicsOfProduct().filter(matchText("Zustand:\\nWiederaufbereitet")).shouldHaveSize(1);
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-4. Checks output characteristic recovery in product page")
  public void testOutputCharacteristicsRecoveryInProductPage(String route) {
    productPage.openProductPageById(route, "1099441")
            .uncoverCharacteristics()
            .getCharacteristicsOfProduct().filter(matchText("Zustand  \\nWiederaufbereitet")).shouldHaveSize(1);

  }

  @AfterMethod
  private void tearDown() {
    close();
  }

}
