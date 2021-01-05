package ATD.Orders_AWS_Delivery.QC_19_WorkWithOrdersInAWS;

import ATD.Product_page_Logic;
import Common.SetUp;
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

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1125 {

    private String userID = "15371589", articleNum, productArticleID;
    private Float productCost, sellingCostInOrder, sumIncomeWithoutVatAllGoods,
            totalSumIncomeWithoutVat, totalCostOrder, totalSellingPriceIncludingDelivery;

    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private Order_aws order_aws = new Order_aws();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product28");
    }

    @Test(dataProvider = "route", enabled = false)
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks adding a auto part to AWS order")
    public void testAddingAutoPartToAwsOrder(String route) {
        openPage(route);
        articleNum = product_page_logic.getArticleNumber();
        productArticleID = product_page_logic.getProductId();
        productCost = product_page_logic.getProductPrice();
        totalSumIncomeWithoutVat = new SearchOrders_page_aws().openSearchOrderPageWithLogin()
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
                .getTotalSumIncomeWithoutVAT();
        sellingCostInOrder = order_aws.clickBtnAddedGoodsINOrder()
                .checkPresencePopUpAddProduct()
                .fillingFieldArticleNumInPopUpAddProduct(articleNum)
                .clickBtnAddedGoodsInPopUpAddProduct()
                .checkPresenceTableWithPopUpAddProduct()
                .chooseArticleIDOfDesiredProduct(productArticleID)
                .clickBtnAddedGoodsInPopUpAddProduct()
                .checkPresenceTableOfWarehousesAndSuppliers()
                .getSellingPriceOfCertainProduct(productArticleID);
        Assert.assertEquals(productCost, sellingCostInOrder);
        sumIncomeWithoutVatAllGoods = order_aws.getTotalSumIncomeWithoutVAT();
        Assert.assertNotEquals(totalSumIncomeWithoutVat, sumIncomeWithoutVatAllGoods);
        order_aws.compareQuantityOfItemsWithQuantityInColumnQuantityOfProducts();
        totalCostOrder = order_aws.getTotalPriceOrderAWS();
        order_aws.reSaveOrder();
        totalSellingPriceIncludingDelivery = order_aws.plusSellingPriceOfAllAddedItemsIncludingDelivery();
        Assert.assertEquals(totalCostOrder, totalSellingPriceIncludingDelivery);
        order_aws.clickRefundBtn()
                .checkPresenceOfGoodsInRefundTable(articleNum);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}