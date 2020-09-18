package ATD.Basket.QC_1486_Island;

import ATD.CartAddress_page_Logic;
import ATD.CartAllData_page_Logic;
import ATD.Search_page_Logic;
import Common.SetUp;
import AWS.Order_aws;
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

import static ATD.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static mailinator.WebMail.passwordForMail;

public class QC_1491_ChecksVerificationIslandsAndFirm_BillingIsUndivided_IncorrectCompanyData {

    private String email = "QC_1491_autotest@autodoc.si", orderNumber;
    private Float totalPrice, totalPriceAWSOrder, totalPriceInEmail;

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
    @Description(value = "Test checks verification of islands + Firm, billing is undivided, incorrect company data")
    public void testChecksVerificationIslandsAndFirmIncorrectCompanyData(String route) {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        clickOfBuyBtnForAllPages();
        new Search_page_Logic().closePopupOtherCategoryIfYes()
                .cartClick().nextButtonClick()
                .signIn(email, password)
                .fillingPostalCodeField("20000")
                .nextBtnClick();
        totalPrice = new CartAddress_page_Logic().checkPresencePopupErrorAboutWrongCompany()
                .clickBtnEinkaufFortsetzenFromPopupErrorAboutWrongCompany()
                .checkAbsenceOfPayPalMethod()
                .chooseVorkasse().nextBtnClick()
                .checkRegularDeliveryPriceAllData("10,95")
                .checkTextContainingVatPercentage("inkl. 20% MwSt.")
                .checkPresenceSafeOrderBlock()
                .getTotalPriceAllDataPage(shop);
        orderNumber = new CartAllData_page_Logic().nextBtnClick().getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumber);
        totalPriceAWSOrder = order_aws.openOrderInAwsWithLogin()
                .checkVatStatusInOrder("Mit MwSt 20%")
                .checkDeliveryPriceOrderAWS("10.95")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
        order_aws.reSaveOrder()
                .checkVatStatusInOrder("Mit MwSt 20%")
                .checkDeliveryPriceOrderAWS("10.95");
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);

        totalPriceInEmail = new WebMail().openMail("QC_1491_autotest@autodoc.si", passwordForMail)
                .checkAndOpenLetterWithOrderNumber(orderNumber)
                .checkRegularDeliveryPriceInEmail("10,95")
                .checkTextContainingVatPercentageInEmail("inkl. 20% MwSt")
                .getTotalPriceInEmail();
        Assert.assertEquals(totalPrice, totalPriceInEmail);

        openPage(route);
        clickOfBuyBtnForAllPages();
        new Search_page_Logic().closePopupOtherCategoryIfYes()
                .cartClick().nextButtonClick();
        new CartAddress_page_Logic().fillingPostalCodeField("97100")
                .nextBtnClick();
        checkingContainsUrl("/basket/payments");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
