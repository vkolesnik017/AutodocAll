package ATD.OrdersAWS_Delivery.QC_19_WorkWithOrdersInAWS;

import ATD.Product_page_Logic;
import ATD.SetUp;
import AWS.Order_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;

public class QC_1125_AddingAutoPartToAwsOrder {

    private String orderNumber = "31797781", articleNum, productArticleID;
    private Float productCost, productCostInOrder, sumIncomeWithoutVatAllGoods, totalSumIncomeWithoutVat;

    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private Order_aws order_aws = new Order_aws(orderNumber);

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "product28");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks adding a auto part to AWS order")
    public void testAddingAutoPartToAwsOrder(String route) {
        openPage(route);
        articleNum = product_page_logic.getArticleNumber();
        productArticleID = product_page_logic.getProductId();
        productCost = product_page_logic.getProductPrice();
        productCostInOrder = order_aws.openOrderInAwsWithLogin()
                .clickBtnAddedGoodsINOrder()
                .checkPresencePopUpAddProduct()
                .fillingFieldArticleNumInPopUpAddProduct(articleNum)
                .clickBtnAddedGoodsInPopUpAddProduct()
                .checkPresenceTableWithPopUpAddProduct()
                .chooseArticleIDOfDesiredProduct(productArticleID)
                .clickBtnAddedGoodsInPopUpAddProduct()
                .checkPresenceTableOfWarehousesAndSuppliers()
                .clickBtnAddedGoodsInPopUpAddProduct()
                .getSellingPriceOfCertainProduct(productArticleID);
        Assert.assertEquals(productCost, productCostInOrder);
        totalSumIncomeWithoutVat = order_aws.getTotalSumIncomeWithoutVAT();
        sumIncomeWithoutVatAllGoods = order_aws.plusAmountOfIncomeWithoutVatOfAllAddedGoods();
        Assert.assertEquals(totalSumIncomeWithoutVat, sumIncomeWithoutVatAllGoods);
        order_aws.compareQuantityOfItemsWithQuantityInColumnQuantityOfProducts();
    }
}