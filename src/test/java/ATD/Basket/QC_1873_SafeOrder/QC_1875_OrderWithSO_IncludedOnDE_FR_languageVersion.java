package ATD.Basket.QC_1873_SafeOrder;

import ATD.CartAllData_page_Logic;
import ATD.Product_page_Logic;
import Common.SetUp;
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
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1875_OrderWithSO_IncludedOnDE_FR_languageVersion {

    private String mail = "QC_1875_autotest@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("ATD").setUpShopsWithSubroute("prod", "DE", "main", "product32");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks order with SO included on FR / DE language versions.")
    public void testOrderWithSO_IncludedOnDE_FR_languageVersion(String route) throws SQLException {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        float totalPriceInAllData =  new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .fillAllFieldsAndFirmForShipping(shop, "12345", "autotest","autotest")
                .fillInCompanyIdFieldForCountryWhereIdNeeded(shop, "FR", "autotest")
                .chooseVorkasse()
                .nextBtnClick()
                .checkPresenceSafeOrderBlock()
                .addSafeOrderInOrderAndCheckTotalPriceIncludedSO(shop)
                .getTotalPriceAllDataPage(shop);
        String orderNumber = new CartAllData_page_Logic().nextBtnClick()
                .getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumber);
        float totalPriceInAWS =  order_aws.openOrderInAwsWithLogin()
                .checkThatStatusSafeOrderIsOn()
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPriceInAllData, totalPriceInAWS);
        totalPriceInAWS = order_aws.reSaveOrder()
                .checkThatStatusSafeOrderIsOn()
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPriceInAllData, totalPriceInAWS);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}