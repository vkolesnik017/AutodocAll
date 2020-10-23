package ATD.Orders_AWS_Delivery.QC_1675_HeavyLoadsDangerousGoodsDropshippingGoods;

import ATD.CartAllData_page_Logic;
import Common.DataBase;
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

import static ATD.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1678_HeavyLoadsNegativeCasAllData {

    private String emailForFirstCase = "qc_1678_autotestFirstCase@mailinator.com";
    private String emailForSecondCase = "qc_1678_autotestSecondCase@mailinator.com";
    private Product_page_Logic product_page_logic = new Product_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routeFirstCase", parallel = true)
    Object[] dataProviderProductsFirstCase() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "HeavyLoadProduct3,productDangerousGoods1");
    }

    @Test(dataProvider = "routeFirstCase", priority = 1)
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks negative purchase of a heavy loads and dangerous goods / AllData")
    public void testOfHeavyLoadsAndDangerousGoodsAllDataPage(String routeFirstCase) throws SQLException {
        openPage(routeFirstCase);
        String idProduct = product_page_logic.getProductId();
        product_page_logic.addProductToCart();
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "product2"));
        String productIDRegularGoods = product_page_logic.getProductId();
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick().nextButtonClick()
                .signIn(emailForFirstCase, password)
                .nextBtnClick()
                .chooseVorkasse().nextBtnClick()
                .checkPresencePopUpDeliveryLimitAllDataPage()
                .closePopUpDeliveryLimitCartAllDataPage()
                .checkAbsenceGoodInCartPage(idProduct)
                .checkPresenceGoodInCardPage(productIDRegularGoods)
                .checkPresenceSafeOrderBlock()
                .checkPresenceRegularDeliveryPrice();
        openPage(routeFirstCase);
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .clickBtnChangeAddressInDeliveryPopupCartPageCartPage()
                .nextBtnClick()
                .chooseVorkasse().nextBtnClick()
                .checkPresencePopUpDeliveryLimitAllDataPage()
                .deleteGoodsInDeliveryPopupCartAllDataPage()
                .checkAbsenceGoodInCartPage(idProduct)
                .checkPresenceGoodInCardPage(productIDRegularGoods)
                .checkPresenceSafeOrderBlock()
                .checkPresenceRegularDeliveryPrice();
        openPage(routeFirstCase);
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .clickBtnChangeAddressInDeliveryPopupCartPageCartPage()
                .nextBtnClick()
                .chooseVorkasse().nextBtnClick()
                .checkPresencePopUpDeliveryLimitAllDataPage()
                .clickBtnChangeAddressInDeliveryPopupCartAllDataPage();
        checkingContainsUrl("/basket/address");
    }

    @DataProvider(name = "routeSecondCase", parallel = true)
    Object[] dataProviderProductsSecondCase() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "productDrop1,product45");
    }

    @Test(dataProvider = "routeSecondCase", priority = 2)
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks negative purchase of a drop and tyres goods / AllData")
    public void testOfDropAndTyresGoodsNegativePurchaseBasket(String routeSecondCase) throws SQLException {
        openPage(routeSecondCase);
        String idProduct = product_page_logic.getProductId();
        product_page_logic.addProductToCart();
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "product2"));
        String productIDRegularGoods = product_page_logic.getProductId();
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick().nextButtonClick()
                .signIn(emailForSecondCase, password)
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .closePopUpDeliveryLimitCartAllDataPage()
                .checkAbsenceGoodInCartPage(idProduct)
                .checkPresenceGoodInCardPage(productIDRegularGoods)
                .checkPresenceSafeOrderBlock()
                .checkPresenceRegularDeliveryPrice();
        openPage(routeSecondCase);
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick();
        new CartAllData_page_Logic().deleteGoodsInDeliveryPopupCartAllDataPage()
                .checkAbsenceGoodInCartPage(idProduct)
                .checkPresenceGoodInCardPage(productIDRegularGoods)
                .checkPresenceSafeOrderBlock()
                .checkPresenceRegularDeliveryPrice();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}