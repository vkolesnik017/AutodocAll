package PKW.Payments;

import AWS.Customer_view_aws;
import AWS.Order_aws;
import Common.DataBase;
import Common.SetUp;
import PKW.*;
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
import static com.codeborne.selenide.Selenide.*;

public class QC_2469_PayPal_PKW {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("PKW").setUpShopsWithSubroute("prod", "DE,AT,BG,CH,CZ,DK,ES,FI,FR,GR,HU,IT,NL,NO,PL,PT,RO,SE,EN", "main", "product9");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description("Test checks method of payment by PayPal")
    public void testPayPal(String route) throws Exception {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        String userData = new DataBase("PKW").getUserIdForPaymentsMethod("payments_userid_pkw", shop, "PayPal");
        String userID = parseUserIdFromBD(userData);
        String mail = parseUserMailFromBD(userData);
        float totalPriceAllData = new Product_page_Logic().addProductToCart()
                .closeBtnOFPopupReviewIfYes()
                .cartClick()
                .checkPresencePaymentsMethodLabel(new Cart_page().payPalLabel())
                .nextButtonClick()
                .signIn(mail, passwordForPayments)
                .chooseDeliveryCountryForShipping(shop)
                .fillFieldTelNumForShipping("100+001")
                .nextBtnClick()
                .checkActivePaymentMethod("paypal")
                .clickOnTheDesiredPaymentMethod(shop, "PayPal")
                .nextBtnClick()
                .checkPresencePaymentsMethodLabel(new CartAllData_page().payPalLabel())
                .getTotalPriceAllDataPage(shop);
        new CartAllData_page_Logic().payPalBtnClick();
        switchTo().window(1);
        checkingContainsUrl("paypal.com");
        closeWindow();
        switchTo().window(0);
        new CartPayments_page_Logic().checkActivePaymentMethod("paypal");
        float totalPriceOrderAws = new Customer_view_aws().openCustomerPersonalArea(userID)
                .checkPresenceOrderHistoryBlock()
                .checkAndOpenOrderWithExpectedData()
                .checkPaymentMethodInOrder("PayPal")
                .checkCurrentStatusInOrder("abgebrochene PayPal-Bestellungen")
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