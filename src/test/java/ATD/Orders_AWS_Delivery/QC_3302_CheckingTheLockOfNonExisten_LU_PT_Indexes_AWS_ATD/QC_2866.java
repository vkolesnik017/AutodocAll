package ATD.Orders_AWS_Delivery.QC_3302_CheckingTheLockOfNonExisten_LU_PT_Indexes_AWS_ATD;

import ATD.Payment_handler_page_Logic;
import ATD.Product_page_Logic;
import AWS.Order_aws;
import Common.DataBase;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.CommonMethods.password;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2866 {

    private String mail = "QC_2866_autotestATD@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @Test()
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks that Invalid Index is not saved after exiting an order in AWS. Portugal")

    public void testInvalidIndexIsNotSavedAfterExitingAnOrderInAWS_LU() throws SQLException {
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "PT", "main", "product32"));
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .fillingPostalCodeFieldJSForShipping("1111-111")
                .chooseDeliveryCountryForShipping("PT")
                .fillFieldTelNumForShipping("100+001")
                .fillFieldFirmNameForShipping("autotest")
                .fillInCompanyIdFieldForCountryWhereIdNeeded("PT", "PT", "autotest")
                .clickOnTheDesiredPaymentMethod("PT", "Bank")
                .nextBtnClick()
                .nextBtnClick();
        String orderNumber = new Payment_handler_page_Logic().getOrderNumber();
        new Order_aws(orderNumber).openOrderInAwsWithLogin()
                .fillingFieldsPostalCodInBilling("0000-000")
                .fillingFieldsPostalCodInShipping("0000-000")
                .reSaveOrder()
                .checkCurrentStatusInOrder("Neue Bestellung")
                .checkPresenceExpectedElement(new Order_aws().errorIconInFieldPostalConForBilling())
                .checkPresenceExpectedElement(new Order_aws().errorIconInFieldPostalConForShipping())
                .clickOrderSearchTad()
                .fillingFieldOrderId(orderNumber)
                .clickSearchBtn()
                .clickOnOrderLinc(orderNumber)
                .checkPostalCodInBillingBlock("1111-111")
                .checkPostalCodInShippingBlock("1111-111")
                .reSaveOrder()
                .checkCurrentStatusInOrder("Testbestellungen");
    }

    @AfterClass
    private void close() {
        closeWebDriver();
    }

}
