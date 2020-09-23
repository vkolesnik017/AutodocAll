package PKW.Payments;

import AWS.Customer_view_aws;
import AWS.Order_aws;
import Common.DataBase;
import Common.Merchant_page;
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
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2467_Klarna {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("PKW").setUpShopsWithSubroute("prod", "DE,AT,DK,NL", "main", "product9");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description("Test checks method of payment by BraintreeCreditCard")
    public void testBraintreeCreditCard(String route) throws Exception {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        String userData = new DataBase("PKW").getUserIdForPaymentsMethod("payments_userid_pkw", shop, "Klarna");
        String userID = parseUserIdFromBD(userData);
        String mail = parseUserMailFromBD(userData);
        float totalPriceAllData = new Product_page_Logic().addProductToCart()
                .closeBtnOFPopupReviewIfYes()
                .cartClick()
                .checkPresencePaymentsMethodLabel(new Cart_page().klarnaLabel())
                .nextButtonClick()
                .signIn(mail, passwordForPayments)
                .chooseDeliveryCountryForShipping(shop)
                .fillFieldTelNumForShipping("100+001")
                .nextBtnClick()
                .clickOnTheDesiredPaymentMethod(shop, "Klarna")
                .nextBtnClick()
                .checkPresencePaymentsMethodLabel(new CartAllData_page().klarnaLabel())
                .getTotalPriceAllDataPage(shop);
        new CartAllData_page_Logic().nextBtnClick();
        new Merchant_page().checkPresenceElementFromMerchantPageForPaymentKlarna();
        new CartPayments_page_Logic().checkActivePaymentMethod("klarna");
        float totalPriceOrderAws = new Customer_view_aws().openCustomerPersonalArea(userID)
                .checkPresenceOrderHistoryBlock()
                .checkAndOpenOrderWithExpectedData()
                .checkPaymentMethodInOrder("Klarna")
                .checkCurrentStatusInOrder(": abgebrochen Klarna")
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