package ATD.OrdersAWS_Delivery.QC_19_WorkWithOrdersInAWS;

import ATD.Product_page_Logic;
import ATD.SetUp;
import ATD.Versand_static_page_Logic;
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
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_1128_ChangingCountryOfDeliveryInAwsOrder {

    private String userID = "15089943", articleNum, productArticleID;
    private Float deliveryCostFromDanemark, deliveryCostInOrder, totalCostInOrder, sellingCostInOrder, productQuantity, amountOfGoods, totalSumProduct,
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
    public void testEditingQuantityOfAutoPartsInAwsOrder(String route) throws Exception {
        deliveryCostFromDanemark = new Versand_static_page_Logic().getDeliveryPriceForAWS("Dänemark");
        openPage(route);
        articleNum = product_page_logic.getArticleNumber();
        productArticleID = product_page_logic.getProductId();
        deliveryCostInOrder = new SearchOrders_page_aws().openSearchOrderPageWithLogin()
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
                .choosesDeliveryCountry("Denmark")
                .fillingPostalCodeInBlockDeliveryAddress("1231")
                .reSaveOrder()
                .checkVatStatusInOrder("Mit MwSt 25%")
                .getDeliveryCostInOrder();
        Assert.assertEquals(deliveryCostInOrder, deliveryCostFromDanemark);
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}