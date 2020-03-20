package ATD.Basket.QC_1675_HeavyLoadsDangerousGoodsDropshippingGoods;

import ATD.CartAllData_page_Logic;
import ATD.Payment_handler_page_Logic;
import ATD.Product_page_Logic;
import ATD.SetUp;
import AWS.Order_aws;
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
import static ATD.CommonMethods.password;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_1679_HeavyLoadsFR_PositiveCase {

    private String email = "qc_1679autotestDE@mailinator.com";
    private Double totalPrice;
    private Double totalPriceAWSOrder;
    private String orderNumber;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "product20");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the purchase of a heavy load in FR")
    public void testOfHeavyLoadsPurchaseInFR(String route) {
        openPage(route);
        totalPrice = new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(email, password).nextBtnClick()
                .chooseVorkasse().nextBtnClick()
                .checkRegularDeliveryPriceAllData("9,95")
                .checkHeavyLoadsDeliveryPriceAllData("36,95")
                .checkAbsenceSafeOrderBlock()
                .getTotalPriceAllDataPage();
        new CartAllData_page_Logic().nextBtnClick();
        orderNumber = new Payment_handler_page_Logic().getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumber);
        totalPriceAWSOrder = order_aws.openOrderInAwsWithLogin().getTotalPriceOrder();
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
        order_aws.checkDeliveryPriceOrderAWS("9.95")
                .checkHeavyLoadsDeliveryPriceOrderAWS("36.95")
                .checkStatusSafeOrder()
                .reSaveOrder();
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
        order_aws.checkDeliveryPriceOrderAWS("9.95")
                .checkHeavyLoadsDeliveryPriceOrderAWS("36.95");
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}