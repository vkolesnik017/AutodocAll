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

public class QC_1681_HeavyLoadsFR_NegativeCaseAllData {

    private String email = "qc_1681autotestDE@mailinator.com";

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
    @Description(value = "Test checks negative purchase of a heavy loads in FR / AllData")
    public void testOfHeavyLoadsNegativePurchaseAllDataInFR (String route) throws SQLException {
        openPage(route);
        product_page_logic.addProductToCart();
        openPage("https://autodoc.de/" + new DataBase().getRouteByRouteName("DE", "search3"));
        clickOfBuyBtnForAllPages();
        new Search_page_Logic().closePopupOtherCategoryIfYes()
                .cartClick().nextButtonClick()
                .signIn(email, password).nextBtnClick()
                .chooseVorkasse().nextBtnClick()
                .checkPresencePopUpCountryDeliveryLimit()
                .checkAbsenceBtnChangeAddressInDeliveryPopup()
                .closePopUpDeliveryLimit()
                .checkAbsenceGoodsInCartPage("7037462")
                .checkPresenceGoodsInCardPage("1187466")
                .checkPresenceSafeOrderBlock()
                .checkPresenceRegularDeliveryPrice()
                .checkAbsenceHeavyLoadsDeliveryPrice();
        openPage(route);
        product_page_logic.addProductToCart().closePopupOtherCategoryIfYes().cartClick();
        new CartAllData_page_Logic().deleteGoodsInDeliveryPopupCartAllDataPage()
                .checkAbsenceGoodsInCartPage("7037462")
                .checkPresenceGoodsInCardPage("1187466")
                .checkPresenceSafeOrderBlock()
                .checkPresenceRegularDeliveryPrice()
                .checkAbsenceHeavyLoadsDeliveryPrice();
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
