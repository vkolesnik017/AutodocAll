package ATD.Orders_AWS_Delivery.QC_19_WorkWithOrdersInAWS;

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

public class QC_173 {

    private String userID = "15089943", articleNum;
    private ArrayList userDataInCreateOrder, userData;

    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private OrderAdd_page_aws orderAdd_page_aws = new OrderAdd_page_aws();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "productDangerousGoods1");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks order creation in AWS with dangerous goods to country to which it is not delivered")
    public void testCreatingOrderInAwsWithDangerousGoodsDeliveryToWrongCountry(String route) {
        openPage(route);
        articleNum = product_page_logic.getArticleNumber();
        userData = new Customer_view_aws().openCustomerView(userID)
                .getUserData();
        openPage(searchOrderPageURL);
        userDataInCreateOrder = new SearchOrders_page_aws().clickAddOrderBtn()
                .fillsInFieldCustomerID(userID)
                .chooseSkinInSelector("autodoc.de (DE)")
                .getUserDataInOrder();
        Assert.assertEquals(userData, userDataInCreateOrder);
        orderAdd_page_aws.choosesDeliveryCountry("Norway")
                .selectedPaymentMethod("PayPal")
                .selectedDeliveryMethod("Standardversand")
                .addProduct(articleNum)
                .checkPresenceTableOfSuppliersAndClickBtnSelect()
                .checkPresencePopupWithDeliveryError();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
