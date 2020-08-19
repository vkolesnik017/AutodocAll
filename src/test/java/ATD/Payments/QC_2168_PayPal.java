package ATD.Payments;

import ATD.CartAllData_page_Logic;
import ATD.DataBase;
import ATD.Product_page_Logic;
import ATD.SetUp;
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
import static com.codeborne.selenide.Selenide.*;

public class QC_2168_PayPal {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopsWithSubroute("prod", "DE,AT,BG,BE,CH,CZ,DK,EN,EE,ES,FI,FR,GR,HU,IT,LD,LT,LV,NL,NO,PL,PT,RO,SE,SI,SK", "main", "product32");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description("Test checks method of payment by PayPal")
    public void testPayPal(String route) throws Exception {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        String userData = new DataBase().getUserIdForPaymentsMethod("payments_userid_atd", shop, "PayPal");
        String userID = parseUserIdFromBD(userData);
        String mail = parseUserMailFromBD(userData);
        float totalPriceAllData = new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .checkPresencePayPalLabel()
                .nextButtonClick()
                .signIn(mail, passwordForPayments)
                .chooseDeliveryCountryForShipping(shop)
                .fillFieldTelNumForShipping("100+001")
                .nextBtnClick()
                .checkDefaultActivePaymentMethod("paypal")
                .clickOnTheDesiredPaymentMethod(shop, "PayPal")
                .nextBtnClick()
                .checkPresencePayPalLabel()
                .getTotalPriceAllDataPage(shop);
                new CartAllData_page_Logic().payPalBtnClick();
        switchTo().window(1);
        checkingContainsUrl("paypal.com");
        closeWindow();
        switchTo().window(0);
        float totalPriceOrderAws = new Customer_view_aws().openCustomerPersonalArea(userID)
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