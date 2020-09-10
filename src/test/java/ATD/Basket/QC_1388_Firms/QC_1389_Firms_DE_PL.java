package ATD.Basket.QC_1388_Firms;

import ATD.CartAllData_page_Logic;
import ATD.Product_page_Logic;
import Common.SetUp;
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
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.switchTo;


public class QC_1389_Firms_DE_PL {

    private String emailPL = "qc_1389_autotestPL@mailinator.com", orderNumberPL;
    private Float totalPricePL, totalPriceAWSOrderPL, totalPriceInEmailPL;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routePL", parallel = true)
    Object[] dataProviderProductsPL() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "PL", "main", "product2");
    }

    @Test(dataProvider = "routePL")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the successful placement of the order indicating the company for the PL shop. " +
                         "Country of shop == country of delivery")
    public void testSuccessfulPlacementOfOrder_Firm_PL(String routePL) {
        openPage(routePL);
        String shop = getCurrentShopFromJSVarInHTML();
        totalPricePL = new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(emailPL, password)
                .fillAllFieldsAndFirmForShipping(shop, "12345","FB-MONT A. Fułek Spółka Komandytowa", "Kalisz")
                .nextBtnClick()
                .choosePrzelewBankowy()
                .nextBtnClick()
                .checkTextInDeliveryAddressInfoBlock("Firma FB-MONT A. Fułek Spółka Komandytowa")
                .checkTextContainingVatPercentage("23% VAT")
                .getTotalPriceAllDataPage(shop);
        orderNumberPL = new CartAllData_page_Logic().nextBtnClick().getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumberPL);
        totalPriceAWSOrderPL = order_aws.openOrderInAwsWithLogin()
                .checkVatStatusInOrder("Mit MwSt 23%")
                .checkFirmConfirmationStatus("Пров. вручную")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPricePL, totalPriceAWSOrderPL);
        totalPriceAWSOrderPL = order_aws.reSaveOrder()
                .checkVatStatusInOrder("Mit MwSt 23%")
                .checkFirmConfirmationStatus("Пров. вручную")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPricePL, totalPriceAWSOrderPL);
        order_aws.clickCustomerId()
                .checkAbsenceBlockLogsCompanyNumbers();
        switchTo().window(1);
        totalPriceInEmailPL = new Mailinator().openEmail("qc_1389_autotestPL@mailinator.com")
                .openLetter(1)
                .checkTextContainingVatPercentageInEmail("w tym 23% VAT")
                .checkFirstFirmNameInEmail("FB-MONT A. Fułek Spółka Komandytowa")
                .getTotalPriceInEmail();
        Assert.assertEquals(totalPricePL, totalPriceInEmailPL);
    }

    private String emailDE = "qc_1389_autotestDE@mailinator.com", orderNumberDE;
    private Float totalPriceDE, totalPriceAWSOrderDE, totalPriceInEmailDE;

    @DataProvider(name = "routeDE", parallel = true)
    Object[] dataProviderProductsDE() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "product2");
    }

    @Test(dataProvider = "routeDE")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the successful placement of the order indicating the company for the DE shop. " +
                         "Country of shop == country of delivery")
    public void testSuccessfulPlacementOfOrder_Firm_DE(String routeDE) {
        openPage(routeDE);
        String shop = getCurrentShopFromJSVarInHTML();
        totalPriceDE = new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(emailDE, password)
                .fillAllFieldsAndFirmForShipping(shop, "12345","Autodoc GmbH", "Berlin")
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .checkTextInDeliveryAddressInfoBlock("Firma Autodoc GmbH")
                .checkTextContainingVatPercentage("inkl. 16% MwSt.")
                .getTotalPriceAllDataPage(shop);
        orderNumberDE = new CartAllData_page_Logic().nextBtnClick().getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumberDE);
        totalPriceAWSOrderDE = order_aws.openOrderInAwsWithLogin()
                .checkVatStatusInOrder("Mit MwSt 16%")
                .checkFirmConfirmationStatus("Пров. вручную")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPriceDE, totalPriceAWSOrderDE);
        totalPriceAWSOrderDE = order_aws.reSaveOrder()
                .checkVatStatusInOrder("Mit MwSt 16%")
                .checkFirmConfirmationStatus("Пров. вручную")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPriceDE, totalPriceAWSOrderDE);
        order_aws.clickCustomerId()
                .checkAbsenceBlockLogsCompanyNumbers();
        switchTo().window(1);
        totalPriceInEmailDE = new Mailinator().openEmail("qc_1389_autotestDE@mailinator.com")
                .openLetter(1)
                .checkTextContainingVatPercentageInEmail("inkl. 16% MwSt")
                .checkFirstFirmNameInEmail("Autodoc GmbH")
                .getTotalPriceInEmail();
        Assert.assertEquals(totalPriceDE, totalPriceInEmailDE);
    }

    private String emailES = "qc_1389_autotestES@mailinator.com", orderNumberES;
    private Float totalPriceES, totalPriceAWSOrderES, totalPriceInEmailES;

    @DataProvider(name = "routeES", parallel = true)
    Object[] dataProviderProductsES() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "ES", "main", "product2");
    }

    @Test(dataProvider = "routeES")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the successful placement of the order indicating the company and " +
                         "delivery to another country, from the ES shop. Country of shop != country of delivery")
    public void testSuccessfulPlacementOfOrder_Firm_ES(String routeES) {
        openPage(routeES);
        String shop = getCurrentShopFromJSVarInHTML();
    totalPriceES = new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(emailES, password)
                .fillAllFieldsAndFirmForShipping("DE", "12345", "Autodoc GmbH", "Berlin")
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .checkTextInDeliveryAddressInfoBlock("Entidad Autodoc GmbH")
                .checkTextContainingVatPercentage("IVA incluido 16%")
                .getTotalPriceAllDataPage(shop);
    orderNumberES = new CartAllData_page_Logic().nextBtnClick().getOrderNumber();
    Order_aws order_aws = new Order_aws(orderNumberES);
    totalPriceAWSOrderES = order_aws.openOrderInAwsWithLogin()
            .checkVatStatusInOrder("Mit MwSt 16%")
                .checkFirmConfirmationStatus("Пров. вручную")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPriceES, totalPriceAWSOrderES);
        totalPriceAWSOrderES = order_aws.reSaveOrder()
                .checkVatStatusInOrder("Mit MwSt 16%")
                .checkFirmConfirmationStatus("Пров. вручную")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPriceES, totalPriceAWSOrderES);
        order_aws.clickCustomerId()
                .checkAbsenceBlockLogsCompanyNumbers();
    switchTo().window(1);
    totalPriceInEmailES = new Mailinator().openEmail("qc_1389_autotestES@mailinator.com")
                .openLetter(1)
                .checkTextContainingVatPercentageInEmail("IVA incluido 16%")
                .checkFirstFirmNameInEmail("Autodoc GmbH")
                .getTotalPriceInEmail();
        Assert.assertEquals(totalPriceES, totalPriceInEmailES);
}

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}