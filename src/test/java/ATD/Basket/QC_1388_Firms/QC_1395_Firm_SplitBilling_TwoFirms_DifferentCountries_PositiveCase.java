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

public class QC_1395_Firm_SplitBilling_TwoFirms_DifferentCountries_PositiveCase {


    private double regularProductPricePerAllDataPageGB, priceWithVatPerAllDataPageGB, priceProductPerProductPageGB,
            totalPriceGB, totalPriceAWSOrderGB, totalPriceInEmailGB, sellingPriceAWSOrderGB, prunedProductPriceGB, prunedPriceWithVatGB, unitPriceGB;
    private String emailGB = "qc_1395_autotestGB@mailinator.com", vatForGB, orderNumberGB;

    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private CartAllData_page_Logic cartAllData_page_logic = new CartAllData_page_Logic();
    private Mailinator mailinator = new Mailinator();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routeEN", parallel = true)
    Object[] dataProviderProductsEN() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "EN", "main", "product2");
    }

    @Test(dataProvider = "routeEN")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the successful execution of an order with split billing, two firms, the different countries, for EN shop. Positive Case.")
    public void testSuccessfulPlacementOfOrder_TwoFirm_DifferentCountries_EN(String routeEN) {
        vatForGB = new PageVAT_aws().getVatForGB();
        openPage(routeEN);
        priceWithVatPerAllDataPageGB = product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(emailGB, password)
                .fillAllFieldsAndFirmForShipping("EN", "YO10 4NT", "Gear4music Limited", "York")
                .fillFieldIdCompanyShipping("552033282")
                .fillAllFieldsAndFirmForBilling("ES", "12311", "Kalisz", "FB-MONT A. Fułek Spółka Komandytowa")
                .fillFieldIdCompanyBilling("6180029941")
                .nextBtnClick()
                .chosseUnicreditBank()
                .nextBtnClick()
                .checkAbsenceOfVatPercentage()
                .checkTextInDeliveryAddressInfoBlock("Company Gear4music Limited")
                .checkTextInPayersAddressInfoBlock("Company FB-MONT A. Fułek Spółka Komandytowa")
                .getPriceIncludingVatForEnShop(vatForGB);
        prunedPriceWithVatGB = cutPriceToFirstDecimalPlace(priceWithVatPerAllDataPageGB);
        cartAllData_page_logic.transitionToProductPage();
        switchTo().window(1);
        priceProductPerProductPageGB = product_page_logic.getProductPriceForEnShop();
        prunedProductPriceGB = cutPriceToFirstDecimalPlace(priceProductPerProductPageGB);
        Assert.assertEquals(prunedPriceWithVatGB, prunedProductPriceGB);
        product_page_logic.cartClick();
        totalPriceGB = cartAllData_page_logic.getTotalPriceAllDataPageForEnShop();
        orderNumberGB = cartAllData_page_logic.nextBtnClick().getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumberGB);
        totalPriceAWSOrderGB = order_aws.openOrderInAwsWithoutLoginAndCheckTestIcon()
                .checkVatStatusInOrder("Ohne Mwst")
                .checkFirmConfirmationStatus("ДА/auto")
                .getTotalPriceOrder();
        Assert.assertEquals(totalPriceGB, totalPriceAWSOrderGB);
        totalPriceAWSOrderGB = order_aws.reSaveOrder()
                .checkVatStatusInOrder("Ohne Mwst")
                .checkFirmConfirmationStatus("ДА/auto")
                .getTotalPriceOrder();
        Assert.assertEquals(totalPriceGB, totalPriceAWSOrderGB);
        sellingPriceAWSOrderGB = order_aws.getSellingPriceOrderAWS();
        switchTo().window(0);
        regularProductPricePerAllDataPageGB = cartAllData_page_logic.getRegularProductPriceForEnShop();
        Assert.assertEquals(sellingPriceAWSOrderGB, regularProductPricePerAllDataPageGB);
        switchTo().window(1);
        order_aws.clickCustomerId();
        switchTo().window(2);
        new Customer_view_aws().checkPresenceBlockLogsCompanyNumbers()
                .checkIdCompanyInBlockLogsCompanyNumbers("GB552033282")
                .checkResponseInBlockLogsCompanyNumbers("success(200)")
                .checkBillingOrShippingInBlockLogsCompanyNumbers("shipping");
        totalPriceInEmailGB = mailinator.openEmail("qc_1395_autotestGB@mailinator.com")
                .openLetter(1)
                .checkAbsenceVatPercentageInEmail()
                .checkFirstFirmNameInEmail("FB-MONT A. Fułek Spółka Komandytowa")
                .checkSecondFirmNameInEmail("Gear4music Limited")
                .getTotalPriceInEmailForENShop();
        Assert.assertEquals(totalPriceGB, totalPriceInEmailGB);
        unitPriceGB = mailinator.getUnitPriceForEnShop();
        Assert.assertEquals(regularProductPricePerAllDataPageGB, unitPriceGB);
    }


    private double regularProductPricePerAllDataPageDE, priceWithVatPerAllDataPageDE, priceProductPerProductPageDE,
            totalPriceDE, totalPriceAWSOrderDE, totalPriceInEmailDE, sellingPriceAWSOrderDE, prunedProductPriceDE, prunedPriceWithVatDE, unitPriceDE;
    private String emailDE = "qc_1395_autotestDE@mailinator.com", vatForDE, orderNumberDE;


    @DataProvider(name = "routeDE", parallel = true)
    Object[] dataProviderProductsDE() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "product2");
    }

    @Test(dataProvider = "routeDE")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the successful execution of an order with non-split billing and shipping, for DE shop. Positive Case")
    public void testSuccessfulPlacementOfOrder_NonSplitBillingAndShipping_DE(String routeDE) {
        vatForDE = new PageVAT_aws().getVatForDE();
        openPage(routeDE);
        priceWithVatPerAllDataPageDE = product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(emailDE, password)
                .fillAllFieldsAndFirmForShipping("EN", "YO10 4NT", "Gear4music Limited", "York")
                .fillFieldIdCompanyShipping("552033282")
                .fillAllFieldsAndFirmForBilling("ES", "12311", "Kalisz", "FB-MONT A. Fułek Spółka Komandytowa")
                .fillFieldIdCompanyBilling("6180029941")
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .checkAbsenceOfVatPercentage()
                .checkTextInDeliveryAddressInfoBlock("Firma Gear4music Limited")
                .checkTextInPayersAddressInfoBlock("Firma FB-MONT A. Fułek Spółka Komandytowa")
                .getPriceIncludingVatForDeShop(vatForDE);
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
                .getTotalPriceOrder();
        Assert.assertEquals(totalPriceDE, totalPriceAWSOrderDE);
        totalPriceAWSOrderDE = order_aws.reSaveOrder()
                .checkVatStatusInOrder("Ohne Mwst")
                .checkFirmConfirmationStatus("ДА/auto")
                .getTotalPriceOrder();
        Assert.assertEquals(totalPriceDE, totalPriceAWSOrderDE);
        sellingPriceAWSOrderDE = order_aws.getSellingPriceOrderAWS();
        switchTo().window(0);
        regularProductPricePerAllDataPageDE = cartAllData_page_logic.getRegularProductPriceForDeShop();
        Assert.assertEquals(sellingPriceAWSOrderDE, regularProductPricePerAllDataPageDE);
        switchTo().window(1);
        order_aws.clickCustomerId();
        switchTo().window(2);
        new Customer_view_aws().checkPresenceBlockLogsCompanyNumbers()
                .checkIdCompanyInBlockLogsCompanyNumbers("GB552033282")
                .checkResponseInBlockLogsCompanyNumbers("success(200)")
                .checkBillingOrShippingInBlockLogsCompanyNumbers("shipping");
        totalPriceInEmailDE = mailinator.openEmail("qc_1395_autotestDE@mailinator.com")
                .openLetter(1)
                .checkAbsenceVatPercentageInEmail()
                .checkFirstFirmNameInEmail("FB-MONT A. Fułek Spółka Komandytowa")
                .checkSecondFirmNameInEmail("Gear4music Limited")
                .getTotalPriceInEmail();
        Assert.assertEquals(totalPriceDE, totalPriceInEmailDE);
        unitPriceDE = mailinator.getUnitPriceInEmail();
        Assert.assertEquals(regularProductPricePerAllDataPageDE, unitPriceDE);
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}