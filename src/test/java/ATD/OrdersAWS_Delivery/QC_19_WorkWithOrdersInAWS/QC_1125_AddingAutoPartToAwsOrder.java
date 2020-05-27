package ATD.OrdersAWS_Delivery.QC_19_WorkWithOrdersInAWS;

import ATD.Product_page_Logic;
import ATD.SetUp;
import AWS.Login_aws;
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

public class QC_1125_AddingAutoPartToAwsOrder {

    private String userID = "15089943", articleNum, productArticleID;
    private Float productCost, productCostInOrder, sumIncomeWithoutVatAllGoods, prunedTotalSumIncomeWithoutVat,
            totalSumIncomeWithoutVat, totalCostOrder, totalSellingPriceIncludingDelivery, prunedSumIncomeWithoutVatAllGoods;

    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private Order_aws order_aws = new Order_aws();

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
        productCostInOrder = new SearchOrders_page_aws().openSearchOrderPageWithLogin()
                .clickAddOrderBtn()
                .fillsInFieldCustomerID(userID)
                .chooseSkinInSelector("autodoc.de (DE)")
                .selectedPaymentMethod("PayPal")
                .selectedDeliveryMethod("Standardversand")
                .addProduct("CAF100729P")
                .checkPresenceTableOfSuppliersAndClickBtnSelect()
                .checkArticleOfAddedProduct("CAF100729P")
                .clickSaveOrderBtn()
                .checkOrderHasTestStatus()
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
        prunedTotalSumIncomeWithoutVat = cutPriceToFirstDecimalPlace(totalSumIncomeWithoutVat);
        sumIncomeWithoutVatAllGoods = order_aws.plusAmountOfIncomeWithoutVatOfAllAddedGoods();
        prunedSumIncomeWithoutVatAllGoods = cutPriceToFirstDecimalPlace(sumIncomeWithoutVatAllGoods);
        Assert.assertEquals(prunedTotalSumIncomeWithoutVat, prunedSumIncomeWithoutVatAllGoods);
        order_aws.compareQuantityOfItemsWithQuantityInColumnQuantityOfProducts();
        totalCostOrder = order_aws.getTotalPriceOrderAWS();
        order_aws.reSaveOrder();
        totalSellingPriceIncludingDelivery = order_aws.plusSellingPriceOfAllAddedItemsIncludingDelivery();
        Assert.assertEquals(totalCostOrder, totalSellingPriceIncludingDelivery);
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}