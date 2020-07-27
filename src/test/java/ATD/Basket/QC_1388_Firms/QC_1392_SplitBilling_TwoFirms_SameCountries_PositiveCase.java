package ATD.Basket.QC_1388_Firms;

import ATD.CartAllData_page_Logic;
import ATD.Product_page_Logic;
import ATD.SetUp;
import AWS.Customer_view_aws;
import AWS.Order_aws;
import AWS.PageVAT_aws;
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
import static com.codeborne.selenide.Selenide.switchTo;

public class QC_1392_SplitBilling_TwoFirms_SameCountries_PositiveCase {

    private Float priceWithVatPerAllDataPageGB, priceProductPerProductPageGB, totalPriceGB, totalPriceAWSOrderGB, totalPriceInEmailGB,
            prunedProductPriceGB, prunedPriceWithVatGB;
    private String emailGB = "qc_1392_autotestGB@mailinator.com", vatForGB, orderNumberGB;

    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private CartAllData_page_Logic cartAllData_page_logic = new CartAllData_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routeEN", parallel = true)
    Object[] dataProviderProductsEN() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "EN", "main", "product32");
    }

    @Test(dataProvider = "routeEN")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the successful execution of an order with split billing, two firms, the same countries, for EN shop. Positive Case")
    public void testSuccessfulPlacementOfOrder_SplitBilling_TwoFirm_EN(String routeEN) {
        vatForGB = new PageVAT_aws().getVatForGB();
        openPage(routeEN);
        priceWithVatPerAllDataPageGB = product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(emailGB, password)
                .fillAllFieldsAndFirmForShipping("BE", "NG11GF", "North 51 Ltd", "BioCity")
                .fillFieldIdCompanyShipping("789964142")
                .fillAllFieldsAndFirmForBilling("BE", "1070", "Anderlecht", "SPRL Brasserie Cantillon")
                .fillFieldIdCompanyBilling("0402065988")
                .nextBtnClick()
                .choseUnicreditBank()
                .nextBtnClick()
                .checkTextInDeliveryAddressInfoBlock("Company North 51 Ltd")
                .checkTextInPayersAddressInfoBlock("Company SPRL Brasserie Cantillon")
                .checkAbsenceOfVatPercentage()
                .getPriceIncludingVat(vatForGB);
        prunedPriceWithVatGB = cutPriceToFirstDecimalPlace(priceWithVatPerAllDataPageGB);
        cartAllData_page_logic.transitionToProductPage();
        switchTo().window(1);
        priceProductPerProductPageGB = product_page_logic.getProductPrice();
        prunedProductPriceGB = cutPriceToFirstDecimalPlace(priceProductPerProductPageGB);
        Assert.assertEquals(prunedPriceWithVatGB, prunedProductPriceGB);
        product_page_logic.cartClick();
        totalPriceGB = cartAllData_page_logic.getTotalPriceAllDataPageForEnShop();
        orderNumberGB = cartAllData_page_logic.nextBtnClick().getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumberGB);
        totalPriceAWSOrderGB = order_aws.openOrderInAwsWithoutLoginAndCheckTestIcon()
                .checkVatStatusInOrder("Ohne Mwst")
                .checkFirmConfirmationStatus("ДА/auto")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPriceGB, totalPriceAWSOrderGB);
        totalPriceAWSOrderGB = order_aws.reSaveOrder()
                .checkVatStatusInOrder("Ohne Mwst")
                .checkFirmConfirmationStatus("ДА/auto")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPriceGB, totalPriceAWSOrderGB);
        order_aws.clickCustomerId();
        switchTo().window(2);
        new Customer_view_aws().checkPresenceBlockLogsCompanyNumbers()
                .checkIdCompanyInBlockLogsCompanyNumbers("BE0402065988")
                .checkResponseInBlockLogsCompanyNumbers("success(200)")
                .checkBillingOrShippingInBlockLogsCompanyNumbers("billing");
        totalPriceInEmailGB = new Mailinator().openEmail("qc_1392_autotestGB@mailinator.com")
                .openLetter(1)
                .checkAbsenceVatPercentageInEmail()
                .checkFirstFirmNameInEmail("SPRL Brasserie Cantillon")
                .checkSecondFirmNameInEmail("North 51 Ltd")
                .getTotalPriceInEmail();
        Assert.assertEquals(totalPriceGB, totalPriceInEmailGB);
    }


    private Float priceWithVatPerAllDataPageDE, priceProductPerProductPageDE, totalPriceDE, totalPriceAWSOrderDE, totalPriceInEmailDE,
            prunedProductPriceDE, prunedPriceWithVatDE;
    private String emailDE = "qc_1392_autotestDE@mailinator.com", vatForDE, orderNumberDE;


    @DataProvider(name = "routeDE", parallel = true)
    Object[] dataProviderProductsDE() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "product32");
    }

    @Test(dataProvider = "routeDE")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the successful execution of an order with split billing, two firms, the same countries, for DE shop. Positive Case")
    public void testSuccessfulPlacementOfOrder_SplitBilling_TwoFirm_DE(String routeDE) {
        vatForDE = new PageVAT_aws().getVatForDE();
        openPage(routeDE);
        priceWithVatPerAllDataPageDE = product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(emailDE, password)
                .fillAllFieldsAndFirmForShipping("BE", "NG11GF", "North 51 Ltd", "BioCity")
                .fillFieldIdCompanyShipping("789964142")
                .fillAllFieldsAndFirmForBilling("BE", "1070", "Anderlecht", "SPRL Brasserie Cantillon")
                .fillFieldIdCompanyBilling("0402065988")
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .checkTextInDeliveryAddressInfoBlock("Firma North 51 Ltd")
                .checkTextInPayersAddressInfoBlock("SPRL Brasserie Cantillon")
                .checkAbsenceOfVatPercentage()
                .getPriceIncludingVat(vatForDE);
        prunedPriceWithVatDE = cutPriceToFirstDecimalPlace(priceWithVatPerAllDataPageDE);
        cartAllData_page_logic.transitionToProductPage();
        switchTo().window(1);
        priceProductPerProductPageDE = product_page_logic.getProductPrice();
        prunedProductPriceDE = cutPriceToFirstDecimalPlace(priceProductPerProductPageDE);
        Assert.assertEquals(prunedPriceWithVatDE, prunedProductPriceDE);
        product_page_logic.cartClick();
        totalPriceDE = cartAllData_page_logic.getTotalPriceAllDataPage();
        orderNumberDE = cartAllData_page_logic.nextBtnClick().getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumberDE);
        totalPriceAWSOrderDE = order_aws.openOrderInAwsWithoutLoginAndCheckTestIcon()
                .checkVatStatusInOrder("Ohne Mwst")
                .checkFirmConfirmationStatus("ДА/auto")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPriceDE, totalPriceAWSOrderDE);
        totalPriceAWSOrderDE = order_aws.reSaveOrder()
                .checkVatStatusInOrder("Ohne Mwst")
                .checkFirmConfirmationStatus("ДА/auto")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPriceDE, totalPriceAWSOrderDE);
        order_aws.clickCustomerId();
        switchTo().window(2);
        new Customer_view_aws().checkPresenceBlockLogsCompanyNumbers()
                .checkIdCompanyInBlockLogsCompanyNumbers("BE0402065988")
                .checkResponseInBlockLogsCompanyNumbers("success(200)")
                .checkBillingOrShippingInBlockLogsCompanyNumbers("billing");
        totalPriceInEmailDE = new Mailinator().openEmail("qc_1392_autotestDE@mailinator.com")
                .openLetter(1)
                .checkAbsenceVatPercentageInEmail()
                .checkFirstFirmNameInEmail("SPRL Brasserie Cantillon")
                .checkSecondFirmNameInEmail("North 51 Ltd")
                .getTotalPriceInEmail();
        Assert.assertEquals(totalPriceDE, totalPriceInEmailDE);
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}