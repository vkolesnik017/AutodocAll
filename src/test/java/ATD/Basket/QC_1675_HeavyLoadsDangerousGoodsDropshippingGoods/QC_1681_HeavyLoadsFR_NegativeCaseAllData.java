package ATD.Basket.QC_1675_HeavyLoadsDangerousGoodsDropshippingGoods;

import ATD.CartAllData_page_Logic;
import ATD.DataBase;
import ATD.Product_page_Logic;
import ATD.SetUp;
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
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1681_HeavyLoadsFR_NegativeCaseAllData {

    private String email = "qc_1681autotestDE@mailinator.com";
    private Product_page_Logic product_page_logic = new Product_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "HeavyLoadProduct1");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks negative purchase of a heavy loads in FR / AllData")
    public void testOfHeavyLoadsNegativePurchaseAllDataInFR(String route) throws SQLException {
        openPage(route);
        product_page_logic.addProductToCart();
        openPage(new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "product2"));
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick().nextButtonClick()
                .signIn(email, password)
                .nextBtnClick()
                .chooseVorkasse().nextBtnClick()
                .checkPresencePopUpCountryDeliveryLimit()
                .checkAbsenceBtnChangeAddressInDeliveryPopup()
                .closePopUpDeliveryLimitCartAllDataPage()
                .checkAbsenceGoodInCartPage("1290766")
                .checkPresenceGoodInCardPage("7807629")
                .checkPresenceSafeOrderBlock()
                .checkPresenceRegularDeliveryPrice()
                .checkAbsenceHeavyLoadsDeliveryPrice();
        openPage(new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "HeavyLoasdProduct1"));
        product_page_logic.addProductToCart().closePopupOtherCategoryIfYes().cartClick();
        new CartAllData_page_Logic().deleteGoodsInDeliveryPopupCartAllDataPage()
                .checkAbsenceGoodInCartPage("1290766")
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
