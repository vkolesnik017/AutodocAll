package ATD.Basket.QC_1388_Firms;

import ATD.*;
import AWS.Customer_view_aws;
import AWS.Order_aws;
import AWS.PageVAT_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import mailinator.Mailinator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static ATD.CommonMethods.cutPriceToFirstDecimalPlace;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.switchTo;

public class QC_1485_FirmValidationAfterError {

    private double regularProductPricePerAllDataPage, priceWithVatPerAllDataPage, priceProductPerProductPage,
            totalPrice, totalPriceAWSOrder, totalPriceInEmail, sellingPriceAWSOrder, prunedProductPrice, prunedPriceWithVat, unitPrice;
    private String email = "qc_1485_autotestEN@mailinator.com", vatForEN, orderNumber;

    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private CartAllData_page_Logic cartAllData_page_logic = new CartAllData_page_Logic();
    private CartAddress_page_Logic cartAddress_page_logic = new CartAddress_page_Logic();
    private Mailinator mailinator = new Mailinator();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "EN", "main", "product2");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the successful execution of an order and checks validation firm after error")
    public void testChecksFirmValidationAfterError(String route) {
        vatForEN = new PageVAT_aws().getVatForGB();
        openPage(route);
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(email, password)
                .fillAllFieldsAndFirmForShipping("EN", "YO10 4NT", "Gear4music Limited", "York")
                .fillFieldIdCompanyShipping("55203")
                .nextBtnClick();
        priceWithVatPerAllDataPage = cartAddress_page_logic.checkPresencePopupErrorAboutWrongCompany()
                .clickBtnFixInPopupAboutWrongCompany()
                .fillFieldIdCompanyShipping("552033282")
                .nextBtnClick()
                .chosseUnicreditBank()
                .nextBtnClick()
                .checkTextInDeliveryAddressInfoBlock("Company Gear4music Limited")
                .checkAbsenceOfVatPercentage()
                .getPriceIncludingVatForEnShop(vatForEN);
        prunedPriceWithVat = cutPriceToFirstDecimalPlace(priceWithVatPerAllDataPage);
        cartAllData_page_logic.transitionToProductPage();
        switchTo().window(1);
        priceProductPerProductPage = product_page_logic.getProductPriceForEnShop();
        prunedProductPrice = cutPriceToFirstDecimalPlace(priceProductPerProductPage);
        Assert.assertEquals(prunedPriceWithVat, prunedProductPrice);
        product_page_logic.cartClick();
        totalPrice = cartAllData_page_logic.getTotalPriceAllDataPageForEnShop();
        orderNumber = cartAllData_page_logic.nextBtnClick().getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumber);
        totalPriceAWSOrder = order_aws.openOrderInAwsWithoutLoginAndCheckTestIcon()
                .checkVatStatusInOrder("Ohne Mwst")
                .checkFirmConfirmationStatus("ДА/auto")
                .getTotalPriceOrder();
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
        totalPriceAWSOrder = order_aws.reSaveOrder()
                .checkVatStatusInOrder("Ohne Mwst")
                .checkFirmConfirmationStatus("ДА/auto")
                .getTotalPriceOrder();
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
        sellingPriceAWSOrder = order_aws.getSellingPriceOrderAWS();
        switchTo().window(0);
        regularProductPricePerAllDataPage = cartAllData_page_logic.getRegularProductPriceForEnShop();
        Assert.assertEquals(sellingPriceAWSOrder, regularProductPricePerAllDataPage);
        switchTo().window(1);
        order_aws.clickCustomerId();
        switchTo().window(2);
        new Customer_view_aws().checkPresenceBlockLogsCompanyNumbers()
                .checkIdCompanyInBlockLogsCompanyNumbers("GB552033282")
                .checkResponseInBlockLogsCompanyNumbers("success(200)")
                .checkBillingOrShippingInBlockLogsCompanyNumbers("shipping");
        totalPriceInEmail = mailinator.openEmail("qc_1485_autotestEN@mailinator.com")
                .openLetter(1)
                .checkAbsenceVatPercentageInEmail()
                .checkFirstFirmNameInEmail("Gear4music Limited")
                .getTotalPriceInEmailForENShop();
        Assert.assertEquals(totalPrice, totalPriceInEmail);
        unitPrice = mailinator.getUnitPriceForEnShop();
        Assert.assertEquals(regularProductPricePerAllDataPage, unitPrice);



    }
}