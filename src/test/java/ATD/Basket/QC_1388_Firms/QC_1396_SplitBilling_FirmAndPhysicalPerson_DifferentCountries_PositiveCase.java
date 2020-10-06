package ATD.Basket.QC_1388_Firms;

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

public class QC_1396_SplitBilling_FirmAndPhysicalPerson_DifferentCountries_PositiveCase {

    private Float priceWithoutVAT, priceProductInAllData,
            totalPriceBE, totalPriceAWSOrderBE, totalPriceInEmailBE, sellingPriceAWSOrderBE, unitPriceBE;
    private String emailGB = "QC_1396_autotestGB@autodoc.si", vatForBE, orderNumberBE;

    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private CartAllData_page_Logic cartAllData_page_logic = new CartAllData_page_Logic();
    private WebMail webMail = new WebMail();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routeBE", parallel = true)
    Object[] dataProviderProductsEN() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "BE", "main", "product10");
    }

    @Test(dataProvider = "routeBE")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the successful execution of an order with split billing, firm and physical person, " +
            "Different Countries, for BE shop. Positive Case")
    public void testSuccessfulPlacementOfOrder_FirmAndPhysicalPerson_DifferentCountries_BE(String routeBE) {
        vatForBE = new PageVAT_aws().getVatForBE();
        openPage(routeBE);
        String shop = getCurrentShopFromJSVarInHTML();
        priceWithoutVAT = product_page_logic.getExactPriceWithoutVAT(vatForBE);
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(emailGB, password)
                .fillAllFieldsAndFirmForShipping("BE", "1070", "SPRL Brasserie Cantillon", "Anderlecht")
                .fillFieldIdCompanyShipping("0402065988")
                .clickCheckboxForOpenBilling()
                .chooseDeliveryCountryForBilling("PL")
                .fillingPostalCodeFieldJSForBilling("11111")
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .checkAbsenceOfVatPercentage()
                .checkTextInDeliveryAddressInfoBlock("Bedrijf SPRL Brasserie Cantillon")
                .checkTextInPayersAddressInfoBlock("autotest autotest");
        priceProductInAllData = cartAllData_page_logic.getRegularProductPriceFormAllDataPage();
        cartAllData_page_logic.checkProductPriceOnSitesMatchesPriceOnAllDataPageIncludingVat(priceWithoutVAT, priceProductInAllData);
        totalPriceBE = cartAllData_page_logic.getTotalPriceAllDataPage(shop);
        orderNumberBE = cartAllData_page_logic.nextBtnClick().getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumberBE);
        totalPriceAWSOrderBE = order_aws.openOrderInAwsWithoutLoginAndCheckTestIcon()
                .checkVatStatusInOrder("Ohne Mwst")
                .checkFirmConfirmationStatus("ДА/auto")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPriceBE, totalPriceAWSOrderBE);
        totalPriceAWSOrderBE = order_aws.reSaveOrder()
                .checkVatStatusInOrder("Ohne Mwst")
                .checkFirmConfirmationStatus("ДА/auto")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPriceBE, totalPriceAWSOrderBE);
        sellingPriceAWSOrderBE = order_aws.getSellingProductPriceOrderAWS();

        // TODO включу данный ассерт после исправлениея дефекта AWS-2830
        /*Assert.assertEquals(sellingPriceAWSOrderBE, priceProductInAllData);*/
        order_aws.clickCustomerId();
        switchTo().window(1);
        new Customer_view_aws().checkPresenceBlockLogsCompanyNumbers()
                .checkIdCompanyInBlockLogsCompanyNumbers("BE0402065988")
                .checkResponseInBlockLogsCompanyNumbers("success(200)")
                .checkBillingOrShippingInBlockLogsCompanyNumbers("shipping");

        totalPriceInEmailBE = webMail.openMail(emailGB, passwordForMail)
                .openLetter(1)
                .checkAbsenceVatPercentageInEmail()
                .checkSecondFirmNameInEmail("SPRL Brasserie Cantillon")
                .getTotalPriceInEmail();
        Assert.assertEquals(totalPriceBE, totalPriceInEmailBE);
        unitPriceBE = webMail.getUnitPriceInEmail();
        Assert.assertEquals(priceProductInAllData, unitPriceBE);
    }


    private Float regularProductPricePerAllDataPageDE,
            totalPriceDE, totalPriceAWSOrderDE, totalPriceInEmailDE, sellingPriceAWSOrderDE, unitPriceDE;
    private String emailDE = "QC_1396_autotestDE@autodoc.si", vatForИУ, orderNumberDE;

    @DataProvider(name = "routeDE", parallel = true)
    Object[] dataProviderProductsDE() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product32");
    }

    @Test(dataProvider = "routeDE")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the successful execution of an order with split billing, firm and physical person, " +
            "Different Countries, for DE shop. Positive Case. Positive Case")
    public void testSuccessfulPlacementOfOrder_NonSplitBillingAndShipping_DE(String routeDE) {
        openPage(routeDE);
        String shop = getCurrentShopFromJSVarInHTML();
        regularProductPricePerAllDataPageDE = product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(emailDE, password)
                .fillAllFieldsAndFirmForShipping("BE", "1070", "SPRL Brasserie Cantillon", "Anderlecht")
                .fillFieldIdCompanyShipping("0402065988")
                .clickCheckboxForOpenBilling()
                .chooseDeliveryCountryForBilling("PL")
                .fillingPostalCodeFieldJSForBilling("11111")
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .checkAbsenceOfVatPercentage()
                .checkTextInDeliveryAddressInfoBlock("Firma SPRL Brasserie Cantillon")
                .checkTextInPayersAddressInfoBlock("autotest autotest")
                .getRegularProductPriceFormAllDataPage();
        totalPriceDE = cartAllData_page_logic.getTotalPriceAllDataPage(shop);
        orderNumberDE = cartAllData_page_logic.nextBtnClick().getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumberDE);
        totalPriceAWSOrderDE = order_aws.openOrderInAwsWithLogin()
                .checkVatStatusInOrder("Ohne Mwst")
                .checkFirmConfirmationStatus("ДА/auto")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPriceDE, totalPriceAWSOrderDE);
        totalPriceAWSOrderDE = order_aws.reSaveOrder()
                .checkVatStatusInOrder("Ohne Mwst")
                .checkFirmConfirmationStatus("ДА/auto")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPriceDE, totalPriceAWSOrderDE);
        sellingPriceAWSOrderDE = order_aws.getSellingProductPriceOrderAWS();
        Assert.assertEquals(sellingPriceAWSOrderDE, regularProductPricePerAllDataPageDE);
        order_aws.clickCustomerId();
        switchTo().window(1);
        new Customer_view_aws().checkPresenceBlockLogsCompanyNumbers()
                .checkIdCompanyInBlockLogsCompanyNumbers("BE0402065988")
                .checkResponseInBlockLogsCompanyNumbers("success(200)")
                .checkBillingOrShippingInBlockLogsCompanyNumbers("shipping");

        totalPriceInEmailDE = webMail.openMail(emailDE, passwordForMail)
                .openLetter(1)
                .checkAbsenceVatPercentageInEmail()
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