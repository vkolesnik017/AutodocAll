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

import static ATD.CommonMethods.openPage;
import static ATD.CommonMethods.password;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.switchTo;
import static mailinator.WebMail.passwordForMail;

public class QC_1483 {

    private String email = "QC_1483_autotestEN@autodoc.si", orderNumber, vatForNL;
    private Float totalPrice, totalPriceAWSOrder, totalPriceInEmail;

    private CartAddress_page_Logic cartAddress_page_logic = new CartAddress_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
        vatForNL = new PageVAT_aws().getVatForNL();
        close();
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProduct() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "EN", "main", "product2");
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
                .checkTextContainingVatPercentage("incl. " + vatForNL + "% VAT")
                .getTotalPriceAllDataPageForEnShop();
        orderNumber = new CartAllData_page_Logic().nextBtnClick().getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumber);
        totalPriceAWSOrder = order_aws.openOrderInAwsWithLogin()
                .checkVatStatusInOrder("Mit MwSt " + vatForNL + "%")
                .checkFirmConfirmationStatus("??????. ??????/??????????")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
        totalPriceAWSOrder = order_aws.reSaveOrder()
                .checkVatStatusInOrder("Mit MwSt " + vatForNL + "%")
                .checkFirmConfirmationStatus("??????. ??????/??????????")
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

        totalPriceInEmail = new WebMail().openMail(email, passwordForMail)
                .openLetter(1)
                .checkTextContainingVatPercentageInEmail("incl. " + vatForNL + "% VAT")
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