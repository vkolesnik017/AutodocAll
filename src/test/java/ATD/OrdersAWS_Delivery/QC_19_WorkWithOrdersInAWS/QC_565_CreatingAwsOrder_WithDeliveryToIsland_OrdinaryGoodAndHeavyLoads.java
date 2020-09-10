package ATD.OrdersAWS_Delivery.QC_19_WorkWithOrdersInAWS;

import Common.DataBase;
import ATD.Product_page_Logic;
import Common.SetUp;
import AWS.Customer_view_aws;
import AWS.OrderAdd_page_aws;
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
import java.util.ArrayList;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static AWS.SearchOrders_page_aws.searchOrderPageURL;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_565_CreatingAwsOrder_WithDeliveryToIsland_OrdinaryGoodAndHeavyLoads {

    private String userID = "15371548", articleNum, productArticleID, heavyLoadsArticleNun;
    private ArrayList userDataInCreateOrder, userData;

    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private OrderAdd_page_aws orderAdd_page_aws = new OrderAdd_page_aws();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product27");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks creating order in AWS with delivery to island")
    public void testCreatingOrderInAWS(String route) throws SQLException {
        openPage(route);
        articleNum = product_page_logic.getArticleNumber();
        productArticleID = product_page_logic.getProductId();
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "HeavyLoadProduct1"));
        heavyLoadsArticleNun = product_page_logic.getArticleNumber();
        userData = new Customer_view_aws().openCustomerPersonalArea(userID)
                .getUserData();
        openPage(searchOrderPageURL);
        userDataInCreateOrder = new SearchOrders_page_aws().clickAddOrderBtn()
                .fillsInFieldCustomerID(userID)
                .chooseSkinInSelector("autodoc.de (DE)")
                 .getUserDataInOrder();
        Assert.assertEquals(userData, userDataInCreateOrder);
        userDataInCreateOrder = orderAdd_page_aws.choosesDeliveryCountry("Italy")
                .fillingPostalCodeInBlockDeliveryAddress("22060")
                .getUserDataInOrder();
        orderAdd_page_aws.selectedPaymentMethod("Vorkasse");
        orderAdd_page_aws.selectedDeliveryMethod("Standardversand")
                .addProduct(articleNum)
                .chooseArticleIDOfDesiredProductAndClickBtnChooseProduct(productArticleID)
                .checkPresenceTableOfSuppliersAndClickBtnSelect()
                .addProduct(heavyLoadsArticleNun)
                .checkPresenceTableOfSuppliersAndClickBtnSelect()
                .checkPresencePopupWithDeliveryError();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}