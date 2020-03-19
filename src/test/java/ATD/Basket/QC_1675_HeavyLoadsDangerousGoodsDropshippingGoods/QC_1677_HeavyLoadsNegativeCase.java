package ATD.Basket.QC_1675_HeavyLoadsDangerousGoodsDropshippingGoods;

import ATD.*;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.clickOfBuyBtnForAllPages;
import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;

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
        openPage("https://autodoc.de/" + new DataBase().getRouteByRouteName("DE", "search3"));
        clickOfBuyBtnForAllPages();
        new Search_page_Logic().closePopupOtherCategoryIfYes();
        new Main_page_Logic().loginFromHeader(email)
                .cartClick()
                .closePopUpDeliveryLimitCartPage()
                .checkAbsenceGoodsInCartPage("7037462")
                .checkPresenceGoodsInCardPage("1187466");
        openPage(route);
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .deleteGoodsInDeliveryPopupCartPage()
                .checkAbsenceGoodsInCartPage("7037462")
                .checkPresenceGoodsInCardPage("1187466");
        openPage(route);
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .clickBtnContinueShoppingInDeliveryPopupCartPage()
                .checkPresenceGoodsInCardPage("7037462")
                .checkPresenceGoodsInCardPage("1187466");
    }
}