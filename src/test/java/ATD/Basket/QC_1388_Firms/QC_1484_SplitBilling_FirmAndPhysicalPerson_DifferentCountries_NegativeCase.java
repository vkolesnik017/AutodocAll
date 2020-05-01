package ATD.Basket.QC_1388_Firms;

import ATD.CartAddress_page_Logic;
import ATD.CartAllData_page_Logic;
import ATD.Product_page_Logic;
import ATD.SetUp;
import AWS.Customer_view_aws;
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

import static ATD.CommonMethods.openPage;
import static ATD.CommonMethods.password;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.switchTo;

public class QC_1484_SplitBilling_FirmAndPhysicalPerson_DifferentCountries_NegativeCase {

    private String email = "qc_1484_autotestEN@mailinator.com", orderNumber;
    private Double totalPrice, totalPriceAWSOrder, totalPriceInEmail;

    private CartAddress_page_Logic cartAddress_page_logic = new CartAddress_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "EN", "main", "product2");
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
                .chosseUnicreditBank()
                .nextBtnClick()
                .checkTextInDeliveryAddressInfoBlock("Company Gear4music Limited")
                .checkTextInPayersAddressInfoBlock("autotest autotest")
                .checkTextContainingVatPercentage("incl. 20% VAT")
                .getTotalPriceAllDataPageForEnShop();
        orderNumber = new CartAllData_page_Logic().nextBtnClick().getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumber);
        totalPriceAWSOrder = order_aws.openOrderInAwsWithLogin()
                .checkVatStatusInOrder("Mit MwSt 20%")
                .checkFirmConfirmationStatus("Нет")
                .getTotalPriceOrder();
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
        totalPriceAWSOrder = order_aws.reSaveOrder()
                .checkVatStatusInOrder("Mit MwSt 20%")
                .checkFirmConfirmationStatus("Нет")
                .getTotalPriceOrder();
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
        order_aws.clickCustomerId();
        switchTo().window(1);
        new Customer_view_aws().checkPresenceBlockLogsCompanyNumbers()
                .checkIdCompanyInBlockLogsCompanyNumbers("GB5520332")
                .checkResponseInBlockLogsCompanyNumbers("The requested VAT registration number is invalid ")
                .checkBillingOrShippingInBlockLogsCompanyNumbers("shipping");
        totalPriceInEmail = new Mailinator().openEmail("qc_1484_autotestEN@mailinator.com")
                .openLetter(1)
                .checkTextContainingVatPercentageInEmail("incl. 20% VAT")
                .checkNamePhysicalPersonInEmail("autotest autotest")
                .checkSecondFirmNameInEmail("Gear4music Limited")
                .getTotalPriceInEmailForENShop();
        Assert.assertEquals(totalPrice, totalPriceInEmail);
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}