package PKW.Payments;

import AWS.Order_aws;
import Common.DataBase;
import Common.SetUp;
import PKW.*;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import mailinator.WebMail;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.canAssertThatPdfContainsText;
import static Common.DataBase.parseUserMailFromBD;
import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.*;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static mailinator.WebMail.passwordForMail;

public class QC_2474_Multibanco_PKW {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = false)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("prod", "PT", "main", "product9");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description("Test checks method of payment by Multibanco")
    public void testMultibanco(String route) throws Exception {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        String userData = new DataBase("PKW").getUserIdForPaymentsMethod("payments_userid_pkw", shop, "Multibanco");
        String mail = parseUserMailFromBD(userData);
        float totalPriceAllData = new Product_page_Logic().addProductToCart()
                .closeBtnOFPopupReviewIfYes()
                .cartClick()
                .checkPresencePaymentsMethodLabel(new Cart_page().multibancoLabel())
                .nextButtonClick()
                .signIn(mail, passwordForPayments)
                .chooseDeliveryCountryForShipping(shop)
                .fillFieldTelNumForShipping("200+002")
                .nextBtnClick()
                .clickOnTheDesiredPaymentMethod(shop, "Multibanco")
                .nextBtnClick()
                .checkPresencePaymentsMethodLabel(new CartAllData_page().multibancoLabel())
                .getTotalPriceAllDataPage(shop);
        String requisitesText = new CartAllData_page_Logic().nextBtnClick()
                .closePopupAfterOrder()
                .clickOnLinkForPDF()
                .checkOrganizationName("12057")
                .comparesPriceOfOrderDetailsWithPriceOnAllDataPage(totalPriceAllData)
                .getTextRequisites();
        canAssertThatPdfContainsText("C:/Users/User/Downloads/bank_info.pdf", requisitesText);
        String orderNum = new Payment_handler_page_Logic().getOrderNumber();
        float totalPriceOrderAws = new Order_aws(orderNum).openOrderInAwsWithLogin()
                .checkPaymentMethodInOrder("B2B - Multibanco")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPriceAllData, totalPriceOrderAws);
        float totalPriceOrderAwsAfterReSave = new Order_aws().reSaveOrder()
                .checkCurrentStatusInOrder("Testbestellungen")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPriceAllData, totalPriceOrderAwsAfterReSave);

        new WebMail().openMail(mail, passwordForMail)
                .checkAndOpenLetterWithOrderNumber(orderNum)
                .comparesTextOfRequisitesInMailWithExpectedRequisites(requisitesText);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}