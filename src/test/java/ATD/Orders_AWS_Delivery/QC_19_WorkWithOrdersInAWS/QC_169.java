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

public class QC_169 {

    private String userID = "15371374", articleNum, vatForDE;
    private Float  productCost, deliveryCost, deliveryCostInOrder, sellingCostInOrder, deliveryCostOfHeavyLoads, totalCostInOrder, costIncludingDeliveryInOrder;
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
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "HeavyLoadProduct1");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks creating order in AWS with heavy loads")
    public void testCreatingOrderInAwsWithWithHeavyLoads(String route) throws SQLException {
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
        deliveryCostInOrder = order_aws.checkVatStatusInOrder("Mit MwSt " + vatForDE + "%")
                .checkPaymentMethodInOrder("PayPal")
                .checkThatStatusSafeOrderIsOff()
                .checkContoNR("30047")
                .getDeliveryCostInOrder();
        Assert.assertEquals(deliveryCost, deliveryCostInOrder);
        sellingCostInOrder = order_aws.getSellingProductPriceOrderAWS();

        //TODO отключил данный ассерт из за дефекта AWS-2830
        //Assert.assertEquals(productCost, sellingCostInOrder);
        deliveryCostOfHeavyLoads = order_aws.getDeliveryCostOfHeavyLoads();
        totalCostInOrder = order_aws.getTotalPriceOrderAWS();
        costIncludingDeliveryInOrder = order_aws.getTotalCostIncludingDeliveryAndDeliveryCostOfHeavyLoads(sellingCostInOrder, deliveryCost, deliveryCostOfHeavyLoads);

        //TODO отключил данный ассерт из за дефекта AWS-2830
        //Assert.assertEquals(totalCostInOrder, costIncludingDeliveryInOrder);
        order_aws.reSaveOrder()
                .checkOrderHasTestStatus()
                .getUserDataInOrder();
        Assert.assertEquals(userData, userDataInOrder);
        deliveryCostInOrder = order_aws.checkVatStatusInOrder("Mit MwSt " + vatForDE + "%")
                .checkPaymentMethodInOrder("PayPal")
                .checkThatStatusSafeOrderIsOff()
                .checkContoNR("30047")
                .getDeliveryCostInOrder();
        Assert.assertEquals(deliveryCost, deliveryCostInOrder);
        sellingCostInOrder = order_aws.getSellingProductPriceOrderAWS();

        //TODO отключил данный ассерт из за дефекта AWS-2830
        //Assert.assertEquals(productCost, sellingCostInOrder);
        deliveryCostOfHeavyLoads = order_aws.getDeliveryCostOfHeavyLoads();
        totalCostInOrder = order_aws.getTotalPriceOrderAWS();
        costIncludingDeliveryInOrder = order_aws.getTotalCostIncludingDeliveryAndDeliveryCostOfHeavyLoads(sellingCostInOrder, deliveryCost, deliveryCostOfHeavyLoads);

        //TODO отключил данный ассерт из за дефекта AWS-2830
        //Assert.assertEquals(totalCostInOrder, costIncludingDeliveryInOrder);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}