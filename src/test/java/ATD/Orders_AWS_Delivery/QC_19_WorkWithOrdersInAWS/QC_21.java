package ATD.Orders_AWS_Delivery.QC_19_WorkWithOrdersInAWS;

import ATD.Product_page_Logic;
import AWS.*;
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
import java.util.ArrayList;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static AWS.SearchOrders_page_aws.searchOrderPageURL;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_21 {

    private String userID = "15089943", articleNum, vatForDE;
    private Float deliveryCost, safeOrderCost, productCostInOrder, totalProductCostIncludingDeliveryAndSafeOrder,
            totalDeliveryAmountAndSafeOrder, productCost, totalCostInOrder;
    private ArrayList userDataInCreateOrder, userData, userDataInOrder;

    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private OrderAdd_page_aws orderAdd_page_aws = new OrderAdd_page_aws();
    private Order_aws order_aws = new Order_aws();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
        vatForDE = new PageVAT_aws().getVatForDE();
        close();
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product2");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks creating order in AWS")
    public void testCreatingOrderInAWS(String route) {
        openPage(route);
        articleNum = product_page_logic.getArticleNumber();
        productCost = product_page_logic.getProductPrice();
        userData = new Customer_view_aws().openCustomerView(userID)
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
        safeOrderCost = orderAdd_page_aws.selectedStatusInSafeOrder("??????????????")
                .getSafeOrderCost();
        userDataInOrder = orderAdd_page_aws.addProduct(articleNum)
                .checkPresenceTableOfSuppliersAndClickBtnSelect()
                .checkArticleOfAddedProduct(articleNum)
                .clickSaveOrderBtn()
                .checkOrderHasTestStatus()
                .getUserDataInOrder();
        Assert.assertEquals(userData, userDataInOrder);
        order_aws.checkVatStatusInOrder("Mit MwSt " + vatForDE + "%")
                .checkPaymentMethodInOrder("PayPal")
                .checkThatStatusSafeOrderIsOn();
        totalDeliveryAmountAndSafeOrder = order_aws.getTotalCostDeliveryAmountAndSafeOrder(deliveryCost, safeOrderCost);
        productCostInOrder = order_aws.checkDeliveryPriceOrderAWS(totalDeliveryAmountAndSafeOrder)
                .checkContoNR("30047")
                .getSellingProductPriceOrderAWS();

        Assert.assertEquals(productCost, productCostInOrder, 0.03f);
        totalProductCostIncludingDeliveryAndSafeOrder = order_aws.getTotalCostIncludingDeliveryAndSafeOrder(productCostInOrder, deliveryCost, safeOrderCost);
        totalCostInOrder = order_aws.getTotalPriceOrderAWS();
        Assert.assertEquals(totalCostInOrder, totalProductCostIncludingDeliveryAndSafeOrder, 0.03f);
        order_aws.reSaveOrder();
        userDataInOrder = order_aws.checkOrderHasTestStatus()
                .getUserDataInOrder();
        Assert.assertEquals(userData, userDataInOrder);
        order_aws.checkVatStatusInOrder("Mit MwSt " + vatForDE + "%")
                .checkPaymentMethodInOrder("PayPal")
                .checkThatStatusSafeOrderIsOn();
        totalDeliveryAmountAndSafeOrder = order_aws.getTotalCostDeliveryAmountAndSafeOrder(deliveryCost, safeOrderCost);
        productCostInOrder = order_aws.checkDeliveryPriceOrderAWS(totalDeliveryAmountAndSafeOrder)
                .checkContoNR("30047")
                .getSellingProductPriceOrderAWS();
        Assert.assertEquals(productCost, productCostInOrder, 0.03f);
        totalProductCostIncludingDeliveryAndSafeOrder = order_aws.getTotalCostIncludingDeliveryAndSafeOrder(productCostInOrder, deliveryCost, safeOrderCost);
        totalCostInOrder = order_aws.getTotalPriceOrderAWS();
        Assert.assertEquals(totalCostInOrder, totalProductCostIncludingDeliveryAndSafeOrder, 0.03f);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
