package ATD.Payments;

import ATD.*;
import AWS.Customer_view_aws;
import AWS.Order_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static ATD.DataBase.parseUserIdFromBD;
import static ATD.DataBase.parseUserMailFromBD;
import static ATD.SetUp.setUpBrowser;

public class QC_2160_BraintreeCreditCard {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = false)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopsWithSubroute("prod", "BG,CH,CZ,DK,EE,EN,GR,HU,LD,LT,LV,NO,PL,RO,SE,SI,SK ", "main", "product32");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description("Test checks method of payment by BraintreeCreditCard")
    public void testBraintreeCreditCard(String route) throws Exception {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        String userData = new DataBase().getUserIdForPaymentsMethod("payments_userid_atd", shop, "CreditCard_braintree");
        String userID = parseUserIdFromBD(userData);
        String mail = parseUserMailFromBD(userData);
        float totalPriceAllData = new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .checkPresencePaymentsMethodLabel(new Cart_page().visaLabel())
                .checkPresencePaymentsMethodLabel(new Cart_page().masterCardLabel())
                .nextButtonClick()
                .signIn(mail, passwordForPayments)
                .chooseDeliveryCountryForShipping(shop)
                .fillFieldTelNumForShipping("100+001")
                .nextBtnClick()
                .checkActivePaymentMethod("braintree_creditcards")
                .clickOnTheDesiredPaymentMethod(shop, "CreditCard_braintree")
                .nextBtnClick()
                .checkPresencePaymentsMethodLabel(new CartAllData_page().visaLabel())
                .checkPresencePaymentsMethodLabel(new CartAllData_page().masterCardLabel())
                .checkPresencePaymentsMethodLabel(new CartAllData_page().americanExpressLabel())
                .getTotalPriceAllDataPage(shop);
        new CartAllData_page_Logic().nextBtnClick();
        new Merchant_page().checkPresenceElementFromMerchantPageBraintreeCreditCardAndCancelOrder("5169307507657018", "1225", "658");
        float totalPriceOrderAws = new Customer_view_aws().openCustomerPersonalArea(userID)
                .checkPresenceOrderHistoryBlock()
                .checkAndOpenOrderWithExpectedData()
                .checkPaymentMethodInOrder("Braintree")
                .checkCurrentStatusInOrder("abgebrochene Braintree")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPriceAllData, totalPriceOrderAws);
        float totalPriceOrderAwsAfterReSave = new Order_aws().reSaveOrder()
                .checkCurrentStatusInOrder("Testbestellungen")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPriceAllData, totalPriceOrderAwsAfterReSave);
    }
}