package PKW.Payments;

import AWS.Order_aws;
import PKW.CartAllData_page_Logic;
import PKW.Payment_handler_page_Logic;
import PKW.Product_page_Logic;
import Common.DataBase;
import Common.SetUp;
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
import static Common.DataBase.parseUserMailFromBD;
import static Common.File.*;
import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.*;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static mailinator.WebMail.passwordForMail;

public class QC_2464_Bank_PKW {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);

    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("PKW").setUpShopsWithSubroute("prod", "DE,AT,BG,CH,CZ,DK,ES,FI,FR,GR,HU,IT,NL,NO,PL,PT,RO,SE,EN", "main", "product");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description("Test checks method of payment by Bank")
    public void testPaymentsMethodBank(String route) throws Exception {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        String userData = new DataBase("PKW").getUserIdForPaymentsMethod("payments_userid_pkw", shop, "Bank");
        String mail = parseUserMailFromBD(userData);
        float totalPriceAllData = new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .checksForLabelOfBankPaymentMethod()
                .nextButtonClick()
                .signIn(mail, passwordForPayments)
                .chooseDeliveryCountryAndFillingFirmInput( shop, "autotest")
                .fillInCompanyIdFieldForCountryWhereIdNeeded(shop, shop, "autotest")
                .clickOnTheDesiredPaymentMethod(shop, "Bank")
                .nextBtnClick()
                .checksForLabelOfBankPaymentMethod()
                .getTotalPriceAllDataPage(shop);
        String requisitesText = new CartAllData_page_Logic().nextBtnClick()
                .closePopupAfterOrder()
                .clickOnLinkForPDF()
                .compareExpectedRequisitesWithActual(shop)
                .checksPriceOrderInRequisites(totalPriceAllData)
                .getTextRequisites();
        renameDownloadFile("C:/Users/User/Downloads/bank_info.pdf", "C:/Users/User/Downloads/bank_info_" + shop + ".pdf");
        assertThatPdfContainsText("C:/Users/User/Downloads/bank_info_" + shop + ".pdf", requisitesText);
        String orderNum = new Payment_handler_page_Logic().getOrderNumber();
        float totalPriceOrderAws = new Order_aws(orderNum).openOrderInAwsWithLogin()
                .checkPaymentMethodInOrder("Bank Austria","HypoVereinsbank","Vorkasse","SEB", "UniCredit Bank",
                                           "Przelew Bankowy","PostFinance")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPriceAllData, totalPriceOrderAws);
        float totalPriceOrderAwsAfterReSave = new Order_aws().reSaveOrder()
                .checkCurrentStatusInOrder("Testbestellungen")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPriceAllData, totalPriceOrderAwsAfterReSave);

        new WebMail().openMail(mail, passwordForMail)
                .checkAndOpenLetterWithOrderNumber(orderNum)
                .compareExpectedRequisitesWithActual(shop);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
