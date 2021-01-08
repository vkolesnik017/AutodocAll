package ATD.Orders_AWS_Delivery.QC_1675_HeavyLoadsDangerousGoodsDropshippingGoods;

import ATD.CartAllData_page_Logic;
import ATD.Product_page_Logic;
import ATD.Versand_static_page_Logic;
import AWS.Order_aws;
import Common.SetUp;
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
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2367 {

    private String email = "QC-2367_autotest@mailinator.com";
    private Float totalPrice, totalPriceAWSOrder, totalDeliveryPriceAndSafeOrder, safeOrderPrice, deliveryPrice;
    private CartAllData_page_Logic cartAllData_page_logic = new CartAllData_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "productDangerousGoods1");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the purchase of a dangerous goods")
    public void testOfDangerousGoodsPurchase(String route) throws Exception {
        deliveryPrice = new Versand_static_page_Logic().getDeliveryPriceOfCurrentCountry("DE");
        openPage(route);
        safeOrderPrice = new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(email, password)
                .fillAllFields("DE")
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .checkPresenceSafeOrderBlock()
                .clickSafeOrderCheckbox()
                .checkThatSafeOrderCheckboxIsSelected()
                .checkRegularDeliveryPrice(deliveryPrice)
                .checkPresenceSOPriceFromOrderSummeryBlock()
                .getSafeOrderPrice();
        totalPrice = cartAllData_page_logic.getTotalPriceAllDataPage("DE");
        totalDeliveryPriceAndSafeOrder = cartAllData_page_logic.getTotalDeliveryPriceAndSafeOrder(deliveryPrice, safeOrderPrice);
        String orderNumber = cartAllData_page_logic.nextBtnClick().getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumber);
        totalPriceAWSOrder = order_aws.openOrderInAwsWithLogin()
                .checkDeliveryPriceOrderAWS(totalDeliveryPriceAndSafeOrder)
                .checkThatStatusSafeOrderIsOn()
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
        totalPriceAWSOrder = order_aws.reSaveOrder()
                .checkDeliveryPriceOrderAWS(totalDeliveryPriceAndSafeOrder)
                .checkThatStatusSafeOrderIsOn()
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}