package ATD.OrdersAWS_Delivery.QC_19_WorkWithOrdersInAWS;

import ATD.DataBase;
import ATD.Product_page_Logic;
import ATD.SetUp;
import AWS.OrderAdd_page_aws;
import AWS.Order_aws;
import AWS.SearchOrders_page_aws;
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

public class QC_1126_RemovingAutoPartFromAwsOrder {

    private String userID = "15089943", firstArticleNum, secondArticleNum, secondProductArticleID, firstProductArticleID;
    private Float totalCostOrder, totalCostOrderAfterReSave, totalSumIncomeWithoutVat, sumIncomeWithoutVatAllGoods;


    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private OrderAdd_page_aws orderAdd_page_aws = new OrderAdd_page_aws();
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
    @Description(value = "Test checks removing auto part from AWS Order")
    public void testRemovingAutoPartFromAwsOrder(String route) throws SQLException {
        openPage(route);
        firstArticleNum = product_page_logic.getArticleNumber();
        firstProductArticleID = product_page_logic.getProductId();
        openPage(new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "product27"));
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
                .checkPresenceTableOfSuppliersAndClickBtnSelect()
                .checkArticleOfAddedProduct(secondArticleNum)
                .clickSaveOrderBtn()
                .checkOrderHasTestStatus()
                .getTotalPriceOrderAWS();
        totalCostOrderAfterReSave = order_aws.selectCheckboxDesiredProduct(firstProductArticleID)
                .clickRemoveProductBtn()
                .clockBtnNoInRemoveProductPopUp()
                .reSaveOrder()
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalCostOrderAfterReSave, totalCostOrder);
        totalSumIncomeWithoutVat = order_aws.selectCheckboxDesiredProduct(firstProductArticleID)
                .clickRemoveProductBtn()
                .clockBtnYesInRemoveProductPopUp()
                .compareQuantityOfItemsWithQuantityInColumnQuantityOfProducts()
                .getTotalSumIncomeWithoutVAT();
        sumIncomeWithoutVatAllGoods = order_aws.plusAmountOfIncomeWithoutVatOfAllAddedGoods();
        Assert.assertEquals(sumIncomeWithoutVatAllGoods, totalSumIncomeWithoutVat);

    }
}