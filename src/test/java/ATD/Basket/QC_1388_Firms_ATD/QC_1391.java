package ATD.Basket.QC_1388_Firms_ATD;

import ATD.CartAllData_page_Logic;
import ATD.Product_page_Logic;
import AWS.PageVAT_aws;
import Common.SetUp;
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

public class QC_1391 {

    private String emailPL = "QC_1391_autotestPL@autodoc.si", orderNumberPL;
    private Float totalPricePL, totalPriceAWSOrderPL, totalPriceInEmailPL;
    private PageVAT_aws pageVAT_aws = new PageVAT_aws();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routePL", parallel = true)
    Object[] dataProviderProductsPL() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "PL", "main", "product2");
    }

    @Test(dataProvider = "routePL")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the successful placement of the order indicating the company, split billing " +
            "and same countries for the PL shop. Country of shop == country of delivery")
    public void testSuccessfulPlacementOfOrder_Firm_SameCountries_PL(String routePL) {
        String vatForPL = pageVAT_aws.getVatForPL();
        openPage(routePL);
        String shop = getCurrentShopFromJSVarInHTML();
        totalPricePL = new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(emailPL, password)
                .fillAllFields(shop)
                .fillAllFieldsAndFirmForBilling(shop,"12345","Kaliszc", "FB-MONT A. Fułek Spółka Komandytowa")
                .fillFieldIdCompanyBilling("6180029941")
                .nextBtnClick()
                .choosePrzelewBankowy()
                .nextBtnClick()
                .checkTextInDeliveryAddressInfoBlock("autotest autotest")
                .checkTextInPayersAddressInfoBlock("Firma FB-MONT A. Fułek Spółka Komandytowa")
                .checkTextContainingVatPercentage("" + vatForPL + "% VAT")
                .getTotalPriceAllDataPage(shop);
        orderNumberPL = new CartAllData_page_Logic().nextBtnClick().getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumberPL);
        totalPriceAWSOrderPL = order_aws.openOrderInAwsWithoutLogin()
                .checkVatStatusInOrder("Mit MwSt " + vatForPL + "%")
                .checkFirmConfirmationStatus("Пров. вручную")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPricePL, totalPriceAWSOrderPL);
        totalPriceAWSOrderPL = order_aws.reSaveOrder()
                .checkVatStatusInOrder("Mit MwSt " + vatForPL + "%")
                .checkFirmConfirmationStatus("Пров. вручную")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPricePL, totalPriceAWSOrderPL);
        order_aws.clickCustomerId()
                .checkAbsenceBlockLogsCompanyNumbers();
        switchTo().window(1);

        totalPriceInEmailPL = new WebMail().openMail(emailPL, passwordForMail)
                .openLetter(1)
                .checkTextContainingVatPercentageInEmail("w tym " + vatForPL + "% VAT")
                .checkFirstFirmNameInEmail("FB-MONT A. Fułek Spółka Komandytowa")
                .getTotalPriceInEmail();
        Assert.assertEquals(totalPricePL, totalPriceInEmailPL);
    }


    private String emailDE = "QC_1391_autotestDE@autodoc.si", orderNumberDE;
    private Float totalPriceDE, totalPriceAWSOrderDE, totalPriceInEmailDE;

    @DataProvider(name = "routeDE", parallel = true)
    Object[] dataProviderProductsDE() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product2");
    }

    @Test(dataProvider = "routeDE")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the successful placement of the order indicating the company, split billing " +
            "and same countries for the DE shop. Country of shop == country of delivery")
    public void testSuccessfulPlacementOfOrder_Firm_SameCountries_DE(String routeDE) {
        String vatForDE = pageVAT_aws.getVatForDE();
        openPage(routeDE);
        String shop = getCurrentShopFromJSVarInHTML();
        totalPriceDE = new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(emailDE, password)
                .fillAllFields(shop)
                .fillAllFieldsAndFirmForBilling(shop,"12345","Berlin", "Autodoc GmbH")
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .checkTextInDeliveryAddressInfoBlock("autotest autotest")
                .checkTextInPayersAddressInfoBlock("Firma Autodoc GmbH")
                .checkTextContainingVatPercentage("inkl. " + vatForDE + "% MwSt")
                .getTotalPriceAllDataPage(shop);
        orderNumberDE = new CartAllData_page_Logic().nextBtnClick().getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumberDE);
        totalPriceAWSOrderDE = order_aws.openOrderInAwsWithoutLogin()
                .checkVatStatusInOrder("Mit MwSt " + vatForDE + "%")
                .checkFirmConfirmationStatus("Пров. вручную")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPriceDE, totalPriceAWSOrderDE);
        totalPriceAWSOrderDE = order_aws.reSaveOrder()
                .checkVatStatusInOrder("Mit MwSt " + vatForDE + "%")
                .checkFirmConfirmationStatus("Пров. вручную")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPriceDE, totalPriceAWSOrderDE);
        order_aws.clickCustomerId()
                .checkAbsenceBlockLogsCompanyNumbers();
        switchTo().window(1);

        totalPriceInEmailDE = new WebMail().openMail(emailDE, passwordForMail)
                .openLetter(1)
                .checkTextContainingVatPercentageInEmail("inkl. " + vatForDE + "% MwSt")
                .checkFirstFirmNameInEmail("Autodoc GmbH")
                .getTotalPriceInEmail();
        Assert.assertEquals(totalPriceDE, totalPriceInEmailDE);
    }

    private String emailES = "QC_1391_autotestES@autodoc.si", orderNumberES;
    private Float totalPriceES, totalPriceAWSOrderES, totalPriceInEmailES;

    @DataProvider(name = "routeES", parallel = true)
    Object[] dataProviderProductsES() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "ES", "main", "product2");
    }

    @Test(dataProvider = "routeES")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks successful order placement with indicating of company, split billing " +
            " and some countries and shipping to another country for ES shop. Country of shop != country of delivery")
    public void testSuccessfulPlacementOfOrder_Firm_SameCountries_ES(String routeES) {
        String vatForPL = pageVAT_aws.getVatForPL();
        openPage(routeES);
        String shop = getCurrentShopFromJSVarInHTML();
        totalPriceES = new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(emailES, password)
                .fillAllFields("PL")
                .fillAllFieldsAndFirmForBilling("PL","12345","Kaliszc", "FB-MONT A. Fułek Spółka Komandytowa")
                .fillFieldIdCompanyBilling("6180029941")
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .checkTextInDeliveryAddressInfoBlock("autotest autotest")
                .checkTextInPayersAddressInfoBlock("Entidad FB-MONT A. Fułek Spółka Komandytowa")
                .checkTextContainingVatPercentage("IVA incluido " + vatForPL + "%")
                .getTotalPriceAllDataPage(shop);
        orderNumberES = new CartAllData_page_Logic().nextBtnClick().getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumberES);
        totalPriceAWSOrderES = order_aws.openOrderInAwsWithoutLogin()
                .checkVatStatusInOrder("Mit MwSt " + vatForPL + "%")
                .checkFirmConfirmationStatus("Пров. вручную")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPriceES, totalPriceAWSOrderES);
        totalPriceAWSOrderES = order_aws.reSaveOrder()
                .checkVatStatusInOrder("Mit MwSt " + vatForPL + "%")
                .checkFirmConfirmationStatus("Пров. вручную")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPriceES, totalPriceAWSOrderES);
        order_aws.clickCustomerId()
                .checkAbsenceBlockLogsCompanyNumbers();
        switchTo().window(1);

        totalPriceInEmailES = new WebMail().openMail(emailES, passwordForMail)
                .openLetter(1)
                .checkTextContainingVatPercentageInEmail("IVA incluido " + vatForPL + "%")
                .checkFirstFirmNameInEmail("FB-MONT A. Fułek Spółka Komandytowa")
                .getTotalPriceInEmail();
        Assert.assertEquals(totalPriceES, totalPriceInEmailES);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}