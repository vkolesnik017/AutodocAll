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
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1876_DefaultCheckboxIsSafeOrder {

    private String mail = "QC_1876_autotest@mailinator.com";
    float totalPriceInAllData, totalPriceInAWS, totalPriceInAwsAfterReSave;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopsWithSubroute("prod", "AT, EN, ES, IT, PT, NL, DK, FI, SE, PL, HU, CZ, BG, GR, RO, SK, BE, LD", "main", "product32");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks default checkbox is safe order.")
    public void testDefaultCheckboxIsSafeOrder(String route) throws SQLException {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        totalPriceInAllData = new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .fillAllFieldsAndDefaultPostalCode("autotest", "autotest", "autotest", "autotest", shop,"autotest","autotest")
                .fillInCompanyIdFieldForCountryWhereIdNeeded(shop, shop, "123456")
                .clickOnTheDesiredPaymentMethod(shop, "Bank")
                .nextBtnClick()
                .checkThatSafeOrderCheckboxIsSelected()
                .checkPresenceSafeOrderPriceFromOrderSummeryBlock()
                .getTotalPriceAllDataPage(shop);
        String orderNumber = new CartAllData_page_Logic().nextBtnClick()
                .getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumber);
        totalPriceInAWS =  order_aws.openOrderInAwsWithLogin()
                .checkThatStatusSafeOrderIsOn()
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPriceInAllData, totalPriceInAWS);
        totalPriceInAwsAfterReSave = order_aws.reSaveOrder()
                .checkThatStatusSafeOrderIsOn()
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPriceInAllData, totalPriceInAWS);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}