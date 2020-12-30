package ATD.QASYS_19_Basket;

import ATD.*;
import AWS.Login_aws;
import AWS.ProductSearch_aws;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.openPage;
import static ATD.CommonMethods.usualIdProduct;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;

public class QASYS_455_DeletingOfDangerousProductFromBasket {

    private String[] deliveryCountries = {"GR", "BG", "CZ", "EE", "HU", "IE", "LT", "LV", "NO", "PL", "SI"};

    private String email = "DeletingOfDangerousProductFromBasket@mailinator.com";
    private String password = "1234";
    private String idDangerousProduct;

    private Login_aws login_aws = new Login_aws();
    private CartAllData_page cartAllDataPage = new CartAllData_page();
    private ProductSearch_aws productSearch_aws = new ProductSearch_aws();
    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private CartAddress_page cartAddressPage = new CartAddress_page();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
        login_aws.loginInAwsWithOpen();
        open(productSearch_aws.urlPage);
        idDangerousProduct = productSearch_aws.chooseFilterForDangerousProductsAndGetId();
        close();
    }

    @DataProvider(name = "route", parallel = false)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShopWithListParam("prod", "DE", deliveryCountries);
    }

    @Owner(value = "Evlentiev")
    @Test(dataProvider = "route")
    @Flaky
    @Description(value = "Deleting of dangerous product from basket when delivery to countries where it cannot be delivered")
    public void testDeletingOfDangerousProductFromBasket(String routeAndDeliveryCountry) {
        String route = routeAndDeliveryCountry.split("_")[0];
        String deliveryCountry = routeAndDeliveryCountry.split("_")[1];
        openPage(route);
        product_page_logic.openProductPageById(route, usualIdProduct)
                .addProductToCart()
                .checkQuantityOnBasketIconEquals(1)
                .openProductPageById(route, idDangerousProduct)
                .addProductToCart()
                .checkQuantityOnBasketIconEquals(2)
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(email, password)
                .chooseDeliveryCountryForShipping(deliveryCountry)
                .fillInPostalCode("11111")
                .nextBtnClick()
                .nextBtnClick();
        // check clicks on out of Popup with dangerous product and check deletion product
        cartAllDataPage.searchProductByID(idDangerousProduct).shouldBe(visible);
        cartAllDataPage.popupOfDangerousProduct().shouldBe(visible);
        cartAllDataPage.areaOutOfPopup().click(5, 1);
        cartAllDataPage.searchProductByID(idDangerousProduct).shouldBe(not(visible));
        // check clicks on close button in Popup with dangerous product0
        product_page_logic.openProductPageById(route, idDangerousProduct)
                .addProductToCart()
                .checkQuantityOnBasketIconEquals(2)
                .cartClick();
        cartAllDataPage.searchProductByID(idDangerousProduct).shouldBe(visible);
        cartAllDataPage.popupOfDangerousProduct().shouldBe(visible);
        cartAllDataPage.closePopupBtn().click();
        cartAllDataPage.searchProductByID(idDangerousProduct).shouldBe(not(visible));
        // check clicks on delete product in Popup with dangerous product and check deletion product
        product_page_logic.openProductPageById(route, idDangerousProduct)
                .addProductToCart()
                .checkQuantityOnBasketIconEquals(2)
                .cartClick();
        cartAllDataPage.searchProductByID(idDangerousProduct).shouldBe(visible);
        cartAllDataPage.popupOfDangerousProduct().shouldBe(visible);
        cartAllDataPage.deleteProductBtnInPopup().click();
        cartAllDataPage.searchProductByID(idDangerousProduct).shouldBe(not(visible));
        // check clicks on change address button in Popup with dangerous product and check redirect on cart/address
        product_page_logic.openProductPageById(route, idDangerousProduct)
                .addProductToCart()
                .checkQuantityOnBasketIconEquals(2)
                .cartClick();
        cartAllDataPage.searchProductByID(idDangerousProduct).shouldBe(visible);
        cartAllDataPage.popupOfDangerousProduct().shouldBe(visible);
        cartAllDataPage.changeAddressBtnInPopup().click();
        cartAddressPage.nextButton().shouldBe(visible);
        String dataCode = cartAddressPage.currentCountryInSelector().attr("data-code");
        assertEquals(dataCode, deliveryCountry);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
