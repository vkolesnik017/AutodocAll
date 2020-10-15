package ATD.Basket.QC_1873_SafeOrder;

import ATD.CartAllData_page_Logic;
import ATD.Product_page_Logic;
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

import static ATD.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2343_OrderWithSOIncludedOnBasketPageForShop_FR_DE {

    private String mail = "QC_2343_autotest@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("ATD").setUpShopsWithSubroute("prod", "DE,FR", "main", "product32");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks order with SO included on basket page FR / DE language versions.")
    public void testOrderWithSO_IncludedOnBasketPage(String route) throws SQLException {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        float totalPriceInAllData =  new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .checkThatSafeOrderCheckboxIsNotSelected()
                .clickSafeOrderCheckbox()
                .checkPresenceSOInSummeryBlock()
                .checkTotalPriceIncludedSO()
                .nextButtonClick()
                .signIn(mail, password)
                .fillAllFieldsAndFirmForShipping(shop, "12345", "autotest","autotest")
                .fillInCompanyIdFieldForCountryWhereIdNeeded(shop, "FR", "autotest")
                .clickOnTheDesiredPaymentMethod(shop, "Bank")
                .nextBtnClick()
                .checkThatSafeOrderCheckboxIsSelected()
                .checkPresenceSOPriceFromOrderSummeryBlock()
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