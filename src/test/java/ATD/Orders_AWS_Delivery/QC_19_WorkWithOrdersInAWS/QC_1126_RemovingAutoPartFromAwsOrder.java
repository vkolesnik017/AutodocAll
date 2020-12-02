package ATD.Orders_AWS_Delivery.QC_19_WorkWithOrdersInAWS;

import Common.DataBase;
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

public class QC_1126_RemovingAutoPartFromAwsOrder {

    private String userID = "15371629", firstArticleNum, secondArticleNum, secondProductArticleID, firstProductArticleID;
    private Float totalCostOrder, totalCostOrderAfterReSave, totalSumIncomeWithoutVat, totalSumIncomeWithoutVatAfterRemove,
            sellingCostInOrder, totalCostWithoutOneItem;


    private Product_page_Logic product_page_logic = new Product_page_Logic();
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
    @Description(value = "Test checks removing auto part from AWS Order")
    public void testRemovingAutoPartFromAwsOrder(String route) throws SQLException {
        openPage(route);
        firstArticleNum = product_page_logic.getArticleNumber();
        firstProductArticleID = product_page_logic.getProductId();
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "product28"));
        secondArticleNum = product_page_logic.getArticleNumber();
        secondProductArticleID = product_page_logic.getProductId();
        totalCostOrder = new SearchOrders_page_aws().openSearchOrderPageWithLogin()
                .clickAddOrderBtn()
                .fillsInFieldCustomerID(userID)
                .chooseSkinInSelector("autodoc.de (DE)")
                .selectedPaymentMethod("PayPal")
                .selectedDeliveryMethod("Standardversand")
                .addProduct(firstArticleNum)
                .checkPresenceTableOfSuppliersAndClickBtnSelect()
                .checkArticleOfAddedProduct(firstArticleNum)
                .addProduct(secondArticleNum)
                .chooseArticleIDOfDesiredProductAndClickBtnChooseProduct(secondProductArticleID)
                .checkArticleOfAddedProduct(secondArticleNum)
                .clickSaveOrderBtn()
                .checkOrderHasTestStatus()
                .getTotalPriceOrderAWS();
        totalSumIncomeWithoutVat = order_aws.getTotalSumIncomeWithoutVAT();
        sellingCostInOrder = order_aws.getSellingPriceOfCertainProduct(firstProductArticleID);
        totalCostOrderAfterReSave = order_aws.selectCheckboxDesiredProduct(firstProductArticleID)
                .clickRemoveProductBtn()
                .clickBtnNoInRemoveProductPopUp()
                .reSaveOrder()
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalCostOrderAfterReSave, totalCostOrder);
        totalSumIncomeWithoutVatAfterRemove = order_aws.selectCheckboxDesiredProduct(firstProductArticleID)
                .clickRemoveProductBtn()
                .clickBtnYesInRemoveProductPopUp()
                .compareQuantityOfItemsWithQuantityInColumnQuantityOfProducts()
                .getTotalSumIncomeWithoutVAT();
        Assert.assertNotEquals(totalSumIncomeWithoutVat, totalSumIncomeWithoutVatAfterRemove);
        totalCostWithoutOneItem = order_aws.subtractsRemovedProductCostFromTotalOrderCost(totalCostOrder, sellingCostInOrder);
        totalCostOrder = order_aws.getTotalPriceOrderAWS();
        Assert.assertEquals(totalCostWithoutOneItem, totalCostOrder);
        order_aws.clickRefundBtn()
                .checkAbsenceOfGoodsInRefundTable(firstArticleNum);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}