package ATD.Orders_AWS_Delivery.QC_1675_HeavyLoadsDangerousGoodsDropshippingGoods;

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

    private String email = "qc_1678_autotestDE@mailinator.com";
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
    @Description(value = "Test checks negative purchase of a heavy loads / AllData")
    public void testOfHeavyLoadsNegativePurchaseAllDataPage(String route) throws SQLException {
        openPage(route);
        String idHeavyLoadProduct = product_page_logic.getProductId();
        product_page_logic.addProductToCart();
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "product2"));
        String productID = product_page_logic.getProductId();
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick().nextButtonClick()
                .signIn(email, password).nextBtnClick()
                .chooseVorkasse().nextBtnClick()
                .checkPresencePopUpDeliveryLimitAllDataPage()
                .closePopUpDeliveryLimitCartAllDataPage()
                .checkAbsenceGoodInCartPage(idHeavyLoadProduct)
                .checkPresenceGoodInCardPage(productID)
                .checkPresenceSafeOrderBlock()
                .checkPresenceRegularDeliveryPrice();
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "HeavyLoadProduct3"));
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .clickBtnChangeAddressInDeliveryPopupCartPageCartPage()
                .nextBtnClick()
                .chooseVorkasse().nextBtnClick()
                .checkPresencePopUpDeliveryLimitAllDataPage()
                .deleteGoodsInDeliveryPopupCartAllDataPage()
                .checkAbsenceGoodInCartPage(idHeavyLoadProduct)
                .checkPresenceGoodInCardPage(productID)
                .checkPresenceSafeOrderBlock()
                .checkPresenceRegularDeliveryPrice();
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "HeavyLoadProduct3"));
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

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}