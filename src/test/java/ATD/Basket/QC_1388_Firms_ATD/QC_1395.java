package ATD.Basket.QC_1388_Firms_ATD;

import ATD.CartAllData_page_Logic;
import ATD.Product_page_Logic;
import Common.SetUp;
import AWS.Customer_view_aws;
import AWS.Order_aws;
import AWS.PageVAT_aws;
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

public class QC_1395 {


    private Float priceWithoutVAT, priceProductInAllData,
            totalPriceBE, totalPriceAWSOrderBE, totalPriceInEmailBE, sellingPriceAWSOrderBE, unitPriceBE;
    private String emailBE = "QC_1395_autotestGB@autodoc.si", vatForBE, orderNumberBE;

    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private CartAllData_page_Logic cartAllData_page_logic = new CartAllData_page_Logic();
    private WebMail webMail = new WebMail();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routeEN", parallel = true)
    Object[] dataProviderProductsEN() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "BE", "main", "product10");
    }

    @Test(dataProvider = "routeEN")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the successful execution of an order with split billing, two firms, the different countries, for BE shop. Positive Case.")
    public void testSuccessfulPlacementOfOrder_TwoFirm_DifferentCountries_BE(String routeEN) {
        vatForBE = new PageVAT_aws().getVatForBE();
        openPage(routeEN);
        String shop = getCurrentShopFromJSVarInHTML();
        priceWithoutVAT = product_page_logic.getExactPriceWithoutVAT(vatForBE);
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(emailBE, password)
                .fillAllFieldsAndFirmForShipping("BE", "1070", "SPRL Brasserie Cantillon","Anderlecht")
                .fillFieldIdCompanyShipping("0402065988")
                .fillAllFieldsAndFirmForBilling("ES", "12311", "Kalisz", "FB-MONT A. Fu??ek Sp????ka Komandytowa")
                .fillFieldIdCompanyBilling("6180029941")
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .checkAbsenceOfVatPostscript()
                .checkTextInDeliveryAddressInfoBlock("SPRL Brasserie Cantillon")
                .checkTextInPayersAddressInfoBlock("FB-MONT A. Fu??ek Sp????ka Komandytowa");
        priceProductInAllData = cartAllData_page_logic.getRegularProductPriceFormAllDataPage();
        product_page_logic.checkProductPriceOnSitesMatchesPriceOnAllDataPageIncludingVat(priceWithoutVAT, priceProductInAllData);
        totalPriceBE = cartAllData_page_logic.getTotalPriceAllDataPage(shop);
        orderNumberBE = cartAllData_page_logic.nextBtnClick().getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumberBE);
        totalPriceAWSOrderBE = order_aws.openOrderInAwsWithoutLoginAndCheckTestIcon()
                .checkVatStatusInOrder("Ohne Mwst")
                .checkFirmConfirmationStatus("????/auto")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPriceBE, totalPriceAWSOrderBE);
        totalPriceAWSOrderBE = order_aws.reSaveOrder()
                .checkVatStatusInOrder("Ohne Mwst")
                .checkFirmConfirmationStatus("????/auto")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPriceBE, totalPriceAWSOrderBE);
        sellingPriceAWSOrderBE = order_aws.getSellingProductPriceOrderAWS();

        Assert.assertEquals(sellingPriceAWSOrderBE, priceProductInAllData, 0.03f);
        order_aws.clickCustomerId();
        switchTo().window(1);
        new Customer_view_aws().checkPresenceBlockLogsCompanyNumbers()
                .checkIdCompanyInBlockLogsCompanyNumbers("BE0402065988")
                .checkResponseInBlockLogsCompanyNumbers("success(200)")
                .checkBillingOrShippingInBlockLogsCompanyNumbers("shipping");

        totalPriceInEmailBE = webMail.openMail(emailBE, passwordForMail)
                .openLetter(1)
                .checkAbsenceVatPercentageInEmail()
                .checkFirstFirmNameInEmail("FB-MONT A. Fu??ek Sp????ka Komandytowa")
                .checkSecondFirmNameInEmail("SPRL Brasserie Cantillon")
                .getTotalPriceInEmail();
        Assert.assertEquals(totalPriceBE, totalPriceInEmailBE);
        unitPriceBE = webMail.getUnitPriceInEmail();
        Assert.assertEquals(priceProductInAllData, unitPriceBE);
    }


    private Float regularProductPricePerAllDataPageDE, totalPriceDE, totalPriceAWSOrderDE, totalPriceInEmailDE,
                  sellingPriceAWSOrderDE, unitPriceDE;
    private String emailDE = "QC_1395_autotestDE@autodoc.si", orderNumberDE;


    @DataProvider(name = "routeDE", parallel = true)
    Object[] dataProviderProductsDE() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product32");
    }

    @Test(dataProvider = "routeDE")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the successful execution of an order with non-split billing and shipping, for DE shop. Positive Case")
    public void testSuccessfulPlacementOfOrder_NonSplitBillingAndShipping_DE(String routeDE) {
        openPage(routeDE);
        String shop = getCurrentShopFromJSVarInHTML();
        regularProductPricePerAllDataPageDE = product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(emailDE, password)
                .fillAllFieldsAndFirmForShipping("BE", "1070", "SPRL Brasserie Cantillon","Anderlecht")
                .fillFieldIdCompanyShipping("0402065988")
                .fillAllFieldsAndFirmForBilling("ES", "12311", "Kalisz", "FB-MONT A. Fu??ek Sp????ka Komandytowa")
                .fillFieldIdCompanyBilling("6180029941")
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .checkAbsenceOfVatPostscript()
                .checkTextInDeliveryAddressInfoBlock("Firma SPRL Brasserie Cantillon")
                .checkTextInPayersAddressInfoBlock("Firma FB-MONT A. Fu??ek Sp????ka Komandytowa")
                .getRegularProductPriceFormAllDataPage();
        totalPriceDE = cartAllData_page_logic.getTotalPriceAllDataPage(shop);
        orderNumberDE = cartAllData_page_logic.nextBtnClick().getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumberDE);
        totalPriceAWSOrderDE = order_aws.openOrderInAwsWithLogin()
                .checkVatStatusInOrder("Ohne Mwst")
                .checkFirmConfirmationStatus("????/auto")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPriceDE, totalPriceAWSOrderDE);
        totalPriceAWSOrderDE = order_aws.reSaveOrder()
                .checkVatStatusInOrder("Ohne Mwst")
                .checkFirmConfirmationStatus("????/auto")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPriceDE, totalPriceAWSOrderDE);
        sellingPriceAWSOrderDE = order_aws.getSellingProductPriceOrderAWS();
        
        Assert.assertEquals(sellingPriceAWSOrderDE, regularProductPricePerAllDataPageDE, 0.03f);
        order_aws.clickCustomerId();
        switchTo().window(1);
        new Customer_view_aws().checkPresenceBlockLogsCompanyNumbers()
                .checkIdCompanyInBlockLogsCompanyNumbers("BE0402065988")
                .checkResponseInBlockLogsCompanyNumbers("success(200)")
                .checkBillingOrShippingInBlockLogsCompanyNumbers("shipping");

        totalPriceInEmailDE = webMail.openMail(emailDE, passwordForMail)
                .openLetter(1)
                .checkAbsenceVatPercentageInEmail()
                .checkFirstFirmNameInEmail("FB-MONT A. Fu??ek Sp????ka Komandytowa")
                .checkSecondFirmNameInEmail("SPRL Brasserie Cantillon")
                .getTotalPriceInEmail();
        Assert.assertEquals(totalPriceDE, totalPriceInEmailDE);
        unitPriceDE = webMail.getUnitPriceInEmail();
        Assert.assertEquals(regularProductPricePerAllDataPageDE, unitPriceDE);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}