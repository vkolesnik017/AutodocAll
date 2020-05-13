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

import static ATD.CommonMethods.*;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class QC_1678_HeavyLoadsNegativeCasAllData {

    private String email = "qc_1678_autotestDE@mailinator.com";
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
    @Description(value = "Test checks negative purchase of a heavy loads / AllData")
    public void testOfHeavyLoadsNegativePurchaseAllDataPage(String route) throws SQLException {
        openPage(route);
        product_page_logic.addProductToCart();
        openPage(new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "product2"));
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick().nextButtonClick()
                .signIn(email, password).nextBtnClick()
                .chooseVorkasse().nextBtnClick()
                .checkPresencePopUpDeliveryLimitAllDataPage()
                .closePopUpDeliveryLimitCartAllDataPage()
                .checkAbsenceGoodInCartPage("7037462")
                .checkPresenceGoodInCardPage("7807629")
                .checkPresenceSafeOrderBlock()
                .checkPresenceRegularDeliveryPrice();
        openPage(route);
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .clickBtnChangeAddressInDeliveryPopupCartPageCartPage()
                .nextBtnClick()
                .chooseVorkasse().nextBtnClick()
                .checkPresencePopUpDeliveryLimitAllDataPage()
                .deleteGoodsInDeliveryPopupCartAllDataPage()
                .checkAbsenceGoodInCartPage("7037462")
                .checkPresenceGoodInCardPage("7807629")
                .checkPresenceSafeOrderBlock()
                .checkPresenceRegularDeliveryPrice();
        openPage(route);
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
    private void tearDown() {
        close();
    }
}