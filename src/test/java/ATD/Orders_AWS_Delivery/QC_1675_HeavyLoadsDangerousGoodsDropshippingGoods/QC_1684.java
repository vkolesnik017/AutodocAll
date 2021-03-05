package ATD.Orders_AWS_Delivery.QC_1675_HeavyLoadsDangerousGoodsDropshippingGoods;

import ATD.*;
import AWS.Order_aws;
import Common.DataBase;
import Common.SetUp;
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

public class QC_1684 {


    private String email = "qc_1684_autotestDE@mailinator.com", orderNumber;
    private Float totalPrice, totalPriceAWSOrder;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "HeavyLoadProduct2");
    }

    @Test(dataProvider = "route", enabled = false)
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the purchase of a heavy load and ordinary goods")
    public void testOfHeavyLoadsPurchaseAndOrdinaryGoods(String route) throws SQLException {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        new Product_page_Logic().addProductToCart();
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "search3"));
        clickOfBuyBtnForAllPages();
        totalPrice = new Search_page_Logic().closePopupOtherCategoryIfYes()
                .cartClick().nextButtonClick()
                .signIn(email, password)
                .fillAllFieldsForShipping(shop).nextBtnClick()
                .chooseVorkasse().nextBtnClick()
                .checkRegularDeliveryPrice("6,95")
                .checkHeavyLoadsDeliveryPriceAllData("10,00")
                .checkPresenceSafeOrderBlock()
                .clickSafeOrderCheckbox()
                .checkPresenceSOPriceFromOrderSummeryBlock()
                .getTotalPriceAllDataPage(shop);
        orderNumber = new CartAllData_page_Logic().nextBtnClick().getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumber);
        totalPriceAWSOrder = order_aws.openOrderInAwsWithLogin()
                .checkDeliveryPriceOrderAWS("9.94")
                .checkHeavyLoadsDeliveryPriceOrderAWS("10")
                .checkThatStatusSafeOrderIsOn()
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
        order_aws.reSaveOrder()
                .checkThatStatusSafeOrderIsOn()
                .checkDeliveryPriceOrderAWS("9.94")
                .checkHeavyLoadsDeliveryPriceOrderAWS("10");
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
