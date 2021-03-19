package ATD.Payments;

import ATD.*;
import AWS.Customer_view_aws;
import AWS.Order_aws;
import Common.DataBase;
import Common.Merchant_page;
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
import static Common.CommonMethods.checkingContainsUrl;
import static Common.DataBase.parseUserIdFromBD;
import static Common.DataBase.parseUserMailFromBD;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2157 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "AT", "main", "product32");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description("Test checks method of payment by EPS")
    public void testEPS(String route) throws Exception {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        String userData = new DataBase("ATD").getUserIdForPaymentsMethod("payments_userid_atd", shop, "EPS");
        String userID = parseUserIdFromBD(userData);
        String mail = parseUserMailFromBD(userData);
        float totalPriceAllData = new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .checkPresencePaymentsMethodLabel(new Cart_page().epsLabel())
                .nextButtonClick()
                .signIn(mail, passwordForPayments)
                .chooseDeliveryCountryForShipping(shop)
                .fillFieldTelNumForShipping("100+001")
                .nextBtnClick()
                .clickOnTheDesiredPaymentMethod(shop, "EPS")
                .nextBtnClick()
                .checkPresencePaymentsMethodLabel(new CartAllData_page().epsLabel())
                .getTotalPriceAllDataPage(shop);
        new CartAllData_page_Logic().nextBtnClick();
        checkingContainsUrl("routing.eps.or.at");
        new Merchant_page().abbrechenSubmit().click();
        new CartPayments_page_Logic().checkActivePaymentMethod("epsbank");
        float totalPriceOrderAws = new Customer_view_aws().openCustomerView(userID)
                .checkPresenceOrderHistoryBlock()
                .checkAndOpenOrderWithExpectedData()
                .checkPaymentMethodInOrder("EPS Bank")
                .checkCurrentStatusInOrder("Abgebrochene")
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

