package ATD.Orders_AWS_Delivery.QC_1675_HeavyLoadsDangerousGoodsDropshippingGoods;

import ATD.CartAllData_page_Logic;
import ATD.Product_page_Logic;
import ATD.Versand_static_page_Logic;
import Common.SetUp;
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
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1679_HeavyLoadsFR_PositiveCase {

    private String email = "qc_1679autotestDE@mailinator.com";
    private Float totalPrice;
    private Float totalPriceAWSOrder;
    private String orderNumber;
    private String deliveryPrice;

    @BeforeClass
    void setUp() throws Exception {
        setUpBrowser(false, "chrome", "77.0");
        deliveryPrice = new Versand_static_page_Logic().getDeliveryPrice("Frankreich");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "HeavyLoadProduct5");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the purchase of a heavy load in FR")
    public void testOfHeavyLoadsPurchaseInFR(String route) {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        totalPrice = new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(email, password)
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .checkRegularDeliveryPrice(deliveryPrice)
                .checkHeavyLoadsDeliveryPriceAllData("36,95")
                .checkAbsenceSafeOrderBlock()
                .getTotalPriceAllDataPage(shop);
        orderNumber = new CartAllData_page_Logic().nextBtnClick()
                .getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumber);
        totalPriceAWSOrder = order_aws.openOrderInAwsWithLogin()
                .checkDeliveryPriceOrderAWS(deliveryPrice)
                .checkHeavyLoadsDeliveryPriceOrderAWS("36.95")
                .checkThatStatusSafeOrderIsOff()
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
        order_aws.reSaveOrder()
                .checkThatStatusSafeOrderIsOff()
                .checkDeliveryPriceOrderAWS(deliveryPrice)
                .checkHeavyLoadsDeliveryPriceOrderAWS("36.95");
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}