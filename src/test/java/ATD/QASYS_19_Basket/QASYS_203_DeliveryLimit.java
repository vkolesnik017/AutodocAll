package ATD.QASYS_19_Basket;

import ATD.*;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.*;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.close;

public class QASYS_203_DeliveryLimit {

  private String email = "deliveryLimitTestQASYS203@mailinator.com";
  private String password = "1234";

  private Main_page main_page = new Main_page();
  private Product_page product_page = new Product_page();
  private Cart_page cart_page = new Cart_page();
  private CartAllData_page cartAllDataPage = new CartAllData_page();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "route", parallel = true)
  Object[] dataProvider() {
    return new SetUp().setUpShop("prod", "AT, BG, BE, CH, CZ, DE, DK, EE, ES, FI, FR, EN, GR, HU, IT, LD, LT, LV, NL, NO, PL, PT, RO, SE, SI, SK");
  }

  @Owner(value = "Evlentiev")
  @Test(dataProvider = "route")
  @Flaky
  @Description(value = "TC01 Delivery limit in basket")
  public void testDeliveryLimitInBasket(String homepage) {
    openPage(homepage);
    Float deliveryLimit = main_page.clickVersand().getDeliveryLimitFromText();
    product_page.openProductPageById(homepage, idProductMore35EUR)
            .addProductToCart()
            .closePopupOtherCategoryIfYes()
            .cartClick()
            .freeDeliveryIcon().shouldBe(not(visible));
    cart_page.makeAndCheckLimitPriceForFreeDelivery(deliveryLimit);
  }

  @Owner(value = "Evlentiev")
  @Test(dataProvider = "route")
  @Flaky
  @Description(value = "TC02 Delivery limit in alldata")
  public void testDeliveryLimitInAllData(String homepage) {
    openPage(homepage);
    String currentShop = getCurrentShopFromJSVarInHTML();
    Float deliveryLimit = main_page.clickVersand().getDeliveryLimitFromText();
    product_page.openProductPageById(homepage, idProductMore35EUR)
            .addProductToCart()
            .closePopupOtherCategoryIfYes()
            .cartClick()
            .nextButtonClick()
            .signIn(email, password)
            .chooseDeliveryCountry(currentShop)
            .fillInPostalCode("default")
            .fillInFiscalCode()
            .nextBtnClick()
            .nextBtnClick()
            .freeDeliveryIcon().shouldBe(not(visible));
    cartAllDataPage.makeAndCheckLimitPriceForFreeDelivery(deliveryLimit);
  }

  @AfterMethod
  private void tearDown() {
    close();
  }

}
