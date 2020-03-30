package ATD.Basket.QC_1486_Island;

import ATD.*;
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
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_1491_ChecksVerificationIslandsAndFirm_BillingIsUndivided_IncorrectCompanyData {

    private String email = "qc_1491_autotestDE@mailinator.com", orderNumber;
    private Double totalPrice, totalPriceAWSOrder, totalPriceInEmail;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "search9");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks verification of islands + Firm, billing is undivided (Positive case)")
    public void testChecksVerificationIslandsAndFirmBillingIsUndividedPositiveCas(String route) {
        openPage(route);
        clickOfBuyBtnForAllPages();
        new Search_page_Logic().closePopupOtherCategoryIfYes()
                .cartClick().nextButtonClick()
                .signIn(email, password)
                .nextBtnClick();
        totalPrice = new CartAddress_page_Logic().checkPresencePopupErrorAboutWrongCompany()
                .clickBtnEinkaufFortsetzenFromPopupErrorAboutWrongCompany()
                .checkAbsenceOfPayPalMethod()
                .chooseVorkasse().nextBtnClick()
                .checkRegularDeliveryPriceAllData("10,95")
                .checkTextContainingVatPercentage("inkl. 20% MwSt.")
                .checkPresenceSafeOrderBlock()
                .getTotalPriceAllDataPage();
        orderNumber = new CartAllData_page_Logic().nextBtnClick().getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumber);
        totalPriceAWSOrder = order_aws.openOrderInAwsWithLogin()
                .checkVatStatusInOrder("Mit MwSt 20%")
                .checkDeliveryPriceOrderAWS("10.95")
                .getTotalPriceOrder();
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
        order_aws.reSaveOrder()
                .checkVatStatusInOrder("Mit MwSt 20%")
                .checkDeliveryPriceOrderAWS("10.95");
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
        totalPriceInEmail = new Mailinator().openEmail("qc_1491_autotestDE@mailinator.com")
                .openLetter(1)
                .checkRegularDeliveryPriceInEmail("10,95")
                .checkTextContainingVatPercentageInEmail("Inkl. 20% MwSt.")
                .getTotalPriceInEmail();
        Assert.assertEquals(totalPrice, totalPriceInEmail);
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
