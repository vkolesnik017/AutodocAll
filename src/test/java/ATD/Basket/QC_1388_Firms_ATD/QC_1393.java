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

public class QC_1393 {

    private Float priceWithoutVAT, priceProductInAllDat,
            totalPriceBE, totalPriceAWSOrderBE, totalPriceInEmailBE, sellingPriceAWSOrderBE;
    private String emailBE = "QC_1393_autotestGB@autodoc.si", vatForBE, orderNumberBE;

    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private CartAllData_page_Logic cartAllData_page_logic = new CartAllData_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routeBE", parallel = true)
    Object[] dataProviderProductsBE() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "BE", "main", "product10");
    }

    @Test(dataProvider = "routeBE")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the successful execution of an order with split billing, firm and physical person, " +
                         "same countries, for BE shop. Positive Case")
    public void testSuccessfulPlacementOfOrder_SplitBillingAndPhysicalPerson_BE(String routeBE) {
        vatForBE = new PageVAT_aws().getVatForBE();
        openPage(routeBE);
        String shop = getCurrentShopFromJSVarInHTML();
        priceWithoutVAT = product_page_logic.getExactPriceWithoutVAT(vatForBE);
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(emailBE, password)
                .clickCheckboxForOpenBilling()
                .fillAllFields("BE")
                .fillAllFieldsAndFirmForBilling("BE", "1070", "Anderlecht", "SPRL Brasserie Cantillon")
                .fillFieldIdCompanyBilling("0402065988")
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .checkTextInDeliveryAddressInfoBlock("autotest autotest")
                .checkTextInPayersAddressInfoBlock("SPRL Brasserie Cantillon")
                .checkAbsenceOfVatPostscript();
        priceProductInAllDat = cartAllData_page_logic.getRegularProductPriceFormAllDataPage();
        product_page_logic.checkProductPriceOnSitesMatchesPriceOnAllDataPageIncludingVat(priceWithoutVAT, priceProductInAllDat);
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

        Assert.assertEquals(sellingPriceAWSOrderBE, priceProductInAllDat, 0.03f);
        order_aws.clickCustomerId();
        switchTo().window(1);
        new Customer_view_aws().checkPresenceBlockLogsCompanyNumbers()
                .checkIdCompanyInBlockLogsCompanyNumbers("BE0402065988")
                .checkResponseInBlockLogsCompanyNumbers("success(200)")
                .checkBillingOrShippingInBlockLogsCompanyNumbers("billing");

        totalPriceInEmailBE = new WebMail().openMail(emailBE, passwordForMail)
                .openLetter(1)
                .checkAbsenceVatPercentageInEmail()
                .checkFirstFirmNameInEmail("SPRL Brasserie Cantillon")
                .getTotalPriceInEmail();
        Assert.assertEquals(totalPriceBE, totalPriceInEmailBE);
    }


    private Float regularProductPricePerAllDataPageDE,
            totalPriceDE, totalPriceAWSOrderDE, totalPriceInEmailDE, sellingPriceAWSOrderDE;
    private String emailDE = "QC_1393_autotestDE@autodoc.si", orderNumberDE;

    @DataProvider(name = "routeDE", parallel = true)
    Object[] dataProviderProductsDE() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product32");
    }

    @Test(dataProvider = "routeDE")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the successful execution of an order with split billing and physical person, " +
            "same countries, for DE shop. Positive Case")
    public void testSuccessfulPlacementOfOrder_SplitBillingAndPhysicalPerson_DE(String routeDE) {
        openPage(routeDE);
        String shop = getCurrentShopFromJSVarInHTML();
        regularProductPricePerAllDataPageDE = product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(emailDE, password)
                .clickCheckboxForOpenBilling()
                .fillAllFields("BE")
                .fillAllFieldsAndFirmForBilling("BE", "1070", "Anderlecht", "SPRL Brasserie Cantillon")
                .fillFieldIdCompanyBilling("0402065988")
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .checkTextInDeliveryAddressInfoBlock("autotest autotest")
                .checkTextInPayersAddressInfoBlock("SPRL Brasserie Cantillon")
                .checkAbsenceOfVatPostscript()
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

        Assert.assertEquals(sellingPriceAWSOrderDE, regularProductPricePerAllDataPageDE, 0.03f);
        order_aws.clickCustomerId();
        switchTo().window(1);
        new Customer_view_aws().checkPresenceBlockLogsCompanyNumbers()
                .checkIdCompanyInBlockLogsCompanyNumbers("BE0402065988")
                .checkResponseInBlockLogsCompanyNumbers("success(200)")
                .checkBillingOrShippingInBlockLogsCompanyNumbers("billing");

        totalPriceInEmailDE = new WebMail().openMail(emailDE, passwordForMail)
                .openLetter(1)
                .checkAbsenceVatPercentageInEmail()
                .checkFirstFirmNameInEmail("SPRL Brasserie Cantillon")
                .getTotalPriceInEmail();
        Assert.assertEquals(totalPriceDE, totalPriceInEmailDE);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}