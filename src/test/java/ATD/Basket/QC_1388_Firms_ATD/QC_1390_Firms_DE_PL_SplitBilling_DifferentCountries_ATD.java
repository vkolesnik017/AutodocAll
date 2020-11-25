package ATD.Basket.QC_1388_Firms_ATD;

import ATD.CartAllData_page_Logic;
import ATD.Product_page_Logic;
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

public class QC_1390_Firms_DE_PL_SplitBilling_DifferentCountries_ATD {

    private String emailPL = "QC_1390_autotestPL@autodoc.si", orderNumberPL;
    private Float totalPricePL, totalPriceAWSOrderPL, totalPriceInEmailPL;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routePL", parallel = true)
    Object[] dataProviderProductsPL() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "PL", "main", "product2");
    }

    @Test(dataProvider = "routePL")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the successful placement of the order indicating the company, split billing " +
                         "and different countries for the PL shop. Country of shop == country of delivery")
    public void testSuccessfulPlacementOfOrder_Firm_SplitBilling_PL(String routePL) {
        openPage(routePL);
        String shop = getCurrentShopFromJSVarInHTML();
        totalPricePL = new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(emailPL, password)
                .fillAllFieldsAndFirmForShipping(shop,"12345","FB-MONT A. Fułek Spółka Komandytowa", "Kalisz")
                .fillAllFieldsAndFirmForBilling("GB","12345","York", "Gear4music Limited")
                .fillFieldIdCompanyBilling("552033282")
                .nextBtnClick()
                .choosePrzelewBankowy()
                .nextBtnClick()
                .checkTextInDeliveryAddressInfoBlock("Firma FB-MONT A. Fułek Spółka")
                .checkTextInPayersAddressInfoBlock("Firma Gear4music Limited")
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

        totalPriceInEmailPL = new WebMail().openMail(emailPL, passwordForMail)
                .openLetter(1)
                .checkTextContainingVatPercentageInEmail("w tym 23% VAT")
                .checkFirstFirmNameInEmail("Gear4music Limited")
                .checkSecondFirmNameInEmail("FB-MONT A. Fułek Spółka Komandytowa")
                .getTotalPriceInEmail();
        Assert.assertEquals(totalPricePL, totalPriceInEmailPL);
    }

    private String emailDE = "QC_1390_autotestDE@autodoc.si", orderNumberDE;
    private Float totalPriceDE, totalPriceAWSOrderDE, totalPriceInEmailDE;

    @DataProvider(name = "routeDE", parallel = true)
    Object[] dataProviderProductsDE() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product2");
    }

    @Test(dataProvider = "routeDE")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the successful placement of the order indicating the company, split billing " +
            "and different countries for the DE shop. Country of shop == country of delivery")
    public void testSuccessfulPlacementOfOrder_Firm_SplitBilling_DE(String routePL) {
        openPage(routePL);
        String shop = getCurrentShopFromJSVarInHTML();
        totalPriceDE = new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(emailDE, password)
                .fillAllFieldsAndFirmForShipping(shop, "12345","Autodoc GmbH", "Berlin")
                .fillAllFieldsAndFirmForBilling("GB","12345","York", "Gear4music Limited")
                .fillFieldIdCompanyBilling("552033282")
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .checkTextInDeliveryAddressInfoBlock("Firma Autodoc GmbH")
                .checkTextInPayersAddressInfoBlock("Firma Gear4music Limited")
                .checkTextContainingVatPercentage("inkl. 16% MwSt")
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

        totalPriceInEmailDE = new WebMail().openMail(emailDE, passwordForMail)
                .openLetter(1)
                .checkTextContainingVatPercentageInEmail("inkl. 16% MwSt")
                .checkFirstFirmNameInEmail("Gear4music Limited")
                .checkSecondFirmNameInEmail("Autodoc GmbH")
                .getTotalPriceInEmail();
        Assert.assertEquals(totalPriceDE, totalPriceInEmailDE);
    }

    private String emailES = "QC_1390_autotestES@autodoc.si", orderNumberES;
    private Float totalPriceES, totalPriceAWSOrderES, totalPriceInEmailES;

    @DataProvider(name = "routeES", parallel = true)
    Object[] dataProviderProductsES() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "ES", "main", "product2");
    }

    @Test(dataProvider = "routeES")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks successful order placement with indicating of company, split billing, " +
                         "different countries and shipping to another country for ES shop. Country of shop != country of delivery")
    public void testSuccessfulPlacementOfOrder_Firm_SplitBilling_ES(String routePL) {
        openPage(routePL);
        String shop = getCurrentShopFromJSVarInHTML();
        totalPriceES = new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(emailES, password)
                .fillAllFieldsAndFirmForShipping( "DE","12345","Autodoc GmbH", "Berlin")
                .fillAllFieldsAndFirmForBilling("GB","12345","York", "Gear4music Limited")
                .fillFieldIdCompanyBilling("552033282")
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .checkTextInDeliveryAddressInfoBlock("Entidad Autodoc GmbH")
                .checkTextInPayersAddressInfoBlock("Entidad Gear4music Limited")
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

        totalPriceInEmailES = new WebMail().openMail(emailES, passwordForMail)
                .openLetter(1)
                .checkTextContainingVatPercentageInEmail("IVA incluido 16%")
                .checkFirstFirmNameInEmail("Gear4music Limited")
                .checkSecondFirmNameInEmail("Autodoc GmbH")
                .getTotalPriceInEmail();
        Assert.assertEquals(totalPriceES, totalPriceInEmailES);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}