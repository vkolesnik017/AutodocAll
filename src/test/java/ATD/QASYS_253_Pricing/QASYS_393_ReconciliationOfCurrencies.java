package ATD.QASYS_253_Pricing;

import ATD.*;
import com.codeborne.selenide.ex.ElementIsNotClickableException;
import org.testng.annotations.*;

import java.sql.SQLException;

import static ATD.CommonMethods.getCurrencyAndVerify;
import static ATD.CommonMethods.getCurrentShopFromJSVarInHTML;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class QASYS_393_ReconciliationOfCurrencies {

  // accessory, chemistry, tool, oil, ordinary (id product)
  private String[] productsId = {"15207804"}; // "13558633" ,"7889318", "15207804", "13626328", "988236"
  private String emailForCH = "reconciliationOfCurrenciesCH@mailinator.com";
  private String emailForAnotherShop = "reconciliationOfCurrencies@mailinator.com";
  private String password = "1234";

  DataBase dataBase = new DataBase();
  Product_page productPage = new Product_page();
  Cart_page cartPage = new Cart_page();
  CartAllData_page cartAllDataPage = new CartAllData_page();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "routeAndProductsIdAllShops", parallel = true)
  Object[] dataProviderAllShops() {
    return new SetUp().setUpShopWithListParam("prod", "AT, BG, BE, CZ, DE, DK, EE, ES, FI, FR, EN, GR, HU, IT, LD, LT, LV, NL, NO, PL, PT, RO, SE, SI, SK", productsId);
  }

  @Test(dataProvider = "routeAndProductsIdAllShops")
  public void testReconciliationOfCurrencies(String routeAndProductId) throws SQLException {
    String route = routeAndProductId.split("_")[0];
    String productId = routeAndProductId.split("_")[1];
    open(route + "/a/" + productId);
    String shop = getCurrentShopFromJSVarInHTML();
    String expectedCurrency = dataBase.getCurrency(shop);
    // checks currency on product page
    getCurrencyAndVerify(productPage.priceWithoutDiscount(), "priceWithoutDiscount", shop, expectedCurrency);
    getCurrencyAndVerify(productPage.productPrice(), "productPrice", shop, expectedCurrency);
    productPage.buyButton().click();

    // checks currency in cart popup
    getCurrencyAndVerify(productPage.firstProductPriceInPopupOfCart(), "productPriceInPopupOfCart", shop, expectedCurrency);
    getCurrencyAndVerify(productPage.totalPriceInPopupOfCart(), "totalPriceInPopupOfCart", shop, expectedCurrency);
    if (productPage.closeBtnOfPopupOtherCategory().isDisplayed()) {
      productPage.closeBtnOfPopupOtherCategory().click();
    }
    // go to cart page
    productPage.cartClick();
    // checks currency on cart page
    getCurrencyAndVerify(cartPage.totalOrderPriceInHead(), "orderPriceInHead", shop, expectedCurrency);
    getCurrencyAndVerify(cartPage.priceOfAllProducts(), "priceOfAllProducts", shop, expectedCurrency);
    getCurrencyAndVerify(cartPage.totalOrderPrice(), "totalOrderPrice", shop, expectedCurrency);
    getCurrencyAndVerify(cartPage.productPrice(), "productPrice", shop, expectedCurrency);
    getCurrencyAndVerify(cartPage.totalProductPrice(), "totalProductPrice", shop, expectedCurrency);
    getCurrencyAndVerify(cartPage.priceWithoutDiscount(), "priceWithoutDiscount", shop, expectedCurrency);
    getCurrencyAndVerify(cartPage.priceWithDiscount(), "priceWithDiscount", shop, expectedCurrency);
    getCurrencyAndVerify(cartPage.discount(), "discount", shop, expectedCurrency);
    //login and go to all data page
      cartPage.nextButtonClick()
              .signIn(emailForAnotherShop, password)
              .nextBtnClick()
              .choosePayPal()
              .nextBtnClick();
    // checks currency on all data page
    getCurrencyAndVerify(cartAllDataPage.totalOrderPriceInHead(), "totalOrderPriceInHead", shop, expectedCurrency);
    getCurrencyAndVerify(cartAllDataPage.productPrice(), "productPrice", shop, expectedCurrency);
    getCurrencyAndVerify(cartAllDataPage.totalProductPrice(), "totalProductPrice", shop, expectedCurrency);
    getCurrencyAndVerify(cartAllDataPage.priceOfAllProducts(), "priceOfAllProducts", shop, expectedCurrency);
    getCurrencyAndVerify(cartAllDataPage.deliveryPrice(), "deliveryPrice", shop, expectedCurrency);
    getCurrencyAndVerify(cartAllDataPage.totalOrderPrice(), "totalOrderPrice", shop, expectedCurrency);
    //checks currency for safe order price
    if (cartAllDataPage.priceOfSafeOrder().isDisplayed()) {
      getCurrencyAndVerify(cartAllDataPage.priceOfSafeOrder(), "priceSafeOrder", shop, expectedCurrency);
      }
  }

  @DataProvider(name = "routeForTires", parallel = true)
  Object[] dataProviderForTires() {
    return new SetUp().setUpShop("prod", "AT, BG, BE, CZ, DE, DK, EE, ES, FI, FR, EN, HU, IT, LV, NL, PL, PT, RO, SE, SI, SK");
  }

  @Test(dataProvider = "routeForTires", enabled = false)
  public void testReconciliationOfCurrenciesForTires(String route) throws SQLException {
    open(route);
    String shop = getCurrentShopFromJSVarInHTML();
    String  expectedCurrency = dataBase.getCurrency(shop);
    new Main_page().clickTiresTab()
            .imagesProductsTires().click();
    // checks currency on product page
    getCurrencyAndVerify(productPage.priceWithoutDiscount(), "priceWithoutDiscount", shop, expectedCurrency);
    getCurrencyAndVerify(productPage.productPrice(), "productPrice", shop, expectedCurrency);
    // go to cart page
    productPage.buyButton().click();
    productPage.cartIcon().hover();
    getCurrencyAndVerify(productPage.firstProductPriceInPopupOfCart(), "productPriceInPopupOfCart", shop, expectedCurrency);
    getCurrencyAndVerify(productPage.totalPriceInPopupOfCart(), "totalPriceInPopupOfCart", shop, expectedCurrency);
    productPage.cartClick();
    // checks currency on cart page
    getCurrencyAndVerify(cartPage.totalOrderPriceInHead(), "orderPriceInHead", shop, expectedCurrency);
    getCurrencyAndVerify(cartPage.priceOfAllProducts(), "priceOfAllProducts", shop, expectedCurrency);
    getCurrencyAndVerify(cartPage.totalOrderPrice(), "totalOrderPrice", shop, expectedCurrency);
    getCurrencyAndVerify(cartPage.productPrice(), "productPrice", shop, expectedCurrency);
    getCurrencyAndVerify(cartPage.totalProductPrice(), "totalProductPrice", shop, expectedCurrency);
    //login and go to all data page
    cartPage.nextButtonClick()
            .signIn(emailForAnotherShop, password)
            .nextBtnClick()
            .choosePayPal()
            .nextBtnClick();
    // checks currency on all data page
    getCurrencyAndVerify(cartAllDataPage.totalOrderPriceInHead(), "totalOrderPriceInHead", shop, expectedCurrency);
    getCurrencyAndVerify(cartAllDataPage.productPrice(), "productPrice", shop, expectedCurrency);
    getCurrencyAndVerify(cartAllDataPage.totalProductPrice(), "totalProductPrice", shop, expectedCurrency);
    getCurrencyAndVerify(cartAllDataPage.priceOfAllProducts(), "priceOfAllProducts", shop, expectedCurrency);
    getCurrencyAndVerify(cartAllDataPage.deliveryPrice(), "deliveryPrice", shop, expectedCurrency);
    getCurrencyAndVerify(cartAllDataPage.totalOrderPrice(), "totalOrderPrice", shop, expectedCurrency);
  }

  @DataProvider(name = "routeAndProductsIdForCH")
  Object[] dataProviderForCH() {
    return new SetUp().setUpShopWithListParam("prod", "CH", productsId);
  }

  @Test(dataProvider = "routeAndProductsIdForCH", enabled = false)
  public void testReconciliationOfCurrenciesForCH(String routeAndProductId) throws SQLException {
    String route = routeAndProductId.split("_")[0];
    String productId = routeAndProductId.split("_")[1];
    open(route + "/a/" + productId);
    String shop = getCurrentShopFromJSVarInHTML();
    String expectedCurrency = dataBase.getCurrency(shop);
    // checks currency on product page
    getCurrencyAndVerify(productPage.productPrice(), "productPrice", shop, expectedCurrency);
    productPage.productInfoUnderPrice().shouldHave(text(expectedCurrency));
    // go to cart page
    productPage.buyButton().click();
    productPage.closeBtnOfPopupOtherCategory().click();
    productPage.cartIcon().hover();
    // checks currency in cart popup
    getCurrencyAndVerify(productPage.firstProductPriceInPopupOfCart(), "productPriceInPopupOfCart", shop, expectedCurrency);
    getCurrencyAndVerify(productPage.totalPriceInPopupOfCart(), "totalPriceInPopupOfCart", shop, expectedCurrency);
    productPage.cartClick()
            .makePriceForMiniumOrderForCH();
    // checks currency on cart page
    getCurrencyAndVerify(cartPage.totalOrderPriceInHead(), "orderPriceInHead", shop, expectedCurrency);
    getCurrencyAndVerify(cartPage.priceOfAllProducts(), "priceOfAllProducts", shop, expectedCurrency);
    getCurrencyAndVerify(cartPage.totalOrderPrice(), "totalOrderPrice", shop, expectedCurrency);
    getCurrencyAndVerify(cartPage.productPrice(), "productPrice", shop, expectedCurrency);
    getCurrencyAndVerify(cartPage.totalProductPrice(), "totalProductPrice", shop, expectedCurrency);
    //login and go to all data page
    cartPage.nextButtonClick()
            .signIn(emailForCH, password)
            .nextBtnClick()
            .choosePayPal()
            .nextBtnClick();
    // checks currency on all data page
    getCurrencyAndVerify(cartAllDataPage.totalOrderPriceInHead(), "totalOrderPriceInHead", shop, expectedCurrency);
    getCurrencyAndVerify(cartAllDataPage.productPrice(), "productPrice", shop, expectedCurrency);
    getCurrencyAndVerify(cartAllDataPage.totalProductPrice(), "totalProductPrice", shop, expectedCurrency);
    getCurrencyAndVerify(cartAllDataPage.priceOfAllProducts(), "priceOfAllProducts", shop, expectedCurrency);
    getCurrencyAndVerify(cartAllDataPage.deliveryPrice(), "deliveryPrice", shop, expectedCurrency);
    getCurrencyAndVerify(cartAllDataPage.totalOrderPrice(), "totalOrderPrice", shop, expectedCurrency);
    //check currency for VAT price
    getCurrencyAndVerify(cartAllDataPage.vatPriceInHead(), "vatPriceInHead", shop, expectedCurrency);
    getCurrencyAndVerify(cartAllDataPage.vatPriceInTotalOrder(), "vatPriceInTotalOrder", shop, expectedCurrency);
  }

  @AfterMethod
  private void tearDown() {
    close();
  }

}
