package ATD.Basket.QC_1486_Island;

import ATD.CartAllData_page_Logic;
import ATD.Search_page_Logic;
import ATD.SetUp;
import ATD.Versand_static_page_Logic;
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

public class QC_1492_ChecksVerificationIslandsAndFirm_BillingIsDivided_DifferentCountries_NegativeCase {

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
    @Description(value = "Test checks verification of islands + Firm, Different Countries, billing is divided (Negative case)")
    public void testChecksVerificationIslandsAndFirmAndDifferentCountries(String route) throws Exception {
        String deliveryPriceToUKalldata = new Versand_static_page_Logic().deliveryPriceToUK();
        Double deliveryPriceToUKaws = new Versand_static_page_Logic().deliveryPriceToUKforAWS();
        openPage(route);
        clickOfBuyBtnForAllPages();
        totalPrice = new Search_page_Logic().closePopupOtherCategoryIfYes()
                .cartClick().nextButtonClick()
                .signIn(email, password)
                .fillAllFieldsAndFirmForShipping("EN", "12345", "Gear4music Limited", "York")
                .fillFieldTaxNumberShipping("552033282")
                .clickCheckboxBilling()
                .chooseDeliveryCountryForBilling("FR")
                .fillingPostalCodeFieldJSForBilling("20000")
                .nextBtnClick()
                .checkPresenceOfPayPalMethod()
                .chooseVorkasse().nextBtnClick()
                .checkAbsenceOfVatPercentage()
                .checkRegularDeliveryPriceAllData(deliveryPriceToUKalldata)
                .checkPresenceSafeOrderBlock()
                .getTotalPriceAllDataPage();
        orderNumber = new CartAllData_page_Logic().nextBtnClick().getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumber);
        totalPriceAWSOrder = order_aws.openOrderInAwsWithLogin()
                .checkVatStatusInOrder("Ohne Mwst")
                .checkDeliveryPriceOrderAWS(deliveryPriceToUKaws)
                .getTotalPriceOrder();
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
        totalPriceAWSOrder = order_aws.reSaveOrder()
                .checkVatStatusInOrder("Ohne Mwst")
                .checkDeliveryPriceOrderAWS(deliveryPriceToUKaws)
                .getTotalPriceOrder();
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
        totalPriceInEmail = new Mailinator().openEmail("qc_1492_autotestDE@mailinator.com")
                .openLetter(1)
                .checkRegularDeliveryPriceInEmail(deliveryPriceToUKalldata)
                .checkAbsenceVatPercentageInEmail()
                .getTotalPriceInEmail();
        Assert.assertEquals(totalPrice, totalPriceInEmail);
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
