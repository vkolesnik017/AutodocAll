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
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.switchTo;

public class QC_1483_SplitBilling_TwoFirms_DifferentCountries_NegativeCase {

    private String email = "qc_1483_autotestEN@mailinator.com", orderNumber;
    private Float totalPrice, totalPriceAWSOrder, totalPriceInEmail;

    private CartAddress_page_Logic cartAddress_page_logic = new CartAddress_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProduct() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "EN", "main", "product2");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the successful execution of an order with split billing, two firms, the different countries. Negative Case")
    public void testSuccessfulPlacementOfOrderTwoFirmsDifferentCountriesNegativeCase(String route) {
        openPage(route);
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(email, password)
                .fillAllFieldsAndFirmForShipping("NL", "1221 JS", "yyyy", "HILVERSUM")
                .fillFieldIdCompanyShipping("855768800B01")
                .fillAllFieldsAndFirmForBilling("BE", "1070", "Anderlecht", "SPRL Brasserie Cantillon")
                .fillFieldIdCompanyBilling("0402065988")
                .nextBtnClick();
        cartAddress_page_logic.checkPresencePopupErrorAboutWrongCompany()
                .clickBtnFixInPopupAboutWrongCompany()
                .nextBtnClick();
        totalPrice = cartAddress_page_logic.checkPresencePopupErrorAboutWrongCompany()
                .clickBtnContinueInPopupAboutWrongCompany()
                .chooseUnicreditBank()
                .nextBtnClick()
                .checkTextInDeliveryAddressInfoBlock("Company yyyy")
                .checkTextInPayersAddressInfoBlock("SPRL Brasserie Cantillon")
                .checkTextContainingVatPercentage("incl. 21% VAT")
                .getTotalPriceAllDataPageForEnShop();
        orderNumber = new CartAllData_page_Logic().nextBtnClick().getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumber);
        totalPriceAWSOrder = order_aws.openOrderInAwsWithLogin()
                .checkVatStatusInOrder("Mit MwSt 21%")
                .checkFirmConfirmationStatus("Нев. имя/город")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
        totalPriceAWSOrder = order_aws.reSaveOrder()
                .checkVatStatusInOrder("Mit MwSt 21%")
                .checkFirmConfirmationStatus("Нев. имя/город")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
        order_aws.clickCustomerId();
        switchTo().window(1);
        new Customer_view_aws().checkPresenceBlockLogsCompanyNumbers()
                .checkIdCompanyInBlockLogsCompanyNumbers("NL855768800B01")
                .checkResponseInBlockLogsCompanyNumbers("success(200)")
                .checkBillingOrShippingInBlockLogsCompanyNumbers("shipping")
                .checkErrorStatusInNameErrorColumn("yes")
                .checkErrorStatusInCityErrorColumn("no");
        totalPriceInEmail = new Mailinator().openEmail("qc_1483_autotestEN@mailinator.com")
                .openLetter(1)
                .checkTextContainingVatPercentageInEmail("incl. 21% VAT")
                .checkFirstFirmNameInEmail("SPRL Brasserie Cantillon")
                .checkSecondFirmNameInEmail("yyyy")
                .getTotalPriceInEmail();
        Assert.assertEquals(totalPrice, totalPriceInEmail);
    }


    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}