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

public class QC_1677_HeavyLoadsNegativeCase {

    private String emailForFirstCase = "qc_1677_autotestFirstCase@mailinator.com";
    private String emailForSecondCase = "qc_1677_autotestSecondCase@mailinator.com";
    private Product_page_Logic product_page_logic = new Product_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routeFirstCase", parallel = true)
    Object[] dataProviderProductsFirstCase() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "HeavyLoadProduct3,productDangerousGoods1");
    }

    @Test(dataProvider = "routeFirstCase", priority = 0)
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks negative purchase of a heavy loads and dangerous goods / Basket")
    public void testOfHeavyLoadsAndDangerousGoodsNegativePurchaseBasket(String routeFirstCase) throws SQLException {
        openPage(routeFirstCase);
        String idProduct = product_page_logic.getProductId();
        product_page_logic.addProductToCart();
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "product2"));
        String productIDRegularGoods = product_page_logic.getProductId();
        product_page_logic.addProductToCart()
                          .closePopupOtherCategoryIfYes();
        new Main_page_Logic().loginFromHeader(emailForFirstCase)
                .cartClick()
                .checkPresencePopUpDeliveryLimit()
                .closePopUpDeliveryLimitCartPage()
                .checkAbsenceGoodInCartPage(idProduct)
                .checkPresenceGoodInCardPage(productIDRegularGoods);
        openPage(routeFirstCase);
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .checkPresencePopUpDeliveryLimit()
                .deleteGoodsInDeliveryPopupCartPage()
                .checkAbsenceGoodInCartPage(idProduct)
                .checkPresenceGoodInCardPage(productIDRegularGoods);
        openPage(routeFirstCase);
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .checkPresencePopUpDeliveryLimit()
                .clickBtnContinueShoppingInDeliveryPopupCartPage()
                .checkPresenceGoodInCardPage(idProduct)
                .checkPresenceGoodInCardPage(productIDRegularGoods);
    }

    @DataProvider(name = "routeSecondCase", parallel = true)
    Object[] dataProviderProductsSecondCase() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main","productDrop1,product45");
    }

    @Test(dataProvider = "routeSecondCase", priority = 1)
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks negative purchase of a drop and tyres goods / Basket")
    public void testOfDropAndTyresGoodsNegativePurchaseBasket(String routeSecondCase) throws SQLException {
        openPage(routeSecondCase);
        String idProduct = product_page_logic.getProductId();
        product_page_logic.addProductToCart();
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "product2"));
        String productIDRegularGoods = product_page_logic.getProductId();
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes();
        new Main_page_Logic().loginFromHeader(emailForSecondCase)
                .cartClick()
                .closePopUpDeliveryLimitCartPage()
                .checkAbsenceGoodInCartPage(idProduct)
                .checkPresenceGoodInCardPage(productIDRegularGoods);
        openPage(routeSecondCase);
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .clickBtnDeletedGoodsViaDeliveryPopup()
                .checkAbsenceGoodInCartPage(idProduct)
                .checkPresenceGoodInCardPage(productIDRegularGoods);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}