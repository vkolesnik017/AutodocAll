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

public class QC_137 {

    private String userID = "15371173", articleNum, productArticleID, vatForDE;
    private Float sellingProductCostInOrder, totalProductCostInOrder, productCost;
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
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "productDrop1");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks creating order in AWS with drop product")
    public void testCreatingOrderInAwsWithDropProduct(String route) {
        openPage(route);
        articleNum = product_page_logic.getArticleNumber();
        productCost = product_page_logic.getProductPrice();
        productArticleID = product_page_logic.getProductId();
        userData = new Customer_view_aws().openCustomerView(userID)
                .getUserData();
        openPage(searchOrderPageURL);
        userDataInCreateOrder = new SearchOrders_page_aws().clickAddOrderBtn()
                .fillsInFieldCustomerID(userID)
                .chooseSkinInSelector("autodoc.de (DE)")
                .getUserDataInOrder();
        Assert.assertEquals(userData, userDataInCreateOrder);
        userDataInOrder = orderAdd_page_aws.selectedPaymentMethod("PayPal")
                .selectedDeliveryMethod("Standardversand")
                .addProduct(articleNum)
                .chooseArticleIDOfDesiredProductAndClickBtnChooseProduct(productArticleID)
                .checkArticleOfAddedProduct(articleNum)
                .clickSaveOrderBtn()
                .checkOrderHasTestStatus()
                .getUserDataInOrder();
        Assert.assertEquals(userData, userDataInOrder);
        sellingProductCostInOrder = order_aws.checkVatStatusInOrder("Mit MwSt " + vatForDE + "%")
                .checkPaymentMethodInOrder("PayPal")
                .checkThatStatusSafeOrderIsOff()
                .checkDeliveryCost("0")
                .checkContoNR("30047")
                .getSellingProductPriceOrderAWS();
        Assert.assertEquals(productCost , sellingProductCostInOrder);
        /* TODO ???????????????????????? ?????????? ?????????????????????? ??????????????  BSK-698
        totalProductCostInOrder = order_aws.getTotalPriceOrder();
        Assert.assertEquals(sellingProductCostInOrder, totalProductCostInOrder);*/
        order_aws.reSaveOrder()
                .checkOrderHasTestStatus()
                .getUserDataInOrder();
        Assert.assertEquals(userData, userDataInOrder);
        sellingProductCostInOrder = order_aws.checkVatStatusInOrder("Mit MwSt " + vatForDE + "%")
                .checkPaymentMethodInOrder("PayPal")
                .checkThatStatusSafeOrderIsOff()
                .checkDeliveryCost("0")
                .checkContoNR("30047")
                .getSellingProductPriceOrderAWS();
        Assert.assertEquals(productCost , sellingProductCostInOrder);
        totalProductCostInOrder = order_aws.getTotalPriceOrderAWS();
        Assert.assertEquals(sellingProductCostInOrder, totalProductCostInOrder);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}