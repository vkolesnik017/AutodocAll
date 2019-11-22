package ATD.QASYS_19_Basket;

import ATD.CartAddress_page;
import ATD.CartAllData_page;
import ATD.Product_page;
import ATD.SetUp;
import AWS.Login_aws;
import AWS.ProductSearch_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.usualIdProduct;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.assertEquals;

public class QASYS_455_DeletingOfDangerousProductFromBasket {

  private String[] deliveryCountries = {"GR", "BG", "CZ", "EE", "HU", "IE", "LT", "LV", "NO", "PL", "SI"};

  private String email = "DeletingOfDangerousProductFromBasket@mailinator.com";
  private String password = "1234";
  private String idDangerousProduct;

  Login_aws login_aws = new Login_aws();
  CartAllData_page cartAllDataPage = new CartAllData_page();
  ProductSearch_aws productSearch_aws = new ProductSearch_aws();
  Product_page productPage = new Product_page();
  CartAddress_page cartAddressPage = new CartAddress_page();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
    login_aws.loginInAws();
    open(productSearch_aws.urlPage);
    idDangerousProduct = productSearch_aws.chooseFilterForDangerousProductsAndGetId();
    close();
  }

  @DataProvider(name = "route", parallel = true)
  Object[] dataProvider() {
    return new SetUp().setUpShopWithListParam("prod", "DE", deliveryCountries);
  }

  @Owner(value = "Evlentiev")
  @Test(dataProvider = "route")
  @Flaky
  @Description(value = "Deleting of dangerous product from basket when delivery to countries where it cannot be delivered")
  public void testDeletingOfDangerousProductFromBasket(String routeAndDeliveryCountry) {
    String route = routeAndDeliveryCountry.split("_")[0];
    String deliveryCountry = routeAndDeliveryCountry.split("_")[1];
    productPage.openProductPageById(route, usualIdProduct)
            .addProductToCart()
            .openProductPageById(route, idDangerousProduct)
            .addProductToCart()
            .closePopupOtherCategoryIfYes()
            .cartClick()
            .nextButtonClick()
            .signIn(email, password)
            .chooseDeliveryCountry(deliveryCountry)
            .fillInPostalCode("11111")
            .nextBtnClick()
            .nextBtnClick();
    // check clicks on out of Popup with dangerous product and check deletion product
    cartAllDataPage.searchProductByID(idDangerousProduct).shouldBe(visible);
    cartAllDataPage.popupOfDangerousProduct().shouldBe(visible);
    cartAllDataPage.areaOutOfPopup().click(1, 1);
    cartAllDataPage.searchProductByID(idDangerousProduct).shouldBe(not(visible));
    // check clicks on close button in Popup with dangerous product0
    productPage.openProductPageById(route, idDangerousProduct)
            .addProductToCart()
            .cartClick();
    cartAllDataPage.searchProductByID(idDangerousProduct).shouldBe(visible);
    cartAllDataPage.popupOfDangerousProduct().shouldBe(visible);
    cartAllDataPage.closePopupBtn().click();
    cartAllDataPage.searchProductByID(idDangerousProduct).shouldBe(not(visible));
    // check clicks on delete product in Popup with dangerous product and check deletion product
    productPage.openProductPageById(route, idDangerousProduct)
            .addProductToCart()
            .cartClick();
    cartAllDataPage.searchProductByID(idDangerousProduct).shouldBe(visible);
    cartAllDataPage.popupOfDangerousProduct().shouldBe(visible);
    cartAllDataPage.deleteProductBtnInPopup().click();
    cartAllDataPage.searchProductByID(idDangerousProduct).shouldBe(not(visible));
    // check clicks on change address button in Popup with dangerous product and check redirect on cart/address
    productPage.openProductPageById(route, idDangerousProduct)
            .addProductToCart()
            .cartClick();
    cartAllDataPage.searchProductByID(idDangerousProduct).shouldBe(visible);
    cartAllDataPage.popupOfDangerousProduct().shouldBe(visible);
    cartAllDataPage.changeAddressBtnInPopup().click();
    cartAddressPage.nextButton().shouldBe(visible);
    String dataCode = cartAddressPage.currentCountryInSelector().attr("data-code");
    assertEquals(dataCode, deliveryCountry);
  }

  @AfterMethod
  private void tearDown() {
    close();
  }
}
