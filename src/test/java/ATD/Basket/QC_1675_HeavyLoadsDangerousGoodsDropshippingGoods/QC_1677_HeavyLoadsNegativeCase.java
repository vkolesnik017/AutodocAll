package ATD.Basket.QC_1675_HeavyLoadsDangerousGoodsDropshippingGoods;

import ATD.*;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_1677_HeavyLoadsNegativeCase {

    private String email = "qc_1677_autotestDE@mailinator.com";
    private Product_page_Logic product_page_logic = new Product_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "product19");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks negative purchase of a heavy loads / Basket")
    public void testOfHeavyLoadsNegativePurchaseBasket(String route) throws SQLException {
        openPage(route);
        product_page_logic.addProductToCart();
        openPage("https://autodoc.de/" + new DataBase().getRouteByRouteName("DE", "product2"));
        product_page_logic.addProductToCart()
                          .closePopupOtherCategoryIfYes();
        new Main_page_Logic().loginFromHeader(email)
                .cartClick()
                .checkPresencePopUpDeliveryLimit()
                .closePopUpDeliveryLimitCartPage()
                .checkAbsenceGoodInCartPage("7037462")
                .checkPresenceGoodInCardPage("7807629");
        openPage(route);
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .checkPresencePopUpDeliveryLimit()
                .deleteGoodsInDeliveryPopupCartPage()
                .checkAbsenceGoodInCartPage("7037462")
                .checkPresenceGoodInCardPage("7807629");
        openPage(route);
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .checkPresencePopUpDeliveryLimit()
                .clickBtnContinueShoppingInDeliveryPopupCartPage()
                .checkPresenceGoodInCardPage("7037462")
                .checkPresenceGoodInCardPage("7807629");
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}