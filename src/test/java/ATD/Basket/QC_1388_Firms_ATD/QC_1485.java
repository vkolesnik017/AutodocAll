package ATD.Basket.QC_1388_Firms_ATD;

import ATD.CartAddress_page_Logic;
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

public class QC_1485 {

    private Float regularProductPricePerAllDataPage, priceWithVatPerAllDataPage, priceProductPerProductPage,
            totalPrice, totalPriceAWSOrder, totalPriceInEmail, sellingPriceAWSOrder, unitPrice;
    private String email = "QC_1485_autotestEN@autodoc.si", vatForEN, orderNumber;

    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private CartAllData_page_Logic cartAllData_page_logic = new CartAllData_page_Logic();
    private CartAddress_page_Logic cartAddress_page_logic = new CartAddress_page_Logic();
    private WebMail webMail = new WebMail();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "EN", "main", "product10");
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
                .fillAllFieldsAndFirmForShipping("BE", "1070", "SPRL Brasserie Cantillon", "Anderlecht")
                .fillFieldIdCompanyShipping("04020")
                .nextBtnClick();
        priceWithVatPerAllDataPage = cartAddress_page_logic.checkPresencePopupErrorAboutWrongCompany()
                .clickBtnFixInPopupAboutWrongCompany()
                .fillFieldIdCompanyShipping("0402065988")
                .nextBtnClick()
                .chooseUnicreditBank()
                .nextBtnClick()
                .checkTextInDeliveryAddressInfoBlock("Company SPRL Brasserie Cantillon")
                .checkAbsenceOfVatPostscript()
                .getPriceIncludingVat(vatForEN);
        cartAllData_page_logic.transitionToProductPage();
        switchTo().window(1);
        priceProductPerProductPage = product_page_logic.getProductPrice();
        Assert.assertEquals(priceWithVatPerAllDataPage, priceProductPerProductPage, 0.02f);
        product_page_logic.cartClick();
        totalPrice = cartAllData_page_logic.getTotalPriceAllDataPageForEnShop();
        orderNumber = cartAllData_page_logic.nextBtnClick().getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumber);
        totalPriceAWSOrder = order_aws.openOrderInAwsWithoutLoginAndCheckTestIcon()
                .checkVatStatusInOrder("Ohne Mwst")
                .checkFirmConfirmationStatus("????/auto")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
        totalPriceAWSOrder = order_aws.reSaveOrder()
                .checkVatStatusInOrder("Ohne Mwst")
                .checkFirmConfirmationStatus("????/auto")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
        sellingPriceAWSOrder = order_aws.getSellingProductPriceOrderAWS();
        switchTo().window(0);
        regularProductPricePerAllDataPage = cartAllData_page_logic.getRegularProductPriceFormAllDataPage();
        Assert.assertEquals(sellingPriceAWSOrder, regularProductPricePerAllDataPage, 0.03f);

        switchTo().window(1);
        order_aws.clickCustomerId();
        switchTo().window(2);
        new Customer_view_aws().checkPresenceBlockLogsCompanyNumbers()
                .checkIdCompanyInBlockLogsCompanyNumbers("BE0402065988")
                .checkResponseInBlockLogsCompanyNumbers("success(200)")
                .checkBillingOrShippingInBlockLogsCompanyNumbers("shipping");

        totalPriceInEmail = webMail.openMail(email, passwordForMail)
                .openLetter(1)
                .checkAbsenceVatPercentageInEmail()
                .checkFirstFirmNameInEmail("SPRL Brasserie Cantillon")
                .getTotalPriceInEmail();
        Assert.assertEquals(totalPrice, totalPriceInEmail);
        unitPrice = webMail.getUnitPriceInEmail();
        Assert.assertEquals(regularProductPricePerAllDataPage, unitPrice);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}