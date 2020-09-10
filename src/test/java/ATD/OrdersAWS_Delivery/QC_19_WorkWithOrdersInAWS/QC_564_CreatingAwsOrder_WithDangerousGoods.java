package ATD.OrdersAWS_Delivery.QC_19_WorkWithOrdersInAWS;

import ATD.Product_page_Logic;
import Common.SetUp;
import AWS.Customer_view_aws;
import AWS.OrderAdd_page_aws;
import AWS.Order_aws;
import AWS.SearchOrders_page_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static AWS.SearchOrders_page_aws.searchOrderPageURL;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_564_CreatingAwsOrder_WithDangerousGoods {


    private String userID = "15371520", articleNum;
    private Float deliveryCost, sellingCostInOrder, productCost, totalCostInOrder, totalCostIncludingSellingAndDeliveryCost;
    private ArrayList userDataInCreateOrder, userData, userDataInOrder;

    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private OrderAdd_page_aws orderAdd_page_aws = new OrderAdd_page_aws();
    private Order_aws order_aws = new Order_aws();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "productDangerousGoods2");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks creating order in AWS with dangerous goods")
    public void testCreatingOrderInAwsWithDangerousGoods(String route) {
        openPage(route);
        articleNum = product_page_logic.getArticleNumber();
        productCost = product_page_logic.getProductPrice();
        userData = new Customer_view_aws().openCustomerPersonalArea(userID)
                .getUserData();
        openPage(searchOrderPageURL);
        userDataInCreateOrder = new SearchOrders_page_aws().clickAddOrderBtn()
                .fillsInFieldCustomerID(userID)
                .chooseSkinInSelector("autodoc.de (DE)")
                .getUserDataInOrder();
        Assert.assertEquals(userData, userDataInCreateOrder);
        orderAdd_page_aws.selectedPaymentMethod("PayPal");
        deliveryCost = orderAdd_page_aws.selectedDeliveryMethod("Standardversand")
                .getDeliveryCost();
        userDataInOrder = orderAdd_page_aws.addProduct(articleNum)
                .checkPresenceTableOfSuppliersAndClickBtnSelect()
                .checkArticleOfAddedProduct(articleNum)
                .clickSaveOrderBtn()
                .checkOrderHasTestStatus()
                .getUserDataInOrder();
        Assert.assertEquals(userData, userDataInOrder);
        sellingCostInOrder = order_aws.checkVatStatusInOrder("Mit MwSt 16%")
                .checkPaymentMethodInOrder("PayPal")
                .checkThatStatusSafeOrderIsOff()
                .checkDeliveryPriceOrderAWS(deliveryCost)
                .checkContoNR("30047")
                .getSellingProductPriceOrderAWS();
        Assert.assertEquals(productCost, sellingCostInOrder);
        totalCostIncludingSellingAndDeliveryCost = order_aws.getTotalCostIncludingSellingCostAndDeliveryCost(sellingCostInOrder, deliveryCost);
        totalCostInOrder = order_aws.getTotalPriceOrderAWS();
        Assert.assertEquals(totalCostInOrder, totalCostIncludingSellingAndDeliveryCost);
        order_aws.reSaveOrder()
                .checkOrderHasTestStatus()
                .getUserDataInOrder();
        Assert.assertEquals(userData, userDataInOrder);
        sellingCostInOrder = order_aws.checkVatStatusInOrder("Mit MwSt 16%")
                .checkPaymentMethodInOrder("PayPal")
                .checkThatStatusSafeOrderIsOff()
                .checkDeliveryPriceOrderAWS(deliveryCost)
                .checkContoNR("30047")
                .getSellingProductPriceOrderAWS();
        Assert.assertEquals(productCost, sellingCostInOrder);
        totalCostIncludingSellingAndDeliveryCost = order_aws.getTotalCostIncludingSellingCostAndDeliveryCost(sellingCostInOrder, deliveryCost);
        totalCostInOrder = order_aws.getTotalPriceOrderAWS();
        Assert.assertEquals(totalCostInOrder, totalCostIncludingSellingAndDeliveryCost);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}