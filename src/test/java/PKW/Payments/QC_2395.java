package PKW.Payments;

import AWS.SearchOrders_page_aws;
import Common.DataBase;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.DataBase.parseUserIdFromBD;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2395 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "shop", parallel = true)
    Object[] dataProviderShop() {
        return new Object[][]{
                {"AT"},
                {"NL"},
                {"DE"},
                {"FI"},
                {"SE"},
                {"ES"},
                {"IT"},
                {"PT"}
        };
    }

    @Test(dataProvider = "shop")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description("Test for checking switching on Braintree on AWS")
    public void testForCheckingSwitchingOnBraintreeOnAWS(String shop) throws SQLException {
        String userData = new DataBase("PKW").getUserIdForPaymentsMethod("payments_userid_pkw", shop, "Braintree_AWS");
        String userID = parseUserIdFromBD(userData);
        new SearchOrders_page_aws().openSearchOrderPageWithLogin()
                .clickAddOrderBtn()
                .fillsInFieldCustomerID(userID)
                .selectShopForSkinPKWFopSpecificCountry(shop)
                .selectedCreditCardMethod(shop,"Be2bill (Visa/MC)")
                .selectedDeliveryMethod("Standardversand")
                .addProduct("50-307044-50")
                .checkPresenceTableOfSuppliersAndClickBtnSelect()
                .clickSaveOrderBtn()
                .checkPaymentMethodInOrder("Be2bill")
                .checkCurrentStatusInOrder("Neue Bestellung")
                .reSaveOrder()
                .checkCurrentStatusInOrder("Testbestellungen");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
