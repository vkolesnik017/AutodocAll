package ATD.Basket.QC_1388_Firms_ATD;

import ATD.CartAddress_page_Logic;
import ATD.CartAllData_page_Logic;
import ATD.Product_page_Logic;
import AWS.PageVAT_aws;
import Common.SetUp;
import AWS.Customer_view_aws;
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
import static com.codeborne.selenide.Selenide.switchTo;
import static mailinator.WebMail.passwordForMail;

public class QC_1484 {

    private String email = "QC_1484_autotestEN@autodoc.si", orderNumber, vatForGB;
    private Float totalPrice, totalPriceAWSOrder, totalPriceInEmail;

    private CartAddress_page_Logic cartAddress_page_logic = new CartAddress_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
        vatForGB = new PageVAT_aws().getVatForGB();
        close();
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "EN", "main", "product2");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the successful execution of an order with split billing, firm and physical person, " +
            "Different Countries, for EN shop. Negative Case")
    public void testFirmAndPhysicalPersonDifferentCountriesNegativeCase(String route) {
        openPage(route);
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(email, password)
                .fillAllFieldsAndFirmForShipping("EN", "YO10 4NT", "Gear4music Limited", "York")
                .fillFieldIdCompanyShipping("5520332")
                .chooseDeliveryCountryForBilling("FR")
                .fillingPostalCodeFieldJSForBilling("11111")
                .nextBtnClick();
        totalPrice = cartAddress_page_logic.checkPresencePopupErrorAboutWrongCompany()
                .clickBtnContinueInPopupAboutWrongCompany()
                .chooseUnicreditBank()
                .nextBtnClick()
                .checkTextInDeliveryAddressInfoBlock("Company Gear4music Limited")
                .checkTextInPayersAddressInfoBlock("autotest autotest")
                .checkTextContainingVatPercentage("incl. " + vatForGB + "% VAT")
                .getTotalPriceAllDataPageForEnShop();
        orderNumber = new CartAllData_page_Logic().nextBtnClick().getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumber);
        totalPriceAWSOrder = order_aws.openOrderInAwsWithLogin()
                .checkVatStatusInOrder("Mit MwSt " + vatForGB + "%")
                .checkFirmConfirmationStatus("Нет")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
        totalPriceAWSOrder = order_aws.reSaveOrder()
                .checkVatStatusInOrder("Mit MwSt " + vatForGB + "%")
                .checkFirmConfirmationStatus("Нет")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
        order_aws.clickCustomerId();
        switchTo().window(1);
        new Customer_view_aws().checkPresenceBlockLogsCompanyNumbers()
                .checkIdCompanyInBlockLogsCompanyNumbers("GB5520332")
                .checkResponseInBlockLogsCompanyNumbers("The requested VAT registration number is invalid ")
                .checkBillingOrShippingInBlockLogsCompanyNumbers("shipping");

        totalPriceInEmail = new WebMail().openMail(email, passwordForMail)
                .openLetter(1)
                .checkTextContainingVatPercentageInEmail("incl. " + vatForGB + "% VAT")
                .checkNamePhysicalPersonInEmail("autotest autotest")
                .checkSecondFirmNameInEmail("Gear4music Limited")
                .getTotalPriceInEmail();
        Assert.assertEquals(totalPrice, totalPriceInEmail);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}