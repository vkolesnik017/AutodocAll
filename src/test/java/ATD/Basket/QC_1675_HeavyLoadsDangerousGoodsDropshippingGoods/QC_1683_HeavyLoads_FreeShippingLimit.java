package ATD.Basket.QC_1675_HeavyLoadsDangerousGoodsDropshippingGoods;

import ATD.*;
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
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class QC_1683_HeavyLoads_FreeShippingLimit {

    private String email = "qc_1683_autotestDE@mailinator.com", orderNumber;
    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private Double totalPrice, totalPriceAWSOrder;

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
    @Description(value = "Test checks free shipping limit and the purchase of a heavy load")
    public void testOfHeavyLoadsPurchaseAndFreeShippingLimit(String route) throws SQLException {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        product_page_logic.addProductToCart();
        openPage(new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "product26"));
        totalPrice = product_page_logic.checkingCounterIncrease(3)
                .addProductToCart().closePopupOtherCategoryIfYes().cartClick()
                .nextButtonClick()
                .signIn(email, password)
                .fillAllFields(shop).nextBtnClick()
                .chooseVorkasse().nextBtnClick()
                .checkPresenceFreeDeliveryPriceCartAllDataPage()
                .checkPresenceHeavyLoadsDeliveryPriceAllDataPage()
                .checkPresenceSafeOrderBlock()
                .checkingCounterDecrease(3, "13811516", "13811516")
                .checkAbsenceFreeDeliveryPriceCartAllDataPage()
                .checkRegularDeliveryPriceAllData("6,95")
                .checkHeavyLoadsDeliveryPriceAllData("10,00")
                .checkingCounterIncrease(3, "13811516", "13811516")
                .checkPresenceFreeDeliveryPriceCartAllDataPage()
                .checkHeavyLoadsDeliveryPriceAllData("10,00")
                .clickSafeOrderCheckbox()
                .checkPresenceSafeOrderPriceFromOrderSummeryBlock()
                .getTotalPriceAllDataPage();
        orderNumber = new CartAllData_page_Logic().nextBtnClick().getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumber);
        totalPriceAWSOrder = order_aws.openOrderInAwsWithLogin()
                .checkDeliveryPriceOrderAWS("2.99")
                .checkHeavyLoadsDeliveryPriceOrderAWS("10")
                .checkThatStatusSafeOrderIsOn()
                .getTotalPriceOrder();
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
        order_aws.reSaveOrder()
                .checkDeliveryPriceOrderAWS("2.99")
                .checkHeavyLoadsDeliveryPriceOrderAWS("10")
                .checkThatStatusSafeOrderIsOn();
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}