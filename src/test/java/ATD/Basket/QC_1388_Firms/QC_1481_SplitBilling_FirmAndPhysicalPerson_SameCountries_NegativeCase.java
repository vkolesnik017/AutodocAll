package ATD.Basket.QC_1388_Firms;

import ATD.CartAllData_page_Logic;
import ATD.Product_page_Logic;
import ATD.SetUp;
import AWS.Order_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import mailinator.Mailinator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.CommonMethods.password;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.switchTo;

public class QC_1481_SplitBilling_FirmAndPhysicalPerson_SameCountries_NegativeCase {

    private String email = "qc_1481_autotestEN@mailinator.com", orderNumber;
    private Double totalPrice, totalPriceAWSOrder, totalPriceInEmail;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProductsPL() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "EN", "main", "product2");
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
                .fillAllFieldsAndFirmForShipping("EN", "YO10 4NT", "Gear4music Limited", "York")
                .fillFieldIdCompanyShipping("552033282")
                .clickCheckboxBilling()
                .chooseDeliveryCountryForBilling("EN")
                .fillingPostalCodeFieldJSForBilling("12345")
                .nextBtnClick()
                .chosseUnicreditBank()
                .nextBtnClick()
                .checkTextContainingVatPercentage("incl. 20% VAT")
                .checkTextInDeliveryAddressInfoBlock("Company Gear4music Limited")
                .checkTextInPayersAddressInfoBlock("autotest autotest")
                .getTotalPriceAllDataPageForEnShop();
        orderNumber = new CartAllData_page_Logic().nextBtnClick().getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumber);
        totalPriceAWSOrder = order_aws.openOrderInAwsWithLogin()
                .checkVatStatusInOrder("Mit MwSt 20%")
                .checkFirmConfirmationStatus("Пров. вручную")
                .getTotalPriceOrder();
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
        totalPriceAWSOrder = order_aws.reSaveOrder()
                .checkVatStatusInOrder("Mit MwSt 20%")
                .checkFirmConfirmationStatus("Пров. вручную")
                .getTotalPriceOrder();
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
        order_aws.clickCustomerId()
                .checkAbsenceBlockLogsCompanyNumbers();
        switchTo().window(1);
        totalPriceInEmail = new Mailinator().openEmail("qc_1481_autotestEN@mailinator.com")
                .openLetter(1)
                .checkTextContainingVatPercentageInEmail("incl. 20% VAT")
                .checkSecondFirmNameInEmail("Gear4music Limited")
                .getTotalPriceInEmailForENShop();
        Assert.assertEquals(totalPrice, totalPriceInEmail);
    }
}