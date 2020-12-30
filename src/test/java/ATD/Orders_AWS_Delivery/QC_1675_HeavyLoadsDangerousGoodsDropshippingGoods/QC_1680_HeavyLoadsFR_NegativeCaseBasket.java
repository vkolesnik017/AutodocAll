package ATD.Orders_AWS_Delivery.QC_1675_HeavyLoadsDangerousGoodsDropshippingGoods;

import Common.DataBase;
import ATD.Main_page_Logic;
import ATD.Product_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1680_HeavyLoadsFR_NegativeCaseBasket {

    private String email = "qc_1680_autotestDE@mailinator.com";
    private Product_page_Logic product_page_logic = new Product_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "HeavyLoadProduct1");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks negative purchase of a heavy loads in FR / Basket")
    public void testOfHeavyLoadsNegativePurchaseBasketInFR(String route) throws SQLException {
        openPage(route);
        product_page_logic.addProductToCart();
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "product2"));
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes();
        new Main_page_Logic().loginFromHeader(email)
                .cartClick()
                .checkAbsenceBtnContinueShoppingInDeliveryPopupCartPage()
                .closePopUpDeliveryLimitCartPage()
                .checkAbsenceGoodInCartPage("7037462")
                .checkPresenceGoodInCardPage("7807629");
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "HeavyLoadProduct1"));
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .deleteGoodsInDeliveryPopupCartPage()
                .checkAbsenceGoodInCartPage("7037462")
                .checkPresenceGoodInCardPage("7807629");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}