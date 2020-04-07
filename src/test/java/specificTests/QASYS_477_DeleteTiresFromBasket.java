package specificTests;

import ATD.*;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static ATD.CommonMethods.getCurrentShopFromJSVarInHTML;
import static ATD.CommonMethods.idProductTire;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class QASYS_477_DeleteTiresFromBasket {

  private String rangeOfPostalCode = System.getenv("rangeOfPostalCode");

  private String email = "QASYS477ONE2@mailinator.com";
  private String password = "123456";

  private List<String> listZipCodes = new ArrayList<>();

  private Product_page_Logic product_page_logic = new Product_page_Logic();
  private CartAllData_page cartAllData_page = new CartAllData_page();
  private Cart_page cart_page = new Cart_page();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
    generatePostalCodes(rangeOfPostalCode);
  }

  @DataProvider(name = "routeAndPostalCodes", parallel = true)
  Object[] dataProvider() {
    String[] zipCodes = listZipCodes.toArray(new String[listZipCodes.size()]);
    return new SetUp().setUpShopWithListParam("prod", "SE", zipCodes);
  }

  @Owner(value = "Evlentiev")
  @Test(dataProvider = "routeAndPostalCodes")
  @Description(value = "Удаление шин из корзины при доставки на остров (SITES-6017)")
  public void testDeleteTiresFromBasket(String routeAndPostalCode) {
    String route = routeAndPostalCode.split("_")[0];
    String postalCode = routeAndPostalCode.split("_")[1];
    product_page_logic.openProductPageById(route, idProductTire);
    String currentShop = getCurrentShopFromJSVarInHTML();
    product_page_logic.addProductToCart()
            .cartClick()
            .nextButtonClick()
            .signIn(email, password)
            .chooseDeliveryCountryForShipping(currentShop)
            .fillInPostalCode(postalCode)
            .nextBtnClick()
            .nextBtnClick();
    cartAllData_page.searchProductByID(idProductTire).shouldBe((visible));
    cartAllData_page.popupOfDangerousProduct().shouldBe(visible);
    cartAllData_page.deleteProductBtnInPopup().click();
    cart_page.emptyCart().shouldBe(visible);
  }

  @Step
  private void generatePostalCodes(String rangeOfPostalCode) {
    if (rangeOfPostalCode.contains("-")) {
      int start = Integer.parseInt(rangeOfPostalCode.split("-")[0]);
      int end = Integer.parseInt(rangeOfPostalCode.substring(rangeOfPostalCode.lastIndexOf("-") + 1));
      for (int code = start; code <= end; code++) {
        listZipCodes.add(String.valueOf(code));
      }
    }
  }

  @AfterMethod
  private void tearDown() {
    close();
  }

}
