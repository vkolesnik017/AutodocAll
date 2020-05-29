package ATD.OrdersAWS_Delivery.QC_19_WorkWithOrdersInAWS;

import ATD.Product_page_Logic;
import ATD.SetUp;
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

import static ATD.CommonMethods.cutPriceToFirstDecimalPlace;
import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_1127_EditingQuantityOfAutoPartsInAwsOrder {

    private String userID = "15371693 ", articleNum, productArticleID;
    private Float totalCostInOrder, sellingCostInOrder, productQuantity, amountOfGoods, totalSumProduct,
            totalSumIncomeWithoutVat, costFromColumnIncomeWithoutVat, prunedTotalSumIncomeWithoutVat,
            prunedCostFromColumnIncomeWithoutVat, deliveryCost, totalSumIncludingDelivery;

    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private Order_aws order_aws = new Order_aws();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "product2");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks editing the quantity of auto parts in AWS order")
    public void testEditingQuantityOfAutoPartsInAwsOrder(String route) {
        openPage(route);
        articleNum = product_page_logic.getArticleNumber();
        productArticleID = product_page_logic.getProductId();
        deliveryCost = new SearchOrders_page_aws().openSearchOrderPageWithLogin()
                .clickAddOrderBtn()
                .fillsInFieldCustomerID(userID)
                .chooseSkinInSelector("autodoc.de (DE)")
                .selectedPaymentMethod("PayPal")
                .selectedDeliveryMethod("Standardversand")
                .addProduct(articleNum)
                .checkPresenceTableOfSuppliersAndClickBtnSelect()
                .checkArticleOfAddedProduct(articleNum)
                .clickSaveOrderBtn()
                .checkOrderHasTestStatus()
                .clickEditItemBtn(productArticleID)
                .editQuantityOfItemInPopUpEditItem("2")
                .checkQuantityOfGoodsInColumnQuantity("2")
                .checkQuantityOfGoodsInColumnExpectedQuantity("2")
                .getDeliveryCostInOrder();
        sellingCostInOrder = order_aws.getSellingPriceOfCertainProduct(productArticleID);
        productQuantity = order_aws.getProductQuantity();
        amountOfGoods = order_aws.multiplyPriceByQuantity(sellingCostInOrder, productQuantity);
        totalSumProduct = order_aws.getTotalSumProductFromColumnSumOfProduct();
        Assert.assertEquals(amountOfGoods, totalSumProduct);
        totalSumIncomeWithoutVat = order_aws.getTotalSumIncomeWithoutVAT();
        prunedTotalSumIncomeWithoutVat = cutPriceToFirstDecimalPlace(totalSumIncomeWithoutVat);
        costFromColumnIncomeWithoutVat = order_aws.getCostFromColumnIncomeWithoutVat();
        prunedCostFromColumnIncomeWithoutVat = cutPriceToFirstDecimalPlace(costFromColumnIncomeWithoutVat);
        Assert.assertEquals(prunedTotalSumIncomeWithoutVat, prunedCostFromColumnIncomeWithoutVat);
        totalCostInOrder = order_aws.checkQuantityOfGoodsInColumnCountProduct("1")
                .reSaveOrder()
                .getTotalPriceOrderAWS();
        totalSumIncludingDelivery = order_aws.multiplyPriceByQuantityAndPlusDeliveryCost(sellingCostInOrder, productQuantity, deliveryCost);
        Assert.assertEquals(totalCostInOrder, totalSumIncludingDelivery);
        order_aws.clickRefundBtn()
                .checkPresenceOfGoodsInRefundTable(articleNum)
                .checksQuantityOfGoodsInRefundTable("2");
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}