package ATD.Orders_AWS_Delivery.QC_1675_HeavyLoadsDangerousGoodsDropshippingGoods;

import ATD.CartAddress_page_Logic;
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

public class QC_1681 {

    private String email = "qc_1681autotestDE@mailinator.com";
    private Product_page_Logic product_page_logic = new Product_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "HeavyLoadProduct3");
    }

    @Test(dataProvider = "route", enabled = true)
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks negative purchase of a heavy loads in FR / AllData")
    public void testOfHeavyLoadsNegativePurchaseAllDataInFR(String route) throws SQLException {
        openPage(route);
        product_page_logic.addProductToCart();
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "product2"));
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick().nextButtonClick()
                .signIn(email, password)
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .checkPresencePopUpDeliveryLimitAllDataPage()
                .clickBtnChangeAddressInDeliveryPopupCartAllDataPage();
        checkingContainsUrl("basket/address");
        new CartAddress_page_Logic().nextBtnClick()
                .nextBtnClick()
                .closePopUpDeliveryLimitCartAllDataPage()
                .checkAbsenceGoodInCartPage("7037462")
                .checkPresenceGoodInCardPage("7807629")
                .checkPresenceSafeOrderBlock()
                .checkPresenceRegularDeliveryPrice()
                .checkAbsenceHeavyLoadsDeliveryPrice();
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "HeavyLoadProduct3"));
        product_page_logic.addProductToCart().closePopupOtherCategoryIfYes().cartClick();
        new CartAllData_page_Logic().deleteGoodsInDeliveryPopupCartAllDataPage()
                .checkAbsenceGoodInCartPage("7037462")
                .checkPresenceGoodInCardPage("7807629")
                .checkPresenceSafeOrderBlock()
                .checkPresenceRegularDeliveryPrice()
                .checkAbsenceHeavyLoadsDeliveryPrice();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
