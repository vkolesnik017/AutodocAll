package PKW.Payments;

import Common.Merchant_page;
import AWS.Customer_view_aws;
import AWS.Order_aws;
import Common.DataBase;
import Common.SetUp;
import PKW.CartAllData_page;
import PKW.CartAllData_page_Logic;
import PKW.Cart_page;
import PKW.Product_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.DataBase.parseUserIdFromBD;
import static Common.DataBase.parseUserMailFromBD;
import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.*;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2465 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = false)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("PKW").setUpShopsWithSubroute("prod", "DE,AT,CH", "main", "product");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description("Test checks method of payment by Sofort")
    public void testSofort(String route) throws Exception {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        String userData = new DataBase("PKW").getUserIdForPaymentsMethod("payments_userid_pkw", shop, "Sofort");
        String userID = parseUserIdFromBD(userData);
        String mail = parseUserMailFromBD(userData);
        float totalPriceAllData = new Product_page_Logic().addProductToCart()
                .cartClick()
                .checkPresencePaymentsMethodLabel(new Cart_page().sofortLabel())
                .nextButtonClick()
                .signIn(mail, passwordForPayments)
                .chooseDeliveryCountryForShipping(shop)
                .fillFieldTelNumForShipping("100+001")
                .nextBtnClick()
                .clickOnTheDesiredPaymentMethod(shop, "Sofort")
                .nextBtnClick()
                .checkPresencePaymentsMethodLabel(new CartAllData_page().sofortLabel())
                .getTotalPriceAllDataPage(shop);
        new CartAllData_page_Logic().nextBtnClick();
        new Merchant_page().cancelOrderForSofortMethod()
                .checkActivePaymentMethod("directbank");
        float totalPriceOrderAws = new Customer_view_aws().openCustomerView(userID)
                .checkPresenceOrderHistoryBlock()
                .checkAndOpenOrderWithExpectedData()
                .checkPaymentMethodInOrder("Sofortüberweisung")
                .checkCurrentStatusInOrder("Abgebrochen")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPriceAllData, totalPriceOrderAws);
        float totalPriceOrderAwsAfterReSave = new Order_aws().reSaveOrder()
                .checkCurrentStatusInOrder("Testbestellungen")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPriceAllData, totalPriceOrderAwsAfterReSave);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}