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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.CommonMethods.password;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2855 {

    private String mail = "QC_2855_autotestATD@mailinator.com";
    private Order_aws order_aws = new Order_aws();

    private String orderNumber;

    @BeforeClass
    void setUp() throws SQLException {
        setUpBrowser(false, "chrome", "77.0", false);
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
        orderNumber = new Payment_handler_page_Logic().getOrderNumber();
        new Order_aws(orderNumber).openOrderInAwsWithLogin();
    }

    @DataProvider(name = "indexes")
    Object[] dataProviderProducts() {
        return new Object[][]{
                {"0001-000"},
                {"0111-111"},
                {"0550-505"},
                {"0999-999"},
                {"0999-000"},
                {"0998-111"},
                {"0000-000"},
                {"9999-001"},
                {"9999-550"},
                {"9999-999"},
                {"9999-998"},
                {"9999-000"}
        };
    }

    @Test(dataProvider = "indexes")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checking ban on entering non-existent index when editing order in aws for Portugal")
    public void testCheckingBAnOnEnteringNonexistentIndexWhenEditingOrderInAWS_PT(String indexes) {
        new Order_aws().fillingFieldsPostalCodInBilling(indexes)
                .fillingFieldsPostalCodInShipping("1111-111")
                .reSaveOrder()
                .checkCurrentStatusInOrder("Neue Bestellung")
                .checkPresenceExpectedElement(order_aws.errorIconInFieldPostalConForBilling())
                .checkAbsenceExpectedElement(order_aws.errorIconInFieldPostalConForShipping())
                .fillingFieldsPostalCodInBilling("1111-111")
                .fillingFieldsPostalCodInShipping(indexes)
                .reSaveOrder()
                .checkCurrentStatusInOrder("Neue Bestellung")
                .checkPresenceExpectedElement(order_aws.errorIconInFieldPostalConForShipping())
                .checkAbsenceExpectedElement(order_aws.errorIconInFieldPostalConForBilling());
    }

    @AfterClass
    private void close() {
        new Order_aws().fillingFieldsPostalCodInBilling("1111-111")
                .fillingFieldsPostalCodInShipping("1111-111")
                .reSaveOrder()
                .checkCurrentStatusInOrder("Testbestellungen");
        closeWebDriver();
    }
}
