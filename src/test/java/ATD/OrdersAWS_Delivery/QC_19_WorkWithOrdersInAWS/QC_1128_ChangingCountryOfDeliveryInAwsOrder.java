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
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1128_ChangingCountryOfDeliveryInAwsOrder {

    private String userID = "15371745", articleNum, productArticleID;
    private Float deliveryCostFromDanemark, deliveryCostInOrder, totalCostInOrderAfterChangeCountry, sellingCostInOrder, totalCostInOrder,
            totalSumIncludingVat;

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
        totalCostInOrder = new SearchOrders_page_aws().openSearchOrderPageWithLogin()
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
                .getTotalPriceOrderAWS();
        order_aws.choosesDeliveryCountry("Denmark")
                .fillingPostalCodeInBlockDeliveryAddress("1231")
                .reSaveOrder();
        deliveryCostInOrder = order_aws.checkVatStatusInOrder("Mit MwSt 25%")
                .getDeliveryCostInOrder();
        Assert.assertEquals(deliveryCostInOrder, deliveryCostFromDanemark);
        totalCostInOrderAfterChangeCountry = order_aws.getTotalPriceOrderAWS();
        sellingCostInOrder = order_aws.getSellingProductPriceOrderAWS();
        totalSumIncludingVat = order_aws.getTotalCostIncludingSellingCostAndDeliveryCost(sellingCostInOrder, deliveryCostInOrder);
        Assert.assertEquals(totalCostInOrderAfterChangeCountry, totalSumIncludingVat);
        Assert.assertNotEquals(totalCostInOrder, totalCostInOrderAfterChangeCountry);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}