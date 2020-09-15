package ATD.Basket.QC_1486_Island;

import ATD.CartAllData_page_Logic;
import ATD.Search_page_Logic;
import Common.SetUp;
import AWS.Order_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import mailinator.Mailinator;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1494_ChecksVerificationIslandsAndFirm_BillingIsUndivided_CorrectCompanyData {

    private String email = "qc_1494_autotestDE@mailinator.com", orderNumber;
    private Float totalPrice, totalPriceInEmail;
    private Float totalPriceAWSOrder;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "search9");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks verification of islands + Firm, billing is undivided (Correct Company Data)")
    public void testChecksVerificationIslandsAndFirmCorrectCompanyData(String route) {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        clickOfBuyBtnForAllPages();
        totalPrice = new Search_page_Logic().closePopupOtherCategoryIfYes()
                .cartClick().nextButtonClick()
                .signIn(email, password)
                .nextBtnClick()
                .checkAbsenceOfPayPalMethod()
                .chooseVorkasse()
                .nextBtnClick()
                .checkPresenceSafeOrderBlock()
                .checkAbsenceOfVatPercentage()
                .checkRegularDeliveryPriceAllData("13,00")
                .getTotalPriceAllDataPage(shop);
        orderNumber = new CartAllData_page_Logic().nextBtnClick().getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumber);
        totalPriceAWSOrder = order_aws.openOrderInAwsWithLogin()
                .checkVatStatusInOrder("Ohne Mwst")
                .checkDeliveryPriceOrderAWS("13")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
        order_aws.reSaveOrder()
                .checkVatStatusInOrder("Ohne Mwst")
                .checkDeliveryPriceOrderAWS("13");
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
        //TODO отключен по техническим обстоятельствам
        /*totalPriceInEmail = new Mailinator().openEmail("qc_1494_autotestDE@mailinator.com")
                .openLetter(1)
                .checkRegularDeliveryPriceInEmail("13,00")
                .checkAbsenceVatPercentageInEmail()
                .getTotalPriceInEmail();
        Assert.assertEquals(totalPrice, totalPriceInEmail);*/
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}