package ATD.Basket.QC_1873_SafeOrder;

import ATD.CartAllData_page_Logic;
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

import static ATD.CommonMethods.*;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_1877_OrderWithSO_Disabled {

    private String mail = "QC_1877_autotest@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopsWithSubroute("prod", "AT", "main", "product32");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks Order with SO disabled.")
    public void testOrderWithSO_Disabled(String route) throws SQLException {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        float totalPriceInAllData =  new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .fillAllFieldsAndFirmForShipping(shop, "1234", "autotest", "autotest")
                .fillInCompanyIdFieldForCountryWhereIdNeeded(shop, "AT", "autotest")
                .chooseBankAustria()
                .nextBtnClick()
                .checkThatSafeOrderCheckboxIsSelected()
                .removeSafeOrderInOrderAndCheckTotalPriceIncludedSO()
                .getTotalPriceAllDataPage();
        String orderNumber = new CartAllData_page_Logic().nextBtnClick()
                .getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumber);
        float totalPriceInAWS =  order_aws.openOrderInAwsWithLogin()
                .checkThatStatusSafeOrderIsOff()
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPriceInAllData, totalPriceInAWS);
        totalPriceInAWS = order_aws.reSaveOrder()
                .checkThatStatusSafeOrderIsOff()
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPriceInAllData, totalPriceInAWS);
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}