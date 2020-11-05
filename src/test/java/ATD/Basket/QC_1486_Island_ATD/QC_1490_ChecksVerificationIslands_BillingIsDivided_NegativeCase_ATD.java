package ATD.Basket.QC_1486_Island_ATD;

import ATD.CartAllData_page_Logic;
import ATD.Search_page_Logic;
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

public class QC_1490_ChecksVerificationIslands_BillingIsDivided_NegativeCase_ATD {

    private String email = "QC_1490_autotest@autodoc.si",orderNumber;
    private Float totalPrice, totalPriceAWSOrder, totalPriceInEmail;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "search9");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks verification of islands, billing is divided (Negative case)")
    public void testChecksVerificationIslandsBillingIsDividedNegativeCas(String route) {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        clickOfBuyBtnForAllPages();
        totalPrice = new Search_page_Logic().closePopupOtherCategoryIfYes()
                .cartClick().nextButtonClick()
                .signIn(email, password)
                .nextBtnClick()
                .checkPresenceOfPayPalMethod()
                .chooseVorkasse().nextBtnClick()
                .checkTextContainingVatPercentage("inkl. 20% MwSt.")
                .checkRegularDeliveryPrice("9,95")
                .checkPresenceSafeOrderBlock()
                .getTotalPriceAllDataPage(shop);
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

        totalPriceInEmail = new WebMail().openMail("QC_1490_autotest@autodoc.si", passwordForMail)
                .checkAndOpenLetterWithOrderNumber(orderNumber)
                .checkRegularDeliveryPriceInEmail("9,95")
                .checkTextContainingVatPercentageInEmail("Inkl. 20% MwSt")
                .getTotalPriceInEmail();
        Assert.assertEquals(totalPrice, totalPriceInEmail);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
