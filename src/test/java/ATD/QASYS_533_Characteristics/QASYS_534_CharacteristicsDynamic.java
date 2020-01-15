package ATD.QASYS_533_Characteristics;

import ATD.DataBase;
import ATD.Listing_page;
import ATD.Product_page;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.closeCookiesFooterMessage;
import static ATD.CommonMethods.getShopFromRoute;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.matchesText;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class QASYS_534_CharacteristicsDynamic {

  private String idProduct = "2295352";
  private String articleProduct = "Artikelnummer: V99-75-0011";
  private String desiredCharacteristicRegEx = "einzustellender Elektrodenabstand \\[mm]:\\n0,7";

  private DataBase db = new DataBase();
  private Product_page productPage = new Product_page();
  private Listing_page listingPage = new Listing_page();

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
  @Description(value = "Test-1. Checks output characteristic dynamic on product page")
  public void testOutputCharacteristicOnProductPage(String route) throws SQLException {
    open(route + "/" + db.getRouteByRouteName(getShopFromRoute(route), "maker_car_list2"));
    productPage.openProductPageById(route, idProduct)
            .getCharacteristicsOfProduct()
            .filter(matchText(desiredCharacteristicRegEx))
            .shouldHaveSize(1);
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-2. Checks output characteristic dynamic on tecdoc listing")
  public void testOutputCharacteristicOnTecDocListing(String route) throws SQLException {
    open(route + "/" + db.getRouteByRouteName(getShopFromRoute(route), "category_car_list12"));
    listingPage.getCharacteristicsDesiredProduct(articleProduct)
            .filter(matchesText(desiredCharacteristicRegEx)).shouldHaveSize(1);
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-3. Checks output characteristic dynamic on oen listing")
  public void testOutputCharacteristicOnOenListing(String route) throws SQLException {
    open(route + "/" + db.getRouteByRouteName(getShopFromRoute(route), "maker_car_list2"));
    closeCookiesFooterMessage();
    productPage.openProductPageById(route, idProduct)
            .linksInOenNumbersBlock()
            .filter(matchesText("OEN 12 14 119 — OPEL")).get(0).click();
    listingPage.getCharacteristicsDesiredProduct(articleProduct)
            .filter(matchesText(desiredCharacteristicRegEx)).shouldHaveSize(1);
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-4. Checks output characteristic dynamic on search listing")
  public void testOutputCharacteristicOnSearchListing(String route) throws SQLException {
    open(route + "/" + db.getRouteByRouteName(getShopFromRoute(route), "search9"));
    listingPage.getCharacteristicsDesiredProduct(articleProduct)
            .filter(matchesText(desiredCharacteristicRegEx)).shouldHaveSize(1);
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-5. Checks characteristic is not displayed without selected car in selector")
  public void testCharacteristicIsNotDisplayedWithoutSelectedCar(String route) {
    productPage.openProductPageById(route, idProduct)
            .getCharacteristicsOfProduct()
            .filter(matchesText(desiredCharacteristicRegEx)).shouldHaveSize(0);
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-6. Checks characteristic is not displayed if selected car does not fit spare part")
  public void testCharacteristicIsNotDisplayedIfSelectedCarDoesNotFitSparePart(String route) throws SQLException {
    open(route + "/" + db.getRouteByRouteName(getShopFromRoute(route), "maker_car_list3"));
    productPage.openProductPageById(route, idProduct)
            .getCharacteristicsOfProduct()
            .filter(matchesText(desiredCharacteristicRegEx)).shouldHaveSize(0);
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-7. Checks characteristic is not displayed if car in selector is not fully selected")
  public void testCharacteristicIsNotDisplayedIfCarInSelectorIsNotFullySelected(String route) throws SQLException {
    open(route + "/" + db.getRouteByRouteName(getShopFromRoute(route), "categories_maker"));
    productPage.openProductPageById(route, idProduct)
            .getCharacteristicsOfProduct()
            .filter(matchesText(desiredCharacteristicRegEx)).shouldHaveSize(0);
  }

  @AfterMethod
  private void tearDown() {
    close();
  }

}
