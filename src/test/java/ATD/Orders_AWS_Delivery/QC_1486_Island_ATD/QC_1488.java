package ATD.Orders_AWS_Delivery.QC_1486_Island_ATD;

import ATD.CartAllData_page_Logic;
import ATD.Search_page_Logic;
import ATD.Versand_static_page_Logic;
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
import static mailinator.WebMail.passwordForMail;

public class QC_1488 {

    private String email = "QC_1488_autotest@autodoc.si", orderNumber;
    private Float totalPrice, totalPriceAWSOrder, totalPriceInEmail;
    private String deliveryPrice;

    @BeforeClass
    void setUp() throws Exception {
        setUpBrowser(false, "chrome", "77.0", false);
        deliveryPrice = new Versand_static_page_Logic().getDeliveryPrice("Frankreich");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "search9");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks verification of islands, billing is undivided (Negative case)")
    public void testChecksVerificationIslandsBillingIsUndividedNegativeCas(String route) {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        clickOfBuyBtnForAllPages();
        new Search_page_Logic().closePopupOtherCategoryIfYes()
                .cartClick().nextButtonClick()
                .signIn(email, password)
                .fillingPostalCodeFieldJSForShipping("97100")
                .nextBtnClick()
                .checkAbsenceOfPayPalMethod()
                .chooseVorkasse().nextBtnClick()
                .checkAbsenceOfVatPostscript()
                .checkRegularDeliveryPrice("165,00")
                .checkAbsenceSafeOrderBlock();
        totalPrice = new CartAllData_page_Logic().clickBtnReturnToCartAddressPage()
                .fillingPostalCodeFieldJSForShipping("33333")
                .nextBtnClick()
                .checkPresenceOfPayPalMethod()
                .chooseVorkasse().nextBtnClick()
                .checkPresenceSafeOrderBlock()
                .checkTextContainingVatPercentage("inkl. 20% MwSt.")
                .checkRegularDeliveryPrice(deliveryPrice)
                .getTotalPriceAllDataPage(shop);
        orderNumber = new CartAllData_page_Logic().nextBtnClick().getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumber);
        totalPriceAWSOrder = order_aws.openOrderInAwsWithLogin()
                .checkVatStatusInOrder("Mit MwSt 20%")
                .checkDeliveryPriceOrderAWS(deliveryPrice)
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
        order_aws.reSaveOrder()
                .checkVatStatusInOrder("Mit MwSt 20%")
                .checkDeliveryPriceOrderAWS(deliveryPrice);
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);

        totalPriceInEmail = new WebMail().openMail("QC_1488_autotest@autodoc.si", passwordForMail)
                .checkAndOpenLetterWithOrderNumber(orderNumber)
                .checkTextContainingVatPercentageInEmail("Inkl. 20% MwSt")
                .checkRegularDeliveryPriceInEmail(deliveryPrice)
                .getTotalPriceInEmail();
        Assert.assertEquals(totalPrice, totalPriceInEmail);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}