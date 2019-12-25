package ATD.QASYS_225_General_Common;

import ATD.DataBase;
import ATD.Main_page;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.*;

import java.sql.SQLException;

import static ATD.CommonMethods.closeCookiesFooterMessage;
import static ATD.CommonMethods.getCurrentShopFromJSVarInHTML;
import static ATD.CommonMethods.checkingContainsUrl;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class QASYS_350_Header {

  private Main_page mainPage = new Main_page();
  private DataBase db = new DataBase();

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
  @Description(value = "TC 1.1 and 1.2 Checks presence elements in promotion banner and its closing")
  public void testPromotionBannerInHeader(String route) {
    open(route);
    mainPage.promotionBanner().shouldBe(visible);
    mainPage.clockInPromotionBanner().shouldBe(visible);
    mainPage.textAboveClockInPromotionBanner().shouldBe(visible);
    mainPage.closeBtnOfPromotionBanner().click();
    mainPage.promotionBanner().shouldBe(not(visible));
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "TC 2  Checks presence elements in header")
  public void testPresenceElementsInHeader(String route) {
    open(route);
    mainPage.logoInHeader().shouldBe(visible);
    mainPage.loginBtnInHeader().shouldBe(visible);
    mainPage.menuCatalogInHeader().shouldBe(visible);
    mainPage.searchBar().shouldBe(visible);
    mainPage.infoIconForSearch().shouldBe(visible);
    mainPage.searchButton().shouldBe(visible);
    mainPage.cartIcon().shouldBe(visible);
    mainPage.numberBasket().shouldBe(visible);
    mainPage.totalPriceInCart().shouldBe(visible);
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "TC 2.1  Checks click logo and sign in button")
  public void testClickLogoAndSignInButton(String route) {
    open(route);
    closeCookiesFooterMessage();
    // test logo
    mainPage.logoInHeader().shouldHave(not(attribute("href")));
    mainPage.clickVersand();
    mainPage.logoInHeader().shouldHave(attribute("href")).click();
    mainPage.logoInHeader().shouldHave(not(attribute("href")));
    // test login button
    mainPage.loginBtnInHeader().click();
    mainPage.loginBtnInPopUp().shouldBe(visible);
    mainPage.closeBtnOfLoginPopup().click();
    mainPage.loginBtnInPopUp().shouldBe(not(visible));
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "TC 2.2  Checks hints and info popup for search in header")
  public void testHintsAndInfoPopupForSearchInHeader(String route) {
    open(route);
    // test hints
    mainPage.searchBar().click();
    mainPage.hintsForSearch().shouldBe(visible);
    mainPage.logoInHeader().click();
    mainPage.hintsForSearch().shouldBe(not(visible));
    // test info for search
    mainPage.infoIconForSearch().click();
    mainPage.infoPopupForSearch().shouldBe(visible);
    mainPage.infoPopupForSearch().click();
    mainPage.infoPopupForSearch().shouldBe(not(visible));
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "TC 2.3  Checks catalog menu and click cart")
  public void testCatalogMenuAndClickCart(String route) {
    open(route);
    // test catalog menu
    mainPage.menuCatalogInHeader().hover();
    mainPage.listCategoriesOfCatalog().shouldBe(not(visible));
    mainPage.menuCatalogInHeader().click();
    mainPage.listCategoriesOfCatalog().shouldBe(visible);
    mainPage.logoInHeader().hover();
    mainPage.listCategoriesOfCatalog().shouldBe(not(visible));
    mainPage.menuCatalogInHeader().hover();
    mainPage.listCategoriesOfCatalog().shouldBe(visible);
    // test cart click
    mainPage.cartClick().emptyCart().shouldBe(visible);
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "TC 3.1  Checks navigate categories in header")
  public void testNavigateCategoriesInHeader(String route) throws SQLException {
    open(route);
    String shop = getCurrentShopFromJSVarInHTML();
    mainPage.clickLkwCategory();
    checkingContainsUrl(db.getRouteByRouteName(shop, "lkw_main"));
    mainPage.clickMotoCategory();
    checkingContainsUrl(db.getRouteByRouteName(shop, "moto_main"));
    mainPage.clickTiresCategory();
    checkingContainsUrl(db.getRouteByRouteName(shop, "tyres"));
    mainPage.clickInstrumentsCategory();
    checkingContainsUrl(db.getRouteByRouteName(shop, "index_instruments"));
    mainPage.clickAccessoriesCategory();
    checkingContainsUrl(db.getRouteByRouteName(shop, "index_accessories"));
    mainPage.clickEngineOilCategory();
    checkingContainsUrl(db.getRouteByRouteName(shop, "engine_oil"));
    mainPage.clickFiltersCategory();
    checkingContainsUrl(db.getRouteByRouteName(shop, "filters"));
    mainPage.clickBrakeSystemCategory();
    checkingContainsUrl(db.getRouteByRouteName(shop, "brake_system"));
    mainPage.clickEngineCategory();
    checkingContainsUrl(db.getRouteByRouteName(shop, "engine"));
  }

  @AfterTest
  private void teatDown() {
    close();
  }

}
