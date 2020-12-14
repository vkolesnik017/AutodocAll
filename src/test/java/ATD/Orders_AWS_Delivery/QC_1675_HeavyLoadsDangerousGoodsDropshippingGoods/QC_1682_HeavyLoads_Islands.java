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

import static ATD.CommonMethods.openPage;
import static ATD.CommonMethods.password;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1682_HeavyLoads_Islands {

    private String emailForFirstCase = "qc_1682_autotestFirstCase@mailinator.com";
    private String emailForSecondCase = "qc_1682_autotestSecondCase@mailinator.com";
    private Product_page_Logic product_page_logic = new Product_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routeFirstCase", parallel = true)
    Object[] dataProviderProductsFirstCase() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "productDangerousGoods1,HeavyLoadProduct1");
    }


    @Test(dataProvider = "routeFirstCase", priority = 1)
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks purchase of a heavy loads and dangerous goods for islands")
    public void testOfHeavyLoadsAndDangerousGoodsForIslands(String routeFirstCase) throws SQLException {
        openPage(routeFirstCase);
        String idHeavyLoadProduct = product_page_logic.getProductId();
        product_page_logic.addProductToCart();
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "product2"));
        String productID = product_page_logic.getProductId();
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(emailForFirstCase, password)
                .chooseDeliveryCountryForShipping("IT")
                .fillingPostalCodeFieldJSForShipping("90011")
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .checkPresencePopUpDeliveryLimitAllDataPage()
                .closePopUpDeliveryLimitCartAllDataPage()
                .checkAbsenceGoodInCartPage(idHeavyLoadProduct)
                .checkPresenceGoodInCardPage(productID)
                .checkPresenceSafeOrderBlock()
                .checkPresenceRegularDeliveryPrice();
        openPage(routeFirstCase);
        product_page_logic.addProductToCart().closePopupOtherCategoryIfYes().cartClick();
        new CartAllData_page_Logic().deleteGoodsInDeliveryPopupCartAllDataPage()
                .checkAbsenceGoodInCartPage(idHeavyLoadProduct)
                .checkPresenceGoodInCardPage(productID)
                .checkPresenceSafeOrderBlock()
                .checkPresenceRegularDeliveryPrice();
    }

    @DataProvider(name = "routeSecondCase", parallel = true)
    Object[] dataProviderProductsSecondCase() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "productDrop1,product45");
    }

    @Test(dataProvider = "routeSecondCase", priority = 2)
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks purchase of a drop and tyres goods for islands")
    public void testOfDropAndTyresGoodsNegativeForIslands(String routeSecondCase) throws SQLException {
        openPage(routeSecondCase);
        String idHeavyLoadProduct = product_page_logic.getProductId();
        product_page_logic.addProductToCart();
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "product2"));
        String productID = product_page_logic.getProductId();
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(emailForSecondCase, password)
                .fillingPostalCodeField("90011")
                .chooseDeliveryCountryForShipping("IT")
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .closePopUpDeliveryLimitCartAllDataPage()
                .checkAbsenceGoodInCartPage(idHeavyLoadProduct)
                .checkPresenceGoodInCardPage(productID)
                .checkPresenceSafeOrderBlock()
                .checkPresenceRegularDeliveryPrice();
        openPage(routeSecondCase);
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick();
        new CartAllData_page_Logic().deleteGoodsInDeliveryPopupCartAllDataPage()
                .checkAbsenceGoodInCartPage(idHeavyLoadProduct)
                .checkPresenceGoodInCardPage(productID)
                .checkPresenceSafeOrderBlock()
                .checkPresenceRegularDeliveryPrice();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}