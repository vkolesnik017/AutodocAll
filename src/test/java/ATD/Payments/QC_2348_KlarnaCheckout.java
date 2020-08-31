package ATD.Payments;

import ATD.*;
import AWS.Customer_view_aws;
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
import static ATD.DataBase.parseUserIdFromBD;
import static ATD.DataBase.parseUserMailFromBD;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.closeWindow;

public class QC_2348_KlarnaCheckout {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopsWithSubroute("prod", "FI,SE,NO", "main", "product32");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description("Test checks method of payment by KlarnaCheckout")
    public void testKlarnaCheckout(String route) throws Exception {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        String userData = new DataBase().getUserIdForPaymentsMethod("payments_userid_atd", shop, "KlarnaCheckout");
        String userID = parseUserIdFromBD(userData);
        String mail = parseUserMailFromBD(userData);
        float totalPriceAllData = new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .checkPresencePaymentsMethodLabel(new Cart_page().klarnaLabel())
                .nextButtonClick()
                .signIn(mail, passwordForPayments)
                .chooseDeliveryCountryForShipping(shop)
                .fillFieldTelNumForShipping("100+001")
                .nextBtnClick()
                .clickOnTheDesiredPaymentMethod(shop, "KlarnaCheckout")
                .nextBtnClick()
                .checkPresencePaymentsMethodLabel(new CartAllData_page().klarnaLabel())
                .getTotalPriceAllDataPage(shop);
        new CartAllData_page_Logic().nextBtnClick();
        checkingContainsUrl("/klarna-invoice");
        new Merchant_page().checkPresenceFormInMerchantPageFromKlarnaCheckoutMethod();
        closeWindow();
        float totalPriceOrderAws = new Customer_view_aws().openCustomerPersonalArea(userID)
                .checkPresenceOrderHistoryBlock()
                .checkAndOpenOrderWithExpectedData()
                .checkPaymentMethodInOrder("Klarna Checkout")
                .checkCurrentStatusInOrder("abgebrochene Klarna Checkout")
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