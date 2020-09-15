package ATD.Basket.QC_1675_HeavyLoadsDangerousGoodsDropshippingGoods;

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

public class QC_1677_HeavyLoadsNegativeCase {

    private String email = "qc_1677_autotestDE@mailinator.com";
    private Product_page_Logic product_page_logic = new Product_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "HeavyLoadProduct3");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks negative purchase of a heavy loads / Basket")
    public void testOfHeavyLoadsNegativePurchaseBasket(String route) throws SQLException {
        openPage(route);
        String idHeavyLoadProduct = product_page_logic.getProductId();
        product_page_logic.addProductToCart();
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "product2"));
        String productID = product_page_logic.getProductId();
        product_page_logic.addProductToCart()
                          .closePopupOtherCategoryIfYes();
        new Main_page_Logic().loginFromHeader(email)
                .cartClick()
                .checkPresencePopUpDeliveryLimit()
                .closePopUpDeliveryLimitCartPage()
                .checkAbsenceGoodInCartPage(idHeavyLoadProduct)
                .checkPresenceGoodInCardPage(productID);
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "HeavyLoadProduct3"));
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .checkPresencePopUpDeliveryLimit()
                .deleteGoodsInDeliveryPopupCartPage()
                .checkAbsenceGoodInCartPage(idHeavyLoadProduct)
                .checkPresenceGoodInCardPage(productID);
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "HeavyLoadProduct3"));
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .checkPresencePopUpDeliveryLimit()
                .clickBtnContinueShoppingInDeliveryPopupCartPage()
                .checkPresenceGoodInCardPage(idHeavyLoadProduct)
                .checkPresenceGoodInCardPage(productID);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}