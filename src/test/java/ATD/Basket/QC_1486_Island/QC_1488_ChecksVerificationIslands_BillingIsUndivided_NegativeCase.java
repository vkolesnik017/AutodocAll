package ATD.Basket.QC_1486_Island;

import ATD.CartAllData_page_Logic;
import ATD.Search_page_Logic;
import ATD.SetUp;
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
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_1488_ChecksVerificationIslands_BillingIsUndivided_NegativeCase {

    private String email = "qc_1488_autotestDE@mailinator.com", orderNumber;
    private Float totalPrice, totalPriceAWSOrder, totalPriceInEmail;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "search9");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks verification of islands, billing is undivided (Negative case)")
    public void testChecksVerificationIslandsBillingIsUndividedNegativeCas(String route) {
        openPage(route);
        clickOfBuyBtnForAllPages();
        new Search_page_Logic().closePopupOtherCategoryIfYes()
                .cartClick().nextButtonClick()
                .signIn(email, password)
                .fillingPostalCodeField("97100")
                .nextBtnClick()
                .checkAbsenceOfPayPalMethod()
                .chooseVorkasse().nextBtnClick()
                .checkAbsenceOfVatPercentage()
                .checkRegularDeliveryPriceAllData("165,00")
                .checkAbsenceSafeOrderBlock();
        totalPrice = new CartAllData_page_Logic().clickBtnReturnToCartAddressPage()
                .fillingPostalCodeField("33333")
                .nextBtnClick()
                .checkAbsenceOfPayPalMethod()
                .chooseVorkasse().nextBtnClick()
                .checkPresenceSafeOrderBlock()
                .checkTextContainingVatPercentage("inkl. 20% MwSt.")
                .checkRegularDeliveryPriceAllData("9,95")
                .getTotalPriceAllDataPage();
        orderNumber = new CartAllData_page_Logic().nextBtnClick().getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumber);
        totalPriceAWSOrder = order_aws.openOrderInAwsWithLogin()
                .checkVatStatusInOrder("Mit MwSt 20%")
                .checkDeliveryPriceOrderAWS("9.95")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
        order_aws.reSaveOrder()
                .checkVatStatusInOrder("Mit MwSt 20%")
                .checkDeliveryPriceOrderAWS("9.95");
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
        totalPriceInEmail = new Mailinator().openEmail("qc_1488_autotestDE@mailinator.com")
                .openLetter(1)
                .checkTextContainingVatPercentageInEmail("Inkl. 20% MwSt.")
                .checkRegularDeliveryPriceInEmail("9,95")
                .getTotalPriceInEmail();
        Assert.assertEquals(totalPrice, totalPriceInEmail);
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}