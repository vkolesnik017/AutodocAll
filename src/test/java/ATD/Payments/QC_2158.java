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
import static Common.DataBase.parseUserIdFromBD;
import static Common.DataBase.parseUserMailFromBD;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2158 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("ATD").setUpShopsWithSubroute("prod", "AT,DE,DK,NL,FI,SE,NO", "main", "product32");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description("Test checks method of payment by Klarna")
    public void testKlarna(String route) throws Exception {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        String userData = new DataBase("ATD").getUserIdForPaymentsMethod("payments_userid_atd", shop, "Klarna");
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
                .clickOnTheDesiredPaymentMethod(shop, "Klarna")
                .nextBtnClick()
                .checkPresencePaymentsMethodLabel(new CartAllData_page().klarnaLabel())
                .getTotalPriceAllDataPage(shop);
        new CartAllData_page_Logic().nextBtnClick();
        new Merchant_page().checkPresenceElementFromMerchantPageForPaymentKlarna()
                .checkActivePaymentMethod("klarna");
        float totalPriceOrderAws = new Customer_view_aws().openCustomerView(userID)
                .checkPresenceOrderHistoryBlock()
                .checkAndOpenOrderWithExpectedData()
                .checkPaymentMethodInOrder("Klarna")
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