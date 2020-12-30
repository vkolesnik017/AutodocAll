package PKW.Payments;

import AWS.Customer_view_aws;
import Common.DataBase;
import Common.Merchant_page;
import Common.SetUp;
import PKW.*;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
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

public class QC_2392_CheckingSwitchingOnBraintreeOnFronts_PKW {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("PKW").setUpShopsWithSubroute("prod", "AT,NL,DE,FI,SE,ES,IT,PT", "main", "product");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description("Test for checking switching on Braintree on fronts")
    public void testForCheckingSwitchingOnBraintreeOnFronts(String route) throws Exception {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        String userData = new DataBase("PKW").getUserIdForPaymentsMethod("payments_userid_pkw", shop, "CreditCard_be2bill");
        String userID = parseUserIdFromBD(userData);
        String mail = parseUserMailFromBD(userData);
        new Product_page_Logic().addProductToCart()
                .closeBtnOFPopupReviewIfYes()
                .cartClick()
                .checkPresencePaymentsMethodLabel(new Cart_page().visaLabel(), new Cart_page().masterCardLabel())
                .checksPresenceOfPaymentMethodsLabelForRequiredCountry(shop,"IT", new Cart_page().cartaSiLabel(), new Cart_page().postePayLabel())
                .nextButtonClick()
                .signIn(mail, passwordForPayments)
                .chooseDeliveryCountryForShipping(shop)
                .fillFieldTelNumForShipping("100+001")
                .nextBtnClick()
                .checksPresencePaymentsMethodLabelForRequiredCountry(shop)
                .clickOnTheDesiredPaymentMethod(shop, "CreditCard_be2bill")
                .nextBtnClick()
                .checksPresencePaymentsMethodLabelForRequiredCountry(shop)
                .nextBtnClick();
        new Merchant_page().checkPresenceElementFromMerchantPageB2billCreditCardAndCancelOrder("5169307507657018", "1225", "658");
        new CartPayments_page_Logic().checkActivePaymentMethod("be2bill");
        new Customer_view_aws().openCustomerPersonalArea(userID)
                .checkPresenceOrderHistoryBlock()
                .checkAndOpenOrderWithExpectedData()
                .checkPaymentMethodInOrder("Be2bill")
                .checkCurrentStatusInOrder("Abgebrochene")
                .reSaveOrder()
                .checkCurrentStatusInOrder("Testbestellungen");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}