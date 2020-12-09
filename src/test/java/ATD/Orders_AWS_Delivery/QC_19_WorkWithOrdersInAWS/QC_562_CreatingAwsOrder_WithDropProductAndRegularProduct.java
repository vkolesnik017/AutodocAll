package ATD.Orders_AWS_Delivery.QC_19_WorkWithOrdersInAWS;

import Common.DataBase;
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

public class QC_562_CreatingAwsOrder_WithDropProductAndRegularProduct {

    private String userID = "15371456", articleNum, dropArticleNum, productDropArticleID;
    private Float deliveryCost, safeOrderCost, productPriceIncludingDeliveryAndSafeOrder,
            totalDeliveryAmountAndSafeOrder, productCost, productDropCost, totalProductCostInOrder;
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
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product2");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks creating order in AWS with drop product and regular product")
    public void testCreatingOrderInAwsWithDropProductAndRegularProduct(String route) throws SQLException {
        openPage(route);
        ArrayList <Float> allProductCost = new ArrayList<>();
        articleNum = product_page_logic.getArticleNumber();
        productCost = product_page_logic.getProductPrice();
        allProductCost.add(productCost);
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "productDrop1"));
        dropArticleNum = product_page_logic.getArticleNumber();
        productDropCost = product_page_logic.getProductPrice();
        productDropArticleID = product_page_logic.getProductId();
        allProductCost.add(productDropCost);
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
        safeOrderCost = orderAdd_page_aws.selectedStatusInSafeOrder("Включен")
                .getSafeOrderCost();
        userDataInOrder = orderAdd_page_aws.addProduct(articleNum)
                .checkPresenceTableOfSuppliersAndClickBtnSelect()
                .checkArticleOfAddedProduct(articleNum)
                .addProduct(dropArticleNum)
                .chooseArticleIDOfDesiredProductAndClickBtnChooseProduct(productDropArticleID)
                .checkArticleOfAddedProduct(dropArticleNum)
                .clickSaveOrderBtn()
                .checkOrderHasTestStatus()
                .getUserDataInOrder();
        Assert.assertEquals(userData, userDataInOrder);
        order_aws.checkVatStatusInOrder("Mit MwSt 16%")
                .checkPaymentMethodInOrder("PayPal")
                .checkThatStatusSafeOrderIsOn();
        totalDeliveryAmountAndSafeOrder = order_aws.getTotalCostDeliveryAmountAndSafeOrder(deliveryCost, safeOrderCost);
        totalProductCostInOrder = order_aws.checkDeliveryPriceOrderAWS(totalDeliveryAmountAndSafeOrder)
                .checkContoNR("30047")
                // TODO включу данный ассерт после исправлениея дефекта AWS-2830
                //.comparePriceOfAddedProductWithPricesOnSites(allProductCost)
                .getTotalPriceOrderAWS();
        productPriceIncludingDeliveryAndSafeOrder = order_aws.getTotalCostOfAllGoodsAndDeliveryAndSafeOrder(deliveryCost, safeOrderCost);
        // TODO включу данный ассерт после исправлениея дефекта AWS-2830
        /*Assert.assertEquals(totalProductCostInOrder, productPriceIncludingDeliveryAndSafeOrder);*/
        order_aws.reSaveOrder()
                .checkOrderHasTestStatus()
                .getUserDataInOrder();
        Assert.assertEquals(userData, userDataInOrder);
        order_aws.checkVatStatusInOrder("Mit MwSt 16%")
                .checkPaymentMethodInOrder("PayPal")
                .checkThatStatusSafeOrderIsOn();
        totalDeliveryAmountAndSafeOrder = order_aws.getTotalCostDeliveryAmountAndSafeOrder(deliveryCost, safeOrderCost);
        totalProductCostInOrder = order_aws.checkDeliveryPriceOrderAWS(totalDeliveryAmountAndSafeOrder)
                .checkContoNR("30047")
                // TODO включу данный ассерт после исправлениея дефекта AWS-2830
                //.comparePriceOfAddedProductWithPricesOnSites(allProductCost)
                .getTotalPriceOrderAWS();
        productPriceIncludingDeliveryAndSafeOrder = order_aws.getTotalCostOfAllGoodsAndDeliveryAndSafeOrder(deliveryCost, safeOrderCost);
        // TODO включу данный ассерт после исправлениея дефекта AWS-2830
        /*Assert.assertEquals(totalProductCostInOrder, productPriceIncludingDeliveryAndSafeOrder);*/
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}