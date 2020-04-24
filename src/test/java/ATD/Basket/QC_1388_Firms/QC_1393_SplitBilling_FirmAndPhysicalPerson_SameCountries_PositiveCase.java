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

public class QC_1393_SplitBilling_FirmAndPhysicalPerson_SameCountries_PositiveCase {

    private double regularProductPricePerAllDataPageGB, priceWithVatPerAllDataPageGB, priceProductPerProductPageGB,
                   totalPriceGB, totalPriceAWSOrderGB, totalPriceInEmailGB, sellingPriceAWSOrderGB, prunedProductPriceGB, prunedPriceWithVatGB;
    private String emailGB = "qc_1393_autotestGB@mailinator.com", vatForGB, orderNumberGB;

    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private CartAllData_page_Logic cartAllData_page_logic = new CartAllData_page_Logic();

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
    @Description(value = "Test checks the successful execution of an order with split billing and physical person, " +
                         "same countries, for EN shop. Positive Case")
    public void testSuccessfulPlacementOfOrder_SplitBillingAndPhysicalPerson_EN(String routeEN) {
        vatForGB = new PageVAT_aws().getVatForGB();
        openPage(routeEN);
        String shop = getCurrentShopFromJSVarInHTML();
        priceWithVatPerAllDataPageGB = product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(emailGB, password)
                .clickCheckboxBilling()
                .fillAllFields(shop)
                .fillAllFieldsAndFirmForBilling(shop, "YO10 4NT", "York", "Gear4music Limited")
                .fillFieldIdCompanyBilling("552033282")
                .nextBtnClick()
                .chosseUnicreditBank()
                .nextBtnClick()
                .checkTextInDeliveryAddressInfoBlock("autotest autotest")
                .checkTextInPayersAddressInfoBlock("Company Gear4music Limited")
                .checkAbsenceOfVatPercentage()
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
                .checkBillingOrShippingInBlockLogsCompanyNumbers("billing");
        totalPriceInEmailGB = new Mailinator().openEmail("qc_1393_autotestGB@mailinator.com")
                .openLetter(1)
                .checkAbsenceVatPercentageInEmail()
                .checkFirstFirmNameInEmail("Gear4music Limited")
                .getTotalPriceInEmailForENShop();
        Assert.assertEquals(totalPriceGB, totalPriceInEmailGB);
    }


    private double regularProductPricePerAllDataPageDE, priceWithVatPerAllDataPageDE, priceProductPerProductPageDE,
            totalPriceDE, totalPriceAWSOrderDE, totalPriceInEmailDE, sellingPriceAWSOrderDE, prunedProductPriceDE, prunedPriceWithVatDE;
    private String emailDE = "qc_1393_autotestDE@mailinator.com", vatForDE, orderNumberDE;


    @DataProvider(name = "routeDE", parallel = true)
    Object[] dataProviderProductsDE() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "product2");
    }

    @Test(dataProvider = "routeDE")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the successful execution of an order with split billing and physical person, " +
            "same countries, for DE shop. Positive Case")
    public void testSuccessfulPlacementOfOrder_SplitBillingAndPhysicalPerson_DE(String routeDE) {
        vatForDE = new PageVAT_aws().getVatForDE();
        openPage(routeDE);
        String shop = getCurrentShopFromJSVarInHTML();
        priceWithVatPerAllDataPageDE = product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(emailDE, password)
                .clickCheckboxBilling()
                .fillAllFields("EN")
                .fillAllFieldsAndFirmForBilling("EN", "YO10 4NT", "York", "Gear4music Limited")
                .fillFieldIdCompanyBilling("552033282")
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .checkTextInDeliveryAddressInfoBlock("autotest autotest")
                .checkTextInPayersAddressInfoBlock("Firma Gear4music Limited")
                .checkAbsenceOfVatPercentage()
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
                .checkBillingOrShippingInBlockLogsCompanyNumbers("billing");
        totalPriceInEmailDE = new Mailinator().openEmail("qc_1393_autotestDE@mailinator.com")
                .openLetter(1)
                .checkAbsenceVatPercentageInEmail()
                .checkFirstFirmNameInEmail("Gear4music Limited")
                .getTotalPriceInEmail();
        Assert.assertEquals(totalPriceDE, totalPriceInEmailDE);
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}