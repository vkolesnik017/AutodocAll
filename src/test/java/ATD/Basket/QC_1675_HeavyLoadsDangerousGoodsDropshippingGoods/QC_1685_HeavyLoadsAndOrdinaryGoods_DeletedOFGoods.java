package ATD.Basket.QC_1675_HeavyLoadsDangerousGoodsDropshippingGoods;

import ATD.CartAllData_page_Logic;
import ATD.DataBase;
import ATD.Product_page_Logic;
import ATD.SetUp;
import AWS.Order_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1685_HeavyLoadsAndOrdinaryGoods_DeletedOFGoods {

    private String email = "qc_1685_autotestDE@mailinator.com", orderNumber;
    private Float totalPrice, totalPriceAWSOrder;
    private Product_page_Logic product_page_logic = new Product_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "HeavyLoasdProduct1");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the purchase of a heavy load and ordinary goods")
    public void testOfHeavyLoadsPurchaseAndOrdinaryGoods(String route) throws SQLException {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        product_page_logic.addProductToCart();
        openPage(new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "product2"));
        totalPrice = product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick().nextButtonClick()
                .signIn(email, password)
                .fillAllFields(shop).nextBtnClick()
                .chooseVorkasse().nextBtnClick()
                .checkRegularDeliveryPriceAllData("6,95")
                .checkHeavyLoadsDeliveryPriceAllData("10,00")
                .checkPresenceSafeOrderBlock()
                .clickSafeOrderCheckbox()
                .deleteGoodFromCartAllDataPage("7807629")
                .clickBtnConfirmProductDelete()
                .checkAbsenceSafeOrderBlock()
                .checkAbsenceSafeOrderPriceFromOrderSummeryBlock()
                .getTotalPriceAllDataPage();
        orderNumber = new CartAllData_page_Logic().nextBtnClick().getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumber);
        totalPriceAWSOrder = order_aws.openOrderInAwsWithLogin()
                .checkDeliveryPriceOrderAWS("6.95")
                .checkHeavyLoadsDeliveryPriceOrderAWS("10")
                .checkThatStatusSafeOrderIsOff()
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
        order_aws.reSaveOrder()
                .checkThatStatusSafeOrderIsOff()
                .checkDeliveryPriceOrderAWS("6.95")
                .checkHeavyLoadsDeliveryPriceOrderAWS("10");
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}