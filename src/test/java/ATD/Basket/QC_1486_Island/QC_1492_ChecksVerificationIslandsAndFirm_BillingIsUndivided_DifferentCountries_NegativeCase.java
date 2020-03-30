package ATD.Basket.QC_1486_Island;

import ATD.CartAllData_page_Logic;
import ATD.Search_page_Logic;
import ATD.SetUp;
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

public class QC_1492_ChecksVerificationIslandsAndFirm_BillingIsUndivided_DifferentCountries_NegativeCase {

    private String email = "qc_1492_autotestDE@mailinator.com", orderNumber;
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
    @Description(value = "Test checks verification of islands + Firm, Different Countries, billing is undivided (Positive case)")
    public void testChecksVerificationIslandsAndFirmAndDifferentCountriesBillingIsUndividedPositiveCas(String route) {
        openPage(route);
        clickOfBuyBtnForAllPages();
        totalPrice = new Search_page_Logic().closePopupOtherCategoryIfYes()
                .cartClick().nextButtonClick()
                .signIn(email, password)
                .clickCheckboxBilling()
                .nextBtnClick()
                .checkPresenceOfPayPalMethod()
                .chooseVorkasse().nextBtnClick()
                .checkAbsenceOfVatPercentage()
                .checkRegularDeliveryPriceAllData("9,09")
                .checkPresenceSafeOrderBlock()
                .getTotalPriceAllDataPage();
        orderNumber = new CartAllData_page_Logic().nextBtnClick().getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumber);
        totalPriceAWSOrder = order_aws.openOrderInAwsWithLogin()
                .checkVatStatusInOrder("Ohne Mwst")
                .checkDeliveryPriceOrderAWS("9.09")
                .getTotalPriceOrder();
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
        order_aws.reSaveOrder()
                .checkVatStatusInOrder("Ohne Mwst")
                .checkDeliveryPriceOrderAWS("9.09");
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
        totalPriceInEmail = new Mailinator().openEmail("qc_1492_autotestDE@mailinator.com")
                .openLetter(1)
                .checkRegularDeliveryPriceInEmail("9,09")
                .checkAbsenceVatPercentageInEmail()
                .getTotalPriceInEmail();
        Assert.assertEquals(totalPrice, totalPriceInEmail);
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
