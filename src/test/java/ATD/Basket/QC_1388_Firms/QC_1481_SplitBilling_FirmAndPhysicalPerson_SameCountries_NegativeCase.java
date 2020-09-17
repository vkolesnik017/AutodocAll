package ATD.Basket.QC_1388_Firms;

import ATD.CartAllData_page_Logic;
import ATD.Product_page_Logic;
import Common.SetUp;
import AWS.Order_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.CommonMethods.password;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.switchTo;

public class QC_1481_SplitBilling_FirmAndPhysicalPerson_SameCountries_NegativeCase {

    private String email = "qc_1481_autotestEN@mailinator.com", orderNumber;
    private Float totalPrice, totalPriceAWSOrder, totalPriceInEmail;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProductsPL() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "EN", "main", "product2");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the successful execution of an order with split billing and physical person, same countries. Negative Case")
    public void testSuccessfulPlacementOfOrder_FirmAndPhysicalPerson_NegativeCase(String route) {
        openPage(route);
        totalPrice = new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(email, password)
                .fillAllFieldsAndFirmForShipping("BE", "1070", "SPRL Brasserie Cantillon", "Anderlecht")
                .fillFieldIdCompanyShipping("0402065988")
                .clickCheckboxForOpenBilling()
                .chooseDeliveryCountryForBilling("BE")
                .fillingPostalCodeFieldJSForBilling("12345")
                .nextBtnClick()
                .chooseUnicreditBank()
                .nextBtnClick()
                .checkTextContainingVatPercentage("incl. 21% VAT")
                .checkTextInDeliveryAddressInfoBlock("Company SPRL Brasserie Cantillon")
                .checkTextInPayersAddressInfoBlock("autotest autotest")
                .getTotalPriceAllDataPageForEnShop();
        orderNumber = new CartAllData_page_Logic().nextBtnClick().getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumber);
        totalPriceAWSOrder = order_aws.openOrderInAwsWithLogin()
                .checkVatStatusInOrder("Mit MwSt 21%")
                .checkFirmConfirmationStatus("Пров. вручную")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
        totalPriceAWSOrder = order_aws.reSaveOrder()
                .checkVatStatusInOrder("Mit MwSt 21%")
                .checkFirmConfirmationStatus("Пров. вручную")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
        order_aws.clickCustomerId()
                .checkAbsenceBlockLogsCompanyNumbers();
        switchTo().window(1);
        //TODO отключен по техническим обстоятельствам
        /*totalPriceInEmail = new Mailinator().openEmail("qc_1481_autotestEN@mailinator.com")
                .openLetter(1)
                .checkTextContainingVatPercentageInEmail("incl. 21% VAT")
                .checkSecondFirmNameInEmail("SPRL Brasserie Cantillon")
                .getTotalPriceInEmail();
        Assert.assertEquals(totalPrice, totalPriceInEmail);*/
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}