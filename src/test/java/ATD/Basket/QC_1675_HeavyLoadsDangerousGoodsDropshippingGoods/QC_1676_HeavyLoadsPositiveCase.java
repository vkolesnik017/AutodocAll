package ATD.Basket.QC_1675_HeavyLoadsDangerousGoodsDropshippingGoods;

import ATD.*;
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

import static ATD.CommonMethods.getCurrentShopFromJSVarInHTML;
import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_1676_HeavyLoadsPositiveCase {

    private String email = "checksPurchaseHeavyLoad@mailinator.com", password = "atdtest", orderNumber;
    private Double totalPrice, totalPriceAWSOrder;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "product19");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the purchase of a heavy load")
    public void testOfHeavyLoadsPurchase(String route) {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        totalPrice = new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(email, password)
                .fillAllFields(shop).nextBtnClick()
                .chooseVorkasse().nextBtnClick()
                .checkRegularDeliveryPriceAllData("6,95")
                .checkHeavyLoadsDeliveryPriceAllData("10,00")
                .checkAbsenceSafeOrderBlock()
                .getTotalPriceAllDataPage();
        orderNumber = new CartAllData_page_Logic().nextBtnClick().getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumber);
        totalPriceAWSOrder =  order_aws.openOrderInAwsWithLogin()
                .checkDeliveryPriceOrderAWS("6.95")
                .checkHeavyLoadsDeliveryPriceOrderAWS("10")
                .checkThatStatusSafeOrderIsOff()
                .getTotalPriceOrder();
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
        order_aws.reSaveOrder()
                .checkThatStatusSafeOrderIsOff()
                .checkDeliveryPriceOrderAWS("6.95")
                .checkHeavyLoadsDeliveryPriceOrderAWS("10");
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
