package ATD.QASYS_568_AdvantagesPfands;

import ATD.*;
import AWS.Order_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.getPriceFromElement;
import static ATD.CommonMethods.password;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class QASYS_569_AdvantagesPfands {

  private Product_page productPage = new Product_page();
  private Listing_page listingPage = new Listing_page();
  private Cart_page cartPage = new Cart_page();
  private CartAllData_page cartAllDataPage = new CartAllData_page();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "products", parallel = true)
  Object[] dataProvider() throws SQLException {
    return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "product8,product9,product10");
  }

  @Test(dataProvider = "products")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-1. Checks availability pfand on product page")
  public void testAvailabilityPfandOnProductPage(String route) {
    open(route);
    productPage.pfandBlock().shouldBe(visible);
  }

  @DataProvider(name = "oen", parallel = true)
  Object[] dataProvider2() throws SQLException {
    return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_oen6,category_oen7,category_oen8");
  }

  @Test(dataProvider = "oen")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-2. Checks availability pfand on oen listing")
  public void testAvailabilityPfandOnOenListing(String route) {
    open(route);
    new Listing_page().pfandBlock().shouldBe(visible);
  }

  @DataProvider(name = "search", parallel = true)
  Object[] dataProvider3() throws SQLException {
    return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "search12,search13,search14");
  }

  @Test(dataProvider = "search")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-3. Checks availability pfand on search listing")
  public void testAvailabilityPfandOnSearchListing(String route) {
    open(route);
    listingPage.pfandBlock().shouldBe(visible);
  }

  @DataProvider(name = "tecdoc", parallel = true)
  Object[] dataProvider4() throws SQLException {
    return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_car_list14,category_car_list15,category_car_list16");
  }

  @Test(dataProvider = "tecdoc")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-4. Checks availability pfand on tecdoc listing")
  public void testAvailabilityPfandOnTecDocListing(String route) {
    open(route);
    listingPage.pfandBlock().shouldBe(visible);
  }

  @DataProvider(name = "product", parallel = true)
  Object[] dataProvider5() throws SQLException {
    return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "product8");
  }

  @Test(dataProvider = "product")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-5. Checks availability pfand on tecdoc listing")
  public void testAvailabilityPfandInCartAndInOrderInAws(String route) {
    String testMail = "atdautotest_qasys_569_advantagespfands@mailinator.com";
    open(route);
    productPage.pfandBlock().shouldBe(visible);
    productPage.addProductToCart()
            .closePopupOtherCategoryIfYes()
            .cartClick();
    String pfandPrice = cartPage.pfandPriceInProductBlock().getText();
    cartPage.pfandPriceInTotalPriceBlock().shouldHave(exactText(pfandPrice));
    cartPage.nextButtonClick()
            .signIn(testMail, password)
            .nextBtnClick()
            .chooseVorkasse()
            .nextBtnClick()
            .uncoverPriceInHead().click();
    cartAllDataPage.pfandPriceInHead().shouldHave(exactText(pfandPrice));
    cartAllDataPage.pfandPriceInProductBlock().shouldHave(exactText(pfandPrice));
    cartAllDataPage.pfandPriceInTotalPriceBlock().shouldHave(exactText(pfandPrice));
    pfandPrice = String.valueOf(getPriceFromElement(cartAllDataPage.pfandPriceInProductBlock()));
    String idOrder = cartAllDataPage.nextBtnClick()
            .closePopupAfterOrder()
            .getOrderNumber();
    Order_aws orderAws = new Order_aws(idOrder);
    orderAws.openOrderInAwsWithLogin()
            .checkOrderHasTestStatus()
            .checkOrderHasTestPhone()
            .checkOrderHasExpectedPfandPrice(pfandPrice);
  }

  @AfterMethod
  private void tearDown() {
    close();
  }


}
